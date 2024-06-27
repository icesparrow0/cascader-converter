package cn.icesparrow.cascader;

import java.io.Serializable;

/**
 * @Author: IceSparrow
 * @Email: 814653435@qq.com
 * @Date: 2024/6/27 16:37
 */

public class RegionDTO implements Serializable {
    private static final long serialVersionUID = -5376981511839486821L;

    private String code;

    private String name;

    private String parentCode;

    @Cascader(CascaderFieldEnum.Value)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Cascader(CascaderFieldEnum.Label)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Cascader(CascaderFieldEnum.Parent)
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
