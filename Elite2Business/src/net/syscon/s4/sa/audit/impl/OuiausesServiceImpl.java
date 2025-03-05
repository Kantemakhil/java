package net.syscon.s4.sa.audit.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.sa.audit.OuiausesRepository;
import net.syscon.s4.sa.audit.OuiausesService;
import net.syscon.s4.sa.audit.SysTagAuditFormGetsessiondetail;
/**
 * Class OuiausesServiceImpl */
@Service
public class OuiausesServiceImpl extends BaseBusiness implements OuiausesService{

@Autowired
private OuiausesRepository ouiausesRepository;
	

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<SysTagAuditFormGetsessiondetail> getSessionDetailExecuteQuery(final SysTagAuditFormGetsessiondetail searchRecord)  {
	List<SysTagAuditFormGetsessiondetail> finalsessionList = new ArrayList<SysTagAuditFormGetsessiondetail>();
	List<SysTagAuditFormGetsessiondetail> dateAndTimeList = new ArrayList<SysTagAuditFormGetsessiondetail>();
	finalsessionList =  ouiausesRepository.getSessionDetailExecuteQuery(searchRecord);
	dateAndTimeList = ouiausesRepository.getDateAndTime(searchRecord.getSessionid());
	
	for(SysTagAuditFormGetsessiondetail dateTimeObj : dateAndTimeList) {
		for(SysTagAuditFormGetsessiondetail finalObj : finalsessionList) {
			finalObj.setDate(dateTimeObj.getDate());
			finalObj.setTime(dateTimeObj.getTime());
		}
	}
	
	
	return finalsessionList;
}


}