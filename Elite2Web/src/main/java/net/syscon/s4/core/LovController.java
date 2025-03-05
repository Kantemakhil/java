package net.syscon.s4.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;

@EliteController
public class LovController {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(LovController.class);

	@Autowired
	LovService businessObj;

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/getReferenceDomainCodes", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> getReferenceDomainCodes(@RequestParam(name = "domain") final String domain,
			@RequestParam(name = "parent", required = false) String parent,
			@RequestParam(name = "moduleName") final String moduleName, @RequestHeader HttpHeaders headers) {
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			List<String> authorizationList = headers.get("Authorization");
			String authorization = authorizationList.get(0).split(",")[0];
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchResult = businessObj.getReferenceDomainCodes(domain, parent,userName,moduleName);
		} catch (Exception e) {
			logger.error("Exception in Reference Codes: ",e);
		}
		return searchResult;
	}
}
