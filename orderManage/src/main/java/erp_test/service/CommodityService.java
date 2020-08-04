package erp_test.service;

import java.util.List;

import erp_test.bean.Commodity;
import erp_test.vo.CommodityPieVo;
import erp_test.bean.Commodity;

public interface CommodityService 
{
	/**
	 * 处理与商品信息有关的逻辑业务层  --调用Dao层方法实现业务
	 * */
	public void insert(Commodity commodity);
	public void delete(int id);
	public void update(Commodity commodity);
	public List<Commodity> findAllCommodity();
	public Commodity findCommodityById(int id);
	
	public List<Commodity> findCommodityByFuzzyCommodityName(String str);
	public List<CommodityPieVo> findTopFiveCommodity();
}
