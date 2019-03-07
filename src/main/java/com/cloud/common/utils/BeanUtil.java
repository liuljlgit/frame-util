package com.cloud.common.utils;

import com.cloud.common.exception.BusiException;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Bean 复制
 */
public class BeanUtil {
    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 空参数不会拷贝
     * @param source 数据源
     * @param target 拷贝到的数据体
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        if(source==null) return;
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    /**
     * 将source对象属性的值赋给target对象同属性名的属性
     * @param source 数据源
     * @param target 目标对象
     * @return
     */
    public static <T,B> T copyOwnerAndSuperProperties(B source,Class<T> target){
        try {
            return copyOwnerAndSuperProperties(source,target.newInstance());
        } catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
            logger.error("类型转换异常：",e);
            throw new BusiException("类型转换失败");
        }
    }

    /**
     * 将source对象属性的值赋给target对象同属性名的属性
     * @param source 值的对象
     * @param target 接收值的对象
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static <T,B> T copyOwnerAndSuperProperties(B source,T target) throws IllegalArgumentException, IllegalAccessException {
        List<Field> voFileds = ReflectUtil.allField(source.getClass());
        List<Field> modelFileds = ReflectUtil.allField(target.getClass());
        conpyField((B) source, (T) target, voFileds, modelFileds);
        return target;
    }

    static <T, B> void conpyField(B source, T target, List<Field> voFileds, List<Field> modelFileds) throws IllegalAccessException {
        for (Field voFiled : voFileds) {
            if (Modifier.isFinal(voFiled.getModifiers()))
                continue;
            voFiled.setAccessible(true);
            for (Field modelField : modelFileds) {
                if (Modifier.isFinal(modelField.getModifiers()))
                    continue;
                modelField.setAccessible(true);
                if (modelField.getName().equals(voFiled.getName())) {
                    boolean isSwap = modelField.getType().isPrimitive() || voFiled.getType().isPrimitive();
                    if (!isSwap) { // 如果存在基本类型，不判断类型
                        if (modelField.getType() != voFiled.getType()) {
                            continue;
                        }
                    }

                    Object voValue = voFiled.get(source);
                    if (voValue != null)
                        modelField.set(target, voValue);
                    break;
                }
            }
        }
    }

    /**
     * 空参数会拷贝
     * @param source 数据源
     * @param target 拷贝到的数据体
     */
    public static void copyProperties(Object source, Object target) {
        if(source==null) return;
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 创建新实体，并且把传入实体中的值拷贝到新实体的相同属性
     * @param <B>
     * @param clazz 目标实体的类型
     * @return
     * @throws Exception
     */
    public static <T, B> List<T> createBean(List<B> list, Class<T> clazz){
        if(CollectionUtils.isEmpty(list)) return Lists.newArrayList();
        List<T> collect = list.stream().map(b->createBean(b,clazz)).collect(Collectors.toList());
        return collect;
    }

    /**
     * 创建新实体，并且把传入实体中的值拷贝到新实体的相同属性
     * @param source 数据源
     * @param clazz 目标实体的类型
     * @return
     * @throws Exception
     */
    public static <T> T createBean(Object source,Class<T> clazz){
        if(Objects.isNull(source)) return null;
        T t = null;
        try {
            t = clazz.newInstance();
            copyOwnerAndSuperProperties(source, t);
        } catch (Exception e) {
            logger.error("实体 {} 转换为 {} 类型失败：{}",source,clazz,e);
            throw new BusiException("实体转换失败");
        }
        return t;
    }
}
