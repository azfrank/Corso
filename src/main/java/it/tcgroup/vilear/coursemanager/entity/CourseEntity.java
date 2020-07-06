package it.tcgroup.vilear.coursemanager.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.tcgroup.vilear.coursemanager.entity.enumerated.*;
import it.tcgroup.vilear.coursemanager.entity.jsonb.dataType.JsonDataAttachmentType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "hibernate_lazy_initializer", "handler"})
@Table(name = "course")
@TypeDefs({
        @TypeDef(name = "JsonDataAttachmentType", typeClass = JsonDataAttachmentType.class)
})



public class CourseEntity implements Serializable  {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CourseStatusEnum status;

    @Column(name = "course_title")
    private String courseTitle;

    @Column(name = "max_numeric_of_participants")
    private Integer maxNumericOfParticipants;

    @Column(name = "creation_course_date")
    private Date creationCourseDate;

    @Column(name = "last_change_date")
    private Date lastChangeDate;

    @Column(name = "user_nick")
    private String userNick;

    @Column(name = "number_of_actual_participants")
    private Integer numberOfActualParticipants;

    public CourseEntity() {
    }

    public CourseEntity(UUID id, CourseStatusEnum status, String courseTitle, Integer maxNumericOfParticipants, Date creationCourseDate, Date lastChangeDate, String userNick, Integer numberOfActualParticipants) {
        this.id = id;
        this.status = status;
        this.courseTitle = courseTitle;
        this.maxNumericOfParticipants = maxNumericOfParticipants;
        this.creationCourseDate = creationCourseDate;
        this.lastChangeDate = lastChangeDate;
        this.userNick = userNick;
        this.numberOfActualParticipants = numberOfActualParticipants;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CourseStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CourseStatusEnum status) {
        this.status = status;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Integer getMaxNumericOfParticipants() {
        return maxNumericOfParticipants;
    }

    public void setMaxNumericOfParticipants(Integer maxNumericOfParticipants) {
        this.maxNumericOfParticipants = maxNumericOfParticipants;
    }

    public Date getCreationCourseDate() {
        return creationCourseDate;
    }

    public void setCreationCourseDate(Date creationCourseDate) {
        this.creationCourseDate = creationCourseDate;
    }

    public Date getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public Integer getNumberOfActualParticipants() {
        return numberOfActualParticipants;
    }

    public void setNumberOfActualParticipants(Integer numberOfActualParticipants) {
        this.numberOfActualParticipants = numberOfActualParticipants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (status != that.status) return false;
        if (courseTitle != null ? !courseTitle.equals(that.courseTitle) : that.courseTitle != null) return false;
        if (maxNumericOfParticipants != null ? !maxNumericOfParticipants.equals(that.maxNumericOfParticipants) : that.maxNumericOfParticipants != null)
            return false;
        if (creationCourseDate != null ? !creationCourseDate.equals(that.creationCourseDate) : that.creationCourseDate != null)
            return false;
        if (lastChangeDate != null ? !lastChangeDate.equals(that.lastChangeDate) : that.lastChangeDate != null)
            return false;
        if (userNick != null ? !userNick.equals(that.userNick) : that.userNick != null) return false;
        return numberOfActualParticipants != null ? numberOfActualParticipants.equals(that.numberOfActualParticipants) : that.numberOfActualParticipants == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (courseTitle != null ? courseTitle.hashCode() : 0);
        result = 31 * result + (maxNumericOfParticipants != null ? maxNumericOfParticipants.hashCode() : 0);
        result = 31 * result + (creationCourseDate != null ? creationCourseDate.hashCode() : 0);
        result = 31 * result + (lastChangeDate != null ? lastChangeDate.hashCode() : 0);
        result = 31 * result + (userNick != null ? userNick.hashCode() : 0);
        result = 31 * result + (numberOfActualParticipants != null ? numberOfActualParticipants.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "id=" + id +
                ", status=" + status +
                ", courseTitle='" + courseTitle + '\'' +
                ", maxNumericOfParticipants=" + maxNumericOfParticipants +
                ", creationCourseDate=" + creationCourseDate +
                ", lastChangeDate=" + lastChangeDate +
                ", userNick='" + userNick + '\'' +
                ", numberOfActualParticipants=" + numberOfActualParticipants +
                '}';
    }
}
