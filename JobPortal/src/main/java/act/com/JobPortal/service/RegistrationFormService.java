package act.com.JobPortal.service;

import act.com.JobPortal.model.RegistrationFormModel;
import act.com.JobPortal.repository.RegistrationFormRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import javax.ws.rs.ClientErrorException;
import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationFormService {
    private RegistrationFormRepository formRepository;
    public RegistrationFormModel create(RegistrationFormModel formModel){
        try{
        return formRepository.save(formModel);
        } catch (ObjectOptimisticLockingFailureException e) {
            var ex = new ClientErrorException(409, e);
            throw ex;
        }
    }
    public List<RegistrationFormModel> getAllApplicant(){
        try {
        List<RegistrationFormModel> formModels =formRepository.findAll();
        return formModels;
        } catch (ObjectOptimisticLockingFailureException e) {
            var ex = new ClientErrorException(409, e);
            throw ex;
        }
    }
   public RegistrationFormModel getById(int id){
        return formRepository.findById(id).orElse(null);
    }
    public String deleteData(int id){
        formRepository.deleteById(id);
        return "data deleted ||" +id;
    }
}

