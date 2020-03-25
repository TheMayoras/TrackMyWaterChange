package themayoras.trackmywaterchange.entity;

import java.util.Date;

public class TankDetails {
    private Tank tank;
    private double averageWaterChangeFrequency;
    private Date lastWaterChange;
    private Date firstWaterChange;
    private double averageQuantity;
    private QuantityUnits units;

    public TankDetails(Tank tank, double averageWaterchangeFrequency, Date lastWaterChange, Date firstWaterChange,
                       double averageQuantity, QuantityUnits units) {
        this.tank = tank;
        this.averageWaterChangeFrequency = averageWaterchangeFrequency;
        this.lastWaterChange = lastWaterChange;
        this.firstWaterChange = firstWaterChange;
        this.averageQuantity = averageQuantity;
        this.units = units;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public double getAverageQuantity() {
        return averageQuantity;
    }

    public void setAverageQuantity(double averageQuantity) {
        this.averageQuantity = averageQuantity;
    }

    public Date getLastWaterChange() {
        return lastWaterChange;
    }

    public void setLastWaterChange(Date lastWaterChange) {
        this.lastWaterChange = lastWaterChange;
    }

    public Date getFirstWaterChange() {
        return firstWaterChange;
    }

    public void setFirstWaterChange(Date firstWaterChange) {
        this.firstWaterChange = firstWaterChange;
    }

    public double getAverageWaterChangeFrequency() {
        return averageWaterChangeFrequency;
    }

    public void setAverageWaterChangeFrequency(double averageWaterChangeFrequency) {
        this.averageWaterChangeFrequency = averageWaterChangeFrequency;
    }

    public QuantityUnits getUnits() {
        return units;
    }

    public void setUnis(QuantityUnits units) {
        this.units = units;
    }
}
