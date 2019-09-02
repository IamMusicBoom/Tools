package com.wma.tools.utils;

import com.wma.tools.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王明骜 on 19-8-14 下午1:46.
 */
public class Common {
    public static Map<String,String> keyType = new HashMap<>();

    public static HashMap<String,Integer> WID_IMG_MAP = new HashMap<>();
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

        WID_IMG_MAP.put("00", R.drawable.ic_00);
        WID_IMG_MAP.put("01", R.drawable.ic_01);
        WID_IMG_MAP.put("02", R.drawable.ic_02);
        WID_IMG_MAP.put("03", R.drawable.ic_03);
        WID_IMG_MAP.put("04", R.drawable.ic_04);
        WID_IMG_MAP.put("05", R.drawable.ic_05);
        WID_IMG_MAP.put("06", R.drawable.ic_06);
        WID_IMG_MAP.put("07", R.drawable.ic_07);
        WID_IMG_MAP.put("08", R.drawable.ic_08);
        WID_IMG_MAP.put("09", R.drawable.ic_09);

        WID_IMG_MAP.put("10", R.drawable.ic_10);
        WID_IMG_MAP.put("11", R.drawable.ic_11);
        WID_IMG_MAP.put("12", R.drawable.ic_12);
        WID_IMG_MAP.put("13", R.drawable.ic_13);
        WID_IMG_MAP.put("14", R.drawable.ic_14);
        WID_IMG_MAP.put("15", R.drawable.ic_15);
        WID_IMG_MAP.put("16", R.drawable.ic_16);
        WID_IMG_MAP.put("17", R.drawable.ic_17);
        WID_IMG_MAP.put("18", R.drawable.ic_18);
        WID_IMG_MAP.put("19", R.drawable.ic_19);

        WID_IMG_MAP.put("20", R.drawable.ic_20);
        WID_IMG_MAP.put("21", R.drawable.ic_21);
        WID_IMG_MAP.put("22", R.drawable.ic_22);
        WID_IMG_MAP.put("23", R.drawable.ic_23);
        WID_IMG_MAP.put("24", R.drawable.ic_24);
        WID_IMG_MAP.put("25", R.drawable.ic_25);
        WID_IMG_MAP.put("26", R.drawable.ic_26);
        WID_IMG_MAP.put("27", R.drawable.ic_27);
        WID_IMG_MAP.put("28", R.drawable.ic_28);
        WID_IMG_MAP.put("29", R.drawable.ic_29);


        WID_IMG_MAP.put("30", R.drawable.ic_30);
        WID_IMG_MAP.put("31", R.drawable.ic_31);

        WID_IMG_MAP.put("53", R.drawable.ic_53);

    }
    public static final String WIDS = "wids.txt";

    public static final String CITIES = "cities.txt";


    //云南.txt   吉林.txt 宁夏.txt 山西.txt 新疆.txt 河北.txt 海南.txt 澳门.txt 西藏.txt 重庆.txt 香港.txt
    //内蒙古.txt 四川.txt 安徽.txt 广东.txt 江苏.txt 河南.txt 湖北.txt 甘肃.txt 贵州.txt 陕西.txt 黑龙江.txt
    //上海.txt   台湾.txt   天津.txt 山东.txt 广西.txt 江西.txt 浙江.txt 湖南.txt 福建.txt 辽宁.txt 青海.txt


    public static HashMap<String,String> WID_MAP;



    public static final String CITY = "city";

    public static final String AIQ = "aiq";
    public static final String DIRECT = "direct";
    public static final String HUMIDITY = "humidity";

    public static final String INFO = "info";
    public static final String POWER = "power";
    public static final String TEMPERATURE = "temperature";
    public static final String WID = "wid";
}
