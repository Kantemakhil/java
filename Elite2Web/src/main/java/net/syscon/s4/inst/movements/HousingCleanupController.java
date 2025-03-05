package net.syscon.s4.inst.movements;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;

@EliteController
public class HousingCleanupController {

	private static Logger logger = LogManager.getLogger(HousingCleanupController.class.getName());
	
	@Autowired
	private HousingCleanupService housingCleanupService;

	
	@RequestMapping(value = "/housing/cleanUpHousingData", method = RequestMethod.GET)
	public Integer cleanUpHousingData() {
		Integer isDataCleaned=0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			isDataCleaned=housingCleanupService.cleanUpHousingData(userName);
		} catch (Exception e) {			
			logger.error("cleanUpHousingData", e);			
		}
		return isDataCleaned;
	}
	
	@RequestMapping(value = "/housingDataCreation/checkAgyLocationExist", method = RequestMethod.GET)
	public Boolean checkAgyLocationExist() {
		Boolean isExist=true;
		try {
			isExist=housingCleanupService.checkAgyLocationExist();
		} catch (Exception e) {			
			logger.error("checkAgyLocationExist", e);			
		}
		return isExist;
	}
	
	@RequestMapping(value = "/housingCleanUp/assignDefaultLocation", method = RequestMethod.GET)
	public Integer assignDefaultLocation() {
		Integer result=1;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			result=housingCleanupService.assignDefaultLocation(userName);
		} catch (Exception e) {
			result=-1;
			logger.error("checkAgyLocationExist", e);			
		}
		return result;
	}
	
	@RequestMapping(value = "/housingDataCreation/createAndAdmitOffender", method = RequestMethod.GET)
	public Long createAndAdmitOffender() {
		Long result=1L;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			result=housingCleanupService.createAndAdmitOffender(userName);
		} catch (Exception e) {
			result=0L;
			logger.error("createAndAdmitOffender", e);			
		}
		return result;
	}
	
}
