package net.syscon.s4.iwp;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.legals.beans.OcdlegloSanctionHty;
import net.syscon.s4.inst.legals.beans.OffenderSentencesHty;
import net.syscon.s4.inst.legals.beans.OffenderSentencesHtyCommitBean;

/**
 * @author Arkin Software Technologies
 * @version 1.0
 */
@EliteController
public class OcuadjcrController {
	@Autowired
	private OcuadjcrService ocuadjcrService; 
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuadjcrController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuadjcr/ctlBlkExecuteQuery", method = RequestMethod.POST)
	public List<OffenderSentencesHty> ctlBlkExecuteQuery(@RequestBody OffenderSentencesHty searchBean) {
		List<OffenderSentencesHty> searchResult = new ArrayList<>();
		try {
			searchResult = ocuadjcrService.ctlBlkExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderSentencesHty bean = new OffenderSentencesHty();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocuadjcr/ctlBlkCommit", method = RequestMethod.POST)
	public @ResponseBody Integer ctlBlkCommit(@RequestBody OffenderSentencesHty commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocuadjcrService.ctlBlkCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception : Ocuadjcr", e);
		}
		return liReturn;
	}
	/**
	 * getting rgReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuadjcr/rgReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocuadjcrService.rgReasonRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocuadjcr:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuadjcr/offSenHtyExecuteQuery", method = RequestMethod.POST)
	public List<OffenderSentencesHty> offSenHtyExecuteQuery(@RequestBody OffenderSentencesHty searchBean) {
		List<OffenderSentencesHty> searchResult = new ArrayList<>();
		try {
			searchResult = ocuadjcrService.ctlBlkExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	
	 
		@PreAuthorize("hasEliteRole('full')")
		@RequestMapping(value = "/ocuadjcr/staffName", method = RequestMethod.GET)
		public String staffName() {
			String liReturn = null;
			try {
				liReturn = ocuadjcrService.staffName();
			} catch (Exception e) {
				logger.error("Exception : Ocuadjcr", e);
			}
			return liReturn;
		}
		
		@PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/ocuadjcr/ocdlegloSenHtyExecuteQuery", method = RequestMethod.POST)
		public List<OcdlegloSanctionHty> ocdlegloSenHtyExecuteQuery(@RequestBody OffenderSentencesHty searchBean) {
			List<OcdlegloSanctionHty> searchResult = new ArrayList<>();
			try {
				searchResult = ocuadjcrService.ocdlegloSenHtyExecuteQuery(searchBean);
			} catch (Exception e) {
				logger.error("Exception :", e);
			}
			return searchResult;
		}
		
		@PreAuthorize("hasEliteRole('full')")
		@RequestMapping(value = "/ocuadjcr/ocdlegloSentCommit", method = RequestMethod.POST)
		public @ResponseBody Integer ocdlegloSentCommit(@RequestBody OcdlegloSanctionHty commitBean) {
			Integer liReturn = 0;
			try {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
				liReturn = ocuadjcrService.ocdlegloSentCommit(commitBean);
			} catch (Exception e) {
				logger.error("Exception : ocdlegloSentCommit", e);
			}
			return liReturn;
		}
		
		
} 