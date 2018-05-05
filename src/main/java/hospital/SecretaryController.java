package hospital;

import hospital.dto.ConsultationDTO;
import hospital.dto.PatientDTO;
import hospital.dto.UserDTO;
import hospital.entity.validation.Notification;
import hospital.service.consultation.ConsultationService;
import hospital.service.patient.PatientService;
import hospital.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecretaryController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private UserService userService;
    @Autowired
    private ConsultationService consultationService;

    @RequestMapping(value = "/secretary", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("patients",patientService.getAll());
        model.addAttribute("patient",new PatientDTO());
        return "secretary";
    }

    @RequestMapping(value = "/secretary", method = RequestMethod.POST, params = "Create=Create")
    public String create(@ModelAttribute("patient") PatientDTO patient, Model model){
        Notification<Boolean> notification = patientService.save(patient);
        if(notification.hasErrors()){
            model.addAttribute("result",notification.getFormattedErrors());
        } else {
            model.addAttribute("result","Creation succesful!");
        }

        model.addAttribute("patients",patientService.getAll());
        return "secretary";
    }

    @RequestMapping(value = "/secretary", method = RequestMethod.POST, params = "Update=Update")
    public String update(@ModelAttribute("patient") PatientDTO patient, @RequestParam int id, Model model){
        Notification<Boolean> notification = patientService.update(patient, id);
        if(notification.hasErrors()){
            model.addAttribute("result",notification.getFormattedErrors());
        } else {
            model.addAttribute("result","Update succesful!");
        }

        model.addAttribute("patients",patientService.getAll());
        return "secretary";
    }

    @RequestMapping(value = "/secretary/consultations", method = RequestMethod.GET)
    public String indexConsultations(Model model){
        model.addAttribute("patients",patientService.getAll());
        model.addAttribute("doctors",userService.getDoctors());
        model.addAttribute("consultations",consultationService.getAll());
        model.addAttribute("consultation",new ConsultationDTO());
        return "consultations";
    }
}
