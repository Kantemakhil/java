CREATE OR REPLACE VIEW all_cons_columns
AS SELECT x.cstrschema::character varying(128) AS owner,
    x.cstrname::character varying(128) AS constraint_name,
    x.tblname::character varying(128) AS table_name,
    x.colname::character varying(4000) AS column_name,
    rank() OVER (PARTITION BY x.cstrschema, x.tblname, x.cstrname ORDER BY x.colnum)::smallint AS "position"
   FROM ( SELECT DISTINCT nr.nspname,
            r.relname,
            r.relowner,
            a.attname,
            a.attnum,
            nc.nspname,
            c.conname
           FROM pg_namespace nr,
            pg_class r,
            pg_attribute a,
            pg_depend d,
            pg_namespace nc,
            pg_constraint c
          WHERE nr.oid = r.relnamespace AND r.oid = a.attrelid AND d.refclassid = 'pg_class'::regclass::oid AND d.refobjid = r.oid AND d.refobjsubid = a.attnum AND d.classid = 'pg_constraint'::regclass::oid AND d.objid = c.oid AND c.connamespace = nc.oid AND c.contype = 'c'::"char" AND (r.relkind = ANY (ARRAY['r'::"char", 'p'::"char"])) AND NOT a.attisdropped
        UNION ALL
         SELECT nr.nspname,
            r.relname,
            r.relowner,
            a.attname,
            a.attnum,
            nc.nspname,
            c.conname
           FROM pg_namespace nr,
            pg_class r,
            pg_attribute a,
            pg_namespace nc,
            pg_constraint c
          WHERE nr.oid = r.relnamespace AND r.oid = a.attrelid AND nc.oid = c.connamespace AND r.oid =
                CASE c.contype
                    WHEN 'f'::"char" THEN c.confrelid
                    ELSE c.conrelid
                END AND (a.attnum = ANY (
                CASE c.contype
                    WHEN 'f'::"char" THEN c.confkey
                    ELSE c.conkey
                END)) AND NOT a.attisdropped AND (c.contype = ANY (ARRAY['p'::"char", 'u'::"char", 'f'::"char"])) AND (r.relkind = ANY (ARRAY['r'::"char", 'p'::"char"]))) x(tblschema, tblname, tblowner, colname, colnum, cstrschema, cstrname)
     JOIN pg_user u ON u.usesysid = x.tblowner
  WHERE u.usename = USER OR has_column_privilege(x.tblname::text, x.colname::text, 'SELECT,INSERT,UPDATE,REFERENCES'::text);
  
  CREATE OR REPLACE VIEW all_constraints
AS SELECT n.nspname::character varying(30) AS owner,
    c.conname::character varying(30) AS constraint_name,
    c.contype::character varying(1) AS constraint_type,
    cl.relname::character varying(30) AS table_name,
    pg_get_constraintdef(c.oid) AS search_condition,
    n_ref.nspname::character varying(30) AS r_owner,
    c_ref.conname::character varying(30) AS r_constraint_name,
        CASE c.confdeltype
            WHEN 'a'::"char" THEN 'no action'::character varying(9)
            WHEN 'r'::"char" THEN 'restrict'::character varying(9)
            WHEN 'c'::"char" THEN 'cascade'::character varying(9)
            WHEN 'n'::"char" THEN 'set null'::character varying(9)
            WHEN 'd'::"char" THEN 'set default'::character varying(11)
            ELSE NULL::character varying(9)
        END AS delete_rule,
    'ENABLED'::character varying(8) AS status,
        CASE
            WHEN c.condeferrable THEN 'DEFERRABLE'::character varying(14)
            ELSE 'NOT DEFERRABLE'::character varying(14)
        END AS "deferrable",
        CASE
            WHEN c.condeferred THEN 'DEFERRED'::character varying(9)
            ELSE 'IMMEDIATE'::character varying(9)
        END AS deferred,
        CASE
            WHEN c.convalidated THEN 'VALIDATED'::character varying(13)
            ELSE 'NOT VALIDATED'::character varying(13)
        END AS validated,
    'GENERATED NAME'::character varying(14) AS generated,
    NULL::character varying(3) AS bad,
    NULL::character varying(4) AS rely,
    NULL::date AS last_change,
    n.nspname::character varying(30) AS index_owner,
    cl_ind.relname::character varying(30) AS index_name,
    NULL::character varying(7) AS invalid,
    NULL::character varying(14) AS view_related
   FROM pg_constraint c
     JOIN pg_namespace n ON n.oid = c.connamespace
     JOIN pg_class cl ON cl.oid = c.conrelid
     JOIN pg_user u ON cl.relowner = u.usesysid
     LEFT JOIN pg_class cl_ref ON cl_ref.oid = c.confrelid
     LEFT JOIN pg_namespace n_ref ON n_ref.oid = cl_ref.relnamespace
     LEFT JOIN pg_constraint c_ref ON c_ref.conrelid = c.confrelid AND c_ref.contype = 'p'::"char"
     LEFT JOIN pg_index i ON i.indrelid = cl_ref.oid
     LEFT JOIN pg_class cl_ind ON cl_ind.oid = c.conindid
  WHERE u.usename = USER OR has_table_privilege((quote_ident(n_ref.nspname::text) || '.'::text) || quote_ident(cl.relname::text), 'SELECT,INSERT,UPDATE,DELETE,TRUNCATE,REFERENCES,TRIGGER'::text);
  
  CREATE OR REPLACE VIEW all_ind_columns
AS SELECT insp.nspname::character varying(128) AS index_owner,
    irel.relname::character varying(128) AS index_name,
    tnsp.nspname::character varying(128) AS table_owner,
    trel.relname::character varying(128) AS table_name,
    a.attname::character varying(4000) AS column_name,
    c.ordinality::integer AS column_position,
    COALESCE(information_schema._pg_char_max_length(information_schema._pg_truetypid(a.*, t.*), information_schema._pg_truetypmod(a.*, t.*)), information_schema._pg_numeric_precision(information_schema._pg_truetypid(a.*, t.*), information_schema._pg_truetypmod(a.*, t.*))) AS column_length,
    COALESCE(information_schema._pg_char_max_length(information_schema._pg_truetypid(a.*, t.*), information_schema._pg_truetypmod(a.*, t.*)), 0) AS char_length,
        CASE o.option::integer & 1
            WHEN 1 THEN 'DESC'::text
            ELSE 'ASC'::text
        END::character varying(4) AS descend,
    NULL::integer AS collated_column_id
   FROM pg_index i
     JOIN pg_class trel ON trel.oid = i.indrelid
     JOIN pg_namespace tnsp ON trel.relnamespace = tnsp.oid
     JOIN pg_class irel ON irel.oid = i.indexrelid
     JOIN pg_namespace insp ON irel.relnamespace = insp.oid
     CROSS JOIN LATERAL unnest(i.indkey) WITH ORDINALITY c(colnum, ordinality)
     LEFT JOIN LATERAL unnest(i.indoption) WITH ORDINALITY o(option, ordinality) ON c.ordinality = o.ordinality
     JOIN pg_attribute a ON trel.oid = a.attrelid AND a.attnum = c.colnum
     JOIN (pg_type t
     JOIN pg_namespace nt ON t.typnamespace = nt.oid) ON a.atttypid = t.oid
  WHERE pg_has_role(trel.relowner, 'USAGE'::text) OR has_table_privilege(trel.oid, 'SELECT, INSERT, UPDATE, DELETE, TRUNCATE, REFERENCES, TRIGGER'::text);
  
  CREATE OR REPLACE VIEW all_indexes
AS SELECT tnsp.nspname::character varying(128) AS owner,
    irel.relname::character varying(128) AS index_name,
        CASE
            WHEN i.indexprs IS NULL THEN 'NORMAL'::text
            ELSE 'FUNCTION-BASED NORMAL'::text
        END::character varying(27) AS index_type,
    tnsp.nspname::character varying(128) AS table_owner,
    trel.relname::character varying(128) AS table_name,
    'TABLE'::character varying(11) AS table_type,
        CASE
            WHEN i.indisunique THEN 'UNIQUE'::text
            ELSE 'NONUNIQUE'::text
        END::character varying(9) AS uniqueness,
    'DISABLED'::character varying(13) AS compression,
    NULL::integer AS prefix_length,
    ts.spcname::character varying(30) AS tablespace_name,
    NULL::integer AS ini_trans,
    NULL::integer AS max_trans,
    NULL::integer AS initial_extent,
    NULL::integer AS next_extent,
    NULL::integer AS min_extents,
    NULL::integer AS max_extents,
    NULL::integer AS pct_increase,
    NULL::integer AS pct_threshold,
    NULL::integer AS include_column,
    NULL::integer AS freelists,
    NULL::integer AS freelist_groups,
    NULL::integer AS pct_free,
        CASE trel.relpersistence
            WHEN 'p'::"char" THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS logging,
    NULL::integer AS blevel,
    NULL::integer AS leaf_blocks,
        CASE
            WHEN i.indisprimary THEN st.n_live_tup
            ELSE NULL::bigint
        END::integer AS distinct_keys,
    NULL::integer AS avg_leaf_blocks_per_key,
    NULL::integer AS avg_data_blocks_per_key,
    NULL::integer AS clustering_factor,
        CASE
            WHEN i.indisready THEN 'ENABLED'::text
            ELSE 'DISABLED'::text
        END::character varying(8) AS status,
    st.n_live_tup::integer AS num_rows,
    NULL::integer AS sample_size,
    COALESCE(st.last_autoanalyze, st.last_analyze)::date AS last_analyzed,
    NULL::character varying(40) AS degree,
    NULL::character varying(40) AS instances,
        CASE
            WHEN trel.relispartition THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS partitioned,
        CASE trel.relpersistence
            WHEN 't'::"char" THEN 'Y'::text
            ELSE 'N'::text
        END::character varying(1) AS temporary,
    'N'::character varying(1) AS generated,
    'N'::character varying(1) AS secondary,
    'DEFAULT'::character varying(7) AS buffer_pool,
    'DEFAULT'::character varying(7) AS flash_cache,
    'DEFAULT'::character varying(7) AS cell_flash_cache,
    'NO'::character varying(3) AS user_stats,
    NULL::character varying(15) AS duration,
    NULL::integer AS pct_direct_access,
    NULL::character varying(128) AS ityp_owner,
    NULL::character varying(128) AS ityp_name,
    NULL::character varying(1000) AS parameters,
    'NO'::character varying(3) AS global_stats,
    NULL::character varying(12) AS domidx_status,
    NULL::character varying(6) AS domidx_opstatus,
    NULL::character varying(8) AS funcidx_status,
    'NO'::character varying(3) AS join_index,
    'NO'::character varying(3) AS iot_redundant_pkey_elim,
    'NO'::character varying(3) AS dropped,
        CASE
            WHEN i.indisvalid THEN 'VISIBLE'::text
            ELSE 'INVISIBLE'::text
        END::character varying(9) AS visibility,
    NULL::character varying(14) AS domidx_management,
        CASE irel.relpages
            WHEN 0 THEN 'NO'::text
            ELSE 'YES'::text
        END::character varying(3) AS segment_created,
    'NO'::character varying(3) AS orphaned_entries,
        CASE
            WHEN i.indpred IS NULL THEN 'FULL'::text
            ELSE 'PARTIAL'::text
        END::character varying(7) AS indexing
   FROM pg_index i
     JOIN pg_class trel ON trel.oid = i.indrelid
     JOIN pg_namespace tnsp ON trel.relnamespace = tnsp.oid
     JOIN pg_class irel ON irel.oid = i.indexrelid
     JOIN pg_am am ON am.oid = irel.relam AND am.amname = 'btree'::name
     LEFT JOIN pg_tablespace ts ON ts.oid = irel.reltablespace
     LEFT JOIN pg_stat_all_tables st ON st.schemaname = tnsp.nspname AND st.relname = trel.relname
  WHERE pg_has_role(trel.relowner, 'USAGE'::text) OR has_table_privilege(trel.oid, 'SELECT, INSERT, UPDATE, DELETE, TRUNCATE, REFERENCES, TRIGGER'::text);


-- aws_oracle_ext.sys_all_objects source

CREATE OR REPLACE VIEW all_objects
AS SELECT n.nspname::character varying(30) AS owner,
    c.relname::character varying(30) AS object_name,
    NULL::character varying(30) AS subobject_name,
    c.oid::bigint AS object_id,
        CASE
            WHEN c.relkind = ANY (ARRAY['t'::"char", 'r'::"char"]) THEN c.oid::bigint
            ELSE NULL::bigint
        END AS data_object_id,
        CASE c.relkind
            WHEN 'r'::"char" THEN 'ordinary table'::character varying(17)
            WHEN 'i'::"char" THEN 'index'::character varying(17)
            WHEN 'S'::"char" THEN 'sequence'::character varying(17)
            WHEN 'v'::"char" THEN 'view'::character varying(17)
            WHEN 'c'::"char" THEN 'composite type'::character varying(17)
            WHEN 't'::"char" THEN 'TOAST table'::character varying(17)
            WHEN 'f'::"char" THEN 'foreign table'::character varying(17)
            ELSE NULL::character varying(17)
        END AS object_type,
    NULL::date AS created,
    NULL::date AS last_ddl_time,
    NULL::character varying(78) AS "timestamp",
    'VALID'::character varying(7) AS status,
        CASE c.relpersistence
            WHEN 't'::"char" THEN 'Y'::text
            ELSE 'N'::text
        END AS temporary,
    'N'::character varying(1) AS generated,
    'N'::character varying(1) AS secondary,
    c.reltablespace::integer AS namespace,
    NULL::character varying(30) AS edition_name
   FROM pg_class c
     JOIN pg_namespace n ON c.relnamespace = n.oid
     JOIN pg_user u ON c.relowner = u.usesysid
  WHERE (n.nspname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name])) AND (u.usename = USER OR (c.relkind = ANY (ARRAY['r'::"char", 'v'::"char"])) AND has_table_privilege((quote_ident(n.nspname::text) || '.'::text) || quote_ident(c.relname::text), 'SELECT,INSERT,UPDATE,DELETE,TRUNCATE,REFERENCES,TRIGGER'::text));


