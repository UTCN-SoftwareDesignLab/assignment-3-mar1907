package hospital;

import hospital.dto.PatientDTO;
import hospital.entity.validation.Notification;
import hospital.service.consultation.ConsultationService;
import hospital.service.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DoctorController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private ConsultationService consultationService;

    @RequestMapping(value = "/doctor", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("patients",patientService.getAll());
        return "doctor";
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.POST, params = "Consultations=Consultations")
    public String getConsultations(@RequestParam int id, Model model){
        model.addAttribute("consultations",consultationService.getByPatient(id));

        model.addAttribute("patients",patientService.getAll());
        return "doctor";
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.POST, params = "Update=Update")
    public String update(@RequestParam int cid, @RequestParam String details, Model model){
        Notification<Boolean> notification = consultationService.updateDetails(cid,details);

        if(notification.hasErrors()){
            model.addAttribute("result",notification.getFormattedErrors());
        } else {
            model.addAttribute("result","Update succesful!");
        }

        model.addAttribute("patients",patientService.getAll());
        return "doctor";
    }
}
