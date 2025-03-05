package net.syscon.s4.inst.movementexternal.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderEscape;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.movementexternal.OmuerrcoRepository;
import net.syscon.s4.inst.movementexternal.OmuerrcoService;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapes;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapesCommitBean;
import net.syscon.s4.triggers.OffenderEscapesT1Service;

/**
 * Class OmuerrcoServiceImpl
 */
@Service
public class OmuerrcoServiceImpl extends BaseBusiness implements OmuerrcoService {

	@Autowired
	private EliteDateService dateService;
	@Autowired
	private OmuerrcoRepository omuerrcoRepo;
	
	@Autowired
	private OffenderEscapesT1Service offenderEscapesT1Service;

	/**
	 * Creates new OmuerrcoServiceImpl class Object
	 */
	public OmuerrcoServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public Object offEscPreInsert() {
		return omuerrcoRepo.offEscPreInsert();
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public String cgfkchkOffEscOffEscRef(final ReferenceCodes paramBean) {
		return omuerrcoRepo.cgfkchkOffEscOffEscRef(paramBean.getCode());
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<Object> cgwhenNewFormInstance(final SysDual paramBean) {
		return omuerrcoRepo.cgwhenNewFormInstance(paramBean);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderEscapes> offEscExecuteQuery(final OffenderEscapes searchRecord) {
		return omuerrcoRepo.offEscExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 */
	@Transactional
	public Integer offEscCommit(final OffenderEscapesCommitBean commitBean) {
		int liReturn = 0;

		List<OffenderEscapes> offEscInsert = new ArrayList<>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final OffenderEscapes offEsc : commitBean.getInsertList()) {
				offEsc.setCreateUserId(commitBean.getCreateUserId());
				offEscInsert = new ArrayList<>();
				final Integer escapeId = omuerrcoRepo.offEscPreInsert();
				offEsc.setEscapeId(escapeId);
				offEsc.setModifyDatetime(dateService.getDBTime());
				final String strAdjFlag = offEsc.getAdjustSentenceFlag();
				if (strAdjFlag != null && "true".equals(strAdjFlag)) {
					offEsc.setAdjustSentenceFlag("Y");
				} else {
					offEsc.setAdjustSentenceFlag("N");
				}
				final String strCmpFlag = offEsc.getInCompanyFlag();
				if (strCmpFlag != null && "true".equals(strCmpFlag)) {
					offEsc.setInCompanyFlag("Y");
				} else {
					offEsc.setInCompanyFlag("N");
				}
				offEscInsert.add(offEsc);
				OffenderEscape obj = new OffenderEscape();
				obj.setEscapeDate(offEsc.getEscapeDate());
				obj.setEscapeTime(offEsc.getEscapeTime());
				 offenderEscapesT1Service.offenderEscapesT1(obj);
				liReturn = omuerrcoRepo.offEscInsertOffenderEscapes(offEscInsert);
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {

			for (final OffenderEscapes offEsc : commitBean.getUpdateList()) {
				offEsc.setModifyUserId(commitBean.getCreateUserId());
				final String strAdjFlag = offEsc.getAdjustSentenceFlag();
				if (strAdjFlag != null && "true".equals(strAdjFlag)) {
					offEsc.setAdjustSentenceFlag("Y");
				} else {
					offEsc.setAdjustSentenceFlag("N");
				}
				final String strCmpFlag = offEsc.getInCompanyFlag();
				if (strCmpFlag != null && "true".equals(strCmpFlag)) {
					offEsc.setInCompanyFlag("Y");
				} else {
					offEsc.setInCompanyFlag("N");
				}
				OffenderEscape obj = new OffenderEscape();
				obj.setEscapeDate(offEsc.getEscapeDate());
				obj.setEscapeTime(offEsc.getEscapeTime());
				offenderEscapesT1Service.offenderEscapesT1(obj);
			}
			liReturn = omuerrcoRepo.offEscUpdateOffenderEscapes(commitBean.getUpdateList());
		}

		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkOffEscSecurityBreachCRecordGroup() {
		return omuerrcoRepo.cgfkOffEscSecurityBreachCRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> cgfkOffEscArrestAgyCodeRecordGroup() {
		return omuerrcoRepo.cgfkOffEscArrestAgyCodeRecordGroup();

	}

}