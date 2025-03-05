package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.inst.demographicsbiometrics.OcdgnumbRepository;
import net.syscon.s4.inst.demographicsbiometrics.OcdgnumbService;
import net.syscon.s4.triggers.InternetAddressesT1Service;
import net.syscon.s4.triggers.InternetAddressesT2Service;
import net.syscon.s4.triggers.PhonesT1Service;
import net.syscon.s4.triggers.PhonesT2Service;

/**
 * Class OcdgnumbServiceImpl
 * 
 * 
 */
@Service
public class OcdgnumbServiceImpl extends BaseBusiness implements OcdgnumbService {

	@Autowired
	private OcdgnumbRepository ocdgnumbRepository;
	@Autowired
	private PhonesT1Service phonesT1Service;

	@Autowired
	private PhonesT2Service phonesT2Service;

	@Autowired
	private InternetAddressesT1Service internetAddressesT1Service;

	@Autowired
	private InternetAddressesT2Service internetAddressesT2Service;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdgnumbServiceImpl.class.getName());

	/**
	 * Creates new OcdgnumbServiceImpl class Object
	 */
	public OcdgnumbServiceImpl() {
		super();
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Object phonesPreInsert() {
		Object object = null;
		object = ocdgnumbRepository.phonesPreInsertPreInsert();
		return object;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Object internetAddrPreInsert() {
		Object object = null;
		object = ocdgnumbRepository.internetAddrPreInsert();
		return object;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<Phones> phonesExecuteQuery(final Phones searchRecord) {
		return ocdgnumbRepository.phonesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPHONES
	 *
	 * @
	 */
	@Transactional
	public Integer phonesCommit(final PhonesCommitBean commitBean) {
		int liReturn = 0;
		Phones oldBean = new Phones();
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				for (Phones bean : commitBean.getInsertList()) {
					bean.setCreateUserId(commitBean.getCreateUserId());
					if (bean.getOwnerSeq() == null) {
						bean.setOwnerSeq(BigDecimal.ONE);
					} else {
						 bean.getOwnerSeq().longValue();
					}
					if(bean.getOwnerId() == null) {
						bean.setOwnerId(BigDecimal.ONE);
					}
					phonesT1Service.phonesT1Trigger(bean.getOwnerClass(), bean.getOwnerId().longValue(),
							bean.getOwnerSeq().longValue(), bean.getOwnerCode());

				}
				liReturn = ocdgnumbRepository.phonesInsertPhones(commitBean.getInsertList());
				for (Phones object : commitBean.getInsertList()) {
					oldBean = new Phones();
					phonesT2Service.phonesT2Trigger(oldBean, object);
				}
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				for (Phones bean : commitBean.getUpdateList()) {
					Long ownerSeq = null;
					if (bean.getOwnerSeq() == null) {
						bean.setOwnerSeq(BigDecimal.ONE);
					} else {
						ownerSeq = bean.getOwnerSeq().longValue();
					}
					bean.setModifyUserId(commitBean.getCreateUserId());
					phonesT1Service.phonesT1Trigger(bean.getOwnerClass(), bean.getOwnerId().longValue(),
							ownerSeq != null ? ownerSeq : null, bean.getOwnerCode());
				}
				liReturn = ocdgnumbRepository.phonesUpdatePhones(commitBean.getUpdateList());
				for (Phones object : commitBean.getUpdateList()) {
					oldBean = ocdgnumbRepository.getOldDataOfPhones(object.getPhoneId());
					phonesT2Service.phonesT2Trigger(oldBean, object);
				}
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = ocdgnumbRepository.phonesDeletePhones(commitBean.getDeleteList());
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
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<InternetAddresses> internetAddrExecuteQuery(final InternetAddresses searchRecord) {
		return ocdgnumbRepository.internetAddrExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstINTERNET_ADDR
	 *
	 * @
	 */
	@Transactional
	public Integer internetAddrCommit(final InternetAddressesCommitBean commitBean) {
		int liReturn = 0;
		Long ownerId = 0L;
		Long ownerSeq = 0L;

		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				for (InternetAddresses bean : commitBean.getInsertList()) {
					bean.setCreateUserId(commitBean.getCreateUserId());
					if (bean.getOwnerId() != null) {
						ownerId = bean.getOwnerId().longValue();
					}
					if (bean.getOwnerSeq() != null) {
						ownerSeq = bean.getOwnerSeq().longValue();
					}
					internetAddressesT1Service.internetAddressesT1Trigger(bean.getOwnerClass(), ownerId, ownerSeq,
							bean.getOwnerCode());
				}
				liReturn = ocdgnumbRepository.internetAddrInsertInternetAddresses(commitBean.getInsertList());
				for (InternetAddresses bean : commitBean.getInsertList()) {
					internetAddressesT2Service.internetAddressesT2Trigger(bean);
				}
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				for (InternetAddresses bean : commitBean.getUpdateList()) {
					bean.setModifyUserId(commitBean.getCreateUserId());
					internetAddressesT1Service.internetAddressesT1Trigger(bean.getOwnerClass(),
							bean.getOwnerId() != null ? bean.getOwnerId().longValue(): null, 
							bean.getOwnerSeq() != null ? bean.getOwnerSeq().longValue(): null, 
							bean.getOwnerCode());
				}
				liReturn = ocdgnumbRepository.internetAddrUpdateInternetAddresses(commitBean.getUpdateList());
				for (InternetAddresses bean : commitBean.getUpdateList()) {

					internetAddressesT2Service.internetAddressesT2Trigger(bean);
				}
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = ocdgnumbRepository.internetAddrDeleteInternetAddresses(commitBean.getDeleteList());
				for (InternetAddresses bean : commitBean.getDeleteList()) {
					internetAddressesT2Service.internetAddressesT2Trigger(bean);
				}
			}
		} catch (Exception e) {
			logger.error("internetAddrCommit", e);
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgPhoneTypeRecordGroup() {
		return ocdgnumbRepository.rgPhoneTypeRecordGroup();

	}
	
	@Override
	public List<String> gettingEmailDomains() {
		return ocdgnumbRepository.gettingEmailDomains();
	}

}