package themayoras.trackmywaterchange.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import themayoras.trackmywaterchange.entity.Tank;

public interface TankDao extends JpaRepository<Tank, Integer> {
}
