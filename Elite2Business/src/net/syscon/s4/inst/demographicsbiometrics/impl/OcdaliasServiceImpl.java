package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ResponseEntityBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderIdentifiersCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.inst.demographicsbiometrics.OcdaliasRepository;
import net.syscon.s4.inst.demographicsbiometrics.OcdaliasService;
import net.syscon.s4.triggers.OffIdentVineIntfTrgService;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;
import net.syscon.s4.triggers.OffendersT1Service;
import net.syscon.s4.triggers.OmtoffsrcService;

/**
 * Class OcdaliasServiceImpl
 */
@Service
public class OcdaliasServiceImpl extends BaseBusiness implements OcdaliasService {

	@Autowired
	private OcdaliasRepository aliasDao;

	@Autowired
	private EliteDateService dateService;
	@Autowired
	private OffendersT1Service offendersT1Service;
	@Autowired
	private OmtoffsrcService omtoffsrcService;
	@Autowired
	private OffIdentVineIntfTrgService offIdentVineIntfTrgService;

	@Autowired
	private OffenderBookingsT2Service offenderBookingsT2Service;
	@Autowired
	private OffenderBookingsBkadmTrgService offenderBookingsBkadmTrgService;
	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;
	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;

	/**
	 * Logger object used to print the log in the file
	 */

	private static Logger logger = LogManager.getLogger(OcdaliasServiceImpl.class.getName());

