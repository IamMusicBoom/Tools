package com.wma.tools.model.horoscope;

/**
 * Created by 王明骜 on 19-9-6 上午11:15.
 */
public class DataModel {

    private String id;
    private String key;
    private String value;

    public DataModel(String id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
