package com.base.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * 抽象Controller
 *
 * @author zhangqiao
 * @since 2018-12-31 14:00
 */
public class AbstractController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Resource
    protected JsonView JSON_VIEW;

    protected String getMessage(String i18n) {
        Locale locale = getLocale();
        return applicationContext.getMessage(i18n, null, locale);
    }

    protected String getMessage(String i18n, Object[] args) {
        Locale locale = getLocale();
        return applicationContext.getMessage(i18n, args, locale);
    }

    protected String getMessage(String i18n, String defaultMessage) {
        Locale locale = getLocale();
        return applicationContext.getMessage(i18n, null, defaultMessage, locale);
    }

    private Locale getLocale() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

}
