package com.agree.service;

import com.agree.bean.MODEL_T_MALL_ATTR;
import com.agree.bean.OBJECT_T_MALL_ATTR;
import com.agree.bean.T_MALL_PRODUCT;

import java.util.List;

public interface AttrService {

    public List<OBJECT_T_MALL_ATTR> get_attr_list(int flbh2);
}
