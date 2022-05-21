package data_structures.tuples;

public class Size extends Pair<Integer, Integer>{
    public Size(){
        super(0, 0);
    }
    public Size(int width, int height) {
        super(width, height);
    }

    public int width() {
        return first;
    }

    public int height() {
        return second;
    }

    public void setWidth(int newWidth){
        first = newWidth;
    }

    public void setHeight(int newHeight){
        second = newHeight;
    }

    public int getTotalSize(){return width() * height();}
}
