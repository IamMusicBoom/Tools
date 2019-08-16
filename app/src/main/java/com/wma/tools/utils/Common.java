package com.wma.tools.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王明骜 on 19-8-14 下午1:46.
 */
public class Common {
    public static Map<String,String> keyType = new HashMap<>();
    static {
        keyType.put("头条","top");
        keyType.put("社会","shehui");
        keyType.put("国内","guonei");
        keyType.put("国际","guoji");
        keyType.put("娱乐","yule");
        keyType.put("体育","tiyu");
        keyType.put("军事","junshi");
        keyType.put("科技","keji");
        keyType.put("财经","caijing");
        keyType.put("时尚","shishang");
    }
    public static final String WIDS = "wids.txt";

    public static final String CITIES = "cities.txt";


    //云南.txt   吉林.txt 宁夏.txt 山西.txt 新疆.txt 河北.txt 海南.txt 澳门.txt 西藏.txt 重庆.txt 香港.txt
    //内蒙古.txt 四川.txt 安徽.txt 广东.txt 江苏.txt 河南.txt 湖北.txt 甘肃.txt 贵州.txt 陕西.txt 黑龙江.txt
    //上海.txt   台湾.txt   天津.txt 山东.txt 广西.txt 江西.txt 浙江.txt 湖南.txt 福建.txt 辽宁.txt 青海.txt


    public static HashMap<String,String> WID_MAP;

    public static HashMap<String,String> CITY_MAP;

}
