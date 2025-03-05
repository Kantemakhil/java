package net.syscon.s4.inst.offenderissuestracking.maintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.GrievanceTypes;
import net.syscon.s4.im.beans.GrievanceTypesCommitBean;
import net.syscon.s4.inst.offenderissuestracking.maintenance.OimiitpsRepository;
import net.syscon.s4.inst.offenderissuestracking.maintenance.OimiitpsService;

@Service
public class OimiitpsServiceImpl extends BaseBusiness implements OimiitpsService{
	
	@Autowired
	private OimiitpsRepository oimiitpsRepository;


	@Override
	public List<GrievanceTypes> grievencePermissionExecuteQuery(GrievanceTypes searchBean) {
		return oimiitpsRepository.grievencePermissionExecuteQuery(searchBean);

	}
	
	@Override
	public List<ReferenceCodes> grievenceReasonRecordGroup(String grievType) {
		return oimiitpsRepository.grievenceReasonRecordGroup(grievType);
	}
	
	@Override
	public List<ReferenceCodes> grievenceTypeRecordGroup() {
		return oimiitpsRepository.grievenceTypeRecordGroup();
	}
	
	@Override
	public Integer grievencePermissionCommit(GrievanceTypesCommitBean commitBean) {
		Integer liReturn = 0;
		for (GrievanceTypes ob : commitBean.getUpdateList()) {
			ob.setCreateUserId(commitBean.getCreateUserId());
			ob.setModifyUserId(commitBean.getCreateUserId());
			if(ob.getIsSaved().equals(ApplicationConstants.YFLAG)) {
				liReturn = oimiitpsRepository.grievencePermissionUpdating(ob);
			}
			else {
				liReturn = oimiitpsRepository.grievencePermissionInseting(ob);
			}
		}
		return liReturn;
	}


}
