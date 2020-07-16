package com.it666.web;

import com.it666.domain.User;
import com.it666.service.LoginService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<User> {
    private User user=new User();
    @Override
    public User getModel() {
        return user;
    }

    //注入业务层
    private LoginService loginService;
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public String login(){
        System.out.println("login来了。");
        System.out.println(user);//打印输出已经封装好的user对象
        User resUser = loginService.login(user);
        if (resUser==null)//说明用户名和密码是错误的
        {
            this.addActionError("用户名或者密码错误");//回显信息，在页面中显示
            return LOGIN;//结果页跳转
        }else {//不等于空，表示成功，需要保存用户信息，存到session中
            ActionContext.getContext().getSession().put("curUser",resUser);
            return SUCCESS;//结果页跳转
        }
    }
    public String loginout(){
        System.out.println("来到退出");
        /*清空当前用户的session*/
        ActionContext.getContext().getSession().remove("curUser");
        return "login_out";
    }
}
