package restapi.university.repo;

import org.springframework.data.repository.CrudRepository;
import restapi.university.model.Group;

public interface GroupRepository extends CrudRepository<Group,Long> {
    Group findByName(String name);
}
