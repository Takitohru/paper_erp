package erp_test.repository;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import erp_test.bean.Account;
import erp_test.bean.Client;
import erp_test.dao.AccountDao;
import erp_test.dao.ClientDao;
import erp_test.dao.OrderDetailDao;
import erp_test.dao.OrderMasterDao;
import erp_test.vo.OrderDetailInformationVo;
import erp_test.vo.OrderMasterInformationVo;


@RunWith(SpringRunner.class)
@SpringBootTest
public class eTest {
	
	@Autowired
	ClientDao clientDao;
	
	@Autowired
	OrderDetailDao  orderDetailDao;
	
	@Autowired
	OrderMasterDao orderMasterDao; 
	
	@Autowired
	AccountDao accountDao;
	
	@Test
	public void findClientTest()
	{
		List<Client>list=clientDao.findClientByFuzzyClientName("%"+" "+"%");
		System.out.println(list.size());
		for(int i=0;i<list.size();i++)
		{		
			System.out.println(list.get(i).toString());
		}
	}
	
}
