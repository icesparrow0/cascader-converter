package cn.icesparrow.cascader;

import java.lang.reflect.Method;

public class CascaderDefinition {

    private Method labelMethod;

    private Method valueMethod;

    private Method parentMethod;

    public Method getLabelMethod() {
        return labelMethod;
    }

    public void setLabelMethod(Method labelMethod) {
        this.labelMethod = labelMethod;
    }

    public Method getValueMethod() {
        return valueMethod;
    }

    public void setValueMethod(Method valueMethod) {
        this.valueMethod = valueMethod;
    }

    public Method getParentMethod() {
        return parentMethod;
    }

    public void setParentMethod(Method parentMethod) {
        this.parentMethod = parentMethod;
    }
}
