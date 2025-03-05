package net.syscon.s4.common.impl;

public enum SQLKeyword {
    SELECT("SELECT"),
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    ASC("ASC"),
    DESC("DESC"),
    WHERE("WHERE"),
    AND("AND"),
    OR("OR"),
    ORDER_BY("ORDER BY");

	private final String keyword;

	SQLKeyword(final String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return keyword;
	}
}
