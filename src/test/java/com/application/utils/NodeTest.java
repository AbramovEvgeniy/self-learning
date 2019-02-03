package com.application.utils;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class NodeTest {

    @Test
    public void testDeep() {
        Node<Integer> node1 = new Node<Integer>(1);
        Node<Integer> node2 = new Node<Integer>(2);
        Node<Integer> node3 = new Node<Integer>(3);
        Node<Integer> node4 = new Node<Integer>(4);
        Node<Integer> node5 = new Node<Integer>(5);
        Node<Integer> node6 = new Node<Integer>(6);

        node1.getChildren().add(node2);
        node1.getChildren().add(node3);
        node1.getChildren().add(node4);
        node2.getChildren().add(node3);
        node3.getChildren().add(node4);
        node3.getChildren().add(node6);
        node4.getChildren().add(node5);
        node4.getChildren().add(node6);


        System.out.println(node1);
        System.out.println(node1.deep());
    }
}