package net.syscon.s4.inst.classification.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.classification.OmuclassRepository;
import net.syscon.s4.inst.classification.OmuclassService;
import net.syscon.s4.inst.classification.beans.AssessmentSectionScoresV1;
import net.syscon.s4.inst.classification.beans.AssessmentSectionScoresV1CommitBean;

/**
 * Class OmuclassServiceImpl
 */
@Service
public class OmuclassServiceImpl extends BaseBusiness implements OmuclassService {

	@Autowired
	private OmuclassRepository omuclassRepository;

	/**
	 * Creates new OmuclassServiceImpl class Object
	 */
	public OmuclassServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> cgwhenNewFormInstance() {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<AssessmentSectionScoresV1> subTotalsExecuteQuery(final AssessmentSectionScoresV1 searchRecord) {
		final List<AssessmentSectionScoresV1> returnList = omuclassRepository.subTotalsExecuteQuery(searchRecord);
		for (final AssessmentSectionScoresV1 obj : returnList) {
			final String caseloadType = omuclassRepository.preQueryGetCaseloadType(searchRecord.getCreateUserId());
			if (caseloadType != null) {
				if ("INST".equals(caseloadType)) {
					obj.setDomain("SUP_LVL_TYPE");
					final String secDesc = omuclassRepository.preQueryGetSecurityLevel(obj);
					obj.setSecurityLevelDesc(secDesc);
				} else {
					obj.setDomain("COM_SUP_LVLS");
					final String secDesc = omuclassRepository.preQueryGetSecurityLevel(obj);
					obj.setSecurityLevelDesc(secDesc);
				}
			}
		}

		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSUB_TOTALS
	 *
	 * @
	 */
	@Transactional
	public Integer subTotalsCommit(final AssessmentSectionScoresV1CommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

}