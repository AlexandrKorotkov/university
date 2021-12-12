package restapi.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restapi.university.model.Lesson;
import restapi.university.repo.LessonRepository;

import javax.persistence.Transient;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    @Transient
    private List<Lesson> allLessons;
    @Autowired
    private LessonRepository lessonRepository;

    @PostMapping("/add")
    public Long createLesson(@RequestParam String dayOfWeek, @RequestParam Long teacherId,
                             @RequestParam Long groupId, @RequestParam String name) {
        DayOfWeek dayOfWeek1 = DayOfWeek.valueOf(dayOfWeek.trim().toUpperCase(Locale.ROOT));
        Lesson lesson = new Lesson(dayOfWeek1, name, teacherId, groupId);
        return lessonRepository.save(lesson).getId();
    }

    @GetMapping("/{id}")
    public Lesson getLessonById(@PathVariable Long id) {
        return lessonRepository.findById(id).orElseThrow();
    }

    @GetMapping("/all")
    public List<Lesson> getAllLessons() {
        List<Lesson> lessonList = new ArrayList<>();
        for (Lesson l : lessonRepository.findAll()) {
            lessonList.add(l);
        }
        return lessonList;
    }

    @GetMapping("/{group}/all")
    public List<Lesson> getAllLessonsForGroup(@PathVariable("group") Long groupId) {
        return lessonRepository.findLessonsByGroupId(groupId);
    }

    @GetMapping("/{group}/{day}")
    public List<Lesson> getLessonsForGroupAtDay(@PathVariable("group") Long groupId,
                                                @PathVariable("day") String day) {
        return lessonRepository.findLessonsByGroupIdAndDayOfWeek(groupId,day);
    }

    @PatchMapping("/{id}/edit/day")
    public void editDayOfWeek(@PathVariable("id") Long id, @RequestParam String newDay) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow();
        lesson.setDayOfWeek(DayOfWeek.valueOf(newDay));
        lessonRepository.save(lesson);
    }

    @PatchMapping("/{id}/edit/name")
    public void editSubjectName(@PathVariable("id") Long id, @RequestParam String newName) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow();
        lesson.setSubjectName(newName);
        lessonRepository.save(lesson);
    }

    @PatchMapping("/{id}/edit/teacher")
    public void editTeacher(@PathVariable("id") Long id, @RequestParam Long teacherId) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow();
        lesson.setTeacherId(teacherId);
        lessonRepository.save(lesson);
    }

    @PatchMapping("/{id}/edit/group")
    public void editGroup(@PathVariable("id") Long id, @RequestParam Long groupId) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow();
        lesson.setGroupId(groupId);
        lessonRepository.save(lesson);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        lessonRepository.deleteById(id);
    }

//    @DeleteMapping
//    public void clearWholeDay(@RequestParam String day){
//        lessonRepository.de
//    }

}
