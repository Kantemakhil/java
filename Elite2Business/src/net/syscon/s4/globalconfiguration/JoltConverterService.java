package net.syscon.s4.globalconfiguration;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface JoltConverterService {
       
        String getJsonSpec(String Key);

        List<String> getAllSpecKey();

        String importProcesses(String specKey, MultipartFile importFile, String userName);
}
