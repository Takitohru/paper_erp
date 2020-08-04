package erp_test.dao;

import erp_test.bean.InspectionSheetMaster;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ISheetDao {
    @Insert("insert into inspectionsheetmaster(purchaseId,employeeId,payablePrice) "
            + "values(#{purchaseId},#{employeeId},#{payablePrice})")
    @Options(useGeneratedKeys = true, keyProperty = "inspectionSheetId")
    public int insertCheckMaster(Integer purchaseId, Integer employeeId, double payablePrice);//插入一条验货主表记录
    @Select("select max(inspectionSheetId) from inspectionsheetmaster")
    public Integer getCheckMasterId();//查询主键最大id
    @Insert("insert into inspectionsheetdetail(inspectionSheetId,commodityId,inspectionSituation,price,actualAcceptance)"
    +" values(#{inspectionSheetId},#{commodityId},#{inspectionSituation},#{price},#{actualAcceptance})")
    public int insertCheckDetail(Integer inspectionSheetId, Integer commodityId, Integer actualAcceptance, Double price, String inspectionSituation);//插入验货明细
    @Select("Select * from inspectionsheetmaster")//查询所有验货单
    public List<InspectionSheetMaster> selectAllCheck();
    @Update("update commodity set inventoryNumber=inventoryNumber+#{num} where commodityId=#{id}")
    public int updateInventory(Integer id,Integer num);
}
