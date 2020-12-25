/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.ylz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ZipUtil;
import lombok.RequiredArgsConstructor;
import com.ylz.domain.GenConfig;
import com.ylz.domain.ColumnInfo;
import com.ylz.domain.vo.TableInfo;
import com.ylz.exception.BadRequestException;
import com.ylz.repository.ColumnInfoRepository;
import com.ylz.service.GeneratorService;
import com.ylz.utils.FileUtil;
import com.ylz.utils.GenUtil;
import com.ylz.utils.PageUtil;
import com.ylz.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Zheng Jie
 * @date 2019-01-02
 */
@Service
@RequiredArgsConstructor
public class GeneratorServiceImpl implements GeneratorService {
    private static final Logger log = LoggerFactory.getLogger(GeneratorServiceImpl.class);
    @PersistenceContext
    private EntityManager em;

    private final ColumnInfoRepository columnInfoRepository;

    @Override
    public Object getTables() {
        // 使用预编译防止sql注入
//        String sql = "select table_name ,create_time , engine, table_collation, table_comment from information_schema.tables " +
//                "where table_schema = (select database()) " +
//                "order by create_time desc";
        String sql = "select a.table_name ,a.LAST_ANALYZED,  b.comments as table_comment from " +
                " user_tables a, user_tab_comments b  where a.TABLE_NAME = b.TABLE_NAME ";
        Query query = em.createNativeQuery(sql);
        return query.getResultList();
    }

    @Override
    public Object getTables(String name, int[] startEnd) {
        // 使用预编译防止sql注入
//        String sql = "select table_name ,create_time , engine, table_collation, table_comment from information_schema.tables " +
//                "where table_schema = (select database()) " +
//                "and table_name like ? order by create_time desc";
        String sql = "select a.table_name ,a.LAST_ANALYZED as create_time, '' as engine, '' as table_collation, b.comments as table_comment from  user_tables a " +
                ", user_tab_comments b  where a.TABLE_NAME = b.TABLE_NAME " +
                "and a.table_name like ?  order by a.table_name";
        Query query = em.createNativeQuery(sql);
        query.setFirstResult(startEnd[0]);
        query.setMaxResults(startEnd[1] - startEnd[0]);
        query.setParameter(1, StringUtils.isNotBlank(name) ? ("%" + name + "%") : "%%");
        List result = query.getResultList();
        List<TableInfo> tableInfos = new ArrayList<>();
        for (Object obj : result) {
            Object[] arr = (Object[]) obj;
            tableInfos.add(new TableInfo(arr[0], arr[1], arr[2], arr[3], ObjectUtil.isNotEmpty(arr[4]) ? arr[4] : "-"));
        }
//        Query query1 = em.createNativeQuery("SELECT COUNT(*) from information_schema.tables where table_schema = (select database())");
        Query query1 = em.createNativeQuery("SELECT COUNT(*) from user_tables ");
        Object totalElements = query1.getSingleResult();
        return PageUtil.toPage(tableInfos, totalElements);
    }

    @Override
    public List<ColumnInfo> getColumns(String tableName) {
        List<ColumnInfo> columnInfos = columnInfoRepository.findByTableNameOrderByIdAsc(tableName);
        if (CollectionUtil.isNotEmpty(columnInfos)) {
            return columnInfos;
        } else {
            columnInfos = query(tableName);
            return columnInfoRepository.saveAll(columnInfos);
        }
    }

