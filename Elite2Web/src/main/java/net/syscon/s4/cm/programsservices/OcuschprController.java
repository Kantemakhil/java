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

import net.syscon.s4.iwp.beans.VAcpSchedules;


@net.syscon.s4.common.EliteController
public class OcuschprController {
@Autowired
private OcuschprService ocuschprService;
	
	private static Logger logger = LogManager.getLogger(OcuschprController.class.getName());
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocuschpr/vAcpSchedulesExecuteQuery", method=RequestMethod.POST)
	public List<VAcpSchedules> vAcpSchedulesExecuteQuery(@RequestBody VAcpSchedules searchBean) {
		List<VAcpSchedules> searchResult = new ArrayList<>();
		try {
			searchResult = ocuschprService.vAcpSchedulesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :",e);
		}
		return searchResult;
	}

}