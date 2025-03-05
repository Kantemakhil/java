package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.LovList;
import net.syscon.s4.inst.legals.beans.SentenceCalculation;
import net.syscon.s4.pkgs.common.CommonService;



@EliteController
public class OcucalcrController {
	
	@Autowired
	private OcucalcrService ocucalcrService;
	@Autowired
	private CommonService commonService;
	
	private static Logger logger = LogManager.getLogger(OcupsrdeController.class.getName());
	
	/***
	 * fetch data for court Report from Order table
	 * @return a list of the LovList {@link LovList} from the DB
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucalcr/populateCalculationReasonList", method = RequestMethod.GET)
	public List<LovList> populateCalculationReasonList() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
			List<LovList> calcReasonList = new ArrayList<LovList>();
		try {
			calcReasonList = ocucalcrService.populateCalculationReasonList();
			}
		catch (Exception e) {
			logger.error("populateCalculationReasonList", e);
		}
		return calcReasonList;
	}
	
	    @PreAuthorize("hasEliteRole('no')")
		@RequestMapping(value = "/ocucalcr/populateStaffName", method = RequestMethod.GET)
		public String populateStaffName() {
	    	if(!checkCallFormAccess("read")) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN);
			}
				String staffName="";
			try {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				staffName = ocucalcrService.populateStaffName(userName);
				}
			catch (Exception e) {
				logger.error("populateStaffName", e);
			}
			return staffName;
		}
	    
	    @PreAuthorize("hasEliteRole('no')")
		@RequestMapping(value = "/ocucalcr/getCurrentUserId", method = RequestMethod.GET)
		public String getCurrentUserId() {
				String userName="";
			try {
				userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				}
			catch (Exception e) {
				logger.error("getCurrentUserId", e);
			}
			return userName;
		}
	    
		@PreAuthorize("hasEliteRole('no')")
		@RequestMapping(value = "/ocucalcr/getStaffNameBasedOnUserId", method = RequestMethod.GET)
		public String getStaffNameBasedOnUserId(@RequestParam String userId) {
			if (!checkCallFormAccess("full")) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN);
			}
			String staffName = "";
			try {
				staffName = ocucalcrService.populateStaffName(userId);
			} catch (Exception e) {
				logger.error("getStaffNameBasedOnUserId", e);
			}
			return staffName;
		}
	    
		@PreAuthorize("hasEliteRole('no')")
		@RequestMapping(value = "/ocucalcr/calExpDate", method = RequestMethod.POST)
		public String calExpDate(@RequestBody SentenceCalculation sentenceCalc) {
			if (!checkCallFormAccess("full")) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN);
			}
			String successFlag = "";
			try {
				successFlag = ocucalcrService.calExpDate(sentenceCalc);
			} catch (Exception e) {
				logger.error("calExpDate", e);
			}
			return successFlag;
		}
		
		@PreAuthorize("hasEliteRole('no')")
		@RequestMapping(value = "/ocucalcr/getStaffMembers", method = RequestMethod.GET)
		public List<LovList> getStaffMembers() {
			if (!checkCallFormAccess("full")) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN);
			}
			List<LovList> calcReasonList = new ArrayList<LovList>();
			try {
				calcReasonList = ocucalcrService.getStaffMembers();
			} catch (Exception e) {
				logger.error("getStaffMembers", e);
			}
			return calcReasonList;
		}
		
		private Boolean checkCallFormAccess(String role) {
			String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			return commonService.checkCallFormAccess(userId, role, "OCUCALCR");
		}

}
	