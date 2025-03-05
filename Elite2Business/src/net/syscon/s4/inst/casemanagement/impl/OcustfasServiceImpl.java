package net.syscon.s4.inst.casemanagement.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.CasePlansCommitBean;
import net.syscon.s4.inst.casemanagement.OcdiplanRepository;
import net.syscon.s4.inst.casemanagement.OcdiplanService;
import net.syscon.s4.inst.casemanagement.OcustfasRepository;
import net.syscon.s4.inst.casemanagement.OcustfasService;
import net.syscon.s4.inst.casemanagement.beans.CasePlanStaff;
import net.syscon.s4.inst.casemanagement.beans.OffApV1;
import net.syscon.s4.inst.casemanagement.beans.OffApV1CommitBean;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeeds;
import net.syscon.s4.inst.casemanagement.beans.StaffMembersV2;
import net.syscon.s4.inst.demographicsbiometrics.OcuverifRepository;
import net.syscon.s4.pkgs.pims_weight.PimsWeightService;
import net.syscon.s4.triggers.CasePlansT1Service;
import net.syscon.s4.triggers.CasePlansT2Service;
import net.syscon.s4.triggers.CasePlansT3Service;
import net.syscon.s4.triggers.CasePlansTwfService;

/**
 * Class OcustfasServiceImpl
 */
@Service
public class OcustfasServiceImpl extends BaseBusiness implements OcustfasService {
	
	private static Logger logger = LogManager.getLogger(OcustfasServiceImpl.class.getName());

	@Autowired
	private EliteDateService dateService;

	@Autowired
	private OcustfasRepository ocustfasRepository;
	
	@Autowired
	private PimsWeightService pimsWeightService;
	
	@Autowired
	private CasePlansT1Service  casePlansT1Service;
	
	@Autowired
	private CasePlansT2Service casePlansT2Service;
	
	@Autowired
	private CasePlansT3Service  casePlansT3Service;
	
	@Autowired
	private CasePlansTwfService casePlansTwfService;
	
	@Autowired
	private OcuverifRepository ocuverifRepo;
	
	@Autowired
	private OcdiplanRepository ocdiplanRepository;
	
	@Autowired
	private OcdiplanService ocdiplanservice;
	
	@Autowired
	private OcdiplacRepositoryImpl ocdiplacRepositoryImpl;
	


