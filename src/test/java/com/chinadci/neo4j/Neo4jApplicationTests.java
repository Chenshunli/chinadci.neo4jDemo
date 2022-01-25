package com.chinadci.neo4j;

import com.chinadci.neo4j.dao.entity.Person;
import com.chinadci.neo4j.dao.entity.PersonRelationShip;
import com.chinadci.neo4j.dao.repository.PersonRelationShipRepository;
import com.chinadci.neo4j.dao.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
class Neo4jApplicationTests {
	@Autowired
	PersonRepository personRepository;
	@Autowired
	PersonRelationShipRepository personRelationShipRepository;

	@Test
//	void contextLoads() {
//	}
	public void testCreate(){
		List idList = new ArrayList();
		idList.add(1);
		Iterable<Long> ids = idList;
		List<Person> byId = personRepository.findAllById(idList);
		byId.get(0);
//		List<PersonRelationShip> byIds =personRelationShipRepository.findAllById(idList);
//		byIds.get(0);

	}



}
