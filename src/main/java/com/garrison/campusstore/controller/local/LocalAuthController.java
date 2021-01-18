package com.garrison.campusstore.controller.local;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.garrison.campusstore.dto.PersonInfoExecution;
import com.garrison.campusstore.enums.PersonInfoStateEnum;
import com.garrison.campusstore.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.garrison.campusstore.dao.PersonInfoDao;
import com.garrison.campusstore.dto.LocalAuthExecution;
import com.garrison.campusstore.entity.LocalAuth;
import com.garrison.campusstore.entity.PersonInfo;
import com.garrison.campusstore.enums.LocalAuthStateEnum;
import com.garrison.campusstore.exceptions.LocalAuthOperationException;
import com.garrison.campusstore.service.LocalAuthService;
import com.garrison.campusstore.util.CodeUtil;
import com.garrison.campusstore.util.HttpServletRequestUtil;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/local", method = { RequestMethod.GET, RequestMethod.POST })
public class LocalAuthController {
    @Autowired
    private LocalAuthService localAuthService;

    @Autowired
    private PersonInfoService personInfoService;

    /**
     * 注册用户
     * @param request
     * @return
     */
    @RequestMapping(value = "/bindlocalauth", method = RequestMethod.POST)
    private Map<String, Object> bindLocalAuth(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        //验证码校验
        if(!CodeUtil.checkVerifyCode(request))  {
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码错误");
            return modelMap;
        }

        String userName = HttpServletRequestUtil.getString(request,"userName");
        String password = HttpServletRequestUtil.getString(request,"password");
        Integer userType = HttpServletRequestUtil.getInt(request,"userType");
        PersonInfo user = personInfoService.getPersonInfoByName(userName);
        if( userName != null && userType != null && password != null){
            //如果用户信息没有被注册
            if(user == null || "".equals(user)){
                user = new PersonInfo();
                user.setName(userName);
                user.setEnableStatus(1);
                user.setUserType(userType);
                user.setCreateTime(new Date());
                user.setLastEditTime(new Date());
                PersonInfoExecution pe = personInfoService.insertPersonInfo(user);
                if(pe.getState() != PersonInfoStateEnum.SUCCESS.getState()){
                    modelMap.put("errMsg", pe.getStateInfo());
                }else{
                    //用户信息注册成功，将local auth与其绑定
                    LocalAuth localAuth = new LocalAuth();
                    localAuth.setUsername(userName);
                    localAuth.setPassword(password);
                    localAuth.setPersonInfo(user);
                    localAuth.setCreateTime(new Date());
                    LocalAuthExecution le = localAuthService.bindLocalAuth(localAuth);
                    request.getSession().setAttribute("user", le.getLocalAuth().getPersonInfo());
                    if (le.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
                        modelMap.put("success", true);
                        modelMap.put("userType", userType);
                    } else {
                        modelMap.put("success", false);
                        modelMap.put("errMsg", le.getStateInfo());
                    }
                }

            }else{
                modelMap.put("success", false);
                modelMap.put("errMsg", "用户名已被占用");
            }
        }else{
            modelMap.put("success", false);
            modelMap.put("errMsg", "用户名，密码，或用户类型不能为空");
        }
        return modelMap;
    }

    /**
     * 修改密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/changelocalpwd", method = RequestMethod.POST)
    private Map<String, Object> changeLocalPwd(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 验证码校验
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        // 获取帐号
        String userName = HttpServletRequestUtil.getString(request, "userName");
        // 获取原密码
        String password = HttpServletRequestUtil.getString(request, "password");
        // 获取新密码
        String newPassword = HttpServletRequestUtil.getString(request, "newPassword");
        //获取session中的用户信息
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
        // 非空判断，要求帐号新旧密码以及当前的用户session非空，且新旧密码不相同
        if (userName != null && password != null && newPassword != null && user != null && user.getUserId() != null
                && !password.equals(newPassword)) {
            try {
                // 查看原先帐号，看看与输入的帐号是否一致，不一致则认为是非法操作
                LocalAuth localAuth = localAuthService.getLocalAuthByUserId(user.getUserId());
                if (localAuth == null || !localAuth.getUsername().equals(userName)) {
                    // 不一致则直接退出
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "输入的帐号非本次登录的帐号");
                    return modelMap;
                }
                // 修改平台帐号的用户密码
                LocalAuthExecution le = localAuthService.modifyLocalAuth(user.getUserId(), userName, password,
                        newPassword);
                if (le.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", le.getStateInfo());
                }
            } catch (LocalAuthOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入密码");
        }
        return modelMap;
    }

    /**
     * 登陆验证
     * @param request
     * @return
     */
    @RequestMapping(value = "/logincheck", method = RequestMethod.POST)
    private Map<String, Object> logincheck(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 获取是否需要进行验证码校验的标识符
        boolean needVerify = HttpServletRequestUtil.getBoolean(request, "needVerify");
        if (needVerify && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        // 获取输入的帐号
        String userName = HttpServletRequestUtil.getString(request, "userName");
        // 获取输入的密码
        String password = HttpServletRequestUtil.getString(request, "password");
        // 非空校验
        if (userName != null && password != null) {
            // 传入帐号和密码去获取平台帐号信息
            LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(userName, password);
            if (localAuth != null) {
                // 若能取到帐号信息则登录成功
                modelMap.put("success", true);
                modelMap.put("userType", localAuth.getPersonInfo().getUserType());
                // 同时在session里设置用户信息
                request.getSession().setAttribute("user", localAuth.getPersonInfo());
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "用户名或密码错误");
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "用户名和密码均不能为空");
        }
        return modelMap;
    }

    /**
     * 登出系统
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    private Map<String, Object> logout(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 将用户session置为空
        request.getSession().setAttribute("user", null);
        modelMap.put("success", true);
        return modelMap;
    }
}
