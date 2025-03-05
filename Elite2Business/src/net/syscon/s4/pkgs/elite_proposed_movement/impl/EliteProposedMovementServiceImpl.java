package net.syscon.s4.pkgs.elite_proposed_movement.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;
import net.syscon.s4.inst.transportation.maintenance.beans.OffenderMovementDetails;
import net.syscon.s4.pkgs.elite_proposed_movement.EliteProposedMovementRepository;
import net.syscon.s4.pkgs.elite_proposed_movement.EliteProposedMovementService;

@Service
public class EliteProposedMovementServiceImpl implements EliteProposedMovementService {

	private static Logger logger = LogManager.getLogger(EliteProposedMovementServiceImpl.class.getName());

	@Autowired
	private EliteProposedMovementRepository eliteProposedMovementRepository;

	@Override
	public String ifRoleAssigned(String userId, String roleNm) {
		String returnRole = null;
		try {
			returnRole = eliteProposedMovementRepository.ifRoleAssigned(userId, roleNm);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " ifRoleAssigned() ", e);
		}
		return returnRole;
	}

	@Override
	public Integer getLocationSeq(Integer offenderBookId) {
		Integer locSeqFlag = 0;
		try {
			locSeqFlag = eliteProposedMovementRepository.getSeqCurInst(offenderBookId);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " getLocationSeq() ", e);
		}
		return locSeqFlag;
	}

	@Override
	public Integer insertLocChngDtls(OffenderLocChngDtls insertBean) {
		int insertCount = 0;
		try {
			Integer detSeq = eliteProposedMovementRepository.getSeqCur(insertBean.getOffenderBookId(),
					insertBean.getLocationSeq());
			if (detSeq != null) {
				insertBean.setDetailSeq(detSeq);
			}
			insertCount = eliteProposedMovementRepository.insertLocChngDtls(insertBean);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " insertLocChngDtls() ", e);
		}
		return insertCount;
	}

	@Override
	public List<OffenderLocChngDtls> latestStatusesIntlocs(String pChoice, Integer pOffBkg, Integer pLocSeq) {
		List<OffenderLocChngDtls> returnList = new ArrayList<>();
		try {
			if (pChoice != null) {
				if ((pChoice.equalsIgnoreCase("NEW")) || (pChoice.equalsIgnoreCase("APP"))
						|| (pChoice.equalsIgnoreCase("TXN"))) {
					returnList = eliteProposedMovementRepository.statusInmCurInstLoc(pChoice, pOffBkg, pLocSeq);
				} else {
					returnList = eliteProposedMovementRepository.maxStatusInmCurInstLoc(pChoice, pOffBkg, pLocSeq);
				}
			}
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " latestStatusesIntlocs() ", e);
		}
		return returnList;
	}

	@Override
	public String ifIntrNonAssoExists(Integer offenderBookId, String currAgyId, Integer toLivingUnitId) {
		return eliteProposedMovementRepository.ifIntrNonAssoExists(offenderBookId, currAgyId, toLivingUnitId);
	}

	@Override
	public String getImprisonmentStatus(Integer offenderBookId) {
		String vImpStatus = null;
		try {
			vImpStatus = eliteProposedMovementRepository.impStatusCur(offenderBookId);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " getImprisonmentStatus() ", e);
		}
		return vImpStatus;
	}

	@Override
	public Integer ifSanctionExists(Integer offenderBookId) {
		Integer vCount = null;
		try {
			vCount = eliteProposedMovementRepository.sanctionCur(offenderBookId);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " ifSanctionExists() ", e);
		}
		if (vCount > 0) {
			return 1;
		}
		return vCount;
	}

	@Override
	public Integer getStgAffiliation(Integer offenderBookId) {
		Integer vDescp = null;
		try {
			vDescp = eliteProposedMovementRepository.stgAffiCur(offenderBookId);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " getStgAffiliation() ", e);
		}
		return vDescp;
	}

	@Override
	public List<OffenderMovementDetails> latestStatusesExtlocs(String pChoice, Integer pOffBkg, Integer pMoveSeq) {
		List<OffenderMovementDetails> returnListObj = new ArrayList<OffenderMovementDetails>();
		OffenderMovementDetails returnList = null;
		try {
			if (pOffBkg != null) {
				if ((ApplicationConstants.NEW.equalsIgnoreCase(pChoice))
						|| (ApplicationConstants.APP.equalsIgnoreCase(pChoice))
						|| (ApplicationConstants.TXN.equalsIgnoreCase(pChoice))) {

					returnListObj = eliteProposedMovementRepository.statusInmCur(pChoice, pOffBkg, pMoveSeq);
				} else {

					returnListObj = eliteProposedMovementRepository.maxStatusInmCur(pChoice, pOffBkg, pMoveSeq);
				}
			} else {
				if ((ApplicationConstants.NEW.equalsIgnoreCase(pChoice))
						|| (ApplicationConstants.APP.equalsIgnoreCase(pChoice))
						|| (ApplicationConstants.TXN.equalsIgnoreCase(pChoice))) {
					returnList = eliteProposedMovementRepository.statusNonInmCur();
				} else {
					returnList = eliteProposedMovementRepository.maxStatusNonInmCur();
				}
			}
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " latestStatusesExtlocs() ", e);
		}
		return returnListObj;
	}

	public Integer insertExtMvmtDtls(OffenderMovementDetails insertBean) {
		Long detSeq = eliteProposedMovementRepository.getSeqCur(insertBean.getOffenderBookId(),
				insertBean.getMovementSeq());
		try {
		if (detSeq != null) {
			insertBean.setDetailSeq(detSeq);
		}
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " insertExtMvmtDtls() ", e);
		}
		return eliteProposedMovementRepository.insertExtMvmtDtls(insertBean);

	}

}
