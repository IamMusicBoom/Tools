package com.wma.tools.model.weather;

import java.util.List;

/**
 * Created by 王明骜 on 19-9-3 下午2:37.
 */
public class Model {

    /**
     * error_code : 0
     * iAllApi : {}
     * result : [{"city":"成都","district":"成都","id":"2037","province":"四川"},{"city":"成都","district":"龙泉驿","id":"2038","province":"四川"},{"city":"成都","district":"新都","id":"2039","province":"四川"},{"city":"成都","district":"温江","id":"2040","province":"四川"},{"city":"成都","district":"金堂","id":"2041","province":"四川"},{"city":"成都","district":"双流","id":"2042","province":"四川"},{"city":"成都","district":"郫县","id":"2043","province":"四川"},{"city":"成都","district":"大邑","id":"2044","province":"四川"},{"city":"成都","district":"蒲江","id":"2045","province":"四川"},{"city":"成都","district":"新津","id":"2046","province":"四川"},{"city":"成都","district":"都江堰","id":"2047","province":"四川"},{"city":"成都","district":"彭州","id":"2048","province":"四川"},{"city":"成都","district":"邛崃","id":"2049","province":"四川"},{"city":"成都","district":"崇州","id":"2050","province":"四川"},{"city":"攀枝花","district":"攀枝花","id":"2051","province":"四川"},{"city":"攀枝花","district":"仁和","id":"2052","province":"四川"},{"city":"攀枝花","district":"米易","id":"2053","province":"四川"},{"city":"攀枝花","district":"盐边","id":"2054","province":"四川"},{"city":"自贡","district":"自贡","id":"2055","province":"四川"},{"city":"自贡","district":"富顺","id":"2056","province":"四川"},{"city":"自贡","district":"荣县","id":"2057","province":"四川"},{"city":"绵阳","district":"绵阳","id":"2058","province":"四川"},{"city":"绵阳","district":"三台","id":"2059","province":"四川"},{"city":"绵阳","district":"盐亭","id":"2060","province":"四川"},{"city":"绵阳","district":"安县","id":"2061","province":"四川"},{"city":"绵阳","district":"梓潼","id":"2062","province":"四川"},{"city":"绵阳","district":"北川","id":"2063","province":"四川"},{"city":"绵阳","district":"平武","id":"2064","province":"四川"},{"city":"绵阳","district":"江油","id":"2065","province":"四川"},{"city":"南充","district":"南充","id":"2066","province":"四川"},{"city":"南充","district":"南部","id":"2067","province":"四川"},{"city":"南充","district":"营山","id":"2068","province":"四川"},{"city":"南充","district":"蓬安","id":"2069","province":"四川"},{"city":"南充","district":"仪陇","id":"2070","province":"四川"},{"city":"南充","district":"西充","id":"2071","province":"四川"},{"city":"南充","district":"阆中","id":"2072","province":"四川"},{"city":"达州","district":"达州","id":"2073","province":"四川"},{"city":"达州","district":"宣汉","id":"2074","province":"四川"},{"city":"达州","district":"开江","id":"2075","province":"四川"},{"city":"达州","district":"大竹","id":"2076","province":"四川"},{"city":"达州","district":"渠县","id":"2077","province":"四川"},{"city":"达州","district":"万源","id":"2078","province":"四川"},{"city":"达州","district":"通川","id":"2079","province":"四川"},{"city":"达州","district":"达县","id":"2080","province":"四川"},{"city":"遂宁","district":"遂宁","id":"2081","province":"四川"},{"city":"遂宁","district":"蓬溪","id":"2082","province":"四川"},{"city":"遂宁","district":"射洪","id":"2083","province":"四川"},{"city":"广安","district":"广安","id":"2084","province":"四川"},{"city":"广安","district":"岳池","id":"2085","province":"四川"},{"city":"广安","district":"武胜","id":"2086","province":"四川"},{"city":"广安","district":"邻水","id":"2087","province":"四川"},{"city":"广安","district":"华蓥","id":"2088","province":"四川"},{"city":"巴中","district":"巴中","id":"2089","province":"四川"},{"city":"巴中","district":"通江","id":"2090","province":"四川"},{"city":"巴中","district":"南江","id":"2091","province":"四川"},{"city":"巴中","district":"平昌","id":"2092","province":"四川"},{"city":"泸州","district":"泸州","id":"2093","province":"四川"},{"city":"泸州","district":"泸县","id":"2094","province":"四川"},{"city":"泸州","district":"合江","id":"2095","province":"四川"},{"city":"泸州","district":"叙永","id":"2096","province":"四川"},{"city":"泸州","district":"古蔺","id":"2097","province":"四川"},{"city":"泸州","district":"纳溪","id":"2098","province":"四川"},{"city":"宜宾","district":"宜宾","id":"2099","province":"四川"},{"city":"宜宾","district":"宜宾县","id":"2100","province":"四川"},{"city":"宜宾","district":"南溪","id":"2101","province":"四川"},{"city":"宜宾","district":"江安","id":"2102","province":"四川"},{"city":"宜宾","district":"长宁","id":"2103","province":"四川"},{"city":"宜宾","district":"高县","id":"2104","province":"四川"},{"city":"宜宾","district":"珙县","id":"2105","province":"四川"},{"city":"宜宾","district":"筠连","id":"2106","province":"四川"},{"city":"宜宾","district":"兴文","id":"2107","province":"四川"},{"city":"宜宾","district":"屏山","id":"2108","province":"四川"},{"city":"内江","district":"内江","id":"2109","province":"四川"},{"city":"内江","district":"东兴","id":"2110","province":"四川"},{"city":"内江","district":"威远","id":"2111","province":"四川"},{"city":"内江","district":"资中","id":"2112","province":"四川"},{"city":"内江","district":"隆昌","id":"2113","province":"四川"},{"city":"资阳","district":"资阳","id":"2114","province":"四川"},{"city":"资阳","district":"安岳","id":"2115","province":"四川"},{"city":"资阳","district":"乐至","id":"2116","province":"四川"},{"city":"资阳","district":"简阳","id":"2117","province":"四川"},{"city":"乐山","district":"乐山","id":"2118","province":"四川"},{"city":"乐山","district":"犍为","id":"2119","province":"四川"},{"city":"乐山","district":"井研","id":"2120","province":"四川"},{"city":"乐山","district":"夹江","id":"2121","province":"四川"},{"city":"乐山","district":"沐川","id":"2122","province":"四川"},{"city":"乐山","district":"峨边","id":"2123","province":"四川"},{"city":"乐山","district":"马边","id":"2124","province":"四川"},{"city":"乐山","district":"峨眉","id":"2125","province":"四川"},{"city":"乐山","district":"峨眉山","id":"2126","province":"四川"},{"city":"眉山","district":"眉山","id":"2127","province":"四川"},{"city":"眉山","district":"仁寿","id":"2128","province":"四川"},{"city":"眉山","district":"彭山","id":"2129","province":"四川"},{"city":"眉山","district":"洪雅","id":"2130","province":"四川"},{"city":"眉山","district":"丹棱","id":"2131","province":"四川"},{"city":"眉山","district":"青神","id":"2132","province":"四川"},{"city":"凉山","district":"凉山","id":"2133","province":"四川"},{"city":"凉山","district":"木里","id":"2134","province":"四川"},{"city":"凉山","district":"盐源","id":"2135","province":"四川"},{"city":"凉山","district":"德昌","id":"2136","province":"四川"},{"city":"凉山","district":"会理","id":"2137","province":"四川"},{"city":"凉山","district":"会东","id":"2138","province":"四川"},{"city":"凉山","district":"宁南","id":"2139","province":"四川"},{"city":"凉山","district":"普格","id":"2140","province":"四川"},{"city":"凉山","district":"西昌","id":"2141","province":"四川"},{"city":"凉山","district":"金阳","id":"2142","province":"四川"},{"city":"凉山","district":"昭觉","id":"2143","province":"四川"},{"city":"凉山","district":"喜德","id":"2144","province":"四川"},{"city":"凉山","district":"冕宁","id":"2145","province":"四川"},{"city":"凉山","district":"越西","id":"2146","province":"四川"},{"city":"凉山","district":"甘洛","id":"2147","province":"四川"},{"city":"凉山","district":"雷波","id":"2148","province":"四川"},{"city":"凉山","district":"美姑","id":"2149","province":"四川"},{"city":"凉山","district":"布拖","id":"2150","province":"四川"},{"city":"雅安","district":"雅安","id":"2151","province":"四川"},{"city":"雅安","district":"名山","id":"2152","province":"四川"},{"city":"雅安","district":"荥经","id":"2153","province":"四川"},{"city":"雅安","district":"汉源","id":"2154","province":"四川"},{"city":"雅安","district":"石棉","id":"2155","province":"四川"},{"city":"雅安","district":"天全","id":"2156","province":"四川"},{"city":"雅安","district":"芦山","id":"2157","province":"四川"},{"city":"雅安","district":"宝兴","id":"2158","province":"四川"},{"city":"甘孜","district":"甘孜","id":"2159","province":"四川"},{"city":"甘孜","district":"康定","id":"2160","province":"四川"},{"city":"甘孜","district":"泸定","id":"2161","province":"四川"},{"city":"甘孜","district":"丹巴","id":"2162","province":"四川"},{"city":"甘孜","district":"九龙","id":"2163","province":"四川"},{"city":"甘孜","district":"雅江","id":"2164","province":"四川"},{"city":"甘孜","district":"道孚","id":"2165","province":"四川"},{"city":"甘孜","district":"炉霍","id":"2166","province":"四川"},{"city":"甘孜","district":"新龙","id":"2167","province":"四川"},{"city":"甘孜","district":"德格","id":"2168","province":"四川"},{"city":"甘孜","district":"白玉","id":"2169","province":"四川"},{"city":"甘孜","district":"石渠","id":"2170","province":"四川"},{"city":"甘孜","district":"色达","id":"2171","province":"四川"},{"city":"甘孜","district":"理塘","id":"2172","province":"四川"},{"city":"甘孜","district":"巴塘","id":"2173","province":"四川"},{"city":"甘孜","district":"乡城","id":"2174","province":"四川"},{"city":"甘孜","district":"稻城","id":"2175","province":"四川"},{"city":"甘孜","district":"得荣","id":"2176","province":"四川"},{"city":"阿坝","district":"阿坝","id":"2177","province":"四川"},{"city":"阿坝","district":"汶川","id":"2178","province":"四川"},{"city":"阿坝","district":"理县","id":"2179","province":"四川"},{"city":"阿坝","district":"茂县","id":"2180","province":"四川"},{"city":"阿坝","district":"松潘","id":"2181","province":"四川"},{"city":"阿坝","district":"九寨沟","id":"2182","province":"四川"},{"city":"阿坝","district":"金川","id":"2183","province":"四川"},{"city":"阿坝","district":"小金","id":"2184","province":"四川"},{"city":"阿坝","district":"黑水","id":"2185","province":"四川"},{"city":"阿坝","district":"马尔康","id":"2186","province":"四川"},{"city":"阿坝","district":"壤塘","id":"2187","province":"四川"},{"city":"阿坝","district":"若尔盖","id":"2188","province":"四川"},{"city":"阿坝","district":"红原","id":"2189","province":"四川"},{"city":"阿坝","district":"南坪","id":"2190","province":"四川"},{"city":"德阳","district":"德阳","id":"2191","province":"四川"},{"city":"德阳","district":"中江","id":"2192","province":"四川"},{"city":"德阳","district":"广汉","id":"2193","province":"四川"},{"city":"德阳","district":"什邡","id":"2194","province":"四川"},{"city":"德阳","district":"绵竹","id":"2195","province":"四川"},{"city":"德阳","district":"罗江","id":"2196","province":"四川"},{"city":"广元","district":"广元","id":"2197","province":"四川"},{"city":"广元","district":"旺苍","id":"2198","province":"四川"},{"city":"广元","district":"青川","id":"2199","province":"四川"},{"city":"广元","district":"剑阁","id":"2200","province":"四川"},{"city":"广元","district":"苍溪","id":"2201","province":"四川"}]
     */

    private int error_code;
    private IAllApiBean iAllApi;
    private List<ResultBean> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public IAllApiBean getIAllApi() {
        return iAllApi;
    }

    public void setIAllApi(IAllApiBean iAllApi) {
        this.iAllApi = iAllApi;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class IAllApiBean {
    }

    public static class ResultBean {
        /**
         * city : 成都
         * district : 成都
         * id : 2037
         * province : 四川
         */

        private String city;
        private String district;
        private String id;
        private String province;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }
    }
}
