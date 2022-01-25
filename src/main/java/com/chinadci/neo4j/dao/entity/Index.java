package com.chinadci.neo4j.dao.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * 指标实体类
 */
@Data
@NodeEntity(label = "Index")
public class Index implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Property
    private String name;

    private String displayname;
    private String objId;

    public Index(String objId, String name, String displayname) {
        this.name = name;
        this.displayname = displayname;
        this.objId = objId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }
}
