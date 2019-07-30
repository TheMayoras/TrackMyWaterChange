package themayoras.trackmywaterchange.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import themayoras.trackmywaterchange.entity.Tank;

public interface TankDao extends JpaRepository<Tank, Integer> {

	@Query(value = "SELECT tank FROM Tank tank WHERE tank.name =:namePattern AND tank.user.id = :userId")
	List<Tank> findByNameLikeForUser(@Param("userId") int userId, @Param("namePattern") String namePattern);

}
