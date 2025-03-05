package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmpspayRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmpspayService;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.programsPayBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.programsPayCommitBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.programsPayCompensationBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.programsPayCompensationCommitBean;

@Service
public class OcmpspayServiceImpl extends BaseBusiness implements OcmpspayService {

	@Autowired
	private OcmpspayRepository ocmpspayRepository;

	@Override
	public List<ReferenceCodes> rgCompensationTypeRecorGroup(String programCategory) {
		return ocmpspayRepository.rgCompensationTypeRecorGroup(programCategory);
	}

	@Override
	public List<ReferenceCodes> rgCompensationCodeRecorGroup(Integer programId) {
		return ocmpspayRepository.rgCompensationCodeRecorGroup(programId);
	}

	@Override
	public List<programsPayBean> prgCategoryExecuteQuery() {
		return ocmpspayRepository.prgCategoryExecuteQuery();
	}

	@Transactional
	public Integer prgCategoryCommit(programsPayCommitBean commitBean) {
		Integer liReturn = null;
		if (!commitBean.getInsertList().isEmpty() && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(data -> data.setCreateUserId(commitBean.getCreateUserId()));
			final List<String> programCategory = commitBean.getInsertList().stream()
					.map(data -> data.getProgramCategory()).collect(Collectors.toList());
			final Integer childCount = ocmpspayRepository.categoryPreInsert(programCategory);
			if (childCount > 0) {
				return 2;
			}
			liReturn = ocmpspayRepository.categoryInsert(commitBean.getInsertList());
		}
		if (!commitBean.getUpdateList().isEmpty() && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(data -> data.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocmpspayRepository.categoryUpdate(commitBean.getUpdateList());
		}
		return liReturn;
	}

	@Override
	public List<programsPayCompensationBean> prgCampensationExecuteQuery(final programsPayBean beanObj) {
		return ocmpspayRepository.prgCampensationExecuteQuery(beanObj);
	}

	@Transactional
	public Integer prgCampensationCommit(programsPayCompensationCommitBean commitBean) {
		Integer liReturn = null;
		if (!commitBean.getInsertList().isEmpty() && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(data -> data.setCreateUserId(commitBean.getCreateUserId()));
			final List<String> programCategory = commitBean.getInsertList().stream()
					.map(data -> data.getProgramCategory()).collect(Collectors.toList());
			final List<Integer> programId = commitBean.getInsertList().stream().map(data -> data.getProgramId())
					.collect(Collectors.toList());
			final List<Integer> crsActyId = commitBean.getInsertList().stream().map(data -> data.getCrsActyId())
					.collect(Collectors.toList());
			final Integer childCount = ocmpspayRepository.compensatiponPreInsert(programCategory, programId, crsActyId);
			if (childCount > 0) {
				return 2;
			}
			liReturn = ocmpspayRepository.campensationInsert(commitBean.getInsertList());
		}
		if (!commitBean.getUpdateList().isEmpty() && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(data -> data.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocmpspayRepository.campensationUpdate(commitBean.getUpdateList());
		}
		if (!commitBean.getDeleteList().isEmpty() && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocmpspayRepository.campensationDelete(commitBean.getDeleteList());
		}
		return liReturn;
	}

	@Override
	public List<ProgramServices> listOfProgServices() {
		return ocmpspayRepository.listOfProgServices();
	}

	@Override
	public List<ReferenceCodes> rgUnitRecordGroup() {
		return ocmpspayRepository.rgUnitRecordGroup();
	}

}
