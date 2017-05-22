package com.quickstar.listener;

import lombok.Getter;

/**
 * Created by lzq on 2016/9/3.
 */
public enum ContextParamDictionary {
    PROJECT_PATH("webAppRootKey", "projectRootPath");
    @Getter
    private String paramName;
    @Getter
    private String paramValue;

    ContextParamDictionary(String name, String value) {
        paramName = name;
        paramValue = value;
    }
}
