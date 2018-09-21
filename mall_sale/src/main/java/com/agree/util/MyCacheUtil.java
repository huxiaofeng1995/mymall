package com.agree.util;

import com.agree.bean.OBJECT_T_MALL_SKU;
import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MyCacheUtil {

    public static <T> List<T> getList(String key, Class<T> t) {
        Jedis jedis = null;
        List<T> list = new ArrayList<T>();
        try {
            jedis = JedisUtils.getJedis();
            Set<String> set = jedis.zrange(key, 0, -1);//先从zset里找出sku_id集合
            for (String str : set) {//这里遍历的每个str都是一个sku_id
                String objectStr = jedis.hget("sku", str);//再从hash里根据id找出sku信息
                objectStr = URLDecoder.decode(objectStr,"utf-8");
                list.add(JSONObject.parseObject(objectStr,t));
            }
            return list;
        } catch (Exception e) {
            //记日志
            e.printStackTrace();
        }
        return null;
    }

    public static void setList(String key, List<OBJECT_T_MALL_SKU> list) {
        Jedis jedis = null;
        try {
            jedis = JedisUtils.getJedis();
            jedis.del(key);
            for(int i=0; i<list.size();i++){
                String id = String.valueOf(list.get(i).getId());
                jedis.zadd(key,list.get(i).getSku_xl(), id);//销量当socre,id当值
                if(!jedis.hexists("sku",id)) {
                    String skujsonstr = URLEncoder.encode(JSONObject.toJSONString(list.get(i)), "utf-8");
                    jedis.hset("sku", id, skujsonstr);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setListByAttr(String key, List<OBJECT_T_MALL_SKU> list) {
        Jedis jedis = null;
        try {
            jedis = JedisUtils.getJedis();
            jedis.del(key);
            for(int i=0; i<list.size();i++){
                String id = String.valueOf(list.get(i).getId());
                jedis.zadd(key,list.get(i).getSku_xl(), id);//销量当socre,id当值
                //此处不再需要维护hash里的sku信息了，因为flbh2已经将所有该分类下的sku维护进去了，这里再维护就多余了
//                if(!jedis.hexists("sku",id)) {
//                    String skujsonstr = URLEncoder.encode(JSONObject.toJSONString(list.get(i)), "utf-8");
//                    jedis.hset("sku", id, skujsonstr);
//                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<OBJECT_T_MALL_SKU> getListByAttr(String[] key) {
        Jedis jedis = null;
        List<OBJECT_T_MALL_SKU> list = new ArrayList<OBJECT_T_MALL_SKU>();
        try {
            jedis = JedisUtils.getJedis();
            //生成动态的key，避免操作相同key导致并发问题
            String k0 = "combine";
            for(int i = 0;i < key.length; i++){
                k0 = k0 + "_" + key[i];
            }
            if(!JedisUtils.existsKey(k0)) {
                jedis.zinterstore(k0, key);
            }
            Set<String> set = jedis.zrange(k0,0,-1);
            for (String str : set) {
                String objectStr = jedis.hget("sku", str);//从hash里根据id找出sku信息
                objectStr = URLDecoder.decode(objectStr,"utf-8");
                list.add(JSONObject.parseObject(objectStr,OBJECT_T_MALL_SKU.class));
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

}
