package com.scgraf.data_structures.graph;

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

        public final int index;

        Side(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return sideNames[index];
        }

        public Side getOppositeSide() {
            return getSideTurnedBy(2);
        }

        public Side getSideTurnedBy(int amountOf90degreeTurns) {
            int sideIndex = index;
            if (amountOf90degreeTurns > 0) sideIndex = (sideIndex + amountOf90degreeTurns) % 4;
            else sideIndex =  (sideIndex + 4 + amountOf90degreeTurns % 4) % 4;

            return values()[sideIndex];
        }
    }

    @Override
    public String toString() {
        return Double.toString(getWeight());
    }
}
