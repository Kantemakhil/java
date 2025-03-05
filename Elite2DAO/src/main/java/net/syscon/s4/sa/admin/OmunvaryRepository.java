package net.syscon.s4.sa.admin;
import java.util.List;

import net.syscon.s4.sa.admin.beans.NameSynonyms;
/**
 * Interface OmunvaryRepository
 */
public interface OmunvaryRepository {
	String nameSynonymsInsertNameSynonyms(List<NameSynonyms> object) ;

	List<NameSynonyms> nameSynonymsExecuteQuery(NameSynonyms object) ;

	String nameSynonymsDeleteNameSynonyms(List<NameSynonyms> object) ;

}
