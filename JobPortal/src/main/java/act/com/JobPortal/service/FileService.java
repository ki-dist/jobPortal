package act.com.JobPortal.service;

import act.com.JobPortal.model.FileModel;
import act.com.JobPortal.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

//import javax.ws.rs.Path;
import java.io.IOException;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FileService {
    private FileRepository fileRepository;
   // private Path foundFile;
    public FileModel store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileModel fileModel = new FileModel(fileName, file.getContentType(), file.getBytes());

        return fileRepository.save(fileModel);
    }

    public FileModel getFile(String id) {
        return fileRepository.findById(id).get();
    }

    public Stream<FileModel> getAllFiles() {
        return fileRepository.findAll().stream();
    }

    public FileModel getById(String id){
        return fileRepository.findById(id).orElse(null);
    }
    public String deleteData(String id){
        fileRepository.deleteById(id);
        return "data deleted ||" +id;
    }
    /*public Resource getFileAsResource(String fileCode) throws IOException {
        Path dirPath = Paths.get("Files-Upload");

        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile = file;
                return;
            }
        });

        if (foundFile != null) {
            return new UrlResource(foundFile.toUri());
        }

        return null;
    }*/
}
