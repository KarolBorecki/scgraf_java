import data_structures.queue.Fifo;

public class Main {
    public static void main(String[] args){

        Fifo f = new Fifo(5);
        f.push(1);
        f.push(2);
        System.out.println(f.pop());
        System.out.println(f.pop());
        f.push(6);
        f.push(7);
        f.push(8);
        f.push(9);
        System.out.println(f.pop());
        System.out.println(f.peek());
        System.out.println(f.pop());
        f.push(10);
        f.push(11);
        System.out.println(f);
    }
}
