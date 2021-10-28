package com.cgy.sharding.service.impl;

import com.cgy.sharding.entity.OrgUserEntity;
import com.cgy.sharding.mapper.OrgUserMapper;
import com.cgy.sharding.service.IOrgUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * ORG_USER（群组用户表） 服务实现类
 * </p>
 *
 * @author chenguoyou
 * @since 2021-04-13
 */
@Service
public class OrgUserServiceImpl extends ServiceImpl<OrgUserMapper, OrgUserEntity> implements IOrgUserService {

    @Resource
    private OrgUserMapper mapper;

    @Override
    public void saveEntity(OrgUserEntity entity) {
        mapper.insert(entity);
    }
}
