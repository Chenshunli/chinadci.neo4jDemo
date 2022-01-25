package com.chinadci.neo4j.mapper;


import com.chinadci.neo4j.dto.DataNodeDTO;
import com.chinadci.neo4j.dto.GraphNodeDTO;
import com.chinadci.neo4j.dao.IndexWh;
import com.chinadci.neo4j.dao.Relevant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface GraphdbMapper {

    /**
     * 获取指定表中实体列表
     * @param tableName
     * @return
     */
    List<GraphNodeDTO> getNodeList(@Param("tableName") String tableName, @Param("node") String node, @Param("field")String field);


    /**
     * 向关系表插入新的关系
     * @param relevant
     * @return
     */
    int insertRelevant(Relevant relevant);

    List<Map<String,Object>> getIndexDataInfo();

    /**
     * 读取关系数据库中数据
     */
    List<Map<String,Object>> getDataInfo(@Param("table") String table);

    List<IndexWh> getIndexWh();

    List<DataNodeDTO> getNodeFromDb(@Param("table") String table);

    List<Relevant> getDataRelationFromDb(@Param("node1")String node1,@Param("node2")String node2,@Param("table") String relTable);


}
