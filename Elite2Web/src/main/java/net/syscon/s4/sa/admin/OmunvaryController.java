package net.syscon.s4.sa.admin;

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
import net.syscon.s4.sa.admin.beans.NameSynonyms;
import net.syscon.s4.sa.admin.beans.NameSynonymsCommitBean;

/**
 * Class OmunvaryController
 */
@EliteController
public class OmunvaryController {
	@Autowired
	private OmunvaryService omunvaryService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OmunvaryController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omunvary/nameSynonymsExecuteQuery", method = RequestMethod.POST)
	public List<NameSynonyms> nameSynonymsExecuteQuery(@RequestBody final NameSynonyms searchBean) {
		List<NameSynonyms> searchResult = new ArrayList<>();
		try {
			searchResult = omunvaryService.nameSynonymsExecuteQuery(searchBean);
		} catch (Exception e) {
			final NameSynonyms bean = new NameSynonyms();
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
	@RequestMapping(value = "/omunvary/nameSynonymsCommit", method = RequestMethod.POST)
	public @ResponseBody String nameSynonymsCommit(@RequestBody final NameSynonymsCommitBean commitBean) {
		String liReturn = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = omunvaryService.nameSynonymsCommit(commitBean);
			if (liReturn != null && liReturn.length() > 2) {
				liReturn = omunvaryService.getTableName(liReturn).toUpperCase();
			}
		} catch (Exception e) {

			logger.error("Exception : Omunvary", e);
		}
		return liReturn;
	}

}