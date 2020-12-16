/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ylz.entity;

import java.io.Serializable;


/**
 * 用户的java类形式
 *
 * @author paida 派哒 zeyu.pzy@alibaba-inc.com
 */
public class MybatisDemoUser implements Serializable {

    /**
     * 编号
     */
    private String id;

    /**
     * 用户名
     */
    private Integer age;

    /**
     * 密码
     */
    private Integer height;

    private Integer weight;


    public String getId() {
        return id;
    }

    public MybatisDemoUser setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public MybatisDemoUser setAge(Integer age)
    {
        this.age=age;
        return this;
    }


    public Integer getHeight() {
        return height;
    }

    public MybatisDemoUser setHeight(Integer height)
    {
        this.height=height;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public MybatisDemoUser setWeight(Integer weight)
    {
        this.weight=weight;
        return this;
    }
}
