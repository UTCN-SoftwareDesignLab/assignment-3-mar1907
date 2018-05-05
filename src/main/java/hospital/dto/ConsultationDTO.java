package hospital.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ConsultationDTO {

    public String title;
    public String details;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date date;

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
}
