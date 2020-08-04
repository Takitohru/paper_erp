package erp_test.service;

import java.util.List;

import erp_test.bean.OrderDetail;
import erp_test.bean.OrderMaster;
import erp_test.vo.OrderDetailInformationVo;
import erp_test.vo.OrderMasterInformationVo;

public interface OrderService 
{
	/**订单业务抽象层 负责所有关于销售订单的业务逻辑处理
	 * **/
	
	/*商品销售一栏除原价外增加一个特价,如果当前商品存在特价则显示特价 否则为空,输入客户id后生效*/
	
	/**
	 * 提交销售订单方法
	 * 该方法实现了系统使用者选中商品添加商品输入信息后提交订单并与数据库交互的过程*/
	public  void insertOrder(List<OrderDetail> orderDetail,OrderMaster orderMaster);
	
	/**
	 * 该方法实现了用户对历史销售订单的查询
	 * 按单据*/
	public List<OrderMasterInformationVo> selectOrderByForm();
	
	/**
	 * 该方法实现了用户对历史销售订单的查询
	 * 按明细*/
	public List<OrderDetailInformationVo> selectOrderByDetail();
	/**
	 * 该方法实现了用户对销售订单删除的方法
	 */
	public void deleteOrder(int id);
	
	/**
	 * 该方法实现了用户对销售订单修改的方法*/
	public void updateOrder(OrderDetail orderDetail);

	/**
	 * 该方法实现了用户按订单号查询所有订单信息*/
	public List<OrderDetailInformationVo> findOrderDetailListById(int orderId);
	
	/**
	 * 实现通过订单id查出该id的订单明细
	 */
	public List<OrderDetail> findOrderDetailsbyOrderId(int id);
}
