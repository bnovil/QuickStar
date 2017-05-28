package com.quickstar.listener;


/**
 * Created by lzq on 2016/9/3.
 */
public enum ContextParamDictionary {
    PROJECT_PATH("webAppRootKey", "projectRootPath");

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    private String paramName;

    private String paramValue;

    ContextParamDictionary(String name, String value) {
        paramName = name;
        paramValue = value;
    }
}
