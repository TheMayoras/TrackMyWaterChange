package themayoras.trackmywaterchange.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import themayoras.trackmywaterchange.entity.validation.TankLocation;
import themayoras.trackmywaterchange.entity.validation.TankSize;

@Entity
@Table(name = "tanks")
public class Tank {

    // tankId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tank")
    private int id;

    // name of the tank
    @Column(name = "name")
    private String name;

    // size of the tank
    @TankSize
    @Column(name = "size")
    private Double size;

    // location of the tank
    @TankLocation
    @Column(name = "location")
    private String location;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    // list of water changes on the tank.
    // LAZY. There can be a lot of water changes, so we need want lazy
    @OrderBy("date DESC")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tank", orphanRemoval = true)
    private List<WaterChange> waterChanges;

    @PreRemove
    public void removeTankFromUser() {
        this.user.remove(this);
    }

    public void addWaterChange(WaterChange waterChange) {
        if (waterChanges == null) {
            waterChanges = new ArrayList<>();
        }
        
        waterChanges.add(waterChange);
        waterChange.setTank(this);
    }

    public Tank() {
    }

    public Tank(String name, double size, String location) {
        this.name     = name;
        this.size     = size;
        this.location = location;
    }

    public Tank(String name, double size, String location, List<WaterChange> waterChanges) {
        this.name         = name;
        this.size         = size;
        this.location     = location;
        this.waterChanges = waterChanges;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<WaterChange> getWaterChanges() {
        return waterChanges;
    }

    public void setWaterChanges(List<WaterChange> waterChanges) {
        this.waterChanges = waterChanges;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Tank [id=" + id + ", name=" + name + ", size=" + size + ", location=" + location + ", waterChanges="
                + waterChanges + "]";
    }

}
