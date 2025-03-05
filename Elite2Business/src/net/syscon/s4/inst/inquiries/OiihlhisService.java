package net.syscon.s4.inst.inquiries;

import java.util.List;

import net.syscon.s4.im.beans.BedAssignmentHistories;

public interface OiihlhisService {

	List<BedAssignmentHistories> bedAhExecuteQuery(BedAssignmentHistories objBedAssignmentHistories);
}
