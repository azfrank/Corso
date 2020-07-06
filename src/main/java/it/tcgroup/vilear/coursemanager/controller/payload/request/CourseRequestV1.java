package it.tcgroup.vilear.coursemanager.controller.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.enumerated.CourseStatusEnum;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

public class CourseRequestV1 {

    @JsonProperty("course_title")
    private String courseTitle;

    @JsonProperty("user_nick")
    private String userNick;

    @JsonProperty("status")
    private CourseStatusEnum status;

    @JsonProperty("max_numeric_of_participants")
    private Integer maxNumericOfParticipants;

    @JsonProperty("number_of_actual_participants")
    private Integer numberOfActualParticipants;

    @JsonProperty("creation_course_date")
    private Date creationCourseDate;

    @JsonProperty("last_change_date")
    private Date lastChangeDate;



    public CourseRequestV1() {
    }

    public CourseRequestV1(String courseTitle, String userNick, CourseStatusEnum status, Integer maxNumericOfParticipants, Integer numberOfActualParticipants, Date creationCourseDate, Date lastChangeDate) {
        this.courseTitle = courseTitle;
        this.userNick = userNick;
        this.status = status;
        this.maxNumericOfParticipants = maxNumericOfParticipants;
        this.numberOfActualParticipants = numberOfActualParticipants;
        this.creationCourseDate = creationCourseDate;
        this.lastChangeDate = lastChangeDate;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public CourseStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CourseStatusEnum status) {
        this.status = status;
    }

    public Integer getMaxNumericOfParticipants() {
        return maxNumericOfParticipants;
    }

    public void setMaxNumericOfParticipants(Integer maxNumericOfParticipants) {
        this.maxNumericOfParticipants = maxNumericOfParticipants;
    }

    public Integer getNumberOfActualParticipants() {
        return numberOfActualParticipants;
    }

    public void setNumberOfActualParticipants(Integer numberOfActualParticipants) {
        this.numberOfActualParticipants = numberOfActualParticipants;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseRequestV1 that = (CourseRequestV1) o;

        if (courseTitle != null ? !courseTitle.equals(that.courseTitle) : that.courseTitle != null) return false;
        if (userNick != null ? !userNick.equals(that.userNick) : that.userNick != null) return false;
        if (status != that.status) return false;
        if (maxNumericOfParticipants != null ? !maxNumericOfParticipants.equals(that.maxNumericOfParticipants) : that.maxNumericOfParticipants != null)
            return false;
        if (numberOfActualParticipants != null ? !numberOfActualParticipants.equals(that.numberOfActualParticipants) : that.numberOfActualParticipants != null)
            return false;
        if (creationCourseDate != null ? !creationCourseDate.equals(that.creationCourseDate) : that.creationCourseDate != null)
            return false;
        return lastChangeDate != null ? lastChangeDate.equals(that.lastChangeDate) : that.lastChangeDate == null;
    }

    @Override
    public int hashCode() {
        int result = courseTitle != null ? courseTitle.hashCode() : 0;
        result = 31 * result + (userNick != null ? userNick.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (maxNumericOfParticipants != null ? maxNumericOfParticipants.hashCode() : 0);
        result = 31 * result + (numberOfActualParticipants != null ? numberOfActualParticipants.hashCode() : 0);
        result = 31 * result + (creationCourseDate != null ? creationCourseDate.hashCode() : 0);
        result = 31 * result + (lastChangeDate != null ? lastChangeDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CourseRequestV1{" +
                "courseTitle='" + courseTitle + '\'' +
                ", userNick='" + userNick + '\'' +
                ", status=" + status +
                ", maxNumericOfParticipants=" + maxNumericOfParticipants +
                ", numberOfActualParticipants=" + numberOfActualParticipants +
                ", creationCourseDate=" + creationCourseDate +
                ", lastChangeDate=" + lastChangeDate +
                '}';
    }
}
