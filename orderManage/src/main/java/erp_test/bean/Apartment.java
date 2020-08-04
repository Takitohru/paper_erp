package erp_test.bean;

public class Apartment {
	private Integer apartmentId;
	private String apartmentName;
	
	public Integer getApartmentId() {
		return apartmentId;
	}
	public void setApartmentId(Integer apartmentId) {
		this.apartmentId = apartmentId;
	}
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	@Override
	public String toString() {
		return "Apartment [apartmentId=" + apartmentId + ", apartmentName=" + apartmentName + "]";
	}
	
	
}
