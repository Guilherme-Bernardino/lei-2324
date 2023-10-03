package pt.pa.refactoring;

public class Main {

    public static void main(String[] args) {
        QueueWithLimit<Integer> q = new QueueWithLimit<>();

        System.out.println("Enqueuing...");
        for(int i=1; i<=15; i++) {
            System.out.print(i + " ");

            try {
                q.enqueue(i);
            } catch (QueueFullException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println();

        //does it make sense to allow the following? Not really, so Refactor!
        //q.add(2, 3);
        //q.add(5, 10);

        System.out.println("At front of queue: " + q.front());

        System.out.println("Dequeuing all elements:");
        while(!q.isEmpty()) {
            System.out.println(q.dequeue());
        }
    }
}
