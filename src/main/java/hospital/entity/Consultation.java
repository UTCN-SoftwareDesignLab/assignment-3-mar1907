package hospital.entity;

import hospital.dto.ConsultationDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String details;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public Consultation() {
    }

    public Consultation(String title, String details, Date date, Patient patient, User user) {
        this.title = title;
        this.details = details;
        this.date = date;
        this.patient = patient;
        this.user = user;
    }

    public Consultation(ConsultationDTO consultationDTO, Patient patient, User user){
        this.title = consultationDTO.title;
        this.details = consultationDTO.details;
        this.date = consultationDTO.date;
        this.patient = patient;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
