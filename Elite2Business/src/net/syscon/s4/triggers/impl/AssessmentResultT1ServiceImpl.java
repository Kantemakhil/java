package net.syscon.s4.triggers.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.assesments.maintenance.OcmnoqueService;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.triggers.AssessmentResultT1Repository;
import net.syscon.s4.triggers.AssessmentResultT1Service;

@Service
public class AssessmentResultT1ServiceImpl implements AssessmentResultT1Service {
	private final Logger logger = LogManager.getLogger(AssessmentResultT1ServiceImpl.class.getName());
	@Autowired
	AssessmentResultT1Repository assessmentResultT1Repository;
	@Autowired
	OcmnoqueService ocmnoqueService;

	@Override
	public Integer assessmentResultT1Tgr(final List<AssessmentResults> assessmentResultsList) {
		if (!assessmentResultsList.isEmpty()) {
			for (final AssessmentResults newObj : assessmentResultsList) {
				final AssessmentResults old = assessmentResultT1Repository.getAssessmentResults(newObj);
				if (Optional.ofNullable(old).isPresent()) {
					//ocmnoqueService.AssessentResultUsed not yet Implemented 
//					if (ocmnoqueService.AssessentResultUsed(old.getAssessmentId(), old.getSupervisionLevelType())) {
//						throw new CustomException("The Assessment Result has been applied and cannot be deleted");
//					}
				}
			}
		}
		return null;
	}
}
