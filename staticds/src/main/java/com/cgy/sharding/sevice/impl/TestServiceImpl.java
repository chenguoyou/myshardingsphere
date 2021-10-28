package com.cgy.sharding.sevice.impl;

import com.cgy.sharding.sevice.ITestService;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @ClassName TestServiceImpl.java
 * @Author chenguoyou
 * @Version 1.0.0
 * @CreateTime 2021/4/9
 */

@Service
public class TestServiceImpl implements ITestService {

    @Override
    public String test(String param) {
        return param;
    }
}
