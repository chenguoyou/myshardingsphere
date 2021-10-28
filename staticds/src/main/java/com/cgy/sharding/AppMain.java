package com.cgy.sharding;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cgy.sharding.entity.OrgUserEntity;
import com.cgy.sharding.service.IOrgUserService;
import com.cgy.sharding.sevice.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Wrapper;
import java.util.List;

/**
 * @Description TODO
 * @ClassName AppMain.java
 * @Author chenguoyou
 * @Version 1.0.0
 * @CreateTime 2021/4/9
 */

@Slf4j
public class AppMain {

    public static void main(String[] args) {
        //ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/.xml"); //此种方式会加载 logback.xml 会导致解析异常
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
        //ITestService testService = (ITestService) applicationContext.getBean("testServiceImpl");

        //String str = testService.test("this is a test");

        //log.info("str={}", str);

        //System.out.println(str);

        IOrgUserService orgUserService = (IOrgUserService) applicationContext.getBean("orgUserServiceImpl");
        /*for (int i = 0; i < 10; i++) {
            OrgUserEntity entity = new OrgUserEntity();
            entity.setOrgId(1L);
            entity.setOrgCode("123" + i);
            entity.setOrgName("测试组");
            entity.setComId(1L);
            orgUserService.saveEntity(entity);
        }
        log.info("插入成功");*/

        /*LambdaQueryWrapper<OrgUserEntity> query = Wrappers.lambdaQuery(OrgUserEntity.class);
        //query.eq(OrgUserEntity::getOuId, 1384309221048741889L);
        query.eq(OrgUserEntity::getOrgName,"测试组");
        List<OrgUserEntity> entity = orgUserService.list(query);
        log.info("entity={}", JSON.toJSONString(entity));*/

        LambdaQueryWrapper<OrgUserEntity> query = Wrappers.lambdaQuery(OrgUserEntity.class);
        query.eq(OrgUserEntity::getOrgName,"测试组");
        Page<OrgUserEntity> page = new Page<>(1,10);
        IPage<OrgUserEntity> list = orgUserService.page(page,query);

        log.info("page={}", JSON.toJSONString(list));

    }
}
