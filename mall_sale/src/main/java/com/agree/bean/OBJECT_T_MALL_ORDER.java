package com.agree.bean;

import java.util.List;

public class OBJECT_T_MALL_ORDER extends T_MALL_ORDER{
    private List<T_MALL_FLOW> list_flow;

    public List<T_MALL_FLOW> getList_flow() {
        return list_flow;
    }

    public void setList_flow(List<T_MALL_FLOW> list_flow) {
        this.list_flow = list_flow;
    }
}