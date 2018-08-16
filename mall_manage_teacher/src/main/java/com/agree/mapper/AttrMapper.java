package com.agree.mapper;

import com.agree.bean.OBJECT_T_MALL_ATTR;
import com.agree.bean.T_MALL_ATTR;
import com.agree.bean.T_MALL_VALUE;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttrMapper {

    public void insert_attr(@Param("flbh2") int flbh2, @Param("attr")OBJECT_T_MALL_ATTR attr);

    public void insert_values(@Param("shxid") int shxid, @Param("list_value")List<T_MALL_VALUE> list);
}
