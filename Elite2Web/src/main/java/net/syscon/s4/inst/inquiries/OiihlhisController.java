package net.syscon.s4.inst.inquiries;

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
import net.syscon.s4.im.beans.BedAssignmentHistories;

@EliteController
public class OiihlhisController {
	@Autowired
	private OiihlhisService oiihlhisService;

	private static Logger logger = LogManager.getLogger(OiihlhisController.class.getName());

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiihlhis/bedAhExecuteQuery", method = RequestMethod.POST)
	public List<BedAssignmentHistories> bedAhExecuteQuery(@RequestBody BedAssignmentHistories searchBean) {
		List<BedAssignmentHistories> searchResult = new ArrayList<>();
		try {
			searchResult = oiihlhisService.bedAhExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in bedAhExecuteQuery():", e);
		}
		return searchResult;
	}

}