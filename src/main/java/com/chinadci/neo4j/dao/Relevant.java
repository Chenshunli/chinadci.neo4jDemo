package com.chinadci.neo4j.dao;

/**
 * 关联表实体类
 */
public class Relevant {

    private Long id;
    private String type1; //类型或表名
    private Long content1; //对应表的id
    private String type2; //类型或表名
    private Long content2; //对应表的id
    private Integer intensity; //关联强度
    private Integer direction; //0：无方向；1：正向（1→2）2：反向（2→1）
    private String rela;//节点关系

    public String getRela() {
        return rela;
    }

    public void setRela(String rela) {
        this.rela = rela;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public Long getContent1() {
        return content1;
    }

    public void setContent1(Long content1) {
        this.content1 = content1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public Long getContent2() {
        return content2;
    }

    public void setContent2(Long content2) {
        this.content2 = content2;
    }

    public Integer getIntensity() {
        return intensity;
    }

    public void setIntensity(Integer intensity) {
        this.intensity = intensity;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }
}