-- aws_oracle_ext.sys_all_policies source

CREATE OR REPLACE VIEW all_policies
AS SELECT p.schemaname::character varying(128) AS object_owner,
    p.tablename::character varying(128) AS object_name,
    'SYS_DEFAULT'::character varying(128) AS policy_group,
    p.policyname::character varying(128) AS policy_name,
    p.schemaname::character varying(128) AS pf_owner,
    NULL::character varying(128) AS package,
    NULL::character varying(128) AS function,
        CASE
            WHEN p.cmd = ANY (ARRAY['ALL'::text, 'SELECT'::text]) THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS sel,
        CASE
            WHEN p.cmd = ANY (ARRAY['ALL'::text, 'INSERT'::text]) THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS ins,
        CASE
            WHEN p.cmd = ANY (ARRAY['ALL'::text, 'UPDATE'::text]) THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS upd,
        CASE
            WHEN p.cmd = ANY (ARRAY['ALL'::text, 'DELETE'::text]) THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS del,
    'NO'::character varying(3) AS idx,
        CASE
            WHEN p.with_check IS NOT NULL THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS chk_option,
    'YES'::character varying(3) AS enable,
    'NO'::character varying(3) AS static_policy,
    'DYNAMIC'::character varying(24) AS policy_type,
    'NO'::character varying(3) AS long_predicate,
    'NO'::character varying(3) AS common,
    'NO'::character varying(3) AS inherited
   FROM pg_policies p
     JOIN pg_tables t ON t.schemaname = p.schemaname AND t.tablename = p.tablename
     JOIN pg_user u ON u.usename = t.tableowner
  WHERE pg_has_role(t.tableowner, 'USAGE'::text) OR has_table_privilege(u.usesysid, 'SELECT, INSERT, UPDATE, DELETE'::text);
  
  CREATE OR REPLACE FUNCTION has_column_privilege(
	schema_name name,
	table_name name,
	column_name name)
    RETURNS boolean
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE STRICT PARALLEL UNSAFE
AS $BODY$
begin
    return has_column_privilege(quote_ident($1 || '.'||$2), $3, 'SELECT,INSERT,UPDATE,REFERENCES');
    exception 
   		when others then return null;
end
$BODY$;

ALTER FUNCTION has_column_privilege(name, name, name)
    OWNER TO oms_owner;


CREATE OR REPLACE VIEW all_sequences
AS SELECT s.schemaname::character varying(30) AS sequence_owner,
    s.sequencename::character varying(30) AS sequence_name,
    s.min_value::numeric AS min_value,
    s.max_value::numeric AS max_value,
    s.increment_by::numeric AS increment_by,
        CASE s.cycle
            WHEN false THEN 'N'::character varying(1)
            WHEN true THEN 'Y'::character varying(1)
            ELSE NULL::character varying
        END AS cycle_flag,
    'N'::character varying(1) AS order_flag,
    s.cache_size,
    s.last_value AS last_number
   FROM pg_sequences s
     JOIN pg_class c ON c.relname = s.sequencename AND c.relkind = 'S'::"char"
     JOIN pg_user u ON c.relowner = u.usesysid
  WHERE u.usename = USER OR has_sequence_privilege((quote_ident(s.schemaname::text) || '.'::text) || quote_ident(s.sequencename::text), 'USAGE,SELECT,UPDATE'::text);


-- aws_oracle_ext.sys_all_source source

CREATE OR REPLACE VIEW all_source
AS SELECT n.nspowner::character varying(30) AS owner,
    s.name::character varying(30) AS name,
    s.type::character varying(12) AS type,
    generate_subscripts(s.text, 1)::numeric AS line,
    unnest(s.text)::character varying(4000) AS text
   FROM ( SELECT c.relnamespace AS nsp,
            t.tgname AS name,
            'TRIGGER'::text AS type,
            string_to_array(pg_get_triggerdef(t.oid), chr(10)) AS text
           FROM pg_trigger t
             JOIN pg_class c ON c.oid = t.tgrelid
             JOIN pg_user u ON u.usesysid = c.relowner
          WHERE u.usename = USER OR has_table_privilege(USER, c.oid, 'TRIGGER'::text)
        UNION ALL
         SELECT f.pronamespace AS nsp,
            f.proname AS name,
                CASE
                    WHEN f.prokind = 'f'::"char" THEN 'FUNCTION'::text
                    WHEN f.prokind = 'p'::"char" THEN 'PROCEDURE'::text
                    ELSE NULL::text
                END AS type,
            string_to_array(pg_get_functiondef(f.oid), chr(10)) AS text
           FROM pg_proc f
             JOIN pg_type ret ON f.prorettype = ret.oid
             JOIN pg_user u ON u.usesysid = f.proowner
          WHERE (f.prokind = ANY (ARRAY['f'::"char", 'p'::"char"])) AND (u.usename = USER OR has_function_privilege(USER, f.oid, 'EXECUTE'::text))
        UNION ALL
         SELECT t.typnamespace AS nsp,
            t.typname AS name,
            'TYPE'::text AS type,
                CASE
                    WHEN t.typtype = 'c'::"char" THEN ARRAY( SELECT (('CREATE OR REPLACE TYPE '::text || t.typname::text) || ' AS ('::text) || chr(10)
                    UNION ALL
                     SELECT (((a.attname::text || ' '::text) || format_type(a.atttypid, a.atttypmod)) ||
                            CASE
                                WHEN lead(a.attrelid, 1) OVER () IS NULL THEN ''::text
                                ELSE ','::text
                            END) || chr(10)
                       FROM pg_attribute a
                      WHERE a.attrelid = t.typrelid
                    UNION ALL
                     SELECT ');'::text)
                    WHEN t.typtype = 'd'::"char" THEN ARRAY( SELECT (('CREATE OR REPLACE DOMAIN '::text || t.typname::text) || ' AS '::text) || format_type(tt.oid, NULL::integer)
                       FROM pg_type tt
                      WHERE tt.typarray = t.typbasetype)
                    ELSE NULL::text[]
                END AS text
           FROM pg_type t
             JOIN pg_user u ON u.usesysid = t.typowner
          WHERE (t.typrelid = 0::oid OR ( SELECT c.relkind = 'c'::"char"
                   FROM pg_class c
                  WHERE c.oid = t.typrelid)) AND NOT (EXISTS ( SELECT 1
                   FROM pg_type el
                  WHERE el.oid = t.typelem AND el.typarray = t.oid)) AND (u.usename = USER OR has_type_privilege(USER, t.oid, 'USAGE'::text))) s
     JOIN pg_namespace n ON s.nsp = n.oid
  WHERE n.nspname <> 'pg_catalog'::name AND n.nspname <> 'information_schema'::name AND n.nspname !~ '^pg_toast'::text;


-- aws_oracle_ext.sys_all_tab_cols source

CREATE OR REPLACE VIEW all_tab_cols
AS SELECT n.nspname::character varying(30) AS owner,
    c.relname::character varying(30) AS table_name,
    a.attname::character varying(30) AS column_name,
    format_type(a.atttypid, a.atttypmod)::character varying(106) AS data_type,
    NULL::character varying(3) AS data_type_mod,
    NULL::character varying(30) AS data_type_owner,
    a.attlen::numeric AS data_length,
    NULL::numeric AS data_precision,
    NULL::numeric AS data_scale,
        CASE
            WHEN a.attnotnull THEN 'N'::character varying(1)
            ELSE 'Y'::character varying(1)
        END AS nullable,
    a.attnum::numeric AS column_id,
    NULL::numeric AS default_length,
    pg_get_expr(d.adbin, d.adrelid) AS data_default,
    s.n_distinct::numeric AS num_distinct,
    NULL::numeric AS low_value,
    NULL::numeric AS high_value,
    s.null_frac::numeric AS density,
    NULL::numeric AS num_nulls,
    NULL::numeric AS num_buckets,
    COALESCE(st.last_autoanalyze, st.last_analyze)::date AS last_analyzed,
    NULL::numeric AS sample_size,
    'NCHAR_CS'::character varying(44) AS character_set_name,
    NULL::numeric AS char_col_decl_length,
        CASE
            WHEN COALESCE(st.last_autoanalyze, st.last_analyze) IS NOT NULL THEN 'YES'::character varying(3)
            ELSE 'NO'::character varying(3)
        END AS global_stats,
    'NO'::character varying(3) AS user_stats,
    s.avg_width::numeric AS avg_col_len,
    information_schema._pg_char_max_length(information_schema._pg_truetypid(a.*, t.*), information_schema._pg_truetypmod(a.*, t.*)) AS char_length,
    NULL::character varying(1) AS char_used,
    NULL::character varying(3) AS v80_fmt_image,
    NULL::character varying(3) AS data_upgraded,
    'NO'::character varying(3) AS hidden_column,
    'NO'::character varying(3) AS virtual_column,
    a.attnum::numeric AS segment_column_id,
    a.attnum::numeric AS internal_column_id,
    'NONE'::character varying(4) AS histogram,
    a.attname::character varying(4000) AS qualified_col_name
   FROM pg_attribute a
     JOIN pg_class c ON c.oid = a.attrelid
     JOIN pg_user u ON c.relowner = u.usesysid
     JOIN pg_type t ON a.atttypid = t.oid
     LEFT JOIN pg_attrdef d ON c.oid = d.adrelid AND a.attnum = d.adnum
     JOIN pg_namespace n ON n.oid = c.relnamespace
     LEFT JOIN pg_stats s ON s.schemaname = n.nspname AND s.tablename = c.relname AND s.attname = a.attname
     LEFT JOIN pg_stat_user_tables st ON st.relid = c.oid
  WHERE a.attnum > 0 AND NOT a.attisdropped AND (c.relkind = ANY (ARRAY['r'::"char", 'v'::"char", 'f'::"char", 'p'::"char"])) AND (u.usename = USER OR has_column_privilege(n.nspname, c.relname, a.attname)) AND (n.nspname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name]));




CREATE OR REPLACE VIEW sys_all_tab_columns
AS SELECT n.nspname::character varying(30) AS owner,
    c.relname::character varying(30) AS table_name,
    a.attname::character varying(30) AS column_name,
    format_type(a.atttypid, a.atttypmod)::character varying(106) AS data_type,
    NULL::character varying(3) AS data_type_mod,
    NULL::character varying(30) AS data_type_owner,
    a.attlen::numeric AS data_length,
    NULL::numeric AS data_precision,
    NULL::numeric AS data_scale,
        CASE
            WHEN a.attnotnull THEN 'N'::character varying(1)
            ELSE 'Y'::character varying(1)
        END AS nullable,
    a.attnum::numeric AS column_id,
    NULL::numeric AS default_length,
    pg_get_expr(d.adbin, d.adrelid) AS data_default,
    s.n_distinct::numeric AS num_distinct,
    NULL::numeric AS low_value,
    NULL::numeric AS high_value,
    s.null_frac::numeric AS density,
    NULL::numeric AS num_nulls,
    NULL::numeric AS num_buckets,
    COALESCE(st.last_autoanalyze, st.last_analyze)::date AS last_analyzed,
    NULL::numeric AS sample_size,
    'NCHAR_CS'::character varying(44) AS character_set_name,
    NULL::numeric AS char_col_decl_length,
        CASE
            WHEN COALESCE(st.last_autoanalyze, st.last_analyze) IS NOT NULL THEN 'YES'::character varying(3)
            ELSE 'NO'::character varying(3)
        END AS global_stats,
    'NO'::character varying(3) AS user_stats,
    s.avg_width::numeric AS avg_col_len,
    information_schema._pg_char_max_length(information_schema._pg_truetypid(a.*, t.*), information_schema._pg_truetypmod(a.*, t.*)) AS char_length,
    NULL::character varying(1) AS char_used,
    NULL::character varying(3) AS v80_fmt_image,
    NULL::character varying(3) AS data_upgraded,
    'NONE'::character varying(4) AS histogram
   FROM pg_attribute a
     JOIN pg_class c ON c.oid = a.attrelid
     JOIN pg_user u ON c.relowner = u.usesysid
     LEFT JOIN pg_attrdef d ON c.oid = d.adrelid AND a.attnum = d.adnum
     JOIN pg_namespace n ON n.oid = c.relnamespace
     JOIN pg_type t ON a.atttypid = t.oid
     LEFT JOIN pg_stats s ON s.schemaname = n.nspname AND s.tablename = c.relname AND s.attname = a.attname
     LEFT JOIN pg_stat_user_tables st ON st.relid = c.oid
  WHERE a.attnum > 0 AND NOT a.attisdropped AND (c.relkind = ANY (ARRAY['r'::"char", 'v'::"char", 'f'::"char", 'p'::"char"])) AND (u.usename = USER OR has_column_privilege(n.nspname, c.relname, a.attname)) AND (n.nspname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name]));

CREATE OR REPLACE VIEW all_tab_comments
AS SELECT n.nspname::character varying(30) AS owner,
    c.relname::character varying(30) AS table_name,
        CASE
            WHEN c.relkind = 'r'::"char" THEN 'TABLE'::text
            WHEN c.relkind = 'v'::"char" THEN 'VIEW'::text
            ELSE NULL::text
        END AS table_type,
    obj_description(c.oid)::character varying(4000) AS comments,
    0::numeric AS origin_con_id
   FROM pg_class c
     JOIN pg_namespace n ON c.relnamespace = n.oid
  WHERE (c.relkind = ANY (ARRAY['r'::"char", 'v'::"char"])) AND (n.nspname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name])) AND has_table_privilege((quote_ident(n.nspname::text) || '.'::text) || quote_ident(c.relname::text), 'SELECT,INSERT,UPDATE,DELETE,TRUNCATE,REFERENCES,TRIGGER'::text);


-- aws_oracle_ext.sys_all_tables source

