package com.gm.wj.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Team BraveNiuNiu
 * @date 2021/7
 */
public class CastUtils {
    public static <T> List<T> objectConvertToList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if(obj instanceof List<?>)
        {
            for (Object o : (List<?>) obj)
            {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }
}
