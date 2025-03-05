package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderProfileDetails;
import net.syscon.s4.im.beans.OffenderProfileDetailsCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.demographicsbiometrics.OidpinfoRepository;
import net.syscon.s4.inst.demographicsbiometrics.OidpinfoService;
import net.syscon.s4.triggers.OffendersTjnService;
import net.syscon.s4.triggers.OffendersVineIntfTrgService;
import net.syscon.s4.triggers.OmtoffsrcService;

/**
 * Class OidpinfoServiceImpl
 */
@Service
public class OidpinfoServiceImpl extends BaseBusiness implements OidpinfoService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidpinfoServiceImpl.class.getName());

	@Autowired
	private OidpinfoRepository oidpinfoDao;
	@Autowired
	private OmtoffsrcService omtoffsrcService;
	@Autowired
	private OffendersTjnService offendersTjnService;
	@Autowired
	private OffendersVineIntfTrgService offendersVineIntfTrgService;

	/**
	 * Creates new OidpinfoServiceImpl class Object
	 */
	public OidpinfoServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<Object> offBkgOnCheckDeleteMasteroffPdCur(final OffenderProfileDetails paramBean) {
		return oidpinfoDao.offBkgOnCheckDeleteMasteroffPdCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes offNamePostQuerycOffBirthState(final ReferenceCodes paramBean) {
		return oidpinfoDao.offNamePostQuerycOffBirthState(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ProfileTypes profileCodePostChange(final ProfileTypes paramBean) {
		return oidpinfoDao.profileCodePostChange(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ProfileTypes dspDescriptionWhenValidateItemprofileTypes(final ProfileTypes paramBean) {
		return oidpinfoDao.dspDescriptionWhenValidateItemprofileTypes(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffNameOffRefCodec(final ReferenceCodes paramBean) {
		return oidpinfoDao.cgfkchkOffNameOffRefCodec(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfklkpOffNameOffRefCodec(final ReferenceCodes paramBean) {
		return oidpinfoDao.cgfklkpOffNameOffRefCodec(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ProfileTypes cgfkchkOffPdOffPdPflTypc(final OffenderProfileDetails offenderProfileDetails) {
		return oidpinfoDao.cgfkchkOffPdOffPdPflTypc(offenderProfileDetails);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ProfileCodes cgfkchkOffPdOffPdPflCodc(final OffenderProfileDetails offenderProfileDetails) {
		return oidpinfoDao.cgfkchkOffPdOffPdPflCodc(offenderProfileDetails);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ProfileTypes cgfklkpOffPdOffPdPflCod(final ProfileTypes paramBean) {
		return oidpinfoDao.cgfklkpOffPdOffPdPflCod(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ProfileCodes cgfklkpOffPdOffPdPflCodc(final ProfileCodes paramBean) {
		return oidpinfoDao.cgfklkpOffPdOffPdPflCodc(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<Offenders> offNameSearchOffenders(final Offenders searchRecord) {
		return oidpinfoDao.offNameSearchOffenders(searchRecord);
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenders
	 *
	 */
	@Transactional
	public Integer offNameUpdateOffenders(final List<Offenders> lstOffenders) {
		return oidpinfoDao.offNameUpdateOffenders(lstOffenders);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderProfileDetails> offPdSearchOffenderProfileDetails(final OffenderProfileDetails searchRecord) {
		List<OffenderProfileDetails> returnProfList = new ArrayList<OffenderProfileDetails>();
		final List<OffenderProfileDetails> returnList = new ArrayList<OffenderProfileDetails>();
		returnProfList = oidpinfoDao.offPdSearchOffenderProfileDetails(searchRecord);
		if (returnProfList != null) {
			for (final OffenderProfileDetails obj : returnProfList) {
				final ProfileTypes profileTypes = cgfkchkOffPdOffPdPflTypc(obj);
				obj.setProfileTypeDesc((profileTypes.getDescription()));
			}
		}
		if (returnProfList != null && returnProfList.size() > 0) {
			for (final OffenderProfileDetails obj : returnProfList) {
				if (obj.getProfileTypeDesc() != null) {
					returnList.add(obj);
				}
			}
		}
		return returnList;
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderProfileDetails
	 *
	 */
	@Transactional
	public Integer offPdUpdateOffenderProfileDetails(final List<OffenderProfileDetails> lstOffenderProfileDetails) {
		return oidpinfoDao.offPdUpdateOffenderProfileDetails(lstOffenderProfileDetails);
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkOffNameDspDescriptionRgroup() {
		return oidpinfoDao.cgfkOffNameDspDescriptionRgroup();
	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @param profileCodes
	 */
	public List<ProfileCodes> cgfkOffPdDspDescriptionRgroup(final String profileType) {
		List<ProfileCodes> returnList = new ArrayList<>();
		returnList = oidpinfoDao.cgfkOffPdDspDescriptionRgroup(profileType);
		for (final ProfileCodes obj:returnList) {
			obj.setCode(obj.getProfileCode());
		}
		if(Optional.ofNullable(returnList).isPresent()) {
			returnList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgBirthStateRecordGroup() {
		return oidpinfoDao.rgBirthStateRecordGroup();
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<Dual> cgwhenNewFormInstancec() {
			return oidpinfoDao.cgwhenNewFormInstancec();
	}
	
	/**
	 * Update the records from database table
	 *
	 * @param lstOffenders
	 *
	 */
	@Transactional
	public Integer offNameCommit(final OffendersCommitBean commitBean) {
		int liReturn = 0;
		List<Offenders> offenderList= new ArrayList<>();
		try {
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				for (Offenders obj : commitBean.getUpdateList()) {
					obj.setModifyUserId(commitBean.getCreateUserId());
				}
				String operations="UPDATE";
				omtoffsrcService.omtoffsrc(commitBean.getUpdateList(), operations);
				liReturn = offNameUpdateOffenders(commitBean.getUpdateList());
				String operation="UPDATE";
				for (Offenders obj : commitBean.getUpdateList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
				}
				//offendersTjnService.offendersTjn(offenderList, commitBean.getUpdateList(), operation);
				offendersVineIntfTrgService.offendersVineIntfTrg(commitBean.getUpdateList(), operation);
			}
		} catch (Exception e) {
			logger.error("offNameCommit:",e);
		}
		return liReturn;
	}
	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderProfileDetails
	 *
	 */
	@Transactional
	public Integer offPdCommit(final OffenderProfileDetailsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderProfileDetails obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			try {
				liReturn = offPdUpdateOffenderProfileDetails(commitBean.getUpdateList());
			} catch (Exception e) {
				logger.error("offPdCommit:",e);
			}
		}
		return liReturn;
	}

}