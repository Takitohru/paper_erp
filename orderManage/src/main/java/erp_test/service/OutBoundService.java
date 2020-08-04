package erp_test.service;

import java.util.List;

import erp_test.bean.OutboundDetail;
import erp_test.bean.OutboundOrder;
import erp_test.vo.OutBoundDetailVo;
import erp_test.vo.OutBoundMasterVo;

public interface OutBoundService
{
	/**出库业务抽象层 负责所有关于出库的业务逻辑处理
	 * **/
	
	
	/**
	 * 提交出库方法
	 * 该方法实现了用户根据订单进行出库的方法
	 * 直接录入信息出库
	 * */
	public void insertOutBound(OutboundOrder outboundOrder,List<OutboundDetail>orderDetaillist);
	
	
	/**通过订单表出库
	 * */
	public void insertOutBoundByOrder(OutboundOrder outboundOrder,List<OutboundDetail>orderDetaillist,int orderId);
	
	
	/**
	 * 该方法实现了用户对出库单的查询
	 * 按单据
	 * */
	
	public List<OutBoundMasterVo> selectOutBoundByMaster();
	
	/**
	 * 该方法实现了用户对出库单的查询
	 * 按明细
	 * */
	
	public List<OutBoundDetailVo> selectOutBoundByDetail();
	
	/**
	 * 该方法实现了用户对出库订单删除的方法
	 */
	
	public void deleteOutBound();
	
	/**
	 * 该方法实现了用户对出库订单修改的方法*/
	public void updateOutBound();
	
	public List<OutBoundDetailVo> getOutboundTableByOutboundId(int outboundId);
}
