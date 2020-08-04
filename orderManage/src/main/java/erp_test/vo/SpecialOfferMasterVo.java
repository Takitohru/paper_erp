package erp_test.vo;

import erp_test.bean.Client;
import erp_test.bean.Employee;
import erp_test.bean.SpecialOfferMaster;

public class SpecialOfferMasterVo extends SpecialOfferMaster
{
	private Employee employee;
	private Client   client;
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
