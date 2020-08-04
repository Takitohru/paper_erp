package erp_test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp_test.bean.OrderDetail;
import erp_test.bean.OrderMaster;
import erp_test.dao.OrderDetailDao;
import erp_test.dao.OrderMasterDao;
import erp_test.service.OrderService;
import erp_test.vo.OrderDetailInformationVo;
import erp_test.vo.OrderMasterInformationVo;



@Service
public class OrderServiceImpl implements OrderService {

	/**利用dao层对业务逻辑进行处理     前后端分离  
	 * Vo层负责直接显示页面的数据
	 * Dao负责前端返回后端的数据交互
	 * 多表查询都转化为单表连接查询
	 * */
	
	@Autowired
	OrderDetailDao orderDetailDao;
	@Autowired
	OrderMasterDao orderMasterDao;
	
	/*由controller层处理数据化为orderDetail  orderMaster传入服务层  orderDetail中的id需要调用
	 * 主表插入后才能得到
	 * */
	@Override
	public void insertOrder(List<OrderDetail> orderDetaillist,OrderMaster orderMaster) 
	{
		// TODO Auto-generated method stub	
		/*下订单后处理业务过程   插入订单主表   插入订单明细*/	 
		int sum=0;
		for(int i=0;i<orderDetaillist.size();i++)
		{
			sum=sum+orderDetaillist.get(i).getCommodityNum();
		}
		orderMaster.setCommoditySum(sum);
		orderMasterDao.insertOrderMaster(orderMaster);
		for(int i=0;i<orderDetaillist.size();i++)         //每条明细插入一次
		{
			orderDetaillist.get(i).setOrderId(orderMaster.getOrderId());   //将订单主表返回的订单id放入明细中
			orderDetailDao.insertOrderDetail(orderDetaillist.get(i));
		}
	}
	
	/**
	 * 该方法实现了用户对历史销售订单的查询
	 * 按单据
	 * */
	@Override
	public List<OrderMasterInformationVo> selectOrderByForm() 
	{		
		// TODO Auto-generated method stub
	  List<OrderMasterInformationVo> orderMasterInformationVo=orderMasterDao.findOrderMasterInformations();
	  //返回一个OrderMasterInformationVo的对象
	  return orderMasterInformationVo;
	}

	/**
	 * 该方法实现了用户对历史销售订单的查询
	 * 按明细
	 * */
	@Override
	public List<OrderDetailInformationVo> selectOrderByDetail() 
	{
		List<OrderDetailInformationVo> orderDetailInformationVolist=orderDetailDao.findOrderDetail();
		//返回该Vo对象
		return orderDetailInformationVolist;
	}
	
	/**
	 * 该方法实现了用户对销售订单的删除*/
	@Override
	public void deleteOrder(int id) 
	{
		// TODO Auto-generated method stub
	}

	/**
	 * 该方法实现了用户对销售订单的更新*/
	@Override
	public void updateOrder(OrderDetail orderDetail) 
	{
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrderDetailInformationVo> findOrderDetailListById(int orderId) {
		// TODO Auto-generated method stub
		
		List<OrderDetailInformationVo> orderDetailInformationVolist=orderDetailDao.findOrderDetailListById(orderId);
		//返回该Vo对象
		return orderDetailInformationVolist;
	}
	@Override
	public List<OrderDetail> findOrderDetailsbyOrderId(int id) {
		// TODO Auto-generated method stub
		List<OrderDetail> orderDetail = orderDetailDao.findOrderDetailByOrderId(id);
		return orderDetail;
	}

}
