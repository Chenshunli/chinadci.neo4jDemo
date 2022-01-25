package com.chinadci.neo4j.dao.repository;

import com.chinadci.neo4j.dao.entity.Node;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface NodeRepository extends Neo4jRepository<Node,Long> {

    @Query("MATCH (p:$startLabel),(o:$endLabel)" + "WHERE p.id = $start and o.id=$end"
            + "CREATE (p)-[r:$relationLabel{type:relation}]->(o)")
    void creatrRS(@Param("startLabel") String startLabel, @Param("start") Long start,
                  @Param("startLabel") String endLabel, @Param("startLabel") Long end,
                  @Param("startLabel") String relationLabel, @Param("startLabel") String relation);

}
