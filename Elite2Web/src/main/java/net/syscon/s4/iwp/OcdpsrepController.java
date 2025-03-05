package net.syscon.s4.iwp;

import java.math.BigDecimal;
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
import net.syscon.s4.common.beans.CourtReportCharges;
import net.syscon.s4.common.beans.CourtReportChargesCommitBean;
import net.syscon.s4.common.beans.OrderPpslCondActivities;
import net.syscon.s4.common.beans.OrderPpslCondActivitiesCommitBean;
import net.syscon.s4.common.beans.OrderProposalConditions;
import net.syscon.s4.common.beans.OrderProposalConditionsCommitBean;
import net.syscon.s4.common.beans.OrderProposals;
import net.syscon.s4.common.beans.OrderProposalsCommitBean;
import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.common.beans.OrdersCommitBean;
import net.syscon.s4.common.beans.OrdersHty;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OcdpsrepController {
	@Autowired
	private OcdpsrepService ocdpsrepService;
	@Autowired
	private ProsmainService prosmainService;
	private static Logger logger = LogManager.getLogger(OcdpsrepController.class.getName());

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpsrep/rgOrderTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOrderTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpsrepService.rgOrderTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpsrep/rgReportProposalRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgReportProposalRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpsrepService.rgReportProposalRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);

		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpsrep/rgCourtAgyLocDescRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCourtAgyLocDescRecordGroup(final String caseloadId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpsrepService.rgCourtAgyLocDescRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpsrep/rgProposedSentenceRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgProposedSentenceRecordGroup(final String sentenceCategory) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpsrepService.rgProposedSentenceRecordGroup(sentenceCategory);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpsrep/rgProposedCategoryRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgProposedCategoryRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpsrepService.rgProposedCategoryRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpsrep/rgProposedRequirementRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgProposedRequirementRecordGroup(final String parentSentenceCategory) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpsrepService.rgProposedRequirementRecordGroup(parentSentenceCategory);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpsrep/ordExecuteQuery", method = RequestMethod.GET)
	public List<Orders> ordExecuteQuery(final String offenderBookId) {
		List<Orders> searchResult = new ArrayList<>();
		try {
			searchResult = ocdpsrepService.ordExecuteQuery(offenderBookId);
		} catch (Exception e) {
			Orders bean = new Orders();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdpsrep/ordCommit", method = RequestMethod.POST)
	public Integer ordCommit(@RequestBody final OrdersCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		List<Orders> teamAndStaff = new ArrayList<Orders>();
		if(commitBean != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
		}
		try {
			if(commitBean.getUpdateList() != null) {
				teamAndStaff = ocdpsrepService.getComparedTeamAndStaff(commitBean.getUpdateList());
			}
			liReturn = ocdpsrepService.ordCommit(commitBean);
			if(1==liReturn) {
				if (commitBean.getUpdateList()!= null) {
					commitBean.getUpdateList().clear();
					commitBean.getUpdateList().addAll(teamAndStaff);
				}
				prosmainService.enableTriggers(commitBean, authorization, "46");
			}
		}catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpsrep/ordProposalsExecuteQuery", method = RequestMethod.GET)
	public List<OrderProposals> ordProposalsExecuteQuery(final BigDecimal orderId) {
		List<OrderProposals> searchResult = new ArrayList<>();
		try {
			searchResult = ocdpsrepService.ordProposalsExecuteQuery(orderId);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")

	@RequestMapping(value = "/ocdpsrep/ordProposalsCommit", method = RequestMethod.POST)
	public Integer ordProposalsCommit(@RequestBody final OrderProposalsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if(commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdpsrepService.ordProposalsCommit(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "47");
			}
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpsrep/ordPropCondsExecuteQuery", method = RequestMethod.POST)
	public List<OrderProposalConditions> ordPropCondsExecuteQuery(@RequestBody final OrderProposals proposals) {
		List<OrderProposalConditions> searchResult = new ArrayList<>();
		try {
			searchResult = ocdpsrepService.ordPropCondsExecuteQuery(proposals);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")

	@RequestMapping(value = "/ocdpsrep/ordPropCondsCommit", method = RequestMethod.POST)
	public Integer ordPropCondsCommit(@RequestBody final OrderProposalConditionsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if(commitBean!=null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdpsrepService.ordPropCondsCommit(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "48");
			}
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpsrep/ordPpslCondActExecuteQuery", method = RequestMethod.GET)
	public List<OrderPpslCondActivities> ordPpslCondActExecuteQuery(final BigDecimal orderProposalConditionId) {
		List<OrderPpslCondActivities> searchResult = new ArrayList<>();
		try {
			searchResult = ocdpsrepService.ordPpslCondActExecuteQuery(orderProposalConditionId);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdpsrep/ordPpslCondActCommit", method = RequestMethod.POST)
	public Integer ordPpslCondActCommit(@RequestBody final OrderPpslCondActivitiesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if(commitBean!=null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdpsrepService.ordPpslCondActCommit(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "49");
			}
		} catch (Exception e) {

			logger.error("in method ordPpslCondActCommit :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpsrep/rgTeamRecordGroup", method = RequestMethod.GET)
	public List<Teams> rgTeamRecordGroup( Long offenderBookId ) {
		List<Teams> recordList = new ArrayList<Teams>();
		try {
				String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = ocdpsrepService.rgTeamRecordGroup(offenderBookId , userId);
		} catch (Exception e) {
			logger.error("In method rgTeamRecordGroup :", e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpsrep/rgStaffMembersRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStaffMembersRecordGroup(String caseLoadId,String teamId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpsrepService.rgStaffMembersRecordGroup( caseLoadId, teamId); 
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgStaffMembersRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpsrep/ordHistoryExecuteQuery", method = RequestMethod.GET)
	public List<OrdersHty> ordHistoryExecuteQuery(final BigDecimal orderId) {
		List<OrdersHty> searchResult = new ArrayList<>();
		try {
			searchResult = ocdpsrepService.ordHistoryExecuteQuery(orderId);
		} catch (Exception e) {
			OrdersHty bean = new OrdersHty();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpsrep/rgAccreditedProgramsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAccreditedProgramsRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdpsrepService.rgAccreditedProgramsRecordGroup(); 
		} catch (Exception e) {
			logger.error("In method rgAccreditedProgramsRecordGroup", e);
		}
		return recordList;
	}
	
	@RequestMapping(value = "/ocdpsrep/ordAuthorCommit", method = RequestMethod.POST)
	public Integer ordAuthorCommit(@RequestBody final Orders ordersBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if(ordersBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				ordersBean.setModifyUserId(userName);
			}
			liReturn = ocdpsrepService.ordAuthorCommit(ordersBean);
			List<Orders> ordList = new ArrayList<Orders>();
			ordList.add(ordersBean);
			OrdersCommitBean ordCommitBean = new OrdersCommitBean();
			ordCommitBean.setUpdateList(ordList);
			if(1 == liReturn) {
				prosmainService.enableTriggers(ordCommitBean, authorization, "79");
			}
		} catch (Exception e) {

			logger.error("In method ordAuthorCommit :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdpsrep/chargesExecuteQuery", method = RequestMethod.POST)
	public List<CourtReportCharges> chargesExecuteQuery(@RequestBody final CourtReportCharges searchBean) {
		List<CourtReportCharges> searchResult = new ArrayList<>();
		try {
			searchResult = ocdpsrepService.chargesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in method chargesExecuteQuery:", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdpsrep/chargesCommit", method = RequestMethod.POST)
	public Integer chargesCommit(@RequestBody final CourtReportChargesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if(commitBean!=null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdpsrepService.chargesCommit(commitBean);
		} catch (Exception e) {
			logger.error("in method chargesCommit :", e);
		}
		return liReturn;
	}
}