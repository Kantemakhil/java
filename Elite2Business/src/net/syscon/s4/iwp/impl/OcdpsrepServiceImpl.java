package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
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
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.iwp.OcdpsrepRepository;
import net.syscon.s4.iwp.OcdpsrepService;

/**
 * Class OcdpsrepServiceImpl
 */
@Service
public class OcdpsrepServiceImpl extends BaseBusiness implements OcdpsrepService {

	@Autowired
	private OcdpsrepRepository ocdpsrepRepository;

	private static String YFLAG = "Y";

	public List<Orders> ordExecuteQuery(final String offenderBookId) {
		List<Orders> ordersList = null;
		ordersList = ocdpsrepRepository.ordExecuteQuery(offenderBookId);
		return ordersList;

	}

	@Transactional
	public Integer ordCommit(final OrdersCommitBean commitBean) {
		int liReturn = 0;
		List<OrdersHty> orderHtyList = new ArrayList<OrdersHty>();
		OrdersHty orderHtyBean = new OrdersHty();
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (Orders obj : commitBean.getInsertList()) {
				obj.setOrderId(ocdpsrepRepository.getOrdersSeq());
				obj.setCreateUserId(commitBean.getCreateUserId());
				orderHtyBean = copyProperties(obj);
				orderHtyList.add(orderHtyBean);
			}
			liReturn = ocdpsrepRepository.ordInsertOrders(commitBean.getInsertList());
			liReturn = ocdpsrepRepository.ordInsertOrdersHty(orderHtyList);
			orderHtyList.clear();
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (Orders obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				orderHtyBean = copyProperties(obj);
				orderHtyBean.setCreateUserId(commitBean.getCreateUserId());
				orderHtyList.add(orderHtyBean);
			}
			liReturn = ocdpsrepRepository.ordUpdateOrders(commitBean.getUpdateList());
			liReturn = ocdpsrepRepository.ordInsertOrdersHty(orderHtyList);
		}
		return liReturn;
	}

	public List<OrderProposals> ordProposalsExecuteQuery(final BigDecimal orderId) {
		return ocdpsrepRepository.ordProposalsExecuteQuery(orderId);

	}

	@Transactional
	public Integer ordProposalsCommit(final OrderProposalsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OrderProposals obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdpsrepRepository.ordProposalsInsert(commitBean.getInsertList());
			if(liReturn == 2) {
				return liReturn;
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OrderProposals obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdpsrepRepository.ordProposalsUpdate(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocdpsrepRepository.ordProposalsDelete(commitBean.getDeleteList());
		}
		return liReturn;
	}

	public List<OrderProposalConditions> ordPropCondsExecuteQuery(final OrderProposals proposals) {
		return ocdpsrepRepository.ordPropCondsExecuteQuery(proposals);

	}

	@Transactional
	public Integer ordPropCondsCommit(OrderProposalConditionsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(ordPropconds -> {
				ordPropconds.setCreateUserId(commitBean.getCreateUserId());
				ordPropconds.setOrderProposalConditionId(ocdpsrepRepository.propCondsPreInsert());
			});
			liReturn = ocdpsrepRepository.ordPropCondsInsertOrderProposalConditions(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo->{
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocdpsrepRepository.ordPropCondsUpdateOrderProposalConditions(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocdpsrepRepository.ordPropCondsDeleteOrderProposalConditions(commitBean.getDeleteList());
		}

		return liReturn;
	}

	public List<OrderPpslCondActivities> ordPpslCondActExecuteQuery(final BigDecimal orderId) {
		return ocdpsrepRepository.ordPpslCondActExecuteQuery(orderId);

	}

	@Transactional
	public Integer ordPpslCondActCommit(final OrderPpslCondActivitiesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(ordppslCondAct -> {
				ordppslCondAct.setOrderPpslCondActivityId(ocdpsrepRepository.ordPpslcondActpreInnsert());
				ordppslCondAct.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = ocdpsrepRepository.ordPpslCondActInsertOrderPpslCondActivities(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo->{
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocdpsrepRepository.ordPpslCondActUpdateOrderPpslCondActivities(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocdpsrepRepository.ordPpslCondActDeleteOrderPpslCondActivities(commitBean.getDeleteList());
		}
		return liReturn;
	}

	public List<ReferenceCodes> rgOrderTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		recordList = ocdpsrepRepository.rgOrderTypeRecordGroup();
		for (ReferenceCodes referenceCodes : recordList) {
			if (YFLAG.equals(referenceCodes.getActiveFlag())) {
				referenceCodes.setCanDisplay(true);
			} else {
				referenceCodes.setCanDisplay(false);
			}

		}
		return recordList;
	}

	public List<ReferenceCodes> rgReportProposalRecordGroup() {
		List<ReferenceCodes> returnList = new ArrayList<>();
		returnList = ocdpsrepRepository.rgReportProposalRecordGroup();
		if(returnList != null && !returnList.isEmpty()) {
			returnList.forEach(ele -> {
				if(ApplicationConstants.YES.equalsIgnoreCase(ele.getActiveFlag())) {
					ele.setCanDisplay(true);
				} else {
					ele.setCanDisplay(false);
				}
			});
		}
		return returnList;
	}

	public List<ReferenceCodes> rgCourtAgyLocDescRecordGroup(final String caseloadId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		recordList = ocdpsrepRepository.rgCourtAgyLocDescRecordGroup(caseloadId);
		for (ReferenceCodes referenceCodes : recordList) {
			if (YFLAG.equals(referenceCodes.getActiveFlag())) {
				referenceCodes.setCanDisplay(true);
			} else {
				referenceCodes.setCanDisplay(false);
			}

		}
		return recordList;

	}

	public List<ReferenceCodes> rgProposedSentenceRecordGroup(final String sentenceCategory) {
		return ocdpsrepRepository.rgProposedSentenceRecordGroup(sentenceCategory);

	}

	public List<ReferenceCodes> rgProposedCategoryRecordGroup() {
		return ocdpsrepRepository.rgProposedCategoryRecordGroup();
	}

	public List<ReferenceCodes> rgProposedRequirementRecordGroup(final String sentencecategory) {
		List<ReferenceCodes> recordList = ocdpsrepRepository.rgProposedRequirementRecordGroup(sentencecategory);
		for (ReferenceCodes referenceCodes : recordList) {
			referenceCodes.setCanDisplay(true);
		}
		return recordList;
	}

	@Override
	public List<Teams> rgTeamRecordGroup(Long offenderBookId , String userId) {
		return ocdpsrepRepository.rgTeamRecordGroup(offenderBookId ,userId  );
	}

	@Override
	public  List<ReferenceCodes> rgStaffMembersRecordGroup(String caseLoadId,String teamResponsible) {
	return ocdpsrepRepository.rgStaffMembersRecordGroup(caseLoadId, teamResponsible); 

	}

	private OrdersHty copyProperties(Orders order) {
		OrdersHty bean = new OrdersHty();
		BeanUtils.copyProperties(order, bean);
		return bean;
	}

	@Override
	public List<OrdersHty> ordHistoryExecuteQuery(BigDecimal orderId) {
		return ocdpsrepRepository.ordHistoryExecuteQuery(orderId);
	}
	
	@Override
	public List<ReferenceCodes> rgAccreditedProgramsRecordGroup() {
		return ocdpsrepRepository.rgAccreditedProgramsRecordGroup();
	}

	@Override
	public Integer ordAuthorCommit(Orders orderBean) {
		return ocdpsrepRepository.ordAuthorCommit(orderBean);
	}

	@Override
	public List<Orders> getComparedTeamAndStaff(final List<Orders> list) {
		List<Orders> Orders = new ArrayList<Orders>();
		Integer count = 0;
		try {
			for (Orders obj : list) {
				Orders oldobj = new Orders();
				if (obj.getOrderId() != null) {
					oldobj = ocdpsrepRepository.getOldRecordToCompareTeamAndStaff(obj.getOrderId());
					if(oldobj.getTeamId() != null && obj.getTeamId() != null) {
						if(!obj.getTeamId().equals(oldobj.getTeamId())) {
							count++;
						}
						if((obj.getTeamId().equals(oldobj.getTeamId()))&&((obj.getStaffMemberId()==null)&&oldobj.getStaffMemberId()!=null)) {
							count++;
						}
					}
					if(oldobj.getStaffMemberId() != null && obj.getStaffMemberId() != null) {
						if(!obj.getStaffMemberId().equals(oldobj.getStaffMemberId())) {
							count++;
						}
					}
					if((oldobj.getTeamId() == null || oldobj.getStaffMemberId() == null ) && ( obj.getStaffMemberId() != null || obj.getTeamId() != null) ) {
						count++;
					}
					if(count>=1) {
						Orders.add(obj);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Orders;
	}
	
	@Override
	public List<CourtReportCharges> chargesExecuteQuery(CourtReportCharges searchBean) {
		return ocdpsrepRepository.chargesExecuteQuery(searchBean);
	}

	@Transactional
	public Integer chargesCommit(CourtReportChargesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(CourtReportCharges courtReportCharges: commitBean.getInsertList()) {
				courtReportCharges.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdpsrepRepository.insertCharges(commitBean.getInsertList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocdpsrepRepository.deleteCharges(commitBean.getDeleteList());
		}
		return liReturn;
	}

}