package erp_test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import erp_test.bean.Company;
import erp_test.service.AccountService;
import erp_test.service.CompanyService;

@Controller  
@RequestMapping("/erp/company")  
public class companyController 
{	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	AccountService accountService;
	
	/**
	 * 公司资料页面
	 * */
	@RequestMapping("/company")  
	public String company(Model model)
	{
		Company company=new Company();
		if(companyService.findCompanyById(accountService.getAccount().getId())!=null)
		{
		  company=companyService.findCompanyById(accountService.getAccount().getId());
		}
		else
		 company.setId(accountService.getAccount().getId());
		 model.addAttribute("company", company);
         return "company-list"; 
	}
	
	
	
	/**
	 * 修改界面
	 * */
	@RequestMapping("/companyEdit")  
	public String updateCompanyHome(Model model,String id)
	{
		 Company company=new Company();
		if(companyService.findCompanyById(id)!=null)
	     company=companyService.findCompanyById(id);
		 company.setId(accountService.getAccount().getId());
		 model.addAttribute("company",company);
         return "company-edit"; 
	}
	
	/**
	 * 编辑单个公司信息
	 * */
	@RequestMapping("/update")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String updateCompany(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)  
	{
		/**
		 * 将前端数据与bean层实体类绑定调用服务层的添加公司方法
		 * */
		Company company=new Company();//公司类实体
		company.setId(httpServletRequest.getParameter("id"));
		company.setCompanyName(httpServletRequest.getParameter("companyName"));    //通过ajax传入数据 可以使用json也可以使用字段 通过getp方法得到对应的值
		company.setCompanyAddress(httpServletRequest.getParameter("companyAddress"));
		company.setCompanyTel(httpServletRequest.getParameter("companyTel"));
		
		if(companyService.findCompanyById(accountService.getAccount().getId())==null)  //未初始化公司时
			companyService.insert(company);
		else  //初始化以后更新
		companyService.update(company);
		
		return "redirect:/erp/company/company"; //重新请求公司页面
	}
	
}
