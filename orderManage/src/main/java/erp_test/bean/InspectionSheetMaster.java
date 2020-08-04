package erp_test.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InspectionSheetMaster {
	private Integer inspectionSheetId;
	private Integer purchaseId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date inspectionDate;
	private Integer employeeId;
	private Double payablePrice;
	
	public Integer getInspectionSheetId() {
		return inspectionSheetId;
	}
	public void setInspectionSheetId(Integer inspectionSheetId) {
		this.inspectionSheetId = inspectionSheetId;
	}
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public Date getInspectionDate() {
		return inspectionDate;
	}
	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Double getPayablePrice() {
		return payablePrice;
	}
	public void setPayablePrice(Double payablePrice) {
		this.payablePrice = payablePrice;
	}
	@Override
	public String toString() {
		return "InspectionSheetMaster [inspectionSheetId=" + inspectionSheetId + ", purchaseId=" + purchaseId
				+ ", inspectionDate=" + inspectionDate + ", employeeId=" + employeeId + ", payablePrice=" + payablePrice
				+ "]";
	}
	
	
}
