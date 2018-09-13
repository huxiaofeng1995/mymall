package com.agree.service.impl;

import com.agree.bean.*;
import com.agree.mapper.CartMapper;
import com.agree.mapper.OrderMapper;
import com.agree.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void save_order(OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS addr) {
        List<Integer> list_id = new ArrayList<Integer>();//用来存储要删除的购物车id
        //保存order订单对象
        order.setDzh_id(addr.getId());
        order.setDzh_mch(addr.getYh_dz());
        order.setShhr(addr.getShjr());
        orderMapper.insert_order(order);
        //保存flow送货清单
        List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
        List<T_MALL_ORDER_INFO> list_info = new ArrayList<>();
        for(OBJECT_T_MALL_FLOW flow : list_flow){
            flow.setDd_id(order.getId());
            flow.setMdd(order.getDzh_mch());
            orderMapper.insert_flow(flow);
            //保存info订单信息
            list_info = flow.getList_info();
            Map<Object, Object> map_info = new HashMap<Object, Object>();
            map_info.put("list_info", list_info);
            map_info.put("dd_id", order.getId());
            map_info.put("flow_id", flow.getId());
            orderMapper.insert_infos(map_info);
            for(T_MALL_ORDER_INFO info : list_info){
                list_id.add(info.getGwch_id());
            }
        }
        //删除购物车信息
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("list_id", list_id);
        cartMapper.delete_carts(map);
    }
}
