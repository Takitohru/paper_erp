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

import erp_test.bean.Commodity;
import erp_test.bean.Commodity;
import erp_test.service.CommodityService;

@Controller  
@RequestMapping("/erp/commodity")  
public class CommodityController 
{	
	@Autowired
	CommodityService commodityService;
	
	String name="";
	
	/**
	 * 商品资料页面
	 * */
	@RequestMapping("/commodity")  
	public String scommodity(Model model)
	{
		List<Commodity>  CommodityList=commodityService.findCommodityByFuzzyCommodityName("%"+name+"%");
		if(CommodityList.size()==0)
		{
		    CommodityList=commodityService.findAllCommodity();
			model.addAttribute("CommodityList", CommodityList);
		}
		else
		{
			model.addAttribute("CommodityList", CommodityList);
		}
		
	    return "commodity-list"; 
	}
	
	/**
	 * 商品查询
	 * */
	@RequestMapping("/query")  
	public String query(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Model model)
	{		
		 name=httpServletRequest.getParameter("name");
         return "commodity-list"; 
	}
	
	/**
	 * 添加商品页面
	 * */
	@RequestMapping("/addCommodity")  
	public String addCommodity(Model model)
	{
		 List<Commodity>CommodityList=commodityService.findAllCommodity();
		 model.addAttribute("CommodityList", CommodityList);
         return "commodity-add"; 
	}	
	
	/**
	 * 添加商品插入处理控制层
	 * */
	@RequestMapping("/add")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String insertCommodity(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)  
	{
		/**
		 * 将前端数据与bean层实体类绑定调用服务层的添加商品方法
		 * */
		Commodity commodity=new Commodity();//商品类实体
		
		commodity.setCommodityName(httpServletRequest.getParameter("commodityName"));    //通过ajax传入数据 可以使用json也可以使用字段 通过getp方法得到对应的值
		commodity.setBrand(httpServletRequest.getParameter("brand"));
		commodity.setInventoryNumber(Integer.valueOf(httpServletRequest.getParameter("inventoryNumber")));
		commodity.setSafeInventory(Integer.valueOf(httpServletRequest.getParameter("safeInventory")));
		commodity.setPrice(Double.parseDouble(httpServletRequest.getParameter("price")));
		commodity.setRank(httpServletRequest.getParameter("rank"));
		commodity.setPaperClass(httpServletRequest.getParameter("paperClass"));
		commodity.setPaperSize(httpServletRequest.getParameter("paperSize"));
		commodity.setPaperWeight(Integer.valueOf(httpServletRequest.getParameter("paperWeight")));
		commodity.setVipAccount(Double.parseDouble(httpServletRequest.getParameter("vipAccounts")));
		commodityService.insert(commodity);
	 
		return "redirect:/erp/commodity/commodity"; //重新请求商品页面
	}
	
	/**
	 * 修改界面
	 * */
	@RequestMapping("/commodityEdit")  
	public String updateCommodityHome(Model model,int id)
	{
		 Commodity commodity=commodityService.findCommodityById(id);
		 model.addAttribute("commodity",commodity);
         return "commodity-edit"; 
	}
	
	/**
	 * 编辑单个商品信息
	 * */
	@RequestMapping("/update")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String updateCommodity(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)  
	{
		/**
		 * 将前端数据与bean层实体类绑定调用服务层的添加商品方法
		 * */
		Commodity commodity=new Commodity();//商品类实体
			
		commodity.setCommodityId(Integer.valueOf(httpServletRequest.getParameter("commodityId")));
		commodity.setCommodityName(httpServletRequest.getParameter("commodityName"));    //通过ajax传入数据 可以使用json也可以使用字段 通过getp方法得到对应的值
		commodity.setBrand(httpServletRequest.getParameter("brand"));
		commodity.setInventoryNumber(Integer.valueOf(httpServletRequest.getParameter("inventoryNumber")));
		commodity.setSafeInventory(Integer.valueOf(httpServletRequest.getParameter("safeInventory")));
		commodity.setPrice(Double.parseDouble(httpServletRequest.getParameter("price")));
		commodity.setRank(httpServletRequest.getParameter("rank"));
		commodity.setPaperClass(httpServletRequest.getParameter("paperClass"));
		commodity.setPaperSize(httpServletRequest.getParameter("paperSize"));
		commodity.setPaperWeight(Integer.valueOf(httpServletRequest.getParameter("paperWeight")));
		commodity.setVipAccount(Double.parseDouble(httpServletRequest.getParameter("vipAccounts")));
		commodityService.update(commodity);
		
		return "redirect:/erp/commodity/commodity"; //重新请求商品页面
	}
	
	/**
	 *  删除商品
	 * */
	@RequestMapping("/commodityDelete")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String deleteCommodity(Model model,int id)  
	{		
		commodityService.delete(id);	
		return "redirect:/erp/commodity/commodity"; //重新请求商品页面
	}
	
	/**
	 * 浏览商品信息
	 * */
	@RequestMapping("/commodityView")
	public String commodityView(Model model,int id)
	{
		 Commodity commodity=commodityService.findCommodityById(id);
		 model.addAttribute("commodity",commodity);
         return "commodity-view"; 
	}

	/**
	 *  批量删除商品
	 * */
	@RequestMapping("/delAll")
	@ResponseBody
	public String deleteAllCommodity(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)  
	{		
		/**
		 * 删除
		 * */
		String[] ids = httpServletRequest.getParameterValues("ids[]");
		int id;
		
		for(int i=0;i<ids.length;i++) {
			id = Integer.valueOf(ids[i]);
			commodityService.delete(id);
		}	
		return "redirect:/erp/commodity/commodity"; //重新请求商品页面
	}
}
