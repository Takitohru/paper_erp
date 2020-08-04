package erp_test.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import erp_test.bean.Client;
import erp_test.service.ClientService;

@Controller  
@RequestMapping("/erp/client")  
public class ClientController {

	@Autowired
	ClientService clientService;
	
	String name="";
	
	/**
	 * 客户资料页面
	 * */
	
	
	/**
	 * 直接进入资料页面
	 * */
	
	
	@RequestMapping("/client")  
	public String sclient(Model model)
	{
		List<Client>  clientList=clientService.findClientByFuzzyClientName("%"+name+"%");
		if(clientList.size()==0)
		{
		    clientList=clientService.findAllClient();
			model.addAttribute("clientList", clientList);
		}
		else
		{
			model.addAttribute("clientList", clientList);
		}
	    return "client-list"; 
	}
	
	
	/**
	 * 客户模糊查询
	 * 	
	 * * */
	
	
	@RequestMapping("/query")  
	public String query(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Model model)
	{		
		 name=httpServletRequest.getParameter("name");
         return "client-list"; 
	}
	
	
	
	/**
	 * 添加客户页面
	 * */
	@RequestMapping("/addClient")  
	public String addClient(Model model)
	{
		 List<Client> clientList=clientService.findAllClient();
		 model.addAttribute("clientList", clientList);
         return "client-add"; 
	}	
	
	/**
	 * 添加客户插入处理控制层
	 * */
	@RequestMapping("/add")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String insertClient(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)  
	{
		/**
		 * 将前端数据与bean层实体类绑定调用服务层的添加客户方法
		 * */
		Client client=new Client();//客户类实体

		client.setClientName(httpServletRequest.getParameter("clientName"));
		client.setClientAddress(httpServletRequest.getParameter("clientAddress"));
		client.setClientTel(httpServletRequest.getParameter("clientTel"));
		client.setIsVip(httpServletRequest.getParameter("isVip"));
		
		clientService.insert(client);
	 
		return "redirect:/erp/client/client"; //重新请求客户页面
	}
	
	/**
	 * 修改界面
	 * */
	@RequestMapping("/clientEdit")  
	public String updateClientHome(Model model,int id)
	{
		Client client=clientService.findClientById(id);
		model.addAttribute("client",client);
		return "client-edit"; 
	}
	
	/**
	 * 编辑单个商品信息
	 * */
	@RequestMapping("/update")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String updateClient(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)  
	{
		/**
		 * 将前端数据与bean层实体类绑定调用服务层的添加客户方法
		 * */
		Client client=new Client();//客户类实体

		client.setClientId(Integer.valueOf(httpServletRequest.getParameter("clientId")));
		client.setClientName(httpServletRequest.getParameter("clientName"));
		client.setClientAddress(httpServletRequest.getParameter("clientAddress"));
		client.setClientTel(httpServletRequest.getParameter("clientTel"));
		client.setIsVip(httpServletRequest.getParameter("isVip"));
		
		clientService.update(client);
		
		return "redirect:/erp/client/client"; //重新请求客户页面
	}
	
	/**
	 *  删除商品
	 * */
	@RequestMapping("/clientDelete")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String deleteClient(Model model,int id)  
	{
		/**
		 * 删除
		 * */
			
		clientService.delete(id);
				
		
		return "redirect:/erp/client/client"; //重新请求商品页面
	}

	/**
	 *  批量删除客户
	 * */
	@RequestMapping("/delAll")   //  调用路径为  erp/login/loginDeal 
	@ResponseBody
	public String deleteAllClient(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)  
	{
		/**
		 * 删除
		 * */
		String[] ids = httpServletRequest.getParameterValues("ids[]");
		int id;
		
		for(int i=0;i<ids.length;i++) {
			id = Integer.valueOf(ids[i]);
			clientService.delete(id);
		}
		
		return "redirect:/erp/client/client"; //重新请求供应商页面
	}

}
