package com.snowreplicator.common.constants;

public class CommonConst {

    // настройка выбора участников: все пользователи / участиники сайта
    public final static int USER_SEARCH_GROUP_ALL = 0; // все пользователи
    public final static int USER_SEARCH_GROUP_SITE = 1; // участники сайта

    public static int[] getUserSearchGroupModes() {
        return new int[]{USER_SEARCH_GROUP_ALL, USER_SEARCH_GROUP_SITE};
    }

    public static String getUserSearchGroupModeName(int searchGroupMode) {
        switch (searchGroupMode) {
            case USER_SEARCH_GROUP_ALL:
                return "user-search-group-all";
            case USER_SEARCH_GROUP_SITE:
                return "user-search-group-site";
        }
        return "";
    }

}
