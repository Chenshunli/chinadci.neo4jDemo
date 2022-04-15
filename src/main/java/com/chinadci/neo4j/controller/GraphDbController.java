package com.chinadci.neo4j.controller;

import com.chinadci.neo4j.dao.ServiceResult;
import com.chinadci.neo4j.dto.GraphDataDTO;
import com.chinadci.neo4j.service.GraphDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "GraphDbController",tags={"图数据库相关接口"})
@RestController
@RequestMapping(produces = "application/json;charset=UTF-8")
public class GraphDbController {
    @Autowired
    GraphDbService graphDbService;

    @ApiOperation(value = "根据标签名称和节点id查询具体节点信息")
    @RequestMapping(value = "/getNodes", method = RequestMethod.GET)
    public ServiceResult getNodes(
            @RequestParam(name = "labelName") @ApiParam(value = "查询标签",required = true) String label,
            @RequestParam(name = "id",required = false) @ApiParam(value = "为空，则查询当前标签下所有节点") List<Long> ids){
//            return graphDbService.findNodes(ids);
        return new ServiceResult(graphDbService.findNodes(label,ids));
    }

    @ApiOperation(value = "节点及关系查询：当前查询节点所有相关节点和关系")
    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    public ServiceResult getAllData(
            @RequestParam(name = "labelName") @ApiParam(value= "查询标签",required = true) String labelName,
            @RequestParam(name = "id") @ApiParam(value= "节点id",required = true) Long id,
            @RequestParam(name = "depth",required = false) @ApiParam(value= "深度,查询层级") Integer depth
    ){
        return graphDbService.getData(labelName,id,depth);
    }


    @ApiOperation(value = "图数据库中数据节点创建1：通过自定义节点方式")
    @RequestMapping(value = "/createGraphData",method = RequestMethod.POST)
    public ServiceResult createGraphData(
            @RequestParam(name = "label") @ApiParam(value = "创建节点所属标签",required = true) String label,
            @RequestBody GraphDataDTO graphDataDTO
    ){
        return graphDbService.createGraphData(label,graphDataDTO);
    }

    @ApiOperation(value = "图数据库中数据节点创建2：通过关系数据库转换")
    @RequestMapping(value = "/createGD",method = RequestMethod.POST)
    public ServiceResult PgToGraphDatabase2(
            @RequestParam(name = "label") @ApiParam(value = "创建节点所属标签",required = true) String label,
            @RequestParam(name = "tableName") @ApiParam(value = "关系数据库中对应表名",required = true) String tableName
    ){
        return graphDbService.pgToGraphDatabase(label,tableName);
    }


    @ApiOperation(value = "relevant中数据关系创建")
    @RequestMapping(value = "/createRelevant",method = RequestMethod.POST)
    public ServiceResult createRelevant(
            @RequestParam(name = "nodeType1") @ApiParam(value = "实体类型1",required = true) String nodeType1,
            @RequestParam(name = "nodeType2") @ApiParam(value = "实体类型2",required = true) String nodeType2,
            @RequestParam(name = "tableName1") @ApiParam(value = "表1（实体1所在关系表名称）",required = true) String tableName1,
            @RequestParam(name = "tableName2") @ApiParam(value = "表2（实体2所在关系表名称）",required = true) String tableName2,
            @RequestParam(name = "node1") @ApiParam(value = "表1中实体标识",required = true) String node1,
            @RequestParam(name = "node2") @ApiParam(value = "表2中实体标识",required = true) String node2,
            @RequestParam(name = "field1") @ApiParam(value = "表1中关联字段名称",required = true) String filed1,
            @RequestParam(name = "field2") @ApiParam(value = "表2中关联字段名称",required = true) String filed2,
            @RequestParam(name = "relation") @ApiParam(value = "关系名称(实体1指向实体2)",required = true) String relation
    ){
        return graphDbService.createRelevant(nodeType1,nodeType2,tableName1,tableName2,node1,node2,filed1,filed2,relation);
    }


    @ApiOperation(value = "图数据库关系创建1：自定义")
    @RequestMapping(value = "/createRelationShip",method = RequestMethod.POST)
    public ServiceResult createRelation(
            @RequestParam(name = "startNodeLabel") @ApiParam(value = "节点标签",required = true) String label1,
            @RequestParam(name = "startNode" )@ApiParam(value = "开始节点",required = true) String startNode,
            @RequestParam(name = "endNodeLabel") @ApiParam(value = "结束节点标签",required = true) String label2,
            @RequestParam(name = "endNode") @ApiParam(value = "结束节点",required = true) String endNode,
            @RequestParam(name = "relationLabel") @ApiParam(value = "关系标签",required = true) String relationLabel,
            @RequestParam(name = "relationShip") @ApiParam(value = "关系名称",required = true) String relation
    ){
        return graphDbService.createRelationShip(label1,startNode,label2,endNode,relationLabel,relation);
    }


    @ApiOperation(value = "图数据库关系创建2：根据关系数据库构建")
    @RequestMapping(value = "/createRS",method = RequestMethod.POST)
    public ServiceResult createRelationShip(
            @RequestParam(name = "startNodeLabel") @ApiParam(value = "开始节点标签,与relevant表中名称一致",required = true) String label1,
            @RequestParam(name = "endNodeLabel") @ApiParam(value = "结束节点标签,与relevant表中名称一致",required = true) String label2,
            @RequestParam(name = "relationLabel") @ApiParam(value = "关系标签",required = true) String relationLabel,
            @RequestParam(name = "relationTable",required = false) @ApiParam(value = "关系名称") String relationTable
    ){
        return graphDbService.createRS(label1,label2,relationLabel,relationTable);
    }

    @ApiOperation(value = "图数据库数据节点删除")
    @RequestMapping(value = "/deleteData",method = RequestMethod.DELETE)
    public ServiceResult deleteData(
            @RequestParam(name = "label") @ApiParam(value = "创建节点所属标签",required = true) String label,
            @RequestParam(name = "id") @ApiParam(value = "节点id",required = true) Long id
    ){
        return graphDbService.deleteData(label,id);
    }

    @ApiOperation(value = "删除标签")
    @RequestMapping(value = "/deleteLabel",method = RequestMethod.DELETE)
    public ServiceResult deleteLabel(
            @RequestParam(name = "label") @ApiParam(value = "删除标签",required = true) String label
    ){
        return graphDbService.deleteLabel(label);
    }


}
