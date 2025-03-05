package net.syscon.s4.inst.movements.proposedmovements;

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
import net.syscon.s4.inst.movements.proposedmovements.beans.VOffSchOverview;


@EliteController
public class OiuschovController {
	
	@Autowired
	private OiuschovService oiuschovService;
	
	private static Logger logger = LogManager.getLogger(OiuschovController.class.getName());
	
	
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiuschov/vOffSchOverviewExecuteQuery", method=RequestMethod.POST)
	public List<VOffSchOverview> vOffSchOverviewExecQuery(@RequestBody VOffSchOverview searchBean) {
		List<VOffSchOverview> searchResult = new ArrayList<>();
		try {
			searchResult = oiuschovService.vOffSchOverviewExecQuery(searchBean);
		} catch (Exception e) {
			VOffSchOverview bean = new VOffSchOverview();
			logger.error("Exception occured in " + this.getClass().getName() + " vOffSchOverviewExecQuery() ", e);	
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	
}
