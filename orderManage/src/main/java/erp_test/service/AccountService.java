package erp_test.service;

import erp_test.bean.Account;



public interface AccountService 
{
		/**
		 * 处理与部门有关的逻辑业务层
		 */
	public Account checkAccount(String id,String password);
	
	public Account getAccount();
}
