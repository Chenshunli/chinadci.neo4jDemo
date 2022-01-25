package com.chinadci.neo4j.dto;

/**
 * 关系节点实体映射
 */
public class GraphNodeDTO {
    private String id;
    private String keyValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }
}
