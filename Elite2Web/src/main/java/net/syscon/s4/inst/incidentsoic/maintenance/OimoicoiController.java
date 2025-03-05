package net.syscon.s4.inst.incidentsoic.maintenance;

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
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * @author Arkin Software Technologies
 * @version 1.0
 */
@EliteController
public class OimoicoiController {
	@Autowired
	private OimoicoiService oimoicoiService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimoicoiController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoicoi/oicOfnExecuteQuery", method = RequestMethod.POST)
	public List<OicOffences> oicOfnExecuteQuery(@RequestBody OicOffences searchBean) {
		List<OicOffences> searchResult = new ArrayList<>();
		try {
			searchResult = oimoicoiService.oicOfnExecuteQuery(searchBean);
		} catch (Exception e) {
			OicOffences bean = new OicOffences();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimoicoi/oicOfnCommit", method = RequestMethod.POST)
	public @ResponseBody List<OicOffences> oicOfnCommit(@RequestBody OicOffencesCommitBean commitBean) {
		List<OicOffences> liReturn = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oimoicoiService.oicOfnCommit(commitBean);
		} catch (Exception e) {
			OicOffences obj = new OicOffences();
			logger.error("Exception : oicOfnCommit:", e.getMessage().contains( "oic_offences_uk1"));
			obj.setErrorMessage(e.getMessage().toUpperCase());
			liReturn.add(obj);
			
		}
		return liReturn;
	}

	/**
	 * getting rgOicOffenceCateg LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoicoi/rgOicOffenceCategRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOicOffenceCategRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimoicoiService.rgOicOffenceCategRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oimoicoi:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgOicOffenceType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoicoi/rgOicOffenceTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOicOffenceTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimoicoiService.rgOicOffenceTypeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oimoicoi:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgOicOffenceIndicators LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoicoi/rgOicOffenceIndicatorsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOicOffenceIndicatorsRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimoicoiService.rgOicOffenceIndicatorsRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oimoicoi:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimoicoi/oicOffenceIndicatorsExecuteQuery", method = RequestMethod.POST)
	public List<OicOffenceIndicators> oicOffenceIndicatorsExecuteQuery(@RequestBody OicOffenceIndicators searchBean) {
		List<OicOffenceIndicators> searchResult = new ArrayList<>();
		try {
			searchResult = oimoicoiService.oicOffenceIndicatorsExecuteQuery(searchBean);
		} catch (Exception e) {
			OicOffenceIndicators bean = new OicOffenceIndicators();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimoicoi/oicOffenceIndicatorsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer oicOffenceIndicatorsCommit(@RequestBody OicOffenceIndicatorsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oimoicoiService.oicOffenceIndicatorsCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception : Oimoicoi", e);
		}
		return liReturn;
	}

	
	
	@RequestMapping(value = "/oimoicoi/oicOfnCheckoverLapping", method = RequestMethod.POST)
	public OicOffences oicOfnCheckoverLapping(@RequestBody OicOffences searchBean) {
		OicOffences searchResult = new OicOffences();
		try {
			searchResult = oimoicoiService.oicOfnCheckoverLapping(searchBean);
		} catch (Exception e) {
			OicOffences bean = new OicOffences();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

}