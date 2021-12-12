package restapi.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restapi.university.model.Lesson;
import restapi.university.model.Student;
import restapi.university.repo.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GroupController groupController;
    @Autowired
    private LessonController lessonController;

    @GetMapping("/all")
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    @GetMapping("/{id}/lessons/all")
    public List<Lesson> getScheduleFoStudent(@PathVariable ("id") Long studentId){
        Long groupId = studentRepository.findById(studentId).orElseThrow().getGroupId();
        return lessonController.getAllLessonsForGroup(groupId);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") long id) {
        return studentRepository.findById(id).get();
    }

    @PostMapping("/new")
    public long createStudent(@RequestParam String name) {
        Student student = new Student(name);
        return studentRepository.save(student).getId();
    }

    @PatchMapping("/{id}/move")
    public String moveStudentToGroup(@PathVariable("id") long studentId, @RequestParam long groupId) {
        if (groupController.isGroupExist(groupId)) {
            Student student = getStudentById(studentId);
            student.setGroupId(groupId);
            studentRepository.save(student);

            return "Student " + studentId + " has moved to Group " + groupId;
        }
        return "Wrong groupId";
    }


    @PatchMapping("/{id}")
    public void updateStudentName(@PathVariable("id") Long studentId, @RequestParam String newName) {
        if (studentRepository.existsById(studentId)) {
            Student student = getStudentById(studentId);
            student.setName(newName);
            studentRepository.save(student);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable long id) {
        studentRepository.deleteById(id);

    }

}
