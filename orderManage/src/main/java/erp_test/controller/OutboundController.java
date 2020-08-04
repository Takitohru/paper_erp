package erp_test.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import erp_test.bean.Client;
import erp_test.bean.Commodity;
import erp_test.bean.Employee;
import erp_test.bean.OrderDetail;
import erp_test.bean.OrderMaster;
import erp_test.bean.OutboundDetail;
import erp_test.bean.OutboundOrder;
import erp_test.service.ClientService;
import erp_test.service.CommodityService;
import erp_test.service.EmployeeService;
import erp_test.service.OutBoundService;
import erp_test.vo.EmployeeVo;
import erp_test.vo.OutBoundDetailVo;
import erp_test.vo.OutBoundMasterVo;

@Controller
@RequestMapping("/erp/outbound")
public class OutboundController 
{
	
	List<Commodity> commodityList_out=new ArrayList<Commodity>();
	Client client_out = new Client();
	Employee employee_out =new Employee();
	String name="";
    /**
     *出库业务控制层
     * */
	
	@Autowired
	private OutBoundService outBoundService;
	
	@Autowired  
	private CommodityService commodityService;
	
	@Autowired  
	private ClientService clientService;
	
	@Autowired  
	private EmployeeService employeeService;
	
	/**
	 * 销售出库界面
	 * */
	@RequestMapping("/outbound")    //主页页面tab栏跳转url 测试示例
	public String Sellorder(Model model)
	{
		model.addAttribute("commodityList",commodityList_out);
		model.addAttribute("client", client_out);
		model.addAttribute("employee", employee_out);
		return "outbound";   //销售订单界面
	}
	
	   @RequestMapping("/outboundTableView/page")
	    public String pagOoutboundTableView() {
	        return "outboundTableView";
	    }
		
		/**
	     * 获取历史出库单
	     * @return 出库单列表
	     */
	    @RequestMapping("/outboundTableView")
	    @ResponseBody
	    public List<OutBoundDetailVo> getOutboundTableView(@RequestParam("id") int id ){
	    	
	    	List<OutBoundDetailVo> OutBoundDetailVoByOutboundIdList=outBoundService.getOutboundTableByOutboundId(id);
			for(int i=0;i<OutBoundDetailVoByOutboundIdList.size();i++) {
				System.out.println(OutBoundDetailVoByOutboundIdList.get(i).getOutBoundMasterVo().getOutboundDate());
				//System.out.println(OutBoundDetailVoByOutboundIdList.get(i).getOutBoundMasterVo().getEmployee().getEmployeeName());
				//System.out.println(OutBoundDetailVoByOutboundIdList.get(i).getOutBoundMasterVo().getClient().getClientName());
			}
			
			return OutBoundDetailVoByOutboundIdList;
	    }
	    
	/**
	 * 销售出库历史按明细
	 * */
	@RequestMapping("/outboundHistoryDetail")
	public String OutboundHistoryDetail(Model model)
	{
		List<OutBoundDetailVo> OutBoundDetailVoList=outBoundService.selectOutBoundByDetail();
		model.addAttribute("OutBoundDetailVoList", OutBoundDetailVoList);
		return "outbound-historyDetail";   
	}
	
	/**
	 * 销售出库历史按单据
	 * */
	@RequestMapping("/outboundHistoryMaster")
	public String OutboundHistoryMaster(Model model)
	{
		List<OutBoundMasterVo> OutBoundMasterVoList=outBoundService.selectOutBoundByMaster();
		model.addAttribute("OutBoundMasterVoList", OutBoundMasterVoList);
		return "outbound-historyMaster";   
	}
	
	
	
	/****************************************************************************************************/
	
	/**
	 * 销售出库单
	 */


	/**
	 * 销售订单添加时的客户表
	 * */
	@RequestMapping("/client")
	public String orderClient(Model model)
	{
		List<Client> clientList=clientService.findClientByFuzzyClientName("%"+name+"%");
		if(clientList.size()==0)
		{
			clientList=clientService.findAllClient();
		}
		model.addAttribute("ClientList", clientList);
		return "outboundClient";
	}
	
