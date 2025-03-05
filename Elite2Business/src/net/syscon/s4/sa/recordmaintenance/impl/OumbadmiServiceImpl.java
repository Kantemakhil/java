package net.syscon.s4.sa.recordmaintenance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globaloffenderrecords.OsiosearService;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.sa.admin.beans.VBookAdmin;
import net.syscon.s4.sa.admin.beans.VBookAdminCommitBean;
import net.syscon.s4.sa.recordmaintenance.OumbadmiRepository;
import net.syscon.s4.sa.recordmaintenance.OumbadmiService;

/**
 * Class OumbadmiServiceImpl
 */
@Service
public class OumbadmiServiceImpl extends BaseBusiness implements OumbadmiService {

	@Autowired
	private OumbadmiRepository oumbadmiRepository;
	
	@Autowired
	private OsiosearService osiosearServiceImpl;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> CgfkchkOffContactsOffB2(AgencyLocations paramBean) {
		return oumbadmiRepository.cgfkchkOffContactsOffB2(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VBookAdmin> vBookAdmExecuteQuery(VBookAdmin searchRecord) {
		
		List<VBookAdmin> returnList=new ArrayList<VBookAdmin>();
		List<VBookAdmin> returnFinalList=new ArrayList<VBookAdmin>();
		returnList=oumbadmiRepository.vBookAdmExecuteQuery(searchRecord);
			
		returnFinalList=removeSealOffBookingAdmin(returnList,searchRecord.getCreateUserId());
		return returnFinalList;
		

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_BOOK_ADM
	 *
	 */
	@Transactional
	public Integer vBookAdmCommit(VBookAdminCommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderBookings> offContactsExecuteQuery(OffenderBookings searchRecord) {
		List<OffenderBookings> returnList=new ArrayList<OffenderBookings>();
		List<OffenderBookings> returnFinalList=new ArrayList<OffenderBookings>();
		returnList=oumbadmiRepository.offContactsExecuteQuery(searchRecord);
		returnFinalList=removeSealOffBookingContact(returnList,searchRecord.getCreateUserId());
		return returnFinalList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_CONTACTS
	 *
	 */
	@Transactional
	public Integer offContactsCommit(OffenderBookingsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderBookings objOne : commitBean.getUpdateList()) {
				objOne.setModifyUserId(commitBean.getCreateUserId());
				if ("O".equals(objOne.getBookingStatus())) {
					Integer data = oumbadmiRepository.offenderBookingsBookingStatus(objOne);
					if (data > 0) {
						liReturn = 3;
						return liReturn;
					}
				}
			}
			liReturn = oumbadmiRepository.offContactsUpdateOffenderBookings(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkOffContactsBookingStatRecordGroup() {
		return oumbadmiRepository.cgfkOffContactsBookingStatRecordGroup(); 

	}

	@Override
	public List<Object> CgwhenNewFormInstance() {
		return null;
	}

	private List<VBookAdmin> removeSealOffBookingAdmin(List<VBookAdmin> updatedList, String createUserId) {
		List<VBookAdmin> resultList = new ArrayList<VBookAdmin>();
		Integer returnValue=osiosearServiceImpl.getSystemProfileUserAccValue(createUserId);	
		if (returnValue == 0) {
			resultList = updatedList.stream().filter(object -> !object.getSealFlag().equals("Y"))
					.collect(Collectors.toList());
		} else {
			resultList.addAll(updatedList);
			}
		return resultList;
	}
	
	List<OffenderBookings> removeSealOffBookingContact(List<OffenderBookings> updatedList,String createUserId){
		List<OffenderBookings> resultList = new ArrayList<OffenderBookings>();
		Integer returnValue=osiosearServiceImpl.getSystemProfileUserAccValue(createUserId);	
		if (returnValue == 0) {
			resultList = updatedList.stream().filter(object -> !object.getSealFlag().equals("Y"))
					.collect(Collectors.toList());
		} else {
			resultList.addAll(updatedList);
			}
		return resultList;
	}
}