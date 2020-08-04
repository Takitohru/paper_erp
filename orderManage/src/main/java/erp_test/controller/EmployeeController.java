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
import erp_test.bean.Employee;
import erp_test.service.ApartmentService;
import erp_test.service.EmployeeService;
import erp_test.vo.EmployeeVo;


@Controller
@RequestMapping("/erp/employee") 
public class EmployeeController {
	
	Apartment apartment = new Apartment();
	
	String name="";
	
	
	/**职员订单业务控制层
	 * */
	@Autowired  
	private EmployeeService employeeService;
	@Autowired
	private ApartmentService apartmentService;
	
	
	
	/**
	 * 职员信息
	 * */
	

	@RequestMapping("/employee")  
	public String semployee(Model model)
	{
		List<EmployeeVo>  employeeVoList=employeeService.findEmployeeVoByFuzzyEmployeeName("%"+name+"%");
		if(employeeVoList.size()==0)
		{
		    employeeVoList=employeeService.findAllEmployeeVo();
			model.addAttribute("employeeVoList", employeeVoList);
			model.addAttribute("apartment", apartment);
		}
		else
		{
			model.addAttribute("employeeVoList", employeeVoList);
			model.addAttribute("apartment", apartment);
		}
	    return "employee-list"; 
	}
	
	
	/**
	 * 客户模糊查询
	 * 	
	 * * */
	
	
	@RequestMapping("/query")  
	public String query(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Model model)
	{		
		 name=httpServletRequest.getParameter("name");
         return "employee-list"; 
	}
	/**
	 * 部门信息
	 * */
	@RequestMapping("/apartment")  
	public String apartment(Model model)
	{
		 List<Apartment> apartment=apartmentService.findAllApartment();
		 model.addAttribute("apartment", apartment);
         return "employee-apartment"; 
	}
	
	/**
	 * 职员信息添加部门处理
	 * */
	@RequestMapping("/addApartment")
	public String addApartment(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		apartment=apartmentService.findApartmentById(Integer.valueOf(httpServletRequest.getParameter("apartmentId")));
		return "redirect:/erp/employee/employee";		
	}
	
	/**
	 * 添加职员页面
	 * */
	@RequestMapping("/addEmployee")  
	public String addEmployee(Model model)
	{
		 List<EmployeeVo> employeeList=employeeService.findAllEmployeeVo();
		 model.addAttribute("employeeList", employeeList);
		 model.addAttribute("apartment", apartment);
         return "employee-add"; 
	}	
	
	/**
	 * 添加职员插入处理控制层
	 * */
	@RequestMapping("/add")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String insertEmployee(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)  
	{
		/**
		 * 将前端数据与bean层实体类绑定调用服务层的添加职员方法
		 * */
		Employee employee=new Employee();//职员类实体
		employee.setEmployeeName(httpServletRequest.getParameter("employeeName"));
		employee.setSex(httpServletRequest.getParameter("sex"));
		employee.setEmployeeTel(httpServletRequest.getParameter("employeeTel"));
		employee.setApartmentId(apartment.getApartmentId());
	
		employeeService.insert(employee);
		apartment = new Apartment();
		
		return "redirect:/erp/employee/employee"; //重新请求职员页面
	}
	
	/**
	 * 修改界面
	 * */
	@RequestMapping("/employeeEdit")  
	public String updateEmployeeHome(Model model,int id)
	{
		 EmployeeVo employeeVo=employeeService.findEmployeeByIdVo(id);
		 model.addAttribute("employeeVo",employeeVo);
		 System.out.println(employeeVo.getApartment().getApartmentName());
		 model.addAttribute("apartment", apartment);
         return "employee-edit"; 
	}
	
	/**
	 * 编辑单个职员信息
	 * */
	@RequestMapping("/update")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String updateEmployee(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)  
	{
		/**
		 * 将前端数据与bean层实体类绑定调用服务层的添加职员方法
		 * */
		Employee employee=new Employee();//职员类实体
		employee.setEmployeeId(Integer.valueOf(httpServletRequest.getParameter("employeeId")));
		employee.setEmployeeName(httpServletRequest.getParameter("employeeName"));
		employee.setSex(httpServletRequest.getParameter("sex"));
		employee.setEmployeeTel(httpServletRequest.getParameter("employeeTel"));
		employee.setApartmentId(apartment.getApartmentId());
	
		employeeService.update(employee);
		apartment = new Apartment();
		
		return "redirect:/erp/employee/employee"; //重新请求职员页面
	}
	
	/**
	 *  删除职员
	 * */
	@RequestMapping("/employeeDelete")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String deleteEmployee(Model model,int id)  
	{
		/**
		 * 删除
		 * */
			
		employeeService.delete(id);
				
		
		return "redirect:/erp/employee/employee"; //重新请求职员页面
	}

	/**
	 *  批量删除职员
	 * */
	@RequestMapping("/delAll")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String deleteAllEmployee(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)  
	{
		/**
		 * 删除
		 * */
		String[] ids = httpServletRequest.getParameterValues("ids[]");
		int id;
		
		for(int i=0;i<ids.length;i++) {
			id = Integer.valueOf(ids[i]);
			employeeService.delete(id);
		}
		
		return "redirect:/erp/employee/employee"; //重新请求职员页面
	}
}
