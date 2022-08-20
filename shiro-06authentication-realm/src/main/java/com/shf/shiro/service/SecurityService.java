package com.shf.shiro.service;

import java.util.List;
import java.util.Map;

public interface SecurityService {
    /**
     * 查询密码
     * @param loginName
     * @return
     */
    Map<String,String> findPasswordByLoginName(String loginName);

    /**
     * 查询角色
     * @param loginName
     * @return
     */
    List<String> findRoleByLoginName(String loginName);

    /**
     * 查询资源
     * @param loginName
     * @return
     */
    List<String> findPermissionByLoginName(String loginName);
}
