package restapi.university.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //AUTO or IDENTITY?
    private Long id;

    private String name;
//    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JoinColumn(name = "groupId")

    private Long groupId;

    public Student() {
    }
    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Long getGroupId() {
        return groupId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return getId().equals(student.getId()) && Objects.equals(getName(), student.getName()) && Objects.equals(getGroupId(), student.getGroupId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getGroupId());
    }
}
