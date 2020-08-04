package erp_test.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OutboundOrder {
	private Integer outboundId;  //出库单ID
	private Integer employeeId;  //出库操作员
	private Integer clientId;    //客户id 
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date outboundDate;   //出库日期
	
	public Integer getOutboundId() {
		return outboundId;
	}
	public void setOutboundId(Integer outboundId) {
		this.outboundId = outboundId;
	}
	public Date getOutboundDate() {
		return outboundDate;
	}
	public void setOutboundDate(Date outboundDate) {
		this.outboundDate = outboundDate;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	@Override
	public String toString() {
		return "OutboundOrder [outboundId=" + outboundId + ", outboundDate=" + outboundDate
				+ ", employeeId=" + employeeId + ", clientId=" + clientId + "]";
	}
}
