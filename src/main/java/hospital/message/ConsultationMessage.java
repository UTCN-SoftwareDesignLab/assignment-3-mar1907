package hospital.message;

public class ConsultationMessage {
    private int patientID;
    private String doctor;
    private String date;

    public ConsultationMessage() {
    }

    public ConsultationMessage(int patientID, String doctor, String date) {
        this.patientID = patientID;
        this.doctor = doctor;
        this.date = date;
    }

    public int getpatientID() {
        return patientID;
    }

    public void setpatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
