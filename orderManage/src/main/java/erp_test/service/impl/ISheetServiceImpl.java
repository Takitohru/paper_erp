package erp_test.service.impl;


import erp_test.bean.InspectionSheetMaster;
import erp_test.dao.ISheetDao;
import erp_test.service.ISheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ISheetServiceImpl implements ISheetService {
    @Autowired
    private ISheetDao iSheetDao;

    @Override
    public List<InspectionSheetMaster> selectAllCheck() {
        return iSheetDao.selectAllCheck();
    }

    @Override
    public int insertCheckDetail(Integer inspectionSheetId, Integer commodityId, Integer actualAcceptance, Double price, String inspectionSituation) {
        return iSheetDao.insertCheckDetail(inspectionSheetId,commodityId,actualAcceptance,price,inspectionSituation);
    }

    @Override
    public int updateInventory(Integer id, Integer num) {
        return iSheetDao.updateInventory(id,num);
    }

    @Override
    public Integer insertCheckMaster(Integer purchaseId, Integer employeeId, double payablePrice) {
        iSheetDao.insertCheckMaster(purchaseId,employeeId,payablePrice);//插入一条记录，返回主键最大值
        return  iSheetDao.getCheckMasterId();
    }
}
