package cn.icesparrow.cascader;

public final class CascaderItemDTOBuilder {
    private final CascaderItemDTO cascaderItemDTO;

    private CascaderItemDTOBuilder() {
        cascaderItemDTO = new CascaderItemDTO();
    }

    public static CascaderItemDTOBuilder aCascaderItemDTO() {
        return new CascaderItemDTOBuilder();
    }

    public CascaderItemDTOBuilder withParent(String parent) {
        cascaderItemDTO.setParent(parent);
        return this;
    }

    public CascaderItemDTOBuilder withLabel(String label) {
        cascaderItemDTO.setLabel(label);
        return this;
    }

    public CascaderItemDTOBuilder withValue(String value) {
        cascaderItemDTO.setValue(value);
        return this;
    }

    public CascaderItemDTO build() {
        return cascaderItemDTO;
    }
}
