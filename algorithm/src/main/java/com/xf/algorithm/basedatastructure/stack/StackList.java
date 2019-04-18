package com.xf.algorithm.basedatastructure.stack;

import java.util.LinkedList;

/**
 * @Description: todo(基于LinkedList实现栈)
 * @Author: xiefu
 * @Date: 2019/3/8 22:9
 */
public class StackList<E> {
    private LinkedList<E> ll = new LinkedList<E>();

    //入栈
    public void push(E e){
        ll.addFirst(e);
    }

    //查看栈顶元素但不移除
    public E peek(){
        return ll.getFirst();
    }

    //出栈
    public E pop(){
        return ll.removeFirst();
    }

    //判空
    public boolean empty(){
        return ll.isEmpty();
    }

    /**
     * 打印栈元素
     */
    @Override
    public String toString(){
        return ll.toString();
    }

}
