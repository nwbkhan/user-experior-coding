package com.nwb.userexperior.biometric.service;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nwb.userexperior.biometric.models.response.ActTrackResponse;
import com.nwb.userexperior.biometric.persistence.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class RecordProcessService {

    private final static Logger logger = LoggerFactory.getLogger(RecordProcessService.class);
    private final static Pattern pattern = Pattern.compile("(.)*\\.json$");

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.EAGER_SERIALIZER_FETCH, false);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }


    ActTrackResponse process(String dir) {
        List<Staff> staffs = new ArrayList<>();
        File filesDir = new File(dir);
        List<String> errors = new ArrayList<>();

        // read file by file
        if (filesDir.isDirectory()) {
            final File[] allJsonFiles =
                    filesDir.listFiles(isJsonFIle());

            if (allJsonFiles == null) {
                return ActTrackResponse
                        .builder()
                        .build();
            }
            for (File staffFile : allJsonFiles) {
                mapForFile(staffs, errors, staffFile);
            }
        } else {
            errors.add("Dir is not correct - " + dir);
        }
        return ActTrackResponse
                .builder()
                .errors(errors)
                .staffs(staffs)
                .build();
    }

    private void mapForFile(List<Staff> staffs, List<String> errors, File staffFile) {
        try {
            // stringify the contents of file
            String fileContents = getContentForFile(staffFile);
            if (fileContents != null) {
                try {
                    // parse the contents of file
                    Staff staff = mapFileToStaff(fileContents);
                    // add it to main list
                    staffs.add(staff);
                } catch (JsonProcessingException e) {
                    errors.add(e.getMessage());
                }
            }
        } catch (IOException e) {
            errors.add(e.getMessage());
        }
    }

    private Staff mapFileToStaff(String fileContents) throws JsonProcessingException {
        return mapper.readValue(fileContents, Staff.class);
    }

    private String getContentForFile(File staffFile) throws IOException {
        if (staffFile.isFile() && staffFile.canRead()) {
            BufferedInputStream bufferedInputStream =
                    new BufferedInputStream(new FileInputStream(staffFile));
            byte[] fileData = new byte[1024];
            StringBuilder stringBuilder = new StringBuilder();
            while (bufferedInputStream.read(fileData) != -1) {
                stringBuilder.append(new String(fileData));
            }
            return stringBuilder.toString();
        } else {
            logger.error("file - {} is not valid file or not readable", staffFile.getName());
            return null;
        }
    }

    private FileFilter isJsonFIle() {
        return x -> pattern.matcher(x.getName()).matches();
    }

}
