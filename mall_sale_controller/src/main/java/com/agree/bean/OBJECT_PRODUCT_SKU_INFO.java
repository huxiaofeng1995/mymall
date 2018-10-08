package com.agree.bean;

public class OBJECT_PRODUCT_SKU_INFO extends T_MALL_PRODUCT_SKU_INFO{
    private T_MALL_PRODUCT_COLOR color;

    public T_MALL_PRODUCT_VERSION getVersion() {
        return version;
    }

    public void setVersion(T_MALL_PRODUCT_VERSION version) {
        this.version = version;
    }

    private T_MALL_PRODUCT_VERSION version;

    public T_MALL_PRODUCT_COLOR getColor() {
        return color;
    }

    public void setColor(T_MALL_PRODUCT_COLOR color) {
        this.color = color;
    }
}
