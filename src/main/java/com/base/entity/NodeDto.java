package com.base.entity;

import java.util.List;

/**
 * @author qiaozhang
 * @since 2018/6/11 11:24
 */

public class NodeDto implements java.io.Serializable {
    /**
     * id
     */
    private long id;
    /**
     * 节点名称
     */
    private String text;
    /**
     * 子节点
     */
    private List<NodeDto> nodes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<NodeDto> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeDto> nodes) {
        this.nodes = nodes;
    }
}

