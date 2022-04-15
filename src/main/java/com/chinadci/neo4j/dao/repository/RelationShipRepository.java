package com.chinadci.neo4j.dao.repository;

import com.chinadci.neo4j.dao.entity.PersonRelationShip;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationShipRepository extends Neo4jRepository<PersonRelationShip,Long> {
    @Query("MATCH (p:Person{name:$from}),(o:Person{name:$to})" + "CREATE (p)-[r:CompanyRel{relation:$relation}]->(o);")
    void createRelation(@Param("from") String from, @Param("relation") String relation, @Param("to") String to);

}
