package com.agree.mapper;

import com.agree.bean.T_MALL_PRODUCT;
import com.agree.bean.T_MALL_PRODUCT_COLOR;
import com.agree.bean.T_MALL_PRODUCT_VERSION;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SpuMapper {
    public void insert_spu(T_MALL_PRODUCT product);

    public void insert_imgs(Map<String, Object> map);

    public List<T_MALL_PRODUCT> select_spu_list(@Param("pp_id") int pp_id, @Param("flbh2") int flbh2);

    void insert_colors(Map<String, Object> map);

    void insert_versions(Map<String, Object> map);

    List<T_MALL_PRODUCT_COLOR> select_color_list(int spu_id);
    List<T_MALL_PRODUCT_VERSION> select_version_list(int spu_id);
}
