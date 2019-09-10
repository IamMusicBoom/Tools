package com.wma.tools.model.news;

import com.wma.tools.model.IAllApi;
import com.wma.wmalib.base.BaseModel;
import com.wma.wmalib.callback.HttpCallBack;
import com.wma.wmalib.http.HttpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 王明骜 on 19-8-9 上午10:41.
 */
public class NewsModel extends BaseModel<IAllApi> {
    public IAllApi iAllApi;

    @Override
    public IAllApi getModel(Class<IAllApi> classes) {
        if (iAllApi == null) {
            synchronized (classes) {
                iAllApi = HttpUtils.retrofit.create(classes);
            }
        }
        return iAllApi;
    }

    public NewsModel() {
        getModel(IAllApi.class);
    }

    /**
     * reason : 成功的返回
     * result : {"stat":"1","data":[{"uniquekey":"c32b776ba542766fe6808dcf6382f826","title":"扛走衣柜和伤害\u2014\u2014王者荣耀里的魔术师","date":"2019-08-09 08:17","category":"头条","author_name":"游戏接班人","url":"http://mini.eastday.com/mobile/190809081739691.html","thumbnail_pic_s":"http://05imgmini.eastday.com/mobile/20190809/20190809081739_9a640c3733e54b00685e5c8e20206bd8_1_mwpm_03200403.jpg"},{"uniquekey":"408e51810e287aa865e064aed1870792","title":"今天有个小妹妹问我，为什么有个男生撩着撩着，最近突然就不理她","date":"2019-08-09 08:16","category":"头条","author_name":"天天笑口常开","url":"http://mini.eastday.com/mobile/190809081612337.html","thumbnail_pic_s":"http://06imgmini.eastday.com/mobile/20190809/2019080908_d4826ac9634840cfb88e88774389f420_5294_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06imgmini.eastday.com/mobile/20190809/2019080908_7d70064570134db9b069b736af3fcc19_0811_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06imgmini.eastday.com/mobile/20190809/2019080908_79f7fe6fcf7d4c2e94bff1c72113029b_2471_mwpm_03200403.jpg"},{"uniquekey":"b35a073cda6ff7bbd47287f755f73cae","title":"这两位都是童年女神，如今都出演《知否》，一个大好人一个大反派","date":"2019-08-09 08:09","category":"头条","author_name":"小道消息说娱乐","url":"http://mini.eastday.com/mobile/190809080922169.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20190809/2019080908_8b3776605ff04bad98a21b5a0d98d7c6_5553_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday.com/mobile/20190809/2019080908_7ad720cc83fd428cbde7de2f8ed9d564_2530_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday.com/mobile/20190809/2019080908_1163740434d54d5791af466f359ec7b0_3652_cover_mwpm_03200403.jpg"},{"uniquekey":"9ced144f6b9c8792a24ac37bff872a96","title":"《问政时刻》聚焦营商环境 高陵区蓝田县接受质询","date":"2019-08-09 08:09","category":"头条","author_name":"西安日报","url":"http://mini.eastday.com/mobile/190809080912248.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20190809/20190809080912_1d8ebfaaa062d2f98bccbd591abc064b_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday.com/mobile/20190809/20190809080912_461cc0c86dd97334e04fd16a1434da70_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday.com/mobile/20190809/20190809080912_6c111baa4d3b0f12cc60b1b0c3058112_2_mwpm_03200403.jpg"},{"uniquekey":"f97146beeccf1582dd3c95746ae910b8","title":"Uber高管：将在拼车和共乘业务方面大力创新 投入巨额资金","date":"2019-08-09 08:07","category":"头条","author_name":"铅笔道","url":"http://mini.eastday.com/mobile/190809080727936.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20190809/20190809080727_5f3f027e46764c475ca15bc9508323a1_1_mwpm_03200403.jpg"},{"uniquekey":"4033e373589931f226df4666601b58cd","title":"1985年的王丽坤、李晟、李金铭、童瑶，都不及进军好莱坞的她","date":"2019-08-09 08:05","category":"头条","author_name":"雯子娱乐","url":"http://mini.eastday.com/mobile/190809080558710.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20190809/2019080814_20d40331b5ca415babdbab826e1083d8_0354_wmk_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09imgmini.eastday.com/mobile/20190809/2019080814_5dd4970faf2e4646835d7f23088c906a_9246_wmk_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09imgmini.eastday.com/mobile/20190809/2019080814_32013eec0a73429d9d2348164176f90e_7824_wmk_mwpm_03200403.jpg"},{"uniquekey":"84c93f12dc3e374d9618bcf4e89afb0c","title":"美女街拍：魅力十足的紧身裙小姐姐，身材曼妙，展现优美曲线！","date":"2019-08-09 08:05","category":"头条","author_name":"首席时尚官儿","url":"http://mini.eastday.com/mobile/190809080532308.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20190809/2019080908_d7cd32b6a91c4a97bef376475f7d7b0b_6246_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday.com/mobile/20190809/2019080908_07f1578d63a949c4b89730ab95c57361_1997_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday.com/mobile/20190809/2019080908_8b40323f580740f193a51678b6f73b8c_6711_cover_mwpm_03200403.jpg"},{"uniquekey":"a52e258dcdfb72fcaaf53f812aaef92b","title":"滁州市沙扣年：文化站长扎根基层数十载 26年举办\u201c农民春晚\u201d丰富乡村文化生活","date":"2019-08-09 08:03","category":"头条","author_name":"中安在线","url":"http://mini.eastday.com/mobile/190809080358466.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20190809/20190809080358_123bc01521dbdc60cd120334a19613c8_1_mwpm_03200403.jpg"},{"uniquekey":"2b8ce03e19a58fb2a945b66854bf4c5d","title":"谈话间，老人不止一次对我说：我的事情没有许可，是不能公开讲的","date":"2019-08-09 08:03","category":"头条","author_name":"东方头条 这才是战争","url":"http://mini.eastday.com/mobile/190809080328467.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20190809/2019080814_318d2f671cf5413ebbcce42f32a5b400_0889_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday.com/mobile/20190809/2019080814_99e1d59d9f814a68a761d76d9b1a7814_2023_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday.com/mobile/20190809/2019080814_363068cce5ee450e84f47d5025beef29_4813_cover_mwpm_03200403.jpg"},{"uniquekey":"0068c82def00e1d8ec494bf4dedfa4ff","title":"运动之后体重反而上升了？恭喜你，减肥成功","date":"2019-08-09 08:02","category":"头条","author_name":"颐鹿健康","url":"http://mini.eastday.com/mobile/190809080253583.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20190809/20190809080253_732752c3b3779c0f42eacf0abf81e469_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday.com/mobile/20190809/20190809080253_732752c3b3779c0f42eacf0abf81e469_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday.com/mobile/20190809/20190809080253_732752c3b3779c0f42eacf0abf81e469_1_mwpm_03200403.jpg"},{"uniquekey":"20e32e2105fca36bc12d79aaea2e12d1","title":"颜值颇高，善解人意的三生肖女，事业一顺百顺，婚姻幸福和睦","date":"2019-08-09 08:01","category":"头条","author_name":"么么八卦","url":"http://mini.eastday.com/mobile/190809080110999.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20190809/2019080908_eafaf29e5c584167b9b7c1cacb4895a4_0219_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20190809/2019080908_a02a1ff02934462c954e2e9af4c0f6df_5411_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20190809/2019080908_a9852eef88aa45a9921abe5aef3baa6a_0297_mwpm_03200403.jpg"},{"uniquekey":"13386cfbd7772609df7dbe62c8144c8e","title":"你知道吗？在《红海行动》之前，张译和海清还合作过这些作品","date":"2019-08-09 07:58","category":"头条","author_name":"斌哥娱乐","url":"http://mini.eastday.com/mobile/190809075844271.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20190809/2019080907_cf00b0c337b948198ff20f6427ecf6b3_2522_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09imgmini.eastday.com/mobile/20190809/2019080907_6f6ba96cc7804ced8ee33ad4f62b06cf_7144_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09imgmini.eastday.com/mobile/20190809/2019080907_3ed6e3516cad4ccbb3e836306c09cd3c_5137_cover_mwpm_03200403.jpg"},{"uniquekey":"72b9db3132d91190145140754d6ce81b","title":"汽车召回特刊：广汽本田和雷克萨斯大面积召回问题车型","date":"2019-08-09 07:58","category":"头条","author_name":"懂车之道","url":"http://mini.eastday.com/mobile/190809075820562.html","thumbnail_pic_s":"http://06imgmini.eastday.com/mobile/20190809/2019080907_6c3dca3f7b2f401d80d1e61cc86854d6_2476_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06imgmini.eastday.com/mobile/20190809/2019080907_a27129f710ed481f90c4f02709ed5b6e_0487_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06imgmini.eastday.com/mobile/20190809/2019080907_406111661f4646e3968ceb95e08c41f1_9659_cover_mwpm_03200403.jpg"},{"uniquekey":"1f5cba697d2e709a06d51ba7ac61ea31","title":"张柏芝长子近照曝光如迷你霆锋，12岁的他冷漠拒绝妈妈牵手","date":"2019-08-09 07:57","category":"头条","author_name":"八卦宝宝","url":"http://mini.eastday.com/mobile/190809075723564.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20190809/2019080907_1c846b85acc54c738458c93aec8b6f83_4022_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday.com/mobile/20190809/2019080907_d6d5d32dd90f4787bc8bf63cd1beadd3_8414_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday.com/mobile/20190809/2019080907_cbcdd0cf43484c04b06a5511bca448f1_7537_mwpm_03200403.jpg"},{"uniquekey":"7c110c86133672aeb8ba9b063f2add88","title":"久保建英的皇马卡斯蒂亚身份","date":"2019-08-09 07:57","category":"头条","author_name":"足球新闻翻译官","url":"http://mini.eastday.com/mobile/190809075706520.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20190809/20190809075706_b6270daec8e3483c488c15f0629c6c6d_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07imgmini.eastday.com/mobile/20190809/20190809075706_b6270daec8e3483c488c15f0629c6c6d_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07imgmini.eastday.com/mobile/20190809/20190809075706_b6270daec8e3483c488c15f0629c6c6d_4_mwpm_03200403.jpg"},{"uniquekey":"a359d49be468f4eb0c9c52fe7d41abe5","title":"圣安德烈旗飘向叙利亚！俄罗斯海军在叙利亚战场 作用实实在在","date":"2019-08-09 07:54","category":"头条","author_name":"东方头条 123军情观察室","url":"http://mini.eastday.com/mobile/190809075454664.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20190809/2019080907_d1cfbfc173804495beda86a2a9a3c971_4564_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09imgmini.eastday.com/mobile/20190809/2019080907_6ec63899563b4e3e8c76e210b0e9e681_2018_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09imgmini.eastday.com/mobile/20190809/2019080907_7077492efa9c4833b563c08a0e828ada_7895_mwpm_03200403.jpg"},{"uniquekey":"1fe9a0285322cc5fc6c203c52095035f","title":"紫衣古装对决，关晓彤鞠婧祎赵丽颖唐嫣李沁，谁最勾魂最美艳？","date":"2019-08-09 07:53","category":"头条","author_name":"晓晨说娱乐","url":"http://mini.eastday.com/mobile/190809075310734.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20190809/2019080715_024f2cf5758d4570afaaa8f79c9d8172_5368_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday.com/mobile/20190809/2019080715_39df8cafabbc46e59f41761bbb93dc99_4062_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday.com/mobile/20190809/2019080715_68c88ed7c35d451896499aab7ed675b8_9291_cover_mwpm_03200403.jpg"},{"uniquekey":"48e3e275aa3fa4f57c36ec81733d3329","title":"中国古代有一复姓，极为罕见，在史书上只出现过一次，如今已绝迹","date":"2019-08-09 07:50","category":"头条","author_name":"无风起念","url":"http://mini.eastday.com/mobile/190809075046038.html","thumbnail_pic_s":"http://05imgmini.eastday.com/mobile/20190809/2019080907_d480ec0208764b46ab83a57ce72d4ebd_3249_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05imgmini.eastday.com/mobile/20190809/2019080907_77422bd9da7342969719bcb762954df9_4779_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05imgmini.eastday.com/mobile/20190809/2019080907_97eb0038a6214073a43dedf74cfa5a64_0977_mwpm_03200403.jpg"},{"uniquekey":"d47e659bc87878e3ce8a8ab507d5a8dd","title":"世界上最小的潜水艇，仅1毫米大小，可以在血管中穿梭！","date":"2019-08-09 07:50","category":"头条","author_name":"环球轶闻","url":"http://mini.eastday.com/mobile/190809075012245.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20190809/2019080907_129e4d44a0504a20bd9bc17ffed10383_3348_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday.com/mobile/20190809/2019080907_9bdb739155f04284bb3caa179a9a2127_6009_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday.com/mobile/20190809/2019080907_3ceee23b342a4b9cb27497f6dbe1b17e_9045_mwpm_03200403.jpg"},{"uniquekey":"275e385945c1289551ecd9a8150e222e","title":"出口全球100多个国家和地区 中国轨道车辆凭啥在海外生了根？","date":"2019-08-09 07:50","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/190809075000956.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20190809/20190809075000_7b66750e4b1d968b08b3d473f1aec32c_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20190809/20190809075000_7b66750e4b1d968b08b3d473f1aec32c_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20190809/20190809075000_7b66750e4b1d968b08b3d473f1aec32c_3_mwpm_03200403.jpg"},{"uniquekey":"8d39ea842907f1e545cfa14ee67f30f1","title":"伏天养生很关键，多吃3种食物，护肝排毒，呵护肌肤， 越吃越瘦","date":"2019-08-09 07:46","category":"头条","author_name":"尝遍天下鲜","url":"http://mini.eastday.com/mobile/190809074604738.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20190809/2019080901_324a648f5fb64bb09383168e9d6258bb_9042_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20190809/2019080901_1fa788d5fe164fad8dc55b88686bed83_3327_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20190809/2019080901_84763cc6dfaa4ef8afea7d2fd3077121_6909_cover_mwpm_03200403.jpg"},{"uniquekey":"4d32490619f59727bd041e25ca007222","title":"被动变主动,礼让之风需持续引导 \u201c大拇指行动\u201d引领新风尚","date":"2019-08-09 07:45","category":"头条","author_name":"齐鲁晚报","url":"http://mini.eastday.com/mobile/190809074514270.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20190809/20190809074514_63d97297163ecad7541d8db5c5949046_1_mwpm_03200403.jpg"},{"uniquekey":"76e2ef423a6cd08621846a7012d4be58","title":"农村田里一种\u201c竹叶\u201d，过去被拿去喂猪，现在卖高价","date":"2019-08-09 07:44","category":"头条","author_name":"趣闻小实验","url":"http://mini.eastday.com/mobile/190809074446528.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20190809/2019080907_c9150f4c2db346edb13f926f6cdf0d70_2458_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20190809/2019080907_46430e966ac24555a4d98cdecdea937e_2881_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20190809/2019080907_a41a5c2406924ebfb59d03866fe846de_2277_mwpm_03200403.jpg"},{"uniquekey":"b3a0bcb167d1d3c3d88c417e6cf7cd64","title":"天马科技拟至多1.2亿元回购股份用于股权激励","date":"2019-08-09 07:44","category":"头条","author_name":"第一投资","url":"http://mini.eastday.com/mobile/190809074439858.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20190809/20190809074439_0ec0a77825a276b00c5b52240b1ca3c3_1_mwpm_03200403.jpg"},{"uniquekey":"027e531357483bba5b4545dc7e58b41e","title":"街拍：学生党的外套，洋气才是王道","date":"2019-08-09 07:44","category":"头条","author_name":"嗅怪猎奇","url":"http://mini.eastday.com/mobile/190809074425969.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20190809/2019080907_69b45ff8778d4e23baf82f79f2a73eb3_8323_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday.com/mobile/20190809/2019080907_835b931d0c52446d9f44b0b618119d9d_3937_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday.com/mobile/20190809/2019080907_c52d4076446e4271ad65010f333084bd_3474_mwpm_03200403.jpg"},{"uniquekey":"fcd0767c76560b9ed32a17f228eb28b1","title":"原创 在莫斯科郊外的作家村，找寻《日瓦戈医生》的痕迹","date":"2019-08-09 07:44","category":"头条","author_name":"中国新闻周刊","url":"http://mini.eastday.com/mobile/190809074419458.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20190809/20190809074514_a46579b92b8fb9502a53b30a75e51d0d_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20190809/20190809074514_a46579b92b8fb9502a53b30a75e51d0d_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20190809/20190809074514_a46579b92b8fb9502a53b30a75e51d0d_3_mwpm_03200403.jpg"},{"uniquekey":"97afab26861249ba418b0964b766cf13","title":"实行管控后日本首次批准向韩国出口半导体材料","date":"2019-08-09 07:44","category":"头条","author_name":"经济参考报","url":"http://mini.eastday.com/mobile/190809074404730.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20190809/20190809074404_e8a3cce62c68f4ea9cebfce84df0b21c_1_mwpm_03200403.jpg"},{"uniquekey":"496f81bbeef76a81bd07383b4e071fe0","title":"西班牙人完胜卢塞恩，武磊零射门仍未找回感觉","date":"2019-08-09 07:43","category":"头条","author_name":"新京报","url":"http://mini.eastday.com/mobile/190809074343944.html","thumbnail_pic_s":"http://05imgmini.eastday.com/mobile/20190809/20190809074343_2b60dd97507e7b5d5c46042e70fcaebb_1_mwpm_03200403.jpg"},{"uniquekey":"ca2ca919364ae4d1e95e953040b63b90","title":"对冲基金大力做空科技股 两天增加17亿美元卖空头寸","date":"2019-08-09 07:43","category":"头条","author_name":"大众网","url":"http://mini.eastday.com/mobile/190809074306256.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20190809/20190809074306_7063952229a4b8ca53d68c489242d34c_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday.com/mobile/20190809/20190809074306_7063952229a4b8ca53d68c489242d34c_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday.com/mobile/20190809/20190809074306_7063952229a4b8ca53d68c489242d34c_3_mwpm_03200403.jpg"},{"uniquekey":"fccaa0d00390cfc44b13328d9e70e8bb","title":"山区一\u201c野花\u201d价格珍贵，果子像石榴，有着很高的营养价值","date":"2019-08-09 07:42","category":"头条","author_name":"农事一箩筐","url":"http://mini.eastday.com/mobile/190809074250891.html","thumbnail_pic_s":"http://03imgmini.eastday.com/mobile/20190809/2019080907_7f3bfbc540ef478bb0f028fb2ca351c6_3100_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday.com/mobile/20190809/2019080907_9d788575cb3540508897abc5284efb31_0295_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday.com/mobile/20190809/2019080907_331a8e043f5646239f75366dd4a2fa5c_4689_mwpm_03200403.jpg"}]}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * stat : 1
         * data : [{"uniquekey":"c32b776ba542766fe6808dcf6382f826","title":"扛走衣柜和伤害\u2014\u2014王者荣耀里的魔术师","date":"2019-08-09 08:17","category":"头条","author_name":"游戏接班人","url":"http://mini.eastday.com/mobile/190809081739691.html","thumbnail_pic_s":"http://05imgmini.eastday.com/mobile/20190809/20190809081739_9a640c3733e54b00685e5c8e20206bd8_1_mwpm_03200403.jpg"},{"uniquekey":"408e51810e287aa865e064aed1870792","title":"今天有个小妹妹问我，为什么有个男生撩着撩着，最近突然就不理她","date":"2019-08-09 08:16","category":"头条","author_name":"天天笑口常开","url":"http://mini.eastday.com/mobile/190809081612337.html","thumbnail_pic_s":"http://06imgmini.eastday.com/mobile/20190809/2019080908_d4826ac9634840cfb88e88774389f420_5294_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06imgmini.eastday.com/mobile/20190809/2019080908_7d70064570134db9b069b736af3fcc19_0811_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06imgmini.eastday.com/mobile/20190809/2019080908_79f7fe6fcf7d4c2e94bff1c72113029b_2471_mwpm_03200403.jpg"},{"uniquekey":"b35a073cda6ff7bbd47287f755f73cae","title":"这两位都是童年女神，如今都出演《知否》，一个大好人一个大反派","date":"2019-08-09 08:09","category":"头条","author_name":"小道消息说娱乐","url":"http://mini.eastday.com/mobile/190809080922169.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20190809/2019080908_8b3776605ff04bad98a21b5a0d98d7c6_5553_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday.com/mobile/20190809/2019080908_7ad720cc83fd428cbde7de2f8ed9d564_2530_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday.com/mobile/20190809/2019080908_1163740434d54d5791af466f359ec7b0_3652_cover_mwpm_03200403.jpg"},{"uniquekey":"9ced144f6b9c8792a24ac37bff872a96","title":"《问政时刻》聚焦营商环境 高陵区蓝田县接受质询","date":"2019-08-09 08:09","category":"头条","author_name":"西安日报","url":"http://mini.eastday.com/mobile/190809080912248.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20190809/20190809080912_1d8ebfaaa062d2f98bccbd591abc064b_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday.com/mobile/20190809/20190809080912_461cc0c86dd97334e04fd16a1434da70_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday.com/mobile/20190809/20190809080912_6c111baa4d3b0f12cc60b1b0c3058112_2_mwpm_03200403.jpg"},{"uniquekey":"f97146beeccf1582dd3c95746ae910b8","title":"Uber高管：将在拼车和共乘业务方面大力创新 投入巨额资金","date":"2019-08-09 08:07","category":"头条","author_name":"铅笔道","url":"http://mini.eastday.com/mobile/190809080727936.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20190809/20190809080727_5f3f027e46764c475ca15bc9508323a1_1_mwpm_03200403.jpg"},{"uniquekey":"4033e373589931f226df4666601b58cd","title":"1985年的王丽坤、李晟、李金铭、童瑶，都不及进军好莱坞的她","date":"2019-08-09 08:05","category":"头条","author_name":"雯子娱乐","url":"http://mini.eastday.com/mobile/190809080558710.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20190809/2019080814_20d40331b5ca415babdbab826e1083d8_0354_wmk_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09imgmini.eastday.com/mobile/20190809/2019080814_5dd4970faf2e4646835d7f23088c906a_9246_wmk_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09imgmini.eastday.com/mobile/20190809/2019080814_32013eec0a73429d9d2348164176f90e_7824_wmk_mwpm_03200403.jpg"},{"uniquekey":"84c93f12dc3e374d9618bcf4e89afb0c","title":"美女街拍：魅力十足的紧身裙小姐姐，身材曼妙，展现优美曲线！","date":"2019-08-09 08:05","category":"头条","author_name":"首席时尚官儿","url":"http://mini.eastday.com/mobile/190809080532308.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20190809/2019080908_d7cd32b6a91c4a97bef376475f7d7b0b_6246_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday.com/mobile/20190809/2019080908_07f1578d63a949c4b89730ab95c57361_1997_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday.com/mobile/20190809/2019080908_8b40323f580740f193a51678b6f73b8c_6711_cover_mwpm_03200403.jpg"},{"uniquekey":"a52e258dcdfb72fcaaf53f812aaef92b","title":"滁州市沙扣年：文化站长扎根基层数十载 26年举办\u201c农民春晚\u201d丰富乡村文化生活","date":"2019-08-09 08:03","category":"头条","author_name":"中安在线","url":"http://mini.eastday.com/mobile/190809080358466.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20190809/20190809080358_123bc01521dbdc60cd120334a19613c8_1_mwpm_03200403.jpg"},{"uniquekey":"2b8ce03e19a58fb2a945b66854bf4c5d","title":"谈话间，老人不止一次对我说：我的事情没有许可，是不能公开讲的","date":"2019-08-09 08:03","category":"头条","author_name":"东方头条 这才是战争","url":"http://mini.eastday.com/mobile/190809080328467.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20190809/2019080814_318d2f671cf5413ebbcce42f32a5b400_0889_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday.com/mobile/20190809/2019080814_99e1d59d9f814a68a761d76d9b1a7814_2023_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday.com/mobile/20190809/2019080814_363068cce5ee450e84f47d5025beef29_4813_cover_mwpm_03200403.jpg"},{"uniquekey":"0068c82def00e1d8ec494bf4dedfa4ff","title":"运动之后体重反而上升了？恭喜你，减肥成功","date":"2019-08-09 08:02","category":"头条","author_name":"颐鹿健康","url":"http://mini.eastday.com/mobile/190809080253583.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20190809/20190809080253_732752c3b3779c0f42eacf0abf81e469_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday.com/mobile/20190809/20190809080253_732752c3b3779c0f42eacf0abf81e469_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday.com/mobile/20190809/20190809080253_732752c3b3779c0f42eacf0abf81e469_1_mwpm_03200403.jpg"},{"uniquekey":"20e32e2105fca36bc12d79aaea2e12d1","title":"颜值颇高，善解人意的三生肖女，事业一顺百顺，婚姻幸福和睦","date":"2019-08-09 08:01","category":"头条","author_name":"么么八卦","url":"http://mini.eastday.com/mobile/190809080110999.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20190809/2019080908_eafaf29e5c584167b9b7c1cacb4895a4_0219_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20190809/2019080908_a02a1ff02934462c954e2e9af4c0f6df_5411_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20190809/2019080908_a9852eef88aa45a9921abe5aef3baa6a_0297_mwpm_03200403.jpg"},{"uniquekey":"13386cfbd7772609df7dbe62c8144c8e","title":"你知道吗？在《红海行动》之前，张译和海清还合作过这些作品","date":"2019-08-09 07:58","category":"头条","author_name":"斌哥娱乐","url":"http://mini.eastday.com/mobile/190809075844271.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20190809/2019080907_cf00b0c337b948198ff20f6427ecf6b3_2522_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09imgmini.eastday.com/mobile/20190809/2019080907_6f6ba96cc7804ced8ee33ad4f62b06cf_7144_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09imgmini.eastday.com/mobile/20190809/2019080907_3ed6e3516cad4ccbb3e836306c09cd3c_5137_cover_mwpm_03200403.jpg"},{"uniquekey":"72b9db3132d91190145140754d6ce81b","title":"汽车召回特刊：广汽本田和雷克萨斯大面积召回问题车型","date":"2019-08-09 07:58","category":"头条","author_name":"懂车之道","url":"http://mini.eastday.com/mobile/190809075820562.html","thumbnail_pic_s":"http://06imgmini.eastday.com/mobile/20190809/2019080907_6c3dca3f7b2f401d80d1e61cc86854d6_2476_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06imgmini.eastday.com/mobile/20190809/2019080907_a27129f710ed481f90c4f02709ed5b6e_0487_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06imgmini.eastday.com/mobile/20190809/2019080907_406111661f4646e3968ceb95e08c41f1_9659_cover_mwpm_03200403.jpg"},{"uniquekey":"1f5cba697d2e709a06d51ba7ac61ea31","title":"张柏芝长子近照曝光如迷你霆锋，12岁的他冷漠拒绝妈妈牵手","date":"2019-08-09 07:57","category":"头条","author_name":"八卦宝宝","url":"http://mini.eastday.com/mobile/190809075723564.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20190809/2019080907_1c846b85acc54c738458c93aec8b6f83_4022_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday.com/mobile/20190809/2019080907_d6d5d32dd90f4787bc8bf63cd1beadd3_8414_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday.com/mobile/20190809/2019080907_cbcdd0cf43484c04b06a5511bca448f1_7537_mwpm_03200403.jpg"},{"uniquekey":"7c110c86133672aeb8ba9b063f2add88","title":"久保建英的皇马卡斯蒂亚身份","date":"2019-08-09 07:57","category":"头条","author_name":"足球新闻翻译官","url":"http://mini.eastday.com/mobile/190809075706520.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20190809/20190809075706_b6270daec8e3483c488c15f0629c6c6d_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07imgmini.eastday.com/mobile/20190809/20190809075706_b6270daec8e3483c488c15f0629c6c6d_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07imgmini.eastday.com/mobile/20190809/20190809075706_b6270daec8e3483c488c15f0629c6c6d_4_mwpm_03200403.jpg"},{"uniquekey":"a359d49be468f4eb0c9c52fe7d41abe5","title":"圣安德烈旗飘向叙利亚！俄罗斯海军在叙利亚战场 作用实实在在","date":"2019-08-09 07:54","category":"头条","author_name":"东方头条 123军情观察室","url":"http://mini.eastday.com/mobile/190809075454664.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20190809/2019080907_d1cfbfc173804495beda86a2a9a3c971_4564_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09imgmini.eastday.com/mobile/20190809/2019080907_6ec63899563b4e3e8c76e210b0e9e681_2018_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09imgmini.eastday.com/mobile/20190809/2019080907_7077492efa9c4833b563c08a0e828ada_7895_mwpm_03200403.jpg"},{"uniquekey":"1fe9a0285322cc5fc6c203c52095035f","title":"紫衣古装对决，关晓彤鞠婧祎赵丽颖唐嫣李沁，谁最勾魂最美艳？","date":"2019-08-09 07:53","category":"头条","author_name":"晓晨说娱乐","url":"http://mini.eastday.com/mobile/190809075310734.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20190809/2019080715_024f2cf5758d4570afaaa8f79c9d8172_5368_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday.com/mobile/20190809/2019080715_39df8cafabbc46e59f41761bbb93dc99_4062_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday.com/mobile/20190809/2019080715_68c88ed7c35d451896499aab7ed675b8_9291_cover_mwpm_03200403.jpg"},{"uniquekey":"48e3e275aa3fa4f57c36ec81733d3329","title":"中国古代有一复姓，极为罕见，在史书上只出现过一次，如今已绝迹","date":"2019-08-09 07:50","category":"头条","author_name":"无风起念","url":"http://mini.eastday.com/mobile/190809075046038.html","thumbnail_pic_s":"http://05imgmini.eastday.com/mobile/20190809/2019080907_d480ec0208764b46ab83a57ce72d4ebd_3249_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05imgmini.eastday.com/mobile/20190809/2019080907_77422bd9da7342969719bcb762954df9_4779_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05imgmini.eastday.com/mobile/20190809/2019080907_97eb0038a6214073a43dedf74cfa5a64_0977_mwpm_03200403.jpg"},{"uniquekey":"d47e659bc87878e3ce8a8ab507d5a8dd","title":"世界上最小的潜水艇，仅1毫米大小，可以在血管中穿梭！","date":"2019-08-09 07:50","category":"头条","author_name":"环球轶闻","url":"http://mini.eastday.com/mobile/190809075012245.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20190809/2019080907_129e4d44a0504a20bd9bc17ffed10383_3348_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday.com/mobile/20190809/2019080907_9bdb739155f04284bb3caa179a9a2127_6009_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday.com/mobile/20190809/2019080907_3ceee23b342a4b9cb27497f6dbe1b17e_9045_mwpm_03200403.jpg"},{"uniquekey":"275e385945c1289551ecd9a8150e222e","title":"出口全球100多个国家和地区 中国轨道车辆凭啥在海外生了根？","date":"2019-08-09 07:50","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/190809075000956.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20190809/20190809075000_7b66750e4b1d968b08b3d473f1aec32c_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20190809/20190809075000_7b66750e4b1d968b08b3d473f1aec32c_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20190809/20190809075000_7b66750e4b1d968b08b3d473f1aec32c_3_mwpm_03200403.jpg"},{"uniquekey":"8d39ea842907f1e545cfa14ee67f30f1","title":"伏天养生很关键，多吃3种食物，护肝排毒，呵护肌肤， 越吃越瘦","date":"2019-08-09 07:46","category":"头条","author_name":"尝遍天下鲜","url":"http://mini.eastday.com/mobile/190809074604738.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20190809/2019080901_324a648f5fb64bb09383168e9d6258bb_9042_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20190809/2019080901_1fa788d5fe164fad8dc55b88686bed83_3327_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20190809/2019080901_84763cc6dfaa4ef8afea7d2fd3077121_6909_cover_mwpm_03200403.jpg"},{"uniquekey":"4d32490619f59727bd041e25ca007222","title":"被动变主动,礼让之风需持续引导 \u201c大拇指行动\u201d引领新风尚","date":"2019-08-09 07:45","category":"头条","author_name":"齐鲁晚报","url":"http://mini.eastday.com/mobile/190809074514270.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20190809/20190809074514_63d97297163ecad7541d8db5c5949046_1_mwpm_03200403.jpg"},{"uniquekey":"76e2ef423a6cd08621846a7012d4be58","title":"农村田里一种\u201c竹叶\u201d，过去被拿去喂猪，现在卖高价","date":"2019-08-09 07:44","category":"头条","author_name":"趣闻小实验","url":"http://mini.eastday.com/mobile/190809074446528.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20190809/2019080907_c9150f4c2db346edb13f926f6cdf0d70_2458_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20190809/2019080907_46430e966ac24555a4d98cdecdea937e_2881_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20190809/2019080907_a41a5c2406924ebfb59d03866fe846de_2277_mwpm_03200403.jpg"},{"uniquekey":"b3a0bcb167d1d3c3d88c417e6cf7cd64","title":"天马科技拟至多1.2亿元回购股份用于股权激励","date":"2019-08-09 07:44","category":"头条","author_name":"第一投资","url":"http://mini.eastday.com/mobile/190809074439858.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20190809/20190809074439_0ec0a77825a276b00c5b52240b1ca3c3_1_mwpm_03200403.jpg"},{"uniquekey":"027e531357483bba5b4545dc7e58b41e","title":"街拍：学生党的外套，洋气才是王道","date":"2019-08-09 07:44","category":"头条","author_name":"嗅怪猎奇","url":"http://mini.eastday.com/mobile/190809074425969.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20190809/2019080907_69b45ff8778d4e23baf82f79f2a73eb3_8323_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday.com/mobile/20190809/2019080907_835b931d0c52446d9f44b0b618119d9d_3937_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday.com/mobile/20190809/2019080907_c52d4076446e4271ad65010f333084bd_3474_mwpm_03200403.jpg"},{"uniquekey":"fcd0767c76560b9ed32a17f228eb28b1","title":"原创 在莫斯科郊外的作家村，找寻《日瓦戈医生》的痕迹","date":"2019-08-09 07:44","category":"头条","author_name":"中国新闻周刊","url":"http://mini.eastday.com/mobile/190809074419458.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20190809/20190809074514_a46579b92b8fb9502a53b30a75e51d0d_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20190809/20190809074514_a46579b92b8fb9502a53b30a75e51d0d_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20190809/20190809074514_a46579b92b8fb9502a53b30a75e51d0d_3_mwpm_03200403.jpg"},{"uniquekey":"97afab26861249ba418b0964b766cf13","title":"实行管控后日本首次批准向韩国出口半导体材料","date":"2019-08-09 07:44","category":"头条","author_name":"经济参考报","url":"http://mini.eastday.com/mobile/190809074404730.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20190809/20190809074404_e8a3cce62c68f4ea9cebfce84df0b21c_1_mwpm_03200403.jpg"},{"uniquekey":"496f81bbeef76a81bd07383b4e071fe0","title":"西班牙人完胜卢塞恩，武磊零射门仍未找回感觉","date":"2019-08-09 07:43","category":"头条","author_name":"新京报","url":"http://mini.eastday.com/mobile/190809074343944.html","thumbnail_pic_s":"http://05imgmini.eastday.com/mobile/20190809/20190809074343_2b60dd97507e7b5d5c46042e70fcaebb_1_mwpm_03200403.jpg"},{"uniquekey":"ca2ca919364ae4d1e95e953040b63b90","title":"对冲基金大力做空科技股 两天增加17亿美元卖空头寸","date":"2019-08-09 07:43","category":"头条","author_name":"大众网","url":"http://mini.eastday.com/mobile/190809074306256.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20190809/20190809074306_7063952229a4b8ca53d68c489242d34c_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday.com/mobile/20190809/20190809074306_7063952229a4b8ca53d68c489242d34c_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday.com/mobile/20190809/20190809074306_7063952229a4b8ca53d68c489242d34c_3_mwpm_03200403.jpg"},{"uniquekey":"fccaa0d00390cfc44b13328d9e70e8bb","title":"山区一\u201c野花\u201d价格珍贵，果子像石榴，有着很高的营养价值","date":"2019-08-09 07:42","category":"头条","author_name":"农事一箩筐","url":"http://mini.eastday.com/mobile/190809074250891.html","thumbnail_pic_s":"http://03imgmini.eastday.com/mobile/20190809/2019080907_7f3bfbc540ef478bb0f028fb2ca351c6_3100_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday.com/mobile/20190809/2019080907_9d788575cb3540508897abc5284efb31_0295_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday.com/mobile/20190809/2019080907_331a8e043f5646239f75366dd4a2fa5c_4689_mwpm_03200403.jpg"}]
         */

        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * uniquekey : c32b776ba542766fe6808dcf6382f826
             * title : 扛走衣柜和伤害——王者荣耀里的魔术师
             * date : 2019-08-09 08:17
             * category : 头条
             * author_name : 游戏接班人
             * url : http://mini.eastday.com/mobile/190809081739691.html
             * thumbnail_pic_s : http://05imgmini.eastday.com/mobile/20190809/20190809081739_9a640c3733e54b00685e5c8e20206bd8_1_mwpm_03200403.jpg
             * thumbnail_pic_s02 : http://06imgmini.eastday.com/mobile/20190809/2019080908_7d70064570134db9b069b736af3fcc19_0811_mwpm_03200403.jpg
             * thumbnail_pic_s03 : http://06imgmini.eastday.com/mobile/20190809/2019080908_79f7fe6fcf7d4c2e94bff1c72113029b_2471_mwpm_03200403.jpg
             */

            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }
        }
    }

    public void getDatas(String type, final HttpCallBack<ResultBean> callBack) {
        HttpUtils.httpUtils.init(IAllApi.NEWS_HOST);
        Map<String, String> map = new HashMap<>();
        map.put("key", "ddf058f205abc13f4a0bd68661b41f55");
        Observable<NewsModel> news = iAllApi.getNews(type, map);
        news.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onBegin();
                    }

                    @Override
                    public void onNext(NewsModel newsModel) {
                        if (newsModel.getError_code() != 0) {
                            callBack.onFail(newsModel.getReason());
                        } else if (newsModel.getResult() == null) {
                            callBack.onFail("返回数据为空...");
                        } else if (newsModel.getResult().getData() == null) {
                            callBack.onFail("返回数据为空...");
                        } else if (newsModel.getResult().getData().size() <= 0) {
                            callBack.onFail("返回数据为空...");
                        } else {
                            callBack.onSuccess(newsModel.getResult());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        callBack.onComplete();
                    }
                });
    }
}
