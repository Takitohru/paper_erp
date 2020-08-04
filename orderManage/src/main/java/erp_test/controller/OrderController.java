package erp_test.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import erp_test.dao.OrderMasterDao;
import erp_test.service.ClientService;
import erp_test.service.CommodityService;
import erp_test.service.EmployeeService;
import erp_test.service.OrderService;
import erp_test.service.OutBoundService;
import erp_test.vo.EmployeeVo;
import erp_test.vo.OrderDetailInformationVo;
import erp_test.vo.OrderMasterInformationVo;



@Controller
@RequestMapping("/erp/order")   //页面请求url  /erp/order+具体调用方法的RequestMapping值即跳转的最终地址 
public class OrderController 
{
	
	List<Commodity> commodityList=new ArrayList<Commodity>();
	Client client=new Client();
	Employee employee=new Employee();
	String name="";
	
	/**销售订单业务控制层
	 * */
	@Autowired  
	private OrderService orderService;
	
	@Autowired  
	private CommodityService commodityService;
	
	@Autowired  
	private ClientService clientService;
	
	@Autowired  
	private EmployeeService employeeService;
	
	@Autowired
	private OutBoundService outBoundService;
	
	
	/**
	 * 销售订单历史按单据
	 * */
	@RequestMapping("/sellorderHistoryMaster")  
	public String SellorderHistoryMaster(Model model)
	{
		 List<OrderMasterInformationVo>OrderMasterInformationVoList=orderService.selectOrderByForm();
		 model.addAttribute("OrderMasterInformationVoList", OrderMasterInformationVoList);
         return "sellorder-historyMaster"; 
	}
	
