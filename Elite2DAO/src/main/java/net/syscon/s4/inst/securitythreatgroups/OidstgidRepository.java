package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.StgIdentifiers;
import net.syscon.s4.im.beans.StgIdentifyingWords;

/**
 * Interface OidstgidRepository
 */
public interface OidstgidRepository {
	List<Images> stgIdentifiersPostQuery(Images paramBean);

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	Integer image1InsertImages(List<Images> list);

	List<StgIdentifyingWords> stgIdentifyingWordsExecuteQuery(StgIdentifyingWords stgId);

	Integer stgIdentifiersInsertStgIdentifiers(List<StgIdentifiers> lstStgIdentifiers);

	List<Images> image1ExecuteQuery(Long stgId, Long identifierSeq);

	Integer stgIdentifiersUpdateStgIdentifiers(List<StgIdentifiers> lstStgIdentifiers);

	Integer stgIdentifyingWordsUpdateStgIdentifyingWords(List<StgIdentifyingWords> object);

	Integer stgIdentifyingWordsDeleteStgIdentifyingWords(List<StgIdentifyingWords> object);

	List<StgIdentifiers> stgIdentifiersExecuteQuery(StgIdentifiers objStgIdentifiers);

	Integer stgIdentifiersDeleteStgIdentifiers(List<StgIdentifiers> lstStgIdentifiers);

	List<ProfileTypes> stgIdentifiersPostQuery(ProfileTypes paramBean);

	List<ProfileTypes> rgProfileTypeRecordGroup(Long profileType);

	Integer image1DeleteImages(List<Images> lstImages);

	Integer stgIdentifyingWordsInsertStgIdentifyingWords(List<StgIdentifyingWords> object);

	Long identifierSeqData(Long stgId);

	Long wordSeqData(Long stgId);

	String oidstgidGetGlobalStgDescription(BigDecimal stgId);
	
	List<StgIdentifiers> getAllStgIndentifiersData(final Long stgId,String profileType);

}
