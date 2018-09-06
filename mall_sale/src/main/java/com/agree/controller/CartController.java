package com.agree.controller;

import com.agree.bean.T_MALL_SHOPPINGCAR;
import com.agree.bean.T_MALL_USER_ACCOUNT;
import com.agree.service.CartService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("/miniCart")
    public String mini_cart(HttpSession session,@CookieValue(value = "list_cart_cookie",required = false) String list_cart_cookie,Map map){
        List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
        try {
            list_cart_cookie = URLDecoder.decode(list_cart_cookie , "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(user == null){
            //从cookie中获取
            if(!StringUtils.isBlank(list_cart_cookie)){
                list_cart = JSON.parseArray(list_cart_cookie,T_MALL_SHOPPINGCAR.class);
            }
        }else {
            list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
        }
        map.put("list_cart", list_cart);
        return "miniCartList";
    }

    @RequestMapping("/add_cart")
    public String add_cart(@CookieValue(value = "list_cart_cookie",required = false) String list_cart_cookie, T_MALL_SHOPPINGCAR cart, HttpServletResponse response, HttpSession session){
        List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
        int user_id = cart.getYh_id();
        if(user_id == 0){
            //用户未登录
            if(StringUtils.isBlank(list_cart_cookie)){
                //cookie为空
                list_cart.add(cart);
            }else{
                //cookit不为空
                list_cart = JSON.parseArray(list_cart_cookie,T_MALL_SHOPPINGCAR.class);
                if(contains(list_cart,cart)){
                    //cookie中存在，更新
                    for (int i = 0; i < list_cart.size(); i++) {
                        if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
                            list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + cart.getTjshl());//更新添加数量
                            list_cart.get(i).setHj(list_cart.get(i).getTjshl() * list_cart.get(i).getSku_jg());//更新价格合计
                        }
                    }
                }else {
                    //cookie中不存在，添加
                    list_cart.add(cart);
                }
            }
            String json = "";
            try {
                json = URLEncoder.encode(JSON.toJSONString(list_cart),"utf-8");//解决cookie中文乱码的问题
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Cookie cookie = new Cookie("list_cart_cookie", json);
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24);
            response.addCookie(cookie);
        }else {
            //用户已登录
            list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
            boolean b = cartService.if_cart_exists(cart);

            if (!b) {
                cartService.add_cart(cart);
                if (list_cart == null || list_cart.isEmpty()) {
                    list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
                    list_cart.add(cart);
                    session.setAttribute("list_cart_session", list_cart);//更新session
                } else {
                    list_cart.add(cart);
                }
            } else {
                for (int i = 0; i < list_cart.size(); i++) {
                    if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
                        list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + cart.getTjshl());
                        list_cart.get(i).setHj(list_cart.get(i).getTjshl() * list_cart.get(i).getSku_jg());
                        // 老车，更新
                        cartService.update_cart(list_cart.get(i));
                        //session.setAttribute("list_cart_session", list_cart);//更新session
                    }
                }
            }
        }
        return "redirect:/cart_success.do";
    }

    private Boolean contains(List<T_MALL_SHOPPINGCAR> list_cart, T_MALL_SHOPPINGCAR cart){
        Boolean flag = false;
        for(int i = 0; i <list_cart.size();i++){
            if(list_cart.get(i).getSku_id() == cart.getSku_id()){
                flag = true;
                break;
            }
        }
        return flag;
    }

    @RequestMapping("/cart_success")
    public String cart_success() {

        return "cartSuccess";
    }


}