CREATE OR REPLACE VIEW all_tables
AS SELECT t.schemaname::character varying(30) AS owner,
    t.tablename::character varying(30) AS table_name,
    t.tablespace::character varying(30) AS tablespace_name,
    NULL::character varying(30) AS cluster_name,
    NULL::character varying(30) AS iot_name,
    'VALID'::character varying(8) AS status,
    NULL::numeric AS pct_free,
    NULL::numeric AS pct_used,
    NULL::numeric AS ini_trans,
    NULL::numeric AS max_trans,
    NULL::numeric AS initial_extent,
    NULL::numeric AS next_extent,
    NULL::numeric AS min_extents,
    NULL::numeric AS max_extents,
    NULL::numeric AS pct_increase,
    NULL::numeric AS freelists,
    NULL::numeric AS freelist_groups,
    'NO'::character varying(3) AS logging,
    'N'::character varying(1) AS backed_up,
    stat.n_live_tup AS num_rows,
    NULL::numeric AS blocks,
    NULL::numeric AS empty_blocks,
    NULL::numeric AS avg_space,
    NULL::numeric AS chain_cnt,
    NULL::numeric AS avg_row_len,
    NULL::numeric AS avg_space_freelist_blocks,
    NULL::numeric AS num_freelist_blocks,
    NULL::character varying(10) AS degree,
    NULL::character varying(10) AS instances,
    NULL::character varying(5) AS cache,
    NULL::character varying(8) AS table_lock,
    NULL::numeric AS sample_size,
    COALESCE(stat.last_autoanalyze, stat.last_analyze)::date AS last_analyzed,
    'NO'::character varying(3) AS partitioned,
    NULL::character varying(12) AS iot_type,
        CASE
            WHEN n.nspname !~ '^pg_toast'::text AND n.nspname ~~ 'pg_temp%'::text THEN 'Y'::text
            ELSE 'N'::text
        END AS temporary,
    'N'::character varying(1) AS secondary,
    'NO'::character varying(3) AS nested,
    'DEFAULT'::character varying(7) AS buffer_pool,
    'DEFAULT'::character varying(7) AS flash_cache,
    'DEFAULT'::character varying(7) AS cell_flash_cache,
    'DISABLED'::character varying(8) AS row_movement,
    'NO'::character varying(3) AS global_stats,
    NULL::character varying(3) AS user_stats,
    NULL::character varying(15) AS duration,
    'DISABLED'::character varying(8) AS skip_corrupt,
    'NO'::character varying(3) AS monitoring,
    NULL::character varying(30) AS cluster_owner,
    'DISABLED'::character varying(8) AS dependencies,
    'DISABLED'::character varying(8) AS compression,
    NULL::character varying(12) AS compress_for,
    'NO'::character varying(3) AS dropped,
    NULL::character varying(3) AS read_only,
    'YES'::character varying(3) AS segment_created,
    'DEFAULT'::character varying(7) AS result_cache
   FROM pg_tables t
     JOIN pg_stat_user_tables stat ON t.schemaname = stat.schemaname AND t.tablename = stat.relname
     JOIN pg_namespace n ON t.schemaname = n.nspname
  WHERE (t.schemaname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name])) AND (t.tableowner = USER OR has_table_privilege((quote_ident(t.schemaname::text) || '.'::text) || quote_ident(t.tablename::text), 'SELECT,INSERT,UPDATE,DELETE,TRUNCATE,REFERENCES,TRIGGER'::text));




CREATE OR REPLACE VIEW all_triggers
AS SELECT n.nspname::character varying(128) AS owner,
    trg.tgname::character varying(128) AS trigger_name,
        CASE trg.tgtype::integer & 66
            WHEN 2 THEN 'BEFORE'::text ||
            CASE trg.tgtype::integer & 1
                WHEN 1 THEN ' EACH ROW'::text
                ELSE 'STATEMENT'::text
            END
            WHEN 64 THEN 'INSTEAD OF'::text
            ELSE 'AFTER'::text ||
            CASE trg.tgtype::integer & 1
                WHEN 1 THEN ' EACH ROW'::text
                ELSE 'STATEMENT'::text
            END
        END::character varying(16) AS trigger_type,
        CASE trg.tgtype::integer & 28::smallint::integer
            WHEN 32 THEN 'TRUNCATE'::text
            WHEN 16 THEN 'UPDATE'::text
            WHEN 8 THEN 'DELETE'::text
            WHEN 4 THEN 'INSERT'::text
            WHEN 20 THEN 'INSERT OR UPDATE'::text
            WHEN 28 THEN 'INSERT OR UPDATE OR DELETE'::text
            WHEN 24 THEN 'UPDATE OR DELETE'::text
            WHEN 12 THEN 'INSERT OR DELETE'::text
            ELSE NULL::text
        END::character varying(246) AS triggering_event,
    ns.nspname::character varying(128) AS table_owner,
        CASE trg.tgtype::integer & 66
            WHEN 64 THEN 'VIEW'::text
            ELSE 'TABLE'::text
        END::character varying(18) AS base_object_type,
    tbl.relname::character varying(128) AS table_name,
    ((( SELECT string_agg(a.attname::text, ', '::text) AS string_agg
           FROM pg_attribute a
          WHERE a.attrelid = tbl.oid AND (a.attnum = ANY (trg.tgattr::smallint[])))))::character varying(4000) AS column_name,
    'REFERENCING NEW AS NEW OLD AS OLD'::character varying(422) AS referencing_names,
        CASE
            WHEN pg_has_role(tbl.relowner, 'USAGE'::text) THEN (regexp_match(pg_get_triggerdef(trg.oid), '.{35,} WHEN \((.+)\) EXECUTE PROCEDURE'::text))[1]
            ELSE NULL::text
        END::character varying(4000) AS when_clause,
        CASE
            WHEN trg.tgenabled = 'D'::"char" THEN 'DISABLED'::text
            ELSE 'ENABLED'::text
        END::character varying(8) AS status,
    obj_description(trg.oid)::character varying(400) AS description,
    'PL/SQL'::character varying(11) AS action_type,
    (n.nspname::text || '.'::text) || proc.proname::text AS trigger_body,
    'NO'::character varying(3) AS crossedition,
    'NO'::character varying(3) AS before_statement,
    'NO'::character varying(3) AS before_row,
    'NO'::character varying(3) AS after_row,
    'NO'::character varying(3) AS after_statement,
    'NO'::character varying(3) AS instead_of_row,
        CASE trg.tgtype::integer & 66
            WHEN 2 THEN 'YES'::text
            WHEN 64 THEN 'NO'::text
            ELSE 'YES'::text
        END::character varying(3) AS fire_once,
    'NO'::character varying(3) AS apply_server_only
   FROM pg_trigger trg
     JOIN pg_proc proc ON proc.oid = trg.tgfoid
     JOIN pg_namespace n ON n.oid = proc.pronamespace
     JOIN pg_class tbl ON trg.tgrelid = tbl.oid
     JOIN pg_namespace ns ON ns.oid = tbl.relnamespace
  WHERE NOT trg.tgisinternal AND (pg_has_role(tbl.relowner, 'USAGE'::text) OR has_table_privilege(tbl.oid, 'INSERT, UPDATE, DELETE, TRUNCATE, REFERENCES, TRIGGER'::text) OR has_any_column_privilege(tbl.oid, 'INSERT, UPDATE, REFERENCES'::text));
  
  CREATE OR REPLACE VIEW all_views
AS SELECT pg_views.schemaname::character varying(30) AS owner,
    pg_views.viewname::character varying(30) AS view_name,
    length(pg_views.definition)::numeric AS text_length,
    pg_views.definition::character varying(409600) AS text,
    NULL::numeric AS type_text_length,
    NULL::character varying(4000) AS type_text,
    NULL::numeric AS oid_text_length,
    NULL::character varying(4000) AS oid_text,
    NULL::character varying(30) AS view_type_owner,
    NULL::character varying(30) AS view_type,
    NULL::character varying(30) AS superview_name,
    'N'::character varying(1) AS editioning_view,
    'Y'::character varying(1) AS read_only
   FROM pg_views
  WHERE (pg_views.schemaname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name])) AND (pg_views.viewowner = USER OR has_table_privilege((quote_ident(pg_views.schemaname::text) || '.'::text) || quote_ident(pg_views.viewname::text), 'SELECT,INSERT,UPDATE,DELETE,REFERENCES,TRIGGER'::text));


-- aws_oracle_ext.sys_dba_cons_columns source

CREATE OR REPLACE VIEW dba_cons_columns
AS SELECT x.cstrschema::character varying(128) AS owner,
    x.cstrname::character varying(128) AS constraint_name,
    x.tblname::character varying(128) AS table_name,
    x.colname::character varying(4000) AS column_name,
    rank() OVER (PARTITION BY x.cstrschema, x.tblname, x.cstrname ORDER BY x.colnum)::smallint AS "position"
   FROM ( SELECT DISTINCT nr.nspname,
            r.relname,
            r.relowner,
            a.attname,
            a.attnum,
            nc.nspname,
            c.conname
           FROM pg_namespace nr,
            pg_class r,
            pg_attribute a,
            pg_depend d,
            pg_namespace nc,
            pg_constraint c
          WHERE nr.oid = r.relnamespace AND r.oid = a.attrelid AND d.refclassid = 'pg_class'::regclass::oid AND d.refobjid = r.oid AND d.refobjsubid = a.attnum AND d.classid = 'pg_constraint'::regclass::oid AND d.objid = c.oid AND c.connamespace = nc.oid AND c.contype = 'c'::"char" AND (r.relkind = ANY (ARRAY['r'::"char", 'p'::"char"])) AND NOT a.attisdropped
        UNION ALL
         SELECT nr.nspname,
            r.relname,
            r.relowner,
            a.attname,
            a.attnum,
            nc.nspname,
            c.conname
           FROM pg_namespace nr,
            pg_class r,
            pg_attribute a,
            pg_namespace nc,
            pg_constraint c
          WHERE nr.oid = r.relnamespace AND r.oid = a.attrelid AND nc.oid = c.connamespace AND r.oid =
                CASE c.contype
                    WHEN 'f'::"char" THEN c.confrelid
                    ELSE c.conrelid
                END AND (a.attnum = ANY (
                CASE c.contype
                    WHEN 'f'::"char" THEN c.confkey
                    ELSE c.conkey
                END)) AND NOT a.attisdropped AND (c.contype = ANY (ARRAY['p'::"char", 'u'::"char", 'f'::"char"])) AND (r.relkind = ANY (ARRAY['r'::"char", 'p'::"char"]))) x(tblschema, tblname, tblowner, colname, colnum, cstrschema, cstrname)
  WHERE ( SELECT pg_user.usesuper
           FROM pg_user
          WHERE pg_user.usename = USER) OR pg_has_role('rds_superuser'::name, 'member'::text);


-- aws_oracle_ext.sys_dba_constraints source

CREATE OR REPLACE VIEW dba_constraints
AS SELECT n.nspname::character varying(30) AS owner,
    c.conname::character varying(30) AS constraint_name,
    c.contype::character varying(1) AS constraint_type,
    cl.relname::character varying(30) AS table_name,
    pg_get_constraintdef(c.oid) AS search_condition,
    n_ref.nspname::character varying(30) AS r_owner,
    c_ref.conname::character varying(30) AS r_constraint_name,
        CASE c.confdeltype
            WHEN 'a'::"char" THEN 'no action'::character varying(9)
            WHEN 'r'::"char" THEN 'restrict'::character varying(9)
            WHEN 'c'::"char" THEN 'cascade'::character varying(9)
            WHEN 'n'::"char" THEN 'set null'::character varying(9)
            WHEN 'd'::"char" THEN 'set default'::character varying(11)
            ELSE NULL::character varying(9)
        END AS delete_rule,
    'ENABLED'::character varying(8) AS status,
        CASE
            WHEN c.condeferrable THEN 'DEFERRABLE'::character varying(14)
            ELSE 'NOT DEFERRABLE'::character varying(14)
        END AS "deferrable",
        CASE
            WHEN c.condeferred THEN 'DEFERRED'::character varying(9)
            ELSE 'IMMEDIATE'::character varying(9)
        END AS deferred,
        CASE
            WHEN c.convalidated THEN 'VALIDATED'::character varying(13)
            ELSE 'NOT VALIDATED'::character varying(13)
        END AS validated,
    'GENERATED NAME'::character varying(14) AS generated,
    NULL::character varying(3) AS bad,
    NULL::character varying(4) AS rely,
    NULL::date AS last_change,
    n.nspname::character varying(30) AS index_owner,
    cl_ind.relname::character varying(30) AS index_name,
    NULL::character varying(7) AS invalid,
    NULL::character varying(14) AS view_related
   FROM pg_constraint c
     JOIN pg_namespace n ON n.oid = c.connamespace
     JOIN pg_class cl ON cl.oid = c.conrelid
     LEFT JOIN pg_class cl_ref ON cl_ref.oid = c.confrelid
     LEFT JOIN pg_namespace n_ref ON n_ref.oid = cl_ref.relnamespace
     LEFT JOIN pg_constraint c_ref ON c_ref.conrelid = c.confrelid AND c_ref.contype = 'p'::"char"
     LEFT JOIN pg_index i ON i.indrelid = cl_ref.oid
     LEFT JOIN pg_class cl_ind ON cl_ind.oid = c.conindid
  WHERE ( SELECT pg_user.usesuper
           FROM pg_user
          WHERE pg_user.usename = USER) OR pg_has_role('rds_superuser'::name, 'member'::text);


-- aws_oracle_ext.sys_dba_ind_columns source

