package com.ylz.mapper;

import com.ylz.req.CommonSqlReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description: 因子库动态SQL操作Dao
 * @Author: Nomark
 * @CreateDate: 2019/8/31 18:22
 * @Version: v1.0
 */
@Repository
@Mapper
public interface DynamicStatementMapper {
    /*
     * @Description: 获取字符型单值
     * @Author: Nomark
     * @Date: 2019/8/31 18:23
     * @Param: [sql]
     * @Return: java.lang.String
     */
    String getSingleStringValue(@Param("sql") String sqlStatement);

    /*
     * @Description:返回单列记录
     * @Author: Nomark
     * @Date: 2019/9/8 17:29
     * @Param: [sqlStatement]
     * @Return: java.util.Map
     */
    Map getSingleObject(@Param("sql") String sqlStatement);

    /*
     * @Description:返回单行记录
     * @Author: Nomark
     * @Date: 2019/9/8 17:29
     * @Param: [sqlStatement]
     * @Return: java.util.Map
     */
    Map getSingleMap(@Param("sql") String sqlStatement);

    /*
     * @Description: 查询要插入和更新的表中是否有满足条件数据
     * @Author: Nomark
     * @Date: 2019/9/5 14:43
     * @Param: [commonReq]
     * @Return: java.lang.Integer
     */
    Integer queryInterfaceIfExists(CommonSqlReq commonReq);

    /*
     * @Description: 动态插入记录接口
     * @Author: Nomark
     * @Date: 2019/9/6 23:46
     * @Param: [commonReq]
     * @Return: boolean
     */
    boolean insertInterface(CommonSqlReq commonReq);

    /*
     * @Description: 动态更新接口
     * @Author: Nomark
     * @Date: 2019/9/6 23:47
     * @Param: [commonReq]
     * @Return: boolean
     */
    boolean updateInterface(CommonSqlReq commonReq);

    /*
     * @Description: 公共查询List接口
     * @Author: Nomark
     * @Date: 2019/9/9 0:09
     * @Param: [sql]
     * @Return: java.util.List<java.util.Map>
     */
    List<Map> commonQueryList(@Param("sql") String sql);
}
