package org.gy.framework.demoutil.convert;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.gy.framework.demoutil.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述：bean之间属性复制
 *
 * @author gy
 */
public class BeanUtil {

    /**
     * 记录日志的变量
     */
    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    private static final Map<String, BeanCopier> mapCaches = new ConcurrentHashMap<>();

    private BeanUtil() {

    }

    public static void main(String[] args) {
        int times = 100000;

        Response<String> response1 = new Response<>();
        response1.setResult(String.valueOf(System.currentTimeMillis()));
        Response<String> response2 = new Response<>();
        BeanUtil.copy(response1, response2);

        doCopy(times, new BeanCopy() {
            @Override
            public void copy(Response<String> from, Response<String> to) {
                copyProperties(from, to);
            }
        }, "org.apache.commons.beanutils.BeanUtils.copyProperties");

        doCopy(times, new BeanCopy() {
            @Override
            public void copy(Response<String> from, Response<String> to) {
                try {
                    PropertyUtils.copyProperties(to, from);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }, "org.apache.commons.beanutils.PropertyUtils.copyProperties");

        doCopy(times, new BeanCopy() {
            @Override
            public void copy(Response<String> from, Response<String> to) {
                BeanUtils.copyProperties(from, to);
            }
        }, "org.springframework.beans.BeanUtils.copyProperties");

        doCopy(times, new BeanCopy() {
            @Override
            public void copy(Response<String> from, Response<String> to) {
                BeanUtil.copy(from, to);
            }
        }, "org.springframework.cglib.beans.BeanCopier");

    }

    static void doCopy(Integer times, BeanCopy beanCopy, String type) {
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < times; j++) {
            Response<String> response1 = new Response<>();
            response1.setResult(String.valueOf(System.currentTimeMillis()));
            Response<String> response2 = new Response<>();
            beanCopy.copy(response1, response2);
        }
        System.out.printf("执行%d次用时%dms---------%s%n", times, System.currentTimeMillis() - startTime, type);
    }

    interface BeanCopy {
        void copy(Response<String> from, Response<String> to);
    }

    /**
     * 功能描述：属性复制（只拷贝名称和类型都相同的属性）
     *
     * @param srcObj
     * @param destObj
     * @return void
     * @author gy
     * @version 1.0.0
     */
    public static <S, T> void copy(S srcObj, T destObj) {
        if (srcObj == null || destObj == null) {
            return;
        }
        String key = genKey(srcObj.getClass(), destObj.getClass());
        BeanCopier copier = null;
        try {
            if ((copier = mapCaches.get(key)) == null) {
                copier = BeanCopier.create(srcObj.getClass(), destObj.getClass(), false);
                mapCaches.put(key, copier);
            }
            copier.copy(srcObj, destObj, null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 功能描述：组装缓存key
     *
     * @param srcClazz  源类
     * @param destClazz 目标类
     * @return java.lang.String
     * @author gy
     */
    private static <S, T> String genKey(Class<S> srcClazz, Class<T> destClazz) {
        return srcClazz.getName() + destClazz.getName();
    }

    /**
     * 将一个 JavaBean 对象转化为一个 Map
     *
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的 Map 对象
     */
    public static Map<String, Object> convertBean(Object bean) {
        Map<String, Object> returnMap = new HashMap<>();
        if (bean == null) {
            return returnMap;
        }
        try {
            Class type = bean.getClass();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!"class".equals(propertyName)) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean);

                    returnMap.put(propertyName, result);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return returnMap;
    }

    /**
     * 将一个 Map 对象转化为一个 JavaBean
     *
     * @param type 要转化的类型
     * @param map  包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     */
    public static <T> T convertMap(Class<T> type,
                                   Map<String, Object> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        try {
            // 获取类属性
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            // 创建 JavaBean 对象
            T obj = type.newInstance();

            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();

                if (map.containsKey(propertyName)) {
                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                    Object value = map.get(propertyName);

                    Object[] args = new Object[1];
                    args[0] = value;

                    descriptor.getWriteMethod().invoke(obj, args);
                }
            }
            return obj;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 功能描述: bean属性复制<br>
     * 〈功能详细描述〉
     *
     * @param source 源bean
     * @param target 目标bean
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Deprecated
    public static void copyProperties(Object source,
                                      Object target) {
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 功能描述: bean属性复制<br>
     * 〈功能详细描述〉
     *
     * @param source           源bean
     * @param target           目标bean
     * @param ignoreProperties 要忽略的属性
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Deprecated
    public static void copyProperties(Object source,
                                      Object target,
                                      String[] ignoreProperties) {
        BeanUtils.copyProperties(source, target, ignoreProperties);
    }

    /**
     * 功能描述:list复制 <br>
     * 〈功能详细描述〉
     *
     * @param sourList 源list
     * @param clazz    目标list中元素类型class
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <S, T> List<T> copyList(List<S> sourList,
                                          Class<T> clazz) {
        List<T> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(sourList)) {
            return list;
        }
        for (S sObj : sourList) {
            try {
                T dObj = clazz.newInstance();
                copyProperties(sObj, dObj);
                list.add(dObj);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return list;
    }

    /**
     * 对象转map
     *
     * @param list
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, Object>[] obj2Map(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap[0];
        } else {
            Map<String, Object>[] rst = new HashMap[list.size()];
            for (int i = 0; i < list.size(); i++) {
                T t = list.get(i);
                Map<String, Object> m = BeanUtil.convertBean(t);
                rst[i] = m;
            }
            return rst;
        }

    }

}
