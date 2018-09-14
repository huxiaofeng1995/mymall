package com.agree.controller;

import com.agree.bean.*;
import com.agree.exception.OverSaleException;
import com.agree.server.UserServer;
import com.agree.service.CartService;
import com.agree.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@SessionAttributes(value = {"order","list_adrress"})
@Controller
public class OrderController {

    @Autowired
    private UserServer userServer;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/goto_checkOrder")
    public String goto_checkOrder(HttpSession session, ModelMap map){
        List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
        if(user == null){
            return "redirect:/goto_login_check.do";
        }else {
            //session获取购物车列表
            list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
            /* 一个订单对应多个物流flow
             * 一个库存地址对应一个物流flow
             * 一个选中的购物车对应一个订单信息order_info
             * 一个物流对应多个订单信息
             */
            OBJECT_T_MALL_ORDER order = new OBJECT_T_MALL_ORDER();
            order.setJdh(1);
            order.setYh_id(user.getId());
            order.setZje(getMoney(list_cart));
            List<OBJECT_T_MALL_FLOW> list_flow = new ArrayList<>();
            //获取选中商品的去重后的库存地址
            Set<String> set_kcdz = new HashSet<>();
            for(T_MALL_SHOPPINGCAR cart : list_cart){
                if(cart.getShfxz().equals("1")){
                    set_kcdz.add(cart.getKcdz());
                }
            }
            //根据库存地址生成送货清单-->flow
            for(String kcdz : set_kcdz){
                OBJECT_T_MALL_FLOW flow = new OBJECT_T_MALL_FLOW();
                flow.setMqdd("商品未出库");
                flow.setPsfsh("小红快递");
                flow.setYh_id(user.getId());
                List<T_MALL_ORDER_INFO> list_info = new ArrayList<>();
                //根据购物车生成订单信息
                for(T_MALL_SHOPPINGCAR cart : list_cart){
                    if(cart.getShfxz().equals("1") && cart.getKcdz().equals(kcdz)){
                        T_MALL_ORDER_INFO info = new T_MALL_ORDER_INFO();
                        info.setGwch_id(cart.getId());
                        info.setShp_tp(cart.getShp_tp());
                        info.setSku_id(cart.getSku_id());
                        info.setSku_jg(cart.getSku_jg());
                        info.setSku_kcdz(kcdz);
                        info.setSku_mch(cart.getSku_mch());
                        info.setSku_shl(cart.getTjshl());
                        list_info.add(info);
                    }
                }
                flow.setList_info(list_info);
                list_flow.add(flow);
            }
            order.setList_flow(list_flow);
            map.put("order", order);//与sessionAttributes配合，将订单信息放入session中，实现跨请求访问
        }
        List<T_MALL_ADDRESS> list_address = new ArrayList<>();
        try {
            list_address = userServer.get_address_list(user);
        }catch (Exception e){
            e.printStackTrace();
            map.put("error", new ErrorBean("500", "请求地址服务出错啦！请重新选择购物车结算！"));
            return "error";
        }
        map.put("list_address", list_address);
        return "checkOrder";
    }

    private BigDecimal getMoney(List<T_MALL_SHOPPINGCAR> list_cart) {
        BigDecimal sum = new BigDecimal("0");
        for(T_MALL_SHOPPINGCAR cart : list_cart){
            if(cart.getShfxz().equals("1")) {//选中的才统计金额
                sum = sum.add(new BigDecimal(cart.getHj() + ""));//添加前要先转换
            }
        }
        return sum;
    }

    @RequestMapping(value = "/save_order")
    public String save_order(HttpSession session, @ModelAttribute(value = "order")OBJECT_T_MALL_ORDER order,T_MALL_ADDRESS addr,Map map){
        //根据id获取地址信息
        try{
            addr = userServer.get_address(addr.getId());
        }catch (Exception e){
            e.printStackTrace();
            map.put("error", new ErrorBean("500", "请求地址服务出错啦！请重新选择购物车结算！"));
            return "error";
        }
        //保存order订单对象
        orderService.save_order(order, addr);
        //同步session
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
        session.setAttribute("list_cart_session", cartService.get_cart_list_by_user(user));
        // 重定向到支付服务，传入订单号和交易金额
        return "redirect:/goto_pay.do";
    }

    @RequestMapping(value = "/goto_pay")
    public String goto_pay() {
        // 伪支付服务
        return "pay";
    }

    @RequestMapping(value = "/pay_success")
    public String pay_success(@ModelAttribute(value = "order")OBJECT_T_MALL_ORDER order,Map map){
        //修改订单物流信息
        // 操作sku和库存
        try {
            orderService.pay(order);
        } catch (OverSaleException e) {
            e.printStackTrace();
            map.put("error", new ErrorBean("500", e.getMessage()));
            return "error";

        }
        return "redirect:/order_success.do";
    }

    @RequestMapping(value = "/order_success")
    public String order_success(){
        return "orderSuccess";
    }
}