	/**
	 * Creates new OcdaliasServiceImpl class Object
	 */
	public OcdaliasServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws SQLException
	 */
	public VHeaderBlock getWorkingNameOffenderID(final Offenders params) {
		return aliasDao.getWorkingNameOffenderID(params);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws SQLException
	 */
	public String offIdPreInsert(final String params) {
		return aliasDao.offIdPreInsert(params);
	}

	/**
	 * change working name
	 *
	 * @param params
	 *
	 * @throws SQLException
	 */
	public VHeaderBlock changeWorkingName(final VHeaderBlock lstVHeaderBlock) {
		VHeaderBlock vheader = null;
		OffenderBookings olddta = aliasDao.gettingOldData(
				lstVHeaderBlock.getOffenderBookId() != null ? lstVHeaderBlock.getOffenderBookId() : null);

		OffenderBookings offenderBooking = new OffenderBookings();
		offenderBooking.setModifyUserId(lstVHeaderBlock.getModifyUserId());
		offenderBooking.setAgyLocId(lstVHeaderBlock.getAgyLocId() != null ? lstVHeaderBlock.getAgyLocId() : null);
		offenderBooking.setCreateAgyLocId(
				lstVHeaderBlock.getCreateAgyLocId() != null ? lstVHeaderBlock.getCreateAgyLocId() : null);
		offenderBooking
				.setLivingUnitId(lstVHeaderBlock.getLivingUnitId() != null ? lstVHeaderBlock.getLivingUnitId() : null);
		offenderBooking.setOffenderBookId(lstVHeaderBlock.getOffenderBookId().longValue());
		offenderBooking.setOffenderId(lstVHeaderBlock.getOffenderId());
		offenderBooking.setActiveFlag(lstVHeaderBlock.getActiveFlag() != null ? lstVHeaderBlock.getActiveFlag() : null);
		offenderBookingsT2Service.offenderBookingsT2(offenderBooking, olddta);
		vheader = aliasDao.changeWorkingName(lstVHeaderBlock);
		String operation = "UPDATING";
		offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(olddta, offenderBooking, operation);
		offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(offenderBooking,ApplicationConstants.UPDATING);
		offenderBookingsT7Service.offenderBookingsT7Trigger(offenderBooking);

		return vheader;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<Offenders> offNameSearchOffenders(final Offenders objOffenders) {
		List<Offenders> returnList = new ArrayList<>();
		returnList = aliasDao.offNameSearchOffenders(objOffenders);
		final VHeaderBlock obj;
		obj = aliasDao.getWorkingNameOffenderID(objOffenders);
		final BigDecimal offenderId = obj.getOffenderId();
		for (final Offenders listval : returnList) {
			listval.setAge(BigDecimal.valueOf(dateService.calculateAge(listval.getBirthDate())));
			if (offenderId != null) {
				if (offenderId.equals(BigDecimal.valueOf(listval.getOffenderId()))) {
					listval.setAliasColEditCheck(true);
				}
			}
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderIdentifier> offIdSearchOffenderIdentifiers(final Offenders searchRecord) {
		return aliasDao.offIdSearchOffenderIdentifiers(searchRecord);
	}

	/**
	 * @throws SQLException
	 * 
	 */
	public List<OffenderIdentifier> offIdAllSearchOffenderIdentifiers(final Offenders searchBean) {
		return aliasDao.offIdAllSearchOffenderIdentifiers(searchBean);
	}

	/**
	 * @throws SQLException
	 * 
	 */
	@Override
	@Transactional
	public ResponseEntityBean offNameCommit(final OffendersCommitBean commitBean) {
		ResponseEntityBean responseEntityBean = new ResponseEntityBean();
		Integer liReturn = 0;

		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				liReturn = 0;
				for (Offenders obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
				}
				String operations = "INSERT";
				omtoffsrcService.omtoffsrc(commitBean.getInsertList(), operations);
				liReturn = aliasDao.offNameInsertOffenders(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				liReturn = 0;
				for (Offenders obj : commitBean.getUpdateList()) {
					obj.setModifyUserId(commitBean.getCreateUserId());
				aliasDao.gettingOldRecord(obj.getOffenderId());
				}
				String operations = "UPDATE";
				omtoffsrcService.omtoffsrc(commitBean.getUpdateList(), operations);
				liReturn = aliasDao.offNameUpdateOffenders(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				liReturn = 0;
				String operations = "DELETE";
				omtoffsrcService.omtoffsrc(commitBean.getDeleteList(), operations);
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = aliasDao.offNameDeleteOffenders(commitBean.getDeleteList());
				for (Offenders obj : commitBean.getDeleteList()) {
					offendersT1Service.offendersT1Trigger(obj.getOffenderId());
				}
			}
			
			if(liReturn == 1) {
				VHeaderBlock vHeaderBlockResp = null;
				VHeaderBlock vHeaderBlock = new VHeaderBlock();
				if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
					for(Offenders offenders : commitBean.getInsertList()) {
						if(offenders.getAliasColEditCheck() != null && offenders.getAliasColEditCheck()) {
							vHeaderBlock.setModifyUserId(commitBean.getCreateUserId());
							vHeaderBlock.setOffenderId(new BigDecimal(offenders.getOffenderId()));
							vHeaderBlock.setRootOffenderId(commitBean.getRootOffenderId());
							vHeaderBlock.setOffenderBookId(new BigDecimal(commitBean.getOffenderBookId()));
						}
					}
				}
				if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
					for(Offenders offenders : commitBean.getUpdateList()) {
						if(offenders.getAliasColEditCheck() != null && offenders.getAliasColEditCheck()) {
							vHeaderBlock.setModifyUserId(commitBean.getCreateUserId());
							vHeaderBlock.setOffenderId(new BigDecimal(offenders.getOffenderId()));
							vHeaderBlock.setRootOffenderId(commitBean.getRootOffenderId());
							vHeaderBlock.setOffenderBookId(new BigDecimal(commitBean.getOffenderBookId()));
						}
					}
				}
				if(vHeaderBlock != null && vHeaderBlock.getOffenderBookId() != null &&  vHeaderBlock.getRootOffenderId() != null && vHeaderBlock.getOffenderId() != null) {
					vHeaderBlockResp = changeWorkingName(vHeaderBlock);
					if(vHeaderBlockResp != null) {
						responseEntityBean.setLiReturn(1);
						responseEntityBean.setvHeaderBlock(vHeaderBlockResp); 
					} else {
						responseEntityBean.setLiReturn(0);
						responseEntityBean.setvHeaderBlock(vHeaderBlockResp);
					}
				} else {
					responseEntityBean.setLiReturn(1);
					responseEntityBean.setvHeaderBlock(null);
				}
				
			} else {
				responseEntityBean.setLiReturn(0);
			}
		} catch (Exception e) {
			logger.error("offNameCommit", e);
			responseEntityBean.setLiReturn(0);
		}
		return responseEntityBean;
	}

	/**
	 * @throws SQLException
	 * 
	 */
	@Override
	@Transactional
	public Integer offIdCommit(final OffenderIdentifiersCommitBean commitBean) {
		Integer liReturn = 0;
		List<OffenderIdentifier> oldList = new ArrayList<>();
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				liReturn = 0;
				for (OffenderIdentifier obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
				}
				liReturn = aliasDao.offIdInsertOffenderIdentifiers(commitBean.getInsertList());
				String operation = "INSERT";
				offIdentVineIntfTrgService.offIdentVineIntfTrg(commitBean.getInsertList(), oldList, operation);
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				liReturn = 0;
				for (OffenderIdentifier obj : commitBean.getUpdateList()) {
					obj.setModifyUserId(commitBean.getCreateUserId());
				}
				liReturn = aliasDao.offIdUpdateOffenderIdentifiers(commitBean.getUpdateList());
				String operation = "UPDATE";
				offIdentVineIntfTrgService.offIdentVineIntfTrg(commitBean.getUpdateList(), oldList, operation);
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				liReturn = 0;
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = aliasDao.offIdDeleteOffenderIdentifiers(commitBean.getDeleteList());
				String operation = "DELETE";
				offIdentVineIntfTrgService.offIdentVineIntfTrg(commitBean.getDeleteList(), oldList, operation);
			}
		} catch (Exception e) {
			logger.error("offIdCommit"+ e);
		}
		return liReturn;
	}

	public List<ReferenceCodes> getGenderDescription() {
		return aliasDao.getGenderDescription();
	}

	public Integer offNameOnCheckDeleteMasteroffIdCur(final Offenders aliasesBean) {
		return aliasDao.offNameOnCheckDeleteMasteroffIdCur(aliasesBean);
	}

	/**
	 * MEthod used to get PROFILE_VALUE, PROFILE_VALUE2 from SystemProfiles
	 * 
	 * @return SystemProfiles
	 */
	public SystemProfiles vsRangeCursor() {
		return aliasDao.vsRangeCursor();
	}
}