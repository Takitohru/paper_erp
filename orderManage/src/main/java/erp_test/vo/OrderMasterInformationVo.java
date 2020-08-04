package erp_test.vo;

import erp_test.bean.Client;
import erp_test.bean.Employee;
import erp_test.bean.OrderMaster;

public class OrderMasterInformationVo extends OrderMaster{

	private Employee employee;
	private Client client;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
}
