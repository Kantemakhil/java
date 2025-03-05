package net.syscon.s4.inst.systemsearch;

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

@EliteController
public class OiimyoffController {
	@Autowired
	private OiimyoffService oiimyoffService;
	
	private static Logger logger = LogManager.getLogger(OiimyoffController.class.getName());
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiimyoff/getMyOffendersList", method = RequestMethod.POST)
	public @ResponseBody List<VHeaderBlock> getMyOffenderList(@RequestBody final VHeaderBlock paramBean) {
		List<VHeaderBlock> myOffenderList = new ArrayList<VHeaderBlock>();
		try {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			paramBean.setUserId(authentication.getName());
			myOffenderList = oiimyoffService.getMyOffenderList(paramBean);
		} catch (Exception e) {
			logger.error("", e);
		}
		return myOffenderList;
	}
}
