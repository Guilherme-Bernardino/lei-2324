package pt.pa.adts;

/**
 *
 * @author brunomnsilva
 * @param <E>
 */

//nivel 1
public class PriorityQueueLinkedList<E extends Comparable<E>>  implements Queue<E>{

    private final ListNode header, trailer;
   //nivel 5
    // private int size;

    public PriorityQueueLinkedList() {
        this.trailer = new ListNode(null, null, null); //header not instantiated yet!
        this.header = new ListNode(null, null, this.trailer);
        this.trailer.prev = this.header;
        //nivel 5
        //this.size = 0;
    }

    @Override
    public int size() {
        //return this.size;
        //nivel 5
        int size=0;
        ListNode node= this.header.next;
        while(node!=this.trailer){
            size++;
            node=node.next;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (this.size() == 0);

    }

    @Override
    public void clear() {
        /* return to empty state */
        this.header.next = this.trailer;
        this.trailer.prev = this.header;
        //nivel 5
        //this.size = 0;
    }

    @Override //nivel 3
    public void enqueue(E elem) throws QueueFullException {

        try {
            ListNode nodeAfter = nextNodeForElement(elem);

            ListNode newNode = new ListNode(elem, nodeAfter.prev, nodeAfter);
            newNode.next.prev = newNode;
            newNode.prev.next = newNode;

        } catch (OutOfMemoryError e) {
            throw new QueueFullException();
        }

    }
    //nivel 3
    private ListNode nextNodeForElement(E elem) {
        if(size() == 0) return this.trailer;

        ListNode current = this.header.next;
        while(current != this.trailer) {
            if(current.elem.compareTo(elem) < 0) { //current has less priority
                break;
            }
            current = current.next;
        }

        return current;
    }

    @Override
    public E dequeue() throws QueueEmptyException {
        if( this.header.next == this.trailer ) throw new QueueEmptyException();

        E front = this.header.next.elem;

        this.header.next = this.header.next.next;
        this.header.next.prev = this.header;
        //nivel 5
        //this.size--;

        return front;
    }

    @Override
    public E front() throws QueueEmptyException {
        if( this.header.next == this.trailer ) throw new QueueEmptyException();

        return this.header.next.elem;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PriorityQueueLinkedList{size=" + this.size() + ", front|[");

        ListNode node = this.header.next;

        while(node != this.trailer) {
            sb.append( node.elem );
            if(node != this.trailer.prev) { //don't place last comma
                sb.append( "," );
            }
            node = node.next;
        }

        sb.append("]|end}");
        return sb.toString();
    }

    private class ListNode {
        E elem;
        ListNode next, prev;

        public ListNode(E elem, ListNode prev, ListNode next) {
            this.elem = elem;
            this.next = next;
            this.prev = prev;
        }

    }
}