	/**
	 * Creates new OcustfasServiceImpl class Object
	 */
	public OcustfasServiceImpl() {
		// OcustfasServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Integer casePlansPreInsert(final CasePlans paramBean) {
		return ocustfasRepository.casePlansPreInsert(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CasePlans> casePlansExecuteQuery(final CasePlans searchRecord) {
		List<CasePlans> listObj=new ArrayList<CasePlans>();
		try {
			listObj=ocustfasRepository.casePlansExecuteQuery(searchRecord);
		}catch (Exception e) {
			 logger.error("in casePlansExecuteQuery:"+e);
		}
		return listObj;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCASE_PLANS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer casePlansCommit(final CasePlansCommitBean commitBean) {
		int liReturn = 0;
		Long offBookid = null;
		Integer casePlanId = null;
		Integer sacStaffId = null;
		
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<CasePlans> recordSavingObject = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					commitBean.getInsertList().forEach(bean->bean.setCreateUserId(commitBean.getCreateUserId()));
					recordSavingObject = new ArrayList<>();
					final CasePlans offenderPropertyItemObj = commitBean.getInsertList().get(i);
					offBookid = offenderPropertyItemObj.getOffenderBookId();
					casePlanId = ocustfasRepository.offmrPreInsertc(offBookid);
					
					offenderPropertyItemObj.setCasePlanId((Long.valueOf(casePlanId)));
					offenderPropertyItemObj.setCreateUserId(commitBean.getCreateUserId());
					offenderPropertyItemObj.setModifyUserId(commitBean.getCreateUserId());
					if (offenderPropertyItemObj.getOffenderBookId() != null) {
						/*
						 * Integer verifyFlag = ocustfasRepository
						 * .casePlansCount(offenderPropertyItemObj.getOffenderBookId()); if (verifyFlag
						 * == 0) { offenderPropertyItemObj.setVerifiedFlag("N"); } else {
						 * offenderPropertyItemObj.setVerifiedFlag("Y"); }
						 */
						//String returnId = ocustfasRepository.casePlansNextReviewDatePreInsert(offenderPropertyItemObj);
						String returnId = pimsWeightService.getSupLevel(offenderPropertyItemObj.getOffenderBookId(),offenderPropertyItemObj.getCreateUserId());
						
						if (returnId != null) {
							offenderPropertyItemObj.setSupervisionLevel(returnId);
						} else {
							String superVisionLevelTest = ocustfasRepository.casePlansPreInsertUnClass();
							if(superVisionLevelTest!=null) {
								offenderPropertyItemObj.setSupervisionLevel(superVisionLevelTest);
							}else {
								offenderPropertyItemObj.setSupervisionLevel(null);
							}
						}
					}
					if (offenderPropertyItemObj.getSupervisionLevel() != null) {
						Integer superVisionLevel = ocustfasRepository.casePlansPreInsert(offenderPropertyItemObj);
						if (superVisionLevel != null) {
							Date nextReviewDate = new Date(
									dateService.getDBTime().getTime() + (86400000 * superVisionLevel));
							offenderPropertyItemObj.setNextReviewDate(nextReviewDate);
						}
					}
				offenderPropertyItemObj.setInstSacStaffId(offenderPropertyItemObj.getSacStaffId());
					if (offenderPropertyItemObj.getInstSacStaffId() != null
							&& offenderPropertyItemObj.getAgyLocId() != null) {
						StaffMembersV2 instFromDate = ocustfasRepository
								.casPlnPreQuerySacStaffId(offenderPropertyItemObj);
						if (instFromDate != null) {
							offenderPropertyItemObj.setInstFromDate(instFromDate.getFromDate());
							offenderPropertyItemObj.setInstRole(instFromDate.getRole());
							offenderPropertyItemObj.setInstPosition(instFromDate.getPosition());
						}
					}
					List<OffenderCriminogenicNeeds> list = ocustfasRepository.getOldDataOffenderCriminogenicNeeds(offenderPropertyItemObj.getOffenderBookId());
					if(list.size()>0 && !list.isEmpty()) {
						list.forEach(bo->{
							bo. setCasePlanId(new BigDecimal(offenderPropertyItemObj.getCasePlanId()));
						});
					}
				   recordSavingObject.add(offenderPropertyItemObj);
					try {
					casePlansTwfService.casePlansTwf(offenderPropertyItemObj);
					liReturn = ocustfasRepository.casePlansInsertCasePlans(recordSavingObject);
					casePlansT2Service.casePlansT2(offenderPropertyItemObj, offenderPropertyItemObj.getCreateUserId());
					casePlansT3Service.casePlansT3(offenderPropertyItemObj, offenderPropertyItemObj.getCreateUserId());
					casePlansT1Service.casePlansT1(offenderPropertyItemObj);
					if(liReturn==1) {
						List<CasePlanStaff> obj=new ArrayList<CasePlanStaff>();
						obj.addAll(commitBean.getInsertList().get(i).getList());
						for(int j=0;j<obj.size();j++) {
							obj.get(j).setCasePlanStaffRoleId(null);
							obj.get(j).setCasePlanId(casePlanId);
							obj.get(j).setOffenderBookId(offBookid.intValue());
							obj.get(j).setCreateUserId(commitBean.getCreateUserId());
						}		
						ocustfasRepository.caseplansUpdateCasePlansStatus(offBookid.intValue(),commitBean.getCreateUserId());
						ocdiplacRepositoryImpl.insertCasePlanStaffMemberDetails(obj);
					}
					
					List<OffApV1> sentCondListFinal = new ArrayList<>();
					if (list.size() > 0) {
						list.forEach(bo -> {
							Long offCrimNeedIdSeq = ocdiplanRepository.getoffCrimNeedIdSeq();
							List<OffApV1> sentCondList = ocustfasRepository.getOldDataPlanOfActon(bo.getOffCrimNeedId());
							if(sentCondList.size()>0) {
								sentCondList.forEach(obj->{
									obj.setOffCrimNeedId(new BigDecimal(offCrimNeedIdSeq));
								});
							}
							sentCondListFinal.addAll(sentCondList);
							bo.setOffCrimNeedId(offCrimNeedIdSeq);
						});
					}
					//inserting data
					ocdiplanRepository.offCriNeedsInsertOffenderCriminogenicNeeds(list);
					if (sentCondListFinal.size() > 0) {
					//	ocdiplanRepository.offActionPlansV1InsertOffApV1(sentCondListFinal);
						OffApV1CommitBean commitBeanTemp = new OffApV1CommitBean();
						sentCondListFinal.forEach(bo->{
							bo.setOffActionPlanId(null);
						});
						commitBeanTemp.setInsertList(sentCondListFinal);
						commitBeanTemp.setCreateUserId(commitBean.getCreateUserId()); 
						ocdiplanservice.offActionPlansV1Commit(commitBeanTemp);
					}
					} catch (Exception e) {
                      logger.error("in insertlist:"+e);
					}
					
				}
			}
		}
		
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(
					bean->{
						bean.setModifyUserId(commitBean.getCreateUserId());
						bean.setInstSacStaffId(bean.getSacStaffId());
					}
					);
			liReturn = ocustfasRepository.casePlansUpdateCasePlans(commitBean.getUpdateList());
			List<CasePlanStaff> insertCasePlanStaffList =new ArrayList<CasePlanStaff>();
			List<CasePlanStaff> updateCasePlanStaffList =new ArrayList<CasePlanStaff>();

			for (CasePlanStaff casePlanStaff : commitBean.getUpdateList().get(0).getList()) {
				casePlanStaff.setCreateUserId(commitBean.getCreateUserId());
				casePlanStaff.setModifyUserId(commitBean.getCreateUserId());
				casePlanStaff.setOffenderBookId(commitBean.getUpdateList().get(0).getOffenderBookId().intValue());
				if (casePlanStaff.getCreateDatetime() != null) {
					updateCasePlanStaffList.add(casePlanStaff);
				} else {
					casePlanStaff.setCasePlanId(commitBean.getUpdateList().get(0).getCasePlanId().intValue());
					insertCasePlanStaffList.add(casePlanStaff);
				}
			}
			if (insertCasePlanStaffList != null && insertCasePlanStaffList.size() > 0)
				ocdiplacRepositoryImpl.insertCasePlanStaffMemberDetails(insertCasePlanStaffList);
			if (updateCasePlanStaffList != null && updateCasePlanStaffList.size() > 0)
				ocdiplacRepositoryImpl.updateCasePlanStaffMemberDetails(updateCasePlanStaffList);
			

		}
		
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<StaffMembers> rgStaffNameRecordGroup(final String caseLoadId) {
		final List<StaffMembers> refList = ocustfasRepository.rgStaffNameRecordGroup(caseLoadId);
		for (StaffMembers stfMembers : refList) {
			stfMembers.setOfficer(stfMembers.getLastName() + ", " + stfMembers.getFirstName());
		}	
		return refList;
	
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offmrPreInsertc
	 *
	 * @param paramBean
	 *
	 */
	public String agencyLocations(final String agyLocId) {
		return ocustfasRepository.agencyLocations(agyLocId);
	}

}
