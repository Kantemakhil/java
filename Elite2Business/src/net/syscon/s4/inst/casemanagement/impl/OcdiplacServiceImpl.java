package net.syscon.s4.inst.casemanagement.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.casemanagement.OcdiplacRepository;
import net.syscon.s4.inst.casemanagement.OcdiplacService;
import net.syscon.s4.inst.casemanagement.beans.CasePlanStaff;
import net.syscon.s4.inst.casemanagement.beans.CasePlanStaffCommitBean;

@Service
public class OcdiplacServiceImpl extends BaseBusiness implements OcdiplacService {


	@Autowired
	private OcdiplacRepository ocdiplacRepository;

	@Override
	public List<ReferenceCodes> staffMemebersListByAgyLocId(String agyLocId) {
		List<ReferenceCodes> returnList = ocdiplacRepository.staffMemebersListByAgyLocId(agyLocId);
		if (returnList.size() > 0) {
			for (ReferenceCodes referenceCodes : returnList) {
				String toDate = null;
				Date expiredDate = null;
				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
				if (referenceCodes.getExpiredDate() != null) {
					toDate = dateFormat.format(referenceCodes.getExpiredDate());
				}
				SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
				String fromDate = dateFormat.format(new Date());
				try {
					if (toDate != null) {
						expiredDate = sdformat.parse(toDate);
					}
					Date currentDate = sdformat.parse(fromDate);
					if (agyLocId.equalsIgnoreCase(referenceCodes.getFirstName()) && (expiredDate == null
							|| (expiredDate != null && expiredDate.compareTo(currentDate) > 0))) {
						referenceCodes.setCanDisplay(true);
					} else {
						referenceCodes.setCanDisplay(false);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
		}
		return returnList;
	}

	@Override
	public List<CasePlanStaff> getAllStaffMembersList(Integer offenderBookId, Integer casePlanId) {

		List<CasePlanStaff> returnList = ocdiplacRepository.getAllStaffMembersList(offenderBookId, casePlanId);
		return returnList;
	}

	@Override
	public Integer insertUpdateCasePlanStaffMemberDetails(CasePlanStaffCommitBean commitBean) {
		Integer returnData = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (CasePlanStaff casePlanStaffDetails : commitBean.getInsertList()) {
				casePlanStaffDetails.setCreateUserId(commitBean.getCreateUserId());
			}
			returnData = ocdiplacRepository.insertCasePlanStaffMemberDetails(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (CasePlanStaff casePlanStaffDetails : commitBean.getUpdateList()) {
				casePlanStaffDetails.setModifyUserId(commitBean.getCreateUserId());
			}
			returnData = ocdiplacRepository.updateCasePlanStaffMemberDetails(commitBean.getUpdateList());
		}

		return returnData;
	}

	@Override
	public List<CasePlanStaff> childDataCarry(Integer offenderBookId) {
		List<CasePlanStaff> returnList = ocdiplacRepository.childDataCarry(offenderBookId);
		return returnList;
	}

}
