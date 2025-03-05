package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.StaffMembers;

public interface OmtsmService {

	StaffMembers getOmtsam(StaffMembers newObject,StaffMembers oldObject,String operation);
}
