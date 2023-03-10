package act.com.JobPortal.controller;

import act.com.JobPortal.dto.ResponseMessage;
import act.com.JobPortal.dto.ResponsefileDto;
import act.com.JobPortal.model.FileModel;
import act.com.JobPortal.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file")
@AllArgsConstructor

public class FileController {
    private FileService fileService;
    @PostMapping("/upload")
    //@RolesAllowed("USER")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            fileService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    //@RolesAllowed("ADMIN")
    public ResponseEntity<List<ResponsefileDto>> getListFiles() {
        List<ResponsefileDto> files = fileService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponsefileDto(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
   // @RolesAllowed("ADMIN")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileModel fileModel = fileService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileModel.getName() + "\"")
                .body(fileModel.getData());
    }
    @DeleteMapping("/id")
    public String deletData(String id){
        return fileService.deleteData(id);
    }
}
