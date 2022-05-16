import data_structures.queue.Fifo;
import data_structures.queue.PriorityQueue;

public class Main {
    public static void main(String[] args){

        PriorityQueue<Integer> f = new PriorityQueue<Integer>(5);
        f.push(4);
        f.push(2);
        System.out.println(f.pop());
        System.out.println(f.pop());
        f.push(10);
        f.push(7);
        f.push(11);
        f.push(9);
        System.out.println(f.pop());
        System.out.println(f.peek());
        System.out.println(f.pop());
        f.push(10);
        f.push(13);
        System.out.println(f);
    }
}
