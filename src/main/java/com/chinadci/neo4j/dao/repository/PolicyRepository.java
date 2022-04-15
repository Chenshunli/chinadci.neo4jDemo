package com.chinadci.neo4j.dao.repository;

import com.chinadci.neo4j.dao.entity.Policy;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends Neo4jRepository<Policy,Long> {

//    void creatrRS(@Param("startLabel") String startLabel, @Param("start") Long start,
//                  @Param("startLabel") String endLabel, @Param("startLabel") Long end,
//                  @Param("startLabel") String relationLabel, @Param("startLabel") String relation);

    /**
     * 根据节点名称查找关系
     * @param name
     * @return
     */
    @Query("MATCH c=(cf:CustomerNode)-[r:CustomerRelation]->(ct:CustomerNode) WHERE ct.name=$name OR cf.name=$name RETURN cf")
    List<Policy> findRelationByCustomerNode(String name);

    @Query("MATCH c=(cf:CustomerNode) WHERE cf.id=$id RETURN cf")
    Policy findNodeById(Long id);

    
}
