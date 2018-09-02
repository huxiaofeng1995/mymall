package com.agree.service.impl;

import com.agree.bean.MODEL_T_MALL_ATTR;
import com.agree.bean.OBJECT_T_MALL_ATTR;
import com.agree.bean.T_MALL_PRODUCT;
import com.agree.bean.T_MALL_VALUE;
import com.agree.mapper.AttrMapper;

import com.agree.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    private AttrMapper attrMapper;


    @Override
    public List<OBJECT_T_MALL_ATTR> get_attr_list(int flbh2) {
        return attrMapper.select_attr_list(flbh2);
    }
}
