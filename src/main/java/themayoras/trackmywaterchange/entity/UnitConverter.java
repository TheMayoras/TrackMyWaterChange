package themayoras.trackmywaterchange.entity;

import java.util.HashMap;
import java.util.Map;

import static themayoras.trackmywaterchange.entity.QuantityUnits.*;

public class UnitConverter {
    private static final Map<QuantityUnits, Double> CONVERSIONS_TO_LITERS;

    static {
        CONVERSIONS_TO_LITERS = new HashMap<>();
        CONVERSIONS_TO_LITERS.put(L, 1.0);
        CONVERSIONS_TO_LITERS.put(ML, 0.001);
        CONVERSIONS_TO_LITERS.put(GAL, 3.78541);
        CONVERSIONS_TO_LITERS.put(PERCENT, 1.0);

    }

    private QuantityUnits from;
    private double quantity;
    private QuantityUnits sizeUnits;
    private double size;

    public UnitConverter(QuantityUnits from, double quantity, QuantityUnits sizeUnits, double size) {
        this.from = from;
        this.quantity = quantity;
        this.size = size;

        if (from.equals(PERCENT)) {
            this.quantity = getValue(from, quantity);
            this.from = sizeUnits;
        }
    }

    public double convertTo(QuantityUnits to) {
        if (to.equals(PERCENT)) {
            return quantity / size;
        }

        // initial value
        double value = quantity;

        // converted to L
        value *= CONVERSIONS_TO_LITERS.get(from);

        // converted from L
        value /= CONVERSIONS_TO_LITERS.get(to);

        return value;
    }

    private double getValue(QuantityUnits unit, double value) {
        if (unit.equals(QuantityUnits.PERCENT)) {
            if (value >= 1) {
                value /= 100;
            }
            return value * size;
        }

        return value;
    }
}
