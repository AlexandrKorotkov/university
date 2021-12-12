package restapi.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restapi.university.model.Teacher;
import restapi.university.repo.TeacherRepository;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;
    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable("id") Long id){
        return teacherRepository.findById(id).orElseThrow();
    }
    @PostMapping("/add")
    public String createTeacher(@RequestParam String name) {
        if (!teacherRepository.existsTeacherByName(name)) {
            Teacher teacher = new Teacher(name);
            return String.valueOf(teacherRepository.save(teacher).getId());
        }
        return "Teacher already exists.";
    }
    @PatchMapping("/{id}")
    public void editTeachersName(@PathVariable("id") Long id, @RequestParam String name){
        teacherRepository.findById(id).orElseThrow().setName(name);
    }
    @DeleteMapping("/{id}")
    public void deleteTeacherById(@PathVariable ("id") Long id){
        teacherRepository.deleteById(id);
    }
    @DeleteMapping("/{name}")
    public void deleteTeacherByName(@PathVariable ("name") String name){
        teacherRepository.deleteById(teacherRepository.findTeacherByName(name).getId());
    }
}

