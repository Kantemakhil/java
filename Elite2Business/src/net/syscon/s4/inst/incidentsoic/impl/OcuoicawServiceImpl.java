package net.syscon.s4.inst.incidentsoic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.syscon.s4.common.beans.OffenderOicSanctions;
import net.syscon.s4.common.beans.OffenderOicSanctionsCommitBean;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.incidentsoic.OcuoicawRepository;
import net.syscon.s4.inst.incidentsoic.OcuoicawService;
import net.syscon.s4.inst.incidentsoic.maintenance.OicSanctionLimits;
import net.syscon.s4.pkgs.tag_adjudication.TagAdjudicationService;
import net.syscon.s4.triggers.OffenderOicSanctionsT1Service;

/**
 * class OcuoicawServiceImpl
 */
@Service
public class OcuoicawServiceImpl extends BaseBusiness implements OcuoicawService {
	/**
	 * Logger object used to print the log in the file
	 */
	@Autowired
	private OcuoicawRepository ocuoicawrepository;
	
	@Autowired
	private OffenderOicSanctionsT1Service offenderOicSanctionsT1Service;
	
	@Autowired
	private TagAdjudicationService tagAdjudicationService;
	
	private static Logger logger = LogManager.getLogger(OcuoicawServiceImpl.class.getName());

