package net.syscon.s4.pkgs.pims_weight.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.inst.legals.au.StaffWorkAssignmentsV1;
import net.syscon.s4.pkgs.PotConcurrencies;
import net.syscon.s4.pkgs.TempWeightings;
import net.syscon.s4.pkgs.pims_weight.PimsWeightRepository;
import net.syscon.s4.pkgs.pims_weight.PimsWeightService;

@Service
public class PimsWeightServiceImpl implements PimsWeightService {

	@Autowired
	private PimsWeightRepository pimsWeightRepository;

	private static final String ZERO = "-0";
	private static final String ONE = "-1";

	private static Logger logger = LogManager.getLogger(PimsWeightServiceImpl.class.getName());
	//this method is used for retrieving reviewSupLevelType from database
	@Override
	public String getSupLevel(final Long pOffenderBookId,final String userName) {
		Long lvAssessmentSeq = null;
		String lvCaseloadType = null;
		lvCaseloadType = getCaseloadType(userName);
		lvAssessmentSeq = pimsWeightRepository.getMaxAssSeqCur(lvCaseloadType, pOffenderBookId);
		return pimsWeightRepository.getSupLevelCur(pOffenderBookId, lvAssessmentSeq);
	}
    //This method is used for retrieving weighting from database
	@Override
	public Long getWeighting(final StaffWorkAssignmentsV1 element) {
		String lvSupLevel = null;
		String lvTimeServed = null;
		Long lvWeighting = null;

		lvTimeServed = timeServedCode(element.getOffenderBookId().longValue(), element.getChargeSeq().longValue(),
				element.getSentenceSeq().longValue());
		if (lvTimeServed.equals(ZERO)) {
			lvWeighting = 0l;
		} else {
			lvSupLevel = getSupLevel(element.getOffenderBookId().longValue(),element.getCreateUserId());
		}
		return pimsWeightRepository.selectWeight(element.getOrderType(), element.getOrderCode(), element.getComponent(),
				lvSupLevel, lvTimeServed);
	}

	@Override
	public String timeServedCode(final Long offenderBookId, final Long chargeSeq, final Long seq) {
		String lvTsCode = null;
		if (lvTsCode != null) {
			return lvTsCode;
		} else {
			lvTsCode = ONE;
		}
		return lvTsCode;
	}

	@Override
	public String getCaseloadType(final String userName) {
		return pimsWeightRepository.getCaseloadType(userName);
	}
    