CREATE OR REPLACE VIEW dba_ind_columns
AS SELECT insp.nspname::character varying(128) AS index_owner,
    irel.relname::character varying(128) AS index_name,
    tnsp.nspname::character varying(128) AS table_owner,
    trel.relname::character varying(128) AS table_name,
    a.attname::character varying(4000) AS column_name,
    c.ordinality::integer AS column_position,
    COALESCE(information_schema._pg_char_max_length(information_schema._pg_truetypid(a.*, t.*), information_schema._pg_truetypmod(a.*, t.*)), information_schema._pg_numeric_precision(information_schema._pg_truetypid(a.*, t.*), information_schema._pg_truetypmod(a.*, t.*))) AS column_length,
    COALESCE(information_schema._pg_char_max_length(information_schema._pg_truetypid(a.*, t.*), information_schema._pg_truetypmod(a.*, t.*)), 0) AS char_length,
        CASE o.option::integer & 1
            WHEN 1 THEN 'DESC'::text
            ELSE 'ASC'::text
        END::character varying(4) AS descend,
    NULL::integer AS collated_column_id
   FROM pg_index i
     JOIN pg_class trel ON trel.oid = i.indrelid
     JOIN pg_namespace tnsp ON trel.relnamespace = tnsp.oid
     JOIN pg_class irel ON irel.oid = i.indexrelid
     JOIN pg_namespace insp ON irel.relnamespace = insp.oid
     CROSS JOIN LATERAL unnest(i.indkey) WITH ORDINALITY c(colnum, ordinality)
     LEFT JOIN LATERAL unnest(i.indoption) WITH ORDINALITY o(option, ordinality) ON c.ordinality = o.ordinality
     JOIN pg_attribute a ON trel.oid = a.attrelid AND a.attnum = c.colnum
     JOIN (pg_type t
     JOIN pg_namespace nt ON t.typnamespace = nt.oid) ON a.atttypid = t.oid
  WHERE ( SELECT pg_user.usesuper
           FROM pg_user
          WHERE pg_user.usename = USER) OR pg_has_role('rds_superuser'::name, 'member'::text);


-- aws_oracle_ext.sys_dba_indexes source

CREATE OR REPLACE VIEW dba_indexes
AS SELECT tnsp.nspname::character varying(128) AS owner,
    irel.relname::character varying(128) AS index_name,
        CASE
            WHEN i.indexprs IS NULL THEN 'NORMAL'::text
            ELSE 'FUNCTION-BASED NORMAL'::text
        END::character varying(27) AS index_type,
    tnsp.nspname::character varying(128) AS table_owner,
    trel.relname::character varying(128) AS table_name,
    'TABLE'::character varying(11) AS table_type,
        CASE
            WHEN i.indisunique THEN 'UNIQUE'::text
            ELSE 'NONUNIQUE'::text
        END::character varying(9) AS uniqueness,
    'DISABLED'::character varying(13) AS compression,
    NULL::integer AS prefix_length,
    ts.spcname::character varying(30) AS tablespace_name,
    NULL::integer AS ini_trans,
    NULL::integer AS max_trans,
    NULL::integer AS initial_extent,
    NULL::integer AS next_extent,
    NULL::integer AS min_extents,
    NULL::integer AS max_extents,
    NULL::integer AS pct_increase,
    NULL::integer AS pct_threshold,
    NULL::integer AS include_column,
    NULL::integer AS freelists,
    NULL::integer AS freelist_groups,
    NULL::integer AS pct_free,
        CASE trel.relpersistence
            WHEN 'p'::"char" THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS logging,
    NULL::integer AS blevel,
    NULL::integer AS leaf_blocks,
        CASE
            WHEN i.indisprimary THEN st.n_live_tup
            ELSE NULL::bigint
        END::integer AS distinct_keys,
    NULL::integer AS avg_leaf_blocks_per_key,
    NULL::integer AS avg_data_blocks_per_key,
    NULL::integer AS clustering_factor,
        CASE
            WHEN i.indisready THEN 'ENABLED'::text
            ELSE 'DISABLED'::text
        END::character varying(8) AS status,
    st.n_live_tup::integer AS num_rows,
    NULL::integer AS sample_size,
    COALESCE(st.last_autoanalyze, st.last_analyze)::date AS last_analyzed,
    NULL::character varying(40) AS degree,
    NULL::character varying(40) AS instances,
        CASE
            WHEN trel.relispartition THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS partitioned,
        CASE trel.relpersistence
            WHEN 't'::"char" THEN 'Y'::text
            ELSE 'N'::text
        END::character varying(1) AS temporary,
    'N'::character varying(1) AS generated,
    'N'::character varying(1) AS secondary,
    'DEFAULT'::character varying(7) AS buffer_pool,
    'DEFAULT'::character varying(7) AS flash_cache,
    'DEFAULT'::character varying(7) AS cell_flash_cache,
    'NO'::character varying(3) AS user_stats,
    NULL::character varying(15) AS duration,
    NULL::integer AS pct_direct_access,
    NULL::character varying(128) AS ityp_owner,
    NULL::character varying(128) AS ityp_name,
    NULL::character varying(1000) AS parameters,
    'NO'::character varying(3) AS global_stats,
    NULL::character varying(12) AS domidx_status,
    NULL::character varying(6) AS domidx_opstatus,
    NULL::character varying(8) AS funcidx_status,
    'NO'::character varying(3) AS join_index,
    'NO'::character varying(3) AS iot_redundant_pkey_elim,
    'NO'::character varying(3) AS dropped,
        CASE
            WHEN i.indisvalid THEN 'VISIBLE'::text
            ELSE 'INVISIBLE'::text
        END::character varying(9) AS visibility,
    NULL::character varying(14) AS domidx_management,
        CASE irel.relpages
            WHEN 0 THEN 'NO'::text
            ELSE 'YES'::text
        END::character varying(3) AS segment_created,
    'NO'::character varying(3) AS orphaned_entries,
        CASE
            WHEN i.indpred IS NULL THEN 'FULL'::text
            ELSE 'PARTIAL'::text
        END::character varying(7) AS indexing
   FROM pg_index i
     JOIN pg_class trel ON trel.oid = i.indrelid
     JOIN pg_namespace tnsp ON trel.relnamespace = tnsp.oid
     JOIN pg_class irel ON irel.oid = i.indexrelid
     JOIN pg_am am ON am.oid = irel.relam AND am.amname = 'btree'::name
     LEFT JOIN pg_tablespace ts ON ts.oid = irel.reltablespace
     LEFT JOIN pg_stat_all_tables st ON st.schemaname = tnsp.nspname AND st.relname = trel.relname
  WHERE ( SELECT pg_user.usesuper
           FROM pg_user
          WHERE pg_user.usename = USER) OR pg_has_role('rds_superuser'::name, 'member'::text);


-- aws_oracle_ext.sys_dba_objects source

CREATE OR REPLACE VIEW dba_objects
AS SELECT n.nspname::character varying(30) AS owner,
    c.relname::character varying(30) AS object_name,
    NULL::character varying(30) AS subobject_name,
    c.oid::bigint AS object_id,
        CASE
            WHEN c.relkind = ANY (ARRAY['t'::"char", 'r'::"char"]) THEN c.oid::bigint
            ELSE NULL::bigint
        END AS data_object_id,
        CASE c.relkind
            WHEN 'r'::"char" THEN 'ordinary table'::character varying(17)
            WHEN 'i'::"char" THEN 'index'::character varying(17)
            WHEN 'S'::"char" THEN 'sequence'::character varying(17)
            WHEN 'v'::"char" THEN 'view'::character varying(17)
            WHEN 'c'::"char" THEN 'composite type'::character varying(17)
            WHEN 't'::"char" THEN 'TOAST table'::character varying(17)
            WHEN 'f'::"char" THEN 'foreign table'::character varying(17)
            ELSE NULL::character varying(17)
        END AS object_type,
    NULL::date AS created,
    NULL::date AS last_ddl_time,
    NULL::character varying(78) AS "timestamp",
    'VALID'::character varying(7) AS status,
        CASE c.relpersistence
            WHEN 't'::"char" THEN 'Y'::text
            ELSE 'N'::text
        END AS temporary,
    'N'::character varying(1) AS generated,
    'N'::character varying(1) AS secondary,
    c.reltablespace::integer AS namespace,
    NULL::character varying(30) AS edition_name
   FROM pg_class c
     JOIN pg_namespace n ON c.relnamespace = n.oid
  WHERE (( SELECT pg_user.usesuper
           FROM pg_user
          WHERE pg_user.usename = USER) OR pg_has_role('rds_superuser'::name, 'member'::text)) AND (n.nspname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name]));


-- aws_oracle_ext.sys_dba_policies source

CREATE OR REPLACE VIEW dba_policies
AS SELECT p.schemaname::character varying(128) AS object_owner,
    p.tablename::character varying(128) AS object_name,
    'SYS_DEFAULT'::character varying(128) AS policy_group,
    p.policyname::character varying(128) AS policy_name,
    p.schemaname::character varying(128) AS pf_owner,
    NULL::character varying(128) AS package,
    NULL::character varying(128) AS function,
        CASE
            WHEN p.cmd = ANY (ARRAY['ALL'::text, 'SELECT'::text]) THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS sel,
        CASE
            WHEN p.cmd = ANY (ARRAY['ALL'::text, 'INSERT'::text]) THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS ins,
        CASE
            WHEN p.cmd = ANY (ARRAY['ALL'::text, 'UPDATE'::text]) THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS upd,
        CASE
            WHEN p.cmd = ANY (ARRAY['ALL'::text, 'DELETE'::text]) THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS del,
    'NO'::character varying(3) AS idx,
        CASE
            WHEN p.with_check IS NOT NULL THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS chk_option,
    'YES'::character varying(3) AS enable,
    'NO'::character varying(3) AS static_policy,
    'DYNAMIC'::character varying(24) AS policy_type,
    'NO'::character varying(3) AS long_predicate,
    'NO'::character varying(3) AS common,
    'NO'::character varying(3) AS inherited
   FROM pg_policies p
  WHERE ( SELECT pg_user.usesuper
           FROM pg_user
          WHERE pg_user.usename = USER) OR pg_has_role('rds_superuser'::name, 'member'::text);

CREATE OR REPLACE VIEW dba_roles
AS SELECT r.rolname::character varying(128) AS role,
    u.usesysid::bigint AS role_id,
        CASE COALESCE(r.rolpassword, ''::text)
            WHEN ''::text THEN 'NO'::text
            ELSE 'YES'::text
        END::character varying(8) AS password_required,
        CASE COALESCE(r.rolpassword, ''::text)
            WHEN ''::text THEN 'NONE'::text
            ELSE 'PASSWORD'::text
        END::character varying(11) AS authentication_type,
    'NO'::character varying(3) AS common,
    'N'::character varying(1) AS oracle_maintained,
    'NO'::character varying(3) AS inherited,
    'NO'::character varying(3) AS implicit
   FROM pg_authid r
     JOIN pg_user u ON u.usename = r.rolname;


-- aws_oracle_ext.sys_dba_sequences source

CREATE OR REPLACE VIEW dba_sequences
AS SELECT s.schemaname::character varying(30) AS sequence_owner,
    s.sequencename::character varying(30) AS sequence_name,
    s.min_value::numeric AS min_value,
    s.max_value::numeric AS max_value,
    s.increment_by::numeric AS increment_by,
        CASE s.cycle
            WHEN false THEN 'N'::character varying(1)
            WHEN true THEN 'Y'::character varying(1)
            ELSE NULL::character varying
        END AS cycle_flag,
    'N'::character varying(1) AS order_flag,
    s.cache_size,
    s.last_value AS last_number
   FROM pg_sequences s
     JOIN pg_class c ON c.relname = s.sequencename AND c.relkind = 'S'::"char"
     JOIN pg_user u ON c.relowner = u.usesysid
  WHERE ( SELECT pg_user.usesuper
           FROM pg_user
          WHERE pg_user.usename = USER) OR pg_has_role('rds_superuser'::name, 'member'::text);


-- aws_oracle_ext.sys_dba_source source

CREATE OR REPLACE VIEW dba_source
AS SELECT n.nspowner::character varying(30) AS owner,
    s.name::character varying(30) AS name,
    s.type::character varying(12) AS type,
    generate_subscripts(s.text, 1)::numeric AS line,
    unnest(s.text)::character varying(4000) AS text
   FROM ( SELECT c.relnamespace AS nsp,
            t.tgname AS name,
            'TRIGGER'::text AS type,
            string_to_array(pg_get_triggerdef(t.oid), chr(10)) AS text
           FROM pg_trigger t
             JOIN pg_class c ON c.oid = t.tgrelid
             JOIN pg_user u ON u.usesysid = c.relowner
        UNION ALL
         SELECT f.pronamespace AS nsp,
            f.proname AS name,
                CASE
                    WHEN f.prokind = 'f'::"char" THEN 'FUNCTION'::text
                    WHEN f.prokind = 'p'::"char" THEN 'PROCEDURE'::text
                    ELSE NULL::text
                END AS type,
            string_to_array(pg_get_functiondef(f.oid), chr(10)) AS text
           FROM pg_proc f
             JOIN pg_type ret ON f.prorettype = ret.oid
             JOIN pg_user u ON u.usesysid = f.proowner
          WHERE f.prokind = ANY (ARRAY['f'::"char", 'p'::"char"])
        UNION ALL
         SELECT t.typnamespace AS nsp,
            t.typname AS name,
            'TYPE'::text AS type,
                CASE
                    WHEN t.typtype = 'c'::"char" THEN ARRAY( SELECT (('CREATE OR REPLACE TYPE '::text || t.typname::text) || ' AS ('::text) || chr(10)
                    UNION ALL
                     SELECT (((a.attname::text || ' '::text) || format_type(a.atttypid, a.atttypmod)) ||
                            CASE
                                WHEN lead(a.attrelid, 1) OVER () IS NULL THEN ''::text
                                ELSE ','::text
                            END) || chr(10)
                       FROM pg_attribute a
                      WHERE a.attrelid = t.typrelid
                    UNION ALL
                     SELECT ');'::text)
                    WHEN t.typtype = 'd'::"char" THEN ARRAY( SELECT (('CREATE OR REPLACE DOMAIN '::text || t.typname::text) || ' AS '::text) || format_type(tt.oid, NULL::integer)
                       FROM pg_type tt
                      WHERE tt.typarray = t.typbasetype)
                    ELSE NULL::text[]
                END AS text
           FROM pg_type t
             JOIN pg_user u ON u.usesysid = t.typowner
          WHERE (t.typrelid = 0::oid OR ( SELECT c.relkind = 'c'::"char"
                   FROM pg_class c
                  WHERE c.oid = t.typrelid)) AND NOT (EXISTS ( SELECT 1
                   FROM pg_type el
                  WHERE el.oid = t.typelem AND el.typarray = t.oid))) s
     JOIN pg_namespace n ON s.nsp = n.oid
  WHERE n.nspname <> 'pg_catalog'::name AND n.nspname <> 'information_schema'::name AND n.nspname !~ '^pg_toast'::text;


