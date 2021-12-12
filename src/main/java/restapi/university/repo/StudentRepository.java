package restapi.university.repo;

import org.springframework.data.repository.CrudRepository;
import restapi.university.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
