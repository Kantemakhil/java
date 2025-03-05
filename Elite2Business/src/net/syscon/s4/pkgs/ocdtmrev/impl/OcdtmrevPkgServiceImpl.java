package net.syscon.s4.pkgs.ocdtmrev.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.pkgs.ocdtmrev.OcdtmrevPkgRepository;
import net.syscon.s4.pkgs.ocdtmrev.OcdtmrevPkgService;

@Service
public class OcdtmrevPkgServiceImpl implements OcdtmrevPkgService {

	@Autowired
	private OcdtmrevPkgRepository ocdtmrevRepository;

	@Override
	public Date getReviewDate(final CourseSchedules searchRecord) {
		return ocdtmrevRepository.getReviewDate(searchRecord);
	}

}
