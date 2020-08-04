package erp_test.dao;

import erp_test.bean.Purchase;
import erp_test.bean.PurchaseDetail;
import erp_test.bean.PurchaseEmpolyee;
import erp_test.bean.PurchaseSupplier;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PurchaseDao {
    @Select("select supplierId,supplierName from supplier")
    public List<PurchaseSupplier> getSupplier();//获取供应商名称
    @Select("select employeeId,employeeName from employee")
    public List<PurchaseEmpolyee> getEmployee();//获取采购员
    @Select("select distinct(commodityName) from commodity")
    public List<String> getCname();//获取商品名称
    @Select("select distinct(brand) from commodity where commodityName =#{commodityName}")
    public List<String> getCbrand(String commodityName);//根据名称获取品牌
    @Select("select distinct(paperClass) from commodity where commodityName=#{commodityName} and brand=#{brand}")
    public List<String> getCclass(String commodityName, String brand);//根据名称获取纸种类
    @Select("select distinct(paperSize) from commodity where commodityName=#{commodityName} and brand=#{brand} and paperClass=#{paperClass}")
    public List<String> getCsize(String commodityName, String brand, String paperClass);//根据名称获取纸种规格
    @Select("select distinct(`rank`) from commodity where commodityName=#{commodityName} and brand=#{brand} and paperClass=#{paperClass} and paperSize=#{paperSize}")
    public List<String> getCrank(String commodityName, String brand, String paperClass, String paperSize);//根据名称获取纸等级
    @Select("select commodityId from commodity where commodityName=#{commodityName} and brand=#{brand} and paperClass=#{paperClass} and paperSize=#{paperSize} and `rank`=#{Crank}")
    public int getCid(String commodityName, String brand, String paperClass, String paperSize, String Crank);//获取商品id
    @Insert("insert into purchase(supplierId,employeeId,totalPrice,status) "
            + "values(#{supplierId},#{employeeId},#{totalPrice},'未入库')")
    @Options(useGeneratedKeys = true, keyProperty = "purchaseId")
    public int insertPurchase(Integer supplierId, Integer employeeId, double totalPrice);//插入一条采购单记录
    @Select("select Max(purchaseId) from purchase")
    public int getPurchaseId();//返回刚刚插入的订单id,因为主键自增，所以是最大的id
    @Insert("insert into purchasedetail values(#{purchaseId},#{commodityId},#{purchasePrice},#{quantity})")
    public int insertPurchaseDetail(Integer purchaseId, Integer commodityId, double purchasePrice, Integer quantity);//插入一条采购明细
    @Select("Select * from purchase where status='未入库'")
    public List<Purchase> getAllPurchase();//查询所有采购单
    @Select("Select * from purchasedetail where purchaseId=#{purchaseId}")
    public List<PurchaseDetail> selectDetailById(Integer purchaseId);
    @Select("Select totalPrice from purchase where purchaseId=#{purchaseId}")
    public double selectTotalPrice(Integer purchaseId);
    @Update("update purchase set status='已出库' where purchaseId=#{purchaseId}")
    public int updateState(Integer purchaseId);
    @Select("Select * from purchase where status='已出库'")
    public List<Purchase> getPurchaseHistory();//查询所有已采购单
}
