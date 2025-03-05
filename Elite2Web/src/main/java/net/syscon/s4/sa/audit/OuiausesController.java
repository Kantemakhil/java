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

@EliteController
public class OuiausesController {
@Autowired
private OuiausesService ouiausesService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OuiausesController.class.getName());
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ouiauses/getSessionDetailExecuteQuery", method=RequestMethod.POST)
	public List<SysTagAuditFormGetsessiondetail> getSessionDetailExecuteQuery(@RequestBody final SysTagAuditFormGetsessiondetail searchBean) {
		List<SysTagAuditFormGetsessiondetail> searchResult = new ArrayList<>();
		try {
			searchResult = ouiausesService.getSessionDetailExecuteQuery(searchBean);
		} catch (Exception e) {
			final SysTagAuditFormGetsessiondetail bean = new SysTagAuditFormGetsessiondetail();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}