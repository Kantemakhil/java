package net.syscon.s4.inst.workflow.maintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.teamsworkflow.beans.Work;
import net.syscon.s4.cm.teamsworkflow.beans.WorkCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.workflow.maintenance.OcmcnperRepository;
import net.syscon.s4.inst.workflow.maintenance.OcmcnperSevice;

@Service
public class OcmcnperSeviceImpl extends BaseBusiness implements OcmcnperSevice {
	@Autowired
	private OcmcnperRepository ocmcnperRepository;

	@Override
	public Integer caseNotePermissionCommit(WorkCommitBean commitBean) {
		Integer liReturn = 0;
		Integer count = 0;
		for (Work ob : commitBean.getUpdateList()) {
			ob.setCreateUserId(commitBean.getCreateUserId());
			ob.setModifyUserId(commitBean.getCreateUserId());
			count = ocmcnperRepository.gettingCountFromCaseNotePermission(ob.getWorkId(), ob.getRoleId());
			if (count == 0) {
				liReturn = ocmcnperRepository.caseNotePermissionInseting(ob);
			} else {
				liReturn = ocmcnperRepository.caseNotePermissionUpdating(ob);
			}
		}
		

		return liReturn;
	}

	@Override
	public List<Work> caseNotePermissionExecuteQuery(Work searchBean) {
		return ocmcnperRepository.caseNotePermissionExecuteQuery(searchBean);

	}

}
