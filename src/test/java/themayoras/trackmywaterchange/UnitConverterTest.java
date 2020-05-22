package themayoras.trackmywaterchange;

import org.junit.Assert;
import org.junit.Test;
import themayoras.trackmywaterchange.entity.UnitConverter;

import static themayoras.trackmywaterchange.entity.QuantityUnits.*;

public class UnitConverterTest {
    private static final double DELTA = 0.001;

    @Test
    public void testUnitConverter() {
        UnitConverter converter = new UnitConverter(L, 5.0, GAL, 100);
        Assert.assertEquals(5.0, converter.convertTo(L), DELTA);

        converter = new UnitConverter(GAL, 5.0, GAL, 100);
        Assert.assertEquals(5.0, converter.convertTo(GAL), DELTA);

        converter = new UnitConverter(GAL, 5.0, GAL, 100);
        Assert.assertEquals(18.9271, converter.convertTo(L), DELTA);

        converter = new UnitConverter(GAL, 5.0, GAL, 100);
        Assert.assertEquals(18.9271, converter.convertTo(L), DELTA);

        converter = new UnitConverter(L, 5.0, GAL, 100);
        Assert.assertEquals(1.32086, converter.convertTo(GAL), DELTA);

        converter = new UnitConverter(PERCENT, 5.0, GAL, 100);
        Assert.assertEquals(5, converter.convertTo(GAL), DELTA);

        converter = new UnitConverter(PERCENT, 0.05, GAL, 100);
        Assert.assertEquals(5, converter.convertTo(GAL), DELTA);

        converter = new UnitConverter(PERCENT, 5.0, L, 100);
        Assert.assertEquals(1.32086, converter.convertTo(GAL), DELTA);

        converter = new UnitConverter(PERCENT, 5.0, GAL, 100);
        Assert.assertEquals(18.9271, converter.convertTo(L), DELTA);
    }
}
