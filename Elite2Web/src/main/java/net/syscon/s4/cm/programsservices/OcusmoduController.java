package net.syscon.s4.cm.programsservices;

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
public class OcusmoduController {
@Autowired
private OcusmoduService ocusmoduService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OcusmoduController.class.getName());
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocusmodu/vAcpSchExecuteQuery", method=RequestMethod.POST)
	public List<VAcpSchedules> vAcpSchExecuteQuery(@RequestBody final VAcpSchedules searchBean) {
		List<VAcpSchedules> searchResult = new ArrayList<>();
		try {
			searchResult = ocusmoduService.vAcpSchExecuteQuery(searchBean);
		} catch (Exception e) {
			final VAcpSchedules bean = new VAcpSchedules();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}