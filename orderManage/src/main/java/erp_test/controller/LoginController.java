package erp_test.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import erp_test.service.AccountService;

@Controller
@RequestMapping("/erp/login")
public class LoginController 
{
	/**
	 * 登录账号控制层  调用服务层的方法对账号登录进行验证 负责控制登录界面有关的转向
	 * **/
	@Autowired
	AccountService accountService;    //实现层自动注入接口   
	
	
	@RequestMapping("/loginView") //登录界面
	public String loginView() {
		return "login";  //跳转回登录界面 login.html
	}
	
	@RequestMapping("/loginError")
	public String loginError()
	{
		return "loginError";
	}
	
	@RequestMapping("/loginDeal")   //  调用路径为  erp/login/loginDeal 
	public String check_Login(@RequestParam("id") String id,@RequestParam("password") String password,HttpSession session,Model model)  //处理用户登录的方法
	{
		String toPage="redirect:/erp/login/loginView";  //默认跳转回登录界面
		if(accountService.checkAccount(id, password)!=null) //登录成功
		{
			session.setAttribute("Account",accountService.checkAccount(id, password));  //将当前账号信息提交
			toPage="redirect:/erp/home/index";  //进入IndexController视图层调用其方法打开Index.html页面
		}
		else  //登录失败
		{
			    toPage="redirect:/erp/login/loginError";  //跳转登录失败界面
		}
		return toPage;
	}
}
