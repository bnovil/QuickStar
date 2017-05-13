package com.quickstar.common.message;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author yuton
 * @version 1.0
 * @description 页面显示错误信息
 * @since 2017/2/7 16:54
 */
@Component
@PropertySource("classpath:copyWriteUI.properties")
@ConfigurationProperties(prefix = "ui")
public class CopyWriteUI {

    @Getter
    @Setter
    private String phoneException; //手机号码异常,请重新输入!
    @Getter
    @Setter
    private String passwordException; // 密码错误
    @Getter
    @Setter
    private String havePhone; //手机号已注册
    @Getter
    @Setter
    private String loginError; //用户名密码错误
    @Getter
    @Setter
    private String emailException;//邮箱号码异常
    @Getter
    @Setter
    private String hadEmail;//邮箱号码存在
    @Getter
    @Setter
    private String signInException;//请输入正确的邮箱号或密码
    @Getter
    @Setter
    private String authCodeException;//验证码已失效请重新输入
    @Getter
    @Setter
    private String authCodeIsEmpty; //请输入验证码
    @Getter
    @Setter
    private String imeiException;//请输入imei号码
    @Getter
    @Setter
    private String userDoesNotExist;//该用户不存在
    @Getter
    @Setter
    private String failToRegister;//注册失败
    @Setter
    @Getter
    private String limitedAuthority;//权限拒绝不能操作
    @Setter
    @Getter
    private String equipInfoIsEmpty;//设备信息为空
    @Getter
    @Setter
    private String equipInfoIsNotEmpty;//设备信息不为空
    @Setter
    @Getter
    private String fenceIsEmpty;//电子围栏为空
    @Setter
    @Getter
    private String phoneIsEmpty;//电话不存在
    @Getter
    @Setter
    private String fileIsEmpty;//上传文件为空
    @Getter
    @Setter
    private String imageIp;//图片服务器地址
    @Setter
    @Getter
    private String imageAddress;//上传图片路径
    @Setter
    @Getter
    private String userDoesNotMatch;//用户不匹配
    @Getter
    @Setter
    private String remoteMonitoringSuccess;//远程监听成功
    @Getter
    @Setter
    private String remoteMonitoringFailure;//远程监听失败
    @Getter
    @Setter
    private String lowElectricityMessage;//手杖电量{0}，请及时充电。
    @Getter
    @Setter
    private String intoFenceMessage;//请注意，{0}进入了{1}。
    @Getter
    @Setter
    private String outOfFenceMessage;//请注意，{0}离开了{1}。
}
