package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.ImagesCommitBean;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.StgIdentifiers;
import net.syscon.s4.im.beans.StgIdentifiersCommitBean;
import net.syscon.s4.im.beans.StgIdentifyingWords;
import net.syscon.s4.im.beans.StgIdentifyingWordsCommitBean;

/**
 * Interface OidstgidService
 */
public interface OidstgidService {

	Integer stgIdentifiersCommit(StgIdentifiersCommitBean commitBean);

	List<StgIdentifyingWords> stgIdentifyingWordsExecuteQuery(StgIdentifyingWords stgId);

	List<StgIdentifiers> stgIdentifiersExecuteQuery(StgIdentifiers objStgIdentifiers);

	Integer image1Commit(ImagesCommitBean commitBean);

	List<ProfileTypes> rgProfileTypeRecordGroup(Long stgId);

	List<Images> image1ExecuteQuery(Long stgId, Long identifierSeq);

	Integer stgIdentifyingWordsCommit(StgIdentifyingWordsCommitBean commitBean);

	String oidstgidGetGlobalStgDescription(BigDecimal stgId);

}
