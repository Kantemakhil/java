package net.syscon.s4.inst.movements.housingchanges.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.movements.beans.ReserveBedLocations;
import net.syscon.s4.inst.movements.beans.ReserveBedLocationscommitBean;
import net.syscon.s4.inst.movements.housingchanges.OidrhlocRepository;
import net.syscon.s4.inst.movements.housingchanges.OidrhlocService;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.omuavbed.OmuavbedPkgService;

/**
 * Class OidrhlocServiceImpl
 */
@Service
public class OidrhlocServiceImpl extends BaseBusiness implements OidrhlocService {

	@Autowired
	private OidrhlocRepository oidrhlocRepository;

	@Autowired
	private NonAssociationService nonAssociationService;
	@Autowired
	private OmuavbedPkgService omuavbedService;
	Integer revCount = 0;
	List<ReserveBedLocations> resBlList;

	/**
	 * Creates new OidrhlocServiceImpl class Object
	 */
	public OidrhlocServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<CaseloadAgencyLocations> cgfkchkResBlResBlCsldAl(final CaseloadAgencyLocations paramBean) {
		return (List<CaseloadAgencyLocations>) oidrhlocRepository.cgfkchkResBlResBlCsldAl(paramBean);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<ReserveBedLocations> resBlExecuteQuery(final ReserveBedLocations searchRecord) {
		final List<ReserveBedLocations> returnList = oidrhlocRepository.resBlExecuteQuery(searchRecord);
		resBlList = returnList;
		final LivingUnits livUnitBeanObj = new LivingUnits();
		final Offenders offBeanObj = new Offenders();
		final OffenderBookings offBkgObj = new OffenderBookings();
		revCount = 0;
		for (final ReserveBedLocations obj : returnList) {
			final Boolean ocReturnValue = getOcFlagValue(obj);
			obj.setOcFlag(ocReturnValue);
			final OffenderBookings offbkgObj = oidrhlocRepository.getBookingInfoCur(obj);
			offbkgObj.setLivingUnitId(obj.getLivingUnitId());
			if (offbkgObj.getOffenderBookId() != null && offbkgObj.getLivingUnitId() != null) {
				final Integer returnObj = oidrhlocRepository.validateLivingUnitId(offbkgObj);
				if (returnObj > 0) {
					obj.setWarningFlag(true);
					Map<String, Object> getConflictWarning = omuavbedService.getConflictWarning(obj.getOffenderBookId(),
							obj.getOffenderId(), obj.getLivingUnitId(),searchRecord.getAgyLocId());
					obj.setWarningMsg(getConflictWarning.get("P_WARNING_MSG") + "");
					obj.setWarningPrompt(getConflictWarning.get("P_WARNING_PROMPT") + "");
				} else {
					obj.setWarningFlag(false);
				}
			} else {
				obj.setWarningFlag(false);
			}
			if (obj.getLivingUnitId() != null) {
				livUnitBeanObj.setLivingUnitId(obj.getLivingUnitId());
				final LivingUnits livReturnObj = oidrhlocRepository.getLivingUnitIddesc(livUnitBeanObj);
				if (livReturnObj.getDescription() != null) {
					obj.setLivingUnitDesc(livReturnObj.getDescription());
				}
			}
			if (obj.getOffenderId() != null) {
				offBeanObj.setOffenderId(Long.parseLong(obj.getOffenderId().toString()));
				final Offenders offReturnObj = oidrhlocRepository.getOffenderDetails(offBeanObj);
				obj.setLastName(offReturnObj.getLastName());
				obj.setFirstName(offReturnObj.getFirstName());
				obj.setOffenderIdDisplay(offReturnObj.getOffenderIdDisplay());
				final String rootOffIdValue = String.valueOf(offReturnObj.getRootOffenderId());
				offBkgObj.setAgyLocId(obj.getAgyLocId());
				if (!"null".equals(rootOffIdValue) && obj.getAgyLocId() != null) {
					final LivingUnits livReturnObj = oidrhlocRepository.getBookingInfoCur(rootOffIdValue,
							obj.getAgyLocId());
					if (livReturnObj.getAgyLocId() != null && livReturnObj.getDescription() != null) {
						if (livReturnObj.getAgyLocId().equals(obj.getAgyLocId())
								&& livReturnObj.getDescription().equals(obj.getLivingUnitDesc())) {
							obj.setCbFlag(true);
						} else {
							obj.setCbFlag(false);
						}
					} else {
						obj.setCbFlag(false);
					}
				}

			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstRES_BL
	 *
	 * @
	 */
	@Transactional
	public Integer resBlCommit(final ReserveBedLocationscommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (ReserveBedLocations bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oidrhlocRepository.resBlInsertReserveBedLocations(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (ReserveBedLocations bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidrhlocRepository.resBlUpdateReserveBedLocations(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidrhlocRepository.resBlDeleteReserveBedLocations(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<CaseloadAgencyLocations> cgfkResBlAgyLocIdRecordGroup(final String caseloadId) {
		List<CaseloadAgencyLocations> refList = oidrhlocRepository.cgfkResBlAgyLocIdRecordGroup(caseloadId);
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
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public Integer validateLivingUnitId(final OffenderBookings searchRecord) {
		final Integer returnValue = oidrhlocRepository.validateLivingUnitId(searchRecord);
		return returnValue;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public LivingUnits getBookingInfoCur(final String offenderId, final String caseloadId) {
		final Offenders offBeanObj = new Offenders();
		LivingUnits returnObj = new LivingUnits();
		if (!("null".equals(offenderId))) {
			offBeanObj.setOffenderId(Long.parseLong(offenderId));
			final Offenders offReturnObj = oidrhlocRepository.getOffenderDetails(offBeanObj);
			final String rootOffIdValue = String.valueOf(offReturnObj.getRootOffenderId());
			returnObj = oidrhlocRepository.getBookingInfoCur(rootOffIdValue, caseloadId);
		}
		return returnObj;
	}

	@Override
	public Boolean getOcFlagValue(final ReserveBedLocations searchRecord) {
		Boolean ocFlagValue = null;
		final Integer capacityValue = oidrhlocRepository.getOccCountCur(searchRecord);
		final Integer livCount = nonAssociationService.getCountForLivUnit(searchRecord);
		final Integer bkgVcount = oidrhlocRepository.getVcountCur(searchRecord);
		if (resBlList.size() > 0) {
			Integer index = resBlList.indexOf(searchRecord);
			if (index > 0) {
				index = index - 1;
			} else if (index == -1) {
				index = resBlList.size() - 1;
			} else {
				index = 0;
			}
			if (capacityValue > livCount) {
				ocFlagValue = false;
			} else {
				ocFlagValue = true;
			}
			if (resBlList.get(index).getLivingUnitId().equals(searchRecord.getLivingUnitId())) {
				if (capacityValue > bkgVcount + revCount) {
					ocFlagValue = false;
					revCount = revCount + 1;
				}
			} else if (capacityValue > bkgVcount) {
				ocFlagValue = false;
				revCount = 1;
			}
		} else {
			if (capacityValue > livCount) {
				ocFlagValue = false;
			} else {
				ocFlagValue = true;
			}
		}
		return ocFlagValue;
	}
}