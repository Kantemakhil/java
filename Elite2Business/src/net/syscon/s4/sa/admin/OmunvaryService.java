package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.sa.admin.beans.NameSynonyms;
import net.syscon.s4.sa.admin.beans.NameSynonymsCommitBean;

/**
 * Interface OmunvaryService
 */
public interface OmunvaryService {
	String nameSynonymsCommit(NameSynonymsCommitBean commitBean);

	List<NameSynonyms> nameSynonymsExecuteQuery(NameSynonyms object);

	String getTableName(String liReturn);

}
