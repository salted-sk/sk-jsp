package com.sk.jsp.controller;

import com.base.common.AbstractController;
import com.base.util.Constant;
import com.smart.sso.client.filter.SessionPermission;
import com.smart.sso.client.model.SysPermission;
import com.smart.sso.client.service.FilterSysRoleService;
import com.smart.sso.client.service.FilterSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * login控制层
 *
 * @author zhangqiao
 * @since 2019-01-08 17:27:03
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

    @Autowired
    private FilterSysRoleService roleService;

    @Autowired
    private FilterSysUserService userService;

    /**
     * 进入首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("/index");
        //当前角色
        String roleId = (String) WebUtils.getSessionAttribute(request, "role");
        view.addObject("roleId", roleId);
        //用户菜单
        SessionPermission sessionPermission = (SessionPermission) WebUtils.getSessionAttribute(request, "_sessionUserPermission");
        List<SysPermission> menuList = sessionPermission == null? null : sessionPermission.getMenuList();
        view.addObject("menuList", menuList);
        return view;
    }

    /**
     * 修改密码
     *
     * @param pwd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/savePwd", method = RequestMethod.GET)
    public ModelAndView savePwd (String pwd) {
        int ret = userService.savePwd("",pwd);
        return new ModelAndView(JSON_VIEW).addObject(Constant.RETURN, ret);
    }

    /**
     * 进入统计图表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public ModelAndView eCharts(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("/admin/first");
        return view;
    }

}