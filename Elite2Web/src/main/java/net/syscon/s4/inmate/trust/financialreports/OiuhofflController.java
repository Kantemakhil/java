package net.syscon.s4.inmate.trust.financialreports;

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
import net.syscon.s4.common.beans.VHeaderBlock;

@EliteController
public class OiuhofflController {
	@Autowired
	private OiuhofflService oiuhofflService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiuhofflController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuhoffl/vOffBkgExecuteQuery", method = RequestMethod.POST)
	public List<VHeaderBlock> vOffBkgExecuteQuery(@RequestBody VHeaderBlock searchBean) {
		List<VHeaderBlock> searchResult = new ArrayList<>();
		try {
			searchResult = oiuhofflService.vOffBkgExecuteQuery(searchBean);
		} catch (Exception e) {
			VHeaderBlock bean = new VHeaderBlock();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}