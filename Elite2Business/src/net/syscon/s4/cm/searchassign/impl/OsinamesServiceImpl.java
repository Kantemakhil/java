package net.syscon.s4.cm.searchassign.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.searchassign.OsinamesRepository;
import net.syscon.s4.cm.searchassign.OsinamesService;
import net.syscon.s4.common.beans.VNameSearch2;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globaloffenderrecords.OsiosearService;

/**
 * class OsinamesServiceImpl
 */
@Service
public class OsinamesServiceImpl extends BaseBusiness implements OsinamesService {

	@Autowired
	private OsinamesRepository osinamesRepository;
	
	@Autowired
	private OsiosearService osiosearServiceImpl;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VNameSearch2> nameSrchExecuteQuery(final VNameSearch2 searchRecord) {
			  String str = null;
		if (searchRecord.getOffenderIdDisplay() != null) {
			for (int i = searchRecord.getOffenderIdDisplay().length(); i < 10; i++) {
				if (str == null) {
					str = searchRecord.getOffenderIdDisplay();
				}
				str = 0 + str;
			}
			if (str != null) {
				searchRecord.setOffenderIdDisplay(str);
			}
		}
			   List<VNameSearch2> returnList = osinamesRepository.nameSrchExecuteQuery(searchRecord);
			  
			  
				if (returnList != null && returnList.size() > 0) {
					for (VNameSearch2 vNameSearch2 : returnList) {
						vNameSearch2.setDescription(osinamesRepository.getDescription(vNameSearch2.getAgyLocId()));
					}
				}
				returnList = removeSealOffNameSearchActiveInactive(returnList, searchRecord.getCreateUserId());
				return returnList;
				//return returnList;
			}
	
	
	private List<VNameSearch2> removeSealOffNameSearchActiveInactive(List<VNameSearch2> updatedList, String userId) {
		List<VNameSearch2> resultList = new ArrayList<VNameSearch2>();
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