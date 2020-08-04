package erp_test.bean;

import java.util.Date;

public class SpecialOfferMaster {
	private Integer specialOfferId;	//特价表ID
	private Date createDate;		//创建日期
	private Integer clientId;		//客户ID
	//少了一个员工ID
	private Integer employeeId;		//员工ID
	
	public Integer getSpecialOfferId() {
		return specialOfferId;
	}
	public void setSpecialOfferId(Integer specialOfferId) {
		this.specialOfferId = specialOfferId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	@Override
	public String toString() {
		return "SpecialOfferMaster [specialOfferId=" + specialOfferId + ", createDate=" + createDate + ", clientId="
				+ clientId + ", employeeId=" + employeeId + "]";
	}
}
