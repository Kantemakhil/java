package net.syscon.s4.inst.visitsmanagement.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.booking.beans.PersonIdentifiers;
import net.syscon.s4.inst.booking.beans.PersonIdentifiersCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;
import net.syscon.s4.inst.visitsmanagement.OcucnperRepository;
import net.syscon.s4.inst.visitsmanagement.OcucnperService;
import net.syscon.s4.inst.visitsmanagement.OsipsearService;

/**
 * Class OcucnperServiceImpl
 */
@Service
public class OcucnperServiceImpl extends BaseBusiness implements OcucnperService {

	@Autowired
	private OcucnperRepository ocucnperRepository;
	
	@Autowired
	private OsipsearService osipsearservice;

	/**
	 * Creates new OcucnperServiceImpl class Object
	 */
	public OcucnperServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Object personsPreInsert() {
		final Object object = ocucnperRepository.personsPreInsert();
		return object;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<Persons> personsExecuteQuery(final Persons searchRecord) {
		return ocucnperRepository.personsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPERSONS
	 *
	 * @
	 */
	@Transactional
	public Integer personsCommit(final PersonsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final Persons obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setPersonId(ocucnperRepository.personsPreInsert());
			}
			liReturn = ocucnperRepository.personsInsertPersons(commitBean.getInsertList());
		}
		
		if(commitBean.getInsertList()!=null && commitBean.getInsertList().size() == 1) {
			if("true".equalsIgnoreCase(commitBean.getInsertList().get(0).getPinValue())) {
				PersonIdentifiersCommitBean CommitBean= new PersonIdentifiersCommitBean();
				PersonIdentifiers bean = new PersonIdentifiers();
				List<PersonIdentifiers> list = new ArrayList<PersonIdentifiers>();
				bean.setPersonId(commitBean.getInsertList().get(0).getPersonId());
				bean.setIdentifierType(commitBean.getInsertList().get(0).getpIdentifierType());
				bean.setIdentifier(commitBean.getInsertList().get(0).getpIdentifierValue());
				bean.setCreateUserId(commitBean.getCreateUserId());
				list.add(bean);
				if(!"".equals(commitBean.getInsertList().get(0).getPninValue())) {
					PersonIdentifiers pninObj = new PersonIdentifiers();
					pninObj.setPersonId(commitBean.getInsertList().get(0).getPersonId());
					pninObj.setIdentifier(commitBean.getInsertList().get(0).getPninValue());
					pninObj.setIdentifierType("PNIN");
					pninObj.setCreateUserId(commitBean.getCreateUserId());
					list.add(pninObj);
				}
				CommitBean.setInsertList(list);
				CommitBean.setCreateUserId(commitBean.getCreateUserId());
				osipsearservice.perIdentCommit(CommitBean);
				list.clear();
				commitBean.getInsertList().clear();
			}
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		return ocucnperRepository.rgSexCodeRecordGroup();

	}

}