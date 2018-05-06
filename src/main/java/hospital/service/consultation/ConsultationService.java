package hospital.service.consultation;

import hospital.dto.ConsultationDTO;
import hospital.entity.Consultation;
import hospital.entity.validation.Notification;

import java.util.List;

public interface ConsultationService {

    List<Consultation> getAll();
    Notification<Boolean> save(ConsultationDTO consultationDTO, String doctor, Integer patientId);
    Notification<Boolean> update(Integer consultationId, ConsultationDTO consultationDTO, String doctor, Integer patientId);
    Notification<Boolean> delete(Integer consultationId);

    List<Consultation> getByPatient(int patientId);
    Notification<Boolean> updateDetails(int id, String details);
}
