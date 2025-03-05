package net.syscon.s4.globaloffenderrecords;

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
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

@EliteController
public class OcunawrnController {
	
	private static Logger logger = LogManager.getLogger(OcunawrnController.class.getName());
	
	@Autowired
	private OcunawrnService ocunawrnService;
	
	
	/**
	 * getting checkNonAssociationConflicts Data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocunawrn/checkNonAssociationConflicts", method = RequestMethod.POST)
	public VOffenderAllSchedulesCommitBean cgfkOffNaDspOffenderIdDiRecordGroup(@RequestBody VOffenderAllSchedulesCommitBean commitBean) {
		
		try {
			commitBean = ocunawrnService.checkNonAssociationConflicts(commitBean);
		} catch (final Exception e) {
			logger.error("Exception in " + this.getClass().getName() + " checkNonAssociationConflicts", e);
		}
		return commitBean;
	}

}
