package themayoras.trackmywaterchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "water_change_comments")
public class WaterChangeComment {

    // comment id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private int id;

    // comment body
    @Column(name = "comment")
    private String comment;

    // The parent water change
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_water_change")
    private WaterChange waterChange;

    public WaterChangeComment() {
    }

    public WaterChangeComment(String comment) {
        this.comment = comment;
    }

    public WaterChangeComment(int id, String comment) {
        this.id      = id;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public WaterChange getWaterChange() {
        return this.waterChange;
    }

    public void setWaterChange(WaterChange waterChange) {
        this.waterChange = waterChange;
    }

    @Override
    public String toString() {
        return "WaterChangeComment [id=" + id + ", comment=" + comment + "]";
    }
    
    public boolean isEmpty() {
        return this.comment.trim().isEmpty();
    }

}
