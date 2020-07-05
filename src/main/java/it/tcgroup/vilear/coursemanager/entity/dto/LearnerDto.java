package it.tcgroup.vilear.coursemanager.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.enumerated.DegreeOfStudiesEnum;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class LearnerDto implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty( "name")
    private String name;

    @JsonProperty( "surname")
    private String surname;

    @JsonProperty( "fiscal_code")
    private String fiscalCode;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty( "date_of_birth")
    private Date dateOfBirth;

    @JsonProperty( "birth_place")
    private  String birthPlace;

    @JsonProperty( "phone")
    private  String phone;

    @JsonProperty( "email")
    private String email;

    @JsonProperty( "degree_of_studies")
    private DegreeOfStudiesEnum degreeOfStudies;

    @JsonProperty( "course_of_study")
    private String courseOfStudy;

    @JsonProperty( "note")
    private String note;

    @JsonProperty( "attachemnts")
    private List<Attachment> attachemnts;

    @JsonProperty("residential_address")
    private AddressDto residentialAddress;

    @JsonProperty("domicile_address")
    private AddressDto domicileAddress;

    @JsonProperty("domicile_equals_residential")
    private Boolean domicileEqualsResidential ;

    public LearnerDto() {
    }

    public LearnerDto(String id, String name, String surname, String fiscalCode, Date dateOfBirth, String birthPlace, String phone, String email, DegreeOfStudiesEnum degreeOfStudies, String courseOfStudy, String note, List<Attachment> attachemnts, AddressDto residentialAddress, AddressDto domicileAddress, Boolean domicileEqualsResidential) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
        this.dateOfBirth = dateOfBirth;
        this.birthPlace = birthPlace;
        this.phone = phone;
        this.email = email;
        this.degreeOfStudies = degreeOfStudies;
        this.courseOfStudy = courseOfStudy;
        this.note = note;
        this.attachemnts = attachemnts;
        this.residentialAddress = residentialAddress;
        this.domicileAddress = domicileAddress;
        this.domicileEqualsResidential = domicileEqualsResidential;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DegreeOfStudiesEnum getDegreeOfStudies() {
        return degreeOfStudies;
    }

    public void setDegreeOfStudies(DegreeOfStudiesEnum degreeOfStudies) {
        this.degreeOfStudies = degreeOfStudies;
    }

    public String getCourseOfStudy() {
        return courseOfStudy;
    }

    public void setCourseOfStudy(String courseOfStudy) {
        this.courseOfStudy = courseOfStudy;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Attachment> getAttachemnts() {
        return attachemnts;
    }

    public void setAttachemnts(List<Attachment> attachemnts) {
        this.attachemnts = attachemnts;
    }

    public AddressDto getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(AddressDto residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public AddressDto getDomicileAddress() {
        return domicileAddress;
    }

    public void setDomicileAddress(AddressDto domicileAddress) {
        this.domicileAddress = domicileAddress;
    }

    public Boolean getDomicileEqualsResidential() {
        return domicileEqualsResidential;
    }

    public void setDomicileEqualsResidential(Boolean domicileEqualsResidential) {
        this.domicileEqualsResidential = domicileEqualsResidential;
    }

    @Override
    public String toString() {
        return "LearnerDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", fiscalCode='" + fiscalCode + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", birthPlace='" + birthPlace + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", degreeOfStudies=" + degreeOfStudies +
                ", courseOfStudy='" + courseOfStudy + '\'' +
                ", note='" + note + '\'' +
                ", attachemnts=" + attachemnts +
                ", residentialAddress=" + residentialAddress +
                ", domicileAddress=" + domicileAddress +
                ", domicileEqualsResidential=" + domicileEqualsResidential +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LearnerDto that = (LearnerDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(fiscalCode, that.fiscalCode) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(birthPlace, that.birthPlace) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                degreeOfStudies == that.degreeOfStudies &&
                Objects.equals(courseOfStudy, that.courseOfStudy) &&
                Objects.equals(note, that.note) &&
                Objects.equals(attachemnts, that.attachemnts) &&
                Objects.equals(residentialAddress, that.residentialAddress) &&
                Objects.equals(domicileAddress, that.domicileAddress) &&
                Objects.equals(domicileEqualsResidential, that.domicileEqualsResidential);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, fiscalCode, dateOfBirth, birthPlace, phone, email, degreeOfStudies, courseOfStudy, note, attachemnts, residentialAddress, domicileAddress, domicileEqualsResidential);
    }
}
