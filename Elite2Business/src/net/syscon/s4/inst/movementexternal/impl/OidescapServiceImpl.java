package net.syscon.s4.inst.movementexternal.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderEscape;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.movementexternal.OidescapRepository;
import net.syscon.s4.inst.movementexternal.OidescapService;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapes;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapesCommitBean;
import net.syscon.s4.triggers.OffenderEscapesT1Service;

/**
 * Class OidescapServiceImpl
 */
@Service
public class OidescapServiceImpl extends BaseBusiness implements OidescapService {

	@Autowired
	private OidescapRepository oidescapRepo;
	
	
	@Autowired
	private OffenderEscapesT1Service offenderEscapesT1Service;
	
	
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidescapServiceImpl.class.getName());

	/**
	 * Creates new OidescapServiceImpl class Object
	 */
	public OidescapServiceImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffenderEscapes> offEscExecuteQuery(final OffenderEscapes searchRecord) {

		List<OffenderEscapes> lstOffEsc = new ArrayList<OffenderEscapes>();
		lstOffEsc = (List<OffenderEscapes>) oidescapRepo.offEscExecuteQuery(searchRecord);
		if (lstOffEsc != null && !lstOffEsc.isEmpty()) {
			final String strBookNo = oidescapRepo.getOffenderBookingNo(searchRecord);
			for (final OffenderEscapes offEsc : lstOffEsc) {
//				offEsc.setEscapeAgyLocId(oidescapRepo.getOffenderFromLocation(offEsc.getOffenderBookId()));
				final String returnObj = oidescapRepo.getreasonDescPreQuery(offEsc.getEscapeMovementReason());
				if (returnObj != null) {
					offEsc.setDspEscapeReason(returnObj);
				}
				final String recaptureDesc = oidescapRepo.getRecaptureReasonDescPreQuery(offEsc);
				if (recaptureDesc != null) {
					offEsc.setDspRecaptureReason(recaptureDesc);
				}
				offEsc.setBookNo(strBookNo);
			}

		}
		return lstOffEsc;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_ESC
	 *
	 * 
	 */
	@Transactional
	public Integer offEscCommit(final OffenderEscapesCommitBean commitBean) {
		Integer liReturn = 0;
		Integer liInsertReturn = 0;
		List<OffenderEscapes> offEscInsert = new ArrayList<OffenderEscapes>();

		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final OffenderEscapes offEsc : commitBean.getInsertList()) {
				offEsc.setCreateUserId(commitBean.getCreateUserId());
				OffenderEscape  offesbean = new OffenderEscape();
				offesbean.setEscapeDate(offEsc.getEscapeDate());
				offesbean.setEscapeTime(offEsc.getEscapeTime());
				try {
				offEscInsert = new ArrayList<OffenderEscapes>();
				final Integer escapeId = oidescapRepo.offEscPreInsert();
				offEsc.setEscapeId(escapeId);

				final String strAdjFlag = offEsc.getAdjustSentenceFlag();
				if (strAdjFlag != null && strAdjFlag.equals("true")) {
					offEsc.setAdjustSentenceFlag("Y");
				} else {
					offEsc.setAdjustSentenceFlag("N");
				}
				final String strCmpFlag = offEsc.getInCompanyFlag();
				if (strCmpFlag != null && strCmpFlag.equals("true")) {
					offEsc.setInCompanyFlag("Y");
				} else {
					offEsc.setInCompanyFlag("N");
				}
				offEscInsert.add(offEsc);
				OffenderEscape  offesbean1 = new OffenderEscape();
				offesbean1 = offenderEscapesT1Service.offenderEscapesT1(offesbean);
				offEsc.setEscapeTime(offesbean1.getEscapeTime());
				
				liReturn = oidescapRepo.offEscInsertOffenderEscapes(offEscInsert);
				if (liReturn == 0) {
					logger.error("Offender Escape insert fail : ");
					throw new RuntimeException("Offender Escape insert fail : ");
				}

				if (offEsc.getReturnFlag() != null && offEsc.getReturnFlag()) {
					liInsertReturn = liReturn;
				}
				} catch (Exception e) {
					logger.error("Main method Insert : ", e);
					throw new RuntimeException("Main method Insert : ", e);
				}
			}
		}

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final OffenderEscapes offEsc : commitBean.getUpdateList()) {
				offEsc.setModifyUserId(commitBean.getCreateUserId());
				OffenderEscape  offesbean = new OffenderEscape();
				offesbean.setEscapeDate(offEsc.getEscapeDate());
				offesbean.setEscapeTime(offEsc.getEscapeTime());
				try {
				final String strAdjFlag = offEsc.getAdjustSentenceFlag();
				if (strAdjFlag != null && strAdjFlag.equals("true")) {
					offEsc.setAdjustSentenceFlag("Y");
				} else {
					offEsc.setAdjustSentenceFlag("N");
				}
				final String strCmpFlag = offEsc.getInCompanyFlag();
				if (strCmpFlag != null && strCmpFlag.equals("true")) {
					offEsc.setInCompanyFlag("Y");
				} else {
					offEsc.setInCompanyFlag("N");
				}
				if (offEsc.getReturnFlag() != null && offEsc.getReturnFlag()) {
					liInsertReturn = offEsc.getEscapeId();
				}
				} catch (Exception e) {
					logger.error("Main method Update : ", e);
					throw new RuntimeException("Main method Update : ", e);
				}
				OffenderEscape  offesbean1 = new OffenderEscape();
				offesbean1 = offenderEscapesT1Service.offenderEscapesT1(offesbean);
				offEsc.setEscapeTime(offesbean1.getEscapeTime());
			}
			liReturn = oidescapRepo.offEscUpdateOffenderEscapes(commitBean.getUpdateList());
		}

		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidescapRepo.offEscDeleteOffenderEscapes(commitBean.getDeleteList());
		}

		if (liInsertReturn > 0) {
			liReturn = liInsertReturn;
		}

		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return oidescapRepo.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		return 0;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyLocations> cgfkOffEscEscapeAgyLocIdRecordGroup() {
		List<AgencyLocations> refList = oidescapRepo.cgfkOffEscEscapeAgyLocIdRecordGroup();
		if(Optional.ofNullable(refList).isPresent()) {
			refList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return refList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkOffEscEscapeEscortCodRecordGroup() {
		return oidescapRepo.cgfkOffEscEscapeEscortCodRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkOffEscEscapeCircumstanRecordGroup() {
		return oidescapRepo.cgfkOffEscEscapeCircumstanRecordGroup();

	}

	@Override
	public List<Object> cgwhenNewFormInstance() {
		return null;
	}

	@Override
	public OffenderEscapes offBkgOnCheckDeleteMaster(final OffenderEscapes paramBean) {
		return null;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkOffEscSecurityBreachCRecordGroup() {
		return oidescapRepo.cgfkOffEscSecurityBreachCRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> cgfkOffEscArrestAgyCodeRecordGroup() {
		return oidescapRepo.cgfkOffEscArrestAgyCodeRecordGroup();

	}

	/**
	 * Used to getMax escape Date no for the Offender
	 * 
	 * @return Date
	 */
	public Date getMaxEscapeDate(final Integer offenderBookId) {
		return oidescapRepo.getMaxEscapeDate(offenderBookId);
	}

}
