package com.example.abs.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.abs.common.JwtUtils;
import com.example.abs.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("X-Token");
        try {
            JwtUtils.decodeByToken(token);
        } catch (Exception e) {     // 拦截异常token 多半是空token或错误token
            log.error(e.getMessage());  // 提示拦截失败
            response.getWriter().write(JSON.toJSONString(Result.error().message(e.getMessage())));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
