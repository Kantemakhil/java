package net.syscon.s4.common.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import net.syscon.s4.common.IQueryBuilder;

public abstract class AbstractQueryBuilder implements IQueryBuilder {
	protected final String initialSQL;
	protected final Map<String, FieldMapper> fieldMap;
	protected final Map<String, String> fieldNameToColumnMap;

	protected boolean includePagination;
	protected boolean includeRowCount;

	protected final StringBuilder extraWhere = new StringBuilder();

	protected String extraOrderBy = "";
	protected DatabaseDialect dialect;
	protected boolean removeSpecialChars;

	protected AbstractQueryBuilder(final String initialSQL, final Map<String, FieldMapper> fieldMap,
			final DatabaseDialect dialect) {
		this.initialSQL = initialSQL;
		this.dialect = dialect;
		this.fieldMap = fieldMap;
		if (fieldMap != null) {
			this.fieldNameToColumnMap = fieldMap.entrySet().stream()
					.collect(Collectors.toMap(value -> value.getValue().getName(), Map.Entry::getKey));
		} else {
			this.fieldNameToColumnMap = Collections.EMPTY_MAP;
		}
	}

	public IQueryBuilder removeSpecialChars() {
		this.removeSpecialChars = true;
		return this;
	}

	public IQueryBuilder addPagination() {
		includePagination = true;

		return this;
	}

	public IQueryBuilder addRowCount() {
		includeRowCount = true;

		return this;
	}

	public IQueryBuilder addQuery(final String query) {
		return this;
	}

	public IQueryBuilder addOrderBy(final boolean isAscending, String fields) {
		final String[] colOrder = StringUtils.split(fields, ",");
		if (colOrder != null && colOrder.length > 0) {
			List<String> cols = Arrays.stream(colOrder).map(fieldNameToColumnMap::get).filter(Objects::nonNull)
					.collect(Collectors.toList());

			if (!cols.isEmpty()) {
				extraOrderBy += StringUtils.join(cols, " " + addOrderDirection(isAscending) + ",") + " "
						+ addOrderDirection(isAscending);
			}
		}
		return this;
	}

	private SQLKeyword addOrderDirection(boolean isAscending) {
		return isAscending ? SQLKeyword.ASC : SQLKeyword.DESC;
	}

	protected String removeSpecialCharacters(final String sql) {
		if (sql == null)
			return null;
		final String stmts[] = { sql, sql.replace('\n', ' ').replace('\r', ' ').replace('\t', ' ') };
		while (!stmts[0].equals(stmts[1])) {
			stmts[0] = stmts[1];
			stmts[1] = stmts[1].replace('\n', ' ').replace('\r', ' ').replace('\t', ' ');
			stmts[1] = stmts[1].replaceAll("  ", " ");
		}
		return stmts[0].trim();
	}

	protected Optional<SQLKeyword> getStatementType() {
		String modifiedSQL = initialSQL.toUpperCase().replace('\t', ' ').replace('\r', ' ');

		String[] lines = modifiedSQL.split("\\n");

		SQLKeyword statementType = null;

		for (String line : lines) {
			if (StringUtils.startsWithIgnoreCase(line.trim(), SQLKeyword.SELECT.toString())) {
				statementType = SQLKeyword.SELECT;

				break;
			}

			if (StringUtils.startsWithIgnoreCase(line.trim(), SQLKeyword.INSERT.toString())) {
				statementType = SQLKeyword.INSERT;

				break;
			}

			if (StringUtils.startsWithIgnoreCase(line.trim(), SQLKeyword.UPDATE.toString())) {
				statementType = SQLKeyword.UPDATE;

				break;
			}

			if (StringUtils.startsWithIgnoreCase(line.trim(), SQLKeyword.DELETE.toString())) {
				statementType = SQLKeyword.DELETE;

				break;
			}
		}

		return Optional.ofNullable(statementType);
	}
}
