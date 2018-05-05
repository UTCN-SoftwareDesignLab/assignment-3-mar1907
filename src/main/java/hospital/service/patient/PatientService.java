package hospital.service.patient;

import hospital.dto.PatientDTO;
import hospital.entity.Patient;
import hospital.entity.validation.Notification;

import java.util.List;

public interface PatientService {
    List<Patient> getAll();
    Notification<Boolean> save(PatientDTO patientDTO);
    Notification<Boolean> update(PatientDTO patientDTO, Integer id);
}