	//This method is used for calculating workload
	@Override
	public Long officerWork(final StaffMemberRoles staffMem, final String userName) {
		Long lvWorkLoad = null;
		Long lvWeighting = null;
		Long lvOffenderBookId = null;
		Long lvChargeSeq = null;
		Long lvSeq = null;
		String lvOrderType = null;
		String lvOrderCodenull;
		String lvComponent = null;
		String lvsentenceStatus = null;
		Date lvSentenceExpiryDate = null;
		Long lvSessionId = null;
		Long lvOverWeighting = null;

		try {
		pimsWeightRepository.userEnv();
		
		List<StaffWorkAssignmentsV1> offaaasCur = pimsWeightRepository.offaaasCur(staffMem);

		Long lvOldBookId = null;
		String lvOldOrderType = null;
		String lvOldOrderCode = null;
		String lvOldComponent = null;
		Long lvOldWeighting = 0l;
		Long lvCount = null;
		
		if (lvCount == null) {
			lvCount = 1l;
		}
		if (!offaaasCur.isEmpty()) {
			for (final StaffWorkAssignmentsV1 bean : offaaasCur) {
				bean.setOffenderBookId(bean.getBookId());
				bean.setChargeSeq(bean.getChargeSeq());
				bean.setSentenceSeq(bean.getLine());
				bean.setOrderType(bean.getOrderType());
				bean.setOrderCode(bean.getOrderType());
				bean.setComponent(bean.getComponent());
				
				lvWeighting = getWeighting(bean);
			}
		}
//
		if (lvOldBookId == null) {
			for (final StaffWorkAssignmentsV1 bean : offaaasCur) {
				lvOldBookId = bean.getOffenderBookId().longValue();
				lvOldOrderType = bean.getOrderType();
				lvOldOrderCode = bean.getOrderCode();
				lvOldWeighting = lvWeighting;
				lvOldComponent = lvComponent;
			}
		}

		if (lvOldBookId != null && lvOffenderBookId != null && lvOldBookId == lvOffenderBookId && lvOldOrderType.equals(lvOrderType)
				&& lvOldOrderCode.equals(lvOldOrderCode) && lvOldComponent.equals(lvComponent)) {
			if (lvOldWeighting < lvWeighting) {
				lvOldWeighting = lvWeighting;
			}
		} else {
			final TempWeightings bean = new TempWeightings();
			bean.setBookId(lvOffenderBookId);
			bean.setOrderType(lvOrderType);
			bean.setOrderCode(lvOldOrderCode);
			bean.setComponent(lvComponent);
			bean.setWeighting(lvWeighting);
			bean.setCreateUserId(userName);
			pimsWeightRepository.insertTempWeightings(bean);
		}
		final TempWeightings bean = new TempWeightings();
		bean.setBookId(lvOffenderBookId);
		bean.setOrderType(lvOrderType);
		bean.setOrderCode(lvOldOrderCode);
		bean.setComponent(lvComponent);
		bean.setWeighting(lvWeighting);
		bean.setCreateUserId(userName);
		if (lvCount == 0) {
			pimsWeightRepository.insertTempWeightingsOne(bean);
		} else {
			return 0l;
		}

		final List<TempWeightings> allRecsCur = pimsWeightRepository.allRecsCur(lvSessionId);
		
		for (final TempWeightings allRecsCur1 : allRecsCur) {
			lvCount = 0l;
			List<PotConcurrencies> conCur = pimsWeightRepository.conCur(allRecsCur1.getOrderType(),
					allRecsCur1.getOrderCode());
			for (final PotConcurrencies conCur1 : conCur) {

				if (allRecsCur1.getOrderType().equals(conCur1.getOredrType())
						&& allRecsCur1.getOrderCode().equals(conCur1.getOrderCode())) {
					lvOrderType = conCur1.getOrderTypeConcurrentTo();
					lvOldOrderCode = conCur1.getOrderCodeConcurrentTo();
				} else {
					lvOrderType = conCur1.getOredrType();
					lvOldOrderCode = conCur1.getOrderCode();
				}
				final TempWeightings tempWeig = new TempWeightings();
				tempWeig.setBookId(allRecsCur1.getBookId());
				tempWeig.setOrderType(lvOrderType);
				tempWeig.setOrderCode(lvOldOrderCode);
				tempWeig.setComponent(allRecsCur1.getComponent());
				final List<TempWeightings> conTmpWeiCur = pimsWeightRepository.conTmpWeiCur(tempWeig);
				for (final TempWeightings tempWgts : conTmpWeiCur) {
					lvCount = lvCount + 1l;
					if (tempWgts.getOverriddenBy() != null) {
						lvOverWeighting = pimsWeightRepository.lvOverWeighting(tempWgts.getOverriddenBy(), lvSessionId);
						if (lvOverWeighting < allRecsCur1.getWeighting()) {
							pimsWeightRepository.updatrWeightingsOne(allRecsCur1.getWeighting(), allRecsCur1.getRowId(),
									userName);
							pimsWeightRepository.updatrWeightingsTwo(allRecsCur1.getTmpWeiId(),
									tempWgts.getOverriddenBy(), lvSessionId, userName);
						} else {
							pimsWeightRepository.updatrWeightingsThree(tempWgts.getOverriddenBy(),
									allRecsCur1.getRowId(), userName);
						}
					} else {
						if (tempWgts.getWeighting() < allRecsCur1.getWeighting()) {
							pimsWeightRepository.updateWeightingsFour(allRecsCur1.getWeighting(),
									allRecsCur1.getRowId(), userName);
							pimsWeightRepository.updateWeightingsFive(allRecsCur1.getTmpWeiId(), tempWgts.getTmpWeiId(),
									lvSessionId, userName);
						} else {
							pimsWeightRepository.updateWeightingsSix(allRecsCur1.getTmpWeiId(), tempWgts.getTmpWeiId(),
									userName);
						}
					}
				}
			}
			if (lvCount == 0) {
				pimsWeightRepository.updateWeightingsSeven(allRecsCur1.getWeighting(), allRecsCur1.getRowId(),
						userName);
			}
		}
		lvWorkLoad = pimsWeightRepository.calculatedWeighting(lvSessionId);
		pimsWeightRepository.deleteWeightings(lvSessionId, userName);
		}catch (Exception e) {
			logger.error("officerWork()",e);
		}
		return lvWorkLoad;
	}
}