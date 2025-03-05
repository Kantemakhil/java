package net.syscon.s4.sa.audit;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.StaffMembers;

/**
 * OiusmselController
 */
@EliteController
public class OiusmselController {
	@Autowired
	private OiusmselService oiusmselService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiusmselController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiusmsel/staffMembersExecuteQuery", method = RequestMethod.POST)
	public List<StaffMembers> staffMembersExecuteQuery(@RequestBody final StaffMembers searchBean) {
		List<StaffMembers> searchResult = new ArrayList<>();
		try {
			searchResult = oiusmselService.staffMembersExecuteQuery(searchBean);
		} catch (final Exception e) {
			final StaffMembers bean = new StaffMembers();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}