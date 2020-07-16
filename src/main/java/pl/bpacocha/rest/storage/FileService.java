package pl.bpacocha.rest.storage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    public String headerFile = "PRIMARY_KEY,NAME,DESCRIPTION,UPDATED_TIMESTAMP";

    @Autowired
    private FileServiceInterface fileServiceInterface;

    public void uploadFile(MultipartFile file) {

        try {
            Path copyLocation = Paths
                .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

            List<String> lines = Files.readAllLines(Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename())));

            List<DataModel> dataModels =  ParseLines(lines);

        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                + ". Please try again!");
        }
    }

    private List<DataModel> ParseLines(List<String> lines) {

        List<DataModel> dataModels = new ArrayList<>();

        lines.forEach(
                line -> {
                    if (!line.equals(headerFile)) {
                        String[] regexLine = line.split(",");
                        if (regexLine.length == 4) {
                            DataModel dataModel = new DataModel(regexLine);
                            dataModels.add(dataModel);
                            fileServiceInterface.save(dataModel);
                        }
                    }
                }
        );

        return dataModels;
    }

    public List<DataModel> findAll() {
        return fileServiceInterface.findAll();
    }

    public void deleteDataModelById(String id) {
        fileServiceInterface.deleteById(id);
    }

    public DataModel getById(String id) {
        return fileServiceInterface.getOne(id);
    }

    public List<DataModel> findBetweenDates(String fromTime, String toTime) {

        List<DataModel> dataModels = new ArrayList<>();

        List<DataModel> all = fileServiceInterface.findAll();

        Timestamp from = Utils.ConvertStringToTimestamp(fromTime, "yyyyMMdd");
        Timestamp to = Utils.ConvertStringToTimestamp(toTime, "yyyyMMdd");

        dataModels = all.stream()

                .filter(dataModel -> dataModel.getTIMESTAMP().before(to) && dataModel.getTIMESTAMP().after(from))
                .collect(Collectors.toList());

        return dataModels;

    }

}
