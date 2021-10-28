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

import com.google.common.collect.Range;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

/**
 * 分库算法
 */
public final class StandardModuloShardingDatabaseAlgorithm implements StandardShardingAlgorithm<Integer> {
    
    @Override
    public void init() {
    }
    
    @Override
    public String doSharding(final Collection<String> databaseNames, final PreciseShardingValue<Integer> shardingValue) {
        for (String each : databaseNames) {
            if (each.endsWith(String.valueOf(shardingValue.getValue() % databaseNames.size()))) {
                return each;
            }
        }
        throw new UnsupportedOperationException("");
    }

    /**
     * 这种区间的值，要是有序，连续的值
     * @param databaseNames
     * @param shardingValueRange
     * @return
     */
    @Override
    public Collection<String> doSharding(final Collection<String> databaseNames, final RangeShardingValue<Integer> shardingValueRange) {
        /*Set<String> result = new LinkedHashSet<>();

        if (Range.closed(1, 5).encloses(shardingValueRange.getValueRange())) {
            for (String each : databaseNames) {
                if (each.endsWith("0")) {
                    result.add(each);
                }
            }
        } else if (Range.closed(6, 10).encloses(shardingValueRange.getValueRange())) {
            for (String each : databaseNames) {
                if (each.endsWith("1")) {
                    result.add(each);
                }
            }
        } else if (Range.closed(1, 10).encloses(shardingValueRange.getValueRange())) {
            result.addAll(databaseNames);
        } else {
            throw new UnsupportedOperationException("");
        }
        return result;*/
        throw new UnsupportedOperationException("不支持的分片值");
    }

    @Override
    public Properties getProps() {
        return new Properties();
    }

    @Override
    public void setProps(final Properties properties) {

    }

    @Override
    public String getType() {
        return "STANDARD_TEST_DB_DEMO";
    }
}
