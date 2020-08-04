package erp_test.service;

import erp_test.bean.InspectionSheetMaster;

import java.util.List;

public interface ISheetService {
    public Integer insertCheckMaster(Integer purchaseId, Integer employeeId, double payablePrice);//插入一条验货主表记录
    public int insertCheckDetail(Integer inspectionSheetId, Integer commodityId, Integer actualAcceptance, Double price, String inspectionSituation);//插入验货明细
    public List<InspectionSheetMaster> selectAllCheck();
    public int updateInventory(Integer id,Integer num);
}
