import utils.Utils;

public class Main {
    public static void main(String[] args){
        Integer[] w = new Integer[3];
        w[0] = 1;
        w[1] = 2;
        w[2] = 3;
        Utils.Swap(w, 0, 1);
        System.out.println(w[0]);
        System.out.println(w[1]);
    }
}
