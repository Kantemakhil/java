package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.legals.beans.InterestedParties;
import net.syscon.s4.inst.legals.beans.InterestedPartiesCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OcdintpaController {
	
	@Autowired
	private OcdintpaService ocdintpaService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	private static Logger logger = LogManager.getLogger(OcdintpaController.class.getName());
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "ocdintpa/executeQuery", method = RequestMethod.POST)
	public List<InterestedParties> executeQuery(@RequestBody InterestedParties interestedParties) {
		List<InterestedParties> result = new ArrayList<>();
		try {
			result = ocdintpaService.executeQuery(interestedParties);
		} catch (Exception e) {
			logger.error("executeQuery", e.getMessage());
		}
		return result;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "ocdintpa/commitData", method = RequestMethod.POST)
	public Integer commitData(@RequestBody InterestedPartiesCommitBean interestedPartiesCommitBean, @RequestHeader HttpHeaders headers) {
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		Integer result = 1;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		interestedPartiesCommitBean.setCreateUserId(userName);
		try {
			if(interestedPartiesCommitBean.getInsertList() != null && !interestedPartiesCommitBean.getInsertList().isEmpty()) {
				interestedPartiesCommitBean.getInsertList().forEach(obj->{
					obj.setCreateUserId(userName);
				});
				result = ocdintpaService.insertInterestedParties(interestedPartiesCommitBean.getInsertList());
			}
			if(interestedPartiesCommitBean.getUpdateList() != null && !interestedPartiesCommitBean.getUpdateList().isEmpty()) {
				interestedPartiesCommitBean.getUpdateList().forEach(obj->{
					obj.setModifyUserId(userName);
				});
				result = ocdintpaService.updateInterestedParties(interestedPartiesCommitBean.getUpdateList());
			}
			if(interestedPartiesCommitBean.getDeleteList() != null && !interestedPartiesCommitBean.getDeleteList().isEmpty()) {
				interestedPartiesCommitBean.getDeleteList().forEach(obj->{
					obj.setModifyUserId(userName);
				});
				result = ocdintpaService.deleteInterestedParties(interestedPartiesCommitBean.getDeleteList());
			}
			if(result > 0 ) {
				prosmainService.enableTriggers(interestedPartiesCommitBean, authorization, "88");
			}
		} catch (Exception e) {
			logger.error("commitData", e.getMessage());
			return 0;
		}
		return result;
	}
	
}
