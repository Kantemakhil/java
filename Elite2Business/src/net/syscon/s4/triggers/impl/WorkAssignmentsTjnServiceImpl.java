package net.syscon.s4.triggers.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.legals.legalorders.WorkAssignments;
import net.syscon.s4.triggers.WorkAssignmentsTjnRepository;
import net.syscon.s4.triggers.WorkAssignmentsTjnService;

@Service
public class WorkAssignmentsTjnServiceImpl implements WorkAssignmentsTjnService {
	private static Logger logger = LogManager.getLogger(WorkAssignmentsTjnServiceImpl.class.getName());
	@Autowired
	WorkAssignmentsTjnRepository workAssignmentsTjnRepository;

	@Override
	public Integer workAssignmentsTjnTgr(final List<WorkAssignments> workAssignmentsList, final String sqlOperation) {
		Integer result = 0;
		if (!workAssignmentsList.isEmpty()) {
			try {
				for (final WorkAssignments newObj : workAssignmentsList) {
					final WorkAssignments old = workAssignmentsTjnRepository.getWorkAssignments(newObj);
					if ("INSERTING".equalsIgnoreCase(sqlOperation)) {
						result = workAssignmentsTjnRepository.insert(newObj);
					} else if ("UPDATING".equalsIgnoreCase(sqlOperation)) {
						result = workAssignmentsTjnRepository.insert(old);
					} else if ("DELETING".equalsIgnoreCase(sqlOperation)) {
						result = workAssignmentsTjnRepository.insert(old);
					}

				}
			} catch (final Exception e) {
				result = 0;
				logger.error("workAssignmentsTjnTgr", e);
			}
		}
		return result;
	}

}
