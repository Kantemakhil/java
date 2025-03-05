package net.syscon.s4.sa.audit;

import java.io.Serializable;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the JOURNAL_TABLE_VIEW database table.
 * 
 */
public class JournalTableView extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String status;

	private String tableName;

	public JournalTableView() {
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
