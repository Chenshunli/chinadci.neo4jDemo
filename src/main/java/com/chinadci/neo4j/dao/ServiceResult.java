package com.chinadci.neo4j.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 服务返回结果
 */
@JSONType(orders = { "code", "message", "pageNum", "pageSize", "total", "count",
    "metadata", "summary", "style", "data" })
public class ServiceResult implements Serializable {

    /**
     * 服务返回错误码枚举
     */
    public enum ServiceErrorCode {
        /**
         * 以下常见错误类型，可根据具体情况扩充新类型
         */
        SERVICE_SUCCESS(0, null),
        UNKNOWN_ERROR(1000, "未知异常"),
        NETWORK_ERROR(1001, "网络异常"),
        DATABASE_ERROR(1002, "数据库异常"),
        INVALID_METHOD(1003, "无效方法名"),
        INVALID_PARAMETER(1004, "无效参数名"),
        INVALID_TIMEDATE(1005, "无效时间类型"),
        DATA_ERROR(1006, "无效传入数据"),
        INTERNAL_ERROR(1007, "内部错误"),
        WARN_INVALID(1008, "警告操作无效"),
        NOT_AUTHENTICATED(1009, "身份未认证"),
        UNAUTHORIZED(1010, "权限不足"),
        RUNTIME_ERROR(1011, "运行时错误");

        private int code;
        private String message;

        private ServiceErrorCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * 字段
     */
    private class Field implements Serializable {
        private String fieldkey;
        private String fieldname;
        private String unit;

        public Field(String fieldkey, String fieldname, String unit) {
            this.fieldkey = fieldkey;
            this.fieldname = fieldname;
            this.unit = unit;
        }

        public Field(String fieldkey, String fieldname) {
            this.fieldkey = fieldkey;
            this.fieldname = fieldname;
        }

        public String getFieldkey() {
            return fieldkey;
        }

        public void setFieldkey(String fieldkey) {
            this.fieldkey = fieldkey;
        }

        public String getFieldname() {
            return fieldname;
        }

        public void setFieldname(String fieldname) {
            this.fieldname = fieldname;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Field && ((fieldkey == null && ((Field) obj).getFieldkey() == null) ||
                    fieldkey != null && fieldkey.equals(((Field) obj).getFieldkey()));
        }
    }

    @JSONType(orders= {"displayname", "lastupdate", "fields", "dimension"})
    private class Metadata implements Serializable {
        private String displayname;
        private String lastupdate;
        private List<Field> fields;
        private List<String> dimension;

        public String getDisplayname() {
            return displayname;
        }

        public void setDisplayname(String displayname) {
            this.displayname = displayname;
        }

        public String getLastupdate() {
            return lastupdate;
        }

        public void setLastupdate(String lastupdate) {
            this.lastupdate = lastupdate;
        }

        public synchronized void addField(Field field) {
            if (field == null) return;

            if (fields == null) {
                fields = new ArrayList<>();
            }
            fields.add(field);
        }

        public synchronized void removeField(Field field) {
            if (fields == null || field == null) return;
            fields.remove(field);
            if (fields.size() == 0) {
                fields = null;
            }
        }

        public void removeField(String fieldkey) {
            removeField(new Field(fieldkey, null));
        }

        public List<Field> getFields() {
            return fields;
        }

        public List<String> getDimension() {
            return dimension;
        }

        public void setDimension(List<String> dimension) {
            this.dimension = dimension;
        }
    }

    /**
     * 服务返回码，服务正常返回为0
     */
    private Integer code = 0;

    /**
     * 服务返回信息
     */
    private String message = "";

    /**
     * 服务返回分页信息 - 当前页码
     */
    private Integer pageNum;

    /**
     * 服务返回分页信息 - 每页数量
     */
    private Integer pageSize;

    /**
     * 服务返回经过筛选前的总数
     */
    private Long total;

    /**
     * 服务返回数据内容的记录总数
     */
    private Long count;

    /**
     * 当前服务返回数据的元数据信息
     */
    private List<?> metadata;

    /**
     * 当前服务返回数据的概要信息
     */
    private List<?> summary;

    /**
     * 当前服务返回数据的样式
     */
    private Map<String, ?> style;

