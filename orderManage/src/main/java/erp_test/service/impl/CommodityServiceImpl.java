package erp_test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp_test.bean.Commodity;
import erp_test.bean.Commodity;
import erp_test.dao.CommodityDao;
import erp_test.service.CommodityService;
import erp_test.vo.CommodityPieVo;


@Service
public class CommodityServiceImpl implements CommodityService
{

	@Autowired
	CommodityDao commodityDao;
		
	
	@Override
	public List<CommodityPieVo> findTopFiveCommodity() {
		// TODO Auto-generated method stub
		return commodityDao.findTopFiveCommodity();
	}
	
	/**
	 * 商品添加
	 * */
	@Override
	public void insert(Commodity commodity) 
	{
		// TODO Auto-generated method stub
		commodityDao.insertCommodity(commodity);
	}

	
	/**
	 * 商品删除
	 * */
	@Override
	public void delete(int id) 
	{
		// TODO Auto-generated method stub
		commodityDao.deleteCommodity(id);
	}

	
	/**
	 * 商品更新
	 * */
	@Override
	public void update(Commodity commodity) 
	{
		// TODO Auto-generated method stub
		commodityDao.updateCommodity(commodity);
	}

	
	/**
	 * 返回所有商品信息
	 * */
	@Override
	public List<Commodity> findAllCommodity()
	{
		// TODO Auto-generated method stub
		List<Commodity> commodityList=commodityDao.findAllCommodityList();
		return commodityList;
	}


	@Override
	public Commodity findCommodityById(int id)
	{
		// TODO Auto-generated method stub
		return commodityDao.findCommodityById(id);
	}
	
	public List<Commodity> findCommodityByFuzzyCommodityName(String str) {
		// TODO Auto-generated method stub
		return commodityDao.findCommodityByFuzzyCommodityName(str);
	}
}
