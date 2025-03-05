package net.syscon.s4.globalconfiguration.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.syscon.s4.common.beans.JsonSpecification;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.JoltConverterRepository;
import net.syscon.s4.globalconfiguration.JoltConverterService;

@Service
public class JoltConverterServiceImpl extends BaseBusiness implements JoltConverterService {

    @Autowired
    private JoltConverterRepository connectServiceBusRep;

    @Override
    public String getJsonSpec(String specKey) {
        String jsonSpec = null;
        if (specKey != null) {
            JsonSpecification spec = connectServiceBusRep.getJsonSpecData(specKey);
            if (spec != null && spec.getJsonSpecs() != null) {
                jsonSpec = new String(spec.getJsonSpecs());

            }
        }

        return jsonSpec;
    }

    @Override
    public List<String> getAllSpecKey() {
        return connectServiceBusRep.getAllSpecKey();
    }

    @Override
    public String importProcesses(String specKey, MultipartFile importFile, String userName) {
        Boolean jsonExist = connectServiceBusRep.findJsonExist(specKey);
        String response = "";
        JsonSpecification jsonSpecification = new JsonSpecification();
        jsonSpecification.setSpecKey(specKey);
        jsonSpecification.setCreateUserId(userName);
        jsonSpecification.setModifyUserId(userName);
        try {
            jsonSpecification.setJsonSpecs(importFile.getBytes());
            int responseCount = 0;
            if (!jsonExist) {
                responseCount = connectServiceBusRep.insertJsonData(jsonSpecification);
                if (responseCount > 0) {
                    response = "Record Inserted Successfully.";
                } else {
                    response = "Record Insertion Failed.";
                }
            } else {
                responseCount = connectServiceBusRep.updateJsonData(jsonSpecification);
                if (responseCount > 0) {
                    response = "Record Updated Successfully.";
                } else {
                    response = "Record Updation Failed.";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
