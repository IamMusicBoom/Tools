package com.wma.tools.model.horoscope;

import com.wma.tools.model.IAllApi;
import com.wma.wmalib.base.BaseModel;

import java.util.List;

/**
 * Created by 王明骜 on 19-9-6 下午1:34.
 */
public abstract class BuShouBaseModel extends BaseModel<IAllApi> {


    /**
     * reason : success
     * result : [{"id":"1","bihua":"1","bushou":"丨"},{"id":"2","bihua":"1","bushou":"亅"},{"id":"3","bihua":"1","bushou":"丿"},{"id":"4","bihua":"1","bushou":"乛"},{"id":"5","bihua":"1","bushou":"一"},{"id":"6","bihua":"1","bushou":"乙"},{"id":"7","bihua":"1","bushou":"乚"},{"id":"8","bihua":"1","bushou":"丶"},{"id":"9","bihua":"2","bushou":"八"},{"id":"10","bihua":"2","bushou":"勹"},{"id":"11","bihua":"2","bushou":"匕"},{"id":"12","bihua":"2","bushou":"冫"},{"id":"13","bihua":"2","bushou":"卜"},{"id":"14","bihua":"2","bushou":"厂"},{"id":"15","bihua":"2","bushou":"刀"},{"id":"16","bihua":"2","bushou":"刂"},{"id":"17","bihua":"2","bushou":"儿"},{"id":"18","bihua":"2","bushou":"二"},{"id":"19","bihua":"2","bushou":"匚"},{"id":"20","bihua":"2","bushou":"阝"},{"id":"21","bihua":"2","bushou":"丷"},{"id":"22","bihua":"2","bushou":"几"},{"id":"23","bihua":"2","bushou":"卩"},{"id":"24","bihua":"2","bushou":"冂"},{"id":"25","bihua":"2","bushou":"力"},{"id":"26","bihua":"2","bushou":"冖"},{"id":"27","bihua":"2","bushou":"凵"},{"id":"28","bihua":"2","bushou":"人"},{"id":"29","bihua":"2","bushou":"亻"},{"id":"30","bihua":"2","bushou":"入"},{"id":"31","bihua":"2","bushou":"十"},{"id":"32","bihua":"2","bushou":"厶"},{"id":"33","bihua":"2","bushou":"亠"},{"id":"34","bihua":"2","bushou":"匸"},{"id":"35","bihua":"2","bushou":"讠"},{"id":"36","bihua":"2","bushou":"廴"},{"id":"37","bihua":"2","bushou":"又"},{"id":"38","bihua":"3","bushou":"艹"},{"id":"39","bihua":"3","bushou":"屮"},{"id":"40","bihua":"3","bushou":"彳"},{"id":"41","bihua":"3","bushou":"巛"},{"id":"42","bihua":"3","bushou":"川"},{"id":"43","bihua":"3","bushou":"辶"},{"id":"44","bihua":"3","bushou":"寸"},{"id":"45","bihua":"3","bushou":"大"},{"id":"46","bihua":"3","bushou":"飞"},{"id":"47","bihua":"3","bushou":"干"},{"id":"48","bihua":"3","bushou":"工"},{"id":"49","bihua":"3","bushou":"弓"},{"id":"50","bihua":"3","bushou":"廾"},{"id":"51","bihua":"3","bushou":"广"},{"id":"52","bihua":"3","bushou":"己"},{"id":"53","bihua":"3","bushou":"彐"},{"id":"54","bihua":"3","bushou":"彑"},{"id":"55","bihua":"3","bushou":"巾"},{"id":"56","bihua":"3","bushou":"口"},{"id":"57","bihua":"3","bushou":"马"},{"id":"58","bihua":"3","bushou":"门"},{"id":"59","bihua":"3","bushou":"宀"},{"id":"60","bihua":"3","bushou":"女"},{"id":"61","bihua":"3","bushou":"犭"},{"id":"62","bihua":"3","bushou":"山"},{"id":"63","bihua":"3","bushou":"彡"},{"id":"64","bihua":"3","bushou":"尸"},{"id":"65","bihua":"3","bushou":"饣"},{"id":"66","bihua":"3","bushou":"士"},{"id":"67","bihua":"3","bushou":"扌"},{"id":"68","bihua":"3","bushou":"氵"},{"id":"69","bihua":"3","bushou":"纟"},{"id":"70","bihua":"3","bushou":"巳"},{"id":"71","bihua":"3","bushou":"土"},{"id":"72","bihua":"3","bushou":"囗"},{"id":"73","bihua":"3","bushou":"兀"},{"id":"74","bihua":"3","bushou":"夕"},{"id":"75","bihua":"3","bushou":"小"},{"id":"76","bihua":"3","bushou":"忄"},{"id":"77","bihua":"3","bushou":"幺"},{"id":"78","bihua":"3","bushou":"弋"},{"id":"79","bihua":"3","bushou":"尢"},{"id":"80","bihua":"3","bushou":"夂"},{"id":"81","bihua":"3","bushou":"子"},{"id":"82","bihua":"4","bushou":"贝"},{"id":"83","bihua":"4","bushou":"比"},{"id":"84","bihua":"4","bushou":"灬"},{"id":"85","bihua":"4","bushou":"长"},{"id":"86","bihua":"4","bushou":"车"},{"id":"87","bihua":"4","bushou":"歹"},{"id":"88","bihua":"4","bushou":"斗"},{"id":"89","bihua":"4","bushou":"厄"},{"id":"90","bihua":"4","bushou":"方"},{"id":"91","bihua":"4","bushou":"风"},{"id":"92","bihua":"4","bushou":"父"},{"id":"93","bihua":"4","bushou":"戈"},{"id":"94","bihua":"4","bushou":"卝"},{"id":"95","bihua":"4","bushou":"户"},{"id":"96","bihua":"4","bushou":"火"},{"id":"97","bihua":"4","bushou":"旡"},{"id":"98","bihua":"4","bushou":"见"},{"id":"99","bihua":"4","bushou":"斤"},{"id":"100","bihua":"4","bushou":"耂"},{"id":"101","bihua":"4","bushou":"毛"},{"id":"102","bihua":"4","bushou":"木"},{"id":"103","bihua":"4","bushou":"肀"},{"id":"104","bihua":"4","bushou":"牛"},{"id":"105","bihua":"4","bushou":"牜"},{"id":"106","bihua":"4","bushou":"爿"},{"id":"107","bihua":"4","bushou":"片"},{"id":"108","bihua":"4","bushou":"攴"},{"id":"109","bihua":"4","bushou":"攵"},{"id":"110","bihua":"4","bushou":"气"},{"id":"111","bihua":"4","bushou":"欠"},{"id":"112","bihua":"4","bushou":"犬"},{"id":"113","bihua":"4","bushou":"日"},{"id":"114","bihua":"4","bushou":"氏"},{"id":"115","bihua":"4","bushou":"礻"},{"id":"116","bihua":"4","bushou":"手"},{"id":"117","bihua":"4","bushou":"殳"},{"id":"118","bihua":"4","bushou":"水"},{"id":"119","bihua":"4","bushou":"瓦"},{"id":"120","bihua":"4","bushou":"尣"},{"id":"121","bihua":"4","bushou":"王"},{"id":"122","bihua":"4","bushou":"韦"},{"id":"123","bihua":"4","bushou":"文"},{"id":"124","bihua":"4","bushou":"毋"},{"id":"125","bihua":"4","bushou":"心"},{"id":"126","bihua":"4","bushou":"牙"},{"id":"127","bihua":"4","bushou":"爻"},{"id":"128","bihua":"4","bushou":"曰"},{"id":"129","bihua":"4","bushou":"月"},{"id":"130","bihua":"4","bushou":"爫"},{"id":"131","bihua":"4","bushou":"支"},{"id":"132","bihua":"4","bushou":"止"},{"id":"133","bihua":"4","bushou":"爪"},{"id":"134","bihua":"5","bushou":"白"},{"id":"135","bihua":"5","bushou":"癶"},{"id":"136","bihua":"5","bushou":"歺"},{"id":"137","bihua":"5","bushou":"甘"},{"id":"138","bihua":"5","bushou":"瓜"},{"id":"139","bihua":"5","bushou":"禾"},{"id":"140","bihua":"5","bushou":"钅"},{"id":"141","bihua":"5","bushou":"立"},{"id":"142","bihua":"5","bushou":"龙"},{"id":"143","bihua":"5","bushou":"矛"},{"id":"144","bihua":"5","bushou":"皿"},{"id":"145","bihua":"5","bushou":"母"},{"id":"146","bihua":"5","bushou":"目"},{"id":"147","bihua":"5","bushou":"疒"},{"id":"148","bihua":"5","bushou":"鸟"},{"id":"149","bihua":"5","bushou":"皮"},{"id":"150","bihua":"5","bushou":"生"},{"id":"151","bihua":"5","bushou":"石"},{"id":"152","bihua":"5","bushou":"矢"},{"id":"153","bihua":"5","bushou":"示"},{"id":"154","bihua":"5","bushou":"罒"},{"id":"155","bihua":"5","bushou":"田"},{"id":"156","bihua":"5","bushou":"玄"},{"id":"157","bihua":"5","bushou":"穴"},{"id":"158","bihua":"5","bushou":"疋"},{"id":"159","bihua":"5","bushou":"业"},{"id":"160","bihua":"5","bushou":"衤"},{"id":"161","bihua":"5","bushou":"用"},{"id":"162","bihua":"5","bushou":"玉"},{"id":"163","bihua":"6","bushou":"耒"},{"id":"164","bihua":"6","bushou":"艸"},{"id":"165","bihua":"6","bushou":"臣"},{"id":"166","bihua":"6","bushou":"虫"},{"id":"167","bihua":"6","bushou":"而"},{"id":"168","bihua":"6","bushou":"耳"},{"id":"169","bihua":"6","bushou":"缶"},{"id":"170","bihua":"6","bushou":"艮"},{"id":"171","bihua":"6","bushou":"虍"},{"id":"172","bihua":"6","bushou":"臼"},{"id":"173","bihua":"6","bushou":"米"},{"id":"174","bihua":"6","bushou":"齐"},{"id":"175","bihua":"6","bushou":"肉"},{"id":"176","bihua":"6","bushou":"色"},{"id":"177","bihua":"6","bushou":"舌"},{"id":"178","bihua":"6","bushou":"覀"},{"id":"179","bihua":"6","bushou":"页"},{"id":"180","bihua":"6","bushou":"先"},{"id":"181","bihua":"6","bushou":"行"},{"id":"182","bihua":"6","bushou":"血"},{"id":"183","bihua":"6","bushou":"羊"},{"id":"184","bihua":"6","bushou":"聿"},{"id":"185","bihua":"6","bushou":"至"},{"id":"186","bihua":"6","bushou":"舟"},{"id":"187","bihua":"6","bushou":"衣"},{"id":"188","bihua":"6","bushou":"竹"},{"id":"189","bihua":"6","bushou":"自"},{"id":"190","bihua":"6","bushou":"羽"},{"id":"191","bihua":"6","bushou":"糸"},{"id":"192","bihua":"6","bushou":"糹"},{"id":"193","bihua":"7","bushou":"貝"},{"id":"194","bihua":"7","bushou":"采"},{"id":"195","bihua":"7","bushou":"镸"},{"id":"196","bihua":"7","bushou":"車"},{"id":"197","bihua":"7","bushou":"辰"},{"id":"198","bihua":"7","bushou":"赤"},{"id":"199","bihua":"7","bushou":"辵"},{"id":"200","bihua":"7","bushou":"豆"},{"id":"201","bihua":"7","bushou":"谷"},{"id":"202","bihua":"7","bushou":"見"},{"id":"203","bihua":"7","bushou":"角"},{"id":"204","bihua":"7","bushou":"克"},{"id":"205","bihua":"7","bushou":"里"},{"id":"206","bihua":"7","bushou":"卤"},{"id":"207","bihua":"7","bushou":"麦"},{"id":"208","bihua":"7","bushou":"身"},{"id":"209","bihua":"7","bushou":"豕"},{"id":"210","bihua":"7","bushou":"辛"},{"id":"211","bihua":"7","bushou":"言"},{"id":"212","bihua":"7","bushou":"邑"},{"id":"213","bihua":"7","bushou":"酉"},{"id":"214","bihua":"7","bushou":"豸"},{"id":"215","bihua":"7","bushou":"走"},{"id":"216","bihua":"7","bushou":"足"},{"id":"217","bihua":"8","bushou":"青"},{"id":"218","bihua":"8","bushou":"靑"},{"id":"219","bihua":"8","bushou":"雨"},{"id":"220","bihua":"8","bushou":"齿"},{"id":"221","bihua":"8","bushou":"長"},{"id":"222","bihua":"8","bushou":"非"},{"id":"223","bihua":"8","bushou":"阜"},{"id":"224","bihua":"8","bushou":"金"},{"id":"225","bihua":"8","bushou":"釒"},{"id":"226","bihua":"8","bushou":"隶"},{"id":"227","bihua":"8","bushou":"門"},{"id":"228","bihua":"8","bushou":"靣"},{"id":"229","bihua":"8","bushou":"飠"},{"id":"230","bihua":"8","bushou":"鱼"},{"id":"231","bihua":"8","bushou":"隹"},{"id":"232","bihua":"9","bushou":"風"},{"id":"233","bihua":"9","bushou":"革"},{"id":"234","bihua":"9","bushou":"骨"},{"id":"235","bihua":"9","bushou":"鬼"},{"id":"236","bihua":"9","bushou":"韭"},{"id":"237","bihua":"9","bushou":"面"},{"id":"238","bihua":"9","bushou":"首"},{"id":"239","bihua":"9","bushou":"韋"},{"id":"240","bihua":"9","bushou":"香"},{"id":"241","bihua":"9","bushou":"頁"},{"id":"242","bihua":"9","bushou":"音"},{"id":"243","bihua":"10","bushou":"髟"},{"id":"244","bihua":"10","bushou":"鬯"},{"id":"245","bihua":"10","bushou":"鬥"},{"id":"246","bihua":"10","bushou":"高"},{"id":"247","bihua":"10","bushou":"鬲"},{"id":"248","bihua":"10","bushou":"馬"},{"id":"249","bihua":"11","bushou":"黄"},{"id":"250","bihua":"11","bushou":"鹵"},{"id":"251","bihua":"11","bushou":"鹿"},{"id":"252","bihua":"11","bushou":"麻"},{"id":"253","bihua":"11","bushou":"麥"},{"id":"254","bihua":"11","bushou":"鳥"},{"id":"255","bihua":"11","bushou":"魚"},{"id":"256","bihua":"12","bushou":"鼎"},{"id":"257","bihua":"12","bushou":"黑"},{"id":"258","bihua":"12","bushou":"黽"},{"id":"259","bihua":"12","bushou":"黍"},{"id":"260","bihua":"12","bushou":"黹"},{"id":"261","bihua":"13","bushou":"鼓"},{"id":"262","bihua":"13","bushou":"鼠"},{"id":"263","bihua":"14","bushou":"鼻"},{"id":"264","bihua":"14","bushou":"齊"},{"id":"265","bihua":"15","bushou":"齒"},{"id":"266","bihua":"15","bushou":"龍"},{"id":"267","bihua":"15","bushou":"龠"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * bihua : 1
         * bushou : 丨
         */

        private String id;
        private String bihua;
        private String bushou;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBihua() {
            return bihua;
        }

        public void setBihua(String bihua) {
            this.bihua = bihua;
        }

        public String getBushou() {
            return bushou;
        }

        public void setBushou(String bushou) {
            this.bushou = bushou;
        }
    }
}
