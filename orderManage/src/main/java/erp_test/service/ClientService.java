package erp_test.service;

import java.util.List;

import erp_test.bean.Client;


public interface ClientService {
	/**
	 * 处理与客户信息有关的逻辑业务层  --调用Dao层方法实现业务
	 * */
	
	public void insert(Client client);
	public void delete(int id);
	public void update(Client client);
	public List<Client> findAllClient();
	public Client findClientById(int id);
	
	public List<Client> findClientByFuzzyClientName(String str);
}
