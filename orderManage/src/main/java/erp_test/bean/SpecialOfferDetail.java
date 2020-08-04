package erp_test.bean;

public class SpecialOfferDetail {
	private Integer commdityId;		//商品ID
	private Double specialPrice;	//特价
	private Integer specialOfferId;	//特价表ID
	
	public Integer getCommdityId() {
		return commdityId;
	}
	public void setCommdityId(Integer commdityId) {
		this.commdityId = commdityId;
	}
	public Double getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(Double specialPrice) {
		this.specialPrice = specialPrice;
	}
	public Integer getSpecialOfferId() {
		return specialOfferId;
	}
	public void setSpecialOfferId(Integer specialOfferId) {
		this.specialOfferId = specialOfferId;
	}
	@Override
	public String toString() {
		return "SpecialOfferDetail [commdityId=" + commdityId + ", specialPrice=" + specialPrice + ", specialOfferId="
				+ specialOfferId + "]";
	}

	
}
