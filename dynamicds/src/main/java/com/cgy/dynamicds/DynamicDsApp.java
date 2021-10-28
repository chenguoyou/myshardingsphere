package com.cgy.dynamicds;

import com.alibaba.fastjson.JSON;
import com.cgy.dynamicds.entity.OrgUserEntity;
import com.cgy.dynamicds.mapper.OrgUserMapper;
import com.github.pagehelper.PageInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sound.midi.Soundbank;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Description TODO
 * @ClassName DynamicDsApp.java
 * @Author chenguoyou
 * @Version 1.0.0
 * @CreateTime 2021/4/20
 */

@Slf4j
public class DynamicDsApp {

    public static void main(String[] args) throws SQLException {

        System.out.println("this is a test……");
        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(createDataSourceMap(), Collections.singleton(createShardingRuleConfiguration()), new Properties());
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");

        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        PageInterceptor pageHelper = new PageInterceptor();
        configuration.addInterceptor(pageHelper); //设置分页插件
        //加入资源

        //* <mapper resource="ssm/BlogMapper.xml"/>

        //configuration.addLoadedResource("com/cgy/dynamicds/mapper/OrgUserMapper.xml");

        configuration.addMapper(OrgUserMapper.class);
        //configuration.addMappers("com.cgy.dynamicds.mapper");


        SqlSessionFactory sqlSessionFactoryTemp = new SqlSessionFactoryBuilder().build(configuration);
        System.out.println(sqlSessionFactoryTemp);


        SqlSession session= sqlSessionFactoryTemp.openSession();

        OrgUserMapper mapper = session.getMapper(OrgUserMapper.class);

        List<OrgUserEntity> list = mapper.pageList();

        //List<OrgUserEntity> list = session.selectList("com.cgy.dynamicds.mapper.OrgUserMapper.pageList");

        /*IOrgUserService orgUserService = (IOrgUserService) applicationContext.getBean("orgUserServiceImpl");

        LambdaQueryWrapper<OrgUserEntity> query = Wrappers.lambdaQuery(OrgUserEntity.class);
        query.eq(OrgUserEntity::getOuId, 1384309221048741889L);
        List<OrgUserEntity> entity = orgUserService.list(query);*/
        log.info("entity={}", JSON.toJSONString(list));

    }

    private static ShardingRuleConfiguration createShardingRuleConfiguration() {
        ShardingRuleConfiguration result = new ShardingRuleConfiguration(); //分片规则
        result.getTables().add(getOrgUserTableRuleConfiguration());
        //result.getTables().add(getOrderItemTableRuleConfiguration());
        //result.getBindingTableGroups().add("t_order, t_order_item");  //设置绑定表
        //result.getBroadcastTables().add("t_address");                 //设置广播表
        //设置默认分库规则
        result.setDefaultDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("ou_id", "inline"));
        //设置分表规则
        result.setDefaultTableShardingStrategy(new StandardShardingStrategyConfiguration("ou_id", "standard_test_tbl_demo"));
        //Properties props = new Properties();
        //props.setProperty("algorithm-expression", "demo_ds_${user_id % 2}");


        //设置分库及分表算法 具体算法实现 通过 spi 注入 默认
        //inline 分库算法
        /*Properties props = new Properties();
        String db_algorithm_expression ="ds_${user_id % 3}";
        props.setProperty("algorithm-expression", db_algorithm_expression);
        result.getShardingAlgorithms().put("inline", new ShardingSphereAlgorithmConfiguration("INLINE", props));*/

        result.getShardingAlgorithms().put("standard_test_db_demo", new ShardingSphereAlgorithmConfiguration("STANDARD_TEST_DB_DEMO", new Properties()));  //自定义分库算法，通过spi注入
        result.getShardingAlgorithms().put("standard_test_tbl_demo", new ShardingSphereAlgorithmConfiguration("STANDARD_TEST_TBL_DEMO", new Properties())); //自定义分表算法，通过spi注入

        //设置主键生成算法 具体算法 通过 spi 注入
        result.getKeyGenerators().put("snowflake", new ShardingSphereAlgorithmConfiguration("SNOWFLAKE", getProperties())); //主键生成策略
        return result;
    }

    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("db_0", createDataSource("db0"));
        result.put("db_1", createDataSource("db1"));
        result.put("db_2", createDataSource("db2"));
        return result;
    }

    public static DataSource createDataSource(final String dataSourceName) {
        HikariDataSource result = new HikariDataSource();
        result.setDriverClassName("com.mysql.jdbc.Driver");
        result.setJdbcUrl(String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8", "114.67.245.78", "3306", dataSourceName));
        result.setUsername("root");
        result.setPassword("1qaz@WSX");
        return result;
    }

    /**
     * 表分片规则 ogr_user
     *
     * @return
     */
    private static ShardingTableRuleConfiguration getOrgUserTableRuleConfiguration() {
        String actualDN = "db_${0..2}.org_user"; //实际数据节点
        ShardingTableRuleConfiguration result = new ShardingTableRuleConfiguration("org_user", actualDN);
        result.setTableShardingStrategy(new StandardShardingStrategyConfiguration("ou_id", "standard_test_tbl_demo"));
        result.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("ou_id", "snowflake"));
        return result;
    }

    private static Properties getProperties() {
        Properties result = new Properties();
        result.setProperty("worker-id", "123");
        return result;
    }
}
