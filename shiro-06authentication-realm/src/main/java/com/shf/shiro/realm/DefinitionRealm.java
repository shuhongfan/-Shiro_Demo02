package com.shf.shiro.realm;

import com.shf.shiro.service.impl.SecurityServiceImpl;
import com.shf.shiro.tools.DigestsUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.List;
import java.util.Map;

public class DefinitionRealm extends AuthorizingRealm {

    public DefinitionRealm() {
//        指定密码匹配方式sha1
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher(DigestsUtil.SHA1);
//        指定密码迭代次数
        hashedCredentialsMatcher.setHashIterations(DigestsUtil.ITERATIONS);
//        生效
        setCredentialsMatcher(hashedCredentialsMatcher);
    }

    /**
     * 鉴权方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        拿到用户凭证信息
        String loginName = (String) principalCollection.getPrimaryPrincipal();

//        从数据库查询对应的角色和权限
        SecurityServiceImpl securityService = new SecurityServiceImpl();
        List<String> role = securityService.findRoleByLoginName(loginName);
        List<String> permission = securityService.findPermissionByLoginName(loginName);

//        构建资源校验对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(role);
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证方法
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        获取登录名
        String loginName = (String) authenticationToken.getPrincipal();

        SecurityServiceImpl securityService = new SecurityServiceImpl();
        Map<String, String> map = securityService.findPasswordByLoginName(loginName);

        if (map.isEmpty()) {
            throw new UnknownAccountException("账户不存在");
        }

        String salt = map.get("salt");
        String password = map.get("password");

        return new SimpleAuthenticationInfo(loginName, password, ByteSource.Util.bytes(salt), getName());
    }
}
