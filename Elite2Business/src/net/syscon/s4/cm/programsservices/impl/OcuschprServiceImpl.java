package net.syscon.s4.cm.programsservices.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.programsservices.OcuschprRepository;
import net.syscon.s4.cm.programsservices.OcuschprService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.iwp.beans.VAcpSchedules;

/**
 * Class OcuschprServiceImpl */
@Service
public class OcuschprServiceImpl extends BaseBusiness implements OcuschprService{

@Autowired
private OcuschprRepository ocuschprRepository;

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<VAcpSchedules> vAcpSchedulesExecuteQuery(VAcpSchedules searchRecord)  {
	List<VAcpSchedules> list =new ArrayList<>();
		if(searchRecord.getCatchUpSessionFlag() !=null && searchRecord.getCatchUpSessionFlag().equals("true")) {
			searchRecord.setCatchUpSessionFlag("Y");
		} else {
			searchRecord.setCatchUpSessionFlag("N");
		}
		list=ocuschprRepository.vAcpSchedulesExecuteQuery(searchRecord);
		list.forEach(bo->{
			bo.setProgramDesc(bo.getProgramInstanceDesc());
		});
		return list;

}


}