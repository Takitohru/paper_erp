package erp_test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp_test.bean.Apartment;
import erp_test.bean.Apartment;
import erp_test.dao.ApartmentDao;
import erp_test.service.ApartmentService;

@Service
public class ApartmentServiceImpl implements ApartmentService {

	@Autowired
	ApartmentDao apartmentDao;
	
	
	@Override
	public void insert(Apartment apartment) {
		// TODO Auto-generated method stub
		apartmentDao.insertApartment(apartment);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		apartmentDao.deleteApartment(id);
	}

	@Override
	public void update(Apartment apartment) {
		// TODO Auto-generated method stub
		apartmentDao.updateApartment(apartment);
	}

	@Override
	public List<Apartment> findAllApartment() {
		// TODO Auto-generated method stub
		return apartmentDao.findAllApartmentList();
	}

	@Override
	public Apartment findApartmentById(int id) {
		// TODO Auto-generated method stub
		return apartmentDao.findApartmentById(id);
	}
	
	public List<Apartment> findApartmentByFuzzyApartmentName(String str) {
		// TODO Auto-generated method stub
		return apartmentDao.findApartmentByFuzzyApartmentName(str);
	}
}
