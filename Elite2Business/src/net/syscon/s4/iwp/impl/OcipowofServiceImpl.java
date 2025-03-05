package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.VAssignedOffenders;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.legals.au.StaffWorkAssignmentsV1;
import net.syscon.s4.iwp.OcipowofRepository;
import net.syscon.s4.iwp.OcipowofService;
import net.syscon.s4.pkgs.pims_weight.PimsWeightService;


@Service
public class OcipowofServiceImpl extends BaseBusiness implements OcipowofService {

	@Autowired
	private OcipowofRepository ocipowofRepository;
	
	@Autowired
	private PimsWeightService  pimsWeightService;
	
	private static String OUMTROLE = "OUMTROLE";

	public List<StaffMembers> staffExecuteQuery(final StaffMembers searchRecord) {

		final List<StaffMembers> list = ocipowofRepository.staffExecuteQuery(searchRecord);
		final String modulename = OUMTROLE;
		Integer vTotal;
		for (final StaffMembers staffMembers : list) {
			if (OUMTROLE.equals(modulename)) {
				vTotal = 0;
				final List<StaffMemberRoles> returnData = ocipowofRepository.gettingStaffPostQuery(staffMembers);
				for (final StaffMemberRoles staffMemberRoles : returnData) {
					staffMemberRoles.setStaffId(staffMembers.getStaffId());
//					final Integer vWorkload = ocipowofRepository.gettingWorkload(staffMemberRoles);
					// adding procedure call
					final Integer vWorkload = pimsWeightService.officerWork(staffMemberRoles, searchRecord.getCreateUserId()).intValue();
					if (vWorkload != null) {
						vTotal = vTotal + vWorkload;
						staffMembers.setTotalWorkload(vTotal);
					}
				}
			}
			Integer vNumber = null;
			vNumber = ocipowofRepository.gettingOffenderStaffPostQuerry(staffMembers);

			staffMembers.setCgnbtNoOffender(vNumber);

		}
		return list;

	}

	public List<VAssignedOffenders> vAssOffExecuteQuery(final VAssignedOffenders searchRecord) {
		final List<VAssignedOffenders> list = ocipowofRepository.vAssOffExecuteQuery(searchRecord);
		list.forEach(element -> {
			final Images image = ocipowofRepository.imageData(element.getOffenderBookId());
			if (image != null && image.getImageThumbnail() != null) {
				element.setImageData(image.getImageThumbnail());
		}

			Integer vHpCount = 0;
			Integer vYCount = 0;
			Integer vAcount = 0;

			vHpCount = ocipowofRepository.gettingHpCountNumber(element.getOffenderBookId());
			vYCount = ocipowofRepository.gettingYCountNumber(element.getOffenderBookId());
			vAcount = ocipowofRepository.gettingACountNumber(element.getOffenderBookId());
			if (vHpCount > 0) {
				element.setDspCaseType("HP");
			} else {
				if (vYCount > 0 && vAcount > 0) {
					element.setDspCaseType("D");
				} else if (vYCount > 0 && vAcount == 0) {
					element.setDspCaseType("Y");
				} else if (vAcount > 0 && vYCount == 0) {
					element.setDspCaseType("A");
				} else {
					element.setDspCaseType(null);
				}
			}

		});

		return list;

	}

	public List<StaffWorkAssignmentsV1> vswaExecuteQuery(final StaffWorkAssignmentsV1 searchRecord) {
		final List<StaffWorkAssignmentsV1> list = ocipowofRepository.vswaExecuteQuery(searchRecord);

		list.forEach(element -> {

//			final BigDecimal vWeighting = ocipowofRepository.gettingLinePostQuerry(element);
			// adding procedure call
			final BigDecimal vWeighting = BigDecimal.valueOf(pimsWeightService.getWeighting(searchRecord));
			if (vWeighting != null) {
				element.setLine(vWeighting);
			}

		});
		return list;

	}

}