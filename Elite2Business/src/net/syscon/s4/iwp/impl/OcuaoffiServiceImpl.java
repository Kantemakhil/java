package net.syscon.s4.iwp.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.casemanagement.beans.StaffMembersV2;
import net.syscon.s4.iwp.OcuaoffiRepository;
import net.syscon.s4.iwp.OcuaoffiService;

@Service
public class OcuaoffiServiceImpl extends BaseBusiness implements OcuaoffiService {

	@Autowired
	private OcuaoffiRepository ocuaoffiRepository;

	public List<StaffMembersV2> addStaffExecuteQuery(final StaffMembersV2 searchRecord) {
		return ocuaoffiRepository.addStaffExecuteQuery(searchRecord);

	}

}