package themayoras.trackmywaterchange.entity;

public enum QuantityUnits {
    GAL {
        @Override
        public String toString() {
            return "gal";
        }
    },
    L, ML {
        @Override
        public String toString() {
            return "mL";
        }
    },
    PERCENT {
        @Override
        public String toString() {
            return "%";
        }
    }

}
