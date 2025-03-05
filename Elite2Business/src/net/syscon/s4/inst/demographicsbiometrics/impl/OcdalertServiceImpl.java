package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.exolab.castor.xml.handlers.DateFieldHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderAlertsCommitBean;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.OffenderAlerts;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogsCommitBean;
import net.syscon.s4.inst.demographicsbiometrics.OcdalertRepository;
import net.syscon.s4.inst.demographicsbiometrics.OcdalertService;
import net.syscon.s4.triggers.OffenderAlertsTwfService;

/**
 * Class OcdalertServiceImpl
 */
@Service
public class OcdalertServiceImpl extends BaseBusiness implements
		OcdalertService {

	@Autowired
	private OcdalertRepository ocdalertDao;

	@Autowired
	private EliteDateService dateService;
	
	@Autowired
	private OffenderAlertsTwfService offenderAlertsTwfService;

	/**
	 * Creates new OcdalertServiceImpl class Object
	 */
	public OcdalertServiceImpl() {
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @return Integer
	 * @Param commitBean
	 */
	@Transactional
	public String insertUpdateDeleteOffenderAlerts(
			final OffenderAlertsCommitBean commitBean) {
		String liReturn = "0";
		Long offenderBookId = null;
		if (commitBean.getInsertList() != null
				&& commitBean.getInsertList().size() > 0) {
			for (OffenderAlerts offenderAlerts : commitBean.getInsertList()) {
				offenderBookId = offenderAlerts
						.getOffenderBookId();
			}
			List<OffenderAlerts> recordSavingObject = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final int valueAlertSeq = alertPreInsertc(offenderBookId);
					final OffenderAlerts offenderAlertObj = commitBean
							.getInsertList().get(i);

					offenderAlertObj.setAlertSeq(Long.valueOf(valueAlertSeq));
					offenderAlertObj.setCreateUserId(commitBean.getCreateUserId());
					recordSavingObject.add(offenderAlertObj);
					liReturn = alertInsertOffenderAlerts(recordSavingObject);
					String sqlOperation="INSERTING";
					for (OffenderAlerts offenderAlerts : commitBean.getInsertList()) {
						offenderAlertsTwfService.offenderAlertsTwfTrigger(offenderAlerts, sqlOperation);
					}
				}
			}
		}
		if (commitBean.getUpdateList() != null
				&& commitBean.getUpdateList().size() > 0) {
			for (OffenderAlerts bean : commitBean.getUpdateList()) {				
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = alertUpdateOffenderAlerts(commitBean.getUpdateList());
			String sqlOperation="UPDATING";
			for (OffenderAlerts offenderAlerts : commitBean.getUpdateList()) {
			offenderAlertsTwfService.offenderAlertsTwfTrigger(offenderAlerts, sqlOperation);
		}
		}
		if (commitBean.getDeleteList() != null
				&& commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = alertDeleteOffenderAlerts(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws SQLException
	 */
	public Integer alertPreInsertc(final Long offenderBookId) {
		return ocdalertDao.alertPreInsertc(offenderBookId);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws SQLException
	 */
	@Override
	public Integer alertPostQueryvarificationCurr(
			final ModulePrivileges modulePrivileges) {
		return ocdalertDao.alertPostQueryvarificationCurr(modulePrivileges);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkchkAlertAlertRefAlertc(
			final String alertCode, final String mode) {
		return ocdalertDao.cgfkchkAlertAlertRefAlertc(alertCode, mode);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfklkpAlertAlertRefAlertc(final String code,
			final String activeFlag, final String description,
			final String parentCode) {
		return ocdalertDao.cgfklkpAlertAlertRefAlertc(code, activeFlag,
				description, parentCode);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws SQLException
	 */
	@Override
	public List<ReferenceCodes> cgfkchkAlertAlertRefAlc(final String code,
			final String domain) {
		return ocdalertDao.cgfkchkAlertAlertRefAlc(code, domain);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws SQLException
	 */
	public List<OffenderAlerts> cguvchkOffAlertUkc(
			final OffenderAlerts paramBean) {
		return ocdalertDao.cguvchkOffAlertUkc(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws SQLException
	 */
	public OffenderBookings getBookingBeginDate(final String const0) {
		return ocdalertDao.getBookingBeginDate(const0);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderAlerts> searchOffenderAlerts(
			final OffenderAlerts searchRecord) {
		final List<OffenderAlerts> refList = ocdalertDao
				.searchOffenderAlerts(searchRecord);
		for (OffenderAlerts offenderAlerts : refList) {
			offenderAlerts.setAlertCode(offenderAlerts.getAlertCode().trim());
			offenderAlerts.setAlertType(offenderAlerts.getAlertType().trim());

			if (offenderAlerts.getCaseloadType() != null) {
				final String str = String.valueOf(offenderAlerts
						.getCaseloadType().charAt(0));
				offenderAlerts.setCaseloadType(str);
			}
		}
		return refList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderAlerts
	 *
	 * @throws SQLException
	 */
	@Transactional
	public String alertInsertOffenderAlerts(
			final List<OffenderAlerts> lstOffenderAlerts) {
		Long workFlowId = null;
		String liReturn = null;
		for (OffenderAlerts obj : lstOffenderAlerts) {
			if (obj.getExpiryDate() != null) {
				if (!(obj.getAlertDate().compareTo(obj.getExpiryDate()) < 0)
						&& !(obj.getAlertDate().compareTo(obj.getExpiryDate()) < 0)) {
					obj.setAlertStatus("INACTIVE");
				} else {
					if (!(obj.getExpiryDate().compareTo(obj.getAlertDate()) < 0)
							&& !(obj.getExpiryDate().compareTo(
									obj.getAlertDate()) < 0)) {
						obj.setAlertStatus("ACTIVE");
					}
				}
				if (obj.getExpiryDate().before(dateService.getDBTime())) {
					obj.setAlertStatus("INACTIVE");
				}
			}
		}
		liReturn = ocdalertDao.alertInsertOffenderAlerts(lstOffenderAlerts)
				+ "";
		for (OffenderAlerts objWorkFlow : lstOffenderAlerts) {
			if (objWorkFlow.getOffenderBookId() != null
					&& objWorkFlow.getAlertSeq() != null) {
				Integer workFlowIdCount = ocdalertDao.workFlowIdCount(
						objWorkFlow.getOffenderBookId(),
						objWorkFlow.getAlertSeq());
				if (workFlowIdCount == 0) {
					workFlowId = ocdalertDao.workFlowLogsPreInsertc();
					objWorkFlow.setWorkFlowId(Long.valueOf(workFlowId));
					liReturn = ocdalertDao
							.workFlowsInsertWorkFlows(lstOffenderAlerts) + "";
					liReturn = ocdalertDao
							.workFlowsInsertWorkFlowLogs(lstOffenderAlerts)
							+ "";
				}
			}
		}
		return liReturn;
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderAlerts
	 *
	 * @throws SQLException
	 */
	@Transactional
	public String alertUpdateOffenderAlerts(
			final List<OffenderAlerts> lstOffenderAlerts) {
		String liReturn = null;
		String modifiedFlag = "N";
		Integer maximumWorkFlowId = 0;
		Long workFlowId = null;
		Integer firstData = 0;
		 SimpleDateFormat dtobj = new SimpleDateFormat("dd/MM/yyyy");
		for (OffenderAlerts obj : lstOffenderAlerts) {
			List<OffenderAlerts> alertDetails = ocdalertDao.getAlertDetails(obj);
			obj.setVerifiedFlag(alertDetails.get(0).getVerifiedFlag());
			if ((obj.getAlertDate() != null)) {
				if(alertDetails.get(0).getAlertDate() != null) {
					if(!(dtobj.format(obj.getAlertDate()).equals(dtobj.format(alertDetails.get(0).getAlertDate())))) {
						obj.setVerifiedFlag("N");
						modifiedFlag = "Y";
					}
				} else {
					obj.setVerifiedFlag("N");
					modifiedFlag = "Y";
				}
			}
			if ((obj.getExpiryDate() != null)) {
				if(alertDetails.get(0).getExpiryDate() != null) {
					if(!(dtobj.format(obj.getExpiryDate()).equals(dtobj.format(alertDetails.get(0).getExpiryDate())))) {
						obj.setVerifiedFlag("N");
						modifiedFlag = "Y";
					}
				} else {
					obj.setVerifiedFlag("N");
					modifiedFlag = "Y";
				}
				if (!(obj.getAlertDate().compareTo(obj.getExpiryDate()) < 0)
						&& !(obj.getAlertDate().compareTo(obj.getExpiryDate()) < 0)) {
					obj.setAlertStatus("INACTIVE");
				} else {
					if (!(obj.getExpiryDate().compareTo(obj.getAlertDate()) < 0)
							&& !(obj.getExpiryDate().compareTo(
									obj.getAlertDate()) < 0)) {
						obj.setAlertStatus("ACTIVE");
					}
					if (obj.getExpiryDate().before(dateService.getDBTime())) {
						obj.setAlertStatus("INACTIVE");
					}
				}
			} else {
				obj.setAlertStatus("ACTIVE");
			}
		}
		liReturn = ocdalertDao.alertUpdateOffenderAlerts(lstOffenderAlerts)
				+ "";
		for (OffenderAlerts objWorkFlow : lstOffenderAlerts) {
			if("Y".equals(modifiedFlag)) {
				maximumWorkFlowId = ocdalertDao.workFlowIdMaxVal(
						objWorkFlow.getOffenderBookId(),
						objWorkFlow.getAlertSeq());
				if (maximumWorkFlowId != null) {
					objWorkFlow.setWorkFlowId(Long.valueOf(maximumWorkFlowId));
					firstData = ocdalertDao.checkWorkActionCode(objWorkFlow
							.getWorkFlowId());
					if (firstData < 2) {
						workFlowId = ocdalertDao.workFlowLogsPreInsertc();
						objWorkFlow.setWorkFlowId(Long.valueOf(workFlowId));
						liReturn = ocdalertDao
								.workFlowsInsertWorkFlows(lstOffenderAlerts)
								+ "";
						liReturn = ocdalertDao
								.workFlowsInsertWorkFlowLogs(lstOffenderAlerts)
								+ "";
					} else {
						Integer workFlowSeq = ocdalertDao.preInsert(objWorkFlow
								.getWorkFlowId());
						objWorkFlow.setWorkFlowSeq(Long.valueOf(workFlowSeq));
						liReturn = ocdalertDao
								.workFlowsInsertWorkFlowLogsUpdate(lstOffenderAlerts)
								+ "";
					}
				}
			}
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderAlerts
	 *
	 * @throws SQLException
	 */
	@Transactional
	public String alertDeleteOffenderAlerts(
			final List<OffenderAlerts> lstOffenderAlerts) {
		String liReturn = null;
		liReturn = ocdalertDao.alertDeleteOffenderAlerts(lstOffenderAlerts)
				+ "";
		for (OffenderAlerts obj : lstOffenderAlerts) {
			if (obj.getOffenderBookId() != null && obj.getAlertSeq() != null) {
				Integer maxWorkFlowId = ocdalertDao.workFlowIdMaxVal(
						obj.getOffenderBookId(), obj.getAlertSeq());
				if (maxWorkFlowId != null) {
					obj.setWorkFlowId(Long.valueOf(maxWorkFlowId));
					liReturn = ocdalertDao
							.workFlowsDeleteWorkFlowLogs(lstOffenderAlerts)
							+ "";
					liReturn = ocdalertDao
							.workFlowsDeleteWorkFlows(lstOffenderAlerts) + "";
				}
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<SystemProfiles> sysPflSearchSystemProfiles(
			final SystemProfiles searchRecord) {
		return ocdalertDao.sysPflSearchSystemProfiles(searchRecord);
	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @param offenderBookId
	 *
	 * @throws SQLException
	 */
	public List<OffenderAlerts> alertSearchOffenderAlerts(
			final String offenderBookId) {
		return ocdalertDao.alertSearchOffenderAlerts(offenderBookId);

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @throws SQLException
	 */
	public List<ReferenceCodes> findDescriptionbyCode() {
		return ocdalertDao.getCodeAlertDes();
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param offenderId
	 *
	 * @throws SQLException
	 */
	public Integer countOffenderBookings(final String offenderId) {
		return ocdalertDao.countOffenderBookings(offenderId);
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param sysDual
	 *
	 * @throws SQLException
	 */
	public List<Dual> cgwhenNewFormInstancec(final SysDual sysDual) {
		return ocdalertDao.cgwhenNewFormInstancec(sysDual);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws SQLException
	 */
	@Override
	public List<ReferenceCodes> findDescriptionbyDomain(
			final ReferenceCodes referenceCodes) {
		return ocdalertDao.findDescriptionbyDomain(referenceCodes);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws SQLException
	 */
	@Override
	public List<ReferenceCodes> findDescriptionbyDescriptionCodeAndParentCode(
			final ReferenceCodes referenceCodes) {
		return ocdalertDao
				.findDescriptionbyDescriptionCodeAndParentCode(referenceCodes);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param referenceCodesBean
	 *
	 * @throws SQLException
	 */
	@Override
	public List<ReferenceCodes> cgfklkpAlertAlertRefAlc(
			final ReferenceCodes referenceCodesBean) {
		return ocdalertDao.cgfklkpAlertAlertRefAlc(referenceCodesBean);
	}

	@Override
	public List<String> findAlertStatusList() {
		return ocdalertDao.findAlertStatusList();
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstWORK_FL
	 *
	 */
	@Transactional
	public Integer workFlCommit(final WorkFlowLogsCommitBean commitBean) {
		int liReturn = 0;
		final List<OffenderAlerts> updateList = new ArrayList<>();
		final OffenderAlerts beanObj = new OffenderAlerts();
		// insertRecords
		if (commitBean.getInsertList() != null
				&& commitBean.getInsertList().size() > 0) {
			for (final WorkFlowLogs obj : commitBean.getInsertList()) {
				beanObj.setCreateUserId(commitBean.getCreateUserId());
				if (obj.getNbtOffenderBookId() != null) {
					beanObj.setOffenderBookId(Long.valueOf(String.valueOf(obj
							.getNbtOffenderBookId())));
				}
				if (obj.getNbtAlertSeq() > 0) {
					beanObj.setAlertSeq(obj.getNbtAlertSeq());
				}
				final Integer workFlowSeq = ocdalertDao.preInsert(obj
						.getWorkFlowId());
				obj.setWorkFlowSeq(workFlowSeq);
			}
			liReturn = ocdalertDao.workFlCommit(commitBean.getInsertList());
			if (liReturn == 1) {
				liReturn = 0;
				final OffenderAlerts bean = new OffenderAlerts();
				bean.setVerifiedFlag("Y");
				bean.setOffenderBookId(beanObj.getOffenderBookId());
				bean.setAlertSeq(beanObj.getAlertSeq());
				bean.setModifyUserId(commitBean.getCreateUserId());
				updateList.add(bean);
				liReturn = ocdalertDao.offAlertUpdate(updateList);
			}
		}
		return liReturn;
	}

	/**
	 * method for query callings
	 * 
	 * @param String
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfklkpAlertAlertRefCodes() {
		List<ReferenceCodes> finalList = new ArrayList<>();
		List<ReferenceCodes> actualList =  ocdalertDao.cgfklkpAlertAlertRefCodes();
		List<ReferenceCodes> alertList = ocdalertDao.getAlertTypeCount();
		Long staffCount = ocdalertDao.getStaffMemCount();
		List<String> values=alertList.stream().filter(i->i.getCode()!=null).map(d -> d.getCode()).collect(Collectors.toList());
		for (ReferenceCodes actual : actualList) {
			for (ReferenceCodes alert : alertList) {
				if (values.contains(actual.getCode())) {
					if(actual.getCode().equals(alert.getCode())) {
						if (staffCount.intValue() == alert.getStaffId()) {
							actual.setCanDisplay(false);
							finalList.add(actual);
						} else {
							actual.setCanDisplay(true);
							finalList.add(actual);
							break;
						}
					}
				}else {
					actual.setCanDisplay(true);
					finalList.add(actual);
					break;
				}
			}
		}
		return finalList;
	}

	@Override
	public List<ReferenceCodes> cgfklkpAlertAlertCode(final String alertType) {
		List<ReferenceCodes> finalList = new ArrayList<>();
		List<ReferenceCodes> actualList =  ocdalertDao.cgfklkpAlertAlertCode(alertType);
		List<ReferenceCodes> alertList = ocdalertDao.getAlertCodeCount();
		Long staffCount = ocdalertDao.getStaffMemCount();
		List<String> values=alertList.stream().filter(i->i.getCode()!=null).map(d -> d.getCode()).collect(Collectors.toList());
		for (ReferenceCodes actual : actualList) {
			for (ReferenceCodes alert : alertList) {
				if (values.contains(actual.getCode())) {
					if(actual.getCode().equals(alert.getCode())) {
						if (staffCount.intValue() == alert.getStaffId()) {
							actual.setCanDisplay(false);
							finalList.add(actual);
						} else {
							actual.setCanDisplay(true);
							finalList.add(actual);
							break;
						}
					}
				}else {
					actual.setCanDisplay(true);
					finalList.add(actual);
					break;
				}
			}
		}
		return finalList;
	}

	@Override
	public Integer alertPreInsertc(String offenderBookId) {
		return null;
	}

	@Override
	public String alertDeleteChecking() {
		String data=ocdalertDao.alertDeleteData();
		return data;
	}

	@Override
	public String alertCodeChecking() {
		String data=ocdalertDao.alertCodeData();
		return data;
	}

	
	
}