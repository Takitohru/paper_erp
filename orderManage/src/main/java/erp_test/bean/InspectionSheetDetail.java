package erp_test.bean;

public class InspectionSheetDetail 
{
	private Integer inspectionSheetId;
	private Integer commodityId;
	private String inspectionSituation;
	private Double price;
	private Integer actualAcceptance;
	
	public Integer getInspectionSheetId() {
		return inspectionSheetId;
	}
	public void setInspectionSheetId(Integer inspectionSheetId) {
		this.inspectionSheetId = inspectionSheetId;
	}
	public Integer getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public String getInspectionSituation() {
		return inspectionSituation;
	}
	public void setInspectionSituation(String inspectionSituation) {
		this.inspectionSituation = inspectionSituation;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getActualAcceptance() {
		return actualAcceptance;
	}
	public void setActualAcceptance(Integer actualAcceptance) {
		this.actualAcceptance = actualAcceptance;
	}
	@Override
	public String toString() {
		return "InspectionSheetDetail [inspectionSheetId=" + inspectionSheetId + ", commodityId=" + commodityId
				+ ", inspectionSituation=" + inspectionSituation + ", price=" + price + ", actualAcceptance="
				+ actualAcceptance + "]";
	}
}
