package com.yizhi.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yizhi.common.config.Constant;
import com.yizhi.system.dao.RoleDao;
import com.yizhi.system.dao.RoleMenuDao;
import com.yizhi.system.dao.UserDao;
import com.yizhi.system.dao.UserRoleDao;
import com.yizhi.system.domain.RoleDO;
import com.yizhi.system.domain.RoleMenuDO;
import com.yizhi.system.service.RoleService;


@Service
@CacheConfig(cacheNames ={Constant.SYSTEM_NAME+".role"})
public class RoleServiceImpl implements RoleService {

    public static final String ROLE_ALL_KEY = "\"role_all\"";

    public static final String DEMO_CACHE_NAME =  Constant.SYSTEM_NAME+".role";

    @Autowired
    RoleDao roleMapper;
    @Autowired
    RoleMenuDao roleMenuMapper;
    @Autowired
    UserDao userMapper;
    @Autowired
    UserRoleDao userRoleMapper;

    @Cacheable( value = DEMO_CACHE_NAME,key = ROLE_ALL_KEY)
    @Override
    public List<RoleDO> list() {
        List<RoleDO> roles = roleMapper.list(new HashMap<>(16));
        return roles;
    }


    @Override
    public List<RoleDO> list(Long userId) {
        List<Long> rolesIds = userRoleMapper.listRoleId(userId);
        List<RoleDO> roles = roleMapper.list(new HashMap<>(16));
        for (RoleDO roleDO : roles) {
            roleDO.setRoleSign("false");
            for (Long roleId : rolesIds) {
                if (Objects.equals(roleDO.getRoleId(), roleId)) {
                    roleDO.setRoleSign("true");
                    break;
                }
            }
        }
        return roles;
    }
    @CacheEvict(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    @Transactional
    @Override
    public int save(RoleDO role) {
        int count = roleMapper.save(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        List<RoleMenuDO> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        roleMenuMapper.removeByRoleId(roleId);
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }
        return count;
    }

    @CacheEvict(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    @Transactional
    @Override
    public int remove(Long id) {
        int count = roleMapper.remove(id);
        userRoleMapper.removeByRoleId(id);
        roleMenuMapper.removeByRoleId(id);
        return count;
    }

    @Override
    public RoleDO get(Long id) {
        RoleDO roleDO = roleMapper.get(id);
        return roleDO;
    }

    @CacheEvict(value = DEMO_CACHE_NAME, key = ROLE_ALL_KEY)
    @Override
    public int update(RoleDO role) {
        int r = roleMapper.update(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        roleMenuMapper.removeByRoleId(roleId);
        List<RoleMenuDO> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            roleMenuMapper.batchSave(rms);
        }
        return r;
    }

    @Override
    public int batchremove(Long[] ids) {
        int r = roleMapper.batchRemove(ids);
        return r;
    }

}
