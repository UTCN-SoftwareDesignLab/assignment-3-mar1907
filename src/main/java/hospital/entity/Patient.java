package hospital.entity;

import hospital.dto.PatientDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String idCard;
    private String cnp;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dob;
    private String address;

    public Patient() {
    }

    public Patient(String name, String idCard, String cnp, Date dob, String address) {
        this.name = name;
        this.idCard = idCard;
        this.cnp = cnp;
        this.dob = dob;
        this.address = address;
    }

    public Patient(PatientDTO patientDTO){
        this.name = patientDTO.name;
        this.idCard = patientDTO.idCard;
        this.cnp = patientDTO.cnp;
        this.dob = patientDTO.dob;
        this.address = patientDTO.address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
