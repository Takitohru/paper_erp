package erp_test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import erp_test.bean.Supplier;
import erp_test.bean.Supplier;
import erp_test.service.SupplierService;

@Controller  
@RequestMapping("/erp/supplier")  
public class SupplierController {
	@Autowired
	SupplierService supplierService;

	String name="";
	/**
	 * 供应商资料页面
	 * */
	@RequestMapping("/supplier")  
	public String ssupplier(Model model)
	{
		List<Supplier>  supplier=supplierService.findSupplierByFuzzySupplierName("%"+name+"%");
		if(supplier.size()==0)
		{
		    supplier=supplierService.findAllSupplier();
			model.addAttribute("supplier", supplier);
		}
		else
		{
			model.addAttribute("supplier", supplier);
		}
	    return "supplier-list"; 
	}
	
	
	/**
	 * 客户模糊查询
	 * 	
	 * * */
	
	
	@RequestMapping("/query")  
	public String query(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Model model)
	{		
		 name=httpServletRequest.getParameter("name");
         return "supplier-list"; 
	}
	
	
	/**
	 * 添加供应商页面
	 * */
	@RequestMapping("/addSupplier")  
	public String addSupplier(Model model)
	{
		 List<Supplier> supplier=supplierService.findAllSupplier();
		 model.addAttribute("supplier", supplier);
         return "supplier-add"; 
	}	
	
	/**
	 * 添加供应商插入处理控制层
	 * */
	@RequestMapping("/add")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String insertSupplier(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)  
	{
		/**
		 * 将前端数据与bean层实体类绑定调用服务层的添加供应商方法
		 * */
		Supplier supplier=new Supplier();//供应商类实体
				
		supplier.setSupplierName(httpServletRequest.getParameter("supplierName"));    //通过ajax传入数据 可以使用json也可以使用字段 通过getp方法得到对应的值
		supplier.setSupplierAddress(httpServletRequest.getParameter("supplierAddress"));
		supplier.setSupplierTel(httpServletRequest.getParameter("supplierTel"));

		supplierService.insert(supplier);
	 
		return "redirect:/erp/supplier/supplier"; //重新请求供应商页面
	}
	
	/**
	 * 修改界面
	 * */
	@RequestMapping("/supplierEdit")  
	public String updateSupplierHome(Model model,int id)
	{
		 Supplier supplier=supplierService.findSupplierById(id);
		 model.addAttribute("supplier",supplier);
         return "supplier-edit"; 
	}
	
	/**
	 * 编辑单个供应商信息
	 * */
	@RequestMapping("/update")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String updateSupplier(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)  
	{
		/**
		 * 将前端数据与bean层实体类绑定调用服务层的添加供应商方法
		 * */
		Supplier supplier=new Supplier();//供应商类实体
			
		supplier.setSupplierId(Integer.valueOf(httpServletRequest.getParameter("supplierId")));
		supplier.setSupplierName(httpServletRequest.getParameter("supplierName"));    //通过ajax传入数据 可以使用json也可以使用字段 通过getp方法得到对应的值
		supplier.setSupplierAddress(httpServletRequest.getParameter("supplierAddress"));
		supplier.setSupplierTel(httpServletRequest.getParameter("supplierTel"));


		supplierService.update(supplier);
		
		return "redirect:/erp/supplier/supplier"; //重新请求供应商页面
	}
	
	/**
	 *  删除供应商
	 * */
	@RequestMapping("/supplierDelete")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String deleteSupplier(Model model,int id)  
	{
		/**
		 * 删除
		 * */
			
		supplierService.delete(id);
				
		
		return "redirect:/erp/supplier/supplier"; //重新请求供应商页面
	}


	/**
	 *  批量删除供应商
	 * */
	@RequestMapping("/delAll")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String deleteAllSupplier(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)  
	{
		/**
		 * 删除
		 * */
		String[] ids = httpServletRequest.getParameterValues("ids[]");
		int id;
		
		for(int i=0;i<ids.length;i++) {
			id = Integer.valueOf(ids[i]);
			supplierService.delete(id);
		}
		
		return "redirect:/erp/supplier/supplier"; //重新请求供应商页面
	}
}
