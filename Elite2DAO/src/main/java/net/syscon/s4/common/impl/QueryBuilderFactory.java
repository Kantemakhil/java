package net.syscon.s4.common.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import net.syscon.s4.common.IQueryBuilder;

@Component
public class QueryBuilderFactory {

    private final DatabaseDialect dialect;

    public QueryBuilderFactory() {
        this.dialect = DatabaseDialect.ORACLE_11;
    };

    public IQueryBuilder getQueryBuilder(String initialSql, Map<String, FieldMapper> fieldMap) {
      final  IQueryBuilder queryBuilder = new OracleQueryBuilder(initialSql, fieldMap, dialect);

        return queryBuilder;
    }
}
