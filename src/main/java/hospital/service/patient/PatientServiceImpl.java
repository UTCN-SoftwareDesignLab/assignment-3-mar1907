package hospital.service.patient;

import hospital.dto.PatientDTO;
import hospital.entity.Patient;
import hospital.entity.validation.Notification;
import hospital.entity.validation.PatientValidator;
import hospital.repository.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Notification<Boolean> save(PatientDTO patientDTO) {
        Patient patient = new Patient(patientDTO);

        PatientValidator validator = new PatientValidator(patient);
        boolean patientValid = validator.validate();
        Notification<Boolean> notification = new Notification<>();

        if(!patientValid){
            validator.getErrors().forEach(notification::addError);
            notification.setResult(Boolean.FALSE);
            return notification;
        } else {
            patientRepository.save(patient);
            notification.setResult(Boolean.TRUE);
            return notification;
        }
    }

    @Override
    public Notification<Boolean> update(PatientDTO patientDTO, Integer id) {
        Patient patient = new Patient(patientDTO);
        patient.setId(id);

        PatientValidator validator = new PatientValidator(patient);
        boolean patientValid = validator.validate();
        Notification<Boolean> notification = new Notification<>();

        if(!patientValid){
            validator.getErrors().forEach(notification::addError);
            notification.setResult(Boolean.FALSE);
            return notification;
        } else {
            patientRepository.save(patient);
            notification.setResult(Boolean.TRUE);
            return notification;
        }
    }

    @Override
    public Patient findById(int id) {
        return patientRepository.findPatientById(id);
    }
}
