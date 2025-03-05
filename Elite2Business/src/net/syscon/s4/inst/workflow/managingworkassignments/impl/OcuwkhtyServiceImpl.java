package net.syscon.s4.inst.workflow.managingworkassignments.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.workflow.managingworkassignments.OcuwkhtyRepository;
import net.syscon.s4.inst.workflow.managingworkassignments.OcuwkhtyService;
import net.syscon.s4.inst.workflow.managingworkassignments.VWorkAssignmentHistory;

/**
 * Class OcuwkhtyServiceImpl
 */
@Service
public class OcuwkhtyServiceImpl extends BaseBusiness implements OcuwkhtyService {

	@Autowired
	private OcuwkhtyRepository ocuwkhtyRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VWorkAssignmentHistory> vWorkAssignmentHistoryExecuteQuery(final VWorkAssignmentHistory searchRecord) {
		return ocuwkhtyRepository.vWorkAssignmentHistoryExecuteQuery(searchRecord);

	}

}