package restapi.university.model;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Objects;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private DayOfWeek dayOfWeek;
    private String subjectName;
    private Long teacherId;
    private Long groupId;

    public Lesson() {
    }

    public Lesson(DayOfWeek dayOfWeek, String subjectName, Long teacherId, Long groupId) {
        this.dayOfWeek = dayOfWeek;
        this.teacherId = teacherId;
        this.subjectName = subjectName;
        this.groupId = groupId;
    }

    public long getId() {
        return id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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
        Lesson lesson = (Lesson) o;
        return getId() == lesson.getId() && getDayOfWeek() == lesson.getDayOfWeek() && getSubjectName().equals(lesson.getSubjectName()) && Objects.equals(getTeacherId(), lesson.getTeacherId()) && Objects.equals(getGroupId(), lesson.getGroupId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDayOfWeek(), getSubjectName(), getTeacherId(), getGroupId());
    }
}
