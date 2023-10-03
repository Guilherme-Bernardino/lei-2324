package com.brunomnsilva;

public class Main {

    public static void main(String[] args) {
	    //Queue<Integer> q = new QueueArrayList<>();
        Queue<Integer> q = new QueueLinkedList<>();

	    for(int i=1; i<11; i++) {
	        q.enqueue(i);
        }

        System.out.println(q);

        System.out.println("At front: " + q.front());

	    while(!q.isEmpty()) {
            System.out.println(q.dequeue());
        }
    }
}
