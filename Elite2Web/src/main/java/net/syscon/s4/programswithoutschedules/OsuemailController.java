package net.syscon.s4.programswithoutschedules;

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

import net.syscon.s4.cm.teamsworkflow.beans.Work;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.NewEmailCommitBean;
import net.syscon.s4.inst.programswithoutschedules.OsuemailService;

/**
 * OsuemailController
 */
@EliteController
public class OsuemailController {
	@Autowired
	private OsuemailService osuemailService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OsuemailController.class.getName());

	/**
	 * getting rgWorks LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osuemail/rgWorksRecordGroup", method = RequestMethod.GET)
	public List<Work> rgWorksRecordGroup(@RequestParam("caseLoadType") final String caseloadType) {
		List<Work> recordList = new ArrayList<>();
		try {
			recordList = osuemailService.rgWorksRecordGroup(caseloadType);
		} catch (final Exception e) {
			final Work obj = new Work();
			logger.error("rgWorksRecordGroup :", e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Method is used to send email
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osuemail/createAdhocEmail", method = RequestMethod.POST)
	public Integer createAdhocEmail(@RequestBody final NewEmailCommitBean newEmailCommitBean) {
		Integer sealFlag;
		try {
			sealFlag = osuemailService.createAdhocEmail(newEmailCommitBean);
		} catch (final Exception e) {
			logger.error("createAdhocEmail :", e);
			sealFlag = 2;
		}
		return sealFlag;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osuemail/osuemailExecuteQuery", method = RequestMethod.POST)
	public NewEmailCommitBean osuemailExecuteQuery(@RequestBody final NewEmailCommitBean createAdhocEmail) {
		NewEmailCommitBean newEmailCommitBean = new NewEmailCommitBean();
		try {
			newEmailCommitBean = osuemailService.osuemailExecuteQuery(createAdhocEmail);
		} catch (final Exception e) {
			final NewEmailCommitBean bean = new NewEmailCommitBean();
			logger.error("osuemailExecuteQuery :", e);
			bean.setErrorMessage(e.getMessage());
		}
		return newEmailCommitBean;
	}

	/**
	 * getting offender Name and id values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osuemail/getOffendersDetails", method = RequestMethod.GET)
	public String getOffendersDetails(@RequestParam("offenderBookId") final Integer offenderBookId) {
		String offendersName = null;
		try {
			offendersName = osuemailService.getOffendersDetails(offenderBookId);
		} catch (final Exception e) {
			logger.error("getOffendersDetails :", e);
		}
		return offendersName;
	}
}