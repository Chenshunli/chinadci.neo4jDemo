package com.chinadci.neo4j.dao.repository;

import com.chinadci.neo4j.dao.entity.PersonRelationShip;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRelationShipRepository extends Neo4jRepository<PersonRelationShip,Long> {
}
