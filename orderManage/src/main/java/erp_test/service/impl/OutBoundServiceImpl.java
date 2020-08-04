package erp_test.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp_test.bean.Commodity;
import erp_test.bean.OrderDetail;
import erp_test.bean.OrderMaster;
import erp_test.bean.OutboundDetail;
import erp_test.bean.OutboundOrder;
import erp_test.dao.OrderDetailDao;
import erp_test.dao.OrderMasterDao;
import erp_test.dao.OutboundDetailDao;
import erp_test.dao.OutboundOrderDao;
import erp_test.service.CommodityService;
import erp_test.service.OutBoundService;
import erp_test.vo.OrderDetailInformationVo;
import erp_test.vo.OutBoundDetailVo;
import erp_test.vo.OutBoundMasterVo;


@Service
public class OutBoundServiceImpl implements OutBoundService
{
	/**出库业务抽象实现层 负责所有关于出库的业务逻辑处理
	 * **/
	
	//自动装配实现Dao接口
	@Autowired
	OrderDetailDao orderDetailDao;
	@Autowired
	OrderMasterDao orderMasterDao;
	@Autowired
	OutboundOrderDao outboundOrderDao;
	@Autowired
	OutboundDetailDao outboundDetailDao;
	@Autowired
	CommodityService commodityService;

	
	/**
	 * 提交出库方法--提交出库单有两种方式 
	 * 1.通过采购单出库  可以部分出库或者全部出库
	 * 2.通过录入信息出库  类似于销售订单的填写
	 * 该方法实现了用户根据订单进行出库的方法
	 * 订单出库前端返回的数据有订单号,对应的每个商品的信息以及该商品本次出库的数量
	 * 
	 * */
	@Override
	public void insertOutBoundByOrder(OutboundOrder outboundOrder,List<OutboundDetail>outboundDetaillist,int orderId)
	{
		OrderMaster orderMaster=orderMasterDao.findOrderMasterById(orderId);
		
		//录入主表信息
		outboundOrder.setClientId(orderMaster.getClientId());
		outboundOrder.setEmployeeId(orderMaster.getEmployeeId());
		outboundOrder.setOutboundDate(new Date());
		
		List<OrderDetail> orderDetailList=orderDetailDao.findOrderDetailByOrderId(orderId);    //调用服务层方法给OrderId查出对应的全部订单明细表的信息
		
		int CountOutboundNum=0;  //计算已经发货的商品数量
		int AccountMasterNum=0;  //计算需要发货的全部数量
		outboundOrderDao.insertOutboundOrder(outboundOrder);  //将信息录入本次出库主表并得到自增Id
		
		for(int i=0;i<outboundDetaillist.size();i++)
		{     
			
			Commodity commodity=commodityService.findCommodityById(orderDetailList.get(i).getCommodityId());
			commodity.setInventoryNumber(commodity.getInventoryNumber()-outboundDetaillist.get(i).getOutboundNum());  //出库后修改对应仓库中的物料库存数
			commodityService.update(commodity);
			 
			outboundDetaillist.get(i).setOutboundId(outboundOrder.getOutboundId());  //为每条明细添加主表返回的自增ID
			outboundDetaillist.get(i).setCommodityId(orderDetailList.get(i).getCommodityId());//出库表添加商品Id		
			outboundDetailDao.insertOutboundDetail(outboundDetaillist.get(i));   //将出库明细表信息插入数据库
			
			CountOutboundNum=CountOutboundNum+outboundDetaillist.get(i).getOutboundNum()+orderDetailList.get(i).getOutBoundNumber(); //计算本次出库以后的总商品数量
			AccountMasterNum=AccountMasterNum+orderDetailList.get(i).getCommodityNum();
			orderDetailList.get(i).setOutBoundNumber(outboundDetaillist.get(i).getOutboundNum()+orderDetailList.get(i).getOutBoundNumber());
			orderDetailDao.updateOrderDetailForOutBoundNum(orderDetailList.get(i));
		}
		
		/*改变订单状态逻辑*/
		
		if(AccountMasterNum>CountOutboundNum) //说明还是部分发货状态
			orderMaster.setOrderState("部分发货");
		else
			orderMaster.setOrderState("发货完成");
		orderMaster.setOutboundNum(CountOutboundNum); 
		
		orderMasterDao.updateOrderMaster(orderMaster);
		
			
	}
	
	@Override
	public List<OutBoundDetailVo> getOutboundTableByOutboundId(int outboundId) {
		return outboundDetailDao.findOutBoundDetailByOutboundId(outboundId);
	}
	
	/*直接录入信息出库*/
	@Override
	public void insertOutBound(OutboundOrder outboundOrder,List<OutboundDetail>outboundDetaillist)
	{
		outboundOrder.setOutboundDate(new Date());
		 outboundOrderDao.insertOutboundOrder(outboundOrder);    //信息录入本次出库主表
		 for(int i=0;i<outboundDetaillist.size();i++) {
			 Commodity commodity=commodityService.findCommodityById(outboundDetaillist.get(i).getCommodityId());
			 commodity.setInventoryNumber(commodity.getInventoryNumber()-outboundDetaillist.get(i).getOutboundNum());  //出库后修改对应仓库中的物料库存数
			 commodityService.update(commodity);
			 outboundDetaillist.get(i).setOutboundId(outboundOrder.getOutboundId());
			 outboundDetailDao.insertOutboundDetail(outboundDetaillist.get(i));   //将信息录入本次的出库明细表
		 }
			 
	}
	
	/**
	 * 该方法实现了用户对出库单的查询
	 * 按单据
	 * */
	@Override
	public List<OutBoundMasterVo> selectOutBoundByMaster()
	{
		List<OutBoundMasterVo> OutBoundMasterVolist=outboundOrderDao.findOutboundOrderByForm();   //查询出所有主表内容
		return OutBoundMasterVolist;
	}
	
	/** 该方法实现了用户对出库单的查询
	 *  按明细
	 * @return
	 */
	@Override
	public List<OutBoundDetailVo> selectOutBoundByDetail()
	{
		List<OutBoundDetailVo> OutBoundDetailVo = outboundDetailDao.findOutBoundDetailVo();  //返回出库明细信息
		return OutBoundDetailVo;
	}
	
	/**
	 * 该方法实现了用户对出库订单删除的方法
	 */
	@Override
	public void deleteOutBound()
	{
		
	}
	
	/**
	 * 该方法实现了用户对出库订单修改的方法*/
	@Override
	public void updateOutBound()
	{
		
	}
}
