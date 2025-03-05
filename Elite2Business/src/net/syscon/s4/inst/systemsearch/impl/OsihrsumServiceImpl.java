package net.syscon.s4.inst.systemsearch.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.FormAccessibleFormsCommitBean;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.systemsearch.OsihrsumRepository;
import net.syscon.s4.inst.systemsearch.OsihrsumService;
import net.syscon.s4.inst.systemsearch.VHistoricalBookings;
import net.syscon.s4.pkgs.oms_form_access.OmsFormAccessService;

/**
 * Class OsihrsumServiceImpl
 */
@Service
public class OsihrsumServiceImpl extends BaseBusiness implements OsihrsumService {

	@Autowired
	private OsihrsumRepository osihrsumRepository;
	@Autowired
	private OmsFormAccessService omsFormAccessService;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VHistoricalBookings> vHisBooExecuteQuery(final String rootOffenderId) {
		final List<VHistoricalBookings> historicalBookingsList = osihrsumRepository.vHisBooExecuteQuery(rootOffenderId);
		final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		final DateFormat timeormat = new SimpleDateFormat("HH:mm:ss");
		String additionDate = null;
		String additionTime = null;
		for (final VHistoricalBookings vHistoricalBookings : historicalBookingsList) {
			if (vHistoricalBookings.getAgyLocType() != null && vHistoricalBookings.getAgyLocType().equals("INST")) {
				vHistoricalBookings.setAdmitIntakeComments(osihrsumRepository.getCommentCur(
						vHistoricalBookings.getOffenderBookId(), vHistoricalBookings.getInMovementSeq()));
				vHistoricalBookings.setReleaseCloseComments(osihrsumRepository.getOffenderReleaseComment(
						vHistoricalBookings.getOffenderBookId(),vHistoricalBookings.getOutMovementSeq()));
			} else {
				if (vHistoricalBookings.getInDate() != null) {
					additionDate = null;
					additionDate = dateFormat.format(vHistoricalBookings.getInDate());
					additionTime = timeormat.format(vHistoricalBookings.getInTime());
					vHistoricalBookings.setAdmitIntakeComments(
							osihrsumRepository.getCommCommentCur(vHistoricalBookings, additionDate, additionTime));
				}
				if (vHistoricalBookings.getOutDate() != null) {
					additionDate = null;
					additionDate = dateFormat.format(vHistoricalBookings.getOutDate());
					additionTime = timeormat.format(vHistoricalBookings.getOutTime());
					vHistoricalBookings.setReleaseCloseComments(
							osihrsumRepository.closeCommentCur(vHistoricalBookings, additionDate, additionTime));
				}
			}
		}
		return historicalBookingsList;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<FormAccessibleForms> fafExecuteQuery(final VHeaderBlock2 searchRecord) {
		List<FormAccessibleForms> formAccessibleForms = null;
		Map<String, Object> returnObject = null;
		String checkFlag = null;
		formAccessibleForms = osihrsumRepository.fafExecuteQuery(searchRecord.getCreateUserId());
		if (formAccessibleForms != null && !formAccessibleForms.isEmpty()) {
			for (final FormAccessibleForms formAccessible : formAccessibleForms) {
				if (formAccessible != null && formAccessible.getDestinationForm() != null) {
					formAccessible.setOriginatingForm("OSIHRSUM");
					formAccessible.setOffenderId(searchRecord.getRootOffenderId());
					formAccessible.setBookId(searchRecord.getOffenderBookId());
					checkFlag  = omsFormAccessService.checkDataAvailable(formAccessible);
					if(checkFlag!=null && "TRUE".equals(checkFlag)) {
						formAccessible.setCheckFlag("Y");
					} else {
						formAccessible.setCheckFlag("N");
					}
					/*returnObject = osihrsumRepository.checkDataAvaliable("OSIHRSUM",
							formAccessible.getDestinationForm(), searchRecord.getOffenderBookId(),
							searchRecord.getRootOffenderId());*/
					/*if (returnObject != null && returnObject.containsKey("P_DATA_AVAIL")) {
						if (returnObject.get("P_DATA_AVAIL") != null
								&& returnObject.get("P_DATA_AVAIL").equals("TRUE")) {
							formAccessible.setCheckFlag("Y");
						} else {
							formAccessible.setCheckFlag("N");
						}
					}*/
				}
			}
		}
		return formAccessibleForms;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstFAF
	 *
	 */
	@Transactional
	public FormAccessibleForms fafCommit(final FormAccessibleFormsCommitBean commitBean) {
		FormAccessibleForms accessibleForms = new FormAccessibleForms();
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (FormAccessibleForms bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				
			}
			accessibleForms = osihrsumRepository.fafInsertFormAccessibleForms(commitBean.getInsertList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			accessibleForms = osihrsumRepository.fafDeleteFormAccessibleForms(commitBean.getDeleteList());
		}
		return accessibleForms;
	}

	@Override
	public List<OmsModules> cgfkFafDestinationFormRecordGroup() {
		return osihrsumRepository.cgfkFafDestinationFormRecordGroup();
	}

	@Override
	public Images getImageData(final VHistoricalBookings searchBean) {
		return osihrsumRepository.getImageData(searchBean);
	}
}