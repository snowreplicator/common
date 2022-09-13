package com.snowreplicator.common.util;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.PortalSessionContext;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Locale;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static final String DISABLED = "_DISABLED";

    // получить пользователя из сессии
    public static long getUserId() {
        return getUserId(0);
    }

    // получить пользователя из сессии
    public static long getUserId(long defaultUserId) {
        HttpSession session = PortalSessionThreadLocal.getHttpSession();
        Long userId = null;
        if (session != null) {
            userId = (Long) session.getAttribute(WebKeys.USER_ID);
        }
        return userId != null ? userId : defaultUserId;
    }

    // получить Locale из сессии
    public static Locale getLocale() {
        HttpSession session = PortalSessionThreadLocal.getHttpSession();
        if (session != null) {
            User user = (User) session.getAttribute(WebKeys.USER);
            if (user != null) {
                return user.getLocale();
            }
        }
        return LocaleUtil.getDefault();
    }

    // получить параметр типа long из сессии
    public static long getSessionParam(String param, long defaultValue) {
        if (!ServiceContextUtil.isAttribute(param + DISABLED)) {
            HttpSession session = PortalSessionThreadLocal.getHttpSession();
            if (session != null) {
                Long value = (Long) session.getAttribute(param);
                if (value != null) {
                    return value;
                }
            }
        }
        return defaultValue;
    }

    // записать параметр типа long в сессию
    public static void setSessionParam(String param, long value) {
        HttpSession session = PortalSessionThreadLocal.getHttpSession();
        if (session != null) {
            session.setAttribute(param, value);
        }
    }

    // получить параметр типа boolean из сессии
    public static boolean getSessionParam(String param, boolean defaultValue) {
        if (!ServiceContextUtil.isAttribute(param + DISABLED)) {
            HttpSession session = PortalSessionThreadLocal.getHttpSession();
            if (session != null) {
                Boolean value = (Boolean) session.getAttribute(param);
                if (value != null) {
                    return value;
                }
            }
        }
        return defaultValue;
    }

    // записать параметр типа boolean в сессию
    public static void setSessionParam(String param, boolean value) {
        HttpSession session = PortalSessionThreadLocal.getHttpSession();
        if (session != null) {
            session.setAttribute(param, value);
        }
    }

    // удалить указанный аттрибут из сессии
    public static void removeSessionParam(String param) {
        HttpSession session = PortalSessionThreadLocal.getHttpSession();
        if (session != null) {
            session.removeAttribute(param);
        }
    }

    // записать сессию из ServiceContext в сессию текущего потока (если её нет)
    public static void setSessionThreadLocal(ServiceContext serviceContext) {
        HttpSession session = PortalSessionThreadLocal.getHttpSession();
        if (session == null && serviceContext != null) {
            String sessionId = CookieKeys.getCookie(serviceContext.getRequest(), CookieKeys.JSESSIONID);
            if (sessionId != null) {
                session = PortalSessionContext.get(sessionId);
                if (session != null) {
                    PortalSessionThreadLocal.setHttpSession(session);
                }
            }
        }
    }

}
