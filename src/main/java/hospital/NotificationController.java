package hospital;

import hospital.entity.Patient;
import hospital.entity.User;
import hospital.message.ConsultationMessage;
import hospital.message.Notification;
import hospital.service.patient.PatientService;
import hospital.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class NotificationController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    @MessageMapping("/consultation")
    @SendTo("/message/consultation")
    public Notification greeting(ConsultationMessage message) throws Exception {
        Patient patient = patientService.findById(message.getpatientID());
        User user = userService.findByName(message.getDoctor());

        return new Notification("Consultation with " + patient.getName() + " on " + message.getDate()
                + " for " + user.getUsername() + ".\n");
    }

}
