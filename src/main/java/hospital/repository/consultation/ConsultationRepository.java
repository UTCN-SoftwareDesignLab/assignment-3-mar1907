package hospital.repository.consultation;

import hospital.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation,Integer>{

    List<Consultation> getConsultationByPatientId(int patientId);
}
