package cn.icesparrow.cascader;

import java.util.List;

public class CascaderOptionDTO {

    private String label;

    private String value;

    private List<CascaderOptionDTO> children;

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

    public List<CascaderOptionDTO> getChildren() {
        return children;
    }

    public void setChildren(List<CascaderOptionDTO> children) {
        this.children = children;
    }
}
