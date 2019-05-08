package com.sxkj.gaia.api;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sxkj.exception.ExceptionHandle;
import com.sxkj.gaia.model.User;
import com.sxkj.gaia.service.UserService;
import com.sxkj.util.Constants;
import com.sxkj.util.MD5;
import com.sxkj.util.Result;
import com.sxkj.util.ResultUtil;
import com.sxkj.util.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * com.sxkj.gaia.api.LoginController
 *
 * @author zwd
 * @Description LoginController
 * @Date Create in 2018-07-16 0016 8:48
 * @Modified
 */
@Api(value = "注册、登录、退出接口",description = "注册、登录、退出接口")
@RestController
public class LoginController {
    /** 验证码session key */
    private static final String SESSION_VERIFY_CODE = "verifyCode";

    @Autowired
    private ExceptionHandle exceptionHandle;
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录",notes = "使用登录名和密码登录")
    @GetMapping(value = "/login/{username}/{password}")
    public Result login(HttpServletRequest request,@PathVariable(value = "username") String username,
                        @PathVariable(value = "password") String password){
        try {
            User user = userService.findByLoginName(username);
            password = MD5.EncoderByMd5(username+password);
            if(password.equals(user.getLoginPwd())){
                Map<String,Object> map = new HashMap<>(2);
                map.put("userInfo",userService.findById(user.getId()));
                map.put("token",JwtUtil.generateToken(username));
                map.put("loginTime",System.currentTimeMillis());
                request.getSession().setAttribute(Constants.SESSION_USER_ID,user.getId());
                return ResultUtil.success(map);
            }
            return ResultUtil.error("password not match");
        } catch (Exception e) {
            e.printStackTrace();
            return exceptionHandle.exceptionGet(e);
        }
    }
    @ApiOperation(value = "退出")
    @GetMapping(value = "/logout")
    public Result logout(){
        // todo 退出操作
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return ResultUtil.success();
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }

    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @ApiOperation(value = "生成验证码")
    @GetMapping("/validate")
    public Result validate(HttpServletRequest request, HttpServletResponse response) throws Exception{
        byte[] captchaChallengeAsJpeg ;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生成验证码并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute(SESSION_VERIFY_CODE,createText);
            // 使用验证码生成BufferedImage
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return exceptionHandle.exceptionGet(e);
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();

        return ResultUtil.success();
    }
}
