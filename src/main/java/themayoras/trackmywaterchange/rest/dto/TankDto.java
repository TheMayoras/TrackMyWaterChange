package themayoras.trackmywaterchange.rest.dto;

import java.util.List;

public class TankDto {
    private int id;
    private String name;
    private Double size;
    private String units;
    private String location;
    private int userId;
    private List<Integer> waterChangeIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Integer> getWaterChangeIds() {
        return waterChangeIds;
    }

    public void setWaterChangeIds(List<Integer> waterChangeIds) {
        this.waterChangeIds = waterChangeIds;
    }
}
