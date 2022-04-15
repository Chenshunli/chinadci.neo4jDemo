package com.chinadci.neo4j.dao.entity;

import lombok.Data;
//import org.neo4j.ogm.annotation.GeneratedValue;
//import org.neo4j.ogm.annotation.Id;
//import org.neo4j.ogm.annotation.Property;

import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;

import java.io.Serializable;
import java.util.Date;


@Data
@NodeEntity(label = "Policy")
public class Policy extends Node implements Serializable {
    @Property
    private String indexnum; // 索引号
    private String publishnum; // 发文号
    private String title; // 主题
    private String keywords; // 关键词
    private String organization; //机构
    private String districtname; //行政区划名称
    private String level; // 等级
    private Date publishdate; // 发布时间
    private Date abolishdate; // 废弃时间
    private Date createdate; //创建时间
    private String content; //详情
    private String url; // 网址
    private String tags; //标签
    private String genre;  //体裁

    public String getIndexnum() {
        return indexnum;
    }

    public void setIndexnum(String indexnum) {
        this.indexnum = indexnum;
    }

    public String getPublishnum() {
        return publishnum;
    }

    public void setPublishnum(String publishnum) {
        this.publishnum = publishnum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public Date getAbolishdate() {
        return abolishdate;
    }

    public void setAbolishdate(Date abolishdate) {
        this.abolishdate = abolishdate;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
