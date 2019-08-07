package themayoras.trackmywaterchange.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import themayoras.trackmywaterchange.entity.validation.Quantity;

@Entity
@Table(name = "water_changes")
public class WaterChange implements Trackable {

    // water change id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_water_change")
    private int id;

    // water change date
    @NotNull(message = "required")
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    // amount of the water change
    @Quantity(message = "cannot be negative")
    @Column(name = "amount")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "units")
    private QuantityUnits units;

    // comments about the water change
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "waterChange")
    private List<WaterChangeComment> comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tank")
    private Tank tank;

    public String quantityString() {
        return this.amount + " " + this.units;
    }

    @PrePersist
    public void saveWaterChange() {
        System.err.println("Water Change: " + this);
        removeAllEmptyComments();

        if (comments != null) {
            for (WaterChangeComment comment : comments) {
                comment.setWaterChange(this);
            }
        }
    }

    @PreRemove
    public void removeWaterChange() {
        this.tank.getWaterChanges().remove(this);
        this.tank = null;
    }

    public WaterChange() {
    }

    public WaterChange(Date date, double amount) {
        this.date   = date;
        this.amount = amount;
    }

    public WaterChange(Date date, double amount, List<WaterChangeComment> comments) {
        this.date     = date;
        this.amount   = amount;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public QuantityUnits getUnits() {
        return this.units;
    }

    public void setUnits(QuantityUnits units) {
        this.units = units;
    }

    public List<WaterChangeComment> getComments() {
        return comments;
    }

    public void setComments(List<WaterChangeComment> comments) {
        this.comments = comments;
    }

    public Tank getTank() {
        return this.tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    @Override
    public String toString() {
        return "WaterChange [id=" + id + ", date=" + date + ", amount=" + amount + ", comments=" + comments + "]";
    }

    @Override
    public String getType() {
        return "water-change";
    }

    @Override
    public String getDescription() {

        return "[amount=" + amount + ", " + "comments:" + comments + "]";
    }

    public void addComment(WaterChangeComment comment) {
        if (comments == null) {
            comments = new ArrayList<>();
        }

        comments.add(comment);
    }

    public void removeAllEmptyComments() {
        if (comments != null) {
            comments.removeIf(c -> c.isEmpty());
        }

    }

}
