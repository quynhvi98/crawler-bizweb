package com.higgsup.bizwebcrawler.utils;

import org.apache.commons.collections.CollectionUtils;

import javax.persistence.Query;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tiep on 5/5/2017.
 */
public class JpaResultConverter {
    public static <T> T map(Class<T> type, Object[] tuple) {
        List<Class<?>> tupleTypes = new ArrayList<>();
        for(Object field : tuple){
            tupleTypes.add(field.getClass());
        }
        try {
            Constructor<T> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
            return ctor.newInstance(tuple);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> map(Class<T> type, List<Object[]> records) {
        List<T> result = new LinkedList<>();
        for (Object[] record : records) {
            result.add(map(type, record));
        }
        return result;
    }

    public static <T> List<T> getResultList(Query query, Class<T> type) {
        @SuppressWarnings("unchecked")
        List<Object[]> records = query.getResultList();
        return map(type, records);
    }

    public static <T> T getResult(Query query, Class<T> type) {
        @SuppressWarnings("unchecked")
        List<Object[]> records = query.getResultList();
        if (!CollectionUtils.isEmpty(records)) {
            return map(type, records.get(0));
        }
        return null;
    }
}
