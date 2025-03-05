package net.syscon.s4.globalconfiguration;

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
import net.syscon.s4.common.beans.ReferenceCodesCommitBean;
import net.syscon.s4.common.beans.ReferenceDomains;
import net.syscon.s4.common.beans.ReferenceDomainsCommitBean;
import net.syscon.s4.globalconfiguration.impl.OumrcodeServiceImpl;

/**
 * Class OumrcodeController
 */
@EliteController
public class OumrcodeController {
@Autowired
private OumrcodeServiceImpl oumrcodeservice;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OumrcodeController.class.getName());
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@RequestMapping(value="/oumrcode/refDmnExecuteQuery", method=RequestMethod.POST)
	public List<ReferenceDomains> refDmnExecuteQuery(@RequestBody ReferenceDomains searchBean) {
		List<ReferenceDomains> searchResult = new ArrayList<>();
		try {
			searchResult = oumrcodeservice.refDmnExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("refDmnExecuteQuery",e);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@RequestMapping(value="/oumrcode/refDmnCommit",method=RequestMethod.POST)
	public @ResponseBody Integer refDmnCommit(@RequestBody ReferenceDomainsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumrcodeservice.refDmnCommit(commitBean);
		}catch(Exception e){
			String error = e.getMessage().toUpperCase();
			if (error.contains("REFERENCE_DOMAINS_PK")){
				liReturn = 3;
			}
			logger.error("refDmnCommit",e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumrcode/refCodeExecuteQuery", method=RequestMethod.POST)
	public List<ReferenceCodes> refCodeExecuteQuery(@RequestBody ReferenceCodes searchBean) {
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			searchResult = oumrcodeservice.refCodeExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("refCodeExecuteQuery",e);
		}
		return searchResult;
	}

	/**
	 *Performing basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@RequestMapping(value="/oumrcode/refCodeCommit",method=RequestMethod.POST)
	public @ResponseBody Integer refCodeCommit(@RequestBody ReferenceCodesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumrcodeservice.refCodeCommit(commitBean);
		}catch(Exception e){
            String error = e.getMessage().toUpperCase();
			if (error.contains("REFERENCE_CODES_PK")){
				liReturn = 3;
			}
			logger.error("refCodeCommit",e);
		}
		return liReturn;
	}

}