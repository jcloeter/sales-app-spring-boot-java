package com.example.vanillajava.service;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class QueueService extends AbstractCollection {
    
    public void run(){

        Queue<String> queue = new PriorityQueue<>();

        queue.add("J");
        queue.add("o");
        queue.add("h");
        queue.add("n");


        int queueSize = queue.size();
        for (int i = 0; i < queueSize; i++){
            System.out.println(queue.peek());
            queue.poll();
            System.out.println(queue);
        }

        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("C");
        linkedList.add("l");
        linkedList.add("o");
        linkedList.add("e");
        linkedList.add("t");
        linkedList.add("e");
        linkedList.add("r");

        System.out.println(linkedList.contains("C"));

        int linkedListSize = linkedList.size();

        for (int i = 0; i < linkedListSize; i++){
            System.err.println(linkedList.element());
            linkedList.remove();
            System.err.println(linkedList);
        }


    }
}