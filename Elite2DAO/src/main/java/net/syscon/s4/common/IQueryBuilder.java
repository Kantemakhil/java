package net.syscon.s4.common;

public interface IQueryBuilder {
	IQueryBuilder addOrderBy(boolean isAscending, String fields);

	IQueryBuilder addPagination();

	IQueryBuilder addQuery(String query);

	IQueryBuilder addRowCount();

	IQueryBuilder removeSpecialChars();

	String build();
}
