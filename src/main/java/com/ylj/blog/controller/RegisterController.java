package com.ylj.blog.controller;


import org.apache.commons.lang3.StringUtils;
import com.ylj.blog.entity.User;
import com.ylj.blog.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.util.concurrent.TimeUnit;

@Controller
public class RegisterController {
    private final static Logger log = Logger.getLogger(RegisterController.class);
    @Autowired
    private UserService userService;

    /**
     * 账号注册
     * @param model
     * @param email
     * @param password
     * @param phone
     * @param nickname
     * @param code
     * @return
     */
    @RequestMapping("/doRegister")
    public String doRegister(Model model, @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "password", required = false) String password,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "nickName", required = false) String nickname,
                             @RequestParam(value = "code", required = false) String code) {

        log.debug("注册...");
        if (StringUtils.isBlank(code)) {
            model.addAttribute("error", "非法注册，请重新注册！");
            return "register";
        }

        int b = checkValidateCode(code);
        if (b == -1) {
            model.addAttribute("error", "验证码超时，请重新注册！");
            return "../register";
        } else if (b == 0) {
            model.addAttribute("error", "验证码不正确,请重新输入!");
            return "register";
        }


        User user = userService.findByEmail(email);
        if (user != null) {
            model.addAttribute("error", "该用户已经被注册！");
            return "register";
        } else {
            user = new User();
            user.setNickName(nickname);

//            user.setPassword(new Md5PasswordEncoder().encodePassword(password, email));
            user.setPhone(phone);
            user.setEmail(email);
            user.setState("0");
            user.setEnable("0");
            user.setImgUrl("/images/icon_m.jpg");
            //邮件激活码
//            String validateCode = MD5Util.encodeToHex("salt" + email + password);
//            redisTemplate.opsForValue().set(email, validateCode, 24, TimeUnit.HOURS);// 24小时 有效激活 redis保存激活码

            userService.regist(user);

//            RoleUser roleUser = new RoleUser();
//            roleUser.setuId(user.getId());
//            roleUser.setrId(Constants.ROLE_USER);
//            roleUserService.add(roleUser);

            log.info("注册成功");
//            SendEmail.sendEmailMessage(email, validateCode);
//            String message = email + "," + validateCode;
//            model.addAttribute("message", message);
            return "regist/registerSuccess";
        }

    }

    /**
     * 验证码校验
     * @param code
     * @return
     */
    public int checkValidateCode(String code) {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Object vercode = attrs.getRequest().getSession().getAttribute("VERCODE_KEY");
        if (null == vercode) {
            return -1;
        }
        if (!code.equalsIgnoreCase(vercode.toString())) {
            return 0;
        }
        return 1;
    }
}