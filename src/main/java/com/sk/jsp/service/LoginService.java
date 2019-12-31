package com.sk.jsp.service;

import com.smart.sso.client.base.utils.EmptyUtils;
import com.smart.sso.client.model.SysCurrentRole;
import com.smart.sso.client.model.SysRole;
import com.smart.sso.client.service.FilterSysCurrentRoleService;
import com.smart.sso.client.service.FilterSysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 登录服务层
 *
 * @author zhangqiao
 * @since 2019-01-09 11:42:21
 */
@Service
public class LoginService {

    @Autowired
    private FilterSysRoleService roleService;

    @Autowired
    private FilterSysCurrentRoleService currentRoleService;

    /**
     * 赋予当前用户角色
     *
     * @param account
     * @param roleId
     * @return
     */
    public int setRole (String account, String roleId) {
        List<SysRole> roleList = roleService.getAllList(null, account);
        int result = 0;
        for (SysRole role : roleList) {
            if (EmptyUtils.isNotEmpty(roleId) && roleId.equals(role.getId())) {
                SysCurrentRole currentRole = currentRoleService.getCurrentRole(account);
                if (EmptyUtils.isNotEmpty(currentRole)) {
                    currentRole.setRoleid(roleId);
                } else {
                    currentRole = new SysCurrentRole();
                    currentRole.setAccount(account);
                    currentRole.setRoleid(roleId);
                }
                result = currentRoleService.saveAndUpdate(currentRole);
                break;
            }
        }
        return result;
    }
    
}