package net.syscon.s4.globalrbac.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.globalconfiguration.OumucreatService;
import net.syscon.s4.globalrbac.OumpersoRepository;
import net.syscon.s4.globalrbac.OumpersoService;
import net.syscon.s4.im.beans.ImagesCommitBean;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.im.beans.StaffMembersCommitBean;
import net.syscon.s4.im.beans.TagImages;
import net.syscon.s4.im.beans.VStaffAddresses;
import net.syscon.s4.im.beans.VStaffAddressesCommitBean;
import net.syscon.s4.triggers.InternetAddressesT1Service;
import net.syscon.s4.triggers.InternetAddressesT2Service;
import net.syscon.s4.triggers.PhonesT1Service;
import net.syscon.s4.triggers.PhonesT2Service;

@Service
public class OumpersoServiceImpl extends BaseBusiness implements OumpersoService {

	private static final Logger lgger = LogManager.getLogger(OumpersoServiceImpl.class.getName());
	@Autowired
	private OumpersoRepository oumpRepository;
	@Autowired
	private OumusersServiceImpl oumusersService;
	@Autowired
	private OumucreatService oumucreatService;
	@Autowired
	private PhonesT1Service phonesT1Service;
	@Autowired
	private PhonesT2Service phonesT2Service;
	@Autowired
	private InternetAddressesT1Service internetAddressesT1Service;
	@Autowired
	private InternetAddressesT2Service internetAddressesT2Service;
	
	

	public static final String VALID = "valid";

