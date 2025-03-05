package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.casemanagement.beans.ProgramServicesCommitBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmphblkRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmphblkService;
import net.syscon.s4.pkgs.tag_service.TagServiceService;

/**
 * Class OcmphblkServiceImpl
 */
@Service
public class OcmphblkServiceImpl extends BaseBusiness implements OcmphblkService {

	@Autowired
	private OcmphblkRepository ocmphblkRepository;
	
	@Autowired
	private TagServiceService tagServiceService;
	

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */

	public List<ProgramServices> prgSrvExecuteQuery(final ProgramServices searchRecord) {
		return ocmphblkRepository.prgSrvExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table method prgSrvCommit
	 * 
	 * @param lstPRG_SRV
	 * @return Integer
	 */
	@Transactional
	public Integer prgSrvCommit(final ProgramServicesCommitBean commitBean) {
		int liReturn = 0;
		Integer vAlertNo = null;
		Integer programlistSeq;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (final ProgramServices object : commitBean.getInsertList()) {
				object.setCreateUserId(commitBean.getCreateUserId());
				if (object.getParentProgramId() != null && object.getListSeq() != null) {
					vAlertNo = ocmphblkRepository.getCheckNextPrgSrvSeqUnique(object);
				}
				if (Optional.ofNullable(vAlertNo).isPresent()) {
					return 7;
				}
				final Integer returnValue = tagServiceService.getProgramIdSeq();
				object.setProgramId(returnValue);

				if (object.getListSeq() == null) {
					programlistSeq = tagServiceService.getNextPrgSrvListSeq(object.getParentProgramId().longValue());
					object.setListSeq(BigDecimal.valueOf(programlistSeq));
				}
			}
			liReturn = ocmphblkRepository.prgSrvInsertProgramServices(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (final ProgramServices obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmphblkRepository.prgSrvUpdateProgramServices(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocmphblkRepository.prgSrvDeleteProgramServices(commitBean.getDeleteList());
		}
		return liReturn;
	}

	@Override
	public Integer getNextPrgSrvListSeq(final ProgramServices programServices) {
		return ocmphblkRepository.getNextPrgSrvListSeq(programServices);
	}

}