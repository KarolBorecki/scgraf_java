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

        //Można wykorzystać fakt, że jest to (Side.index + 2) % 4
        public Side getOppositeSide() {
            if (this.index == Side.TOP.index)
                return Side.BOTTOM;
            else if (this.index == Side.BOTTOM.index)
                return Side.TOP;
            else if (this.index == Side.LEFT.index)
                return Side.RIGHT;
            else return Side.LEFT;

        }

        public Side getSideTurnedBy(int ammountOf90degreeTurns) {
            if (ammountOf90degreeTurns == 0)
                return this;
            int sideIndex;
            if (ammountOf90degreeTurns > 0) {
                ammountOf90degreeTurns %= 4;
            } else {
                ammountOf90degreeTurns = 4 + ammountOf90degreeTurns % 4;
            }
            sideIndex = (this.index + ammountOf90degreeTurns) % 4;

            if (sideIndex == Side.TOP.index)
                return Side.TOP;
            if (sideIndex == Side.BOTTOM.index)
                return Side.BOTTOM;
            if (sideIndex == Side.LEFT.index)
                return Side.LEFT;
            else return Side.RIGHT;
        }
    }

    @Override
    public String toString() {
        return Double.toString(getWeight());
    }
}