    /**
     * 服务返回数据内容
     */
    private List<?> data;

    public ServiceResult(){ }


    public ServiceResult(List<?> data) {
        this.data = data;
        if (data != null) {
            count = (long) data.size();
        }
    }

    public ServiceResult(PageInfo<?> pageInfo) {
        this.data = pageInfo.getList();
        pageNum = pageInfo.getPageNum();
        pageSize = pageInfo.getPageSize();
        count = pageInfo.getTotal();
    }

    public ServiceResult(List<?> metadata, List<?> data) {
        this.metadata = metadata;
        this.data = data;
        if (data != null) {
            count = (long) data.size();
        }
    }

    public ServiceResult(Map<String, ?> style, List<?> data) {
        this.style = style;
        this.data = data;
        if (data != null) {
            count = (long) data.size();
        }
    }
    
    public ServiceResult(List<?> metadata, Map<String, ?> style, List<?> data) {
        this.metadata = metadata;
        this.style = style;
        this.data = data;
        if (data != null) {
            count = (long) data.size();
        }
    }

    public ServiceResult(List<?> metadata, PageInfo<?> pageInfo) {
        this.metadata = metadata;
        this.data = pageInfo.getList();
        pageNum = pageInfo.getPageNum();
        pageSize = pageInfo.getPageSize();
        count = pageInfo.getTotal();
    }

    public ServiceResult(String message, List<?> data) {
        this.message = message;
        this.data = data;
        if (data != null) {
            count = (long) data.size();
        }
    }

    public ServiceResult(List<?> data, Long total) {
        this.total = total;
        this.data = data;
        if (data != null) {
            count = (long) data.size();
        }
    }

    public ServiceResult(ServiceErrorCode errorCode) {
        code = errorCode.getCode();
        message = errorCode.getMessage();
    }

    public ServiceResult(ServiceErrorCode errorCode, String errorDetails) {
        code = errorCode.getCode();
        if (errorDetails != null) {
            if (errorCode.getMessage() == null || errorCode.getMessage().isEmpty()) {
                message = errorDetails;
            } else {
                message = errorCode.getMessage() + "：" + errorDetails;
            }
        }
    }

    public ServiceResult(ServiceErrorCode errorCode, String errorDetails, List<?> data) {
        code = errorCode.getCode();
        if (errorDetails != null) {
            if (errorCode.getMessage() == null || errorCode.getMessage().isEmpty()) {
                message = errorDetails;
            } else {
                message = errorCode.getMessage() + "：" + errorDetails;
            }
        }
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<?> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<?> metadata) {
        this.metadata = metadata;
    }

    public List<?> getSummary() {
        return summary;
    }

    public void setSummary(List<?> summary) {
        this.summary = summary;
    }

    public Map<String, ?> getStyle() {
        return style;
    }
    
