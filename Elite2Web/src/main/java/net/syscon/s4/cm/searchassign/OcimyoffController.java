package net.syscon.s4.cm.searchassign;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;

@EliteController
public class OcimyoffController {
	@Autowired
	private OcimyoffService ocimyoffService;
	
	private static Logger logger = LogManager.getLogger(OcimyoffController.class.getName());
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocimyoff/getMyOffendersList", method = RequestMethod.POST)
	public @ResponseBody List<VHeaderBlock> getMyOffenderList(@RequestBody final VHeaderBlock paramBean) {
		List<VHeaderBlock> myOffenderList = new ArrayList<VHeaderBlock>();
		try {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			paramBean.setUserId(authentication.getName());
			myOffenderList = ocimyoffService.getMyOffenderList(paramBean);
		} catch (Exception e) {
			logger.error("", e);
		}
		return myOffenderList;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocimyoff/offenderConditionExcuteQuery", method = RequestMethod.POST)
	public @ResponseBody List<OffenderSentConditions> offenderConditionExcuteQuery(@RequestBody final VHeaderBlock paramBean) {
		List<OffenderSentConditions> myOffenderList = new ArrayList<OffenderSentConditions>();
		try {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			paramBean.setUserId(authentication.getName());
			myOffenderList = ocimyoffService.offenderConditionExcuteQuery(paramBean);
		} catch (Exception e) {
			logger.error("", e);
		}
		return myOffenderList;
	}
}
