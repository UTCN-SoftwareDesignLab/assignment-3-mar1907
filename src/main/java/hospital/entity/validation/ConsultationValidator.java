package hospital.entity.validation;

import hospital.entity.Consultation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultationValidator {

    private final Consultation consultation;
    private final List<String> errors;

    public ConsultationValidator(Consultation consultation) {
        this.consultation = consultation;
        this.errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean validate() {
        validateDate(consultation.getDate());
        return errors.isEmpty();
    }

    private void validateDate(Date dob) {
        if (dob.before(new Date())) {
            errors.add("Invalid date!");
        }
    }
}
