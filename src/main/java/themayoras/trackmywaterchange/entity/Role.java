package themayoras.trackmywaterchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_user_roles")
    private int id;
    
    @Column(name="role")
    private String role;
    
    public Role() {
    }

    public Role(String role) {
	this.role = role;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
	return "Role [id=" + this.id + ", role=" + this.role + "]";
    }
    
    
    
}
