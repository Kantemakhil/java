package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustment;
import net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustmentCommitBean;
import net.syscon.s4.inst.legals.beans.SentenceAdjustment;
import net.syscon.s4.inst.legalscreens.maintenance.OidcustadRepository;
import net.syscon.s4.inst.legalscreens.maintenance.OidcustadService;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalSettings;

@Service
public class OidcustadServiceImpl implements OidcustadService {
	@Autowired
	private OidcustadRepository oidcustadRepository;
	

	@Override
	@Transactional
	public Integer saveBookingsData(OffenderSentenceAdjustmentCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderSentenceAdjustment obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oidcustadRepository.saveBookings(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderSentenceAdjustment obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidcustadRepository.updateBookings(commitBean.getUpdateList());
		}

		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (OffenderSentenceAdjustment obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidcustadRepository.deleteBookings(commitBean.getDeleteList());
		}

		return liReturn;
	}

	@Override
	public List<OffenderSentenceAdjustment> fetchBookingsData(Long offenderBookId) {
		return oidcustadRepository.fetchBookingDetails(offenderBookId);
	}

	@Override
	public List<SentenceAdjustment> getBookingCodes() {
		List<SentenceAdjustment> bookings = oidcustadRepository.getBookingCodes();
		bookings.forEach(bean -> {
			if (bean.getCode() != null  && bean.getCode().equals("BKG_INI_RM")) {
				bean.setCanDisplay(false);
			}
			if (bean.getActiveFlag() != null  && bean.getActiveFlag().equals(ApplicationConstants.NFLAG)) {
				bean.setCanDisplay(false);
			}
		});
		return bookings;
	}

	@Override
	public String getDebitorCreditCode(String code) {
		return oidcustadRepository.getDebitorCreditCode(code);
	}

	@Override
	public List<SentenceAdjustment> getSentenceCodes() {
		List<SentenceAdjustment> adjustments = oidcustadRepository.getSentenceCodes();
		adjustments.forEach(bean -> {
			if (bean.getCode() != null  && bean.getCode().equals("SENT_INI_REM")) {
				bean.setCanDisplay(false);
			}
			if (bean.getActiveFlag() != null  && bean.getActiveFlag().equals(ApplicationConstants.NFLAG)) {
				bean.setCanDisplay(false);
			}
		});
		return adjustments;
	}

	@Override
	public List<OffenderSentenceAdjustment> getSentenceData(String objectType, Long offenderBookId) {
		return oidcustadRepository.getSentenceData(objectType, offenderBookId);
	}

	@Override
	public String getusagecode(String code) {
		return oidcustadRepository.getusagecode(code);
	}
	
	@Override
	public List<LegalSettings> getRemissionEligibility() {
		return oidcustadRepository.getRemissionEligibility();
	}
	
	@Override
	public OffenderExternalMovements getIntakeDetails(Long offenderBookId) {
		return oidcustadRepository.getIntakeDetails(offenderBookId);
	}

}
