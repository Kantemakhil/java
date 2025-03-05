package net.syscon.s4.cm.programsservices.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.programsservices.OcusmoduRepository;
import net.syscon.s4.cm.programsservices.OcusmoduService;
import net.syscon.s4.cm.programsservices.VAcpSchedules;
import net.syscon.s4.genericservices.BaseBusiness;

@Service
public class OcusmoduServiceImpl extends BaseBusiness implements OcusmoduService {

	@Autowired
	private OcusmoduRepository ocusmoduRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VAcpSchedules> vAcpSchExecuteQuery(final VAcpSchedules searchRecord) {
		List<VAcpSchedules> returnList =  ocusmoduRepository.vAcpSchExecuteQuery(searchRecord);
		returnList.forEach(obj -> {
			obj.setWeekDay(ocusmoduRepository.getWeekDay(obj.getScheduleDate().toString()));
			if(searchRecord.getpModuleFrom() != null && searchRecord.getpModuleTo()!= null) {
				if (obj.getModuleListSeq() >= searchRecord.getpModuleFrom() && obj.getModuleListSeq() <= searchRecord.getpModuleTo()) {
					obj.setNbtSelect("Y");
				} else {
					obj.setNbtSelect("N");
				}
			}

		});
		return returnList;
	}

	

}