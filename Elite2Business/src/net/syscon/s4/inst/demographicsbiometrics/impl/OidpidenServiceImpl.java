package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderIdentifyingMark;
import net.syscon.s4.common.beans.OffenderIdentifyingMarksCommitBean;
import net.syscon.s4.common.beans.OffenderPhysicalAttributesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderPhysicalAttributes;
import net.syscon.s4.im.beans.OffenderProfileDetails;
import net.syscon.s4.im.beans.OffenderProfileDetailsCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.inst.demographicsbiometrics.OidpidenRepository;
import net.syscon.s4.inst.demographicsbiometrics.OidpidenService;
import net.syscon.s4.pkgs.common.impl.CommonServiceImpl;
import net.syscon.s4.triggers.OffendersT1Service;
import net.syscon.s4.triggers.OffendersTjnService;
import net.syscon.s4.triggers.OffendersVineIntfTrgService;
import net.syscon.s4.triggers.OmtoffsrcService;

@Service
public class OidpidenServiceImpl extends BaseBusiness implements OidpidenService {
	/**
	 * Logger object used to print the log in the file
	 */

	@Autowired
	private OidpidenRepository oidpidenDao;

	@Autowired
	private CommonServiceImpl commonServiceImpl;
		
	@Autowired
	private OmtoffsrcService omtoffsrcService;
	
	@Autowired
	private OffendersTjnService offendersTjnService;
	
	@Autowired
	private OffendersVineIntfTrgService offendersVineIntfTrgService;
	
	@Autowired
	private OffendersT1Service offendersT1Service;
	
	
	
