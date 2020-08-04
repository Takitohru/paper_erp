package erp_test.service;



import erp_test.bean.Purchase;
import erp_test.bean.PurchaseDetail;
import erp_test.bean.PurchaseEmpolyee;
import erp_test.bean.PurchaseSupplier;

import java.util.List;

public interface PurchaseService {
    public List<PurchaseSupplier> getSupplier();
    public List<PurchaseEmpolyee> getEmployee();
    public List<String> getCname();
    public List<String> getCbrand(String commodityName);
    public List<String> getCclass(String commodityName, String brand);
    public List<String> getCsize(String commodityName, String brand, String paperClass);
    public List<String> getCrank(String commodityName, String brand, String paperClass, String paperSize);
    public int getCid(String commodityName, String brand, String paperClass, String paperSize, String Crank);
    public int insertPurchase(Integer supplierId, Integer employeeId, double totalPrice);
    public int insertPurchaseDetail(Integer purchaseId, Integer commodityId, double purchasePrice, Integer quantity);
    public List<Purchase> getAllPurchase();//查询所有采购单
    public List<PurchaseDetail> selectDetailById(Integer purchaseId);
    public double selectTotalPrice(Integer purchaseId);
    public int updateState(Integer purchaseId);
    public List<Purchase> getPurchaseHistory();//查询所有已采购单
}
