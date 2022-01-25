package com.chinadci.neo4j.dao;

import java.util.Date;

/**
 * 指标基本信息
 */
public class IndexWh {
    private Long id;
    private Long gradeId;
    private String code;
    private String name;
    private String displayname;
    private String description;
    private String unit;
    private String dataSource;
    private String updatecycle;
    private String granularity; //粒度
    private Double standardlow;
    private Double standardup;
    private String spacecode;
    private String spacetype;
    private String datasourcedes;
    private String conclusion;
    private Integer type;//指标类型：1越大越好  2越小越好  3其他
    private Long content;
    private Long indexcatalogmetaid;
    private Date updatetime;
    private String datatime;
    private Integer evaluatemethod;
    private Double score;
    private String grade;
    private Double weight;
    private Double normalization;
    private String nodeType;
    private Integer normalizationmethod;
    private Double value;
    private Integer rank;
    private String fpName;  //一级父类
    private String spName;  // 二级父类

    public String getFpName() {
        return fpName;
    }

    public void setFpName(String fpName) {
        this.fpName = fpName;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getSpacetype() {
        return spacetype;
    }

    public void setSpacetype(String spacetype) {
        this.spacetype = spacetype;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Long getContent() {
        return content;
    }

    public void setContent(Long content) {
        this.content = content;
    }

    public Long getIndexcatalogmetaid() {
        return indexcatalogmetaid;
    }

    public void setIndexcatalogmetaid(Long indexcatalogmetaid) {
        this.indexcatalogmetaid = indexcatalogmetaid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public Integer getEvaluatemethod() {
        return evaluatemethod;
    }

    public void setEvaluatemethod(Integer evaluatemethod) {
        this.evaluatemethod = evaluatemethod;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getNormalization() {
        return normalization;
    }

    public void setNormalization(Double normalization) {
        this.normalization = normalization;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public Integer getNormalizationmethod() {
        return normalizationmethod;
    }

    public void setNormalizationmethod(Integer normalizationmethod) {
        this.normalizationmethod = normalizationmethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getUpdatecycle() {
        return updatecycle;
    }

    public void setUpdatecycle(String updatecycle) {
        this.updatecycle = updatecycle;
    }

    public String getGranularity() {
        return granularity;
    }

    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }

    public Double getStandardlow() {
        return standardlow;
    }

    public void setStandardlow(Double standardlow) {
        this.standardlow = standardlow;
    }

    public Double getStandardup() {
        return standardup;
    }

    public void setStandardup(Double standardup) {
        this.standardup = standardup;
    }

    public String getSpacecode() {
        return spacecode;
    }

    public void setSpacecode(String spacecode) {
        this.spacecode = spacecode;
    }

    public String getDatasourcedes() {
        return datasourcedes;
    }

    public void setDatasourcedes(String datasourcedes) {
        this.datasourcedes = datasourcedes;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}