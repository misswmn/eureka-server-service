package com.roncoo.eshop.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author wangmn
 * @version 1.0
 * @description
 * @date 2020/3/16 0:16
 */
@Component
public class UserLoginFilter extends ZuulFilter {
    private static final Logger log = LoggerFactory.getLogger(UserLoginFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));

        Object userId = request.getParameter("userId");
        if (null == userId) {
            log.info("userId is empty");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            try {
                requestContext.getResponse().getWriter().write("userId is empty");
            } catch (IOException e) {
                log.error("", e);
            }
            return null;
        }
        return null;
    }
}
