package ma.enset.hospital_system.web;

import lombok.AllArgsConstructor;
import ma.enset.hospital_system.entities.Patient;
import ma.enset.hospital_system.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(Model model, int page, int size) {
        Page<Patient> pagePatients = patientRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("patients", pagePatients.getContent());
        return "patients";
    }
}
