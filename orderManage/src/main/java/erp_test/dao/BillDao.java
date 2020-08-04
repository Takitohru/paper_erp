package erp_test.dao;

import erp_test.bean.BillDetail;
import erp_test.bean.BillMaster;
import erp_test.vo.InCompleteOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BillDao {

    @Select("select * from v_incompleteOrderList")
    List<InCompleteOrder> getAllIncompleteOrder();

    @Select("<script>"+
            "select * from v_incompleteOrderList where clientId=#{clientId}"+
            "<if test=\"ids.size != 0\"> and orderId not in "+
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'> #{item} </foreach> "+
            "</if>"+
            "</script>")
    List<InCompleteOrder> getIncompleteOrderByClientId(@Param("clientId") int clientId, @Param("ids") List<Integer> ids);

    @Insert("<script>"+
            "insert into  Billmaster(billId, type, customerid, `create`,`name`) " +
            "values (#{master.billId},#{t},#{master.customerid},NOW(),#{master.name})"+
            "</script>")
    int recordBillMaster(@Param("master") BillMaster master,@Param("t")int type);

    @Insert("<script>"+
            "insert into Billdetail(billId, related, type,verified, unverified, thistime) values"+
            "<foreach item='item' index='index' collection='list' separator=','>" +
            " (#{item.billId},#{item.orderId},#{t},#{item.received},#{item.unpay},#{item.thistime})" +
            " </foreach> "+
            "</script>")
    int recordBillDetail(@Param("list") List<BillDetail> list,@Param("t")int type);

    @Select("select * from v_billMaster")
    List<BillMaster> getAllBillMaster();

    @Select("select * from v_billMaster where billId=#{b}")
    BillMaster getBillMasterByBillId(@Param("b") String billId);

    @Select("select * from v_billDetail where billId=#{billId}")
    List<BillDetail> getBillDetailByBillId(@Param("billId") String billId);

    @Select("select * from v_paymentincompletedlist")
    List<InCompleteOrder> getAllIncompletePurchaseOrder();

    @Select("<script>"+
            "select * from v_paymentincompletedlist where clientId=#{supplierId}"+
            "<if test=\"ids.size != 0\"> and orderId not in "+
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'> #{item} </foreach> "+
            "</if>"+
            "</script>")
    List<InCompleteOrder> getIncompletePurchaseOrderBySupplierId(
            @Param("supplierId") int supplierId, @Param("ids") List<Integer> ids);

    @Select("select * from v_paymentbillmaster")
    List<BillMaster> getAllPaymentBillMaster();

    @Select("select * from v_paymentbillmaster where billId=#{b}")
    BillMaster getPaymentBillMasterByBillId(@Param("b") String billId);

    @Select("select * from v_paymentBillDetail where billId=#{billId}")
    List<BillDetail> getPaymentBillDetailByBillId(@Param("billId") String billId);

    @Update("update ordermaster set orderState=#{status} where orderId=#{orderid}")
    void updateOrderStatus(@Param("orderid")int orderid,@Param("status")String status);
}
