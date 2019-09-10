package com.wma.tools.model.horoscope;

import com.wma.tools.model.IAllApi;
import com.wma.wmalib.base.BaseModel;

import java.util.List;

/**
 * Created by 王明骜 on 19-9-9 下午3:03.
 */
public abstract class DetailBaseModel extends BaseModel<IAllApi> {


    /**
     * reason : 返回成功
     * result : {"id":"e204a7a91594ce5b","zi":"且","py":"ju,qie","wubi":"egd","pinyin":"jū,qiě","bushou":"一","bihua":"5","jijie":["且","qiě","尚，还，表示进一层：既高且大。尚且。况且。","表示暂时：苟且偷安。姑且。","表示将要、将近：城且拔矣。年且九十。","一面这样，一面那样：且走且说。","表示经久：这双鞋且穿呢！","文言发语词，用在句首，与\u201c夫\u201d相似：且说。","姓。","","且","jū","文言助词，用在句末，与\u201c啊\u201d相似。","多的样子。","农历六月的别称。","敬慎的样子：\u201c有萋有且\u201d。","古同\u201c趄\u201d，趑趄。","","笔画数：5；","部首：一；","笔顺编号：25111"],"xiangjie":["且","ｊū","【助】","用在句末,相当于\u201c啊\u201d","狂童之狂也且。\u2014\u2014《诗·郑风·褰裳》","另见qiě","","且","ｑｉě","【代】","此,这;今〖this〗","匪且有且,匪今斯今。\u2014\u2014《诗·周颂·载芟》。毛传:\u201c且,此也。\u201d","","且","qiě","【副】","将近;几乎〖almost;nearly〗","年且九十。\u2014\u2014《列子·汤问》","上晚年多内宠,小王且二十人。\u2014\u2014《资治通鉴》","去后且三年。\u2014\u2014清·侯方域《壮悔堂文集》","将要〖begoingto;will;shall〗","会且归矣,无庶予子憎。\u2014\u2014《诗·齐风》","故天之且风,草木未动而鸟已翔矣。\u2014\u2014《淮南子》","且为之奈何。\u2014\u2014《史记·项羽本纪》","以为且噬己矣。\u2014\u2014唐·柳宗元《三戒》","火且尽。\u2014\u2014宋·王安石《游褒禅山记》","祸且及汝。\u2014\u2014清·魏禧《大铁椎传》","又如:且当(该当);且然(亦将如此)","暂且;姑且〖just;forthetimebeing〗","且往观乎?\u2014\u2014《诗·郑风》","且携所著。\u2014\u2014清·梁启超《谭嗣同传》","甚且心之所以清。\u2014\u2014清·刘开《问说》","又如:你且等一下;这事且放一下;且可(犹暂且);且休(暂且休整);且自(暂且;只管);且住(暂止);且暂(犹暂且);且权(暂且;姑且);且则(姑且);且复(姑且再)","用来加强语气,表示某事物的极端的、假设的或不可能有的情况或事例〖even〗。如:死且不怕,况困难乎","[方言]∶表示需要或可以延续很长时间〖foralongtime〗。如:这笔且用呢;他且来不了呢","且","qiě","【连】","表示并列关系,相当于\u201c又\u201d、\u201c而且\u201d〖and〗","君子有酒,旨且多。\u2014\u2014《诗·小雅》","行牧且荛。\u2014\u2014唐·柳宗元《童区寄传》","连拜且泣。\u2014\u2014宋·王谠《唐语林·雅量》","香且甘者。\u2014\u2014清·周容《春酒堂遗书·芋老人传》","贼能且众。\u2014\u2014清·魏禧《大铁椎传》","又如:贫且贱;横且直;且并(并且);且是(而且)","又\u2026又。连用以表示两件事同时并进〖both\u2026and\u2026〗。如:既高且大;且战且退","表示选择关系,相当于\u201c抑或\u201d、\u201c或者\u201d〖or〗","王以天下为尊秦乎?且尊齐乎\u201d\u2014\u2014《战国策》","是且非邪。\u2014\u2014唐·韩愈《朱文公校昌黎先生集》","表示递进关系,相当于\u201c尚且\u201d、\u201c况且\u201d〖moreover〗","且焉置土石。\u2014\u2014《列子·汤问》","且北方之人,不习水战。\u2014\u2014《三国志》","且人患志之不立。\u2014\u2014《世说新语·自新》","余悲之,且曰\u2026\u2014\u2014唐·柳宗元《捕蛇者说》","且欲观客。\u2014\u2014清·魏禧《大铁椎传》","又如:且夫(且况。况且)","表示假设关系,相当于\u201c若\u201d、\u201c假如\u201d〖if〗","且静郭君听辨而为之也,必无今日之患也。\u2014\u2014《吕氏春秋》","且复妄言。\u2014\u2014明·高启《书博鸡者事》","又如:且如(假如;如果);且使(假使;倘若)","尚且,还","臣死且不避,卮酒安足辞。\u2014\u2014《史记·项羽本纪》","为众人师且不敢。\u2014\u2014唐·柳宗元《柳河东集》","且继今以往。\u2014\u2014〖英〗赫胥黎著、严复译《天演论》","另见jū","","且不说","qiěbùshuō","〖letalone〗更不用说;不提","但是他有这个胆量\u2014\u2014且不说有这种技能\u2014\u2014去维护自己的信念吗?","且夫","qiěfú","〖besides;furthermore;moreover〗∶连词。表示进一层。况且,再说","且夫天下非小弱也。\u2014\u2014汉·贾谊《过秦论上》","且夫天下固有意外\u2014\u2014宋·苏轼《教战守》","〖oh〗∶语气词。用在句首,表示要发表议论,提起话题。","且夫不好问者。\u2014\u2014清·刘开《问说》","且慢","qiěmàn","〖waitamoment〗暂且慢点儿;先别着急(有制止、阻止意思)","客官且慢,尚有一事相告","且战且退","qiězhàn-qiětuì","〖withdrawasfighting〗一种作战状态,意为一边作战,一边退却","且说","qiěshuō","〖formulaforopeningorcontinuingastory〗却说,姑且先说。旧小说中的发语词"]}
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
         * id : e204a7a91594ce5b
         * zi : 且
         * py : ju,qie
         * wubi : egd
         * pinyin : jū,qiě
         * bushou : 一
         * bihua : 5
         * jijie : ["且","qiě","尚，还，表示进一层：既高且大。尚且。况且。","表示暂时：苟且偷安。姑且。","表示将要、将近：城且拔矣。年且九十。","一面这样，一面那样：且走且说。","表示经久：这双鞋且穿呢！","文言发语词，用在句首，与\u201c夫\u201d相似：且说。","姓。","","且","jū","文言助词，用在句末，与\u201c啊\u201d相似。","多的样子。","农历六月的别称。","敬慎的样子：\u201c有萋有且\u201d。","古同\u201c趄\u201d，趑趄。","","笔画数：5；","部首：一；","笔顺编号：25111"]
         * xiangjie : ["且","ｊū","【助】","用在句末,相当于\u201c啊\u201d","狂童之狂也且。\u2014\u2014《诗·郑风·褰裳》","另见qiě","","且","ｑｉě","【代】","此,这;今〖this〗","匪且有且,匪今斯今。\u2014\u2014《诗·周颂·载芟》。毛传:\u201c且,此也。\u201d","","且","qiě","【副】","将近;几乎〖almost;nearly〗","年且九十。\u2014\u2014《列子·汤问》","上晚年多内宠,小王且二十人。\u2014\u2014《资治通鉴》","去后且三年。\u2014\u2014清·侯方域《壮悔堂文集》","将要〖begoingto;will;shall〗","会且归矣,无庶予子憎。\u2014\u2014《诗·齐风》","故天之且风,草木未动而鸟已翔矣。\u2014\u2014《淮南子》","且为之奈何。\u2014\u2014《史记·项羽本纪》","以为且噬己矣。\u2014\u2014唐·柳宗元《三戒》","火且尽。\u2014\u2014宋·王安石《游褒禅山记》","祸且及汝。\u2014\u2014清·魏禧《大铁椎传》","又如:且当(该当);且然(亦将如此)","暂且;姑且〖just;forthetimebeing〗","且往观乎?\u2014\u2014《诗·郑风》","且携所著。\u2014\u2014清·梁启超《谭嗣同传》","甚且心之所以清。\u2014\u2014清·刘开《问说》","又如:你且等一下;这事且放一下;且可(犹暂且);且休(暂且休整);且自(暂且;只管);且住(暂止);且暂(犹暂且);且权(暂且;姑且);且则(姑且);且复(姑且再)","用来加强语气,表示某事物的极端的、假设的或不可能有的情况或事例〖even〗。如:死且不怕,况困难乎","[方言]∶表示需要或可以延续很长时间〖foralongtime〗。如:这笔且用呢;他且来不了呢","且","qiě","【连】","表示并列关系,相当于\u201c又\u201d、\u201c而且\u201d〖and〗","君子有酒,旨且多。\u2014\u2014《诗·小雅》","行牧且荛。\u2014\u2014唐·柳宗元《童区寄传》","连拜且泣。\u2014\u2014宋·王谠《唐语林·雅量》","香且甘者。\u2014\u2014清·周容《春酒堂遗书·芋老人传》","贼能且众。\u2014\u2014清·魏禧《大铁椎传》","又如:贫且贱;横且直;且并(并且);且是(而且)","又\u2026又。连用以表示两件事同时并进〖both\u2026and\u2026〗。如:既高且大;且战且退","表示选择关系,相当于\u201c抑或\u201d、\u201c或者\u201d〖or〗","王以天下为尊秦乎?且尊齐乎\u201d\u2014\u2014《战国策》","是且非邪。\u2014\u2014唐·韩愈《朱文公校昌黎先生集》","表示递进关系,相当于\u201c尚且\u201d、\u201c况且\u201d〖moreover〗","且焉置土石。\u2014\u2014《列子·汤问》","且北方之人,不习水战。\u2014\u2014《三国志》","且人患志之不立。\u2014\u2014《世说新语·自新》","余悲之,且曰\u2026\u2014\u2014唐·柳宗元《捕蛇者说》","且欲观客。\u2014\u2014清·魏禧《大铁椎传》","又如:且夫(且况。况且)","表示假设关系,相当于\u201c若\u201d、\u201c假如\u201d〖if〗","且静郭君听辨而为之也,必无今日之患也。\u2014\u2014《吕氏春秋》","且复妄言。\u2014\u2014明·高启《书博鸡者事》","又如:且如(假如;如果);且使(假使;倘若)","尚且,还","臣死且不避,卮酒安足辞。\u2014\u2014《史记·项羽本纪》","为众人师且不敢。\u2014\u2014唐·柳宗元《柳河东集》","且继今以往。\u2014\u2014〖英〗赫胥黎著、严复译《天演论》","另见jū","","且不说","qiěbùshuō","〖letalone〗更不用说;不提","但是他有这个胆量\u2014\u2014且不说有这种技能\u2014\u2014去维护自己的信念吗?","且夫","qiěfú","〖besides;furthermore;moreover〗∶连词。表示进一层。况且,再说","且夫天下非小弱也。\u2014\u2014汉·贾谊《过秦论上》","且夫天下固有意外\u2014\u2014宋·苏轼《教战守》","〖oh〗∶语气词。用在句首,表示要发表议论,提起话题。","且夫不好问者。\u2014\u2014清·刘开《问说》","且慢","qiěmàn","〖waitamoment〗暂且慢点儿;先别着急(有制止、阻止意思)","客官且慢,尚有一事相告","且战且退","qiězhàn-qiětuì","〖withdrawasfighting〗一种作战状态,意为一边作战,一边退却","且说","qiěshuō","〖formulaforopeningorcontinuingastory〗却说,姑且先说。旧小说中的发语词"]
         */

        private String id;
        private String zi;
        private String py;
        private String wubi;
        private String pinyin;
        private String bushou;
        private String bihua;
        private List<String> jijie;
        private List<String> xiangjie;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getZi() {
            return zi;
        }

        public void setZi(String zi) {
            this.zi = zi;
        }

        public String getPy() {
            return py;
        }

        public void setPy(String py) {
            this.py = py;
        }

        public String getWubi() {
            return wubi;
        }

        public void setWubi(String wubi) {
            this.wubi = wubi;
        }

        public String getPinyin() {
            return pinyin;
        }

        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }

        public String getBushou() {
            return bushou;
        }

        public void setBushou(String bushou) {
            this.bushou = bushou;
        }

        public String getBihua() {
            return bihua;
        }

        public void setBihua(String bihua) {
            this.bihua = bihua;
        }

        public List<String> getJijie() {
            return jijie;
        }

        public void setJijie(List<String> jijie) {
            this.jijie = jijie;
        }

        public List<String> getXiangjie() {
            return xiangjie;
        }

        public void setXiangjie(List<String> xiangjie) {
            this.xiangjie = xiangjie;
        }
    }
}