-- aws_oracle_ext.sys_dba_tab_cols source

CREATE OR REPLACE VIEW dba_tab_cols
AS SELECT n.nspname::character varying(30) AS owner,
    c.relname::character varying(30) AS table_name,
    a.attname::character varying(30) AS column_name,
    format_type(a.atttypid, a.atttypmod)::character varying(106) AS data_type,
    NULL::character varying(3) AS data_type_mod,
    NULL::character varying(30) AS data_type_owner,
    a.attlen::numeric AS data_length,
    NULL::numeric AS data_precision,
    NULL::numeric AS data_scale,
        CASE
            WHEN a.attnotnull THEN 'N'::character varying(1)
            ELSE 'Y'::character varying(1)
        END AS nullable,
    a.attnum::numeric AS column_id,
    NULL::numeric AS default_length,
    pg_get_expr(d.adbin, d.adrelid) AS data_default,
    s.n_distinct::numeric AS num_distinct,
    NULL::numeric AS low_value,
    NULL::numeric AS high_value,
    s.null_frac::numeric AS density,
    NULL::numeric AS num_nulls,
    NULL::numeric AS num_buckets,
    COALESCE(st.last_autoanalyze, st.last_analyze)::date AS last_analyzed,
    NULL::numeric AS sample_size,
    'NCHAR_CS'::character varying(44) AS character_set_name,
    NULL::numeric AS char_col_decl_length,
        CASE
            WHEN COALESCE(st.last_autoanalyze, st.last_analyze) IS NOT NULL THEN 'YES'::character varying(3)
            ELSE 'NO'::character varying(3)
        END AS global_stats,
    'NO'::character varying(3) AS user_stats,
    s.avg_width::numeric AS avg_col_len,
    information_schema._pg_char_max_length(information_schema._pg_truetypid(a.*, t.*), information_schema._pg_truetypmod(a.*, t.*)) AS char_length,
    NULL::character varying(1) AS char_used,
    NULL::character varying(3) AS v80_fmt_image,
    NULL::character varying(3) AS data_upgraded,
    'NO'::character varying(3) AS hidden_column,
    'NO'::character varying(3) AS virtual_column,
    a.attnum::numeric AS segment_column_id,
    a.attnum::numeric AS internal_column_id,
    'NONE'::character varying(4) AS histogram,
    a.attname::character varying(4000) AS qualified_col_name
   FROM pg_attribute a
     JOIN pg_class c ON c.oid = a.attrelid
     JOIN pg_type t ON a.atttypid = t.oid
     LEFT JOIN pg_attrdef d ON c.oid = d.adrelid AND a.attnum = d.adnum
     JOIN pg_namespace n ON n.oid = c.relnamespace
     LEFT JOIN pg_stats s ON s.schemaname = n.nspname AND s.tablename = c.relname AND s.attname = a.attname
     LEFT JOIN pg_stat_user_tables st ON st.relid = c.oid
  WHERE a.attnum > 0 AND NOT a.attisdropped AND (c.relkind = ANY (ARRAY['r'::"char", 'v'::"char", 'f'::"char", 'p'::"char"])) AND (( SELECT pg_user.usesuper
           FROM pg_user
          WHERE pg_user.usename = USER) OR pg_has_role('rds_superuser'::name, 'member'::text)) AND (n.nspname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name]));


-- aws_oracle_ext.sys_dba_tab_columns source

CREATE OR REPLACE VIEW dba_tab_columns
AS SELECT n.nspname::character varying(30) AS owner,
    c.relname::character varying(30) AS table_name,
    a.attname::character varying(30) AS column_name,
    format_type(a.atttypid, a.atttypmod)::character varying(106) AS data_type,
    NULL::character varying(3) AS data_type_mod,
    NULL::character varying(30) AS data_type_owner,
    a.attlen::numeric AS data_length,
    NULL::numeric AS data_precision,
    NULL::numeric AS data_scale,
        CASE
            WHEN a.attnotnull THEN 'N'::character varying(1)
            ELSE 'Y'::character varying(1)
        END AS nullable,
    a.attnum::numeric AS column_id,
    NULL::numeric AS default_length,
    pg_get_expr(d.adbin, d.adrelid) AS data_default,
    s.n_distinct::numeric AS num_distinct,
    NULL::numeric AS low_value,
    NULL::numeric AS high_value,
    s.null_frac::numeric AS density,
    NULL::numeric AS num_nulls,
    NULL::numeric AS num_buckets,
    COALESCE(st.last_autoanalyze, st.last_analyze)::date AS last_analyzed,
    NULL::numeric AS sample_size,
    'NCHAR_CS'::character varying(44) AS character_set_name,
    NULL::numeric AS char_col_decl_length,
        CASE
            WHEN COALESCE(st.last_autoanalyze, st.last_analyze) IS NOT NULL THEN 'YES'::character varying(3)
            ELSE 'NO'::character varying(3)
        END AS global_stats,
    'NO'::character varying(3) AS user_stats,
    s.avg_width::numeric AS avg_col_len,
    information_schema._pg_char_max_length(information_schema._pg_truetypid(a.*, t.*), information_schema._pg_truetypmod(a.*, t.*)) AS char_length,
    NULL::character varying(1) AS char_used,
    NULL::character varying(3) AS v80_fmt_image,
    NULL::character varying(3) AS data_upgraded,
    'NONE'::character varying(4) AS histogram
   FROM pg_attribute a
     JOIN pg_class c ON c.oid = a.attrelid
     LEFT JOIN pg_attrdef d ON c.oid = d.adrelid AND a.attnum = d.adnum
     JOIN pg_namespace n ON n.oid = c.relnamespace
     JOIN pg_type t ON a.atttypid = t.oid
     LEFT JOIN pg_stats s ON s.schemaname = n.nspname AND s.tablename = c.relname AND s.attname = a.attname
     LEFT JOIN pg_stat_user_tables st ON st.relid = c.oid
  WHERE a.attnum > 0 AND NOT a.attisdropped AND (c.relkind = ANY (ARRAY['r'::"char", 'v'::"char", 'f'::"char", 'p'::"char"])) AND (( SELECT pg_user.usesuper
           FROM pg_user
          WHERE pg_user.usename = USER) OR pg_has_role('rds_superuser'::name, 'member'::text)) AND (n.nspname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name]));


-- aws_oracle_ext.sys_dba_tab_comments source

CREATE OR REPLACE VIEW dba_tab_comments
AS SELECT n.nspname::character varying(30) AS owner,
    c.relname::character varying(30) AS table_name,
        CASE
            WHEN c.relkind = 'r'::"char" THEN 'TABLE'::text
            WHEN c.relkind = 'v'::"char" THEN 'VIEW'::text
            ELSE NULL::text
        END AS table_type,
    obj_description(c.oid)::character varying(4000) AS comments,
    0::numeric AS origin_con_id
   FROM pg_class c
     JOIN pg_namespace n ON c.relnamespace = n.oid
  WHERE (c.relkind = ANY (ARRAY['r'::"char", 'v'::"char"])) AND (( SELECT pg_user.usesuper
           FROM pg_user
          WHERE pg_user.usename = USER) OR pg_has_role('rds_superuser'::name, 'member'::text)) AND (n.nspname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name]));


-- aws_oracle_ext.sys_dba_tables source

CREATE OR REPLACE VIEW dba_tables
AS SELECT t.schemaname::character varying(30) AS owner,
    t.tablename::character varying(30) AS table_name,
    t.tablespace::character varying(30) AS tablespace_name,
    NULL::character varying(30) AS cluster_name,
    NULL::character varying(30) AS iot_name,
    'VALID'::character varying(8) AS status,
    NULL::numeric AS pct_free,
    NULL::numeric AS pct_used,
    NULL::numeric AS ini_trans,
    NULL::numeric AS max_trans,
    NULL::numeric AS initial_extent,
    NULL::numeric AS next_extent,
    NULL::numeric AS min_extents,
    NULL::numeric AS max_extents,
    NULL::numeric AS pct_increase,
    NULL::numeric AS freelists,
    NULL::numeric AS freelist_groups,
    'NO'::character varying(3) AS logging,
    'N'::character varying(1) AS backed_up,
    stat.n_live_tup AS num_rows,
    NULL::numeric AS blocks,
    NULL::numeric AS empty_blocks,
    NULL::numeric AS avg_space,
    NULL::numeric AS chain_cnt,
    NULL::numeric AS avg_row_len,
    NULL::numeric AS avg_space_freelist_blocks,
    NULL::numeric AS num_freelist_blocks,
    NULL::character varying(10) AS degree,
    NULL::character varying(10) AS instances,
    NULL::character varying(5) AS cache,
    NULL::character varying(8) AS table_lock,
    NULL::numeric AS sample_size,
    COALESCE(stat.last_autoanalyze, stat.last_analyze)::date AS last_analyzed,
    'NO'::character varying(3) AS partitioned,
    NULL::character varying(12) AS iot_type,
        CASE
            WHEN n.nspname !~ '^pg_toast'::text AND n.nspname ~~ 'pg_temp%'::text THEN 'Y'::text
            ELSE 'N'::text
        END AS temporary,
    'N'::character varying(1) AS secondary,
    'NO'::character varying(3) AS nested,
    'DEFAULT'::character varying(7) AS buffer_pool,
    'DEFAULT'::character varying(7) AS flash_cache,
    'DEFAULT'::character varying(7) AS cell_flash_cache,
    'DISABLED'::character varying(8) AS row_movement,
    'NO'::character varying(3) AS global_stats,
    NULL::character varying(3) AS user_stats,
    NULL::character varying(15) AS duration,
    'DISABLED'::character varying(8) AS skip_corrupt,
    'NO'::character varying(3) AS monitoring,
    NULL::character varying(30) AS cluster_owner,
    'DISABLED'::character varying(8) AS dependencies,
    'DISABLED'::character varying(8) AS compression,
    NULL::character varying(12) AS compress_for,
    'NO'::character varying(3) AS dropped,
    NULL::character varying(3) AS read_only,
    'YES'::character varying(3) AS segment_created,
    'DEFAULT'::character varying(7) AS result_cache
   FROM pg_tables t
     JOIN pg_stat_user_tables stat ON t.schemaname = stat.schemaname AND t.tablename = stat.relname
     JOIN pg_namespace n ON t.schemaname = n.nspname
  WHERE ( SELECT pg_user.usesuper
           FROM pg_user
          WHERE pg_user.usename = USER) OR pg_has_role('rds_superuser'::name, 'member'::text) AND (t.schemaname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name]));


-- aws_oracle_ext.sys_dba_triggers source

CREATE OR REPLACE VIEW dba_triggers
AS SELECT n.nspname::character varying(128) AS owner,
    trg.tgname::character varying(128) AS trigger_name,
        CASE trg.tgtype::integer & 66
            WHEN 2 THEN 'BEFORE'::text ||
            CASE trg.tgtype::integer & 1
                WHEN 1 THEN ' EACH ROW'::text
                ELSE 'STATEMENT'::text
            END
            WHEN 64 THEN 'INSTEAD OF'::text
            ELSE 'AFTER'::text ||
            CASE trg.tgtype::integer & 1
                WHEN 1 THEN ' EACH ROW'::text
                ELSE 'STATEMENT'::text
            END
        END::character varying(16) AS trigger_type,
        CASE trg.tgtype::integer & 28::smallint::integer
            WHEN 32 THEN 'TRUNCATE'::text
            WHEN 16 THEN 'UPDATE'::text
            WHEN 8 THEN 'DELETE'::text
            WHEN 4 THEN 'INSERT'::text
            WHEN 20 THEN 'INSERT OR UPDATE'::text
            WHEN 28 THEN 'INSERT OR UPDATE OR DELETE'::text
            WHEN 24 THEN 'UPDATE OR DELETE'::text
            WHEN 12 THEN 'INSERT OR DELETE'::text
            ELSE NULL::text
        END::character varying(246) AS triggering_event,
    ns.nspname::character varying(128) AS table_owner,
        CASE trg.tgtype::integer & 66
            WHEN 64 THEN 'VIEW'::text
            ELSE 'TABLE'::text
        END::character varying(18) AS base_object_type,
    tbl.relname::character varying(128) AS table_name,
    ((( SELECT string_agg(a.attname::text, ', '::text) AS string_agg
           FROM pg_attribute a
          WHERE a.attrelid = tbl.oid AND (a.attnum = ANY (trg.tgattr::smallint[])))))::character varying(4000) AS column_name,
    'REFERENCING NEW AS NEW OLD AS OLD'::character varying(422) AS referencing_names,
        CASE
            WHEN pg_has_role(tbl.relowner, 'USAGE'::text) THEN (regexp_match(pg_get_triggerdef(trg.oid), '.{35,} WHEN \((.+)\) EXECUTE PROCEDURE'::text))[1]
            ELSE NULL::text
        END::character varying(4000) AS when_clause,
        CASE
            WHEN trg.tgenabled = 'D'::"char" THEN 'DISABLED'::text
            ELSE 'ENABLED'::text
        END::character varying(8) AS status,
    obj_description(trg.oid)::character varying(400) AS description,
    'PL/SQL'::character varying(11) AS action_type,
    (n.nspname::text || '.'::text) || proc.proname::text AS trigger_body,
    'NO'::character varying(3) AS crossedition,
    'NO'::character varying(3) AS before_statement,
    'NO'::character varying(3) AS before_row,
    'NO'::character varying(3) AS after_row,
    'NO'::character varying(3) AS after_statement,
    'NO'::character varying(3) AS instead_of_row,
        CASE trg.tgtype::integer & 66
            WHEN 2 THEN 'YES'::text
            WHEN 64 THEN 'NO'::text
            ELSE 'YES'::text
        END::character varying(3) AS fire_once,
    'NO'::character varying(3) AS apply_server_only
   FROM pg_trigger trg
     JOIN pg_proc proc ON proc.oid = trg.tgfoid
     JOIN pg_namespace n ON n.oid = proc.pronamespace
     JOIN pg_class tbl ON trg.tgrelid = tbl.oid
     JOIN pg_namespace ns ON ns.oid = tbl.relnamespace
  WHERE NOT trg.tgisinternal AND (( SELECT pg_user.usesuper
           FROM pg_user
          WHERE pg_user.usename = USER) OR pg_has_role('rds_superuser'::name, 'member'::text));	

