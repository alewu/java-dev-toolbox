package com.ale.tree;

import lombok.Data;

import java.util.List;

/**
 * @author alewu
 * @date 2021/3/28
 */
@Data
public class Node {

    private String id;

    private String parentId;

    private String name;

    private List<Node> children;

    public Node(String id, String name, String parentId) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public Node(String id, String name, Node parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.name = name;
    }


}
