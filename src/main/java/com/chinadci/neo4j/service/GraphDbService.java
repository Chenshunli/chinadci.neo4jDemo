package com.chinadci.neo4j.service;

import com.chinadci.neo4j.dao.EntityNoun;
import com.chinadci.neo4j.dao.IndexWh;
import com.chinadci.neo4j.dao.Relevant;
import com.chinadci.neo4j.dao.ServiceResult;
import com.chinadci.neo4j.dao.entity.*;
import com.chinadci.neo4j.dao.repository.*;
import com.chinadci.neo4j.dto.GraphDataDTO;
import com.chinadci.neo4j.dto.GraphNodeDTO;
import com.chinadci.neo4j.mapper.GraphdbMapper;
import com.chinadci.neo4j.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GraphDbService {

    @Autowired
    RelationShipRepository relationShipRepository;
    @Autowired
    NodeRepository nodeRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PddkRepository pddkRepository;
    @Autowired
    GraphdbMapper graphdbMapper;
    @Autowired
    IndexRepository indexRepository;
    @Autowired
    PolicyRepository policyRepository;
    @Autowired
    NounRepository nounRepository;
    @Autowired
    AppUtil appUtil;

    /**
     * 图数据库中节点查询测试
     * @param ids
     * @return
     */
    public List findNodes(String label,List<Long> ids){
        List  res  = new ArrayList<>();
        String beanName = label+"Repository";
        Neo4jRepository neo4jRepository = (Neo4jRepository) appUtil.getBean(beanName);
        if(ids!=null&&ids.size()>0){
            res = neo4jRepository.findAllById(ids);
//            byId.get(0);
        }
        else res = neo4jRepository.findAll();
        return res;
    }


    /**
     * 动态选择具体repository
     * @param label
     * @return
     */
    private Neo4jRepository getRepository(String label){
        Neo4jRepository repository = null;
        switch (label){
            case "person":
                repository = personRepository;
                break;
            case "pddk":
                repository = pddkRepository;
                break;
            case "index":
                repository = indexRepository;
                break;
            case "policy":
                repository = policyRepository;
                break;
            case "noun":
                repository = nounRepository;
        }
        return repository;
    }


    /**
     * 通过关系数据库创建数据关联关系
     * @return
     */
    public ServiceResult createRelevant(String nodeType1, String nodeType2,
                                        String tableName1, String tableName2,
                                        String node1, String node2,
                                        String field1, String field2, String relation){
        List res = new ArrayList();
        List<Relevant> relevantList = new ArrayList<>();
        try{
            List<GraphNodeDTO> nodeList1 = graphdbMapper.getNodeList(tableName1,node1,field1);
            List<GraphNodeDTO> nodeList2 = graphdbMapper.getNodeList(tableName2,node2,field2);
            for (GraphNodeDTO graphNodeDTO1:nodeList1){
                Relevant relevant = new Relevant();
                for (GraphNodeDTO graphNodeDTO2:nodeList2){
                    if(graphNodeDTO1.getId().equals("3")&&graphNodeDTO2.getId().equals("920")){
                        System.out.println(graphNodeDTO1.getId()+'\n'+graphNodeDTO2.getId());
                    }
                    String text1 = graphNodeDTO1.getKeyValue().replaceAll("[\\pP‘’“”]","");
                    String text2 = graphNodeDTO2.getKeyValue().replaceAll("[\\pP‘’“”]","");
                    if(text1.equals(text2)){
                        relevant.setType1(nodeType1);
                        relevant.setContent1(Long.valueOf(graphNodeDTO1.getId().replaceAll(" +","")));
                        relevant.setType2(nodeType2);
                        relevant.setContent2(Long.valueOf(graphNodeDTO2.getId().replaceAll(" +","")));
                        relevant.setRela(relation);
                        graphdbMapper.insertRelevant(relevant);
                        res.add(relevant);
                        continue;
                    }
                }
            }
//        for ()
            return new ServiceResult(res);
        }catch (Exception e){
            return new ServiceResult(ServiceResult.ServiceErrorCode.UNKNOWN_ERROR, e.getMessage());
        }

    }





    /**
     * 图数据中节点创建1：通过自定义节点方式
     * @return
     */
    public ServiceResult createGraphData(String label, GraphDataDTO graphDataDTO){
        List res = new ArrayList();
        try{
            Object node = new Object();
            String beanName = label+"Repository";
            node = getNode(label,graphDataDTO);
//            Neo4jRepository neo4jRepository = getRepository(label);
            Neo4jRepository neo4jRepository = (Neo4jRepository) appUtil.getBean(beanName);
            neo4jRepository.save(node);
            res.add(node);
        }catch (Exception e){
            return new ServiceResult(ServiceResult.ServiceErrorCode.UNKNOWN_ERROR, e.getMessage());
        }
        return new ServiceResult(res);
    }


    /**
     * 根据传入参数构建具体节点
     * @param label
     * @param graphDataDTO
     * @return
     */
    public Object getNode(String label,GraphDataDTO graphDataDTO){
        Object node = new Object();
        Map<String,Object>  properties = graphDataDTO.getPropertyValues();
        String name = (String) properties.get("name");
        switch (label){
            case "person":
                Person person = new Person();
                Integer born = (Integer) properties.get("born");
                person.setName(name);
                person.setBorn(born);
                node = person;
                break;
            case "index":
                Index index = new Index();
                index.setId((Long) properties.get("id"));
                index.setName((String) properties.get("name"));
                node = index;
                break;
            case "noun":
                Noun noun = new Noun();
                noun.setId((Long) properties.get("id"));
                noun.setDisplayname((String) properties.get("displayName"));
                node = noun;
                break;
        }
        return node;
    }


    /**
     * 图数据库中数据创建
     * 从数据库导入数据到图数据库
     * @return
     */
    public ServiceResult pgToGraphDatabase(String label,String tableName){

        List res = new ArrayList();
        String beanName = label+"Repository";
        try{
            Neo4jRepository neo4jRepository = (Neo4jRepository) appUtil.getBean(beanName);
            if(tableName.equals("entity_noun")){
                List<EntityNoun> entityNouns = graphdbMapper.getEntityNouns();
                for (EntityNoun entityNoun:entityNouns){
                    Noun noun = (Noun) objToEntity(entityNoun,"noun");
//                    Noun noun = new Noun();
//                    noun.setDescription(entityNoun.getDiscription());
//                    noun.setDisplayname(entityNoun.getDisplayName());
//                    noun.setId(entityNoun.getId());
//                    noun.setTags(entityNoun.getTags());
                    nounRepository.save(noun);
                    res.add(noun);
                }
            }
            else if(tableName.equals("index")){
                List<IndexWh> indexWhList = graphdbMapper.getIndexWh();
                for (IndexWh indexWh: indexWhList){
                    Index index = (Index) objToEntity(indexWh,"index");
                    indexRepository.save(index);
                }
            }

        }catch (Exception e){
            res.add(e.getMessage());
        }
        return new ServiceResult(res);
    }

    /**
     * 关系数据库中数据转为图数据库实体
     * @param obj
     * @param entityClass
     * @return
     */
    private Object objToEntity(Object obj,String entityClass){
        Object entity = new Object();
        switch (entityClass){
            case "noun":
                EntityNoun entityNoun = (EntityNoun) obj;
                Noun noun = new Noun();
                noun.setDescription(entityNoun.getDiscription());
                noun.setDisplayname(entityNoun.getDisplayName());
                noun.setId(entityNoun.getId());
                noun.setTags(entityNoun.getTags());
                entity = noun;
                break;
            case "index":
                IndexWh indexWh = new IndexWh();
                Index index = new Index();
                index.setObjId(indexWh.getId());
                index.setName(indexWh.getName());
                index.setDisplayname(indexWh.getDisplayname());
                entity = index;
                break;
        }
        return entity;
    }


    /**
     * 图数据库关系创建1：自定义
     * @param
     * @param startNode
     * @param
     * @param endNode
     * @param relationLabel
     * @param relation
     * @return
     */
    public ServiceResult createRelationShip(String startLabel,String startNode,String endLabel,String endNode,
                                            String relationLabel,String relation){
        List res = new ArrayList();
        try{
//            根据仓库自带方法创建关系，失败！原因尚未知
//            PersonRelationShip  Ship = new PersonRelationShip();
//            Person from = new Person();
//            Person to = new Person();
//            from.setName(startNode);
//            to.setName(endNode);
//            Ship.setStart(from);
//            Ship.setEnd(to);
//            Ship.setRelation("同事");
////            PersonRelationShip personRelationShip = PersonRelationShip.builder().relation("同事").start(from).end(to).build();
//            personRelationShipRepository.save(Ship);
            String from = startNode;
            String to = endNode;
//            自写方法创建关系：成功！
            relationShipRepository.createRelation(from,relation,to);
            personRepository.createRelation(from,relation,to);
            //方式三：选择使用该方法
            nodeRepository.createRSByName(startLabel,startNode,endLabel,endNode,relationLabel,relation);
            res.add(startNode);
        }catch (Exception e){

        }
        return new ServiceResult(res);
    }


    /**
     * 图数据库关系创建2：根据关系数据库构建
     * @param nodeLabel1
     * @param nodeLabel2
     * @param relationLabel
     * @param relationTable
     * @return
     */
    public ServiceResult createRS(String nodeLabel1,String nodeLabel2,
                                            String relationLabel,String relationTable){
        try {
            List<Relevant> relevantList = graphdbMapper.getDataRelationFromDb(nodeLabel1,nodeLabel2,relationTable);
            for (Relevant relevant:relevantList){
                Long start = relevant.getContent1();
                Long end = relevant.getContent2();
                String startLabel = relevant.getType1();
                String endLabel = relevant.getType2();
                String relation = relevant.getRela();
                nodeRepository.createRSById(startLabel,start,endLabel,end,relationLabel,relation);
//                personRepository.creatrRS(startLabel,start,endLabel,end,relationLabel,relation);
            }
            return new ServiceResult(relevantList);
        }catch (Exception e){
            return new ServiceResult(ServiceResult.ServiceErrorCode.UNKNOWN_ERROR, e.getMessage());
        }
    }


    public ServiceResult createRSTest(String nodeLabel1,String nodeLabel2,
                                  String relationLabel,String relationTable){
        try {
            List<Relevant> relevantList = graphdbMapper.getDataRelationFromDb(nodeLabel1,nodeLabel2,relationTable);
            for (Relevant relevant:relevantList){
                Long start = relevant.getContent1();
                Long end = relevant.getContent2();
                String startLabel = relevant.getType1();
                String endLabel = relevant.getType2();
                String relation = relevant.getRela();
                personRepository.creatrRS(startLabel,start,endLabel,end,relationLabel,relation);
            }
            return new ServiceResult(relevantList);
        }catch (Exception e){
            return new ServiceResult(ServiceResult.ServiceErrorCode.UNKNOWN_ERROR, e.getMessage());
        }
    }


    /**
     * 图数据库中数据删除
     * @param labelName
     * @param id
     * @return
     */
    public ServiceResult deleteData(String labelName,Long id){
        List res = new ArrayList();
        try{
//            List resList = Collections.singletonList(indexRepository.findAll());
//            indexRepository.deleteAll(resList);
//            List resList = Collections.singletonList(pddkRepository.findAll());
            pddkRepository.findAll();
//            res.addAll((Collection) resList.get(0));
        }catch (Exception e){
            res.add(e.getMessage());
        }
        return new ServiceResult(res);
    }


    /**
     * 删除标签
     * @param label
     * @return
     */
    public ServiceResult deleteLabel(String label){
        List res = new ArrayList();
//        删除某类节点，以及它存在的所有关系
        Neo4jRepository neo4jRepository = getRepository(label);
        neo4jRepository.deleteAll();
        return new ServiceResult(res);
    }

    public ServiceResult getData(String labelName,Long id,Integer depth){
        List res = new ArrayList();
        String beanName = labelName+"Repository";
        Neo4jRepository neo4jRepository = (Neo4jRepository) appUtil.getBean(beanName);

        return new ServiceResult(res);
    }

}
