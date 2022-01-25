package com.chinadci.neo4j.dao.entity;

import lombok.Data;
//import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.io.Serializable;

@Data
//@NodeEntity(label = "Person")
@Node(labels = "Person")
public class Person extends com.chinadci.neo4j.dao.entity.Node implements Serializable {
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @Property
//    private String name;
    @Property
    private Integer born;

}
