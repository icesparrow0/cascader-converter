package cn.icesparrow.cascader;

import java.io.Serializable;

public class CascaderItemDTO implements Serializable {
    private static final long serialVersionUID = 9142480953808197737L;

    private String parent;

    private String label;

    private String value;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
