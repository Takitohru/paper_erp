package erp_test.bean;

public class OrderDetail {
	private Integer orderId;		//订单ID
	private Integer commodityId;	//商品ID
	private Integer commodityNum;	//商品数量
	private Double  commodityFreight;//商品运费
	private Integer outBoundNumber;  //发货数量
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public Integer getCommodityNum() {
		return commodityNum;
	}
	public void setCommodityNum(Integer commodityNum) {
		this.commodityNum = commodityNum;
	}
	public Double getCommodityFreight() {
		return commodityFreight;
	}
	public void setCommodityFreight(Double commodityFreight) {
		this.commodityFreight = commodityFreight;
	}
	public Integer getOutBoundNumber() {
		return outBoundNumber;
	}
	public void setOutBoundNumber(Integer outBoundNumber) {
		this.outBoundNumber = outBoundNumber;
	}
	@Override
	public String toString() {
		return "OrderDetail [orderId=" + orderId + ", commodityId=" + commodityId + ", commodityNum=" + commodityNum
				+ ", commodityFreight=" + commodityFreight + ", outBoundNumber=" + outBoundNumber + "]";
	}
	
	
}
