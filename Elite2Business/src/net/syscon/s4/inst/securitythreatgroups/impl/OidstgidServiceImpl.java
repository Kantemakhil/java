package net.syscon.s4.inst.securitythreatgroups.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.ImageOriginals;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.ImagesCommitBean;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.StgIdentifiers;
import net.syscon.s4.im.beans.StgIdentifiersCommitBean;
import net.syscon.s4.im.beans.StgIdentifyingWords;
import net.syscon.s4.im.beans.StgIdentifyingWordsCommitBean;
import net.syscon.s4.inst.securitythreatgroups.OidstgidRepository;
import net.syscon.s4.inst.securitythreatgroups.OidstgidService;
import net.syscon.s4.triggers.ImagesTiService;

/**
 * Class OidstgidServiceImplService
 */
@Service
public class OidstgidServiceImpl extends BaseBusiness implements OidstgidService {

	@Autowired
	private OidstgidRepository oidstgidRepository;
	
	@Autowired
	private ImagesTiService imagesTiService;

	/**
	 * Creates new OidstgidServiceImpl class Object
	 */
	public OidstgidServiceImpl() {
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @return List<StgIdentifiers>
	 * @param searchRecord
	 *
	 * 
	 */
	public List<StgIdentifiers> stgIdentifiersExecuteQuery(final StgIdentifiers searchRecord) {
		final List<StgIdentifiers> resultList = oidstgidRepository.stgIdentifiersExecuteQuery(searchRecord);
		if (resultList != null && resultList.size() > 0) {
			resultList.stream().forEach(data -> {
				if (data.getStgId() != null && data.getIdentifierSeq() != null) {
					List<Images> imgResult = oidstgidRepository.image1ExecuteQuery(data.getStgId(),
							data.getIdentifierSeq());
					if (imgResult != null && imgResult.size() > 0) {
						imgResult.forEach(imgData -> {
							if (imgData.getImageThumbnail() != null) {
								data.setImageData(imgData.getImageThumbnail());
							}
						});
					}

				}
			});
		}
		return resultList;

	}

	/**
	 * Insert the records from database table
	 * 
	 * @return Integer
	 * @param lstSTG_IDENTIFIERS
	 *
	 * 
	 */
	@Transactional
	public Integer stgIdentifiersCommit(final StgIdentifiersCommitBean commitBean) {
		int liReturn = 0;
		Long stgId = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final StgIdentifiers stgIdentifiers : commitBean.getInsertList()) {
				stgId = stgIdentifiers.getStgId();
			}
			List<StgIdentifiers> saveObj = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					saveObj = new ArrayList<>();
					final Long identifierSeq = oidstgidRepository.identifierSeqData(stgId);
					final StgIdentifiers offenderAlertObj = commitBean.getInsertList().get(i);
					offenderAlertObj.setIdentifierSeq(identifierSeq);
					offenderAlertObj.setCreateUserId(commitBean.getCreateUserId());
					saveObj.add(offenderAlertObj);
					liReturn = oidstgidRepository.stgIdentifiersInsertStgIdentifiers(saveObj);
				}
			}
		}

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(action -> {
				action.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = oidstgidRepository.stgIdentifiersUpdateStgIdentifiers(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			liReturn = oidstgidRepository.stgIdentifiersDeleteStgIdentifiers(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderAlerts
	 * @return Integer
	 *
	 * 
	 */
	@Transactional
	public Integer stgIdentifiersUpdateStgIdentifiers(final List<StgIdentifiers> lstOffenderAlerts) {
		lstOffenderAlerts.forEach(element -> {
			if (element.getStgId() != null) {
				final Long identifierSeq = oidstgidRepository.identifierSeqData(element.getStgId());
				if (identifierSeq != null) {
					element.setIdentifierSeq(identifierSeq);
				}
			}
		});
		return oidstgidRepository.stgIdentifiersUpdateStgIdentifiers(lstOffenderAlerts);
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param identifierSeq,stgId
	 * @return List<Images>
	 */
	public List<Images> image1ExecuteQuery(final Long stgId, final Long identifierSeq) {
		return oidstgidRepository.image1ExecuteQuery(stgId, identifierSeq);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 * @return Integer
	 *
	 * 
	 */
	@Transactional
	public Integer image1Commit(final ImagesCommitBean commitBean) {
		int liReturn = 0;
		ImageOriginals io = new ImageOriginals();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (Images images : commitBean.getUpdateList()) {
				io.setImageId(images.getImageId().intValue());
				io.setCreateUserId(commitBean.getCreateUserId());
				images.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oidstgidRepository.image1InsertImages(commitBean.getInsertList());
			imagesTiService.imagesTiTrigger(io);
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidstgidRepository.image1DeleteImages(commitBean.getDeleteList());
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
	public List<StgIdentifyingWords> stgIdentifyingWordsExecuteQuery(final StgIdentifyingWords stgId) {
		final List<StgIdentifyingWords> returnList = oidstgidRepository.stgIdentifyingWordsExecuteQuery(stgId);
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 * @return Integer
	 *
	 * 
	 */
	@Transactional
	public Integer stgIdentifyingWordsCommit(final StgIdentifyingWordsCommitBean commitBean) {
		Integer liReturn = 0;
		Long stgId = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final StgIdentifyingWords stgIdentifiers : commitBean.getInsertList()) {
				
				stgId = stgIdentifiers.getStgId();
			}
			List<StgIdentifyingWords> recordSavingObj = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					recordSavingObj = new ArrayList<>();
					final Long identifierSeq = oidstgidRepository.wordSeqData(stgId);
					final StgIdentifyingWords offenderAlertObj = commitBean.getInsertList().get(i);
					offenderAlertObj.setWordSeq(identifierSeq);
					offenderAlertObj.setCreateUserId(commitBean.getCreateUserId());
					recordSavingObj.add(offenderAlertObj);
					liReturn = oidstgidRepository.stgIdentifyingWordsInsertStgIdentifyingWords(recordSavingObj);
				}
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(action->{
				action.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = oidstgidRepository.stgIdentifyingWordsUpdateStgIdentifyingWords(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidstgidRepository.stgIdentifyingWordsDeleteStgIdentifyingWords(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @param stgId
	 * @return List<ProfileTypes>
	 */
	public List<ProfileTypes> rgProfileTypeRecordGroup(final Long stgId) {
		List<ProfileTypes> returnList = oidstgidRepository.rgProfileTypeRecordGroup(stgId);
		Long listSeq=0l;
		for(ProfileTypes obj : returnList) {
			obj.setListSeq(new BigDecimal(++listSeq));
			obj.setCanDisplay(true);
		}
		return returnList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @param stgId
	 * @return String
	 */
	@Override
	public String oidstgidGetGlobalStgDescription(final BigDecimal stgId) {
		return oidstgidRepository.oidstgidGetGlobalStgDescription(stgId);
	}

}