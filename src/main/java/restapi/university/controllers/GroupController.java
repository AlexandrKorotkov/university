package restapi.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restapi.university.model.Group;
import restapi.university.repo.GroupRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    public boolean isGroupExist(long id) {
        return groupRepository.existsById(id);
    }

    public boolean isGroupExist(String groupName) {
        return groupRepository.findByName(groupName) != null;
    }

    public Group getGroupById(long id) {
        return groupRepository.findById(id).orElseThrow();
    }

    @GetMapping("/all")
    public List<Group> getAllGroups() {
        List<Group> allGroups = new ArrayList<>();
        for (Group g : groupRepository.findAll()) {
            allGroups.add(g);
        }
        return allGroups;
    }

    @GetMapping("/{id}")
    public Group getGroup(@PathVariable("id") long id) {
        return groupRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public String createGroup(@RequestParam String name) {
        if (!isGroupExist(name)) {
            Group group = new Group(name);
            return String.valueOf(groupRepository.save(group).getId());
        }
        return "Group " + name + " already exist.";
    }

    @PatchMapping("/{id}")
    public String changeGroupName(@PathVariable("id") long id, @RequestParam String newName) {
        if (isGroupExist(id)) {
            Group group = groupRepository.findById(id).get();
            if (!group.getName().equals(newName)) {
                group.setName(newName);
                groupRepository.save(group);
                return "Group name has changed successfully.";
            }
            return "New group name is the same as old one";
        }
        return "Wrong group id.";
    }

    @DeleteMapping("/{id}")
    public String deleteGroupById(@PathVariable("id") long id) {
        if (isGroupExist(id)) {
            groupRepository.deleteById(id);
            return "Group deleted successfully.";
        }
        return "Wrong group id.";
    }
}
