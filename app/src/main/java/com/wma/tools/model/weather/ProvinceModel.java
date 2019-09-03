package com.wma.tools.model.weather;

/**
 * Created by 王明骜 on 19-9-3 上午10:40.
 */
public class ProvinceModel {
    private String name;
    private boolean isCheck;

    public ProvinceModel(String name, boolean isCheck) {
        this.name = name;
        this.isCheck = isCheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