CREATE OR REPLACE FUNCTION dbms_utility$current_instance(
	)
    RETURNS integer
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE PARALLEL UNSAFE
AS $BODY$
SELECT 1
$BODY$;

ALTER FUNCTION dbms_utility$current_instance()
    OWNER TO oms_owner;

COMMENT ON FUNCTION dbms_utility$current_instance()
    IS 'This function returns the current connected instance number.';

CREATE OR REPLACE VIEW dba_users
AS SELECT pg_namespace.nspname AS username,
    pg_namespace.nspowner AS user_id,
    NULL::character varying(30) AS password,
    'OPEN'::character varying(32) AS account_status,
    NULL::timestamp without time zone AS lock_date,
    NULL::timestamp without time zone AS expiry_date,
    'USERS'::character varying(30) AS default_tablespace,
    'TEMP'::character varying(30) AS temporary_tablespace,
    NULL::timestamp without time zone AS created,
    'DEFAULT'::character varying(30) AS profile,
    NULL::character varying(30) AS initial_rsrc_consumer_group,
    NULL::character varying(4000) AS external_name,
    NULL::character varying(8) AS password_versions,
    NULL::character varying(1) AS editions_enabled,
    'PASSWORD'::character varying(8) AS authentication_type
   FROM pg_namespace
  WHERE ( SELECT pg_user.usesuper
           FROM pg_user
          WHERE pg_user.usename = USER) OR pg_has_role('rds_superuser'::name, 'member'::text);


-- aws_oracle_ext.sys_dba_views source

CREATE OR REPLACE VIEW dba_views
AS SELECT pg_views.schemaname::character varying(30) AS owner,
    pg_views.viewname::character varying(30) AS view_name,
    length(pg_views.definition)::numeric AS text_length,
    pg_views.definition::character varying(409600) AS text,
    NULL::numeric AS type_text_length,
    NULL::character varying(4000) AS type_text,
    NULL::numeric AS oid_text_length,
    NULL::character varying(4000) AS oid_text,
    NULL::character varying(30) AS view_type_owner,
    NULL::character varying(30) AS view_type,
    NULL::character varying(30) AS superview_name,
    'N'::character varying(1) AS editioning_view,
    'Y'::character varying(1) AS read_only
   FROM pg_views
  WHERE (( SELECT pg_user.usesuper
           FROM pg_user
          WHERE pg_user.usename = USER) OR pg_has_role('rds_superuser'::name, 'member'::text)) AND (pg_views.schemaname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name]));


-- aws_oracle_ext.sys_user_cons_columns source

CREATE OR REPLACE VIEW user_cons_columns
AS SELECT x.cstrschema::character varying(128) AS owner,
    x.cstrname::character varying(128) AS constraint_name,
    x.tblname::character varying(128) AS table_name,
    x.colname::character varying(4000) AS column_name,
    rank() OVER (PARTITION BY x.cstrschema, x.tblname, x.cstrname ORDER BY x.colnum)::smallint AS "position"
   FROM ( SELECT DISTINCT nr.nspname,
            r.relname,
            r.relowner,
            a.attname,
            a.attnum,
            nc.nspname,
            c.conname
           FROM pg_namespace nr,
            pg_class r,
            pg_attribute a,
            pg_depend d,
            pg_namespace nc,
            pg_constraint c
          WHERE nr.oid = r.relnamespace AND r.oid = a.attrelid AND d.refclassid = 'pg_class'::regclass::oid AND d.refobjid = r.oid AND d.refobjsubid = a.attnum AND d.classid = 'pg_constraint'::regclass::oid AND d.objid = c.oid AND c.connamespace = nc.oid AND c.contype = 'c'::"char" AND (r.relkind = ANY (ARRAY['r'::"char", 'p'::"char"])) AND NOT a.attisdropped
        UNION ALL
         SELECT nr.nspname,
            r.relname,
            r.relowner,
            a.attname,
            a.attnum,
            nc.nspname,
            c.conname
           FROM pg_namespace nr,
            pg_class r,
            pg_attribute a,
            pg_namespace nc,
            pg_constraint c
          WHERE nr.oid = r.relnamespace AND r.oid = a.attrelid AND nc.oid = c.connamespace AND r.oid =
                CASE c.contype
                    WHEN 'f'::"char" THEN c.confrelid
                    ELSE c.conrelid
                END AND (a.attnum = ANY (
                CASE c.contype
                    WHEN 'f'::"char" THEN c.confkey
                    ELSE c.conkey
                END)) AND NOT a.attisdropped AND (c.contype = ANY (ARRAY['p'::"char", 'u'::"char", 'f'::"char"])) AND (r.relkind = ANY (ARRAY['r'::"char", 'p'::"char"]))) x(tblschema, tblname, tblowner, colname, colnum, cstrschema, cstrname)
     JOIN pg_user u ON u.usesysid = x.tblowner
  WHERE u.usename = USER;


-- aws_oracle_ext.sys_user_constraints source

CREATE OR REPLACE VIEW user_constraints
AS SELECT n.nspname::character varying(30) AS owner,
    c.conname::character varying(30) AS constraint_name,
    c.contype::character varying(1) AS constraint_type,
    cl.relname::character varying(30) AS table_name,
    pg_get_constraintdef(c.oid) AS search_condition,
    n_ref.nspname::character varying(30) AS r_owner,
    c_ref.conname::character varying(30) AS r_constraint_name,
        CASE c.confdeltype
            WHEN 'a'::"char" THEN 'no action'::character varying(9)
            WHEN 'r'::"char" THEN 'restrict'::character varying(9)
            WHEN 'c'::"char" THEN 'cascade'::character varying(9)
            WHEN 'n'::"char" THEN 'set null'::character varying(9)
            WHEN 'd'::"char" THEN 'set default'::character varying(11)
            ELSE NULL::character varying(9)
        END AS delete_rule,
    'ENABLED'::character varying(8) AS status,
        CASE
            WHEN c.condeferrable THEN 'DEFERRABLE'::character varying(14)
            ELSE 'NOT DEFERRABLE'::character varying(14)
        END AS "deferrable",
        CASE
            WHEN c.condeferred THEN 'DEFERRED'::character varying(9)
            ELSE 'IMMEDIATE'::character varying(9)
        END AS deferred,
        CASE
            WHEN c.convalidated THEN 'VALIDATED'::character varying(13)
            ELSE 'NOT VALIDATED'::character varying(13)
        END AS validated,
    'GENERATED NAME'::character varying(14) AS generated,
    NULL::character varying(3) AS bad,
    NULL::character varying(4) AS rely,
    NULL::date AS last_change,
    n.nspname::character varying(30) AS index_owner,
    cl_ind.relname::character varying(30) AS index_name,
    NULL::character varying(7) AS invalid,
    NULL::character varying(14) AS view_related
   FROM pg_constraint c
     JOIN pg_namespace n ON n.oid = c.connamespace
     JOIN pg_class cl ON cl.oid = c.conrelid
     JOIN pg_user u ON cl.relowner = u.usesysid
     LEFT JOIN pg_class cl_ref ON cl_ref.oid = c.confrelid
     LEFT JOIN pg_namespace n_ref ON n_ref.oid = cl_ref.relnamespace
     LEFT JOIN pg_constraint c_ref ON c_ref.conrelid = c.confrelid AND c_ref.contype = 'p'::"char"
     LEFT JOIN pg_index i ON i.indrelid = cl_ref.oid
     LEFT JOIN pg_class cl_ind ON cl_ind.oid = c.conindid
  WHERE u.usename = USER;


-- aws_oracle_ext.sys_user_ind_columns source

CREATE OR REPLACE VIEW user_ind_columns
AS SELECT irel.relname::character varying(128) AS index_name,
    trel.relname::character varying(128) AS table_name,
    a.attname::character varying(4000) AS column_name,
    c.ordinality::integer AS column_position,
    COALESCE(information_schema._pg_char_max_length(information_schema._pg_truetypid(a.*, t.*), information_schema._pg_truetypmod(a.*, t.*)), information_schema._pg_numeric_precision(information_schema._pg_truetypid(a.*, t.*), information_schema._pg_truetypmod(a.*, t.*))) AS column_length,
    COALESCE(information_schema._pg_char_max_length(information_schema._pg_truetypid(a.*, t.*), information_schema._pg_truetypmod(a.*, t.*)), 0) AS char_length,
        CASE o.option::integer & 1
            WHEN 1 THEN 'DESC'::text
            ELSE 'ASC'::text
        END::character varying(4) AS descend,
    NULL::integer AS collated_column_id
   FROM pg_index i
     JOIN pg_class trel ON trel.oid = i.indrelid
     JOIN pg_namespace tnsp ON trel.relnamespace = tnsp.oid
     JOIN pg_class irel ON irel.oid = i.indexrelid
     JOIN pg_namespace insp ON irel.relnamespace = insp.oid
     CROSS JOIN LATERAL unnest(i.indkey) WITH ORDINALITY c(colnum, ordinality)
     LEFT JOIN LATERAL unnest(i.indoption) WITH ORDINALITY o(option, ordinality) ON c.ordinality = o.ordinality
     JOIN pg_attribute a ON trel.oid = a.attrelid AND a.attnum = c.colnum
     JOIN (pg_type t
     JOIN pg_namespace nt ON t.typnamespace = nt.oid) ON a.atttypid = t.oid
     JOIN pg_user ui ON irel.relowner = ui.usesysid
     JOIN pg_user ut ON trel.relowner = ut.usesysid
  WHERE ui.usename = USER OR ut.usename = USER;


-- aws_oracle_ext.sys_user_indexes source

CREATE OR REPLACE VIEW user_indexes
AS SELECT irel.relname::character varying(128) AS index_name,
        CASE
            WHEN i.indexprs IS NULL THEN 'NORMAL'::text
            ELSE 'FUNCTION-BASED NORMAL'::text
        END::character varying(27) AS index_type,
    tnsp.nspname::character varying(128) AS table_owner,
    trel.relname::character varying(128) AS table_name,
    'TABLE'::character varying(11) AS table_type,
        CASE
            WHEN i.indisunique THEN 'UNIQUE'::text
            ELSE 'NONUNIQUE'::text
        END::character varying(9) AS uniqueness,
    'DISABLED'::character varying(13) AS compression,
    NULL::integer AS prefix_length,
    ts.spcname::character varying(30) AS tablespace_name,
    NULL::integer AS ini_trans,
    NULL::integer AS max_trans,
    NULL::integer AS initial_extent,
    NULL::integer AS next_extent,
    NULL::integer AS min_extents,
    NULL::integer AS max_extents,
    NULL::integer AS pct_increase,
    NULL::integer AS pct_threshold,
    NULL::integer AS include_column,
    NULL::integer AS freelists,
    NULL::integer AS freelist_groups,
    NULL::integer AS pct_free,
        CASE trel.relpersistence
            WHEN 'p'::"char" THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS logging,
    NULL::integer AS blevel,
    NULL::integer AS leaf_blocks,
        CASE
            WHEN i.indisprimary THEN st.n_live_tup
            ELSE NULL::bigint
        END::integer AS distinct_keys,
    NULL::integer AS avg_leaf_blocks_per_key,
    NULL::integer AS avg_data_blocks_per_key,
    NULL::integer AS clustering_factor,
        CASE
            WHEN i.indisready THEN 'ENABLED'::text
            ELSE 'DISABLED'::text
        END::character varying(8) AS status,
    st.n_live_tup::integer AS num_rows,
    NULL::integer AS sample_size,
    COALESCE(st.last_autoanalyze, st.last_analyze)::date AS last_analyzed,
    NULL::character varying(40) AS degree,
    NULL::character varying(40) AS instances,
        CASE
            WHEN trel.relispartition THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS partitioned,
        CASE trel.relpersistence
            WHEN 't'::"char" THEN 'Y'::text
            ELSE 'N'::text
        END::character varying(1) AS temporary,
    'N'::character varying(1) AS generated,
    'N'::character varying(1) AS secondary,
    'DEFAULT'::character varying(7) AS buffer_pool,
    'DEFAULT'::character varying(7) AS flash_cache,
    'DEFAULT'::character varying(7) AS cell_flash_cache,
    'NO'::character varying(3) AS user_stats,
    NULL::character varying(15) AS duration,
    NULL::integer AS pct_direct_access,
    NULL::character varying(128) AS ityp_owner,
    NULL::character varying(128) AS ityp_name,
    NULL::character varying(1000) AS parameters,
    'NO'::character varying(3) AS global_stats,
    NULL::character varying(12) AS domidx_status,
    NULL::character varying(6) AS domidx_opstatus,
    NULL::character varying(8) AS funcidx_status,
    'NO'::character varying(3) AS join_index,
    'NO'::character varying(3) AS iot_redundant_pkey_elim,
    'NO'::character varying(3) AS dropped,
        CASE
            WHEN i.indisvalid THEN 'VISIBLE'::text
            ELSE 'INVISIBLE'::text
        END::character varying(9) AS visibility,
    NULL::character varying(14) AS domidx_management,
        CASE irel.relpages
            WHEN 0 THEN 'NO'::text
            ELSE 'YES'::text
        END::character varying(3) AS segment_created,
    'NO'::character varying(3) AS orphaned_entries,
        CASE
            WHEN i.indpred IS NULL THEN 'FULL'::text
            ELSE 'PARTIAL'::text
        END::character varying(7) AS indexing
   FROM pg_index i
     JOIN pg_class trel ON trel.oid = i.indrelid
     JOIN pg_namespace tnsp ON trel.relnamespace = tnsp.oid
     JOIN pg_class irel ON irel.oid = i.indexrelid
     JOIN pg_am am ON am.oid = irel.relam AND am.amname = 'btree'::name
     LEFT JOIN pg_tablespace ts ON ts.oid = irel.reltablespace
     LEFT JOIN pg_stat_all_tables st ON st.schemaname = tnsp.nspname AND st.relname = trel.relname
     JOIN pg_user u ON irel.relowner = u.usesysid
  WHERE u.usename = USER;