    public void setStyle(Map<String, ?> style) {
        this.style = style;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    /**
     * 元数据相关：设定元数据显示名
     * @param displayName 显示名
     */
    public void setMetadataDisplayname(String displayName) {
        Metadata mdata = null;
        if (metadata == null || metadata.size() == 0) {
            mdata = new Metadata();
            metadata = Collections.singletonList(mdata);
        } else if (metadata.get(0) instanceof Metadata) {
            mdata = (Metadata) metadata.get(0);
        }
        if (mdata != null) {
            mdata.setDisplayname(displayName);
        }
    }

    /**
     * 元数据相关：设定元数据最后更新时间
     * @param lastupdate 最后更新时间
     */
    public void setMetadataLastupdate(String lastupdate) {
        Metadata mdata = null;
        if (metadata == null || metadata.size() == 0) {
            mdata = new Metadata();
            metadata = Collections.singletonList(mdata);
        } else if (metadata.get(0) instanceof Metadata) {
            mdata = (Metadata) metadata.get(0);
        }
        if (mdata != null) {
            mdata.setLastupdate(lastupdate);
        }
    }

    /**
     * 元数据相关：设定元数据维度数组
     * @param dimension 维度数组
     */
    public void setMetadataDimension(List<String> dimension) {
        if (dimension != null) {
            Metadata mdata = null;
            if (metadata == null || metadata.size() == 0) {
                mdata = new Metadata();
                metadata = Collections.singletonList(mdata);
            } else if (metadata.get(0) instanceof Metadata) {
                mdata = (Metadata) metadata.get(0);
            }
            if (mdata != null) {
                mdata.setDimension(dimension);
            }
        }
    }

    /**
     * 元数据相关：增加元数据字段
     * @param fieldkey 字段key
     * @param fieldname 字段名称
     * @param unit 字段单位
     */
    public void addMetadataField(String fieldkey, String fieldname, String unit) {
        Metadata mdata = null;
        if (metadata == null || metadata.size() == 0) {
            mdata = new Metadata();
            metadata = Collections.singletonList(mdata);
        } else if (metadata.get(0) instanceof Metadata) {
            mdata = (Metadata) metadata.get(0);
        }
        if (mdata != null) {
            mdata.addField(new Field(fieldkey, fieldname, unit));
        }
    }

    /**
     * 元数据相关：增加元数据字段
     * @param fieldkey 字段key
     * @param fieldname 字段名称
     */
    public void addMetadataField(String fieldkey, String fieldname) {
        addMetadataField(fieldkey, fieldname, null);
    }

    /**
     * 元数据相关：移除元数据字段
     * @param fieldkey 字段key
     */
    public void removeMetadataField(String fieldkey) {
        if (metadata != null && metadata.size() > 0 && metadata.get(0) instanceof Metadata) {
            Metadata mdata = (Metadata) metadata.get(0);
            mdata.removeField(fieldkey);
        }
    }

    /**
     * 将数据中部分属性多维度化
     * @param dimenProperties 多维度化的属性数组
     */
    public void dimensionalize(List<String> dimenProperties) {
        dimensionalize(dimenProperties, false);
    }

    /**
     * 将数据中部分属性多维度化
     * @param dimenProperties 多维度化的属性数组
     * @param retainProperty 是否保留原属性
     */
    public void dimensionalize(List<String> dimenProperties, boolean retainProperty) {
        if (data == null) return;

        List<Object> newData = new ArrayList<>();
        Map<String, Object> newElement;
        for (Object element : data) {
            newElement = getMapObject(element);
            if (newElement == null) continue;

            List<Map<String, Object>> children = new ArrayList<>();
            for (String property : dimenProperties) {
                if (property != null && newElement.containsKey(property)) {
                    Map<String, Object> child = new LinkedHashMap<>();
                    child.put("key", property);
                    child.put("value", newElement.get(property));

                    // 如果有元数据，则处理元数据
                    if (metadata != null && metadata.size() > 0 && metadata.get(0) != null) {
                        Map<String, Object> metaData = getMapObject(metadata.get(0));
                        if (metaData != null && metaData.containsKey("fields") && metaData.get("fields") instanceof List) {
                            List found = (List) ((List) metaData.get("fields")).stream().filter(
                                    o-> o != null &&
                                    o instanceof Map &&
                                    ((Map)o).containsKey("fieldkey") &&
                                    property.equals(((Map)o).get("fieldkey"))).collect(Collectors.toList());
                            if (found.size() > 0) {
                                Map field = (Map)found.get(0);
                                child.put("name", field.get("fieldname"));
                                if (field.containsKey("unit")) {
                                    child.put("unit", field.get("unit"));
                                }
                            }
                        }
                    }

                    children.add(child);

                    // 如果不保留属性，则移除属性
                    if (!retainProperty) {
                        newElement.remove(property);
                    }
                }
            }
            newElement.put("children", children);
            newData.add(newElement);
        }

        // 如果不保留属性，则从元数据中删除
        if (!retainProperty) {
            dimenProperties.stream().forEach(this::removeMetadataField);
        }

        data = newData;
        setMetadataDimension(Arrays.asList(new String[]{"name", "value"}));
    }

    private Map<String, Object> getMapObject(Object obj) {
        return (obj instanceof Map) ?
                (Map<String, Object>) obj :
                (Map<String, Object>) JSONObject.parse(JSONObject.toJSONStringWithDateFormat(obj, JSON.DEFFAULT_DATE_FORMAT));
    }

}