package net.syscon.s4.pkgs.oms_form_access;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.sa.admin.beans.AccessibleFormTables;

public interface OmsFormAccessRepository {

	List<AccessibleFormTables> tableCur(final String origForm, final String destForm);

	Integer lvStmtSelect(final String tableName);

	Integer lvStmtIfSelect(final String tableName, final BigDecimal offenderId);

	Integer lvStmtElseSelect(final String tableName, final BigDecimal bookId);

	Integer lvStmtLastElseSelect(final String tableName, final BigDecimal offenderId);

	Integer lvStmt(final String tableName,Long stgdId);

}
