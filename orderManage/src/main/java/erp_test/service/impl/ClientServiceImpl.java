package erp_test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp_test.bean.Client;
import erp_test.dao.ClientDao;
import erp_test.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService
{

	@Autowired
	ClientDao clientDao;
	
	@Override
	public void insert(Client client) {
		// TODO Auto-generated method stub
		clientDao.insertClient(client);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		clientDao.deleteClient(id);
	}

	@Override
	public void update(Client client) {
		// TODO Auto-generated method stub
		clientDao.updateClient(client);
	}

	@Override
	public List<Client> findAllClient() {
		// TODO Auto-generated method stub
		//List<Client> client = clientDao.findAllClientList();
		//return client;
		return clientDao.findAllClientList();
	}

	@Override
	public Client findClientById(int id) {
		// TODO Auto-generated method stub
		return clientDao.findClientById(id);
	}

	@Override
	public List<Client> findClientByFuzzyClientName(String str) {
		// TODO Auto-generated method stub
		return clientDao.findClientByFuzzyClientName(str);
	}

}
