package hospital.service.consultation;

import hospital.dto.ConsultationDTO;
import hospital.entity.Consultation;
import hospital.entity.Patient;
import hospital.entity.User;
import hospital.entity.validation.ConsultationValidator;
import hospital.entity.validation.Notification;
import hospital.repository.consultation.ConsultationRepository;
import hospital.repository.patient.PatientRepository;
import hospital.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    private ConsultationRepository consultationRepository;
    private PatientRepository patientRepository;
    private UserRepository userRepository;

    @Autowired
    public ConsultationServiceImpl(ConsultationRepository consultationRepository, PatientRepository patientRepository, UserRepository userRepository) {
        this.consultationRepository = consultationRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Consultation> getAll() {
        return consultationRepository.findAll();
    }

    @Override
    public Notification<Boolean> save(ConsultationDTO consultationDTO, String doctor, Integer patientId) {
        User user = userRepository.findUserByUsername(doctor);
        Patient patient = patientRepository.findPatientById(patientId);

        Consultation consultation = new Consultation(consultationDTO,patient,user);
        ConsultationValidator validator = new ConsultationValidator(consultation);
        boolean valid = validator.validate();
        Notification<Boolean> notification = new Notification<>();

        if(!valid){
            validator.getErrors().forEach(notification::addError);
            notification.setResult(Boolean.FALSE);
            return notification;
        } else {
            consultationRepository.save(consultation);
            notification.setResult(Boolean.TRUE);
            return notification;
        }
    }

    @Override
    public Notification<Boolean> update(Integer consultationId, ConsultationDTO consultationDTO, String doctor, Integer patientId) {
        User user = userRepository.findUserByUsername(doctor);
        Patient patient = patientRepository.findPatientById(patientId);

        Consultation consultation = new Consultation(consultationDTO,patient,user);
        consultation.setId(consultationId);
        ConsultationValidator validator = new ConsultationValidator(consultation);
        boolean valid = validator.validate();
        Notification<Boolean> notification = new Notification<>();

        if(!valid){
            validator.getErrors().forEach(notification::addError);
            notification.setResult(Boolean.FALSE);
            return notification;
        } else {
            consultationRepository.save(consultation);
            notification.setResult(Boolean.TRUE);
            return notification;
        }
    }

    @Override
    public Notification<Boolean> delete(Integer consultationId) {
        consultationRepository.delete(consultationId);
        Notification<Boolean> notification = new Notification<>();
        notification.setResult(Boolean.TRUE);
        return notification;
    }
}
