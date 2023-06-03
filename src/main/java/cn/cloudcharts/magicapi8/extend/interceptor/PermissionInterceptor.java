package cn.cloudcharts.magicapi8.extend.interceptor;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.ssssssss.magicapi.core.interceptor.RequestInterceptor;
import org.ssssssss.magicapi.core.model.ApiInfo;
import org.ssssssss.magicapi.core.model.JsonBean;
import org.ssssssss.magicapi.core.model.Options;
import org.ssssssss.magicapi.core.servlet.MagicHttpServletRequest;
import org.ssssssss.magicapi.core.servlet.MagicHttpServletResponse;
import org.ssssssss.script.MagicScriptContext;

/**
 * @author wuque
 * @title: CustomRequestInterceptor
 * @projectName magic-api8
 * @description: 自定义接口拦截器  接口鉴权
 * @date 2023/6/310:23
 */
@Component
public class PermissionInterceptor implements RequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);

    /**
     * 接口请求之前
     * @param info	接口信息
     * @param context	脚本变量信息
     */
    @Override
    public Object preHandle(ApiInfo info, MagicScriptContext context, MagicHttpServletRequest request, MagicHttpServletResponse response) throws Exception {
        User user = null; // = XXXUtils.getUser(request);
        logger.info("{} 请求接口：{}", user, info.getName());
        // 接口选项配置了需要登录
        if ("true".equals(info.getOptionValue(Options.REQUIRE_LOGIN))) {
            if (user == null) {
                return new JsonBean<>(401, "用户未登录");
            }
        }
        String role = info.getOptionValue(Options.ROLE);
//        if (StringUtils.isNotBlank(role) && user.hasRole(role)) {
//            return new JsonBean<>(403, "用户权限不足");
//        }
        String permission = info.getOptionValue(Options.PERMISSION);
//        if (StringUtils.isNotBlank(permission) && user.hasPermission(permission)) {
//            return new JsonBean<>(403, "用户权限不足");
//        }
        return null;
    }

    /**
     * 接口执行之后
     * @param info	接口信息
     * @param context	变量信息
     * @param value 即将要返回到页面的值
     */
    @Override
    public Object postHandle(ApiInfo info, MagicScriptContext context, Object value, MagicHttpServletRequest request, MagicHttpServletResponse response) throws Exception {
        logger.info("{} 执行完毕，返回结果:{}", info.getName(), value);
        return null;
    }

}
