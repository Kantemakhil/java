package net.syscon.s4.address.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.syscon.s4.address.AddressDTO;
import net.syscon.s4.address.AddressifyAddressDTO;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.globalconfiguration.impl.OumsysetServiceImpl;

@Service
public class AddressifyAddressValidation implements AddressValidation {
	private static Logger logger = LogManager.getLogger(AddressifyAddressValidation.class.getName());
    @Autowired
    private OumsysetService oumsysetService;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public AddressDTO validateAddress(String term) {
        Map<String, String> configMap = oumsysetService.getSettingValuesKeyValueMap("AddressConfig", "ADDRESSIFY_URL");
        String host = configMap.getOrDefault("ADDRSSIFY_URL", "");
        String decryptApiKey="";
        String uri = host + "/addresspro/info?api_key={api_key}&term={term}";
        Map<String, String> queryParam = new HashMap<>(2);
        try {
        if(configMap!=null && configMap.containsKey("ADDRSSIFY_APIKEY") && configMap.get("ADDRSSIFY_APIKEY")!=null) {
       	 Base64.Decoder decoder  = Base64.getDecoder();
    		 decryptApiKey = new String(decoder.decode(configMap.get("ADDRSSIFY_APIKEY")));
       }
        queryParam.put("api_key", decryptApiKey);
        queryParam.put("term", term);
        
            ResponseEntity<AddressifyAddressDTO> autoSegesstionResp = restTemplate.getForEntity(uri,
                    AddressifyAddressDTO.class, queryParam);
            if (autoSegesstionResp.getStatusCode().equals(HttpStatus.OK)) {
                return autoSegesstionResp.getBody();
            }
        } catch (Exception e) {
        	logger.error("error in validateAddress using addressify service:: "+e.getMessage());
        }
        return new AddressifyAddressDTO();
    }


    @Override
    public List<String> suggestionsAutoComplete(String term,String state) {
        Map<String, String> configMap = oumsysetService.getSettingValuesKeyValueMap("AddressConfig", "ADDRESSIFY_URL");
        String host = configMap.getOrDefault("ADDRSSIFY_URL", "");
        Map<String, String> queryParam = new HashMap<>(2);
        String uri=null;
        String decryptApiKey="";
        if(state.equals("") || state.equals("undefined")) {
        	  uri = host + "/addresspro/autocomplete?api_key={api_key}&term={term}";
        }else {
        	 uri = host + "/addresspro/autocomplete?api_key={api_key}&term={term}&state={state}";
        	queryParam.put("state", state);
        }
        
        
       
        try {
        if(configMap!=null && configMap.containsKey("ADDRSSIFY_APIKEY") && configMap.get("ADDRSSIFY_APIKEY")!=null) {
        	 Base64.Decoder decoder  = Base64.getDecoder();
     		 decryptApiKey = new String(decoder.decode(configMap.get("ADDRSSIFY_APIKEY")));
        }
       
        queryParam.put("api_key", decryptApiKey);
        queryParam.put("term", term);
       
            ResponseEntity<String[]> autoSegesstionResp = restTemplate.getForEntity(uri, String[].class, queryParam);
            if (autoSegesstionResp.getStatusCode().equals(HttpStatus.OK)) {
                String[] body = autoSegesstionResp.getBody();
                return Arrays.asList(body);
            }
        } catch (Exception e) {
        	logger.error("error in suggestionsAutoComplete using addressify service:: "+e.getMessage());
        }
        return new ArrayList<>(1);
    }

    @Override
    public AddressType getType() {
        return AddressType.ADDRESSIFY;
    }
}
