package net.syscon.s4.cm.searchassign;

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
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.VNameSearch2;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * OsinamesController
 */
@EliteController
public class OsinamesController {
	@Autowired
	private OsinamesService osinamesService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OsinamesController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/osinames/nameSrchExecuteQuery", method = RequestMethod.POST)
	public List<VNameSearch2> nameSrchExecuteQuery(@RequestBody final VNameSearch2 searchRecord) {
		List<VNameSearch2> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchRecord.setCreateUserId(userName);
			searchResult = osinamesService.nameSrchExecuteQuery(searchRecord);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OSINAMES");
	}


}