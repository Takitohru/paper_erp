package erp_test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import erp_test.bean.Apartment;
import erp_test.bean.Employee;
import erp_test.service.ApartmentService;
import erp_test.service.EmployeeService;

@Controller  
@RequestMapping("/erp/apartment")  
public class ApartmentController {
	@Autowired
	ApartmentService apartmentService;
	@Autowired
	EmployeeService employeeService;
	
	String name="";
	
	/**
	 * 部门资料页面
	 * */
	
	@RequestMapping("/apartment")  
	public String sapartment(Model model)
	{
		List<Apartment>  apartmentList=apartmentService.findApartmentByFuzzyApartmentName("%"+name+"%");
		if(apartmentList.size()==0)
		{
		    apartmentList=apartmentService.findAllApartment();
			model.addAttribute("apartmentList", apartmentList);
		}
		else
		{
			model.addAttribute("apartmentList", apartmentList);
		}
		//System.out.println(name);
		//System.out.println(apartmentList.toString());
	    return "apartment-list"; 
	}
	
	
	/**
	 * 客户模糊查询
	 * 	
	 * * */
	
	
	@RequestMapping("/query")  
	public String query(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Model model)
	{		
		 name=httpServletRequest.getParameter("name");
         return "apartment-list"; 
	}
	/**
	 * 添加部门页面
	 * */
	@RequestMapping("/addApartment")  
	public String addApartment(Model model)
	{
		 List<Apartment> apartmentList=apartmentService.findAllApartment();
		 model.addAttribute("apartmentList", apartmentList);
         return "apartment-add"; 
	}	
	
	/**
	 * 添加部门插入处理控制层
	 * */
	@RequestMapping("/add")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String insertApartment(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)  
	{
		/**
		 * 将前端数据与bean层实体类绑定调用服务层的添加商品方法
		 * */
		Apartment apartment=new Apartment();//商品类实体
				
		apartment.setApartmentName(httpServletRequest.getParameter("apartmentName"));

		apartmentService.insert(apartment);
	 
		return "redirect:/erp/apartment/apartment"; //重新请求部门页面
	}
	
	/**
	 * 修改界面
	 * */
	@RequestMapping("/apartmentEdit")  
	public String updateApartmentHome(Model model,int id)
	{
		Apartment apartment=apartmentService.findApartmentById(id);
		model.addAttribute("apartment",apartment);
		return "apartment-edit"; 
	}
	
	/**
	 * 编辑单个信息
	 * */
	@RequestMapping("/update")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String updateApartment(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)  
	{
		/**
		 * 将前端数据与bean层实体类绑定调用服务层的添加商品方法
		 * */
		Apartment apartment=new Apartment();//部门类实体
		apartment.setApartmentId(Integer.valueOf(httpServletRequest.getParameter("apartmentId")));
		apartment.setApartmentName(httpServletRequest.getParameter("apartmentName"));

		apartmentService.update(apartment);
		
		return "redirect:/erp/apartment/apartment"; //重新请求商品页面
	}
	
	/**
	 *  删除部门
	 * */
	@RequestMapping("/apartmentDelete")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String deleteApartment(Model model,int id)  
	{
		/**
		 * 删除
		 * */
		String flag="true";
		
		List<Employee> employees = employeeService.findEmployeeByApartmentId(id);
		if(employees != null && !employees.isEmpty())	{
			//System.out.println("还有"+employees.size()+"个人在这个部门，无法删除");
			flag="false";
		} else {
			apartmentService.delete(id);
		}
				
		return flag;
		//return "redirect:/erp/apartment/apartment"; //重新请求商品页面
	}
	
	
	/**
	 *  批量删除部门
	 * */
	@RequestMapping("/delAll")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String deleteAllApartment(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)  
	{
		/**
		 * 删除
		 * */
		String[] ids = httpServletRequest.getParameterValues("ids[]");
		int id;
		String flag="true";
		
		for(int i=0;i<ids.length;i++) {
			id = Integer.valueOf(ids[i]);
			
			List<Employee> employees = employeeService.findEmployeeByApartmentId(id);
			if(employees != null && !employees.isEmpty())	{
				//System.out.println("还有"+employees.size()+"个人在这个部门，无法删除");
				flag="false";
			} else {
				apartmentService.delete(id);
			}
		}
		return flag;
		
		//return "redirect:/erp/supplier/supplier"; //重新请求供应商页面
	}
}
