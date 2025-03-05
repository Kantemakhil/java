package net.syscon.s4.inst.booking.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.booking.OcdsabusRepository;
import net.syscon.s4.inst.booking.OcdsabusService;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceDetails;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceDetailsCommitBean;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceTreatments;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceTreatmentsCommitBean;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceUses;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceUsesCommitBean;

/**
 * Class OcdsabusServiceImpl
 */
@Service
public class OcdsabusServiceImpl extends BaseBusiness implements OcdsabusService {

	@Autowired
	private OcdsabusRepository ocdsabusRepository;
	private static final String ZERO = "0";
	private static final String ONE = "1";
	private static final String TWO = "2";
	private static final String THREE = "3";
	private static final int NUMBER_ONE = 1;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderSubstanceTreatments> offStPreInsert(final OffenderSubstanceTreatments paramBean) {
		final List<OffenderSubstanceTreatments> offenderSubstanceTreatmentsList = ocdsabusRepository
				.offStPreInsert(paramBean);

		return offenderSubstanceTreatmentsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> cgfklkpOffSuOffSuRefCod(final ReferenceCodes paramBean) {

		final List<ReferenceCodes> referenceCodesList = ocdsabusRepository.cgfklkpOffSuOffSuRefCod(paramBean);

		return referenceCodesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderSubstanceUses> cguvchkOffenderSubstanceUs(final OffenderSubstanceUses paramBean) {

		final List<OffenderSubstanceUses> offenderSubstanceUsesList = ocdsabusRepository
				.cguvchkOffenderSubstanceUs(paramBean);

		return offenderSubstanceUsesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> cgfkchkOffSdOffSdRefCod(final ReferenceCodes paramBean) {

		final List<ReferenceCodes> referenceCodesList = ocdsabusRepository.cgfkchkOffSdOffSdRefCod(paramBean);

		return referenceCodesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> cgfklkpOffSdOffSdRefCod(final ReferenceCodes paramBean) {

		final List<ReferenceCodes> referenceCodesList = ocdsabusRepository.cgfklkpOffSdOffSdRefCod(paramBean);

		return referenceCodesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderSubstanceDetails> cguvchkOffenderSubstanceDe(final OffenderSubstanceDetails paramBean) {

		final List<OffenderSubstanceDetails> offenderSubstanceDetailsList = ocdsabusRepository
				.cguvchkOffenderSubstanceDe(paramBean);

		return offenderSubstanceDetailsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> cgfkchkOffStOffStRefCod(final ReferenceCodes paramBean) {

		final List<ReferenceCodes> referenceCodesList = ocdsabusRepository.cgfkchkOffStOffStRefCod(paramBean);

		return referenceCodesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> cgfklkpOffStOffStRefCod(final ReferenceCodes paramBean) {

		final List<ReferenceCodes> referenceCodesList = ocdsabusRepository.cgfklkpOffStOffStRefCod(paramBean);

		return referenceCodesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> cgfkchkOffStOffStRef(final ReferenceCodes paramBean) {

		final List<ReferenceCodes> referenceCodesList = ocdsabusRepository.cgfkchkOffStOffStRef(paramBean);

		return referenceCodesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> cgfklkpOffStOffStRef(final ReferenceCodes paramBean) {

		final List<ReferenceCodes> referenceCodesList = ocdsabusRepository.cgfklkpOffStOffStRef(paramBean);

		return referenceCodesList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderSubstanceUses> offSuExecuteQuery(final OffenderSubstanceUses searchRecord) {
		return ocdsabusRepository.offSuExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_SU
	 *
	 * @throws SQLException
	 */
	@Transactional
	public OffenderSubstanceUses offSuCommit(final OffenderSubstanceUsesCommitBean commitBean) {
		int liReturn = 0;
		final OffenderSubstanceUses returnObj = new OffenderSubstanceUses();
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			commitBean.getInsertList().forEach(action -> {
				action.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = ocdsabusRepository.offSuInsertOffenderSubstanceUses(commitBean.getInsertList());

		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			commitBean.getUpdateList().forEach(action -> {
				action.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocdsabusRepository.offSuUpdateOffenderSubstanceUses(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {

			for (final OffenderSubstanceUses obj : commitBean.getDeleteList()) {

				obj.setModifyUserId(commitBean.getCreateUserId());
				final List<Integer> listSdTable = ocdsabusRepository.offSuOnCheckDeleteMaster(obj);
				if (listSdTable != null && !listSdTable.isEmpty()) {
					returnObj.setSealFlag(TWO);
					return returnObj;
				}
				final List<Integer> listStTable = ocdsabusRepository.offStOnCheckDeleteMaster(obj);
				if (listStTable != null && !listStTable.isEmpty()) {
					returnObj.setSealFlag(THREE);
					return returnObj;
				}

			}
			liReturn = ocdsabusRepository.offSuDeleteOffenderSubstanceUses(commitBean.getDeleteList());
		}

		if (liReturn == NUMBER_ONE) {
			returnObj.setSealFlag(ONE);
		} else {
			returnObj.setSealFlag(ZERO);
		}
		return returnObj;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderSubstanceDetails> offSdExecuteQuery(final OffenderSubstanceDetails searchRecord) {
		return ocdsabusRepository.offSdExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_SD
	 *
	 * @throws SQLException
	 */
	@Transactional
	public OffenderSubstanceDetails offSdCommit(final OffenderSubstanceDetailsCommitBean commitBean) {
		int liReturn = 0;
		boolean check = true;
		Long seq = null;
		final OffenderSubstanceDetails returnObj = new OffenderSubstanceDetails();
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (final OffenderSubstanceDetails obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				final String seqNumber = ocdsabusRepository.getoffSdSequence(obj.getOffenderBookId(),
						obj.getSubstanceType());
				if (check && seqNumber != null) {
					check = false;
					seq = Long.valueOf(seqNumber);

				}
				obj.setSeqNumber(seq);
				seq++;
			}

			liReturn = ocdsabusRepository.offSdInsertOffenderSubstanceDetails(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			commitBean.getUpdateList().forEach(action -> {
				action.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocdsabusRepository.offSdUpdateOffenderSubstanceDetails(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocdsabusRepository.offSdDeleteOffenderSubstanceDetails(commitBean.getDeleteList());
		}
		if (liReturn == NUMBER_ONE) {
			returnObj.setSealFlag(ONE);
		} else {
			returnObj.setSealFlag(ZERO);
		}
		return returnObj;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderSubstanceTreatments> offStExecuteQuery(final OffenderSubstanceTreatments searchRecord) {
		return ocdsabusRepository.offStExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_ST
	 *
	 * @throws SQLException
	 */
	@Transactional
	public OffenderSubstanceTreatments offStCommit(final OffenderSubstanceTreatmentsCommitBean commitBean) {
		int liReturn = 0;
		boolean check = true;
		Long seq = null;
		final OffenderSubstanceTreatments returnObj = new OffenderSubstanceTreatments();
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (final OffenderSubstanceTreatments obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				final String seqNumber = ocdsabusRepository.getOffStSequnce(obj.getOffenderBookId(),
						obj.getSubstanceType());
				if (check && seqNumber != null) {
					check = false;
					seq = Long.valueOf(seqNumber);

				}
				obj.setTreatmentSeq(seq);
				seq++;
			}
			liReturn = ocdsabusRepository.offStInsertOffenderSubstanceTreatments(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			commitBean.getUpdateList().forEach(action -> {
				action.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocdsabusRepository.offStUpdateOffenderSubstanceTreatments(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocdsabusRepository.offStDeleteOffenderSubstanceTreatments(commitBean.getDeleteList());
		}
		if (liReturn == NUMBER_ONE) {
			returnObj.setSealFlag(ONE);
		} else {
			returnObj.setSealFlag(ZERO);
		}
		return returnObj;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> ageRecordGroup() {
		return ocdsabusRepository.ageRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> lSourceInfoRecordGroup() {
		return ocdsabusRepository.lSourceInfoRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkOffSuDspDescriptionRecordGroup() {
		return ocdsabusRepository.cgfkOffSuDspDescriptionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkOffStDspDescription3RecordGroup() {
		return ocdsabusRepository.cgfkOffStDspDescription3RecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkOffStDspDescriptionRecordGroup() {
		return ocdsabusRepository.cgfkOffStDspDescriptionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkOffSdDspDescriptionRecordGroup() {
		return ocdsabusRepository.cgfkOffSdDspDescriptionRecordGroup();

	}

	@Override
	public OffenderSubstanceUses onDeleteOfSuAbHistory(final OffenderSubstanceUses obj) {
		final OffenderSubstanceUses returnObj = new OffenderSubstanceUses();
		final List<Integer> listSdTable = ocdsabusRepository.offSuOnCheckDeleteMaster(obj);
		if (listSdTable != null && !listSdTable.isEmpty()) {
			returnObj.setSealFlag(TWO);
		}
		return returnObj;
	}
}