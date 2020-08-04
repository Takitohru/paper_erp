package erp_test.vo;

import erp_test.bean.Apartment;
import erp_test.bean.Employee;

public class EmployeeVo extends Employee
{
	private Apartment apartment;

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}
	
}
