package erp_test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import erp_test.bean.BillDetail;
import erp_test.bean.BillMaster;
import erp_test.bean.Client;
import erp_test.service.BillService;
import erp_test.vo.BillVo;
import erp_test.vo.InCompleteOrder;
import erp_test.vo.IncompleteOrdersVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/erp/collectByOrder")
public class CollectByOrderController {

    @Autowired
    private BillService billService;

    /**
     * 展示按单付款界面
     * @return 返回界面
     */
    @RequestMapping("/page")
    public String page() {
        return "collectByOrder";
    }
    /**
     * 展示按单付款界面
     * @return 返回界面
     */
    @RequestMapping("/selectOrder")
    public String pageSelect() {
        return "receiptSelectOrder";
    }

    @RequestMapping("/orders")
    @ResponseBody
    public IncompleteOrdersVo getIncompleteOrders(){
        IncompleteOrdersVo icoo=new IncompleteOrdersVo();
        List<InCompleteOrder> l=billService.getAllIncompleteOrder();

        icoo.setCode(0);
        icoo.setMsg("");
        icoo.setCount(l.size());
        icoo.setData(l);

        return icoo;
    }

    @RequestMapping("/getAllClient")
    @ResponseBody
    public List<Client> getAllClient(){
        return billService.getAllCilent();
    }

    @RequestMapping("/getIncompleteOrders/clientId")
    @ResponseBody
    public IncompleteOrdersVo getIncompleteOrderByClientId(@RequestParam("clientId") int clientId,
                                                           @RequestParam("ids[]") int[] ids){
        List<Integer> ll= Arrays.stream(ids).boxed().collect(Collectors.toList());
        IncompleteOrdersVo icoo=new IncompleteOrdersVo();
        List<InCompleteOrder> l=billService.getIncompleteOrderByClientId(clientId, ll);

        icoo.setCode(0);
        icoo.setMsg("");
        icoo.setCount(l.size());
        icoo.setData(l);

        return icoo;
    }

    @RequestMapping(value = "/recordBill",method = RequestMethod.POST)
    @ResponseBody
    public int recordBill(@RequestParam("customerId") int customerId,@RequestParam("name") String name,
                          @RequestParam("list") String list){
        JSONArray array = JSON.parseArray(list);
        List<InCompleteOrder> icol=array.toJavaList(InCompleteOrder.class);

        BillVo bv=parseBillVo(customerId,name,icol);

        return billService.recoredBill(bv,1);
    }

    /**
     * 格式化订单记录
     * @param customerId -客户id
     * @param name -业务员姓名
     * @param icol -原始数据
     * @return 格式化后的记录帐单的数据
     */
    public static BillVo parseBillVo(int customerId,String name,List<InCompleteOrder> icol){

        BillMaster bm=new BillMaster();
        bm.setBillId(String.valueOf(System.currentTimeMillis()));
        bm.setCustomerid(customerId);
        bm.setName(name);

        List<BillDetail> bd=new ArrayList<>();
        for(InCompleteOrder ic:icol){
            BillDetail b=new BillDetail();
            b.setBillId(bm.getBillId());
            b.setOrderId(ic.getOrderId());
            b.setReceived(ic.getReceived()+ic.getThistime());
            b.setUnpay(ic.getUnpay()-ic.getThistime());
            b.setThistime(ic.getThistime());
            bd.add(b);
        }

        BillVo bv=new BillVo();
        bv.setBillMaster(bm);
        bv.setList(bd);
        return bv;

    }

    @RequestMapping("/getBillMaster")
    @ResponseBody
    public List<BillMaster> getAllBillMasterList(@RequestParam("clientId") int clientId,
                                                   @RequestParam("ids[]") int[] ids){
        List<Integer> ll= Arrays.stream(ids).boxed().collect(Collectors.toList());
        IncompleteOrdersVo icoo=new IncompleteOrdersVo();
        List<InCompleteOrder> l=billService.getIncompleteOrderByClientId(clientId, ll);

        icoo.setCode(0);
        icoo.setMsg("");
        icoo.setCount(l.size());
        icoo.setData(l);

        return null;
    }


}
