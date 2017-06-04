package com.quickstar.common.message;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author lzq
 * @version 1.0
 * @description 页面显示错误信息
 * @since 2017/2/7 16:54
 */
@Component
@PropertySource("classpath:UIMessage.properties")
@ConfigurationProperties(prefix = "ui")
public class UIMessage {

    private String passwordException; // 密码错误

    private String havePhone; //手机号已注册

    private String loginError; //用户名密码错误

    private String emailException;//邮箱号码异常

    private String hadEmail;//邮箱号码存在

    public String getPasswordException() {
        return passwordException;
    }

    public void setPasswordException(String passwordException) {
        this.passwordException = passwordException;
    }

    public String getHavePhone() {
        return havePhone;
    }

    public void setHavePhone(String havePhone) {
        this.havePhone = havePhone;
    }

    public String getLoginError() {
        return loginError;
    }

    public void setLoginError(String loginError) {
        this.loginError = loginError;
    }

    public String getEmailException() {
        return emailException;
    }

    public void setEmailException(String emailException) {
        this.emailException = emailException;
    }

    public String getHadEmail() {
        return hadEmail;
    }

    public void setHadEmail(String hadEmail) {
        this.hadEmail = hadEmail;
    }

    public String getSignInException() {
        return signInException;
    }

    public void setSignInException(String signInException) {
        this.signInException = signInException;
    }

    public String getAuthCodeException() {
        return authCodeException;
    }

    public void setAuthCodeException(String authCodeException) {
        this.authCodeException = authCodeException;
    }

    public String getAuthCodeIsEmpty() {
        return authCodeIsEmpty;
    }

    public void setAuthCodeIsEmpty(String authCodeIsEmpty) {
        this.authCodeIsEmpty = authCodeIsEmpty;
    }

    public String getUserDoesNotExist() {
        return userDoesNotExist;
    }

    public void setUserDoesNotExist(String userDoesNotExist) {
        this.userDoesNotExist = userDoesNotExist;
    }

    public String getFailToRegister() {
        return failToRegister;
    }

    public void setFailToRegister(String failToRegister) {
        this.failToRegister = failToRegister;
    }

    public String getLimitedAuthority() {
        return limitedAuthority;
    }

    public void setLimitedAuthority(String limitedAuthority) {
        this.limitedAuthority = limitedAuthority;
    }


    public String getFileIsEmpty() {
        return fileIsEmpty;
    }

    public void setFileIsEmpty(String fileIsEmpty) {
        this.fileIsEmpty = fileIsEmpty;
    }

    public String getImageIp() {
        return imageIp;
    }

    public void setImageIp(String imageIp) {
        this.imageIp = imageIp;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getUserDoesNotMatch() {
        return userDoesNotMatch;
    }

    public void setUserDoesNotMatch(String userDoesNotMatch) {
        this.userDoesNotMatch = userDoesNotMatch;
    }

    private String signInException;//请输入正确的邮箱号或密码

    private String authCodeException;//验证码已失效请重新输入

    private String authCodeIsEmpty; //请输入验证码

    private String userDoesNotExist;//该用户不存在

    private String failToRegister;//注册失败

    private String limitedAuthority;//权限拒绝不能操作

    private String fileIsEmpty;//上传文件为空

    private String imageIp;//图片服务器地址

    private String imageAddress;//上传图片路径

    private String userDoesNotMatch;//用户不匹配


}
