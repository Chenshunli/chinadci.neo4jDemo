package com.chinadci.neo4j.dao.entity;

import lombok.Builder;
import lombok.Data;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Relationship;
import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Data
//@Builder
@RelationshipEntity(type ="CompanyRel")
public class PersonRelationShip {
    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String relation;

    @StartNode
    private Person start;

    @EndNode
    private Person end;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Person getStart() {
        return start;
    }

    public void setStart(Person start) {
        this.start = start;
    }

    public Person getEnd() {
        return end;
    }

    public void setEnd(Person end) {
        this.end = end;
    }
}
