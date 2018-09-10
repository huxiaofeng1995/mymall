package com.agree.controller;

import com.agree.bean.T_MALL_SHOPPINGCAR;
import com.agree.bean.T_MALL_USER_ACCOUNT;
import com.agree.server.LoginServer;
import com.agree.service.CartService;
import com.agree.util.MyPropertyUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value="/login")
    public String login(@CookieValue(value = "list_cart_cookie",required = false) String list_cart_cookie, T_MALL_USER_ACCOUNT user, HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap map){
        T_MALL_USER_ACCOUNT select_user1 = new T_MALL_USER_ACCOUNT();
                //登录，远程用户认证接口
        JaxWsProxyFactoryBean jwfb = new JaxWsProxyFactoryBean();
        jwfb.setAddress(MyPropertyUtil.getProperty("ws.properties", "login_url"));
        jwfb.setServiceClass(LoginServer.class);
        LoginServer loginServer = (LoginServer) jwfb.create();
        String result = loginServer.login(user);
        T_MALL_USER_ACCOUNT select_user = (T_MALL_USER_ACCOUNT) JSON.parse(result);
        //user.setId(1);//测试使用
        if(select_user.getId() == 0){
            return "redirect:/goto_login.do";
        }else {
            session.setAttribute("user", select_user);
            Cookie cookie = null;
            try {
                cookie = new Cookie("yh_nch", URLEncoder.encode(user.getYh_mch(), "utf-8"));//中文转码
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            cookie.setPath("/");//当类上加了requestMapping注解后，一定要setPath（"/"）
            cookie.setMaxAge(60 * 60 * 24);//必须设置过期时间，否则秒过期
            response.addCookie(cookie);

            //同步购物车
            combine_cart(user, response, session, list_cart_cookie, map);
        }
        return "redirect:/index.do";
    }

    private void combine_cart(T_MALL_USER_ACCOUNT user, HttpServletResponse response, HttpSession session, String list_cart_cookie,ModelMap map) {
        List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
        List<T_MALL_SHOPPINGCAR> list_cart_db = new ArrayList<T_MALL_SHOPPINGCAR>();

        list_cart_db = cartService.get_cart_list_by_user(user);//在db中的购物车
        if(StringUtils.isBlank(list_cart_cookie)){

        }else {
            try {
                list_cart_cookie = URLDecoder.decode(list_cart_cookie , "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            list_cart = JSON.parseArray(list_cart_cookie,T_MALL_SHOPPINGCAR.class);//在cookie中的购物车
            for(T_MALL_SHOPPINGCAR cart : list_cart){
                cart.setYh_id(user.getId());//注意cookie中取出来的cart是没有用户id的，这里我们要设置用户id再执行插入
                boolean flag = cartService.if_cart_exists(cart);
                if(!flag) {
                    //db不存在
                    cartService.add_cart(cart);
                }else {
                    //db存在
                    for(T_MALL_SHOPPINGCAR cart_db : list_cart_db){
                        if(cart_db.getSku_id() == cart.getSku_id()){
                            cart_db.setTjshl(cart_db.getTjshl()+cart.getTjshl());
                            cart_db.setHj(cart_db.getTjshl()*cart_db.getSku_jg());
                            cartService.update_cart(cart_db);
                        }
                    }
                }
            }

        }
        int shp_count = 0 ;
        double sum = 0.0;
        //同步session,清空cookie
        session.setAttribute("list_cart_session", cartService.get_cart_list_by_user(user));
        for(T_MALL_SHOPPINGCAR cart : list_cart){
            shp_count += cart.getTjshl();
            sum += cart.getHj();
        }
        map.put("shp_count", shp_count);
        map.put("sum", sum);
        response.addCookie(new Cookie("list_cart_cookie",""));
    }
}
