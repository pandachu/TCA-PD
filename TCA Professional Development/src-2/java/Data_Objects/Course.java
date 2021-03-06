/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Objects;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Panda
 */
@Entity
@Table(name = "course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findByCourseId", query = "SELECT c FROM Course c WHERE c.courseId = :courseId"),
    @NamedQuery(name = "Course.findByCourseName", query = "SELECT c FROM Course c WHERE c.courseName = :courseName"),
    @NamedQuery(name = "Course.findByCourseType", query = "SELECT c FROM Course c WHERE c.courseType = :courseType"),
    @NamedQuery(name = "Course.findByCourseRoom", query = "SELECT c FROM Course c WHERE c.courseRoom = :courseRoom"),
    @NamedQuery(name = "Course.findByCourseDetails", query = "SELECT c FROM Course c WHERE c.courseDetails = :courseDetails"),
    @NamedQuery(name = "Course.findByTotalSeats", query = "SELECT c FROM Course c WHERE c.totalSeats = :totalSeats"),
    @NamedQuery(name = "Course.findByDescription", query = "SELECT c FROM Course c WHERE c.description = :description"),
    @NamedQuery(name = "Course.findByStatus", query = "SELECT c FROM Course c WHERE c.status = :status"),
    @NamedQuery(name = "Course.findByDate", query = "SELECT c FROM Course c WHERE c.date = :date"),
    @NamedQuery(name = "Course.findByHours", query = "SELECT c FROM Course c WHERE c.hours = :hours"),
    @NamedQuery(name = "Course.findByEquipment", query = "SELECT c FROM Course c WHERE c.equipment = :equipment"),
    @NamedQuery(name = "Course.findByCourseInstructor", query = "SELECT c FROM Course c WHERE c.courseInstructor = :courseInstructor"),
    @NamedQuery(name = "Course.findByCourseAssistants", query = "SELECT c FROM Course c WHERE c.courseAssistants = :courseAssistants"),
    @NamedQuery(name = "Course.findByBeginTime", query = "SELECT c FROM Course c WHERE c.beginTime = :beginTime"),
    @NamedQuery(name = "Course.findByEndTime", query = "SELECT c FROM Course c WHERE c.endTime = :endTime"),
    @NamedQuery(name = "Course.findByCourseTopic", query = "SELECT c FROM Course c WHERE c.courseTopic = :courseTopic")})
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "course_id")
    private Integer courseId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "course_name")
    private String courseName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "course_type")
    private String courseType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "course_room")
    private String courseRoom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "course_details")
    private String courseDetails;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_seats")
    private int totalSeats;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hours")
    private int hours;
    @Size(max = 45)
    @Column(name = "equipment")
    private String equipment;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "course_instructor")
    private String courseInstructor;
    @Size(max = 90)
    @Column(name = "course_assistants")
    private String courseAssistants;
    @Basic(optional = false)
    @NotNull
    @Column(name = "begin_time")
    @Temporal(TemporalType.TIME)
    private Date beginTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "course_topic")
    private String courseTopic;
    @JoinColumn(name = "course_instructor_id", referencedColumnName = "account_id")
    @ManyToOne(optional = false)
    private Account courseInstructorId;

    public Course() {
    }

    public Course(Integer courseId) {
        this.courseId = courseId;
    }

    public Course(Integer courseId, String courseName, String courseType, String courseRoom, String courseDetails, int totalSeats, String description, boolean status, Date date, int hours, String courseInstructor, Date beginTime, Date endTime, String courseTopic) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseType = courseType;
        this.courseRoom = courseRoom;
        this.courseDetails = courseDetails;
        this.totalSeats = totalSeats;
        this.description = description;
        this.status = status;
        this.date = date;
        this.hours = hours;
        this.courseInstructor = courseInstructor;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.courseTopic = courseTopic;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseRoom() {
        return courseRoom;
    }

    public void setCourseRoom(String courseRoom) {
        this.courseRoom = courseRoom;
    }

    public String getCourseDetails() {
        return courseDetails;
    }

    public void setCourseDetails(String courseDetails) {
        this.courseDetails = courseDetails;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(String courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    public String getCourseAssistants() {
        return courseAssistants;
    }

    public void setCourseAssistants(String courseAssistants) {
        this.courseAssistants = courseAssistants;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCourseTopic() {
        return courseTopic;
    }

    public void setCourseTopic(String courseTopic) {
        this.courseTopic = courseTopic;
    }

    public Account getCourseInstructorId() {
        return courseInstructorId;
    }

    public void setCourseInstructorId(Account courseInstructorId) {
        this.courseInstructorId = courseInstructorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseId != null ? courseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.courseId == null && other.courseId != null) || (this.courseId != null && !this.courseId.equals(other.courseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Data_Objects.Course[ courseId=" + courseId + " ]";
    }
    
}
