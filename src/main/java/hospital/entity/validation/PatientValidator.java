package hospital.entity.validation;

import hospital.entity.Patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class PatientValidator {

    private static final String NAME_REGEX = "[a-zA-Z ]+";
    private static final String CNP_REGEX = "[0-9]{13}";
    private static final String ID_REGEX = "[A-Z]{2}[0-9]{6}";


    private final Patient patient;
    private final List<String> errors;

    public PatientValidator(Patient Patient) {
        this.patient = Patient;
        this.errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean validate(){
        validateName(patient.getName());
        validateIDCard(patient.getIdCard());
        validateCNP(patient.getCnp());
        validateDob(patient.getDob());

        return errors.isEmpty();
    }

    private void validateName(String name){
        if (!Pattern.compile(NAME_REGEX).matcher(name).matches()) {
            errors.add("Invalid name!");
        }
    }

    private void validateCNP(String cnp){
        if (!Pattern.compile(CNP_REGEX).matcher(cnp).matches()) {
            errors.add("Invalid cnp!");
        }
    }

    private void validateIDCard(String id){
        if (!Pattern.compile(ID_REGEX).matcher(id).matches()) {
            errors.add("Invalid idcard!");
        }
    }

    private void validateDob(Date dob){
        if(dob.after(new Date())){
            errors.add("Invalid date!");
        }
    }
}
