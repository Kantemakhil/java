package net.syscon.s4.pkgs.ocdtmrev;

import java.util.Date;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;

public interface OcdtmrevPkgService {

	Date getReviewDate(final CourseSchedules searchRecord);
}
