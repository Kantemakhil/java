package net.syscon.s4.sa.audit.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.sa.audit.OiusmselRepository;
import net.syscon.s4.sa.audit.OiusmselService;

/**
 * Class OiusmselServiceImpl
 */
@Service
public class OiusmselServiceImpl extends BaseBusiness implements OiusmselService {

	@Autowired
	private OiusmselRepository oiusmselRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<StaffMembers> staffMembersExecuteQuery(final StaffMembers searchRecord) {
		return oiusmselRepository.staffMembersExecuteQuery(searchRecord);

	}

}