package net.syscon.s4.cm.intakeclosure.intakeclosureaddremoveoffices.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.intakeclosure.intakeclosureaddremoveoffices.OcdrlfccRepository;
import net.syscon.s4.cm.intakeclosure.intakeclosureaddremoveoffices.OcdrlfccService;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderBookingAgyLocsCommitBean;
import net.syscon.s4.triggers.OffenderBookingAgyLocsT1Service;

/**
 * Class OcdrlfccServiceImpl
 */
@Service
public class OcdrlfccServiceImpl extends BaseBusiness implements OcdrlfccService {

	@Autowired
	private EliteDateService eliteDateService;

	@Autowired
	private OcdrlfccRepository ocdrlfccRepository;
	
	@Autowired
	private OffenderBookingAgyLocsT1Service offenderBookingAgyLocsT1Service;
	
	

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<OffenderBookingAgyLocs> offBkgOnCheckDeleteMaster(final OffenderBookingAgyLocs paramBean) {
		return ocdrlfccRepository.offBkgOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<OffenderBookingAgyLocs> offagyOnCheckDeleteMaster(final OffenderBookingAgyLocs paramBean) {
		return ocdrlfccRepository.offagyOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<ReferenceCodes> cgfkchkOffagy1OffagyRefCo(final ReferenceCodes paramBean) {
		return ocdrlfccRepository.cgfkchkOffagy1OffagyRefCo(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<ReferenceCodes> cgfklkpOffagy1OffagyRefCo(final ReferenceCodes paramBean) {
		return ocdrlfccRepository.cgfklkpOffagy1OffagyRefCo(paramBean);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFFAGY
	 */
	@Transactional
	public Integer offagyCommit(final OffenderBookingAgyLocsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<OffenderBookingAgyLocs> recordSavingObject = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final OffenderBookingAgyLocs obj = commitBean.getInsertList().get(i);
					obj.setCreateUserId(commitBean.getCreateUserId());
					obj.setModifyUserId(commitBean.getCreateUserId());
					final Integer offBkgNumber = ocdrlfccRepository.countOffenderBookId(obj.getOffenderBookId());
					final String getYData = ocdrlfccRepository.getYOffenderBookingAgyLocs(obj);
					if ("Y".equals(getYData)) {
						liReturn = 3;
						return liReturn;
					}
					if (obj.getAdditionDate().compareTo(obj.getRemovedDate()) > 0) {
						liReturn = 4;
						return liReturn;
					}
					if (obj.getReasonCode() == null) {
						liReturn = 5;
						return liReturn;
					}
					if (offBkgNumber == 0) {
						liReturn = 6;
						return liReturn;
					}
					if (offBkgNumber > 1) {
						final Integer checkData = checkCasePlan(obj);
						if (checkData == 1) {
							liReturn = 7;
							return liReturn;
						}
						final Date transTime = eliteDateService.getDBTime();
						if (obj.getRemovedDate() != null && ((obj.getRemovedDate().compareTo(transTime) < 0)
								|| (obj.getRemovedDate().compareTo(transTime) == 0))) {
							Date addDate = trunc(obj.getRemovedDate());
							obj.setRemovedDate(addDate);
							ocdrlfccRepository.updateOffenderBookingAgyLocs(obj);
							//By Adding Trigger
							String sqlOperation = "UPDATING";
								offenderBookingAgyLocsT1Service.offenderBookingAgyLocsT1Triger(obj, sqlOperation);
							final BigDecimal seq = ocdrlfccRepository.alertPreInsertc(obj.getOffenderBookId());
							obj.setPtrId(seq);
							ocdrlfccRepository.insertOffenderBookingAgyLocs(obj);
						}
					} else {
						final Integer checkData = checkCasePlan(obj);
						if (checkData == 1) {
							liReturn = 7;
							return liReturn;
						}
						liReturn = 8;
						return liReturn;
					}

					liReturn = 1;
					return liReturn;
				}
			}
		}
		return liReturn;
	}

	public Integer checkCasePlan(final OffenderBookingAgyLocs obj) {
		Integer returnData = 0;
		final String locCur = ocdrlfccRepository.agyLocIdEndDate(obj);
		if (locCur != null) {
			if (locCur.equals(obj.getAgyLocId())) {
				returnData = 1;
			}
		}
		return returnData;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<OffenderBookingAgyLocs> offagyExecuteQuery(final OffenderBookingAgyLocs searchRecord) {
		final List<OffenderBookingAgyLocs> returnList = ocdrlfccRepository.offagyExecuteQuery(searchRecord);
		returnList.forEach(action -> {
			if (action.getAgyLocId() != null) {
				final String description = ocdrlfccRepository.cgfkchkOffagyAgencyLocation(action);
				action.setSealFlag(description);
			}
			if (action.getAdditionDate() != null) {
				final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				action.setMake(simpleDateFormat.format(action.getAdditionDate()));
			}
		});
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> cgfkOffagy1DspDescriptionRecordGroup() {
		return ocdrlfccRepository.cgfkOffagy1DspDescriptionRecordGroup();

	}

	public Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR, 0);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			calender.set(Calendar.MILLISECOND, 0);
			final Date returnDate = calender.getTime();
			return returnDate;
		}
		return null;
	}
}