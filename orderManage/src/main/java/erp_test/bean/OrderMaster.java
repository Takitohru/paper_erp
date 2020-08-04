package erp_test.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderMaster {
	private Integer orderId;		//订单ID
	private Integer clientId;		//客户ID
	private Integer employeeId;     //处理员ID
	private Double orderTotal;		//订单总金额
	private Double transTotal;		//订单总运费
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date orderDate;			//订单日期
	private String orderState;		//订单状态
	private int outboundNum;		//总出库数量
	private int commoditySum;		//商品总数
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public Double getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}
	public Double getTransTotal() {
		return transTotal;
	}
	public void setTransTotal(Double transTotal) {
		this.transTotal = transTotal;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public int getOutboundNum() {
		return outboundNum;
	}
	public void setOutboundNum(int outboundNum) {
		this.outboundNum = outboundNum;
	}
	public int getCommoditySum() {
		return commoditySum;
	}
	public void setCommoditySum(int commoditySum) {
		this.commoditySum = commoditySum;
	}
	public String toString() {
		return "OrderMaster [orderId=" + orderId + ", clientId=" + clientId + ", employeeId=" + employeeId
				+ ", orderTotal=" + orderTotal + ", transTotal=" + transTotal + ", orderDate=" + orderDate
				+ ", orderState=" + orderState + ", outboundNum=" + outboundNum + "]";
	}

}
