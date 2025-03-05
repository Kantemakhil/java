package net.syscon.s4.cm.programsservices.maintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.maintenance.OcmsvmodRepository;
import net.syscon.s4.cm.programsservices.maintenance.OcmsvmodService;
import net.syscon.s4.cm.programsservices.maintenance.VProgramModules;
import net.syscon.s4.cm.programsservices.maintenance.VProgramModulesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.pkgs.tag_service.TagServiceService;

/**
 * Class OcmsvmodServiceImpl
 */
@Service
public class OcmsvmodServiceImpl extends BaseBusiness implements OcmsvmodService {

	@Autowired
	private OcmsvmodRepository ocmsvmodRepository;
	@Autowired
	private TagServiceService tagServiceService;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VProgramModules> vPrgMdlsExecuteQuery(VProgramModules searchRecord) {
		List<VProgramModules> list = ocmsvmodRepository.vPrgMdlsExecuteQuery(searchRecord);
		return list;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_PRG_MDLS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public VProgramModules vPrgMdlsCommit(VProgramModulesCommitBean commitBean) {
		int totalSession = 0;
		int sessionUpdate = 0;
		VProgramModules returnData = new VProgramModules();
		VProgramModules vprogMod = new VProgramModules();
		List<VProgramModules> list = new ArrayList<VProgramModules>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (VProgramModules vpm : commitBean.getInsertList()) {
				vprogMod = vpm;
				vpm.setCreateUserId(commitBean.getCreateUserId());
			}
			returnData = ocmsvmodRepository.vPrgMdlsInsertVProgramModules(commitBean.getInsertList());
			if (returnData.getSealFlag().equals("1")) {
				totalSession = ocmsvmodRepository.getSessionCount(vprogMod);
				sessionUpdate = tagServiceService.doUpdateOnPhase(BigDecimal.valueOf(vprogMod.getProgramPhaseId()),BigDecimal.valueOf(totalSession));
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (VProgramModules vpm : commitBean.getUpdateList()) {
				vprogMod = vpm;
				vprogMod.setModifyUserId(commitBean.getCreateUserId());
				list.add(vpm);
			}
			returnData = ocmsvmodRepository.vPrgMdlsUpdateVProgramModules(list);
			if (returnData.getSealFlag().equals("1")) {
				totalSession = ocmsvmodRepository.getSessionCount(vprogMod);
				sessionUpdate = tagServiceService.doUpdateOnPhase(BigDecimal.valueOf(vprogMod.getProgramPhaseId()),BigDecimal.valueOf(totalSession));
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (VProgramModules vpm : commitBean.getDeleteList()) {
				vpm.setModifyUserId(commitBean.getCreateUserId());
				vprogMod = vpm;
			}
			returnData = ocmsvmodRepository.vPrgMdlsDeleteVProgramModules(commitBean.getDeleteList());
			if (returnData.getSealFlag().equals("1")) {
				totalSession = ocmsvmodRepository.getSessionCount(vprogMod);
				sessionUpdate = tagServiceService.doUpdateOnPhase(BigDecimal.valueOf(vprogMod.getProgramPhaseId()),BigDecimal.valueOf(totalSession));
			}
		}
		return returnData;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param searchRecord
	 *
	 * @return Integer
	 */
	public Integer getListSeqMaxCount(Integer searchRecord) {
		return ocmsvmodRepository.getListSeqMaxCount(searchRecord);
	}

}