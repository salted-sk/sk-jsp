package com.base.exception;

import com.base.common.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * 异常处理方式
 *
 * @author zhangqiao
 * @since 2018-05-02 14:00
 */
public class ApplicationExceptionHandler implements HandlerExceptionResolver, PriorityOrdered, ApplicationContextAware {

    private static final Log LOG = LogFactory.getLog(ApplicationExceptionHandler.class);

    private ApplicationContext applicationContext;

    private boolean debug = false;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            handlerException(request, response, ex, modelAndView);
        } catch (IOException ig) {
            LOG.error("handle exception failure: ".concat(ig.getMessage()));
            ig.printStackTrace();
        }
        return modelAndView;
    }

    private void handlerException(HttpServletRequest request, HttpServletResponse response, Exception ex, ModelAndView modelAndView) throws IOException {
        if (ex != null) {
            ex.printStackTrace();
            if (ex.getMessage() != null) {
                LOG.error("handle exception: ".concat(ex.getMessage()));
            }
        }
        try {
            if (response.getBufferSize() > 0) {
                response.reset();
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            String message = ex.getMessage();
            Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
            message = applicationContext.getMessage(message, null, null, locale);
            if (message == null) {
                String className = ex.getClass().getName();
                message = applicationContext.getMessage(className, null, null, locale);
                if (message == null) {
                    message = applicationContext.getMessage("system.error.unknow", null, null, locale);
                }
            }
            Response failure = Response.failure(new String[]{message});
            if (!request.getHeader("accept").startsWith("text/html")) {
                // write json
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(response.getWriter(), failure);
            } else {
                //model and view
                modelAndView.addObject("failure", failure);
                modelAndView.setViewName("forward:/".concat("error"));
                if (debug) {
                    modelAndView.addObject("debug", debug);
                }
            }
        } catch (Exception e) {
            LOG.error("handle exception has an error,ignore it: ".concat(ex.getMessage()));
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

