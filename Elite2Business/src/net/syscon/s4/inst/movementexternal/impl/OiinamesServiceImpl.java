package net.syscon.s4.inst.movementexternal.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.VNameSearch;
import net.syscon.s4.common.beans.VNameSearchCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globaloffenderrecords.OsiosearService;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.movementexternal.OiinamesRepository;
import net.syscon.s4.inst.movementexternal.OiinamesService;

/**
 * Class OiinamesServiceImpl
 * 
 */
@Service
public class OiinamesServiceImpl extends BaseBusiness implements OiinamesService {

	@Autowired
	private OiinamesRepository oiinamesRepository;
	
	@Autowired
	private OsiosearService osiosearServiceImpl;
	/**
	 * Creates new OiinamesServiceImpl class Object
	 */
	public OiinamesServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<Object> CgfdgetNameSrchDrvActive() {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<Object> CgwhenNewFormInstance() {
		return null;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VNameSearch> nameSrchExecuteQuery(VNameSearch searchRecord) {
		List<VNameSearch> listnames = new ArrayList<VNameSearch>();
		listnames = oiinamesRepository.nameSrchExecuteQuery(searchRecord);
		listnames = removeSealOffNameSearchHeader(listnames,searchRecord.getCreateUserId());
		return listnames;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstNAME_SRCH
	 *
	 * @
	 */
	@Transactional
	public Integer nameSrchCommit(VNameSearchCommitBean CommitBean) {
		if (CommitBean.getInsertList() != null && CommitBean.getInsertList().size() > 0) {
		}
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(SystemProfiles searchRecord) {
		return oiinamesRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * @
	 */
	@Transactional
	public Integer sysPflCommit(SystemProfilesCommitBean CommitBean) {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<String> findAgyLocIdList(String userName) {
		return oiinamesRepository.findAgyLocIdList(userName);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<String> findLivingUnitsList(String userName) {
		return oiinamesRepository.findLivingUnitsList(userName);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<String> findActiveFlagList() {
		return oiinamesRepository.findActiveFlagList();
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param sysDual
	 *
	 * @throws SQLException
	 */
	public List<Dual> cgwhenNewFormInstance(final SysDual sysDual) {
		return oiinamesRepository.cgwhenNewFormInstance(sysDual);
	}
	
	public List<Caseloads> findAgyLocIdListLov() {
		return oiinamesRepository.findAgyLocIdListLov();
	}
	
	private List<VNameSearch> removeSealOffNameSearchHeader(List<VNameSearch> updatedList, String userId) {
		List<VNameSearch> resultList = new ArrayList<VNameSearch>();
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
