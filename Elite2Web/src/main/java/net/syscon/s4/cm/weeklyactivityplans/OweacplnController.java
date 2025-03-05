package net.syscon.s4.cm.weeklyactivityplans;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;

@EliteController
public class OweacplnController {

	private static Logger logger = LogManager.getLogger(OweacplnController.class.getName());

	@Autowired
	private OweacplnService oweacplnService;

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oweacpln/getWeeklyActivity", method = RequestMethod.POST)
	public List<WeeklyActivityPlans> getWeeklyActivity(@RequestBody WeeklyActivityPlans searchBean) {
		List<WeeklyActivityPlans> returnList = new ArrayList<>();
		try {
			returnList = oweacplnService.getWeeklyActivity(searchBean);
		} catch (Exception e) {
			logger.error("Exception in " + this.getClass().getName() + " getWeeklyActivity", e);
		}
		return returnList;

	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oweacpln/saveEmDetails", method = RequestMethod.POST)
	public @ResponseBody List<OffenderEmTagDetails> saveEmDetails(
			@RequestBody final OffenderEmTagDetailsCommitBean commitBean) {
		List<OffenderEmTagDetails> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oweacplnService.saveEmDetails(commitBean);
		} catch (Exception e) {
			final OffenderEmTagDetails error = new OffenderEmTagDetails();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);

		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oweacpln/retrieveEmDetails", method = RequestMethod.POST)
	public List<OffenderEmTagDetails> retrieveEmDetails(@RequestBody final OffenderEmTagDetails searchBean) {
		List<OffenderEmTagDetails> searchResult = new ArrayList<OffenderEmTagDetails>();
		try {
			searchResult = oweacplnService.retrieveEmDetails(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oweacpln/weeklyActivityCommit", method = RequestMethod.POST)
	public @ResponseBody List<WeeklyActivityPlans> weeklyActivityCommit(
			@RequestBody final WeeklyActivityPlansCommitBean commitBean) {
		List<WeeklyActivityPlans> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oweacplnService.weeklyActivityCommit(commitBean);
		} catch (Exception e) {
			final WeeklyActivityPlans error = new WeeklyActivityPlans();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);

		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oweacpln/weeklyActivityHtyCommit", method = RequestMethod.POST)
	public @ResponseBody List<WeeklyActivityPlansHty> weeklyActivityHtyCommit(
			@RequestBody final WeeklyActivityPlansHtyCommitBean commitBean) {
		List<WeeklyActivityPlansHty> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oweacplnService.weeklyActivityHtyCommit(commitBean);
		} catch (Exception e) {
			final WeeklyActivityPlansHty error = new WeeklyActivityPlansHty();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);

		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oweacpln/getWeeklyActivityHty", method = RequestMethod.POST)
	public List<WeeklyActivityPlansHty> getWeeklyActivityHty(@RequestBody WeeklyActivityPlansHty searchBean) {
		List<WeeklyActivityPlansHty> returnList = new ArrayList<>();
		try {
			returnList = oweacplnService.getWeeklyActivityHty(searchBean);
		} catch (Exception e) {
			logger.error("Exception in " + this.getClass().getName() + " getWeeklyActivity", e);
		}
		return returnList;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oweacpln/weeklyActivityCommentCommit", method = RequestMethod.POST)
	public @ResponseBody Integer weeklyActivityCommentCommit(
			@RequestBody final WeeklyActivityPlansHtyCommitBean commitBean) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		Integer result = null;
		try {
			result = oweacplnService.weeklyActivityCommentCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);

		}
		return result;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oweacpln/printReportForStaff", method = RequestMethod.POST)
	public @ResponseBody List<OcrwapstReportBean> printReportForStaff(@RequestBody OcrwapstReportBean commitBean) {
		List<OcrwapstReportBean> list = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			list = oweacplnService.printReportForStaff(commitBean);
			logger.info("printReportSupv response" + list);
		} catch (final Exception e) {

			logger.error("Error in Class " + this.getClass().getName() + " printReportForStaff error :: ", e);
		}
		return list;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oweacpln/printReportForOffender", method = RequestMethod.POST)
	public @ResponseBody List<OcrwapstReportBean> printReportForOffender(@RequestBody OcrwapstReportBean commitBean) {
		List<OcrwapstReportBean> list = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			list = oweacplnService.printReportForOffender(commitBean);
			logger.info("printReportSupv response" + list);
		} catch (final Exception e) {

			logger.error("Error in Class " + this.getClass().getName() + " printReportForStaff error :: ", e);
		}
		return list;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oweacpln/amendWapComment", method = RequestMethod.POST)
	public String amendWapComment(@RequestBody WeeklyActivityPlansHty bean) {
		String result = null;
		try {
			result = oweacplnService.getWapComment(bean);
		} catch (Exception e) {
			logger.error("Exception : amendWapComment:", e);
		}
		return result;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oweacpln/getWeeklyActivityHtyMaxData", method = RequestMethod.POST)
	public List<WeeklyActivityPlans> getWeeklyActivityHtyMaxData(@RequestBody WeeklyActivityPlansHty searchBean) {
		List<WeeklyActivityPlans> returnList = new ArrayList<>();
		try {
			returnList = oweacplnService.getWeeklyActivityHtyMaxData(searchBean);
		} catch (Exception e) {
			logger.error("Exception in " + this.getClass().getName() + " getWeeklyActivityHtyMaxData", e);
		}
		return returnList;

	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oweacpln/getMaxHtyVersion", method = RequestMethod.POST)
	public BigDecimal getMaxHtyVersion(@RequestBody WeeklyActivityPlans bean) {
		BigDecimal result = null;
		try {
			result = oweacplnService.getMaxHtyVersion(bean);
		} catch (Exception e) {
			logger.error("Exception : getMaxHtyVersion:", e);
		}
		return result;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oweacpln/copyOverPreviousWeekData", method = RequestMethod.POST)
	public Integer copyOverPreviousWeekData(@RequestBody WeeklyActivityPlans searchBean) {
		Integer result = null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			result = oweacplnService.copyOverPreviousWeekData(searchBean);
		} catch (Exception e) {
			logger.error("Exception : copyOverPreviousWeekData:", e);
		}
		return result;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oweacpln/populateLoggedStaffName", method = RequestMethod.GET)
	public String populateLoggedStaffName() {
			String staffName="";
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			staffName = oweacplnService.populateLoggedStaffName(userName);
			}
		catch (Exception e) {
			logger.error("populateStaffName", e);
		}
		return staffName;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oweacpln/getMaxHtyVersionData", method = RequestMethod.POST)
	public BigDecimal getMaxHtyVersionData(@RequestBody WeeklyActivityPlans bean) {
		BigDecimal result = null;
		try {
			result = oweacplnService.getMaxHtyVersionData(bean);
		} catch (Exception e) {
			logger.error("Exception : getMaxHtyVersion:", e);
		}
		return result;
	}
}
