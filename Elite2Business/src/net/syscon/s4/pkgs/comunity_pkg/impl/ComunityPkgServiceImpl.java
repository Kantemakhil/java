package net.syscon.s4.pkgs.comunity_pkg.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.VStaffLocation;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.pkgs.comunity_pkg.ComunityPkgRepository;
import net.syscon.s4.pkgs.comunity_pkg.ComunityPkgService;

@Service
public class ComunityPkgServiceImpl implements ComunityPkgService {

	@Autowired
	private ComunityPkgRepository comunityPkgRepository;

	@Override
	public Long getOfficerPo(final VStaffLocation staffLoc) {
		return comunityPkgRepository.poNumCur(staffLoc.getCalAgyLocId(),
				staffLoc.getStaffId() != null ? staffLoc.getStaffId().longValue() : null, staffLoc.getPosition(),
				staffLoc.getRole(), staffLoc.getFromDate());
	}

	@Override
	public Integer getPrimaryOwnPerOfficer(final StaffMembers searchRecord) {
		return comunityPkgRepository.curPrimaryOwnPerOfficer(searchRecord.getStaffId());
	}

}
