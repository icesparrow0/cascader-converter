package cn.icesparrow.cascader;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class CascaderDefinitionUtil {

    private static final int CAPACITY = 128;

    private static final Map<String, CascaderDefinition> definitions = new HashMap<>(CAPACITY);

    private static final ReentrantLock lock = new ReentrantLock();

    public static CascaderDefinition getOptionObjDefinition(Object obj) {
        Class<?> clazz = obj.getClass();
        CascaderDefinition cascaderDefinition = definitions.get(clazz.getName());
        if (cascaderDefinition == null) {
            lock.lock();
            try {
                return innerFetchOptionObjDefinition(clazz);
            } finally {
                lock.unlock();
            }
        }
        return cascaderDefinition;
    }

    private static CascaderDefinition innerFetchOptionObjDefinition(Class<?> clazz) {
        CascaderDefinition cascaderDefinition = definitions.get(clazz.getName());
        if (cascaderDefinition != null) {
            return cascaderDefinition;
        }
        Method labelMethod = null;
        Method valueMethod = null;
        Method parentMethod = null;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            Cascader annotation = method.getAnnotation(Cascader.class);
            if (annotation == null) {
                continue;
            }
            CascaderFieldEnum field = annotation.value();
            if (CascaderFieldEnum.Label.equals(field)) {
                labelMethod = method;
            } else if (CascaderFieldEnum.Value.equals(field)) {
                valueMethod = method;
            } else if (CascaderFieldEnum.Parent.equals(field)) {
                parentMethod = method;
            }
        }
        if (labelMethod != null && valueMethod != null && parentMethod != null) {
            cascaderDefinition = new CascaderDefinition();
            cascaderDefinition.setLabelMethod(labelMethod);
            cascaderDefinition.setValueMethod(valueMethod);
            cascaderDefinition.setParentMethod(parentMethod);
            definitions.put(clazz.getName(), cascaderDefinition);
            return cascaderDefinition;
        }
        throw new RuntimeException(String.format("Cascader Convert Error, Class: %s Loss Label Or Value Cascader Annotation.", clazz.getName()));
    }

}
