package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.inst.demographicsbiometrics.OcdccontRepository;
import net.syscon.s4.inst.demographicsbiometrics.OcdccontService;
import net.syscon.s4.triggers.PhonesT1Service;
import net.syscon.s4.triggers.PhonesT2Service;

/**
 * Class OcdccontServiceImpl
 * 
 */
@Service
public class OcdccontServiceImpl extends BaseBusiness implements OcdccontService {

	@Autowired
	private OcdccontRepository ocdccontRepository;

	@Autowired
	private PhonesT1Service phonesT1Service;

	@Autowired
	private PhonesT2Service phonesT2Service;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdccontServiceImpl.class.getName());

	/**
	 * Creates new OcdccontServiceImpl class Object
	 */
	public OcdccontServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Object phonesPreInsert() {
		Object object;
		object = ocdccontRepository.phonesPreInsert();
		return object;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<Phones> phonesExecuteQuery(final Phones searchRecord) {
		return ocdccontRepository.phonesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPHONES
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer phonesCommit(final PhonesCommitBean commitBean) {
		int liReturn = 0;
		Phones oldBean = new Phones();
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				for (Phones bean : commitBean.getInsertList()) {

					bean.setCreateUserId(commitBean.getCreateUserId());
					Long ownerSeq = null;
					if (bean.getOwnerSeq() == null) {
						ownerSeq = null;
					} else {
						ownerSeq = bean.getOwnerSeq().longValue();
					}
					phonesT1Service.phonesT1Trigger(bean.getOwnerClass(), bean.getOwnerId().longValue(),
							ownerSeq != null ? ownerSeq : null, bean.getOwnerCode());

				}
				liReturn = ocdccontRepository.phonesInsertPhones(commitBean.getInsertList());
				for (Phones object : commitBean.getInsertList()) {
					oldBean = new Phones();
					phonesT2Service.phonesT2Trigger(oldBean, object);
				}
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				for (Phones bean : commitBean.getUpdateList()) {
					bean.setModifyUserId(commitBean.getCreateUserId());
					phonesT1Service.phonesT1Trigger(bean.getOwnerClass(), bean.getOwnerId().longValue(),
							bean.getOwnerSeq().longValue(), bean.getOwnerCode());
				}
				liReturn = ocdccontRepository.phonesUpdatePhones(commitBean.getUpdateList());
				for (Phones object : commitBean.getUpdateList()) {
					oldBean = new Phones();
					phonesT2Service.phonesT2Trigger(oldBean, object);
				}
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = ocdccontRepository.phonesDeletePhones(commitBean.getDeleteList());
				for (Phones object : commitBean.getUpdateList()) {
					oldBean = new Phones();
					phonesT2Service.phonesT2Trigger(oldBean, object);
				}
			}
		} catch (Exception e) {
			logger.error("phonesCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgPhoneTypeRecordGroup() {
		return ocdccontRepository.rgPhoneTypeRecordGroup();

	}

}