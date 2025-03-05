package net.syscon.s4.inst.institutionalactivities.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.institutionalactivities.OidacselRepository;
import net.syscon.s4.inst.institutionalactivities.OidacselService;
import net.syscon.s4.inst.institutionalactivities.beans.VPrisonActivities;

@Service
public class OidacselServiceImpl extends BaseBusiness implements OidacselService {

	@Autowired
	private OidacselRepository oidacselRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VPrisonActivities> scheduledActivitiesExecuteQuery(final VPrisonActivities searchRecord) {
		return oidacselRepository.scheduledActivitiesExecuteQuery(searchRecord);

	}
}