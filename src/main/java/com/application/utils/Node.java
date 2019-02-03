package com.application.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Slf4j
public class Node<T> {

    private T value;
    private Set<Node<T>> children = new HashSet<>();
    private int nodeWeight;

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, int weight) {
        this.value = value;
        this.nodeWeight = weight;
    }

    public int deep() {
        int deep = 0;
        if (children.size() == 0) {
            return deep;
        }
        for (Node<T> child : this.getChildren()) {
            if (child.getChildren().size() > 0) {
                deep = child.deep() + 1;
            }
//            return child.deep() + 1;
        }
        return deep;
    }

    public int weight() {
        int nodeWeight = 0;
        if (children.size() == 0) {
            return this.nodeWeight;
        }
        for (Node<T> child : this.getChildren()) {
            int newNodeWeight = child.weight() + this.nodeWeight;
            if (nodeWeight < newNodeWeight) {
                nodeWeight = newNodeWeight;
            }
        }
        return nodeWeight;
    }

    public void collectResult(List<T> smsList) {
        if (this.getChildren().size() > 0) {
            int deep = this.deep();
            int weight = this.weight();
            Node<T> tempNode = this.getChildren().stream()
                    .filter(node -> node.deep() == deep - 1)
//                    .filter(node -> node.weight() == weight)
                    .findFirst().get();
            smsList.add(tempNode.getValue());
            tempNode.collectResult(smsList);
        }
    }

}