	/**
	 * Creates new OumpersoServiceImpl class Object
	 */
	public OumpersoServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<TagImages> staffOnCheckDeleteMaster(final TagImages paramBean) {
		return (List<TagImages>) oumpRepository.staffOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Object staffPreInsert() {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<Phones> vStfAddrOnCheckDeleteMaster(final Phones paramBean) {
		return (List<Phones>) oumpRepository.vStfAddrOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	public Object addrPhonesPreInsert() {
		return oumpRepository.addrPhonesPreInsert();
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 */
	public Object stfPhonesPreInsert() {
		return oumpRepository.stfPhonesPreInsert();
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 *
	 */
	public Object emailAddrPreInsert() {
		return oumpRepository.emailAddrPreInsert();
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 *
	 */
	public List<StaffMembers> staffExecuteQuery(final StaffMembers searchRecord) {
		return oumpRepository.staffExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *            StaffMembersCommitBean
	 * @return Integer
	 *
	 */
	@Transactional
	public String staffCommit(final StaffMembersCommitBean commitBean) {
		String liReturn = null;
		Integer staffSequence = null;
		Boolean checkFlag = true;
		Object objPreInsert = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final StaffMembers staffObj : commitBean.getInsertList()) {
				if (staffObj.getUserId() != null) {
					String returnObj = oumusersService.validateuserId(staffObj);
					if (!VALID.equals(returnObj)) {
						return returnObj;
					}
				}
				if(staffObj.getMailId() != null)
				{
					try{
						Integer result=oumucreatService.verifyEmailId(staffObj.getMailId());
						if(result > 0) {
							return "DUP_EMAIL";
						}
					} catch(Exception ex) {
						return "DUP_EMAIL";
					}
				}
				if (checkFlag) {
					checkFlag = false;
					objPreInsert = oumpRepository.staffPreInsert();
					staffSequence = Integer.parseInt(objPreInsert.toString());

				}
				staffObj.setStaffId(staffSequence);
				staffSequence++;
				staffObj.setCreateUserId(commitBean.getCreateUserId());
			}

			liReturn = oumpRepository.staffInsertStaffMembers(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (StaffMembers bean : commitBean.getUpdateList()) {
				String returnObj = oumusersService.validateuserId(bean);
				if (!VALID.equals(returnObj)) {
					return returnObj;
				}
				if(bean.getMailId() != null)
				{
					try{
						String mailId = checkMailId(bean.getStaffId());
						if(mailId != null && !mailId.equals("")) {
							/*
							 * if(bean.getAdUser() != null && bean.getAdUser().equals("Y")) {
							 * if(!mailId.equals(bean.getMailId())) { return "AD_USER"; } }
							 */
							int insightUser = oumucreatService.getInsightsUserId(mailId);
							if(insightUser != 0) {
								if(!mailId.equals(bean.getMailId())) {
									return "INSIGHT_USER";
								}
							}
						}
						List<StaffMembers> result=oumucreatService.verifyEmailIdbyStaffId(bean.getMailId(), bean.getStaffId());
						if(bean.getMailId() != null && !bean.getMailId().equals("")) {
							if(result.size() > 1) {
								return "DUP_EMAIL";
							} else if(result.size() == 1 ) {
								if(!bean.getStaffId().equals(result.get(0).getStaffId())) {
									return "DUP_EMAIL";
								}
							}
						}
					} catch(Exception ex) {
						return "DUP_EMAIL";
					}
				}
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumpRepository.staffUpdateStaffMembers(commitBean.getUpdateList());
		}
		return liReturn;
	}
	
	public String checkMailId(int staffId) {
		return oumpRepository.checkMailId(staffId);
	}
	
	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 *
	 */
	public List<Images> imageExecuteQuery(final Images searchRecord) {
		return oumpRepository.imageExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstIMAGE
	 *
	 *
	 */
	@Transactional
	public Integer imageCommit(final ImagesCommitBean commitBean) {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 *
	 */
	public List<VStaffAddresses> vStfAddrExecuteQuery(final VStaffAddresses searchRecord) {
		List<VStaffAddresses> returnList= oumpRepository.vStfAddrExecuteQuery(searchRecord);
		if(returnList!=null && !returnList.isEmpty()) {
		for(VStaffAddresses address:returnList) {
              if(address.getProvStateDesc() == null) {
            		address.setProvStateDesc(address.getProvStateCode());
              }
			
              if(address.getCityName() == null) {
          		address.setCityName(address.getCityCode());
            }
		}
		}
		return returnList;

	

	

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_STF_ADDR
	 *
	 *
	 */
	@Transactional
	public Integer vStfAddrCommit(final VStaffAddressesCommitBean commitBean) {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 *
	 */
	public List<Phones> addrPhonesExecuteQuery(final Phones searchRecord) {
		return oumpRepository.addrPhonesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstADDR_PHONES
	 *
	 *
	 */
	@Transactional
	public Integer addrPhonesCommit(final PhonesCommitBean commitBean) {
		int liReturn = 0;
		Phones oldBean = new Phones();
		Long phoneSequence = null;
		Boolean checkFlag = true;
		Object objPreInsert = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final Phones phObj : commitBean.getInsertList()) {
				if (checkFlag) {
					checkFlag = false;
					objPreInsert = oumpRepository.addrPhonesPreInsert();
					phoneSequence = Long.parseLong(objPreInsert.toString());

				}
				phObj.setPhoneId(phoneSequence);
				phoneSequence++;
				phObj.setCreateUserId(commitBean.getCreateUserId());
				try {
					if(phObj.getOwnerId() == null) {
						phObj.setOwnerId(BigDecimal.ONE);
					}
					if(phObj.getOwnerSeq() == null) {
						phObj.setOwnerSeq(BigDecimal.ONE);
					}
					phonesT1Service.phonesT1Trigger(phObj.getOwnerClass(), phObj.getOwnerId().longValue(),
							phObj.getOwnerSeq().longValue(), phObj.getOwnerCode());
				} catch (CustomException e) {
					lgger.error("addrPhonesCommit", e);
				}
			}

			liReturn = oumpRepository.addrPhonesInsertPhones(commitBean.getInsertList());
			for (Phones phObj : commitBean.getInsertList()) {
				phonesT2Service.phonesT2Trigger(oldBean, phObj);
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (Phones phObj : commitBean.getUpdateList()) {
				phObj.setModifyUserId(commitBean.getCreateUserId());
				try {
					phonesT1Service.phonesT1Trigger(phObj.getOwnerClass(), phObj.getOwnerId().longValue(),
							phObj.getOwnerSeq().longValue(), phObj.getOwnerCode());
				} catch (CustomException e) {
					lgger.error("addrPhonesCommit", e);
				}
			}
			liReturn = oumpRepository.addrPhonesUpdatePhones(commitBean.getUpdateList());
			for (Phones phObj : commitBean.getUpdateList()) {
				phObj.setCreateUserId(commitBean.getCreateUserId());
				phonesT2Service.phonesT2Trigger(oldBean, phObj);
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for(Phones data:commitBean.getDeleteList()) {
				data.setModifyUserId(commitBean.getCreateUserId());
				List<Phones> list=new ArrayList<Phones>();
				Phones old=oumpRepository.getPhonesOldObject(data);
				list.add(data);
				liReturn = oumpRepository.addrPhonesDeletePhones(list);
				try {
					data.setCreateUserId(commitBean.getCreateUserId());
					phonesT2Service.phonesT2Trigger(old, data);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *            PhonesCommitBean
	 *
	 *
	 */
	@Transactional
	public Integer stfPhonesCommit(final PhonesCommitBean commitBean) {
		int liReturn = 0;
		Long phoneSequence = null;
		Boolean checkFlag = true;
		Object objPreInsert = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final Phones phObj : commitBean.getInsertList()) {
				if (checkFlag) {
					checkFlag = false;
					objPreInsert = oumpRepository.stfPhonesPreInsert();
					phoneSequence = Long.parseLong(objPreInsert.toString());
				}
				phObj.setPhoneId(phoneSequence);
				phoneSequence++;
				phObj.setCreateUserId(commitBean.getCreateUserId());
			}

			liReturn = oumpRepository.addrPhonesInsertPhones(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (Phones phObj : commitBean.getUpdateList()) {
				phObj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumpRepository.addrPhonesUpdatePhones(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (Phones phObj : commitBean.getDeleteList()) {
				phObj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumpRepository.addrPhonesDeletePhones(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 *
	 */
	public List<InternetAddresses> emailAddrExecuteQuery(final InternetAddresses searchRecord) {
		return oumpRepository.emailAddrExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstEMAIL_ADDR
	 *
	 *
	 */
	@Transactional
	public Integer emailAddrCommit(final InternetAddressesCommitBean commitBean) {
		int liReturn = 0;
		Long emailSequence = null;
		Boolean check = true;
		Object objPreInsert = null;
		try {
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final InternetAddresses emailObj : commitBean.getInsertList()) {
				if (check) {
					check = false;
					objPreInsert = oumpRepository.emailAddrPreInsert();
					emailSequence = Long.parseLong(objPreInsert.toString());
				}
				emailObj.setInternetAddressId(emailSequence);
				emailSequence++;
				emailObj.setCreateUserId(commitBean.getCreateUserId());
				if(emailObj.getOwnerId() == null) {
					emailObj.setOwnerId(BigDecimal.ONE);
				}
				if(emailObj.getOwnerSeq() == null) {
					emailObj.setOwnerSeq(BigDecimal.ONE);
				}
					internetAddressesT1Service.internetAddressesT1Trigger(emailObj.getOwnerClass(), emailObj.getOwnerId().longValue(), emailObj.getOwnerSeq().longValue(), emailObj.getOwnerCode());
			}
			liReturn = oumpRepository.emailAddrInsertInternetAddresses(commitBean.getInsertList());
			for(InternetAddresses emailObj : commitBean.getInsertList()) {
				internetAddressesT2Service.internetAddressesT2Trigger(emailObj);
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (InternetAddresses inObj : commitBean.getUpdateList()) {
				inObj.setModifyUserId(commitBean.getCreateUserId());
				if(inObj.getOwnerId() == null) {
				inObj.setOwnerId(BigDecimal.ONE);
			}
			if(inObj.getOwnerSeq() == null) {
				inObj.setOwnerSeq(BigDecimal.ONE);
			}
					internetAddressesT1Service.internetAddressesT1Trigger(inObj.getOwnerClass(), inObj.getOwnerId().longValue(), inObj.getOwnerSeq().longValue(), inObj.getOwnerCode());
			}
			liReturn = oumpRepository.emailAddrUpdateInternetAddresses(commitBean.getUpdateList());
			for(InternetAddresses emailObj : commitBean.getUpdateList()) {
				emailObj.setCreateUserId(commitBean.getCreateUserId());
				internetAddressesT2Service.internetAddressesT2Trigger(emailObj);
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for(InternetAddresses emailObj : commitBean.getDeleteList()) {
				emailObj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumpRepository.emailAddrDeleteInternetAddresses(commitBean.getDeleteList());
			for(InternetAddresses emailObj : commitBean.getDeleteList()) {
				emailObj.setCreateUserId(commitBean.getCreateUserId());
				internetAddressesT2Service.internetAddressesT2Trigger(emailObj);
			}
		}
		} catch (CustomException e) {
			lgger.error("internetAddressesT1Trigger", e);
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgPhoneTypeRecordGroup() {
		return oumpRepository.rgPhoneTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgSuffixRecordGroup() {
		return oumpRepository.rgSuffixRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		return oumpRepository.rgSexCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgStatusRecordGroup() {
		return oumpRepository.rgStatusRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgPersonnelTypeRecordGroup() {
		return oumpRepository.rgPersonnelTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgPositionRecordGroup() {
		return oumpRepository.rgPositionRecordGroup();

	}

	@Override
	public List<StaffMembers> getStaffDetails(Date fromDate) {
		return oumpRepository.getStaffDetails(fromDate);
	}

}
