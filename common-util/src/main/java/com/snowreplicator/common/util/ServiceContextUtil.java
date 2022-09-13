package com.snowreplicator.common.util;

import java.io.Serializable;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;

public class ServiceContextUtil {

    // получить id пользователя из сессии
    public static long getUserId() {
        ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
        return serviceContext != null ? serviceContext.getUserId() : 0;
    }

    // проверить значение атрибута в ServiceContext
    public static boolean isAttribute(String name) {
        ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
        if (serviceContext != null) {
            Boolean value = (Boolean) serviceContext.getAttribute(name);
            if (value != null) {
                return value;
            }
        }
        return false;
    }

    // удалить значение атрибута в ServiceContext
    public static boolean removeAttribute(String name) {
        ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
        if (serviceContext != null) {
            serviceContext.removeAttribute(name);
            return true;
        }
        return false;
    }

    // установить значение атрибута в ServiceContext
    public static boolean setAttribute(String name, Serializable value) {
        ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
        if (serviceContext != null) {
            serviceContext.setAttribute(name, value);
            return true;
        }
        return false;
    }

}
