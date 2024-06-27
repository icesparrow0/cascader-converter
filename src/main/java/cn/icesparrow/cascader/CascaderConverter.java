package cn.icesparrow.cascader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * cascader 前端无限层级解析通用组件.使用方式参考， 反射本身会做缓存，不会每次调用都进行反射。故相对还好一点。
 */
public class CascaderConverter {


    public static List<CascaderOptionDTO> convert(List<?> list) {

        if (list == null || list.size() == 0) {
            return Collections.emptyList();
        }
        Object obj = list.get(0);
        CascaderDefinition definition = CascaderDefinitionUtil.getOptionObjDefinition(obj);
        if (definition == null) {
            throw new RuntimeException("Cascader Convert Failed, Cascader Annotation Not Found.");
        }
        Method labelMethod = definition.getLabelMethod();
        Method valueMethod = definition.getValueMethod();
        Method parentMethod = definition.getParentMethod();
        List<CascaderItemDTO> cascaderItemDTOList = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            Object o = list.get(i);
            try {
                String label = (String) labelMethod.invoke(o);
                String value = (String) valueMethod.invoke(o);
                String parent = (String) parentMethod.invoke(o);
                CascaderItemDTO cascaderItemDTO = CascaderItemDTOBuilder.aCascaderItemDTO()
                        .withLabel(label)
                        .withValue(value)
                        .withParent(parent)
                        .build();
                cascaderItemDTOList.add(cascaderItemDTO);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        CascaderOptionDTO root = CascaderOptionDTOBuilder.aCascaderOptionDTO()
                .withValue("")
                .withChildren(new ArrayList<>())
                .build();
        innerConvert(root, cascaderItemDTOList);
        return root.getChildren();
    }

    // 这种算法会出现问题，当List中的孤立点会被遗漏，遗漏的话就属于运维问题，暂不考虑。

    private static void innerConvert(CascaderOptionDTO parent, List<CascaderItemDTO> itemDTOList) {
        List<CascaderItemDTO> children = itemDTOList.stream()
                .filter(item -> parent.getValue() != null && parent.getValue().equals(item.getParent())).collect(Collectors.toList());
        for (int i = 0; i < children.size(); i++) {
            CascaderItemDTO childItem = children.get(i);
            CascaderOptionDTO child = CascaderOptionDTOBuilder.aCascaderOptionDTO()
                    .withLabel(childItem.getLabel())
                    .withValue(childItem.getValue())
                    .withChildren(new ArrayList<>())
                    .build();
            parent.getChildren().add(child);
            innerConvert(child, itemDTOList);
        }
    }


}
