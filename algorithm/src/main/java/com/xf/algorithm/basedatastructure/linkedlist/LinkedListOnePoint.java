package com.xf.algorithm.basedatastructure.linkedlist;

import java.util.Stack;

/**
 * @Description: todo(单向链表的java代码实现)
 * @Author: xiefu
 * @Date: 2019/3/7 22:06
 */
public class LinkedListOnePoint {
    /** 头节点 **/
    private Node head;
    /** 链表长度，即链表中节点数量 **/
    private int size;

    public LinkedListOnePoint(){
        head = null;
        size = 0;
    }

    /**
     * 私有内部类，代表链表的每个节点
     */
    private class Node{
        /** 链表节点的值 **/
        private Object data;
        /** 指向的下一节点 **/
        private Node next;
        public Node(Object data){
            this.data = data;
        }
    }

    /**
     * 判断链表是否为空
     */
    public boolean isEmpty(){
        return size==0 ? true : false;
    }

    /**
     * 返回链表长度
     */
    public int size(){
        return size;
    }

    /**
     * 查看链表的头节点，不移除
     */
    public Object headNode(){
        if(size == 0){
            throw new RuntimeException("空链表");
        }
        return head.data;
    }

    /**
     * 在链表表头插入一个节点(入栈)
     * @param obj
     */
    public void insertInHead(Object obj){
        Node newNode = new Node(obj);
        if(size == 0){
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size ++;
    }

    /**
     * 删除链表表头节点
     */
    public Object deleteHeadNode(){
        if(size == 0){
            throw new RuntimeException("空链表");
        }
        Object obj = head.data;
        if(head.next == null){
            // 只有一个节点
            head = null;
        }else{
            head = head.next;
        }
        return obj;
    }

    /**
     * 链表查找:判断链表中是否包含莫格元素
     */
    public boolean containObject(Object obj){
        if(size == 0){
            // 链表为空
            return false;
        }
        Node n = head;
        while(n != null) {
            if (n.data == obj) {
                return true;
            } else {
                n = n.next;
            }
        }
        return false;
    }

    /**
     * 删除链表中的指定节点（如果包含多个指定节点，只会删除第一个）
     */
    public boolean deleteNode(Object obj){
        if(size == 0){
            throw new RuntimeException("空链表");
        }
        // 先在链表中查询是否包含该结点，找到之后获取该结点和其前一个结点
        // 前一个节点
        Node previous = null;
        // 当前节点
        Node current = head;
        while(current.data != obj){
            if(current.next == null){
                return false;
            }
            previous = current;
            current = current.next;
        }
        if(current == head){
            this.deleteHeadNode();
        }else{
            previous.next = current.next;
            size --;
        }
        return true;
    }

    /**
     * 正向打印链表
     */
    public void display(){
        if(size == 0){
            throw new RuntimeException("空链表!");
        }
        Node n = head;
        while(n != null){
            System.out.print("<-"+n.data);
            n = n.next;
        }
        System.out.println();
    }

    /**
     * 反向打印链表
     */
    public void printListFromTailToHead(Node node){
        if(size == 0){
            throw new RuntimeException("链表为空!");
        }
        Stack<Object> stack = new Stack<Object>();
        while(node != null){
            stack.push(node.data);
            node = node.next;
        }
        while(!stack.empty()){
            //出栈
            System.out.print(stack.pop()+"<-");
        }
        System.out.println();
    }

    /**
     * 反向打印链表（递归）
     */
    public void printListFromTailToHeadDiGui(Node node){
        if(node == null){
            throw new RuntimeException("链表为空!");
        }else{
            if(node.next != null){
                printListFromTailToHeadDiGui(node.next);
            }
            System.out.print(node.data+"<-");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedListOnePoint list = new LinkedListOnePoint();
        //输出结果：true
        System.out.println(list.isEmpty());
        //输出结果：0
        System.out.println(list.size());

        list.insertInHead(0);
        list.insertInHead(1);
        list.insertInHead(2);
        list.insertInHead(3);

        //输出结果：<-3<-2<-1<-0
        list.display();
        //输出结果：0<-1<-2<-3<-
        list.printListFromTailToHead(list.head);
        //输出结果：0<-1<-2<-3<-
        list.printListFromTailToHeadDiGui(list.head);
        //输出结果：false
        System.out.println(list.isEmpty());
        //输出结果：4
        System.out.println(list.size());
        //输出结果：true
        System.out.println(list.containObject(1));
    }
}

