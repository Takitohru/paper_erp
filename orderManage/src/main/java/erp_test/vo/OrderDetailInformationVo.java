package erp_test.vo;


import erp_test.bean.Commodity;
import erp_test.bean.OrderDetail;
public class OrderDetailInformationVo extends OrderDetail
{	
	private Commodity commodity;
	private OrderMasterInformationVo orderMasterInformationVo;
	public OrderMasterInformationVo getOrderMasterInformationVo() {
		return orderMasterInformationVo;
	}
	public void setOrderMasterInformationVo(OrderMasterInformationVo orderMasterInformationVo) {
		this.orderMasterInformationVo = orderMasterInformationVo;
	}
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
}