-- aws_oracle_ext.sys_user_objects source

CREATE OR REPLACE VIEW user_objects
AS SELECT c.relname::character varying(30) AS object_name,
    NULL::character varying(30) AS subobject_name,
    c.oid::bigint AS object_id,
        CASE
            WHEN c.relkind = ANY (ARRAY['t'::"char", 'r'::"char"]) THEN c.oid::bigint
            ELSE NULL::bigint
        END AS data_object_id,
        CASE c.relkind
            WHEN 'r'::"char" THEN 'ordinary table'::character varying(17)
            WHEN 'i'::"char" THEN 'index'::character varying(17)
            WHEN 'S'::"char" THEN 'sequence'::character varying(17)
            WHEN 'v'::"char" THEN 'view'::character varying(17)
            WHEN 'c'::"char" THEN 'composite type'::character varying(17)
            WHEN 't'::"char" THEN 'TOAST table'::character varying(17)
            WHEN 'f'::"char" THEN 'foreign table'::character varying(17)
            ELSE NULL::character varying(17)
        END AS object_type,
    NULL::date AS created,
    NULL::date AS last_ddl_time,
    NULL::character varying(78) AS "timestamp",
    'VALID'::character varying(7) AS status,
        CASE c.relpersistence
            WHEN 't'::"char" THEN 'Y'::text
            ELSE 'N'::text
        END AS temporary,
    'N'::character varying(1) AS generated,
    'N'::character varying(1) AS secondary,
    c.reltablespace::integer AS namespace,
    NULL::character varying(30) AS edition_name
   FROM pg_class c
     JOIN pg_namespace n ON c.relnamespace = n.oid
     JOIN pg_user u ON c.relowner = u.usesysid
  WHERE u.usename = USER AND (n.nspname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name]));


-- aws_oracle_ext.sys_user_policies source

CREATE OR REPLACE VIEW user_policies
AS SELECT p.tablename::character varying(128) AS object_name,
    'SYS_DEFAULT'::character varying(128) AS policy_group,
    p.policyname::character varying(128) AS policy_name,
    p.schemaname::character varying(128) AS pf_owner,
    NULL::character varying(128) AS package,
    NULL::character varying(128) AS function,
        CASE
            WHEN p.cmd = ANY (ARRAY['ALL'::text, 'SELECT'::text]) THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS sel,
        CASE
            WHEN p.cmd = ANY (ARRAY['ALL'::text, 'INSERT'::text]) THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS ins,
        CASE
            WHEN p.cmd = ANY (ARRAY['ALL'::text, 'UPDATE'::text]) THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS upd,
        CASE
            WHEN p.cmd = ANY (ARRAY['ALL'::text, 'DELETE'::text]) THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS del,
    'NO'::character varying(3) AS idx,
        CASE
            WHEN p.with_check IS NOT NULL THEN 'YES'::text
            ELSE 'NO'::text
        END::character varying(3) AS chk_option,
    'YES'::character varying(3) AS enable,
    'NO'::character varying(3) AS static_policy,
    'DYNAMIC'::character varying(24) AS policy_type,
    'NO'::character varying(3) AS long_predicate,
    'NO'::character varying(3) AS common,
    'NO'::character varying(3) AS inherited
   FROM pg_policies p
     JOIN pg_tables t ON t.schemaname = p.schemaname AND t.tablename = p.tablename
  WHERE t.tableowner = USER;


-- aws_oracle_ext.sys_user_sequences source

CREATE OR REPLACE VIEW user_sequences
AS SELECT s.sequence_name::character varying(30) AS sequence_name,
    s.minimum_value::numeric AS min_value,
    s.maximum_value::numeric AS max_value,
    s.increment::numeric AS increment_by,
        CASE s.cycle_option
            WHEN 'NO'::text THEN 'N'::character varying(1)
            WHEN 'YES'::text THEN 'Y'::character varying(1)
            ELSE NULL::character varying
        END AS cycle_flag,
    'N'::character varying(1) AS order_flag,
    0 AS cache_size,
    currval(((quote_ident(s.sequence_schema::text) || '.'::text) || quote_ident(s.sequence_name::text))::regclass) AS last_number
   FROM information_schema.sequences s
     JOIN pg_class c ON c.relname = s.sequence_name::name AND c.relkind = 'S'::"char"
     JOIN pg_user u ON c.relowner = u.usesysid
  WHERE u.usename = USER;


-- aws_oracle_ext.sys_user_source source

CREATE OR REPLACE VIEW user_source
AS SELECT n.nspowner::character varying(30) AS owner,
    s.name::character varying(30) AS name,
    s.type::character varying(12) AS type,
    generate_subscripts(s.text, 1)::numeric AS line,
    unnest(s.text)::character varying(4000) AS text
   FROM ( SELECT c.relnamespace AS nsp,
            t.tgname AS name,
            'TRIGGER'::text AS type,
            string_to_array(pg_get_triggerdef(t.oid), chr(10)) AS text
           FROM pg_trigger t
             JOIN pg_class c ON c.oid = t.tgrelid
             JOIN pg_user u ON u.usesysid = c.relowner
          WHERE u.usename = USER
        UNION ALL
         SELECT f.pronamespace AS nsp,
            f.proname AS name,
                CASE
                    WHEN f.prokind = 'f'::"char" THEN 'FUNCTION'::text
                    WHEN f.prokind = 'p'::"char" THEN 'PROCEDURE'::text
                    ELSE NULL::text
                END AS type,
            string_to_array(pg_get_functiondef(f.oid), chr(10)) AS text
           FROM pg_proc f
             JOIN pg_type ret ON f.prorettype = ret.oid
             JOIN pg_user u ON u.usesysid = f.proowner
          WHERE (f.prokind = ANY (ARRAY['f'::"char", 'p'::"char"])) AND u.usename = USER
        UNION ALL
         SELECT t.typnamespace AS nsp,
            t.typname AS name,
            'TYPE'::text AS type,
                CASE
                    WHEN t.typtype = 'c'::"char" THEN ARRAY( SELECT (('CREATE OR REPLACE TYPE '::text || t.typname::text) || ' AS ('::text) || chr(10)
                    UNION ALL
                     SELECT (((a.attname::text || ' '::text) || format_type(a.atttypid, a.atttypmod)) ||
                            CASE
                                WHEN lead(a.attrelid, 1) OVER () IS NULL THEN ''::text
                                ELSE ','::text
                            END) || chr(10)
                       FROM pg_attribute a
                      WHERE a.attrelid = t.typrelid
                    UNION ALL
                     SELECT ');'::text)
                    WHEN t.typtype = 'd'::"char" THEN ARRAY( SELECT (('CREATE OR REPLACE DOMAIN '::text || t.typname::text) || ' AS '::text) || format_type(tt.oid, NULL::integer)
                       FROM pg_type tt
                      WHERE tt.typarray = t.typbasetype)
                    ELSE NULL::text[]
                END AS text
           FROM pg_type t
             JOIN pg_user u ON u.usesysid = t.typowner
          WHERE (t.typrelid = 0::oid OR ( SELECT c.relkind = 'c'::"char"
                   FROM pg_class c
                  WHERE c.oid = t.typrelid)) AND NOT (EXISTS ( SELECT 1
                   FROM pg_type el
                  WHERE el.oid = t.typelem AND el.typarray = t.oid)) AND u.usename = USER) s
     JOIN pg_namespace n ON s.nsp = n.oid
  WHERE n.nspname <> 'pg_catalog'::name AND n.nspname <> 'information_schema'::name AND n.nspname !~ '^pg_toast'::text;


-- aws_oracle_ext.sys_user_tab_cols source

CREATE OR REPLACE VIEW user_tab_cols
AS SELECT c.relname::character varying(30) AS table_name,
    a.attname::character varying(30) AS column_name,
    format_type(a.atttypid, a.atttypmod)::character varying(106) AS data_type,
    NULL::character varying(3) AS data_type_mod,
    NULL::character varying(30) AS data_type_owner,
    a.attlen::numeric AS data_length,
    NULL::numeric AS data_precision,
    NULL::numeric AS data_scale,
        CASE
            WHEN a.attnotnull THEN 'N'::character varying(1)
            ELSE 'Y'::character varying(1)
        END AS nullable,
    a.attnum::numeric AS column_id,
    NULL::numeric AS default_length,
    pg_get_expr(d.adbin, d.adrelid) AS data_default,
    s.n_distinct::numeric AS num_distinct,
    NULL::numeric AS low_value,
    NULL::numeric AS high_value,
    s.null_frac::numeric AS density,
    NULL::numeric AS num_nulls,
    NULL::numeric AS num_buckets,
    COALESCE(st.last_autoanalyze, st.last_analyze)::date AS last_analyzed,
    NULL::numeric AS sample_size,
    'NCHAR_CS'::character varying(44) AS character_set_name,
    NULL::numeric AS char_col_decl_length,
        CASE
            WHEN COALESCE(st.last_autoanalyze, st.last_analyze) IS NOT NULL THEN 'YES'::character varying(3)
            ELSE 'NO'::character varying(3)
        END AS global_stats,
    'NO'::character varying(3) AS user_stats,
    s.avg_width::numeric AS avg_col_len,
    information_schema._pg_char_max_length(information_schema._pg_truetypid(a.*, t.*), information_schema._pg_truetypmod(a.*, t.*)) AS char_length,
    NULL::character varying(1) AS char_used,
    NULL::character varying(3) AS v80_fmt_image,
    NULL::character varying(3) AS data_upgraded,
    'NO'::character varying(3) AS hidden_column,
    'NO'::character varying(3) AS virtual_column,
    a.attnum::numeric AS segment_column_id,
    a.attnum::numeric AS internal_column_id,
    'NONE'::character varying(4) AS histogram,
    a.attname::character varying(4000) AS qualified_col_name
   FROM pg_attribute a
     JOIN pg_class c ON c.oid = a.attrelid
     JOIN pg_user u ON c.relowner = u.usesysid
     JOIN pg_type t ON a.atttypid = t.oid
     LEFT JOIN pg_attrdef d ON c.oid = d.adrelid AND a.attnum = d.adnum
     JOIN pg_namespace n ON n.oid = c.relnamespace
     LEFT JOIN pg_stats s ON s.schemaname = n.nspname AND s.tablename = c.relname AND s.attname = a.attname
     LEFT JOIN pg_stat_user_tables st ON st.relid = c.oid
  WHERE a.attnum > 0 AND NOT a.attisdropped AND (c.relkind = ANY (ARRAY['r'::"char", 'v'::"char", 'f'::"char", 'p'::"char"])) AND u.usename = USER AND (n.nspname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name]));


-- aws_oracle_ext.sys_user_tab_columns source

CREATE OR REPLACE VIEW user_tab_columns
AS SELECT c.relname::character varying(30) AS table_name,
    a.attname::character varying(30) AS column_name,
    format_type(a.atttypid, a.atttypmod)::character varying(106) AS data_type,
    NULL::character varying(3) AS data_type_mod,
    NULL::character varying(30) AS data_type_owner,
    a.attlen::numeric AS data_length,
    NULL::numeric AS data_precision,
    NULL::numeric AS data_scale,
        CASE
            WHEN a.attnotnull THEN 'N'::character varying(1)
            ELSE 'Y'::character varying(1)
        END AS nullable,
    a.attnum::numeric AS column_id,
    NULL::numeric AS default_length,
    pg_get_expr(d.adbin, d.adrelid) AS data_default,
    s.n_distinct::numeric AS num_distinct,
    NULL::numeric AS low_value,
    NULL::numeric AS high_value,
    s.null_frac::numeric AS density,
    NULL::numeric AS num_nulls,
    NULL::numeric AS num_buckets,
    COALESCE(st.last_autoanalyze, st.last_analyze)::date AS last_analyzed,
    NULL::numeric AS sample_size,
    'NCHAR_CS'::character varying(44) AS character_set_name,
    NULL::numeric AS char_col_decl_length,
        CASE
            WHEN COALESCE(st.last_autoanalyze, st.last_analyze) IS NOT NULL THEN 'YES'::character varying(3)
            ELSE 'NO'::character varying(3)
        END AS global_stats,
    'NO'::character varying(3) AS user_stats,
    s.avg_width::numeric AS avg_col_len,
    information_schema._pg_char_max_length(information_schema._pg_truetypid(a.*, t.*), information_schema._pg_truetypmod(a.*, t.*)) AS char_length,
    NULL::character varying(1) AS char_used,
    NULL::character varying(3) AS v80_fmt_image,
    NULL::character varying(3) AS data_upgraded,
    'NONE'::character varying(4) AS histogram
   FROM pg_attribute a
     JOIN pg_class c ON c.oid = a.attrelid
     JOIN pg_user u ON c.relowner = u.usesysid
     LEFT JOIN pg_attrdef d ON c.oid = d.adrelid AND a.attnum = d.adnum
     JOIN pg_namespace n ON n.oid = c.relnamespace
     JOIN pg_type t ON a.atttypid = t.oid
     LEFT JOIN pg_stats s ON s.schemaname = n.nspname AND s.tablename = c.relname AND s.attname = a.attname
     LEFT JOIN pg_stat_user_tables st ON st.relid = c.oid
  WHERE a.attnum > 0 AND NOT a.attisdropped AND (c.relkind = ANY (ARRAY['r'::"char", 'v'::"char", 'f'::"char", 'p'::"char"])) AND u.usename = USER AND (n.nspname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name]));


