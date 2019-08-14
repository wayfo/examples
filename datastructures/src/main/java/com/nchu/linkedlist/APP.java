package com.nchu.linkedlist;

import org.junit.Test;

/**
 * @Decription 链表测试
 * @Author yangsj
 * @Date 20190801 10:07
 **/
public class APP {

    /**
     * @Description 测试单链表
     * @Author yangsj
     * @Date 2019/8/1 10:12
     **/
    @Test
    public void TestSigleLinkedList(){
        SingleLinkedList list = new SingleLinkedList();

        /*list.show();
        System.out.println("---------------");*/
        SingleLinkedList listSort = new SingleLinkedList();
        listSort.addSort(2);
        listSort.addSort(1);
        listSort.addSort(3);
        listSort.addSort(1);
        listSort.addSort(4);
        listSort.addSort(5);
        listSort.addSort(6);

        //listSort.show();

        // 链表的更新
//        listSort.update(listSort.headNode,2);
//        listSort.show();

        //链表的节点删除
//        listSort.delete(listSort.headNode,7);
//        listSort.show();

       //链表的长度
//        int size = listSort.size();
//        System.out.println(size);
//        listSort.show();

        //链表的倒数第 n 个元素
//        int data = listSort.getReciprocal(8);
//        System.out.println(data);

        //链表的翻转
       /*listSort.reversal();
       listSort.show();*/
       //链表的翻转打印
//        listSort.reversalPrint();
        //链表的有序合并（有序）
        SingleLinkedList list2 = new SingleLinkedList();
        list2.add(11);
        list2.add(23);
        list2.add(19);
        list2.add(12);
        list2.add(33);
        list2.add(10);
        list2.add(9);

        SingleLinkedList mergeList = listSort.mergeSort(list2);
        mergeList.show();
    }

}
