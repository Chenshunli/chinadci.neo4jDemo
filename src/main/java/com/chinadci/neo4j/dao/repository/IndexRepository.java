package com.chinadci.neo4j.dao.repository;

import com.chinadci.neo4j.dao.entity.Index;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IndexRepository extends Neo4jRepository<Index,Long> {
}
