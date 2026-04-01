package org.example.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.JwtUtils;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//定义拦截器
@Slf4j
//@Component
public class TokenInterceptor  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        if (token == null||token.isEmpty()){
            log.info("令牌为空，响应401");
            response.setStatus(401);
            return false;
        }
        try {
            JwtUtils.parseJWT(token);
            log.info("令牌合法，放行");
        } catch (Exception e) {
            log.info("令牌非法，响应401");
            response.setStatus(401);
            return false;
        }
        log.info("令牌合法，放行");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
