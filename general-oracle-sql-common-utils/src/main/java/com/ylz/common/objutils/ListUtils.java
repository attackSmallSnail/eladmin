package com.ylz.common.objutils;

import java.util.List;

public class ListUtils {

    public static boolean isEmpty(List list) {
        if (list == null || isAllNull(list)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }

    private static boolean isAllNull(List list) {
        if (list != null && !list.isEmpty()) {
            int count = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == null) {
                    count++;
                }
            }
            if (count == list.size()) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean isNotAllNull(List list) {
        return !isAllNull(list);
    }
}
