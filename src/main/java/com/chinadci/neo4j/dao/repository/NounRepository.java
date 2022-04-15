package com.chinadci.neo4j.dao.repository;

import com.chinadci.neo4j.dao.entity.Noun;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NounRepository extends Neo4jRepository<Noun,Long> {


}
