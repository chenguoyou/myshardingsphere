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

package com.cgy.dynamicds.algorithm;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Range;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

/**
 * 分表算法
 */
@Slf4j
public final class StandardModuloShardingTableAlgorithm implements StandardShardingAlgorithm<Long> {

    @Override
    public void init() {
    }

    @Override
    public String doSharding(final Collection<String> tableNames, final PreciseShardingValue<Long> shardingValue) {
        log.info("分表算法：tableNames={},shardingValue={}", JSON.toJSONString(tableNames), JSON.toJSONString(shardingValue));
        //只分库不分表，所以返回固定表名
        String tableName = tableNames.stream().findFirst().get();
        log.info("物理表：tableNames={}", tableNames);
        return tableName;
    }

    @Override
    public Collection<String> doSharding(final Collection<String> tableNames, final RangeShardingValue<Long> shardingValue) {
        Set<String> result = new LinkedHashSet<>();
        log.info("分表算法：tableNames={},shardingValue={}", JSON.toJSONString(tableNames), JSON.toJSONString(shardingValue));
        //只分库不分表，所以返回固定表名
        String tableName = tableNames.stream().findFirst().get();
        log.info("物理表：tableNames={}", tableNames);
        result.add(tableName);
        return result;
    }

    @Override
    public String getType() {
        return "STANDARD_TEST_TBL_DEMO";
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void setProps(Properties props) {

    }
}
