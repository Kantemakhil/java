package net.syscon.s4.inst.property.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Statutes;
import net.syscon.s4.inst.property.OidrpitmRepository;
import net.syscon.s4.inst.property.OidrpitmService;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.inst.property.bean.OffenderPptyItemsCommitBean;
import net.syscon.s4.triggers.OffenderPptyItemsT1Service;

/**
 * Class OidrpitmServiceImpl
 * 
 * 
 */
@Service
public class OidrpitmServiceImpl extends BaseBusiness implements OidrpitmService {

	@Autowired
	private OidrpitmRepository oidrpitmRepository;

	@Autowired
	private EliteDateService dateService;
	@Autowired
	private OffenderPptyItemsT1Service offenderPptyItemsT1Service;

	/**
	 * Creates new OidrpitmServiceImpl class Object
	 */
	public OidrpitmServiceImpl() {
		super();
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderPptyItems> vPheadOnCheckDeleteMaster(final OffenderPptyItems paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> offPiPostQuery(final ReferenceCodes paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> cgfkchkOffPiOffPiRef(final ReferenceCodes paramBean) {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderPptyItems> offPiExecuteQuery(final OffenderPptyItems searchRecord) {
		List<OffenderPptyItems> returnList = new ArrayList<OffenderPptyItems>();

		returnList = oidrpitmRepository.offPiExecuteQuery(searchRecord);
		for (final OffenderPptyItems offpptm : returnList) {
			if (offpptm.getPropertyType() != null) {
				final String description = oidrpitmRepository.getDescrption(offpptm.getPropertyType());
				offpptm.setPptyDescription(description);
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_PI
	 *
	 * @
	 */
	@Transactional
	public Integer offPiCommit(final OffenderPptyItemsCommitBean commitBean) {
		int liReturn = 0;
		Integer offBookid = null;
		Integer partySeq = null;
		OffenderPptyItems old = new OffenderPptyItems();
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {

			for (OffenderPptyItems obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			/*
			 * for (final OffenderPptyItems offenderPpty : commitBean.getInsertList()) {
			 * offBookid = offenderPpty.getOffenderBookId(); } partySeq =
			 * oidrpitmRepository.offEmPreInsertc(offBookid); for (final OffenderPptyItems
			 * offenderPpty : commitBean.getInsertList()) {
			 * offenderPpty.setPropertyItemSeq(partySeq); partySeq = partySeq + 1; }
			 * liReturn =
			 * oidrpitmRepository.offpiInsertOffenderPptyItems(commitBean.getInsertList());
			 */
			List<OffenderPptyItems> recordSavingObject = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final OffenderPptyItems offenderPropertyItemObj = commitBean.getInsertList().get(i);
					offBookid = offenderPropertyItemObj.getOffenderBookId();
					partySeq = oidrpitmRepository.offEmPreInsertc(offBookid);
					offenderPropertyItemObj.setPropertyItemSeq(partySeq);
					recordSavingObject.add(offenderPropertyItemObj);
					liReturn = oidrpitmRepository.offpiInsertOffenderPptyItems(recordSavingObject);
					String operation = "INSERTING";
					// for (OffenderPptyItems newbean : commitBean.getInsertList()) {
					offenderPptyItemsT1Service.offenderPptyItemsT1(old, commitBean.getInsertList().get(i), operation);
					// }
				} 
			}
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final OffenderPptyItems offenderPpty : commitBean.getUpdateList()) {
				if (offenderPpty.getCreateDatetime() == null) {
					offenderPpty.setCreateDatetime(dateService.getDBTime());
				}
				if (offenderPpty.getModifyDatetime() == null) {
					offenderPpty.setModifyDatetime(dateService.getDBTime());
				}
				offenderPpty.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidrpitmRepository.offPiUpdateOffenderPptyItems(commitBean.getUpdateList());
			String operation = "UPDATING";
			for (OffenderPptyItems newbean : commitBean.getUpdateList()) {
				offenderPptyItemsT1Service.offenderPptyItemsT1(old, newbean, operation);
			}
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidrpitmRepository.offPiDeleteOffenderPptyItems(commitBean.getDeleteList());
			String operation = "DELETING";
			for (OffenderPptyItems newbean : commitBean.getDeleteList()) {
				offenderPptyItemsT1Service.offenderPptyItemsT1(newbean, old, operation);
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return oidrpitmRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * @
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgColorRecordGroup() {
		return oidrpitmRepository.rgColorRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgCondnRecordGroup() {
		return oidrpitmRepository.rgCondnRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> cgfkOffPiReceivedFromRecordGroup() {
		List<ReferenceCodes> listref = oidrpitmRepository.cgfkOffPiReceivedFromRecordGroup();
		for (final ReferenceCodes referenceCodes : listref) {

		}
		return listref;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> cgfkOffPiPropertyTypeRecordGroup() {
		final List<ReferenceCodes> listRef = oidrpitmRepository.cgfkOffPiPropertyTypeRecordGroup();
		return listRef;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> offRecForm() {
		return oidrpitmRepository.offRecForm();

	}

}