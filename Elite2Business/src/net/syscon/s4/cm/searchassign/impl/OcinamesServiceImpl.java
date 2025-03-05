package net.syscon.s4.cm.searchassign.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.searchassign.OcinamesRepository;
import net.syscon.s4.cm.searchassign.OcinamesService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globaloffenderrecords.OsiosearService;
import net.syscon.s4.im.beans.VPimsNameSearch;

/**
 * Class OcinamesServiceImpl
 */
@Service
public class OcinamesServiceImpl extends BaseBusiness implements OcinamesService {

	@Autowired
	private OcinamesRepository ocinamesRepository;
	
	@Autowired
	private OsiosearService osiosearServiceImpl;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<VPimsNameSearch> vNSearchExecuteQuery(final VPimsNameSearch searchRecord) {
		List<VPimsNameSearch> returnList=new ArrayList<VPimsNameSearch>();
		returnList=ocinamesRepository.vNSearchExecuteQuery(searchRecord);
		
		returnList = removeSealOffNameSearchHeader(returnList, searchRecord.getCreateUserId());
		return returnList;
	}

	private List<VPimsNameSearch> removeSealOffNameSearchHeader(List<VPimsNameSearch> updatedList, String userId) {
		List<VPimsNameSearch> resultList = new ArrayList<VPimsNameSearch>();
		Integer returnValue=osiosearServiceImpl.getSystemProfileUserAccValue(userId);	
		if (returnValue == 0) {
			resultList = updatedList.stream().filter(object -> !object.getSealFlag().equals("Y"))
					.collect(Collectors.toList());
		} else {
			resultList.addAll(updatedList);
			}
		return resultList;
	}
}