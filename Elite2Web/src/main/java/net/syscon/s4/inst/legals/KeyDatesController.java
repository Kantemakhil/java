package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.legals.beans.KeyDates;

@EliteController
public class KeyDatesController {
	
	@Autowired
	private KeyDatesService keyDatesService;
	
	private static Logger logger = LogManager.getLogger(KeyDatesController.class.getName());
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsenkd/fetchKeyDates", method = RequestMethod.GET)
	public List<KeyDates> fetchKeyDates() {		
		return this.listOfKeydates();
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsenhy/fetchKeyDates", method = RequestMethod.GET)
	public List<KeyDates> fetchKeyDatesValue() {
		return this.listOfKeydates();
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsenkd/populateKeyDates", method = RequestMethod.POST)
	public List<KeyDates> populateKeyDates(@RequestBody List<KeyDates> keyDatesLablesList) {
		List<KeyDates> result = new ArrayList<KeyDates>();
		result=populateDateLists(keyDatesLablesList);
		return result;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsenhy/populateKeyDates", method = RequestMethod.POST)
	public List<KeyDates> populateKeyDatesValue(@RequestBody List<KeyDates> keyDatesLablesList) {
		List<KeyDates> result = new ArrayList<KeyDates>();
		result = populateDateLists(keyDatesLablesList);
		return result;
	}
	
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidsenkd/updateKeyDates", method = RequestMethod.POST)
	public Integer updateKeyDates(@RequestBody List<KeyDates> updatedKeyDatesList) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Integer liReturn = 0;
		liReturn = updateKeyDatesValues(updatedKeyDatesList, userName);
		return liReturn;
	}
	
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocucalcr/updateKeyDates", method = RequestMethod.POST)
	public Integer updateKeyDatesValue(@RequestBody List<KeyDates> updatedKeyDatesList) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Integer liReturn = 0;
		liReturn = updateKeyDatesValues(updatedKeyDatesList, userName);
		return liReturn;
	}
	
	private List<KeyDates> listOfKeydates(){
		List<KeyDates> result= null;
		try {
			 result = keyDatesService.fetchKeyDates();
		}
		catch (Exception e) {
			logger.error("fetchKeyDates", e.getMessage());
		}
		return result;
	}
	
	private List<KeyDates>populateDateLists(List<KeyDates> keyDatesLablesList){
		List<KeyDates> result = new ArrayList<KeyDates>();
		try {
			result = keyDatesService.populateKeyDates(keyDatesLablesList);
		}
		catch (Exception e) {
			logger.error("populateKeyDates", e.getMessage());
		}
		return result;
	}
	
	private Integer updateKeyDatesValues(List<KeyDates> updatedKeyDatesList, String staffName){
		Integer liReturn = 0;
		for (KeyDates keyDates: updatedKeyDatesList) {
			keyDates.setStaffName(staffName);
		}
		try {
			liReturn = keyDatesService.updateKeyDates(updatedKeyDatesList);
		}
		catch (Exception e) {
		logger.error("updateKeyDates", e.getMessage());
		}
		return liReturn;
	}

}
