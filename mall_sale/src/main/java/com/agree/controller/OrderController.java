package com.agree.controller;

import com.agree.bean.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SessionAttributes(value = "order")
@Controller
public class OrderController {

    @RequestMapping(value = "/goto_chechOrder")
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
            List<T_MALL_FLOW> list_flow = new ArrayList<>();
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
}
