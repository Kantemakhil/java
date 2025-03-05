package net.syscon.s4.cm.programsservices.maintenance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.maintenance.OcmsvphaRepository;
import net.syscon.s4.cm.programsservices.maintenance.OcmsvphaService;
import net.syscon.s4.cm.programsservices.maintenance.VProgramPhasesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.VProgramPhases;

/**
 * Class OcmsvphaServiceImpl
 */
@Service
public class OcmsvphaServiceImpl extends BaseBusiness implements OcmsvphaService {

	@Autowired
	private OcmsvphaRepository ocmsvphaRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 * @return List<VProgramPhases>
	 */
	public List<VProgramPhases> vPrgPhssExecuteQuery(final VProgramPhases searchRecord) {
		List<VProgramPhases> returnList = ocmsvphaRepository.vPrgPhssExecuteQuery(searchRecord);
		returnList.forEach(action -> {
			if (action.getModuleTypeDesc() != null) {
				action.setModuleTypeDesc(action.getModuleTypeDesc().toUpperCase());
			}
			if (action.getDescription() != null) {
				String desc = ocmsvphaRepository.getDescription(action.getDescription());
				action.setDescription(desc);
				action.setNbtDescription(ocmsvphaRepository.getDescriptionOne(action.getDescription()));
			}
		});
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 * @return VProgramPhases
	 */
	@Transactional
	public VProgramPhases vPrgPhssCommit(VProgramPhasesCommitBean commitBean) {
		VProgramPhases liReturn = new VProgramPhases();
		List<VProgramPhases> list = new ArrayList<VProgramPhases>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (VProgramPhases vpp : commitBean.getInsertList()) {
				vpp.setCreateUserId(commitBean.getCreateUserId());
				vpp.setProgramId(vpp.getProgramId());
				vpp.setProgramPhaseId(ocmsvphaRepository.getProgramIdSeq());
				if (vpp.getDescription() != null) {
					vpp.setDescription(ocmsvphaRepository.getDescriptionOne(vpp.getDescription()));
				}
				if (vpp.getModuleType() == null) {
					vpp.setModuleType(vpp.getModuleTypeDesc());
				}
				if (vpp.getModuleTypeDesc() != null) {
					vpp.setModuleTypeDesc(ocmsvphaRepository.getModuleTypeDescription(vpp.getModuleTypeDesc()));
				}
				list.add(vpp);
			}
			liReturn = ocmsvphaRepository.vPrgPhssInsertVProgramPhases(list);
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (VProgramPhases vpp : commitBean.getUpdateList()) {
				vpp.setUserName(commitBean.getCreateUserId());
				if (vpp.getDescription() != null) {
					vpp.setDescription(ocmsvphaRepository.getDescriptionOne(vpp.getDescription()));
				}
				if (vpp.getModuleType() != null) {
					vpp.setModuleType(vpp.getModuleTypeDesc());
				}
				if (vpp.getModuleTypeDesc() != null) {
					vpp.setModuleTypeDesc(ocmsvphaRepository.getModuleTypeDescription(vpp.getModuleTypeDesc()));

				}
				list.add(vpp);
			}
			liReturn = ocmsvphaRepository.vPrgPhssUpdateVProgramPhases(list);
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (VProgramPhases vpp : commitBean.getDeleteList()) {
				vpp.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmsvphaRepository.vPrgPhssDeleteVProgramPhases(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgPsModTypeRecordGroup() {
		return ocmsvphaRepository.rgPsModTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgPsPhaseRecordGroup() {
		return ocmsvphaRepository.rgPsPhaseRecordGroup();

	}

	/**
	 * getting list seq max count from database
	 *
	 * @param bigDecimal
	 *
	 * @return Integer
	 */
	public Integer getListSeqMaxCount(BigDecimal bigDecimal) {
		Integer listseq = ocmsvphaRepository.getListSeqMaxCount(bigDecimal);
		return listseq;
	}

	/**
	 * getting getCourceActivityCount from database
	 *
	 * @param bigDecimal
	 *
	 * @return Integer
	 */
	@Override
	public Integer getCourceActivityCount(BigDecimal bigDecimal) {
		Integer listseq = ocmsvphaRepository.getCourceActivityCount(bigDecimal);
		return listseq;
	}

	@Override
	public String errorNameValidation(final String sealFlag) {
		return ocmsvphaRepository.errorNameValidation(sealFlag);
	}

}