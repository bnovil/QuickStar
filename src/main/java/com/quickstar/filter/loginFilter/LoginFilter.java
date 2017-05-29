package com.quickstar.filter.loginFilter;//package com.quickstar.filter.loginFilter;
//
//import com.quickstar.common.http.token.CookieUtil;
//import com.quickstar.common.http.token.SessionUtil;
//import com.quickstar.common.message.ResultMessage;
//import com.quickstar.common.other.Files.MD5Util;
//import com.quickstar.redis.RedisUtil;
//import com.quickstar.common.response.IResultException;
//import com.quickstar.pojo.dto.user.UserDTO;
//import com.quickstar.pojo.vo.UserVo;
//import com.quickstar.service.interfaces.users.UserService;
//import org.apache.commons.lang3.StringUtils;
//
//import javax.annotation.Resource;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * @author yuton
// * @version 1.0
// * @description
// * @since 2017/2/9 12:30
// */
//@WebFilter(urlPatterns = "/api/*", filterName = "loginFilter")
//public class LoginFilter implements Filter {
////    @Resource
////    private UserService userService;
//
////    @Resource
////    private RedisUtil<String, UserVo> redisUtil;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
////        HttpServletRequest request = (HttpServletRequest) servletRequest;
////        String accessToken = SessionUtil.getSessionKey(request);
////        if (StringUtils.isBlank(accessToken)) {
////            throw new IResultException(ResultMessage.LOGIN_TIME_OUT);
////        }
////        String nickName = CookieUtil.getCookieByName(request, SessionUtil.NICE_NAME);
////        UserDTO userDTO = userService.getUserByNickName(nickName);
////        if (null == userDTO) {
////            throw new IResultException(ResultMessage.LOGIN_TIME_OUT);
////        }
////        UserVo user = redisUtil.get(MD5Util.getMD5Encode(accessToken, userDTO.getPhone()));
////        if (null == user) {
////            throw new IResultException(ResultMessage.LOGIN_TIME_OUT);
////        }
//        filterChain.doFilter(request, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
