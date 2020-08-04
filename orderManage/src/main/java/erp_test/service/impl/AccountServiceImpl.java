package erp_test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp_test.bean.Account;
import erp_test.dao.AccountDao;
import erp_test.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	

	@Autowired
	AccountDao accountDao;
	
	
	@Override
	public Account checkAccount(String id, String password) {
		// TODO Auto-generated method stub
		// 调用Dao层的方法验证账号密码
		return accountDao.findAccount(id, password);
	}


	@Override
	public Account getAccount() {
		// TODO Auto-generated method stub
		return accountDao.getAccount();
	}
	
	

}
