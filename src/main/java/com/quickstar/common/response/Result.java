package com.quickstar.common.response;

import com.quickstar.common.json.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;


public class Result<T> implements IResult<T> {

    private final Logger log =  LoggerFactory.getLogger(this.getClass());

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    private Integer code = 0;
    private String msg;
    private T result;

    public Result() {
    }

//    public Result(ResultMessage resultMessage) {
//        code = resultMessage.getCode();
//        msg = resultMessage.getMsg();
//    }
//
//    public Result(ResultMessage resultMessage, T result) {
//        code = resultMessage.getCode();
//        msg = resultMessage.getMsg();
//        this.result = result;
//    }

    @Override
    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toJson() throws JsonProcessingException {
        String result = JsonUtil.objectToJson(this, PATTERN);
        log.info(result);
        return result;
    }

    @Override
    public boolean isSuccessful() {
        return code.equals(HttpStatus.OK.value());
    }

    @Override
    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public T getResult() {
        return result;
    }
}