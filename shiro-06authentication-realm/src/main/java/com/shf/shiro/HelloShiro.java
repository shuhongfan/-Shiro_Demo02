package com.shf.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;


public class HelloShiro {
    public Subject shiroLogin() {
//        导入ini配置创建工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//        工厂构建安全管理器
        SecurityManager securityManager = factory.getInstance();
//        使用工具生效安全管理器
        SecurityUtils.setSecurityManager(securityManager);
//        使用工具获得subject主体
        Subject subject = SecurityUtils.getSubject();
//        构建账户密码
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("root", "123");
//        使用subject主体登录
        subject.login(usernamePasswordToken);

        return subject;
    }

    @Test
    public void testPermissionRealm() {
        Subject subject = shiroLogin();

//        打印登录信息
        System.out.println("登录结果："+subject.isAuthenticated());

        System.out.println("是否有admin角色："+subject.hasRole("admin"));

        try {
            subject.checkRole("coder");
            System.out.println("当前用户有coder角色");
        } catch (AuthorizationException e) {
            System.out.println("当前用户没有coder角色");
        }

        System.out.println("是否有查看订单的权限："+subject.isPermitted("order:list"));
        try {
            subject.checkPermission("order:update");
            System.out.println("当前用户有修改订单权限");
        } catch (AuthorizationException e) {
            System.out.println("当前用户没有修改订单权限");
        }
    }
}
