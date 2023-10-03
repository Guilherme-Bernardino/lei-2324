package pt.pa.adts;

public class Main {
  // Nivel 4

    public static void main(String[] args) {
    Queue<PrintJob> queue= new PriorityQueueLinkedList<>();

    PrintJob[] jobs = {
                new PrintJob("Job1", 3, PrintJob.PrintJobPriority.NORMAL),  //jobs[0]
                new PrintJob("Job2", 5, PrintJob.PrintJobPriority.NORMAL),  //jobs[1]
                new PrintJob("Job3", 2, PrintJob.PrintJobPriority.LOW),     //jobs[2]
                new PrintJob("Job4", 7, PrintJob.PrintJobPriority.HIGH),    //jobs[3]
                new PrintJob("Job5", 9, PrintJob.PrintJobPriority.NORMAL)   //jobs[4]
     };

        System.out.println("The number of jobs is "+  queue.size());
        for(PrintJob job:jobs)
            queue.enqueue(job);


        while(!queue.isEmpty()){
            System.out.println(queue.dequeue());
        }

    }


}
