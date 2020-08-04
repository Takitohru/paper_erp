package erp_test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import erp_test.bean.Commodity;
import erp_test.vo.CommodityPieVo;
import erp_test.bean.Commodity;


@Mapper
public interface CommodityDao {
	
	/*增*/
	@Insert("insert into Commodity (commodityName,brand,paperClass,paperSize,paperWeight,price,vipAccount,rank,safeInventory,inventoryNumber) "
			+ "values("
			+ "#{commodityName},#{brand},#{paperClass},#{paperSize},#{paperWeight},"
			+ "#{price},#{vipAccount},#{rank},#{safeInventory},#{inventoryNumber})")
    @Options(useGeneratedKeys = true, keyProperty = "commodityId")
	public int insertCommodity(Commodity commodity);
	
	/*删*/
	@Delete("delete from Commodity where commodityId=#{commodityId}")
	public int deleteCommodity(@Param("commodityId") Integer commodityId);
	
	/*修改*/
	@Update("update Commodity set "
			+ "commodityName=#{commodityName},brand=#{brand},paperClass=#{paperClass},"
			+ "paperSize=#{paperSize},paperWeight=#{paperWeight},price=#{price},"
			+ "vipAccount=#{vipAccount},rank=#{rank},"
			+ "safeInventory=#{safeInventory},inventoryNumber=#{inventoryNumber} "
			+ "where commodityId=#{commodityId}")
	public int updateCommodity(Commodity commodity);
	
	/*查byID*/
	@Select("select * from Commodity where commodityId=#{commodityId} ")
	public Commodity findCommodityById(@Param("commodityId") Integer commodityId);
	
	/*查询所有*/
	@Select("select * from Commodity")
	public List<Commodity> findAllCommodityList();
	
	/*模糊查询*/
	//注：此处为模糊查询，参数中是已拼接好的字符串，如 commodityName= "%"+str+"%"
	@Select("select * from Commodity where commodityName like #{commodityName} ")
	public List<Commodity> findCommodityByFuzzyCommodityName(@Param("commodityName") String commodityName);
	
	@Select("select c.commodityName,a.value " + 
			"from (" + 
			"select commodityId,sum(commodityNum) as value " + 
			"from orderdetail " + 
			"group by commodityId " + 
			"order by value desc limit 0,5 " + 
			")a,commodity c " + 
			"where a.commodityId=c.commodityId")
	public List<CommodityPieVo> findTopFiveCommodity();
}
