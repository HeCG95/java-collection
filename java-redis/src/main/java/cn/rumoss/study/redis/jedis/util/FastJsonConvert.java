package cn.rumoss.study.redis.jedis.util;

import com.alibaba.fastjson.JSON;

public class FastJsonConvert {

    /**
     * Convert Object to JSON string
     * @param obj
     * @return
     */
    public static String convertObject2JSON(Object obj) {

        try{
            String text = JSON.toJSONString(obj);
            return text;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Convert JSON string to Object
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convertJSON2Object(String data, Class<T> clazz) {

        try{
            T t = JSON.parseObject(data, clazz);
            return t;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