    @Override
    public List<ColumnInfo> query(String tableName) {
        // 使用预编译防止sql注入
//        String sql = "select column_name, is_nullable, data_type, column_comment, column_key, extra from information_schema.columns " +
//                "where table_name = ? and table_schema = (select database()) order by ordinal_position";
        String sql = "select a.column_name,a.NULLABLE as is_nullable,a.data_type,b.COMMENTS,(select con.constraint_type " +
                "from user_constraints con,  user_cons_columns col " +
                "where con.constraint_name = col.constraint_name " +
                "and (con.constraint_type='P' or con.constraint_type='U') " +
                "and col.table_name = b.TABLE_NAME and col.COLUMN_NAME = a.COLUMN_NAME) as column_key , '' as extra " +
                "from user_tab_columns a,user_col_comments b " +
                "where a.COLUMN_NAME=b.COLUMN_NAME and a.Table_Name=? and b.TABLE_NAME = a.Table_Name " +
                "order by a.column_name";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, tableName);
        List result = query.getResultList();
        List<ColumnInfo> columnInfos = new ArrayList<>();
        for (Object obj : result) {
            Object[] arr = (Object[]) obj;
            String keyType = " ";
            if (arr[4] != null && arr[4].equals("P")){
                keyType = "PRI";
            }else if (arr[4] != null && arr[4].equals("U")){
                keyType = "UNI";
            }
            columnInfos.add(
                    new ColumnInfo(
                            tableName,
                            arr[0].toString(),
                            "NO".equals(arr[1]),
                            arr[2].toString(),
                            ObjectUtil.isNotNull(arr[3]) ? arr[3].toString() : "",
                            keyType,
                            ObjectUtil.isNotNull(arr[5]) ? arr[5].toString() : null)
            );
        }
        return columnInfos;
    }

    @Override
    public void sync(List<ColumnInfo> columnInfos, List<ColumnInfo> columnInfoList) {
        // 第一种情况，数据库类字段改变或者新增字段
        for (ColumnInfo columnInfo : columnInfoList) {
            // 根据字段名称查找
            List<ColumnInfo> columns = columnInfos.stream().filter(c -> c.getColumnName().equals(columnInfo.getColumnName())).collect(Collectors.toList());
            // 如果能找到，就修改部分可能被字段
            if (CollectionUtil.isNotEmpty(columns)) {
                ColumnInfo column = columns.get(0);
                column.setColumnType(columnInfo.getColumnType());
                column.setExtra(columnInfo.getExtra());
                column.setKeyType(columnInfo.getKeyType());
                if (StringUtils.isBlank(column.getRemark())) {
                    column.setRemark(columnInfo.getRemark());
                }
                columnInfoRepository.save(column);
            } else {
                // 如果找不到，则保存新字段信息
                columnInfo.setId(IdUtil.fastSimpleUUID());
                columnInfoRepository.save(columnInfo);
            }
        }
        // 第二种情况，数据库字段删除了
        for (ColumnInfo columnInfo : columnInfos) {
            // 根据字段名称查找
            List<ColumnInfo> columns = columnInfoList.stream().filter(c -> c.getColumnName().equals(columnInfo.getColumnName())).collect(Collectors.toList());
            // 如果找不到，就代表字段被删除了，则需要删除该字段
            if (CollectionUtil.isEmpty(columns)) {
                columnInfoRepository.delete(columnInfo);
            }
        }
    }

    @Override
    public void save(List<ColumnInfo> columnInfos) {
        columnInfoRepository.saveAll(columnInfos);
    }

    @Override
    public void generator(GenConfig genConfig, List<ColumnInfo> columns) {
        if (genConfig.getId() == null) {
            throw new BadRequestException("请先配置生成器");
        }
        try {
            GenUtil.generatorCode(columns, genConfig);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BadRequestException("生成失败，请手动处理已生成的文件");
        }
    }

    @Override
    public ResponseEntity<Object> preview(GenConfig genConfig, List<ColumnInfo> columns) {
        if (genConfig.getId() == null) {
            throw new BadRequestException("请先配置生成器");
        }
        List<Map<String, Object>> genList = GenUtil.preview(columns, genConfig);
        return new ResponseEntity<>(genList, HttpStatus.OK);
    }

    @Override
    public void download(GenConfig genConfig, List<ColumnInfo> columns, HttpServletRequest request, HttpServletResponse response) {
        if (genConfig.getId() == null) {
            throw new BadRequestException("请先配置生成器");
        }
        try {
            File file = new File(GenUtil.download(columns, genConfig));
            String zipPath = file.getPath() + ".zip";
            ZipUtil.zip(file.getPath(), zipPath);
            FileUtil.downloadFile(request, response, new File(zipPath), true);
        } catch (IOException e) {
            throw new BadRequestException("打包失败");
        }
    }
}
