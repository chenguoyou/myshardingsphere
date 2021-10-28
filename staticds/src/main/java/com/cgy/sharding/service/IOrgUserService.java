package com.cgy.sharding.service;

import com.cgy.sharding.entity.OrgUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * ORG_USER（群组用户表） 服务类
 * </p>
 *
 * @author chenguoyou
 * @since 2021-04-13
 */
public interface IOrgUserService extends IService<OrgUserEntity> {

    void saveEntity(OrgUserEntity entity);
}