	/**
	 * 销售订单添加时的职员表
	 * */
	@RequestMapping("/employee")
	public String orderEmployee(Model model)
	{
		List<EmployeeVo> employeeList=employeeService.findEmployeeVoByFuzzyEmployeeName("%"+name+"%");
		if(employeeList.size()==0)
		{
			employeeList=employeeService.findAllEmployeeVo();
		}
		model.addAttribute("EmployeeList", employeeList);
		return "outboundworker";
	}
	
	
	/**
	 * 显示销售订单添加时的商品列表
	 * */
	@RequestMapping("/commodity")
	public String orderCommodity(Model model)
	{
		List<Commodity>  CommodityList=commodityService.findCommodityByFuzzyCommodityName("%"+name+"%");
		if(CommodityList.size()==0)
		{
		    CommodityList=commodityService.findAllCommodity();
		}
			model.addAttribute("CommodityList", CommodityList);
		return "outboundCommodity";
	}
	
	
	/**
	 * 商品查询
	 * */
	@RequestMapping("/erp/outbound/queryj")  
	public String query(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Model model)
	{		
		 name=httpServletRequest.getParameter("name");
		 List<Commodity>  CommodityList=commodityService.findCommodityByFuzzyCommodityName("%"+name+"%");
		 model.addAttribute("CommodityList", CommodityList);
         return "outboundcommodity"; 
	}
	
	
	/**
	 * 客户查询
	 * */
	@RequestMapping("/erp/outbound/queryC")  
	public String queryC(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Model model)
	{	
		 name=httpServletRequest.getParameter("name");
		 List<Client>  ClientList=clientService.findClientByFuzzyClientName("%"+name+"%");
		 model.addAttribute("ClientList", ClientList);
         return "outboundClient"; 
	}
	
	
	/**
	 * 部员查询
	 * */
	@RequestMapping("/erp/outbound/queryW")  
	public String queryW(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Model model)
	{	
		 name=httpServletRequest.getParameter("name");
		 List<EmployeeVo>  EmployeeList=employeeService.findEmployeeVoByFuzzyEmployeeName("%"+name+"%");
		 model.addAttribute("EmployeeList", EmployeeList);
         return "outboundworker"; 
	}
	
	
	
	
	/**
	 * 出库单界面
	 * */
/*	@RequestMapping("/outbound")    //主页页面tab栏跳转url 测试示例
	public String Sellorder(Model model)
	{
		model.addAttribute("commodityList_out",commodityList_out);
		model.addAttribute("client", client);
		model.addAttribute("employee", employee);
		return "outbound";   //销售订单界面
	}
	*/
	
	/**
	 * 出库单添加用户处理
	 * */
	@RequestMapping("/addClient")
	public String addClient(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		client_out=clientService.findClientById(Integer.valueOf(httpServletRequest.getParameter("clientId")));
		return "redirect:/erp/outbound/outbound";		
	}
	
	
	/**
	 *出库单添加商品处理
	 * */
	@RequestMapping("/addCommodity")
	public String addCommodity(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		int flag=0;
		for(int i=0;i<commodityList_out.size();i++) 
		{
			if(Integer.valueOf(httpServletRequest.getParameter("id"))==commodityList_out.get(i).getCommodityId())
			{
				flag=1;
			}
		}
		if(flag==0)
			commodityList_out.add(commodityService.findCommodityById(Integer.valueOf(httpServletRequest.getParameter("id"))));
		if(commodityList_out.size()==0)
			commodityList_out.add(commodityService.findCommodityById(Integer.valueOf(httpServletRequest.getParameter("id"))));
		return "redirect:/erp/outbound/outbound";		
	}
	
	

	
	
	/**
	 * 出库单添加部员处理
	 * */
	@RequestMapping("/addEmployee")
	public String addEmployee(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		employee_out=employeeService.findEmployeeById(Integer.valueOf(httpServletRequest.getParameter("EmployeeId")));
		return "redirect:/erp/outbound/outbound";		
	}
	
	
	
	
	/**
	 * 移除添加的商品
	 * */
	@RequestMapping("/commodityRemove")
	public String commodityRemove(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		for(int i=0;i<commodityList_out.size();i++)
			if(commodityList_out.get(i).getCommodityId()==Integer.valueOf(httpServletRequest.getParameter("CommodityId")))
				commodityList_out.remove(i);
		return "redirect:/erp/outbound/outbound";		
	}
	
	
	/**
	 * 核心业务  提交出库单
	 * */
	@RequestMapping("/submitOutbound")
	public String submitOutbound(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		
		/*
		 * 得到JS的字符数组即商品id列表,数量列表,运费价格列表
		 * */
		String[] commodityIdList = httpServletRequest.getParameterValues("commodityIdList[]");
		OutboundOrder outboundOrder = new OutboundOrder();
		List<OutboundDetail> outboundDetailList = new ArrayList<OutboundDetail>();
		
		
		outboundOrder.setClientId(client_out.getClientId());
		outboundOrder.setEmployeeId(employee_out.getEmployeeId());
		
		
		
		for(int i=0;i<commodityIdList.length;i++) {
			OutboundDetail outboundDetail = new OutboundDetail();
			outboundDetail.setCommodityId(commodityList_out.get(i).getCommodityId());
			outboundDetail.setOutboundNum(Integer.valueOf(commodityIdList[i]));
			
			outboundDetailList.add(outboundDetail);
		}


		outBoundService.insertOutBound(outboundOrder, outboundDetailList);
		
		/*初始化全局变量*/
		commodityList_out=new ArrayList<Commodity>();
		client_out=new Client();
		employee_out=new Employee();
		return "redirect:/erp/outbound/outbound";
	}
	
	
}