	/**
	 * Creates new OcuoicawServiceImpl class Object
	 */
	public OcuoicawServiceImpl() {
		super();
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 * @throws Exception
	 */
	public List<OicOffences> whenValidateItemgetOicOffenceCodeCur(final OicOffences paramBean) {
		return ocuoicawrepository.whenValidateItemgetOicOffenceCodeCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 * @throws Exception
	 */
	public SystemProfiles getprofilevaluevsprofvalcur(final SystemProfiles paramBean) {
		return ocuoicawrepository.getprofilevaluevsprofvalcur(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderOicSanctions> oicSancSearchOffenderOicSanctions(final OffenderOicSanctions searchRecord) {
		List<OffenderOicSanctions> listvalues = new ArrayList<OffenderOicSanctions>();
		listvalues = ocuoicawrepository.oicSancSearchOffenderOicSanctions(searchRecord);
		for (final OffenderOicSanctions obj : listvalues) {
			if (obj.getConsecutiveSanctionSeq() != null) {
				Integer oicIncId = tagAdjudicationService.getAdjudicationFromSanction(obj.getConsecutiveSanctionSeq(),obj.getOffenderBookId());
			obj.setOicIncidentId(oicIncId);
			} else {
				obj.setOicIncidentId(null);
			}
			
		}
		return listvalues;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderOicSanctions
	 *
	 * @
	 */
	@Transactional
	public List<OffenderOicSanctions> oicsancInsertOffenderOicSanctions(
			final List<OffenderOicSanctions> lstOffenderOicSanctions) {
		Integer sanctionSeq = 0;
		Integer postsanction = 0;
		Integer offBookId = null;
		Integer count = 0;
		final List<OffenderOicSanctions> listcount = new ArrayList<OffenderOicSanctions>();
		 OffenderOicSanctions sanction = new OffenderOicSanctions();
		for (final OffenderOicSanctions offenderOicsan : lstOffenderOicSanctions) {
			offBookId = offenderOicsan.getOffenderBookId();
			sanction=offenderOicsan;
		}
		sanctionSeq =tagAdjudicationService.getNextSanctionSeq(offBookId);
		for (final OffenderOicSanctions offOicSanctions : lstOffenderOicSanctions) {
			offOicSanctions.setSanctionSeq(sanctionSeq);
			sanctionSeq = sanctionSeq + 1;
			OffenderOicSanctions sanc=offenderOicSanctionsT1Service.offenderOicSanctionsT1(sanction);
			offOicSanctions.setLidsSanctionNumber(sanc.getLidsSanctionNumber());
			List<OffenderOicSanctions> list=new ArrayList<OffenderOicSanctions>();
			list.add(offOicSanctions);
			count = ocuoicawrepository.oicsancInsertOffenderOicSanctions(list);
		}
		if (count == 1) {
			for (final OffenderOicSanctions offenderOic : lstOffenderOicSanctions) {
				sanction.setSanctionSeq(offenderOic.getSanctionSeq());
				listcount.add(sanction);
			}
		}
		for (final OffenderOicSanctions offenderOicsan : lstOffenderOicSanctions) {
		if(offenderOicsan.getConsecutiveSanctionSeq() !=null){
			offBookId = offenderOicsan.getOffenderBookId();
			postsanction = tagAdjudicationService.getAdjudicationFromSanction(sanctionSeq,offBookId);
		for (final OffenderOicSanctions offenderOic : listcount) {
			offenderOic.setOicIncidentId(postsanction);
		}
		}
		}
		return listcount;
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderOicSanctions
	 *
	 * @
	 */
	@Transactional
	public List<OffenderOicSanctions> oicSancUpdateOffenderOicSanctions(
			final List<OffenderOicSanctions> lstOffenderOicSan) {
		final List<OffenderOicSanctions> listcount = new ArrayList<OffenderOicSanctions>();
		final OffenderOicSanctions offenderSanction = new OffenderOicSanctions();
		Integer count = 0;
		count = ocuoicawrepository.oicSancUpdateOffenderOicSanctions(lstOffenderOicSan);
		if (count == 1) {
			for (final OffenderOicSanctions offenderOicSan : lstOffenderOicSan) {
				if (offenderOicSan.getSanctionSeq() != null) {
					offenderSanction.setSanctionSeq(offenderOicSan.getSanctionSeq());
					listcount.add(offenderSanction);
				}
			}
		}
		return listcount;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgSanctRecordGroup() {
		return ocuoicawrepository.rgSanctRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgSanctStRecordGroup() {
		return ocuoicawrepository.rgSanctStRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<OffenderOicSanctions> rgOtherSanctionsRecordGroup(final OffenderOicSanctions offenderOicSan) {
		List<OffenderOicSanctions> listRefence = null;
		listRefence = ocuoicawrepository.rgOtherSanctionsRecordGroup(offenderOicSan);
		for (final OffenderOicSanctions offenderOic : listRefence) {
			offenderOic.setHearingDate(offenderOic.getHearingDate());
			offenderOic.setConsecutiveSanctionSeq(offenderOic.getSanctionSeq());
		}
		return listRefence;
	}

	/**
	 * Perfomring insert,update
	 * 
	 * @return Integer
	 * @Param commitBean
	 */
	@Transactional
	public List<OffenderOicSanctions> oicSancCommit(final OffenderOicSanctionsCommitBean commitBean) {
		List<OffenderOicSanctions> listcount = null;
		if (commitBean.getInsertList() != null) {
			for (final OffenderOicSanctions offenderOicSan : commitBean.getInsertList()) {
				offenderOicSan.setCreateUserID(commitBean.getCreateUserId());
				if(offenderOicSan.getCompensation() != null){
				offenderOicSan.setOicIncidentId(offenderOicSan.getConstoicIncidentId());
				offenderOicSan.setCompensationAmount(Double.valueOf(offenderOicSan.getCompensation()));
				}
			}
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				listcount = oicsancInsertOffenderOicSanctions(commitBean.getInsertList());
			}
		}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				for (final OffenderOicSanctions offenderOicSan : commitBean.getUpdateList()) {
					offenderOicSan.setModifyUserId(commitBean.getCreateUserId());
					if(offenderOicSan.getCompensation() != null){
					Double sacVal = Double.valueOf(offenderOicSan.getCompensation());
					
					offenderOicSan.setCompensationAmount(sacVal);
					}
				}
				listcount = oicSancUpdateOffenderOicSanctions(commitBean.getUpdateList());
			}
		return listcount;
	}

	@Override
	public OicSanctionLimits getHearingType(OffenderOicSanctions offenderOicSan) {
		String oicHearingType= tagAdjudicationService.getHearingType(offenderOicSan.getOicHearingId());
		
//	return	ocuoicawrepository.gettingSactionLimits(oicHearingType,offenderOicSan.getOicSanctionCode());
		return null;
		
	}
	
}
