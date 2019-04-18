package com.xf.algorithm.basedatastructure.linkedlist;

import java.util.Objects;

/**
 * @Description: todo(双向链表(DoublyLinkedList)的实现)
 * @Author: xiefu
 * @Date: 2019/3/8 20:17
 */
public class DoublyLinkedList<T> {
    /** 双向链表的头指针 **/
    private Node<T> head;
    /** 双向链表的尾指针 **/
    private Node<T> tail;

    public DoublyLinkedList(){
        head = new Node<>();
        tail = new Node<>();
    }

    /**
     * 双向链表的节点类
     * @param <T>
     */
    public static class Node<T>{
        private T data;
        private Node<T> prev = null;
        private Node<T> next = null;

        /**
         * 必须使用存储数据（data）重写equals和hashcode，自动生成的hashcode会导致栈溢出错误
         */
        public boolean equals(Node<T> node) {
            if (data.equals(node.data)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return data.hashCode();
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    /**
     * 添加节点到链表的头部
     * @param node
     */
    public void addToHead(Node<T> node){
        if(null == head.next){
            head.next = node;
            tail.prev = node;
        }else{
            node.next = head.next;
            node.next.prev = node;
            head.next = node;
        }
    }

    /**
     * 添加节点到链表的尾部
     * @param node
     */
    public void addToTail(Node<T> node){
        if(null == node.prev) {
            node.prev = node;
            head.next = node;
        } else {
            tail.prev.next = node;
            node.prev = tail.prev;
            tail.prev = node;
        }
    }

    /**
     * 链表的遍历(从head顺序遍历)
     */
    public void traversalFromHead(){
        if(isEmpty()){
            throw new RuntimeException("链表为空！");
        }else{
            Node<T> node = head;
            while (null != node.next) {
                System.out.print(node.next + "-->");
                node = node.next;
            }
            System.out.println();
        }
    }

    /**
     * 链表的遍历(从tail倒序遍历)
     */
    public void traversalFromTail(){
        if(isEmpty()){
            throw new RuntimeException("链表为空！");
        }else{
            Node<T> node = tail;
            while (null != node.prev) {
                System.out.print(node.prev+ "-->");
                node = node.prev;
            }
            System.out.println();
        }
    }

    /**
     * 添加某个值到指定的数值的节点后面
     * @param node
     * @param key
     */
    public void insertAfter(Node<T> node, T key){
        if(isEmpty()){
            throw new RuntimeException("链表为空！");
        }else{
            Node<T> theNode = this.head;
            while(null != theNode.next){
                if(theNode.next.data.equals(key)){
                    node.next = theNode.next.next;
                    theNode.next.next.prev = node;
                    theNode.next.next = node;
                    node.prev = theNode.next;
                    break;
                }
                theNode = theNode.next;
            }
        }
    }

    /**
     * 添加某个值到指定的数值的节点前面
     * @param node
     * @param key
     */
    public void insertBefore(Node<T> node, T key){
        if(isEmpty()){
            throw new RuntimeException("链表为空！");
        }else{
            Node<T> theNode = this.head;
            while(null != theNode.next){
                if(theNode.next.data.equals(key)){
                    node.next = theNode.next;
                    theNode.next.prev = node;
                    theNode.next = node;
                    node.prev = theNode;
                    break;
                }
                theNode = theNode.next;
            }
        }
    }

    /**
     * 判断链表是否为空
     * @return
     */
    public boolean isEmpty() {
        if (null == head.next || null == tail.prev) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<Integer>();
        for (int i = 0; i < 6; i++) {
            Node<Integer> node = new Node<Integer>();
            node.data = i;
            doublyLinkedList.addToHead(node);
        }
        doublyLinkedList.traversalFromHead();
        doublyLinkedList.traversalFromTail();
        System.out.println("---------------------------");
        for (int i = 10; i < 16; i++) {
            Node<Integer> node = new Node<Integer>();
            node.data = i;
            doublyLinkedList.addToHead(node);
        }
        Node<Integer> node = new Node<Integer>();
        node.data = 88;
        doublyLinkedList.insertAfter(node, 11);
        doublyLinkedList.traversalFromHead();
        doublyLinkedList.traversalFromTail();

        Node<Integer> node1 = new Node<Integer>();
        node1.data = 99;
        doublyLinkedList.insertBefore(node1, 14);
        doublyLinkedList.traversalFromHead();
        doublyLinkedList.traversalFromTail();
    }
}
