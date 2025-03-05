package net.syscon.s4.cm.programsservices.impl;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.OcussessRepository;
import net.syscon.s4.cm.programsservices.OcussessService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedulesCommitBean;
/**
 * Class OcussessServiceImpl */
@Service
public class OcussessServiceImpl extends BaseBusiness implements OcussessService{

@Autowired
private OcussessRepository ocussessRepository;

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<CourseSchedules> crsSchExecuteQuery(CourseSchedules searchRecord)  {
		return ocussessRepository.crsSchExecuteQuery(searchRecord);

}

/**Insert the records from database table
 *
 * @param lstCRS_SCH
 *
 * @throws SQLException
 */
@Transactional
public Integer crsSchCommit(CourseSchedulesCommitBean CommitBean)  {
		int liReturn = 0;
		return liReturn;
}

}