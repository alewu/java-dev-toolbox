package com.ale.tree;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TreeBuilder {

    /**
     * 两层循环实现建树
     *
     * @param nodes 传入的树节点列表
     * @return list
     */
    public static List<Node> bulid(List<Node> nodes) {

        List<Node> trees = new ArrayList<>();

        for (Node node : nodes) {

            if ("0".equals(node.getParentId())) {
                trees.add(node);
            }

            for (Node it : nodes) {
                if (Objects.equals(it.getParentId(), node.getId())) {
                    List<Node> children = Optional.ofNullable(node.getChildren()).orElse(new ArrayList<>());
                    children.add(it);
                    node.setChildren(children);
                }
            }
        }
        return trees;
    }

    public static void main(String[] args) {
        Node Node1 = new Node("1", "广州", "0");
        Node Node2 = new Node("2", "深圳", "0");

        Node Node3 = new Node("3", "天河区", Node1);
        Node Node4 = new Node("4", "越秀区", Node1);
        Node Node5 = new Node("5", "黄埔区", Node1);
        Node Node6 = new Node("6", "石牌", Node3);
        Node Node7 = new Node("7", "百脑汇", Node6);


        Node Node8 = new Node("8", "南山区", Node2);
        Node Node9 = new Node("9", "宝安区", Node2);
        Node Node10 = new Node("10", "科技园", Node8);


        List<Node> list = new ArrayList<>();

        list.add(Node1);
        list.add(Node2);
        list.add(Node3);
        list.add(Node4);
        list.add(Node5);
        list.add(Node6);
        list.add(Node7);
        list.add(Node8);
        list.add(Node9);
        list.add(Node10);

        List<Node> trees = TreeBuilder.bulid(list);

        System.out.println(JSONUtil.toJsonStr(trees));

    }

}
