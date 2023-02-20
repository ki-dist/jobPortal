package act.com.JobPortal.controller;

import act.com.JobPortal.model.RegistrationFormModel;
import act.com.JobPortal.service.RegistrationFormService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/job")
@CrossOrigin
@AllArgsConstructor
public class RegistrationFormController {
    private RegistrationFormService formService;
    @PostMapping("/registration")
   // @RolesAllowed("/")
    public ResponseEntity saveData (@RequestBody RegistrationFormModel formModel){
        return new ResponseEntity(formService.create(formModel), HttpStatus.ACCEPTED);
    }
    @GetMapping("/allApplicant")
    public ResponseEntity<List<RegistrationFormModel>> getAll(){
        return new ResponseEntity<>(formService.getAllApplicant(),HttpStatus.ACCEPTED) ;
    }
    @DeleteMapping("/id")
    public String deletFormData(int id){
        return formService.deleteData(id);
    }
}
