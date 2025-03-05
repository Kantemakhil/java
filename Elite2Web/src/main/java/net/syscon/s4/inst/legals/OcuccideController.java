package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.legals.beans.CaseIdentifiers;
import net.syscon.s4.inst.legals.beans.IdentifierCommitBean;
import net.syscon.s4.inst.legals.beans.IdentifierType;


@EliteController
public class OcuccideController {
	
	@Autowired
	private OcuccideService ocuccideService;
	
	private static Logger logger = LogManager.getLogger(OcuccideController.class.getName());
	
	/***
	 * method written for Court Case Identifiers
	 * @param selectedCaseId {@link Long}
	 * @return a list of the CaseIdentifiers {@link CaseIdentifiers} for the matched selectedCaseId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuccide/caseIdentifiers", method = RequestMethod.GET)
	public List<CaseIdentifiers> caseIdentifiers(@RequestParam("caseId") Long selectedCaseId) {
			List<CaseIdentifiers> Result = new ArrayList<CaseIdentifiers>();
		try {			
				Result = ocuccideService.caseIdentifiers(selectedCaseId);
			}
		catch (Exception e) {
			logger.error("", e);
		}
		return Result;
	}
	
  /***
   * method written for Identifiers Types
   * fetching Types in Identifiers Types
   */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuccide/identifierType", method = RequestMethod.GET)
	public List<IdentifierType> identifierType() {
			List<IdentifierType> Result = new ArrayList<IdentifierType>();
		try {			
				Result = ocuccideService.identifierType();
			}
		catch (Exception e) {
			logger.error("", e);
		}
		return Result;
	}
	
	/***
	 * function for insert,update or delete 
	 * @param identifierBeanCommit
	 * @return
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocuccide/updateIdentifierData", method = RequestMethod.POST)
	public @ResponseBody int[] updateData(@RequestBody IdentifierCommitBean identifierBeanCommit) {
		int[] result = {};
		try{
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
			int[] returnedRows = ocuccideService.updateIdentifierData(authentication.getName(),identifierBeanCommit);
			result = returnedRows;
		}
		catch (Exception e){
			logger.error("updateIdentifierData", e);
		}
		return result;
	}

}
