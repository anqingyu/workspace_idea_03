package com.xf.algorithm.basedatastructure.binarysearchtree;

import java.util.*;

/**
 * @Description: todo(二叉搜索树)
 *      参考博客：https://www.cnblogs.com/sungoshawk/p/3755789.html
 * @Author: xiefu
 * @Date: 2019/3/12 0:30
 */
public class BinarySearchTree<T> {

    protected TreeNode<T> root = null;
    /** 比较器 **/
    private final Comparator<? super T> comparator;
    private int size = 0;

    public BinarySearchTree() {
        comparator = null;
    }

    public BinarySearchTree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    /**
     * 向树中插入新结点
     *
     * @param key 新结点的key值
     */
    public void insert(T key) {
        TreeNode<T> x = root;
        TreeNode<T> y = null;
        TreeNode<T> z = new TreeNode<T>(key, null);
        int cmp;
        // 如果根结点是null，则新插入结点成为根结点
        if (x == null) {
            root = z;
            size = 1;
            return;
        }
        // x从根结点开始沿树下降，直到找到z应当插入的位置。y用于找出z的父结点的位置
        while (x != null) {
            y = x;
            cmp = compareKey(z.key, x.key);
            if (cmp < 0){
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        cmp = compareKey(z.key, y.key);
        if (cmp < 0) {
            y.left = z;
        } else {
            y.right = z;
        }
        size++;
    }

    /**
     * 删除树中某个结点
     *
     * @param key 待删除结点的key值
     * @return 被删除结点的key值
     */
    public T remove(T key) {
        TreeNode<T> p = find(key);
        if (p == null)
            return null;
        T oldValue = p.key;
        deleteNode(p);
        return oldValue;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    /**
     * 返回二叉查找树中最左子结点
     *
     * @return 返回二叉查找树中最左子结点
     */
    public TreeNode<T> firstNode() {
        return getFirstNode(root);
    }

    /**
     * 返回二叉查找树中最右子结点
     *
     * @return 返回二叉查找树中最右子结点
     */
    public TreeNode<T> lastNode() {
        return getLastNode(root);
    }

    /**
     * 根据key值查找结点
     */
    public TreeNode<T> find(T t) {
        TreeNode<T> p = root;
        while (p != null) {
            int cmp = compareKey(t, p.key);
            if (cmp < 0)
                p = p.left;
            else if (cmp > 0)
                p = p.right;
            else
                return p;
        }
        return null;
    }

    /**
     * 返回结点集
     */
    public Set<TreeNode<T>> nodeSet() {
        return new NodeSet();
    }

    /**
     * 返回传入结点的后继结点
     */
    private static <T> TreeNode<T> successor(TreeNode<T> t) {
        if (t == null)
            return null;
        else if (t.right != null)   // 如果t的右子树不空，那么t的后继就是其右子树中key值最小的结点
            return getFirstNode(t.right);
        else {  // 如果t的右子树为空，左子树不空，那么t的后继就是t的一个最低祖先结点，且该结点的左孩子也必须是t的祖先结点
            TreeNode<T> p = t.parent;
            TreeNode<T> ch = t;
            while (p != null && ch == p.right) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }

    /**
     * 返回传入结点的前趋结点
     */
    private static <T> TreeNode<T> predecessor(TreeNode<T> t) {
        if (t == null)
            return null;
        else if (t.left != null)
            return getLastNode(t.left);
        else {
            TreeNode<T> p = t.parent;
            TreeNode<T> ch = t;
            while (p != null && ch == p.left) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }

    private static <T> TreeNode<T> getFirstNode(TreeNode<T> t) {
        TreeNode<T> p = t;
        if (p != null)
            while (p.left != null)
                p = p.left;
        return p;
    }

    private static <T> TreeNode<T> getLastNode(TreeNode<T> t) {
        TreeNode<T> p = t;
        if (p != null)
            while (p.right != null)
                p = p.right;
        return p;
    }

    /**
     * 用结点v替换结点u在树中的位置
     */
    private void transplant(TreeNode<T> u, TreeNode<T> v) {
        if (u.parent == null)
            root = v;
        else if (u == u.parent.left)
            u.parent.left = v;
        else
            u.parent.right = v;
        if (v != null)
            v.parent = u.parent;
    }

    /**
     * 删除树中的结点
     */
    private void deleteNode(TreeNode<T> p) {
        size--;
        if (p.left == null) // 如果结点的左子树为空，则用右子树替换结点
            transplant(p, p.right);
        else if (p.right == null)   // 如果结点的右子树为空，则用左子树替换结点
            transplant(p, p.left);
        else {  // 结点有两个子结点
            TreeNode<T> y = getFirstNode(p.right); // 首先找出结点p的后继y
            if (y.parent != p) {    // 如果p不是y的父结点，那么需要用y的右孩子替换y
                transplant(y, y.right);
                y.right = p.right;
                y.right.parent = y;
            }
            transplant(p, y);   // 最后用y替换p
            y.left = p.left;
            y.left.parent = y;
        }
    }

    /**
     * 用于比较树中结点的key值
     */
    private int compareKey(T key1, T key2) {
        int cmp;
        if (comparator != null)
            cmp = comparator.compare(key1, key2);
        else {
            if (key1 == null || key2 == null)
                throw new NullPointerException();
            Comparable<? super T> k = (Comparable<? super T>) key1;
            cmp = k.compareTo(key2);
        }
        return cmp;
    }

    /**
     * 二叉树的节点类
     */
    public static final class TreeNode<T>{
        T key;
        /** 左节点 **/
        TreeNode<T> left = null;
        /** 右节点 **/
        TreeNode<T> right = null;
        /** 父节点 **/
        TreeNode<T> parent;

        public TreeNode(T key, TreeNode<T> parent){
            this.key = key;
            this.parent = parent;
        }
        public  T getKey(){
            return key;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof TreeNode)) { return false; }
            TreeNode<?> e = (TreeNode<?>) o;
            return keyEquals(key, e.getKey());
        }

        @Override
        public String toString() {
            return "[" + key + "]";
        }
    }

    private static boolean keyEquals(Object o1, Object o2) {
        return (o1 == null ? o2 == null : o1.equals(o2));
    }

    /**
     * 树中结点的集合类，使用迭代器遍历，从最左结点开始，依次取其后继结点（即中序遍历）
     */
    final class NodeSet extends AbstractSet<TreeNode<T>> {
        @Override
        public Iterator<TreeNode<T>> iterator() {
            return new NodeIterator(firstNode());
        }

        @Override
        public int size() {
            return BinarySearchTree.this.size();
        }
    }

    final class NodeIterator extends PrivateNodeIterator<TreeNode<T>> {
        NodeIterator(TreeNode<T> first) {
            super(first);
        }

        @Override
        public TreeNode<T> next() {
            return nextNode();
        }

        @Override
        public void remove() { }
    }

    abstract class PrivateNodeIterator<E> implements Iterator<E> {
        TreeNode<T> next;

        PrivateNodeIterator(TreeNode<T> first) {
            next = first;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        final TreeNode<T> nextNode() {
            TreeNode<T> e = next;
            if (e == null)
                throw new NoSuchElementException();
            next = successor(e);
            return e;
        }
    }
}