	/**
	 * Creates new OidpidenBusiness class Object
	 */
	public OidpidenServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Offenders offBkgPreDelete(final Offenders paramBean) {
		Offenders deleteList = new Offenders();
		deleteList = oidpidenDao.offBkgPreDelete(paramBean);
		offendersT1Service.offendersT1Trigger(paramBean.getOffenderId());
		return deleteList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> nbtDetailDescWhenValidateItemprofileTypes(final ProfileTypes paramBean) {
		return oidpidenDao.nbtDetailDescWhenValidateItemprofileTypes(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ProfileTypes nbtDetailDescWhenNewItemInstanceprofileTypes(final ProfileTypes paramBean) {
		return oidpidenDao.nbtDetailDescWhenNewItemInstanceprofileTypes(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ProfileTypes offPdWhenNewRecordInstanceprofileTypes(final ProfileTypes paramBean) {
		return oidpidenDao.offPdWhenNewRecordInstanceprofileTypes(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ProfileTypes offPdPostQuerycharDescCur(final OffenderProfileDetails paramBean) {
		return oidpidenDao.offPdPostQuerycharDescCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<SystemProfiles> callToShowFingerprintOld(final SystemProfiles paramBean) {
		return oidpidenDao.callToShowFingerprintOld(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> cgfkchkOffPdOffPdPflCodvalueTypeCur(final ProfileTypes paramBean) {
		return oidpidenDao.cgfkchkOffPdOffPdPflCodvalueTypeCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ProfileCodes cgfkchkOffPdOffPdPflCodc(final OffenderProfileDetails paramBean) {
		return oidpidenDao.cgfkchkOffPdOffPdPflCodc(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderPhysicalAttributes> offPaSearchOffenderPhysicalAttributes(
			final OffenderPhysicalAttributes offenderPhysicalAttributes) {
		return oidpidenDao.offPaSearchOffenderPhysicalAttributes(offenderPhysicalAttributes);

	}

	public Integer insertUpdateDeleteOffenderPhysicalAttributes(final OffenderPhysicalAttributesCommitBean commitBean) {
		int returnvalue = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for( OffenderPhysicalAttributes obj : commitBean.getInsertList()){
			obj.setCreateUserId(commitBean.getCreateUserId());
			}
			returnvalue = insertOffenderPhysicalAttributes(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for( OffenderPhysicalAttributes obj : commitBean.getUpdateList()){
				obj.setModifyUserId(commitBean.getCreateUserId());	
				}
			returnvalue = updateOffenderPhysicalAttributes(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			returnvalue = deleteOffenderPhysicalAttributes(commitBean.getDeleteList());
		}

		return returnvalue;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderPhysicalAttributes
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer insertOffenderPhysicalAttributes(
			final List<OffenderPhysicalAttributes> lstOffenderPhysicalAttributes) {
		int result = 0;
		for (final OffenderPhysicalAttributes offenderPhysicalAttributes : lstOffenderPhysicalAttributes) {
			result = oidpidenDao.getOffenderBook(offenderPhysicalAttributes.getOffenderBookId());
		}

		if (result == 0) {
			result = oidpidenDao.insertOffenderPhysicalAttributes(lstOffenderPhysicalAttributes);
		} else {
			result = oidpidenDao.updateOffenderPhysicalAttributes(lstOffenderPhysicalAttributes);
		}
		return result;
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderPhysicalAttributes
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer updateOffenderPhysicalAttributes(
			final List<OffenderPhysicalAttributes> lstOffenderPhysicalAttributes) {
		return oidpidenDao.updateOffenderPhysicalAttributes(lstOffenderPhysicalAttributes);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderPhysicalAttributes
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer deleteOffenderPhysicalAttributes(
			final List<OffenderPhysicalAttributes> lstOffenderPhysicalAttributes) {
		return oidpidenDao.deleteOffenderPhysicalAttributes(lstOffenderPhysicalAttributes);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<Offenders> offRaceSearchOffenders(final Offenders searchRecord) {
		return oidpidenDao.offRaceSearchOffenders(searchRecord);

	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenders
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offRaceUpdateOffenders(final OffendersCommitBean commitBean) {
		Integer lireturn = 0;
		List<Offenders> oldUpdatedList =new  ArrayList<>();
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for( Offenders  bean: commitBean.getUpdateList()) {
			bean.setModifyUserId(commitBean.getCreateUserId());	
			  oldUpdatedList = oidpidenDao.getoldlistoffenders(bean.getOffenderId());
			}
			omtoffsrcService.omtoffsrc(commitBean.getUpdateList(), "UPDATE");
			lireturn = oidpidenDao.offRaceUpdateOffenders(commitBean.getUpdateList());
			//offendersTjnService.offendersTjn(commitBean.getUpdateList(), oldUpdatedList, "UPDATE");
			offendersVineIntfTrgService.offendersVineIntfTrg(commitBean.getUpdateList(), "UPDATE");
		}
		return lireturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderProfileDetails> offPdSearchOffenderProfileDetails(final OffenderProfileDetails searchRecord) {
		List<OffenderProfileDetails> returnList = new ArrayList<OffenderProfileDetails>();
		final List<OffenderProfileDetails> returnListTemp = new ArrayList<OffenderProfileDetails>();
		returnList = oidpidenDao.offPdSearchOffenderProfileDetails(searchRecord);
		if(returnList !=null){
		for (final OffenderProfileDetails offenderProfileDetails : returnList) {
			final ProfileTypes profileTypes = offPdPostQuerycharDescCur(offenderProfileDetails);
			offenderProfileDetails.setNbtCharacteristic(profileTypes.getDescription());
			if (offenderProfileDetails.getProfileCode() != null && offenderProfileDetails.getProfileType() != null) {
				final ProfileCodes profileCodes = cgfkchkOffPdOffPdPflCodc(offenderProfileDetails);
				offenderProfileDetails.setNbtDetailDesc(profileCodes.getDescription());
			}
		}
		}
	
		if (returnList != null && returnList.size() > 0) {
			for (final OffenderProfileDetails obj : returnList) {
				if (obj.getNbtCharacteristic() != null) {
					returnListTemp.add(obj);
				}
			}
		}
		return returnListTemp;
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderProfileDetails
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offPdUpdateOffenderProfileDetails(final OffenderProfileDetailsCommitBean commitBean) {
		Integer liReturn = 0;
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for(OffenderProfileDetails obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
		}
			liReturn= oidpidenDao.offPdUpdateOffenderProfileDetails(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderIdentifyingMark> offImSearchOffenderIdentifyingMarks(
			final OffenderIdentifyingMark searchRecord, final String imageObjectType) {
		 String imgFlag=null;
		final List<OffenderIdentifyingMark> resultList=oidpidenDao.offImSearchOffenderIdentifyingMarks(searchRecord);
		for(final OffenderIdentifyingMark result : resultList) {
			if(result.getMarkType() != null){
			final String getDescMarkType=oidpidenDao.getDescriptionofMarkType(result.getMarkType());
			result.setNbtMarkTypeDesc(getDescMarkType);
			}
			if(result.getBodyPartCode() != null){
			final String getDescBodyPart=oidpidenDao.getDescriptionofBodyPart(result.getBodyPartCode());
			result.setNbtBodyPartDesc(getDescBodyPart);
			}
			try {
				imgFlag = commonServiceImpl.imageMarkExists(Long.valueOf(result.getOffenderBookId()) != null ? new BigDecimal(result.getOffenderBookId()):null, imageObjectType, result.getMarkType(), result.getBodyPartCode(), Long.valueOf(result.getIdMarkSeq()) != null ? new BigDecimal(result.getIdMarkSeq()):null);
			}catch (Exception e) {
				e.printStackTrace();
			}
			result.setImageFlag(imgFlag);
			if(result.getImages()!=null && !result.getImages().isEmpty()&&result.getImages().get(0)!=null && result.getImages().get(0).getImageId()!=null) {
				result.setImageId(new BigDecimal(result.getImages().get(0).getImageId()));
			}
		}
		return resultList;

	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @return Integer
	 * @Param commitBean
	 */
	@Transactional
	public Integer insertUpdateDeleteOffenderIdentifyingMarks(final OffenderIdentifyingMarksCommitBean commitBean) {
		String offenderBookId = null;
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			offenderBookId = String.valueOf(commitBean.getInsertList().get(0).getOffenderBookId());
			List<OffenderIdentifyingMark> recordSavingObject = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final int value = oidpidenDao.getOffenderIdentifyingMarkIdMarkSeq(offenderBookId);
					final OffenderIdentifyingMark offenderIdentifyingMark = commitBean.getInsertList().get(i);
					offenderIdentifyingMark.setIdMarkSeq(value);
					offenderIdentifyingMark.setCreateUserId(commitBean.getCreateUserId());
					recordSavingObject.add(offenderIdentifyingMark);
					liReturn = insertOffenderIdentifyingMarks(recordSavingObject);
				}
			}
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for(OffenderIdentifyingMark bean: commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = updateOffenderIdentifyingMarks(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = deleteOffenderIdentifyingMarks(commitBean.getDeleteList());
			
		}
		return liReturn;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws SQLException
	 */
	public Integer getOffenderIdentifyingMarkIdMarkSeq(final String offenderBookId) {
		return oidpidenDao.getOffenderIdentifyingMarkIdMarkSeq(offenderBookId);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderIdentifyingMarks
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer insertOffenderIdentifyingMarks(final List<OffenderIdentifyingMark> lstOffenderIdentifyingMarks) {
		return oidpidenDao.insertOffenderIdentifyingMarks(lstOffenderIdentifyingMarks);
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderIdentifyingMarks
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer updateOffenderIdentifyingMarks(final List<OffenderIdentifyingMark> lstOffenderIdentifyingMarks) {
		return oidpidenDao.updateOffenderIdentifyingMarks(lstOffenderIdentifyingMarks);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderIdentifyingMarks
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer deleteOffenderIdentifyingMarks(final List<OffenderIdentifyingMark> lstOffenderIdentifyingMarks) {
		return oidpidenDao.deleteOffenderIdentifyingMarks(lstOffenderIdentifyingMarks);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgRaceCodeRecordGroup() {
		return oidpidenDao.rgRaceCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ProfileCodes> rgProfileRecordGroup(final String profileTypes) {
		List<ProfileCodes> returnList = oidpidenDao.rgProfileRecordGroup(profileTypes);
		for (final ProfileCodes obj : returnList) {
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
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgMarkTypeRecordGroup() {
		return oidpidenDao.rgMarkTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgBodyPartRecordGroup() {
		return oidpidenDao.rgBodyPartRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgPartOrientRecordGroup() {
		return oidpidenDao.rgPartOrientRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgSideRecordGroup() {
		return oidpidenDao.rgSideRecordGroup();

	}

	public String checkImage(final String imageObjType, final String offenderBookId, final String markType, final String bodypart, final String objectSeq) {
		return commonServiceImpl.imageMarkExists(offenderBookId != null ? new BigDecimal(offenderBookId):null, imageObjType, markType, bodypart, objectSeq!= null ? new BigDecimal(objectSeq):null);
	}

	public List<OffenderProfileDetails> checkProfileDetails(String offenderBookId, String caseloadType,
			String profileCategory, String userName) {
		Integer returnList = 0;
		List<OffenderProfileDetails> returnListtemp = new ArrayList<OffenderProfileDetails>();
		
		returnList = oidpidenDao.gettingListseq(offenderBookId);
		if (returnList == 0) {
			
			returnList = oidpidenDao.insertOffenderprofileDetails(offenderBookId, userName);
		}
		returnListtemp = oidpidenDao.listofProfileTypes(offenderBookId, profileCategory);

		    if (returnListtemp.size() > 0) {
			List<String> profileTypeList = new ArrayList<>();
			for (OffenderProfileDetails profiletypes : returnListtemp) {
				profileTypeList.add(profiletypes.getProfileType());
			}
			returnListtemp = oidpidenDao.insertProfileDetails(offenderBookId, caseloadType, profileCategory,
					profileTypeList,userName);
		}

		return returnListtemp;
	}

	
}
