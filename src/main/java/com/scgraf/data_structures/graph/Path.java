package src.main.com.scgraf.data_structures.graph;

public record Path(double weight) {

    public double getWeight() {
        return weight;
    }

    public enum Side {
        TOP(0),
        RIGHT(1),
        BOTTOM(2),
        LEFT(3);

        static final String[] sideNames = new String[]{"TOP", "RIGHT", "BOTTOM", "LEFT"};

        final int index;

        Side(int index){
            this.index = index;
        }

        @Override
        public String toString() {
            return sideNames[index];
        }
    }

    @Override
    public String toString(){
        return Double.toString(getWeight());
    }
}
