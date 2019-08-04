package themayoras.trackmywaterchange.entity;

public enum QuantityUnits {
    GAL, L, ML {
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
