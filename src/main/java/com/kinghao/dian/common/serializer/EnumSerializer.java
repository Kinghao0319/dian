package com.kinghao.dian.common.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.kinghao.dian.common.CommonException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.security.InvalidParameterException;
import java.util.Map;

/**
 * @author kinghao
 * @version 2020/8/1 14:43
 */
@Slf4j
@SuppressWarnings("all")
public class EnumSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {
        if(o == null){
            jsonSerializer.write(o);
            return;
        }
        Class clazz = o.getClass();
        if (!clazz.isEnum()) {
            log.error("serialize error: ",new InvalidParameterException());
            throw new InvalidParameterException();
        }

        Method method;
        try {
            method = clazz.getMethod("toMap");
        } catch (NoSuchMethodException e) {
            log.error("serialize error: method toMap() not defined",e);
            throw new CommonException(null,e);
        }

        Map<String,Object> map = null;
        try {
            method.setAccessible(true);
            map = (Map<String,Object>)method.invoke(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            log.error("serialize error: method toMap() invoke failed",e);
            throw new CommonException(null,e);
        }
        jsonSerializer.write(map);
    }
}
