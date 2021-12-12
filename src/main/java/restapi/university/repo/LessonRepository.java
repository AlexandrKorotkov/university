package restapi.university.repo;

import org.springframework.data.repository.CrudRepository;
import restapi.university.model.Lesson;

import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson,Long> {
    List<Lesson> findLessonsByGroupId(Long groupId);
    List<Lesson> findLessonsByGroupIdAndDayOfWeek(Long groupId, String dayOfWeek);
}
