package com.sk.jsp.controller;

import com.base.common.AbstractController;
import com.base.util.Constant;
import com.smart.sso.client.SessionUtils;
import com.smart.sso.client.model.SysRole;
import com.smart.sso.client.service.FilterSysRoleService;
import com.sk.jsp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

/**
 * login控制层
 *
 * @author zhangqiao
 * @since 2019-01-08 17:27:03
 */
@Controller
@RequestMapping("/")
public class LoginController extends AbstractController {

    @Autowired
    private FilterSysRoleService roleService;

    @Autowired
    private LoginService loginService;

    /**
     * 进入首页
     * m
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String roleUrl = request.getContextPath() + com.smart.sso.client.base.utils.Constant.INDEX_URL_ADMIN;
        response.sendRedirect(roleUrl);
    }

    /**
     * 获取当前用户角色列表
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getRoleList", method = RequestMethod.POST)
    public ModelAndView getRoleList (HttpServletRequest request) {
        String account = SessionUtils.getSessionUser(request).getAccount();
        List<SysRole> roleList = roleService.getAllList(null, account);
        return new ModelAndView(JSON_VIEW).addObject(Constant.RETURN, roleList);
    }

    /**
     * 赋予当前用户角色
     *
     * @param request
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/setRole", method = RequestMethod.POST)
    public ModelAndView setRole (HttpServletRequest request, String roleId) {
        String account = SessionUtils.getSessionUser(request).getAccount();
        int result = loginService.setRole(account, roleId);
        if (1 == result) {
            WebUtils.setSessionAttribute(request, "role", roleId);
        }
        return new ModelAndView(JSON_VIEW).addObject(Constant.RETURN, result);
    }

    /**
     * 退出
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            // 清除session
            Enumeration<String> em = session.getAttributeNames();
            while (em.hasMoreElements()) {
                request.getSession().removeAttribute(em.nextElement().toString());
            }
            session.invalidate();
        }
        String roleUrl = request.getContextPath() + "/admin/logout";
        response.sendRedirect(roleUrl);
    }

}