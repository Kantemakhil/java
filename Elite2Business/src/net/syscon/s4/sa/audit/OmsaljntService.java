package net.syscon.s4.sa.audit;
import java.util.List;
/**
 * Interface OmsaljntService 
 */
public interface OmsaljntService  {
	Integer journalTableViewCommit(JournalTableViewCommitBean commitBean) ;

	List<JournalTableView> journalTableViewExecuteQuery(JournalTableView objJournalTableView) ;

}
