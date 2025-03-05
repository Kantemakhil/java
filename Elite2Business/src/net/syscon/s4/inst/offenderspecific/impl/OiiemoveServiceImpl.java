package net.syscon.s4.inst.offenderspecific.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.offenderspecific.OiiemoveRepository;
import net.syscon.s4.inst.offenderspecific.OiiemoveService;

/**
 * Class OiiemoveServiceImpl
 */
@Service
public class OiiemoveServiceImpl extends BaseBusiness implements OiiemoveService {

	@Autowired
	private OiiemoveRepository oiiemovRepo;

	/**
	 * Creates new OiiemoveServiceImpl class Object
	 */
	public OiiemoveServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements offBkgOnCheckDeleteMaster(final OffenderExternalMovements paramBean) {
		return oiiemovRepo.offBkgOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public OffenderBookings offEm1PostQuery(final OffenderBookings paramBean) {
		return oiiemovRepo.offEm1PostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public SystemProfiles getProfileValue(final SystemProfiles paramBean) {
		return oiiemovRepo.getProfileValue(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmDirecti(final ReferenceCodes paramBean) {
		return oiiemovRepo.cgfkchkOffEmOffEmDirecti(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmMoveRe(final ReferenceCodes paramBean) {
		return oiiemovRepo.cgfkchkOffEmOffEmMoveRe(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEm1OffEmRefMo(final ReferenceCodes paramBean) {
		return oiiemovRepo.cgfkchkOffEm1OffEmRefMo(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEm1OffEmDirect(final ReferenceCodes paramBean) {
		return oiiemovRepo.cgfkchkOffEm1OffEmDirect(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEm1OffEmMov2(final ReferenceCodes paramBean) {
		return oiiemovRepo.cgfkchkOffEm1OffEmMov2(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 *
	 */
	public List<OffenderExternalMovements> offEmExecuteQuery(final OffenderExternalMovements searchRecord) {
		List<OffenderExternalMovements> lstMovements = new ArrayList<OffenderExternalMovements>();
		lstMovements = oiiemovRepo.offEmExecuteQuery(searchRecord);
		if (lstMovements != null && lstMovements.size() > 0) {

			final List<AgencyLocations> agyLocs = oiiemovRepo.alAgyLocIdRgRecordGroup();
			final Map<String, String> agyMap = new HashMap<String, String>();
			if (agyLocs != null && !agyLocs.isEmpty()) {
				for (final AgencyLocations agyLoc : agyLocs) {
					agyMap.put(agyLoc.getAgyLocId(), agyLoc.getDescription());
				}
			}

			for (final OffenderExternalMovements objOffExt : lstMovements) {
				if (objOffExt.getDirectionCode() != null && objOffExt.getDirectionCode().equals("IN")) {
					if (objOffExt.getToAgyLocId() != null) {
						objOffExt.setToAgyLocId(agyMap.get(objOffExt.getToAgyLocId()));
					}
					if (objOffExt.getMovementType() != null && (objOffExt.getMovementType().equals("TAP")
							|| objOffExt.getMovementType().equals("INTER"))) {
						if (objOffExt.getFromCity() != null) {
							objOffExt.setFromAgyLocId(oiiemovRepo.getCityDescFromCode(objOffExt.getFromCity()));
						} else if (objOffExt.getFromAgyLocId() != null) {
							objOffExt.setFromAgyLocId(agyMap.get(objOffExt.getFromAgyLocId()));
						} else {
							if (objOffExt.getFromAddressId() != null) {
								objOffExt.setFromAgyLocId(oiiemovRepo.getFullAddress(objOffExt.getFromAddressId()));
							}
						}
					} else {
						if (objOffExt.getFromAgyLocId() != null) {
							objOffExt.setFromAgyLocId(agyMap.get(objOffExt.getFromAgyLocId()));
						}
					}

				} else {
					if (objOffExt.getFromAgyLocId() != null) {
						objOffExt.setFromAgyLocId(agyMap.get(objOffExt.getFromAgyLocId()));
					}
					if (objOffExt.getMovementType() != null && (objOffExt.getMovementType().equals("TAP")
							|| objOffExt.getMovementType().equals("INTER"))) {

						if (objOffExt.getToCity() != null) {
							objOffExt.setToAgyLocId(oiiemovRepo.getCityDescFromCode(objOffExt.getToCity()));
						} else if (objOffExt.getToAgyLocId() != null) {
							objOffExt.setToAgyLocId(agyMap.get(objOffExt.getToAgyLocId()));
						} else {
							if (objOffExt.getToAddressId() != null) {
								objOffExt.setToAgyLocId(oiiemovRepo.getFullAddress(objOffExt.getToAddressId()));
							}
						}
					} else {
						if (objOffExt.getToAgyLocId() != null) {
							objOffExt.setToAgyLocId(agyMap.get(objOffExt.getToAgyLocId()));
						}
					}

				}
			}
		}
		return lstMovements;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_EM
	 *
	 *
	 */
	@Transactional
	public Integer offEmCommit(final OffenderExternalMovementsCommitBean commitBean) {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 *
	 */
	public List<OffenderExternalMovements> offEm1ExecuteQuery(final OffenderExternalMovements searchRecord) {
		List<OffenderExternalMovements> lstMovements = new ArrayList<OffenderExternalMovements>();
		String strBookNo = null;
		lstMovements = oiiemovRepo.offEmExecuteQuery(searchRecord);
		if (lstMovements != null && lstMovements.size() > 0) {
			final List<AgencyLocations> agyLocs = oiiemovRepo.alAgyLocIdRgRecordGroup();
			final Map<String, String> agyMap = new HashMap<String, String>();
			if (agyLocs != null && !agyLocs.isEmpty()) {
				for (final AgencyLocations agyLoc : agyLocs) {
					agyMap.put(agyLoc.getAgyLocId(), agyLoc.getDescription());
				}
			}
			for (final OffenderExternalMovements objOffExt : lstMovements) {
				if (objOffExt.getOffenderBookId() != null) {
					strBookNo = oiiemovRepo.getOffenderBookingNo(objOffExt.getOffenderBookId());
					objOffExt.setNbtBookNo(strBookNo);
				}
				if (objOffExt.getDirectionCode() != null && objOffExt.getDirectionCode().equals("IN")) {
					if (objOffExt.getToAgyLocId() != null) {
						objOffExt.setToAgyLocId(agyMap.get(objOffExt.getToAgyLocId()));
					}
					if (objOffExt.getMovementType() != null && (objOffExt.getMovementType().equals("TAP")
							|| objOffExt.getMovementType().equals("INTER"))) {
						if (objOffExt.getFromCity() != null) {
							objOffExt.setFromAgyLocId(oiiemovRepo.getCityDescFromCode(objOffExt.getFromCity()));
						} else {
							if (objOffExt.getFromAgyLocId() != null) {
								objOffExt.setFromAgyLocId(agyMap.get(objOffExt.getFromAgyLocId()));
							}
						}
					} else {
						if (objOffExt.getFromAgyLocId() != null) {
							objOffExt.setFromAgyLocId(agyMap.get(objOffExt.getFromAgyLocId()));
						}
					}

				} else {
					if (objOffExt.getFromAgyLocId() != null) {
						objOffExt.setFromAgyLocId(agyMap.get(objOffExt.getFromAgyLocId()));
					}
					if (objOffExt.getMovementType() != null && (objOffExt.getMovementType().equals("TAP")
							|| objOffExt.getMovementType().equals("INTER"))) {

						if (objOffExt.getToCity() != null) {
							objOffExt.setToAgyLocId(oiiemovRepo.getCityDescFromCode(objOffExt.getToCity()));
						} else if (objOffExt.getToAgyLocId() != null) {
							objOffExt.setToAgyLocId(agyMap.get(objOffExt.getToAgyLocId()));
						} else {
							if (objOffExt.getToAddressId() != null) {
								objOffExt.setToAgyLocId(oiiemovRepo.getFullAddress(objOffExt.getToAddressId()));
							}
						}
					} else {
						if (objOffExt.getToAgyLocId() != null) {
							objOffExt.setToAgyLocId(agyMap.get(objOffExt.getToAgyLocId()));
						}
					}

				}

			}

		}
		return lstMovements;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_EM1
	 *
	 *
	 */
	@Transactional
	public Integer offEm1Commit(final OffenderExternalMovementsCommitBean CommitBean) {
		return 0;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgOffEmMovementReasonCoRecordGroup() {
		return oiiemovRepo.rgOffEmMovementReasonCoRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgOffEmMovementTypeRecordGroup() {
		return oiiemovRepo.rgOffEmMovementTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgOffEm1DirectionCodeRecordGroup() {
		return oiiemovRepo.rgOffEm1DirectionCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgOffEm1MovementTypeRecordGroup() {
		return oiiemovRepo.rgOffEm1MovementTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 */
	public List<ReferenceCodes> rgOffEm1MovementReasonCRecordGroup() {
		return oiiemovRepo.rgOffEm1MovementReasonCRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgOffEmDirectionCodeRecordGroup() {
		return oiiemovRepo.rgOffEmDirectionCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyLocations> alAgyLocIdRgRecordGroup() {
		return oiiemovRepo.alAgyLocIdRgRecordGroup();

	}

}