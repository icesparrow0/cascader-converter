package cn.icesparrow.cascader;

import java.util.List;

public final class CascaderOptionDTOBuilder {
    private final CascaderOptionDTO cascaderOptionDTO;

    private CascaderOptionDTOBuilder() {
        cascaderOptionDTO = new CascaderOptionDTO();
    }

    public static CascaderOptionDTOBuilder aCascaderOptionDTO() {
        return new CascaderOptionDTOBuilder();
    }

    public CascaderOptionDTOBuilder withLabel(String label) {
        cascaderOptionDTO.setLabel(label);
        return this;
    }

    public CascaderOptionDTOBuilder withValue(String value) {
        cascaderOptionDTO.setValue(value);
        return this;
    }

    public CascaderOptionDTOBuilder withChildren(List<CascaderOptionDTO> children) {
        cascaderOptionDTO.setChildren(children);
        return this;
    }

    public CascaderOptionDTO build() {
        return cascaderOptionDTO;
    }
}
