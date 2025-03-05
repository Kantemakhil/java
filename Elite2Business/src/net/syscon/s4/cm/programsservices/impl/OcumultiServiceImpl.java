package net.syscon.s4.cm.programsservices.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.OcumultiRepository;
import net.syscon.s4.cm.programsservices.OcumultiService;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

/**
 * Class OcumultiServiceImpl
 */
@Service
public class OcumultiServiceImpl extends BaseBusiness implements OcumultiService {

	@Autowired
	private OcumultiRepository ocumultiRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOffenderAllSchedules> offBlockExecuteQuery(final VOffenderAllSchedules searchRecord) {
		return ocumultiRepository.offBlockExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_BLOCK
	 *
	 */
	@Transactional
	public Integer offBlockCommit(final VOffenderAllSchedulesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean !=null && commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = ocumultiRepository.offBlockInsertVOffenderAllSchedules(commitBean.getInsertList());
			return liReturn;
		}
		return liReturn;
	} 

	@Override
	public List<ReferenceCodes> rgYnFlagRecordGroup() {
		final ReferenceCodes listOne = new ReferenceCodes();
		final ReferenceCodes listTwo = new ReferenceCodes();

		listTwo.setCode("No");
		listTwo.setDescription("No");
		listOne.setCode("Yes");
		listOne.setDescription("Yes");
		return Arrays.asList(listOne, listTwo);
	}

}