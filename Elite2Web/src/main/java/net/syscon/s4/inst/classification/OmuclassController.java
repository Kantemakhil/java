package net.syscon.s4.inst.classification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import net.syscon.s4.inst.classification.beans.AssessmentSectionScoresV1;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * Class OmuclassController
 */
@EliteController
public class OmuclassController {
	@Autowired
	private OmuclassService omuclassService;
	
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OmuclassController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omuclass/subTotalsExecuteQuery", method = RequestMethod.POST)
	public List<AssessmentSectionScoresV1> subTotalsExecuteQuery(
			@RequestBody final AssessmentSectionScoresV1 searchBean) {
		List<AssessmentSectionScoresV1> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (Optional.ofNullable(searchBean).isPresent()) {
				searchBean.setCreateUserId(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			}
			searchResult = omuclassService.subTotalsExecuteQuery(searchBean);
		} catch (Exception e) {
			final AssessmentSectionScoresV1 bean = new AssessmentSectionScoresV1();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role, "OMUCLASS");
	}

}