	/**
	 * 销售订单历史按明细 查询所有
	 * */
	@RequestMapping("/sellorderHistoryDetail")
	public String SellorderHistoryDetail(Model model)
	{
		List<OrderDetailInformationVo> orderDetailInformationVoList=orderService.selectOrderByDetail();
		model.addAttribute("orderDetailInformationVoList", orderDetailInformationVoList);
		return "sellorder-historyDetail";//销售订单历史 ---按明细
	}
	
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
		return "sellClient";
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
		return "sellworker";
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
		return "sellCommodity";
	}
	
	
	
	
	/**
	 * 商品查询
	 * */
	@RequestMapping("/erp/order/queryj")  
	public String query(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Model model)
	{		
		 name=httpServletRequest.getParameter("name");
		 List<Commodity>  CommodityList=commodityService.findCommodityByFuzzyCommodityName("%"+name+"%");
		 model.addAttribute("CommodityList", CommodityList);
         return "sellcommodity"; 
	}
	
	
	/**
	 * 销售订单界面
	 * */
	@RequestMapping("/order")    //主页页面tab栏跳转url 测试示例
	public String Sellorder(Model model)
	{
		model.addAttribute("commodityList",commodityList);
		model.addAttribute("client", client);
		model.addAttribute("employee", employee);
		return "sellorder";   //销售订单界面
	}
	
	/**
	 * 销售订单追踪页面
	 * */
	@RequestMapping("/orderTail")
	public String OutboundHistoryMaster(Model model)
	{
		 List<OrderMasterInformationVo>OrderMasterInformationVoList=orderService.selectOrderByForm();
		 model.addAttribute("OrderMasterInformationVoList", OrderMasterInformationVoList);
		 return "sellorder-tail";   
	}
	
	/**
	 *销售订单添加商品处理
	 * */
	@RequestMapping("/addCommodity")
	public String addCommodity(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		int flag=0;
		for(int i=0;i<commodityList.size();i++) 
		{
			if(Integer.valueOf(httpServletRequest.getParameter("id"))==commodityList.get(i).getCommodityId())
			{
				flag=1;
			}
		}
		if(flag==0)
			commodityList.add(commodityService.findCommodityById(Integer.valueOf(httpServletRequest.getParameter("id"))));
		if(commodityList.size()==0)
			commodityList.add(commodityService.findCommodityById(Integer.valueOf(httpServletRequest.getParameter("id"))));
		return "redirect:/erp/order/order";		
	}
	
	/**
	 * 销售订单添加用户处理
	 * */
	@RequestMapping("/addClient")
	public String addClient(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		client=clientService.findClientById(Integer.valueOf(httpServletRequest.getParameter("clientId")));
		return "redirect:/erp/order/order";		
	}
	
	
	/**
	 * 客户查询
	 * */
	@RequestMapping("/erp/order/queryC")  
	public String queryC(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Model model)
	{	
		 name=httpServletRequest.getParameter("name");
		 List<Client>  ClientList=clientService.findClientByFuzzyClientName("%"+name+"%");
		 model.addAttribute("ClientList", ClientList);
         return "sellClient"; 
	}
	
	
	/**
	 * 部员查询
	 * */
	@RequestMapping("/erp/order/queryW")  
	public String queryW(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Model model)
	{	
		 name=httpServletRequest.getParameter("name");
		 List<EmployeeVo>  EmployeeList=employeeService.findEmployeeVoByFuzzyEmployeeName("%"+name+"%");
		 model.addAttribute("EmployeeList", EmployeeList);
         return "sellworker"; 
	}
	
	
	/**
	 * 销售订单添加部员处理
	 * */
	@RequestMapping("/addEmployee")
	public String addEmployee(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		employee=employeeService.findEmployeeById(Integer.valueOf(httpServletRequest.getParameter("EmployeeId")));
		return "redirect:/erp/order/order";		
	}
	
	/**
	 * 移除添加的商品
	 * */
	@RequestMapping("/commodityRemove")
	public String commodityRemove(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		for(int i=0;i<commodityList.size();i++)
			if(commodityList.get(i).getCommodityId()==Integer.valueOf(httpServletRequest.getParameter("CommodityId")))
				commodityList.remove(i);
		return "redirect:/erp/order/order";		
	}
	

	/**
	 * 核心业务  提交销售订单
	 * */
	@RequestMapping("/submitOrder")
	public String submitOrder(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		
		/*
		 * 得到JS的字符数组即商品id列表,数量列表,运费价格列表
		 * */
		String[] commodityIdList = httpServletRequest.getParameterValues("commodityIdList[]");
		String[] numList = httpServletRequest.getParameterValues("numList[]");
		String[] transPriceList = httpServletRequest.getParameterValues("transPriceList[]");
		
		double transTotal=0; //总运费
		double orderTotal=0; //总商品金额
		
		
		OrderMaster orderMaster=new OrderMaster();
		List<OrderDetail>orderDetaillist=new ArrayList<OrderDetail>();
		
		/*视图层传送数据给服务层*/
		
	    for(int i=0;i<commodityIdList.length;i++)
	    {
	    	OrderDetail orderDetail=new OrderDetail();
	    	orderDetail.setCommodityFreight(Double.parseDouble(transPriceList[i]));  //设置订单明细的运费
	    	orderDetail.setCommodityId(Integer.valueOf(commodityIdList[i]));		//设置订单明细中的商品id
	    	orderDetail.setCommodityNum(Integer.valueOf(numList[i]));				//设置订单明细中的商品数量
	    	orderDetail.setOutBoundNumber(0);
	        Commodity commodity=commodityService.findCommodityById(Integer.valueOf(commodityIdList[i])); //得到订单明细中本条商品信息
	    	transTotal=transTotal+Double.parseDouble(transPriceList[i]);   //计算总运费
	    	if(client.getIsVip().equals("是"))
	    	orderTotal=orderTotal+(commodity.getVipAccount())*Integer.valueOf(numList[i]); //vip客户计算总金额
	    	else
	    	orderTotal=orderTotal+(commodity.getPrice())*Integer.valueOf(numList[i]); //非vip客户计算总金额
	    	orderDetaillist.add(orderDetail);  //将每条订单明细加入订单明细集合中    	
	    } 
	    orderMaster.setOutboundNum(0);   //初始化出库总数量
		orderMaster.setClientId(client.getClientId());
		orderMaster.setOrderDate(new Date());
		orderMaster.setOrderState("订单处理中");
		orderMaster.setOrderTotal(orderTotal);
		orderMaster.setTransTotal(transTotal);
		orderMaster.setEmployeeId(employee.getEmployeeId());
		
		orderService.insertOrder(orderDetaillist, orderMaster);  //调用服务层的订单插入方法
	  
		/*初始化全局变量*/
		commodityList=new ArrayList<Commodity>();
		client=new Client();
		employee=new Employee();
		return "redirect:/erp/order/order";
	}
	
	/**
	 *   订单追踪发货界面
	 * */
	@RequestMapping("/orderOutbound")
	public String orderOutbound(Model model,int id)
	{
		List<OrderDetailInformationVo> orderDetailInformationVoList=orderService.findOrderDetailListById(id);
		
		model.addAttribute("orderDetailInformationVoList",orderDetailInformationVoList);
		model.addAttribute("employeeI", orderDetailInformationVoList.get(0).getOrderMasterInformationVo().getEmployee());
		model.addAttribute("clientI", orderDetailInformationVoList.get(0).getOrderMasterInformationVo().getClient());
		
		model.addAttribute("orderId",id);
		return "orderOutbound";//销售订单--追踪
	}
	

	/**
	 * 核心业务 按订单出库商品
	 * */
	@RequestMapping("/submitOrderOutBound")
	public String 	submitOrderOutBound(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		
		/*
		 * 得到JS的字符数组  得到本次出库数量列表
		 * */
		
		/*
		 * 按订单出库逻辑
		 * 1.前端传回数据  知道是哪个订单出库 因为要关联明细表  
		 * 2.要完成出库主表的信息,出库明细表的录入,以及Id,然后调用服务层方法插入出库主表信息
		 * 3.服务层根据插入后出库主表返回的自增Id,将所有明细插入出库表中
		 * 4.前端通过th:onclick将item.getOrderMasterInformationVo().getOrderId()作为参数传给按钮方法  前端要判断数量的值是否大于了他本来的值
		 * 5.服务层要将订单明细表中的每一条商品的出库数量做出对应的更改
		 * 6.服务层要判断要对应着去修改订单主表的状态   部分发货  发货完成
		 * */
		
		
		String[] numList = httpServletRequest.getParameterValues("numList[]");//每个订单明细的发货数量
		
		
		OutboundOrder outboundOrder=new OutboundOrder();//出库主表
		
		List<OutboundDetail>outboundOrderList=new ArrayList<OutboundDetail>();//出库明细集合
		
		for(int i=0;i<numList.length;i++)
		{
			OutboundDetail outboundDetail=new OutboundDetail();
			outboundDetail.setOutboundNum(Integer.valueOf(numList[i]));  //设置出库明细每个商品的数量的出库数量
			outboundOrderList.add(outboundDetail);
		}
		outBoundService.insertOutBoundByOrder(outboundOrder, outboundOrderList,Integer.valueOf(httpServletRequest.getParameter("orderId")).intValue());
		
		return "redirect:/erp/order/orderTail";
	}
	
	
}
