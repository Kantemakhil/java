package net.syscon.s4.inst.institutionalactivities.impl;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.institutionalactivities.OidschacRepository;
import net.syscon.s4.inst.institutionalactivities.OidschacService;
import net.syscon.s4.inst.institutionalactivities.beans.VSchdPrisonActivities;
/**
 * Class OidschacServiceImpl */
@Service
public class OidschacServiceImpl extends BaseBusiness implements OidschacService{

@Autowired
private OidschacRepository oidschacRepository;

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<VSchdPrisonActivities> schActExecuteQuery(final VSchdPrisonActivities searchRecord)  {

		return oidschacRepository.schActExecuteQuery(searchRecord);

}




}