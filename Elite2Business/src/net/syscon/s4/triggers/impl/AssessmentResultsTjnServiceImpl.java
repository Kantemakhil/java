package net.syscon.s4.triggers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.triggers.AssessmentResultsTjnRepository;
import net.syscon.s4.triggers.AssessmentResultsTjnService;
import net.syscon.s4.triggers.SystemProfilesT1Repository;
@Service
public class AssessmentResultsTjnServiceImpl implements AssessmentResultsTjnService {

	@Autowired
	private AssessmentResultsTjnRepository assessmentResultsTjnRepository;

	@Override
	public Integer insertAssessmentresult(List<AssessmentResults> assessmentResult) {
		
		if (assessmentResult != null) {
			Integer returnVal = assessmentResultsTjnRepository.insertAssessmentResult(assessmentResult);
			return returnVal;
		}
		return null;
	
	}

	@Override
	public Integer updateAssessmentResult(List<AssessmentResults> assessmentResult) {
		if (assessmentResult!=null) {
			Integer returnVal = assessmentResultsTjnRepository.updateAssessmentResult(assessmentResult);
		}
		return null;
	}

	@Override
	public Integer deleteAssessmentResult(List<AssessmentResults> assessmentResult) {
		if (assessmentResult!=null) {
			Integer returnVal = assessmentResultsTjnRepository.deleteAssessmentResult(assessmentResult);
		}
		return null;
	}
}
