package restapi.university.repo;

import org.springframework.data.repository.CrudRepository;
import restapi.university.model.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Teacher findTeacherByName(String name);
    boolean existsTeacherByName(String name);

}