-- aws_oracle_ext.sys_user_tab_comments source

CREATE OR REPLACE VIEW user_tab_comments
AS SELECT c.relname::character varying(30) AS table_name,
        CASE
            WHEN c.relkind = 'r'::"char" THEN 'TABLE'::text
            WHEN c.relkind = 'v'::"char" THEN 'VIEW'::text
            ELSE NULL::text
        END AS table_type,
    obj_description(c.oid)::character varying(4000) AS comments
   FROM pg_class c
     JOIN pg_namespace n ON c.relnamespace = n.oid
     JOIN pg_user u ON u.usesysid = c.relowner
  WHERE (c.relkind = ANY (ARRAY['r'::"char", 'v'::"char"])) AND (n.nspname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name])) AND u.usename = USER;


-- aws_oracle_ext.sys_user_tables source

CREATE OR REPLACE VIEW user_tables
AS SELECT t.tablename::character varying(30) AS table_name,
    t.tablespace::character varying(30) AS tablespace_name,
    NULL::character varying(30) AS cluster_name,
    NULL::character varying(30) AS iot_name,
    'VALID'::character varying(8) AS status,
    NULL::numeric AS pct_free,
    NULL::numeric AS pct_used,
    NULL::numeric AS ini_trans,
    NULL::numeric AS max_trans,
    NULL::numeric AS initial_extent,
    NULL::numeric AS next_extent,
    NULL::numeric AS min_extents,
    NULL::numeric AS max_extents,
    NULL::numeric AS pct_increase,
    NULL::numeric AS freelists,
    NULL::numeric AS freelist_groups,
    'NO'::character varying(3) AS logging,
    'N'::character varying(1) AS backed_up,
    stat.n_live_tup AS num_rows,
    NULL::numeric AS blocks,
    NULL::numeric AS empty_blocks,
    NULL::numeric AS avg_space,
    NULL::numeric AS chain_cnt,
    NULL::numeric AS avg_row_len,
    NULL::numeric AS avg_space_freelist_blocks,
    NULL::numeric AS num_freelist_blocks,
    NULL::character varying(10) AS degree,
    NULL::character varying(10) AS instances,
    NULL::character varying(5) AS cache,
    NULL::character varying(8) AS table_lock,
    NULL::numeric AS sample_size,
    COALESCE(stat.last_autoanalyze, stat.last_analyze)::date AS last_analyzed,
    'NO'::character varying(3) AS partitioned,
    NULL::character varying(12) AS iot_type,
        CASE
            WHEN n.nspname !~ '^pg_toast'::text AND n.nspname ~~ 'pg_temp%'::text THEN 'Y'::text
            ELSE 'N'::text
        END AS temporary,
    'N'::character varying(1) AS secondary,
    'NO'::character varying(3) AS nested,
    'DEFAULT'::character varying(7) AS buffer_pool,
    'DEFAULT'::character varying(7) AS flash_cache,
    'DEFAULT'::character varying(7) AS cell_flash_cache,
    'DISABLED'::character varying(8) AS row_movement,
    'NO'::character varying(3) AS global_stats,
    NULL::character varying(3) AS user_stats,
    NULL::character varying(15) AS duration,
    'DISABLED'::character varying(8) AS skip_corrupt,
    'NO'::character varying(3) AS monitoring,
    NULL::character varying(30) AS cluster_owner,
    'DISABLED'::character varying(8) AS dependencies,
    'DISABLED'::character varying(8) AS compression,
    NULL::character varying(12) AS compress_for,
    'NO'::character varying(3) AS dropped,
    NULL::character varying(3) AS read_only,
    'YES'::character varying(3) AS segment_created,
    'DEFAULT'::character varying(7) AS result_cache
   FROM pg_tables t
     JOIN pg_stat_user_tables stat ON t.schemaname = stat.schemaname AND t.tablename = stat.relname
     JOIN pg_namespace n ON t.schemaname = n.nspname
  WHERE t.tableowner = USER AND (t.schemaname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name]));


-- aws_oracle_ext.sys_user_triggers source

CREATE OR REPLACE VIEW user_triggers
AS SELECT trg.tgname::character varying(128) AS trigger_name,
        CASE trg.tgtype::integer & 66
            WHEN 2 THEN 'BEFORE'::text ||
            CASE trg.tgtype::integer & 1
                WHEN 1 THEN ' EACH ROW'::text
                ELSE 'STATEMENT'::text
            END
            WHEN 64 THEN 'INSTEAD OF'::text
            ELSE 'AFTER'::text ||
            CASE trg.tgtype::integer & 1
                WHEN 1 THEN ' EACH ROW'::text
                ELSE 'STATEMENT'::text
            END
        END::character varying(16) AS trigger_type,
        CASE trg.tgtype::integer & 28::smallint::integer
            WHEN 32 THEN 'TRUNCATE'::text
            WHEN 16 THEN 'UPDATE'::text
            WHEN 8 THEN 'DELETE'::text
            WHEN 4 THEN 'INSERT'::text
            WHEN 20 THEN 'INSERT OR UPDATE'::text
            WHEN 28 THEN 'INSERT OR UPDATE OR DELETE'::text
            WHEN 24 THEN 'UPDATE OR DELETE'::text
            WHEN 12 THEN 'INSERT OR DELETE'::text
            ELSE NULL::text
        END::character varying(246) AS triggering_event,
    ns.nspname::character varying(128) AS table_owner,
        CASE trg.tgtype::integer & 66
            WHEN 64 THEN 'VIEW'::text
            ELSE 'TABLE'::text
        END::character varying(18) AS base_object_type,
    tbl.relname::character varying(128) AS table_name,
    ((( SELECT string_agg(a.attname::text, ', '::text) AS string_agg
           FROM pg_attribute a
          WHERE a.attrelid = tbl.oid AND (a.attnum = ANY (trg.tgattr::smallint[])))))::character varying(4000) AS column_name,
    'REFERENCING NEW AS NEW OLD AS OLD'::character varying(422) AS referencing_names,
        CASE
            WHEN pg_has_role(tbl.relowner, 'USAGE'::text) THEN (regexp_match(pg_get_triggerdef(trg.oid), '.{35,} WHEN \((.+)\) EXECUTE PROCEDURE'::text))[1]
            ELSE NULL::text
        END::character varying(4000) AS when_clause,
        CASE
            WHEN trg.tgenabled = 'D'::"char" THEN 'DISABLED'::text
            ELSE 'ENABLED'::text
        END::character varying(8) AS status,
    obj_description(trg.oid)::character varying(400) AS description,
    'PL/SQL'::character varying(11) AS action_type,
    (n.nspname::text || '.'::text) || proc.proname::text AS trigger_body,
    'NO'::character varying(3) AS crossedition,
    'NO'::character varying(3) AS before_statement,
    'NO'::character varying(3) AS before_row,
    'NO'::character varying(3) AS after_row,
    'NO'::character varying(3) AS after_statement,
    'NO'::character varying(3) AS instead_of_row,
        CASE trg.tgtype::integer & 66
            WHEN 2 THEN 'YES'::text
            WHEN 64 THEN 'NO'::text
            ELSE 'YES'::text
        END::character varying(3) AS fire_once,
    'NO'::character varying(3) AS apply_server_only
   FROM pg_trigger trg
     JOIN pg_proc proc ON proc.oid = trg.tgfoid
     JOIN pg_namespace n ON n.oid = proc.pronamespace
     JOIN pg_class tbl ON trg.tgrelid = tbl.oid
     JOIN pg_namespace ns ON ns.oid = tbl.relnamespace
     JOIN pg_user u ON u.usesysid = tbl.relowner
  WHERE NOT trg.tgisinternal AND u.usename = USER;


-- aws_oracle_ext.sys_user_users source

CREATE OR REPLACE VIEW user_users
AS SELECT n.nspname AS username,
    n.nspowner AS user_id,
    'OPEN'::character varying(32) AS account_status,
    NULL::timestamp without time zone AS lock_date,
    NULL::timestamp without time zone AS expiry_date,
    'USERS'::character varying(30) AS default_tablespace,
    'TEMP'::character varying(30) AS temporary_tablespace,
    NULL::timestamp without time zone AS created,
    NULL::character varying(30) AS initial_rsrc_consumer_group,
    NULL::character varying(4000) AS external_name
   FROM pg_namespace n
     JOIN pg_user u ON n.nspowner = u.usesysid
  WHERE u.usename = USER;


-- aws_oracle_ext.sys_user_views source

CREATE OR REPLACE VIEW user_views
AS SELECT pg_views.viewname::character varying(30) AS view_name,
    length(pg_views.definition)::numeric AS text_length,
    pg_views.definition::character varying(409600) AS text,
    NULL::numeric AS type_text_length,
    NULL::character varying(4000) AS type_text,
    NULL::numeric AS oid_text_length,
    NULL::character varying(4000) AS oid_text,
    NULL::character varying(30) AS view_type_owner,
    NULL::character varying(30) AS view_type,
    NULL::character varying(30) AS superview_name,
    'N'::character varying(1) AS editioning_view,
    'Y'::character varying(1) AS read_only
   FROM pg_views
  WHERE (pg_views.schemaname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name])) AND pg_views.viewowner = USER;


-- aws_oracle_ext."v$instance" source

CREATE OR REPLACE VIEW v$instance
AS SELECT dbms_utility$current_instance() AS instance_number,
    inet_server_addr()::character varying AS instance_name,
    inet_server_addr()::character varying AS host_name,
    current_setting('server_version_num'::text) AS version,
    pg_postmaster_start_time() AS startup_time,
    'OPEN'::character varying AS status,
    'NO'::character varying AS parallel,
    1 AS "THREAD#",
    'FAILED'::character varying AS archiver,
    NULL::character varying AS log_switch_wait,
    'ALLOWED'::character varying AS logins,
    'NO'::character varying AS shutdown_pending,
    'ACTIVE'::character varying AS database_status,
    'PRIMARY_INSTANCE'::character varying AS instance_role,
    'NORMAL'::character varying AS active_state,
    'NO'::character varying AS blocked;


CREATE OR REPLACE FUNCTION has_column_privilege(
	schema_name name,
	table_name name,
	column_name name)
    RETURNS boolean
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE STRICT PARALLEL UNSAFE
AS $BODY$
begin
    return has_column_privilege(quote_ident($1 || '.'||$2), $3, 'SELECT,INSERT,UPDATE,REFERENCES');
    exception 
   		when others then return null;
end
$BODY$;

CREATE OR REPLACE VIEW all_tab_columns
 AS
 SELECT n.nspname::character varying(30) AS owner,
    c.relname::character varying(30) AS table_name,
    a.attname::character varying(30) AS column_name,
    format_type(a.atttypid, a.atttypmod)::character varying(106) AS data_type,
    NULL::character varying(3) AS data_type_mod,
    NULL::character varying(30) AS data_type_owner,
    a.attlen::numeric AS data_length,
    NULL::numeric AS data_precision,
    NULL::numeric AS data_scale,
        CASE
            WHEN a.attnotnull THEN 'N'::character varying(1)
            ELSE 'Y'::character varying(1)
        END AS nullable,
    a.attnum::numeric AS column_id,
    NULL::numeric AS default_length,
    pg_get_expr(d.adbin, d.adrelid) AS data_default,
    s.n_distinct::numeric AS num_distinct,
    NULL::numeric AS low_value,
    NULL::numeric AS high_value,
    s.null_frac::numeric AS density,
    NULL::numeric AS num_nulls,
    NULL::numeric AS num_buckets,
    COALESCE(st.last_autoanalyze, st.last_analyze)::date AS last_analyzed,
    NULL::numeric AS sample_size,
    'NCHAR_CS'::character varying(44) AS character_set_name,
    NULL::numeric AS char_col_decl_length,
        CASE
            WHEN COALESCE(st.last_autoanalyze, st.last_analyze) IS NOT NULL THEN 'YES'::character varying(3)
            ELSE 'NO'::character varying(3)
        END AS global_stats,
    'NO'::character varying(3) AS user_stats,
    s.avg_width::numeric AS avg_col_len,
    information_schema._pg_char_max_length(information_schema._pg_truetypid(a.*, t.*), information_schema._pg_truetypmod(a.*, t.*)) AS char_length,
    NULL::character varying(1) AS char_used,
    NULL::character varying(3) AS v80_fmt_image,
    NULL::character varying(3) AS data_upgraded,
    'NONE'::character varying(4) AS histogram
   FROM pg_attribute a
     JOIN pg_class c ON c.oid = a.attrelid
     JOIN pg_user u ON c.relowner = u.usesysid
     LEFT JOIN pg_attrdef d ON c.oid = d.adrelid AND a.attnum = d.adnum
     JOIN pg_namespace n ON n.oid = c.relnamespace
     JOIN pg_type t ON a.atttypid = t.oid
     LEFT JOIN pg_stats s ON s.schemaname = n.nspname AND s.tablename = c.relname AND s.attname = a.attname
     LEFT JOIN pg_stat_user_tables st ON st.relid = c.oid
  WHERE a.attnum > 0 AND NOT a.attisdropped AND (c.relkind = ANY (ARRAY['r'::"char", 'v'::"char", 'f'::"char", 'p'::"char"])) AND (u.usename = USER OR has_column_privilege(n.nspname, c.relname, a.attname)) AND (n.nspname <> ALL (ARRAY['pg_catalog'::name, 'information_schema'::name, 'public_synonyms'::name]));		  