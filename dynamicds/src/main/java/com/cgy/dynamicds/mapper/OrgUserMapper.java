package com.cgy.dynamicds.mapper;

import com.cgy.dynamicds.entity.OrgUserEntity;

import java.util.List;

/**
 * <p>
 * ORG_USER（群组用户表） Mapper 接口
 * </p>
 *
 * @author chenguoyou
 * @since 2021-04-13
 */
public interface OrgUserMapper {

    List<OrgUserEntity> pageList();
}
