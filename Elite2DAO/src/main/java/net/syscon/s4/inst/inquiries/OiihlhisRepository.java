package net.syscon.s4.inst.inquiries;

import java.util.List;

import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.LivingUnits;

public interface OiihlhisRepository {

	LivingUnits postQuery(Integer livingUnitId);

	List<BedAssignmentHistories> bedAhExecuteQuery(BedAssignmentHistories objBedAssignmentHistories);

}
