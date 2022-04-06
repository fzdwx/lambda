package io.github.fzdwx.inf.common.web;

import cn.dev33.satoken.stp.StpUtil;
import io.github.fzdwx.inf.common.exc.ForbiddenException;
import io.github.fzdwx.inf.common.web.core.Context;
import io.github.fzdwx.inf.common.web.model.UserInfo;
import io.github.fzdwx.logic.domain.entity.UserEntity;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/4/4 21:49
 */
public class Web {

    private final static String USER = "user";

    public static String doLogin(final UserEntity user) {
        StpUtil.login(user.getId());

        Web.cacheUserToSession(user);

        return StpUtil.getTokenValue();
    }

    public static void cacheUserToSession(final UserEntity entity) {
        StpUtil.getSessionByLoginId(entity.getId()).set(USER, UserInfo.of(entity));
    }

    public static UserInfo getUserInfo() {
        final var user = Context.user();
        if (user != null) {
            return user;
        }

        final var sessionUser = StpUtil.getSession().get(USER);
        if (sessionUser == null) {
            throw new ForbiddenException("操作非法");
        }

        Context.user((UserInfo) sessionUser);
        return (UserInfo) sessionUser;
    }

    public static UserInfo getUserInfo(final Object loginId) {
        return (UserInfo) StpUtil.getSessionByLoginId(loginId).get(USER);
    }
}