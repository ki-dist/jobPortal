package act.com.JobPortal.repository;

import act.com.JobPortal.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileModel,String> {
}
