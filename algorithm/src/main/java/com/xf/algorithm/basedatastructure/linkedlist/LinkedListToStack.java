package com.xf.algorithm.basedatastructure.linkedlist;

/**
 * @Description: todo(单链表实现栈)
 * @Author: xiefu
 * @Date: 2019/3/7 23:48
 */
public class LinkedListToStack {
    private LinkedListOnePoint linkedList;

    public LinkedListToStack(){
        linkedList = new LinkedListOnePoint();
    }

    /**
     * 栈大小
     */
    public int size(){
        return linkedList.size();
    }

    /**
     * 是否为空栈
     */
    public boolean isEmpty(){
        return linkedList.isEmpty();
    }

    /**
     * 入栈
     */
    public void push(Object obj){
        linkedList.insertInHead(obj);
    }

    /**
     * 出栈
     */
    public Object pop(){
        if(this.isEmpty()){
            return null;
        }
        return linkedList.deleteHeadNode();
    }

    /**
     * 查看栈顶元素
     */
    public Object peek(){
        if(this.isEmpty()){
            return null;
        }
        return linkedList.headNode();
    }

    /**
     * 打印栈中元素
     */
    public void display(){
        linkedList.display();
    }

    public static void main(String[] args) {
        LinkedListToStack stack = new LinkedListToStack();
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        //输出结果：<-3<-2<-1<-0
        stack.display();
        //输出结果：3
        System.out.println(stack.peek());
        //输出结果：3
        System.out.println(stack.pop());
        //输出结果：2
        System.out.println(stack.pop());
        //输出结果：1
        System.out.println(stack.pop());
        //输出结果：0
        System.out.println(stack.pop());
        //输出结果：NullPointerException
        System.out.println(stack.pop());
    }
}
