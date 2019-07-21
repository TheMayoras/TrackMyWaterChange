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
import javax.persistence.OneToMany;
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
	private double size;

	// location of the tank
	@TankLocation
	@Column(name = "location")
	private String location;

	// list of water changes on the tank.
	// LAZY. There can be a lot of water changes, so we need want lazy
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "id_tank")
	private List<WaterChange> waterChanges;
	
	public void addWaterChange(WaterChange waterChange) {
		if (waterChanges == null) {
			waterChanges = new ArrayList<>();
		}
		
		waterChanges.add(waterChange);
	}

	public Tank() {
	}

	public Tank(String name, double size, String location) {
		this.name = name;
		this.size = size;
		this.location = location;
	}

	public Tank(String name, double size, String location, List<WaterChange> waterChanges) {
		this.name = name;
		this.size = size;
		this.location = location;
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

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
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

	@Override
	public String toString() {
		return "Tank [id=" + id + ", name=" + name + ", size=" + size + ", location=" + location + ", waterChanges="
				+ waterChanges + "]";
	}

}
