package net.syscon.s4.iwp.eoffender;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.eoffender.beans.EoffenderDetails;
import net.syscon.s4.eoffender.beans.FileLimits;
import net.syscon.s4.eoffender.beans.MetaDataParameters;
import net.syscon.s4.eoffender.beans.UploadMetaData;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.iwp.eoffender.EoffenderService;


@EliteController
public class EoffenderController {

	@Autowired
	private EoffenderService eoffenderService;

	private static Logger logger = LogManager.getLogger(EoffenderController.class.getName());

	@RequestMapping(value = "/eoffender/getEoffenderDetails", method = RequestMethod.GET)
	public @ResponseBody Object getEoffenderDetails(@RequestParam(value = "keyLogin") final String keyLogin){
		EoffenderDetails result= new EoffenderDetails();
		try {
			result = eoffenderService.getEoffenderDetails(keyLogin);
		} catch (Exception e) {
			logger.error("getEoffenderDetails", e);
		}
		return result;

	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/eoffender/getUploadMetaData", method = RequestMethod.POST)
	public  List<UploadMetaData> getUploadMetaData(@RequestBody MetaDataParameters metaDataParameters) {
		List<UploadMetaData> returnObj = null ;
		try {
			returnObj = eoffenderService.getUploadMetaData(metaDataParameters);
		} catch (Exception e) {
			logger.error("getPreInsertAgyLocation", e);
		}
		return returnObj;
	}
	
	
	@RequestMapping(value = "/eoffender/imageExecuteQuery", method = RequestMethod.POST)
	public List<Images> imageExecuteQuery(@RequestBody final Images searchBean) {
		List<Images> searchResult = new ArrayList<Images>();
		try {
			searchResult = eoffenderService.imageExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("imageExecuteQuery", e);
		}
		return searchResult;
	}
}
