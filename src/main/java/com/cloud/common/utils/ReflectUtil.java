package com.cloud.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * 反射工具类
 *      反射操作思想：
 *          1.根据类全路径名/对象获取对应的Class对象
 *          2.Class对象可以获取构造函数、属性、方法、注解等
 *          3.反射执行的话一般就行获得Method、Filed等对象，然后传入obj执行
 *          4.通过Class对象获取构造函数可以使用newInstance()创建新对象
 * @author lijun
 */
public class ReflectUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

    /**
     * 得到某个bean的值
     * @param pojo
     * @param fieldName
     * @return
     * @throws Exception
     */
    public static Object getObjField(Object pojo, String fieldName) throws Exception {
        if (StringUtils.isEmpty(fieldName)) {
            throw new Exception("errorField");
        }
        StringBuilder getName = new StringBuilder("get");
        getName.append(fieldName.substring(0, 1).toUpperCase());
        getName.append(fieldName.substring(1));
        Method getMethod = pojo.getClass().getMethod(getName.toString());
        return getMethod.invoke(pojo);
    }

    /**
     * 设置某个bean的值
     * @param pojo
     * @param fieldName
     * @param valType
     * @param val
     * @param <T>
     * @throws Exception
     */
    public static <T> void setObjField(Object pojo, String fieldName, Class<T> valType, T val) throws Exception {
        if(StringUtils.isEmpty(fieldName)){
            throw new Exception("errorField");
        }
        StringBuilder setName = new StringBuilder("set");
        setName.append(fieldName.substring(0, 1).toUpperCase());
        setName.append(fieldName.substring(1));
        Method setMethod = pojo.getClass().getMethod(setName.toString(), valType);
        setMethod.invoke(pojo, val);
    }

    /**
     * bean属性值拷贝，null不处理
     * @param src 源bean
     * @param obj 目标bean
     * @throws Exception
     */
    public static <T> void copyProp(T src, T obj){
        if(src.getClass() != obj.getClass()){
            return;
        }
        try {
            Class<?> clazz = src.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if(!methodName.startsWith("get")){
                    continue;
                }
                Object srcBean = method.invoke(src);
                if(null == srcBean){
                    continue;
                }
                String beanName = methodName.substring(3);
                Class<?> paramType = method.getReturnType();
                Method setMethod = clazz.getMethod("set"+beanName, paramType);
                setMethod.invoke(obj, srcBean);
            }
        } catch (Exception e) {
            logger.debug("复制对象属性失败", e);
            return;
        }
    }

    /**
     * 判断两个对象的属性列表内容是否相等
     * @param aClass
     * @param comparePropList
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean objPropIsSame(Class<?> aClass,String[] comparePropList,Object obj1, Object obj2) throws Exception {
        for(int i=0;i<comparePropList.length;i++){
            String propName = comparePropList[i];
            Field field = aClass.getDeclaredField(propName);
            field.setAccessible(true);
            Object o1Value = field.get(obj1);
            Object o2Value = field.get(obj2);
            if(Objects.isNull(o1Value) && Objects.isNull(o2Value)){
                return true;
            }
            if(!Objects.isNull(o1Value) && Objects.isNull(o2Value)){
                return false;
            }
            if(Objects.isNull(o1Value) && !Objects.isNull(o2Value)){
                return false;
            }
            if(o1Value instanceof String){
                String o1 = (String)o1Value;
                String o2 = (String)o2Value;
                if(!o1.equals(o2)){
                    return false;
                }
            }else if(o1Value instanceof BigDecimal){
                BigDecimal o1 = (BigDecimal)o1Value;
                BigDecimal o2 = (BigDecimal)o2Value;
                if(o1.compareTo(o2) != 0){
                    return false;
                }
            }else if(o1Value instanceof Integer){
                Integer o1 = (Integer)o1Value;
                Integer o2 = (Integer)o2Value;
                if(o1.compareTo(o2) != 0){
                    return false;
                }
            }else if(o1Value instanceof Long){
                Long o1 = (Long)o1Value;
                Long o2 = (Long)o2Value;
                if(o1.compareTo(o2) != 0){
                    return false;
                }
            }else if(o1Value instanceof Byte){
                Byte o1 = (Byte)o1Value;
                Byte o2 = (Byte)o2Value;
                if(o1.compareTo(o2) != 0){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 获取对象的所有Field，包含所有超类
     * @param clazz
     * @return
     */
    public static List<Field> allField(Class<?> clazz){
        Set<Field> rsList = new LinkedHashSet<>();
        Field[] fields = clazz.getDeclaredFields();
        if(fields!=null&&fields.length>0){
            for(Field f:fields){
                rsList.add(f);
            }
        }
        superField(clazz,rsList);

        List<Field> rs = new LinkedList<>();
        rs.addAll(rsList);
        return rs;
    }

    private static void superField(Class<?> clazz,Set<Field> rsList){
        Set<Field> tempSet = new LinkedHashSet<>();
        Class<?> superClass = clazz.getSuperclass();
        Field[] superFields = superClass.getDeclaredFields();
        if(superFields!=null&&superFields.length>0){
            for(Field f:superFields){
                tempSet.add(f);
            }
            tempSet.addAll(rsList);
            rsList.removeAll(rsList);
            rsList.addAll(tempSet);
            superField(superClass,rsList);
        }
    }

    /**
     * 将model对象属性的值赋给vo对象同属性名的属性
     * @param model 接收值的对象
     * @param vo 值的对象
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static <T,B> T modelFromVo(T model, B vo) throws IllegalArgumentException, IllegalAccessException {
        List<Field> voFileds = allField(vo.getClass());
        List<Field> modelFileds = allField(model.getClass());

        BeanUtil.conpyField((B) vo, (T) model, voFileds, modelFileds);

        return model;
    }

}
