package net.syscon.s4.inst.property.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.property.OiddpropRepository;
import net.syscon.s4.inst.property.OiddpropService;
import net.syscon.s4.inst.property.bean.CaseloadFormatPrinter;
import net.syscon.s4.inst.property.bean.Corporate;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemEvents;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxnsCommitBean;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.inst.property.bean.OffenderPptyItemsCommitBean;
import net.syscon.s4.inst.property.bean.Printer;
import net.syscon.s4.triggers.OffenderPptyItemsT1Service;

/**
 * Class OiddpropServiceImpl
 */
@Service
public class OiddpropServiceImpl extends BaseBusiness implements OiddpropService {

	private static final String UPDATING = "UPDATING";

	@Autowired
	private OiddpropRepository oiddRepository;

	@Autowired
	private OffenderPptyItemsT1Service offenderPptyItemsT1Service;

	/**
	 * Creates new OiddpropServiceImpl class Object
	 */
	public OiddpropServiceImpl() {
		// OiddpropServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 */
	public OffenderPptyContainers cgfkchkItmTxItmTxOffCon(final OffenderPptyContainers paramBean) {
		return oiddRepository.cgfkchkItmTxItmTxOffCon(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 */
	public ReferenceCodes cgfkchkItmTxItmTxRefCod(final ReferenceCodes paramBean) {
		return oiddRepository.cgfkchkItmTxItmTxRefCod(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 */
	public OffenderPptyContainers cgfkchkOffPiOffPiOffCon(final OffenderPptyContainers paramBean) {
		return oiddRepository.cgfkchkOffPiOffPiOffCon(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 */
	public ReferenceCodes cgfkchkOffPiOffPiRefCod(final ReferenceCodes paramBean) {
		return oiddRepository.cgfkchkOffPiOffPiRefCod(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 */
	public ReferenceCodes cgfkchkOffPiOffPiRef(final ReferenceCodes paramBean) {
		return oiddRepository.cgfkchkOffPiOffPiRef(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderPptyItemTxns> itmTxExecuteQuery(final OffenderPptyItemTxns searchRecord) {
		return oiddRepository.itmTxExecuteQuery(searchRecord);

	}

	/**
	 * Insert & Update the records from database table
	 *
	 * @param commitBean
	 *
	 */
	@Transactional
	public Integer itmTxCommit(final OffenderPptyItemTxnsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderPptyItemTxns bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oiddRepository.itmTxInsertOffenderPptyItemTxns(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderPptyItemTxns bean : commitBean.getInsertList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oiddRepository.itmTxUpdateOffenderPptyItemTxns(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderPptyItems> offPiExecuteQuery(final OffenderPptyItems searchRecord) {
		List<OffenderPptyItems> returnList = new ArrayList<>();
		returnList = oiddRepository.offPiExecuteQuery(searchRecord);
		for (final OffenderPptyItems obj : returnList) {
			if (obj.getColor() != null) {
				final ReferenceCodes colorDesc = oiddRepository.getDescriptionOfColor(obj.getColor());
				if (colorDesc != null) {
					obj.setColor(colorDesc.getDescription());
				}
			}
			if (obj.getConditionCode() != null) {
				final ReferenceCodes conditionDesc = oiddRepository
						.getDescriptionOfConditionCode(obj.getConditionCode());
				if (conditionDesc != null) {
					obj.setConditionCode(conditionDesc.getDescription());
				}
			}

		}
		return returnList;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderPptyItems> offConPiExecuteQuery(final OffenderPptyItems searchRecord) {
		List<OffenderPptyItems> returnList= oiddRepository.offConPiExecuteQuery(searchRecord);
		if(returnList.size()>0) {
			for(OffenderPptyItems data:returnList) {
				ReferenceCodes codes=oiddRepository.getDescriptionOfConditionCode(data.getConditionCode());
				data.setConditionCodeDesc(codes.getDescription());
			}
		}
		return returnList;
	}

	/**
	 * Update the records from database table
	 *
	 * @param commitBean
	 *
	 */
	@Transactional
	public Integer offPiCommit(final OffenderPptyItemsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderPptyItems bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = offPiUpdateOffenderPptyItems(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOicHearings
	 *
	 */
	@Transactional
	public Integer offPiUpdateOffenderPptyItems(final List<OffenderPptyItems> offPptyItems) {
		List<OffenderPptyItems> oldDtaList = new ArrayList<OffenderPptyItems>();
		for (int i = 0; i < offPptyItems.size(); i++) {
			OffenderPptyItems oldBean = oiddRepository.getOldDateOfOffenderPptyItems(offPptyItems.get(i));
			oldDtaList.add(oldBean);
		}
		final String str = "true";
		final List<OffenderPptyItemEvents> insertEventList = new ArrayList<>();
		final OffenderPptyItemEvents eventBean = new OffenderPptyItemEvents();
		Set<Integer> offPptyContainerSet = new HashSet<>();
		Integer eventSeq = oiddRepository.getEventSeq(offPptyItems.get(0).getOffenderBookId());
		if (eventSeq == null) {
			eventSeq = 1;
		} else {
			eventSeq = eventSeq + 1;
		}
		eventBean.setOffenderBookId(offPptyItems.get(0).getOffenderBookId());
		eventBean.setEventSeq(eventSeq);
		eventBean.setCreateUserId(offPptyItems.get(0).getCreateUserId());
		insertEventList.add(eventBean);
		oiddRepository.itmTxInsertOffenderPptyItemEvents(insertEventList);
		
		for (final OffenderPptyItems obj : offPptyItems) {
			offPptyContainerSet.add(obj.getPropertyContainerId());
			if (str.equalsIgnoreCase(obj.getConfirmFlag())) {
				obj.setStatusCode("DISPOSED");
				obj.setConfirmFlag("N");
			}
		}
		Integer returnValue = oiddRepository.offPiUpdateOffenderPptyItems(offPptyItems);
		// Trigger call
		for (int i = 0; i < oldDtaList.size(); i++) {
			offenderPptyItemsT1Service.offenderPptyItemsT1(oldDtaList.get(i), offPptyItems.get(i), UPDATING);
		}
		return returnValue;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return oiddRepository.sysPflExecuteQuery(searchRecord);
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<OffenderPptyContainers> cgfkItmTxPropertyContainerRecordGroup(final String caseloadId,
			final Integer offenderBookId) {
		return oiddRepository.cgfkItmTxPropertyContainerRecordGroup(caseloadId, offenderBookId);
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<Corporate> rgDspCorporateNameRecordGroup() {
		return oiddRepository.rgDspCorporateNameRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgDisposedToPersonRecordGroup() {
		return oiddRepository.rgDisposedToPersonRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkOffConTrnToAgyLocIdRecordGroup(String agyLocId) {
		 List<ReferenceCodes> refList = oiddRepository.cgfkOffConTrnToAgyLocIdRecordGroup(agyLocId);
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
	public List<ReferenceCodes> cgfkItmTxFromStatusCodeRecordGroup() {
		return oiddRepository.cgfkItmTxFromStatusCodeRecordGroup();
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @return OmsModules
	 * @param paramBean
	 */
	public OmsModules printReportOldgetPrintFormatCur(final OmsModules paramBean) {
		return oiddRepository.printReportOldgetPrintFormatCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 * @return Printer
	 */
	public Printer printReportOldgetPrinterNameCur(final CaseloadFormatPrinter paramBean) {
		return oiddRepository.printReportOldgetPrinterNameCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @return Printer
	 * @param paramBean
	 */
	public Printer printReportgetPrinterNameCur(final CaseloadFormatPrinter paramBean) {
		return oiddRepository.printReportgetPrinterNameCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 * @return SystemProfiles
	 */
	public SystemProfiles printReportroleCur(final SystemProfiles paramBean) {
		return oiddRepository.printReportroleCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 * @return SystemProfiles
	 */
	public SystemProfiles printReportrolePwd(final SystemProfiles paramBean) {
		return oiddRepository.printReportroleCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 * @return SystemProfiles
	 */
	public SystemProfiles printReportlReportAlias(final SystemProfiles paramBean) {
		return oiddRepository.printReportroleCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param pProfileCode
	 * @param SystemProfiles systemProfiles
	 */
	public Object printReportlSystemProfilesDescCur(final SystemProfiles systemProfiles) {
		return oiddRepository.printReportlSystemProfilesDescCur(systemProfiles);
	}

	public List<ReferenceCodes> getDisposedToPersonsGroup(Integer offenderBookId) {
		List<ReferenceCodes> refList = oiddRepository.getDisposedToPersonsGroup(offenderBookId);
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
}