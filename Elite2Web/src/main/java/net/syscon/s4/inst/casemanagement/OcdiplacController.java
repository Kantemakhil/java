package net.syscon.s4.inst.casemanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.CasePlanStaff;
import net.syscon.s4.inst.casemanagement.beans.CasePlanStaffCommitBean;

@EliteController
public class OcdiplacController {

	private static Logger logger = LogManager.getLogger(OcdiplacController.class.getName());

	@Autowired
	private OcdiplacService ocdiplacService;

	@RequestMapping(value = "/ocdiplac/staffMemebersListByAgyLocId", method = RequestMethod.GET)
	public List<ReferenceCodes> staffMemebersListByAgyLocId(@RequestParam("agyLocId") String agyLocId) {
		List<ReferenceCodes> liReturn = new ArrayList<ReferenceCodes>();
		try {
			liReturn = ocdiplacService.staffMemebersListByAgyLocId(agyLocId);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in staffMemebersListByAgyLocId  : " + e);
		}
		return liReturn;
	}

	@RequestMapping(value = "/ocdiplac/getAllStaffMembersList", method = RequestMethod.GET)
	public List<CasePlanStaff> getAllStaffMembersList(
			@RequestParam(value = "offenderBookId") final Integer offenderBookId,
			@RequestParam(value = "casePlanId") final Integer casePlanId) {
		List<CasePlanStaff> liReturn = new ArrayList<CasePlanStaff>();
		try {
			liReturn = ocdiplacService.getAllStaffMembersList(offenderBookId, casePlanId);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getAllStaffMembersList  : " + e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdiplac/insertUpdateCasePlanStaffMemberDetails", method = RequestMethod.POST)
	public Integer insertUpdateCasePlanStaffMemberDetails(@RequestBody final CasePlanStaffCommitBean commitBean) {
		Integer liReturn = 0;

		try {
			if (commitBean != null) {
				String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userId);
			}

			liReturn = ocdiplacService.insertUpdateCasePlanStaffMemberDetails(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in insertUpdateCasePlanStaffMemberDetails :: " + e);
		}

		return liReturn;
	}

	@RequestMapping(value = "/ocdiplac/childDataCarry", method = RequestMethod.GET)
	public List<CasePlanStaff> childDataCarry(@RequestParam(value = "offenderBookId") final Integer offenderBookId) {
		List<CasePlanStaff> liReturn = new ArrayList<CasePlanStaff>();
		try {
			liReturn = ocdiplacService.childDataCarry(offenderBookId);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getAllStaffMembersList  : " + e);
		}
		return liReturn;
	} 
}
