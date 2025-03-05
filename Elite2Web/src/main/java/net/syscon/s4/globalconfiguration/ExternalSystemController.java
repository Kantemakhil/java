package net.syscon.s4.globalconfiguration;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.externalmessage.service.ExternalMessageService;

@EliteController
public class ExternalSystemController {

	private static Logger logger = LogManager.getLogger(ExternalSystemController.class.getName());

	@Autowired
	ExternalMessageService externalMessageService;

	@RequestMapping(value = "/externalsystem/connectExternalSystem", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> connectExternalSystem(@RequestBody String data) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			response = externalMessageService.sendMessage(data);
			} catch (Exception e) {
				logger.error("ExternalSystemController:Exception in sendMessage "+e.getMessage());
			}
		return response;
	}

	@RequestMapping(value = "/externalsystem/convertJson", method = RequestMethod.POST)
	public @ResponseBody Object getConvertedJson(@RequestBody Map<String,Object> elements) {
		Chainr chainr = null;
		Object transformedJsonOutput = null;
		try {
			//JSONObject jObject = new JSONObject(data);
			BigDecimal offenderBookId = null;
			/*logger.info(jObject);
			if (jObject != null && jObject.has("offenderBookId") && jObject.get("offenderBookId") != null) {
				offenderBookId = new BigDecimal(String.valueOf(jObject.get("offenderBookId")));
			}*/
			
			if (elements != null) {
				if (elements.containsKey("specs") && elements.get("specs") != null) {
					logger.info(elements.get("specs"));
					chainr = Chainr.fromSpec(elements.get("specs"));
					if (chainr != null && elements.containsKey("input") && elements.get("input") != null) {
						transformedJsonOutput = chainr.transform(elements.get("input"));
						logger.info("ExternalSystemController::Jolt converted Object" + JsonUtils.toJsonString(transformedJsonOutput));
					}
				}

			}
		} catch (Exception e) {
			logger.error("ExternalSystemController::error in Jolt Conersion " + e.getMessage());
			
		}

		return transformedJsonOutput;

	}
	
	
}
