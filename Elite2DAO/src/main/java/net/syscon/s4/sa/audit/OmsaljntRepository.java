package net.syscon.s4.sa.audit;
import java.util.List;
/**
 * Interface OmsaljntRepository
 */
public interface OmsaljntRepository {
	Integer journalTableViewUpdateJournalTableView(List<JournalTableView> lstJournalTableView) ;

	List<JournalTableView> journalTableViewExecuteQuery(JournalTableView objJournalTableView) ;

}
