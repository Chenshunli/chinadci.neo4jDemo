package com.chinadci.neo4j.dto;

import java.util.List;
import java.util.Map;

/**
 * 图数据库通用节点实体类
 */
public class GraphDataDTO {
    //    节点名称
//    private String name;
    //    节点属性列表
    private List<String> properties;
    //    节点属性值列表
    private Map<String,Object> propertyValues;


    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }

    public Map<String, Object> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(Map<String, Object> propertyValues) {
        this.propertyValues = propertyValues;
    }
}
