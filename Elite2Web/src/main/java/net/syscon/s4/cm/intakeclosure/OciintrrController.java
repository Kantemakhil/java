package net.syscon.s4.cm.intakeclosure;

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
import net.syscon.s4.common.beans.OffIntakeReviewQueue;

@EliteController
public class OciintrrController {
	@Autowired
	private OciintrrService ociintrrService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdsupstController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ociintrr/offIntakeReiewQuExecuteQuery", method = RequestMethod.POST)
	public List<OffIntakeReviewQueue> offIntakeReiewQuExecuteQuery(@RequestBody final OffIntakeReviewQueue searchBean) {
		List<OffIntakeReviewQueue> searchResult = new ArrayList<>();
		final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(userName);
		try {
			searchResult = ociintrrService.offIntakeReiewQuExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception in offIntakeReiewQuExecuteQuery:", e);
		}
		return searchResult;
	}

	/**
	 * method: offIntakeRevAccept
	 * 
	 * @Param OffIntakeReviewQueue
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ociintrr/offIntakeRevAccept", method = RequestMethod.POST)
	public Integer offIntakeRevAccept(@RequestBody final OffIntakeReviewQueue bean) {
		int liReturn = 0;
		try {
			if (bean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				bean.setCreateUserId(userName);
			}
			liReturn = ociintrrService.offIntakeRevAccept(bean);
		} catch (Exception e) {
			logger.error("Exception in offIntakeRevAccept:", e);
		}
		return liReturn;
	}
}
