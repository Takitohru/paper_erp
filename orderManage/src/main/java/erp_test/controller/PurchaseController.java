package erp_test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import erp_test.bean.Purchase;
import erp_test.bean.PurchaseEmpolyee;
import erp_test.bean.PurchaseSupplier;
import erp_test.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/j")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @RequestMapping("/purchase")//采购页面初始化
    public String toPurchase(Model model){
        List<PurchaseSupplier> supplierList=purchaseService.getSupplier();
        List<PurchaseEmpolyee> empolyeeList=purchaseService.getEmployee();
        List<String> cnameList=purchaseService.getCname();
        model.addAttribute("supplierList",supplierList);
        model.addAttribute("empolyeeList",empolyeeList);
        model.addAttribute("cnameList",cnameList);
        return "purchase";
    }
    @RequestMapping("/trackpurchase")
    public String toTrackPurchase(Model model){//跳转采购单跟踪
        return "trackpurchase";
    }
    @RequestMapping("/purchasehistory")
    public String toPurchaseHistory(Model model){
        return "purchasehistory";
    }
    @ResponseBody
    @RequestMapping("/getAllPurchase")//获取采购单跟踪数据
    public PageInfo<Purchase> getAllPurchase(@RequestParam(defaultValue = "1",value = "page") Integer page,
                                   @RequestParam(defaultValue = "10",value = "limit")Integer limit){
        PageHelper.startPage(page,limit);
        List<Purchase> list=purchaseService.getAllPurchase();
        PageInfo<Purchase> pageInfo=new PageInfo<Purchase>(list);
        return pageInfo;
    }
    @ResponseBody
    @RequestMapping("/getpurchasehistory")//获取采购历史数据
    public PageInfo<Purchase> getPurchaseHistory(@RequestParam(defaultValue = "1",value = "page") Integer page,
                                                 @RequestParam(defaultValue = "10",value = "limit")Integer limit){
        PageHelper.startPage(page,limit);
        List<Purchase> list=purchaseService.getPurchaseHistory();
        PageInfo<Purchase> pageInfo=new PageInfo<Purchase>(list);
        return pageInfo;
    }
    @ResponseBody
    @RequestMapping("/getBrand")//获取商品品牌
    public List<String> getBrand(String commodityName){
        System.out.println("获取:"+commodityName);
        List<String> list=purchaseService.getCbrand(commodityName);
        return list;
    }
    @ResponseBody
    @RequestMapping("/getClass")//获取商品种类
    public List<String> getClass(String commodityName,String brand){
        System.out.println("获取:"+commodityName+brand);
        List<String> list=purchaseService.getCclass(commodityName,brand);
        return list;
    }
    @ResponseBody
    @RequestMapping("/getSize")//获取商品规格
    public List<String> getSize(String commodityName,String brand,String paperClass){
        System.out.println("获取:"+commodityName+brand+paperClass);
        List<String> list=purchaseService.getCsize(commodityName,brand,paperClass);
        return list;
    }
    @ResponseBody
    @RequestMapping("/getRank")//获取商品等级
    public List<String> getRank(String commodityName,String brand,String paperClass,String paperSize){
        System.out.println("获取:"+commodityName+brand+paperClass+paperSize);
        List<String> list=purchaseService.getCrank(commodityName,brand,paperClass,paperSize);
        return list;
    }
    @ResponseBody
    @RequestMapping("/getCid")//获取商品id
    public int getCid(String commodityName,String brand,String paperClass,String paperSize,String Crank){
        //System.out.println("获取:"+commodityName+brand+paperClass+paperSize);
        int id=purchaseService.getCid(commodityName,brand,paperClass,paperSize,Crank);
        return id;
    }
    @ResponseBody
    @RequestMapping("/addNewPurchase")//添加采购单
    public int addNewPurchase(Integer supplierId,Integer employeeId,double totalPrice,@RequestParam String detailList){
        System.out.println("---------订单主表信息-------");
        System.out.println("供应商id："+supplierId+" 采购员id："+employeeId+" 总价"+totalPrice);
        Integer purchaseId=purchaseService.insertPurchase(supplierId,employeeId,totalPrice);
        System.out.println("采购单号："+purchaseId);
        JSONArray list = JSON.parseArray(detailList);
        for(int i=0;i<list.size();i++){
            JSONObject itemJson=(JSONObject)list.get(i);
            Integer id=Integer.parseInt(itemJson.getString("commodityId"));
            Double price=Double.parseDouble(itemJson.getString("purchasePrice"));
            Integer num=Integer.parseInt(itemJson.getString("quantity"));
            System.out.println("商品id:"+id+" 采购单价:"+price+" 采购数量："+num);
            purchaseService.insertPurchaseDetail(purchaseId,id,price,num);
        }
        //获取数据之后开始生成采购单与采购明细
        return 1;
    }
//    @ResponseBody
//    @RequestMapping("/test")//测试用
//    public PageInfo<Purchase> test(@RequestParam(defaultValue = "1",value = "page") Integer page,
//                               @RequestParam(defaultValue = "10",value = "limit")Integer limit){
//        PageHelper.startPage(page,limit);
//        List<Purchase> list=purchaseService.getAllPurchase();
//        PageInfo<Purchase> pageInfo=new PageInfo<Purchase>(list);
//        return pageInfo;
//    }
}
