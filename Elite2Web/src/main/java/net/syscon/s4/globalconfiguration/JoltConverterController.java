package net.syscon.s4.globalconfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.EliteController;

@EliteController
public class JoltConverterController {
	
	@Autowired
	private JoltConverterService connectServiceBusService;
	private static Logger logger = LogManager.getLogger(JoltConverterController.class.getName());
	
	
	@RequestMapping(value = "/serviceBusController/convertJson", method = RequestMethod.POST)
	public @ResponseBody Object getConvertedJson(@RequestBody String data) {
		Chainr chainr = null;
		Object transformedJsonOutput = null;
		String jsoSpec =null;
		Map<String, Object> inputData = new HashMap<>();
		Map<String, Object> newMemoModel = null;
		ObjectMapper mapper = new ObjectMapper();
		logger.info("Jolt_getConvertedJson:inputData"+data);
		try {
			if(data!=null) {
				data = data.replaceAll("\"\\[", "[").replaceAll("]\"","]").replaceAll("\"\\{", "{").replaceAll("}\"","}").replaceAll("\\\\", "");
				logger.info("Jolt_getConvertedJson:inputData"+data);
			}
			
			if(data!=null) {
				newMemoModel = new ObjectMapper().configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true).readValue(data, HashMap.class);
				jsoSpec=connectServiceBusService.getJsonSpec(newMemoModel.get("specificationKey").toString());
				logger.info("Jolt_getConvertedJson:jsonSpec"+jsoSpec);
			}
			
			
			
			if(jsoSpec!=null) {
				List<HashMap<String, Object>> jsonMap = mapper.readValue(jsoSpec,
                        new TypeReference<List<HashMap<String, Object>>>(){});
				logger.info("Jolt_getConvertedJson:jsonSpecList"+jsonMap);
				chainr = Chainr.fromSpec(jsonMap);
				if (chainr != null && data!= null) {
					transformedJsonOutput = chainr.transform(newMemoModel);
					logger.info("Jolt_getConvertedJson:convertedObject" + JsonUtils.toJsonString(transformedJsonOutput));
				}
			}
		        
			
		} catch (Exception e) {
			logger.error("Jolt_getConvertedJson : error " + e.getMessage());
			
		}

		return transformedJsonOutput;

	}
	
	 @RequestMapping(value = "/joltspecs", method = RequestMethod.GET)
	    public List<String> getSpecKey() {
	        List<String> allSpecKey = null;
	        try {
	            allSpecKey = connectServiceBusService.getAllSpecKey();
	        } catch (Exception e) {
	            logger.error("Exception :", e);
	            return allSpecKey;
	        }
	        return allSpecKey;
	    }

	    @RequestMapping(value = "/joltjsonspecs", method = RequestMethod.GET, produces = "application/json")
	    public String getJsonSpec(@QueryParam("specKey") String specKey) {
	        String jsonSpec = null;
	        try {
	            jsonSpec = connectServiceBusService.getJsonSpec(specKey);
	        } catch (Exception e) {
	            logger.error("Exception :", e);
	            return jsonSpec;
	        }
	        return jsonSpec;
	    }

	    @RequestMapping(value = "/joltspec", method = RequestMethod.POST)
	    public @ResponseBody String importProcesses(HttpServletRequest httpServletRequest,
	            @RequestParam("importFile") MultipartFile importFile, @RequestParam("specKey") String specKey) {

	        String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
	        String importedPrecesses = "";
	        try {
	            importedPrecesses = connectServiceBusService.importProcesses(specKey, importFile, userName);
	        } catch (Exception e) {
	            logger.error("Exception :", e);
	            return importedPrecesses;

	        }
	        return importedPrecesses;
	    }

	
}
