package com.chinadci.neo4j.dao.repository;

import com.chinadci.neo4j.dao.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends Neo4jRepository<Person,Long> {
    @Query("MATCH (p:Person{name:$from}),(o:Person{name:$to})" + "CREATE (p)-[r:CompanyRel{relation:$relation}]->(o);")
    void createRelation(@Param("from") String from,@Param("relation") String relation, @Param("to") String to);

    @Query("MATCH (p:$startLabel),(o:$endLabel)" + "WHERE p.id = $start and o.id=$end"
            + "CREATE (p)-[r:$relationLabel{type:relation}]->(o)")
    void creatrRS(@Param("startLabel") String startLabel, @Param("start") Long start,
                  @Param("startLabel") String endLabel,@Param("startLabel") Long end,
                  @Param("startLabel") String relationLabel,@Param("startLabel") String relation);
}
