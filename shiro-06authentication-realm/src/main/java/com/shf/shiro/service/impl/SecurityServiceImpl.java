package com.shf.shiro.service.impl;

import com.shf.shiro.service.SecurityService;
import com.shf.shiro.tools.DigestsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SecurityServiceImpl implements SecurityService {
    /**
     * 查询密码
     * @param loginName
     * @return
     */
    @Override
    public Map<String,String> findPasswordByLoginName(String loginName) {
        return DigestsUtil.entryptPassword("123");
    }

    /**
     * 查询角色
     * @param loginName
     * @return
     */
    @Override
    public List<String> findRoleByLoginName(String loginName) {
        ArrayList<String> list = new ArrayList<>();
        list.add("admin");
        list.add("dev");
        return list;
    }

    /**
     * 查询资源
     * @param loginName
     * @return
     */
    @Override
    public List<String> findPermissionByLoginName(String loginName) {
        ArrayList<String> list = new ArrayList<>();
        list.add("order:add");
        list.add("order:list");
        list.add("order:del");
        return list;
    }
}
