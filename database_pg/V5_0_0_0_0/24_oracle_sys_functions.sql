CREATE OR REPLACE FUNCTION round(
	date)
    RETURNS date
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT $1;
$BODY$;

ALTER FUNCTION round(date)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(date)
    IS 'Round dates according to the specified format';
	


	

CREATE OR REPLACE FUNCTION round(
	value timestamp with time zone,
	fmt text)
    RETURNS timestamp with time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT NULL::TIMESTAMP WITH TIME ZONE;
$BODY$;

ALTER FUNCTION round(timestamp with time zone, text)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(timestamp with time zone, text)
    IS 'Round dates according to the specified format';

CREATE OR REPLACE FUNCTION round(
	value timestamp without time zone)
    RETURNS timestamp without time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT round($1, 'DDD');
$BODY$;

ALTER FUNCTION round(timestamp without time zone)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(timestamp without time zone)
    IS 'Round dates according to the specified format';
	
CREATE OR REPLACE FUNCTION round(
	date)
    RETURNS date
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT $1;
$BODY$;

ALTER FUNCTION round(date)
    OWNER TO oms_owner;
COMMENT ON FUNCTION round(date)
    IS 'Round dates according to the specified format';

CREATE OR REPLACE FUNCTION round(
	value timestamp with time zone,
	fmt text)
    RETURNS timestamp with time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT NULL::TIMESTAMP WITH TIME ZONE;
$BODY$;

ALTER FUNCTION round(timestamp with time zone, text)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(timestamp with time zone, text)
    IS 'Round dates according to the specified format';

CREATE OR REPLACE FUNCTION round(
	value timestamp without time zone)
    RETURNS timestamp without time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT round($1, 'DDD');
$BODY$;

ALTER FUNCTION round(timestamp without time zone)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(timestamp without time zone)
    IS 'Round dates according to the specified format';
CREATE OR REPLACE FUNCTION round(
	value date,
	fmt text)
    RETURNS date
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT round($1::TIMESTAMP(0), $2)::DATE;
$BODY$;

ALTER FUNCTION round(date, text)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(date, text)
    IS 'Round dates according to the specified format';
CREATE OR REPLACE FUNCTION round(
	value timestamp with time zone)
    RETURNS timestamp with time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT round($1, 'DDD');
$BODY$;

ALTER FUNCTION round(timestamp with time zone)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(timestamp with time zone)
    IS 'Round dates according to the specified format';

CREATE OR REPLACE FUNCTION round(
	value date,
	fmt text)
    RETURNS date
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT round($1::TIMESTAMP(0), $2)::DATE;
$BODY$;

ALTER FUNCTION round(date, text)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(date, text)
    IS 'Round dates according to the specified format';
	
CREATE OR REPLACE FUNCTION round(
	value timestamp with time zone)
    RETURNS timestamp with time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT round($1, 'DDD');
$BODY$;

ALTER FUNCTION round(timestamp with time zone)
    OWNER TO oms_owner;

COMMENT ON FUNCTION round(timestamp with time zone)
    IS 'Round dates according to the specified format';	

CREATE OR REPLACE FUNCTION last_day(
	pdate timestamp with time zone)
    RETURNS timestamp with time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT MAKE_TIMESTAMPTZ
    (
        EXTRACT('YEAR' FROM pDate)::INT,
        EXTRACT('MONTH' FROM pDate)::INT,
        EXTRACT('DAY' FROM (DATE_TRUNC('MONTH', pDate) +'1MONTH'::INTERVAL -'1 DAY'::INTERVAL))::INT,
        EXTRACT('HOUR' FROM PDATE)::INT,
        EXTRACT('MIN' FROM PDATE)::INT,
        EXTRACT('SEC' FROM PDATE)
    );
$BODY$;

ALTER FUNCTION last_day(timestamp with time zone)
    OWNER TO oms_owner;

CREATE OR REPLACE FUNCTION last_day(
	pdate timestamp without time zone)
    RETURNS timestamp without time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT MAKE_TIMESTAMP
    (
        EXTRACT('YEAR' FROM pDate)::INT,
        EXTRACT('MONTH' FROM pDate)::INT,
        EXTRACT('DAY' FROM (DATE_TRUNC('MONTH', pDate) + '1MONTH'::INTERVAL - '1 DAY'::INTERVAL))::INT,
        EXTRACT('HOUR' FROM pDate)::INT,
        EXTRACT('MIN' FROM pDate)::INT,
        EXTRACT('SEC' FROM pDate)
    );
$BODY$;

ALTER FUNCTION last_day(timestamp without time zone)
    OWNER TO oms_owner;

CREATE OR REPLACE FUNCTION last_day(
	pdate date)
    RETURNS date
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT (
        DATE_TRUNC('month', pDate) +'1month'::INTERVAL - '1 day'::INTERVAL
    )::DATE;
$BODY$;

ALTER FUNCTION last_day(date)
    OWNER TO oms_owner;
CREATE OR REPLACE FUNCTION months_between(
	enddate1 timestamp without time zone,
	startdate2 timestamp without time zone)
    RETURNS numeric
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT
    CASE
        WHEN DATE_TRUNC('DAY', $1) = DATE_TRUNC('DAY', $2) THEN
        
            0::NUMERIC

        WHEN EXTRACT(DAY FROM $1) = EXTRACT(DAY FROM $2) THEN

            ROUND
            (
                EXTRACT(YEARS FROM AGE($1, $2)) * 12 +
                EXTRACT(MONTHS FROM AGE($1, $2))  +
                EXTRACT(DAYS FROM AGE($1, $2)) /31 +
                EXTRACT(HOURS FROM AGE($1, $2)) /31 /24 +
                EXTRACT(SECONDS FROM AGE($1, $2)) /86400 /31
            )::NUMERIC

        WHEN DATE_TRUNC('DAY', last_day($1)) = DATE_TRUNC('DAY', $1) AND
            DATE_TRUNC('DAY', last_day($2)) = DATE_TRUNC('DAY', $2) THEN

            FLOOR
            (
                EXTRACT(YEARS FROM AGE($1, $2)) * 12 +
                EXTRACT(MONTHS FROM AGE($1, $2))  +
                EXTRACT(DAYS FROM AGE($1, $2)) /31 +
                EXTRACT(HOURS FROM AGE($1, $2)) /31 /24 +
                EXTRACT(SECONDS FROM AGE($1, $2)) /86400 /31
            )::NUMERIC

        ELSE

            (
                EXTRACT(YEARS FROM AGE($1, $2)) * 12 +
                EXTRACT(MONTHS FROM AGE($1, $2))  +
                EXTRACT(DAYS FROM AGE($1, $2)) /31 +
                EXTRACT(HOURS FROM AGE($1, $2)) /31 /24 +
                EXTRACT(SECONDS FROM AGE($1, $2)) /86400 /31
            )::NUMERIC

    END;
$BODY$;

ALTER FUNCTION months_between(timestamp without time zone, timestamp without time zone)
    OWNER TO oms_owner;
	
CREATE OR REPLACE FUNCTION instr(
	p_str text,
	p_substr text,
	p_pos numeric DEFAULT 1,
	p_occurrence numeric DEFAULT 1)
    RETURNS numeric
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL SAFE 
AS $BODY$
SELECT
        CASE

            WHEN LENGTH($1) = 0 OR LENGTH($2) = 0 THEN NULL::NUMERIC

            WHEN TRUNC($4) = 0 THEN 1/TRUNC($4)

            WHEN $4 < 0 THEN SQRT($4)

            ELSE

                (

                    WITH RECURSIVE t(str, shift, pos, tail, o, n) AS 
                    (
                        SELECT CASE WHEN TRUNC($3) < 0 THEN REVERSE($1) ELSE $1 END AS str,
                            0 AS shift,
                            CASE WHEN TRUNC($3) < 0 THEN -1 * TRUNC($3)::INT ELSE TRUNC($3)::INT END AS pos,
                            CASE WHEN TRUNC($3) < 0 THEN REVERSE($1) ELSE $1 END AS tail,
                            0 AS o,
                            CASE WHEN TRUNC($3) < 0 THEN REVERSE($2) ELSE $2 END AS n
                        UNION ALL
                        SELECT str,
                            shift + pos AS shift,
                            STRPOS(SUBSTR(str, shift + pos), n) AS pos,
                            SUBSTR(str, shift + pos) AS tail,
                            o + 1 AS o,
                            n
                        FROM t
                        WHERE pos <> 0
                    )
                    ,r AS
                    (
                        SELECT t.str,
                            t.shift,
                            t.pos,
                            t.tail,
                            t.o,
                            CASE
                                WHEN TRUNC($3) > 0 THEN
                                    t.pos + t.shift - 1
                                ELSE
                                    LENGTH(t.str) - t.pos - t.shift + 2
                            END cc
                        FROM t
                        WHERE t.o = TRUNC($4)
                        AND t.pos <> 0
                    )
                    SELECT COALESCE
                    (
                        (
                            SELECT r.cc
                            FROM r
                        ),
                        0
                    )::NUMERiC
                )

        END;
$BODY$;

ALTER FUNCTION instr(text, text, numeric, numeric)
    OWNER TO oms_owner;

COMMENT ON FUNCTION instr(text, text, numeric, numeric)
    IS 'This function searches a string for a substring using characters and returns the position in the string that is the first character of a specified occurrence of the substring.';

CREATE OR REPLACE FUNCTION substr(
	text,
	numeric)
    RETURNS text
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT
        CASE
            WHEN ABS(TRUNC($2)::INTEGER) > LENGTH($1) THEN
                
                NULL::TEXT

            WHEN TRUNC($2)::INTEGER >= 0 THEN

                SUBSTR($1, CASE WHEN TRUNC($2)::INTEGER = 0 THEN 1 ELSE TRUNC($2)::INTEGER END)

            ELSE
                
                SUBSTR($1, LENGTH($1) + TRUNC($2)::INTEGER + 1)
        END;
$BODY$;

ALTER FUNCTION substr(text, numeric)
    OWNER TO oms_owner;

COMMENT ON FUNCTION substr(text, numeric)
    IS 'Implements Oracle SUBSTR(). Original oms_ownerQL SUBSTR() uses different logic with negative arguments and rejects numeric argoments for position';

CREATE OR REPLACE FUNCTION substr(
	text,
	numeric,
	numeric)
    RETURNS text
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT
        CASE
            WHEN TRUNC($3)::INTEGER <= 0 THEN

                NULL::TEXT

            WHEN ABS(TRUNC($2)::INTEGER) > LENGTH($1) THEN
                
                NULL::TEXT

            WHEN TRUNC($2)::INTEGER >= 0 THEN

                SUBSTR($1, CASE WHEN TRUNC($2)::INTEGER = 0 THEN 1 ELSE TRUNC($2)::INTEGER END, TRUNC($3)::INTEGER)

            ELSE
                
                SUBSTR($1, LENGTH($1) + TRUNC($2)::INTEGER + 1, TRUNC($3)::INTEGER)
        END;
$BODY$;

ALTER FUNCTION substr(text, numeric, numeric)
    OWNER TO oms_owner;

COMMENT ON FUNCTION substr(text, numeric, numeric)
    IS 'Implements Oracle SUBSTR(). Original oms_ownerQL SUBSTR() uses different logic with negative arguments and rejects numeric argoments for position and length';

CREATE OR REPLACE FUNCTION add_months(
	pdate timestamp without time zone,
	pmonths integer)
    RETURNS timestamp without time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT
        CASE
            WHEN last_day(pdate) <> pdate THEN
                pdate + MAKE_INTERVAL(months => pmonths)
            ELSE 
                last_day(pDate + MAKE_INTERVAL(months => pmonths))
    END;
$BODY$;

ALTER FUNCTION add_months(timestamp without time zone, integer)
    OWNER TO oms_owner;




CREATE OR REPLACE FUNCTION from_tz(
	timestamp_value timestamp without time zone,
	time_zone_value character varying)
    RETURNS timestamp with time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
SELECT
        CASE

            WHEN LENGTH($2) = 0 THEN

                NULL::TIMESTAMP WITH TIME ZONE

            ELSE

                MAKE_TIMESTAMPTZ
                (
                    EXTRACT(YEAR FROM $1)::INTEGER,
                    EXTRACT(MONTH FROM $1)::INTEGER,
                    EXTRACT(DAY FROM $1)::INTEGER,
                    EXTRACT(HOUR FROM $1)::INTEGER,
                    EXTRACT(MIN FROM $1)::INTEGER,
                    EXTRACT(SEC FROM $1),
                    CONCAT
                    (
                        CASE WHEN $2 !~* '[\-\+]' THEN '+' ELSE '' END,
                        REPLACE($2, ' ', '')
                    )
                )

        END;
$BODY$;

ALTER FUNCTION from_tz(timestamp without time zone, character varying)
    OWNER TO oms_owner;

CREATE OR REPLACE FUNCTION to_char(
	pval character varying)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE STRICT PARALLEL UNSAFE
AS $BODY$
DECLARE
    vResult VARCHAR DEFAULT NULL;
BEGIN

    IF pVal IS NOT NULL AND pVal <> '' THEN
    
        vResult := to_char_formatter
        (
            pVal => pVal
        );
    END IF;

    RETURN vResult;
    
END;
$BODY$;

ALTER FUNCTION to_char(character varying)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char(character varying)
    IS 'to_char convertion VARCHAR2 as Oracle does';
CREATE OR REPLACE FUNCTION to_char(
	pval character)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    vResult VARCHAR DEFAULT NULL;
    vargs TEXT;
BEGIN

    IF pVal IS NOT NULL AND pVal <> '' THEN
        

        vResult := to_char_formatter
        (
            pVal => pVal
        );

    END IF;

    RETURN vResult;
    
END;
$BODY$;

ALTER FUNCTION to_char(character)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char(character)
    IS 'to_char convertion CHAR as Oracle does';
CREATE OR REPLACE FUNCTION to_char(
	pval date)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    vFmt VARCHAR;
BEGIN

    SELECT value
    INTO vFmt
    FROM v$nls_parameters
    WHERE parameter = 'NLS_DATE_FORMAT';

    RETURN to_char
    (
        pVal => pVal::TIMESTAMP(0),
        pFmt => vFmt
    );

END;
$BODY$;

ALTER FUNCTION to_char(date)
    OWNER TO oms_owner;

CREATE OR REPLACE FUNCTION to_char(
	pval double precision)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    vFmt     VARCHAR;
    vResult  VARCHAR DEFAULT NULL;
BEGIN

    IF pVal IS NOT NULL THEN
        

        vFmt := to_char_formatter
        (
            pVal      => pVal,
            pFormat   => '', -- not used
            pNoNumberFormat => '' -- value is just a flag
        );

        vResult := vFmt;

    END IF;

    RETURN vResult;

END;
$BODY$;

ALTER FUNCTION to_char(double precision)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char(double precision)
    IS 'to_char convertion Number as Oracle does';
CREATE OR REPLACE FUNCTION to_char(
	pval double precision,
	pfmt character varying,
	pnlsparam character varying DEFAULT NULL::character varying)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    vFmt     VARCHAR;
    vResult  VARCHAR DEFAULT NULL;
BEGIN

    IF pVal IS NOT NULL THEN

        vFmt := to_char_formatter
        (
            pVal      => pVal,
            pFormat   => pFmt,
            pNlsParam => pNlsParam
        );

        vResult := vFmt;

    END IF;

    RETURN vResult;

END;
$BODY$;

ALTER FUNCTION to_char(double precision, character varying, character varying)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char(double precision, character varying, character varying)
    IS 'to_char convertion Number as Oracle does. Some Format models could return incorrect results. Only NLS_DATE_LANGUAGE is allowed for NLS param. Value must be correct as lc_time value';
CREATE OR REPLACE FUNCTION to_char(
	pval integer)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE STRICT PARALLEL UNSAFE
AS $BODY$
DECLARE
    vFmt     VARCHAR;
    vResult  VARCHAR DEFAULT NULL;
BEGIN

    IF pVal IS NOT NULL THEN

        vFmt := to_char_formatter
        (
            pVal      => pVal,
            pFormat   => '', -- not used
            pNoNumberFormat => '' -- value is just a flag
        );

        vResult := vFmt;

    END IF;

    RETURN vResult;

END;
$BODY$;

ALTER FUNCTION to_char(integer)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char(integer)
    IS 'to_char convertion Number as Oracle does';
CREATE OR REPLACE FUNCTION to_char(
	pval integer,
	pfmt character varying,
	pnlsparam character varying DEFAULT NULL::character varying)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    vFmt     VARCHAR;
    vResult  VARCHAR DEFAULT NULL;
BEGIN

    IF pVal IS NOT NULL THEN
        

        vFmt := to_char_formatter
        (
            pVal      => pVal,
            pFormat   => pFmt,
            pNlsParam => pNlsParam
        );

        vResult := vFmt;

    END IF;

    RETURN vResult;

END;
$BODY$;

ALTER FUNCTION to_char(integer, character varying, character varying)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char(integer, character varying, character varying)
    IS 'to_char convertion Number as Oracle does. Some Format models could return incorrect results. Only NLS_DATE_LANGUAGE is allowed for NLS param. Value must be correct as lc_time value';
CREATE OR REPLACE FUNCTION to_char(
	pval interval)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
BEGIN

    RETURN to_char
    (
        pVal => pVal,
        pFmt => 'd' -- won't be used, just to pass something correct
    );
    
END;
$BODY$;

ALTER FUNCTION to_char(interval)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char(interval)
    IS 'to_char convertion interval with TZ as Oracle does';
CREATE OR REPLACE FUNCTION to_char(
	pval interval,
	pfmt character varying,
	pnlsparam character varying DEFAULT NULL::character varying)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    vResult VARCHAR DEFAULT NULL;
    vFmt VARCHAR;
BEGIN

    IF pVal IS NOT NULL THEN
        
        IF LENGTH(pFmt) <> 0 THEN

            vFmt := to_char_formatter
            (
                pVal      => pVal,
                pFormat   => pFmt,
                pNlsParam => pNlsParam
            );
            -- it is year to month 
            IF pVal::TEXT ~* 'YEAR' OR pVal::TEXT ~* 'MON' THEN

                vResult := CONCAT
                (
                    COALESCE(SUBSTRING(EXTRACT(year FROM pVal)::TEXT FROM '-'),'+'),
                    CASE
                        WHEN EXTRACT(year FROM pVal) < 2 THEN
                            LPAD(SUBSTRING(EXTRACT(year FROM pVal)::TEXT FROM '\d+'), 2, '0')
                        ELSE
                            SUBSTRING(EXTRACT(year FROM pVal)::TEXT FROM '\d+')
                    END,
                    '-',
                    LPAD(ABS(EXTRACT(month FROM pVal))::TEXT, 2, '0')
                );
            -- it is day to second
            ELSE

                vResult := CONCAT
                (
                    COALESCE(SUBSTRING(EXTRACT(day FROM pVal)::TEXT FROM '\-'),'+'),
                    LPAD(SUBSTRING(EXTRACT(day FROM pVal)::TEXT FROM '\d+'), 2, '0'),
                    ' ',
                    LPAD(EXTRACT(hour FROM pVal)::TEXT, 2, '0'),
                    ':',
                    LPAD(EXTRACT(minute FROM pVal)::TEXT, 2, '0'),
                    ':',
                    CASE 
                        WHEN STRPOS(pVal::TEXT, '.') >0 THEN
                            REGEXP_REPLACE(LPAD(EXTRACT(microseconds FROM pVal)::TEXT, 8, '0'), '(\d\d)(\d+)', '\1.\2')
                        ELSE
                            LPAD(EXTRACT(seconds FROM pVal)::TEXT, 2, '0')
                    END
                );

            END IF;

        END IF;

    END IF;

    RETURN vResult;

END;
$BODY$;

ALTER FUNCTION to_char(interval, character varying, character varying)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char(interval, character varying, character varying)
    IS 'to_char convertion interval with TZ as Oracle does. Some Format models could return incorrect results. Only NLS_DATE_LANGUAGE is allowed for NLS param. Value must be correct as lc_time value';
CREATE OR REPLACE FUNCTION to_char(
	pval numeric)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    vFmt     VARCHAR;
    vResult  VARCHAR DEFAULT NULL;
BEGIN

    IF pVal IS NOT NULL THEN
        
        vFmt := to_char_formatter
        (
            pVal      => pVal,
            pFormat   => '', -- not used
            pNoNumberFormat => '' -- value is just a flag
        );

        vResult := vFmt;

    END IF;

    RETURN vResult;

END;
$BODY$;

ALTER FUNCTION to_char(numeric)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char(numeric)
    IS 'to_char convertion Number as Oracle does';
CREATE OR REPLACE FUNCTION to_char(
	pval numeric,
	pfmt character varying,
	pnlsparam character varying DEFAULT NULL::character varying)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    vFmt     VARCHAR;
    vResult  VARCHAR DEFAULT NULL;
BEGIN

    IF pVal IS NOT NULL THEN

        vFmt := to_char_formatter
        (
            pVal      => pVal,
            pFormat   => pFmt,
            pNlsParam => pNlsParam
        );

        vResult := vFmt;

    END IF;

    RETURN vResult;

END;
$BODY$;

ALTER FUNCTION to_char(numeric, character varying, character varying)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char(numeric, character varying, character varying)
    IS 'to_char convertion Number as Oracle does. Some Format models could return incorrect results. Only NLS_DATE_LANGUAGE is allowed for NLS param. Value must be correct as lc_time value';
CREATE OR REPLACE FUNCTION to_char(
	pval real)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE STRICT PARALLEL UNSAFE
AS $BODY$
DECLARE
    vFmt     VARCHAR;
    vResult  VARCHAR DEFAULT NULL;
BEGIN

    IF pVal IS NOT NULL THEN

        vFmt := to_char_formatter
        (
            pVal      => pVal,
            pFormat   => '', -- not used
            pNoNumberFormat => '' -- value is just a flag
        );

        vResult := vFmt;

    END IF;

    RETURN vResult;

END;
$BODY$;

ALTER FUNCTION to_char(real)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char(real)
    IS 'to_char convertion Number as Oracle does';
CREATE OR REPLACE FUNCTION to_char(
	pval real,
	pfmt character varying,
	pnlsparam character varying DEFAULT NULL::character varying)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    vFmt     VARCHAR;
    vResult  VARCHAR DEFAULT NULL;
BEGIN

    IF pVal IS NOT NULL THEN

        vFmt := to_char_formatter
        (
            pVal      => pVal,
            pFormat   => pFmt,
            pNlsParam => pNlsParam
        );

        vResult := vFmt;

    END IF;

    RETURN vResult;

END;
$BODY$;

ALTER FUNCTION to_char(real, character varying, character varying)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char(real, character varying, character varying)
    IS 'to_char convertion Number as Oracle does. Some Format models could return incorrect results. Only NLS_DATE_LANGUAGE is allowed for NLS param. Value must be correct as lc_time value';
CREATE OR REPLACE FUNCTION to_char(
	pval text)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE STRICT PARALLEL UNSAFE
AS $BODY$
DECLARE
    vResult VARCHAR DEFAULT NULL;
BEGIN

    IF pVal IS NOT NULL AND pVal <> '' THEN
        
            vResult := to_char_formatter
            (
                pVal => pVal
            );

    END IF;

    RETURN vResult;
    
END;
$BODY$;

ALTER FUNCTION to_char(text)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char(text)
    IS 'to_char convertion CLOB as Oracle does';
CREATE OR REPLACE FUNCTION to_char(
	pval timestamp with time zone)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    vFmt VARCHAR;
BEGIN

    SELECT value
    INTO vFmt
    FROM v$nls_parameters
    WHERE parameter = 'NLS_TIMESTAMP_TZ_FORMAT';

    RETURN to_char
    (
        pVal => pVal,
        pFmt => vFmt
    );
    
END;
$BODY$;

ALTER FUNCTION to_char(timestamp with time zone)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char(timestamp with time zone)
    IS 'to_char convertion timestamp with TZ as Oracle does';
CREATE OR REPLACE FUNCTION to_char(
	pval timestamp with time zone,
	pfmt character varying,
	pnlsparam character varying DEFAULT NULL::character varying)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    vResult VARCHAR DEFAULT NULL;
    vFmt VARCHAR;
BEGIN

    IF pVal IS NOT NULL THEN

        vFmt := to_char_formatter
        (
            pVal      => pVal,
            pFormat   => pFmt,
            pNlsParam => pNlsParam
        );

        vResult := TO_CHAR(pVal, vFmt);

    END IF;

    RETURN vResult;

END;
$BODY$;

ALTER FUNCTION to_char(timestamp with time zone, character varying, character varying)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char(timestamp with time zone, character varying, character varying)
    IS 'to_char convertion timestamp with TZ as Oracle does. Some Format models could return incorrect results. Only NLS_DATE_LANGUAGE is allowed for NLS param. Value must be correct as lc_time value';
CREATE OR REPLACE FUNCTION to_char(
	pval timestamp without time zone)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    vFmt VARCHAR;
BEGIN

    IF SUBSTRING(TO_CHAR(EXTRACT('epoch' FROM pVal), '9999999999D9999999'),12,6) = '.00000' THEN

        SELECT value
        INTO vFmt
        FROM v$nls_parameters
        WHERE parameter = 'NLS_DATE_FORMAT';

    ELSE

        SELECT value
        INTO vFmt
        FROM v$nls_parameters
        WHERE parameter = 'NLS_TIMESTAMP_FORMAT';

    END IF;

    RETURN to_char
    (
        pVal => pVal,
        pFmt => vFmt
    );

END;
$BODY$;

ALTER FUNCTION to_char(timestamp without time zone)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char(timestamp without time zone)
    IS 'to_char convertion date or timestamp without TZ as Oracle does';
CREATE OR REPLACE FUNCTION to_char(
	pval timestamp without time zone,
	pfmt character varying,
	pnlsparam character varying DEFAULT NULL::character varying)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    vResult VARCHAR DEFAULT NULL;
    vFmt VARCHAR;
BEGIN

    IF pVal IS NOT NULL THEN

        vFmt := to_char_formatter
        (
            pVal      => pVal,
            pFormat   => pFmt,
            pNlsParam => pNlsParam
        );

        vResult := TO_CHAR(pVal, vFmt);

    END IF;

    RETURN vResult;

END;
$BODY$;

ALTER FUNCTION to_char(timestamp without time zone, character varying, character varying)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char(timestamp without time zone, character varying, character varying)
IS 'to_char convertion date or timestamp without TZ as Oracle does. Some Format models could return incorrect results. Only NLS_DATE_LANGUAGE is allowed for NLS param. Value must be correct as lc_time value';

CREATE OR REPLACE FUNCTION to_char(
	pval date,
	pfmt character varying,
	pnlsparam character varying DEFAULT NULL::character varying)
    RETURNS character varying
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
SELECT to_char
    (
        $1::TIMESTAMP(0),
        $2,
        $3
    );
$BODY$;

ALTER FUNCTION to_char(date, character varying, character varying)
    OWNER TO oms_owner;

CREATE OR REPLACE FUNCTION to_date(
	pval character varying)
    RETURNS timestamp without time zone
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    vResult TIMESTAMP DEFAULT NULL;
    vFmt VARCHAR;
BEGIN
    
    SELECT value
    INTO vFmt
    FROM v$nls_parameters
    WHERE parameter = 'NLS_DATE_FORMAT';

    vResult := to_date
    (
        pVal => pVal,
        pFmt => vFmt
    );

    RETURN vResult::TIMESTAMP(0);

END;
$BODY$;

ALTER FUNCTION to_date(character varying)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_date(character varying)
    IS 'Converts VARCHAR2 to DATE as Oracle does';
CREATE OR REPLACE FUNCTION to_date(
	pval character varying,
	pfmt character varying,
	pnlsparam character varying DEFAULT NULL::character varying)
    RETURNS timestamp without time zone
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE

    vResult TIMESTAMP DEFAULT NULL;
    vFmt VARCHAR;
    vValFormatted VARCHAR;

BEGIN

    IF pVal IS NOT NULL AND pVal <> '' THEN
        
        IF pNlsParam IS NULL THEN

            SELECT f.dmodelto
            INTO vFmt
            FROM tdfmt f
            WHERE f.dmodel = pFmt;

            IF FOUND THEN

                RETURN TO_TIMESTAMP(pVal, vFmt)::TIMESTAMP(0);

            END IF;

        END IF;
        
        SELECT vval,
            format
        INTO vValFormatted,
            vFmt
        FROM to_date_formatter
        (
            pVal      => pVal,
            pFormat   => pFmt,
            pNlsParam => pNlsParam
        );

        vResult := TO_TIMESTAMP(vValFormatted, vFmt);

    END IF;

    RETURN vResult::TIMESTAMP(0);

END;
$BODY$;

ALTER FUNCTION to_date(character varying, character varying, character varying)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_date(character varying, character varying, character varying)
    IS 'Converts VARCHAR2 to DATE as Oracle does. Some Format models could return incorrect results. Only NLS_DATE_LANGUAGE is allowed for NLS param. Value must be correct as lc_time value';
CREATE OR REPLACE FUNCTION to_number(
	pval character varying)
    RETURNS numeric
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE STRICT PARALLEL UNSAFE
AS $BODY$
DECLARE
    vResult NUMERIC DEFAULT NULL;
BEGIN

    IF pVal IS NOT NULL AND pVal <> '' THEN

        vResult := to_number_formatter
        (
            pVal => pVal,
            pNoNumberFormat => 'Y'
        );

    END IF;

    RETURN vResult;

END;
$BODY$;

ALTER FUNCTION to_number(character varying)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_number(character varying)
    IS 'Converts VARCHAR to NUMBER when no additional params set';
CREATE OR REPLACE FUNCTION to_number(
	pval character varying,
	pformat character varying,
	pnlsparam character varying DEFAULT NULL::character varying)
    RETURNS numeric
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    vResult NUMERIC DEFAULT NULL;
    vFormat VARCHAR;
BEGIN

    IF pVal IS NOT NULL AND pVal <> '' THEN

        IF pNlsParam IS NULL THEN

            SELECT f.nmodel
            INTO vFormat
            FROM tnfmt f
            WHERE f.nmodel = pFormat;

            IF FOUND THEN

                 RETURN TO_NUMBER(pVal, vFormat);

            END IF;

        END IF;

        vResult := to_number_formatter
        (
            pVal      => pVal,
            pFormat   => pFormat,
            pNlsParam => pNlsParam
        );

    END IF;
    
    RETURN vResult;

END;
$BODY$;

ALTER FUNCTION to_number(character varying, character varying, character varying)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_number(character varying, character varying, character varying)
    IS 'Converts VARCHAR to NUMBER. Some format models could return incorrect result. NLS params must be valid.';
CREATE OR REPLACE FUNCTION to_number(
	pval double precision)
    RETURNS numeric
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE STRICT PARALLEL UNSAFE
AS $BODY$
BEGIN

    RETURN number_limit_correct(pVal::NUMERIC);

END;
$BODY$;

ALTER FUNCTION to_number(double precision)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_number(double precision)
    IS 'Converts BINARY_DOUBLE to NUMBER, no additional params allowed';
CREATE OR REPLACE FUNCTION to_number(
	pval numeric)
    RETURNS numeric
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE STRICT PARALLEL UNSAFE
AS $BODY$
BEGIN

    RETURN number_limit_correct(pVal);

END;
$BODY$;

ALTER FUNCTION to_number(numeric)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_number(numeric)
    IS 'Converts NUMBER to NUMBER when no additional params set';
CREATE OR REPLACE FUNCTION to_number(
	pval numeric,
	pformat character varying,
	pnlsparam character varying DEFAULT NULL::character varying)
    RETURNS numeric
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    vResult NUMERIC DEFAULT NULL;
    vFormat VARCHAR;
BEGIN

    IF pVal IS NOT NULL THEN

        IF pNlsParam IS NULL THEN

            SELECT f.nmodel
            INTO vFormat
            FROM tnfmt f
            WHERE f.nmodel = pFormat;

            IF FOUND THEN

                 RETURN TO_NUMBER(number_limit_correct(pVal), vFormat);

            END IF;

        END IF;

        vResult := to_number_formatter
        (
            pVal      => pVal,
            pFormat   => pFormat,
            pNlsParam => pNlsParam
        );

    END IF;
    
    RETURN vResult;

END;
$BODY$;

ALTER FUNCTION to_number(numeric, character varying, character varying)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_number(numeric, character varying, character varying)
    IS 'Converts NUMBER to NUMBER when additional params are set. Some format models could return incorrect result. NLS params must be valid.';
CREATE OR REPLACE FUNCTION to_number(
	pval real)
    RETURNS numeric
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE STRICT PARALLEL UNSAFE
AS $BODY$
BEGIN

    RETURN number_limit_correct(pVal::NUMERIC);

END;
$BODY$;

ALTER FUNCTION to_number(real)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_number(real)
    IS 'Converts BINARY_FLOAT to NUMBER, no additional params allowed';
CREATE OR REPLACE FUNCTION userenv(
	parameter character varying)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE STRICT PARALLEL UNSAFE
AS $BODY$
begin
  if upper(parameter) in ('ISDBA', 'LANG', 'LANGUAGE', 'CLIENT_INFO', 'TERMINAL') then
  return SYS_CONTEXT_USERENV(parameter);
  else raise EXCEPTION 'invalid USERENV parameter';  
  end if;
end;
$BODY$;

ALTER FUNCTION userenv(character varying)
    OWNER TO oms_owner;	
	
CREATE TABLE ext_session_local_tz
(
    tz_offset numeric
);



ALTER TABLE ext_session_local_tz
    OWNER to oms_owner;	

CREATE OR REPLACE FUNCTION sysdate(
	)
    RETURNS timestamp without time zone
    LANGUAGE 'sql'
    COST 100
    STABLE PARALLEL UNSAFE
AS $BODY$
SELECT CONCAT
    (
        COALESCE
        (
            MAX(tz_offset),
            0
        )::TEXT,
        ' HOURS'
    )::INTERVAL + clock_timestamp()::TIMESTAMP(0) 
    FROM ext_session_local_tz;
$BODY$;

ALTER FUNCTION sysdate()
    OWNER TO oms_owner;

CREATE TABLE dfmt
(
    dmodel character varying(4000) COLLATE pg_catalog."default" NOT NULL,
    dmodelto character varying(4000) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT dfmt_pkey PRIMARY KEY (dmodel)
)

TABLESPACE pg_default;

ALTER TABLE dfmt
    OWNER to oms_owner;
	
CREATE TABLE nfmt
(
    nmodel character varying(4000) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT nfmt_pkey PRIMARY KEY (nmodel)
)

TABLESPACE pg_default;

ALTER TABLE nfmt
    OWNER to oms_owner;

CREATE TABLE tdfmt
(
    dmodel character varying(4000) COLLATE pg_catalog."default" NOT NULL,
    dmodelto character varying(4000) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tdfmt_pkey PRIMARY KEY (dmodel)
)

TABLESPACE pg_default;

ALTER TABLE tdfmt
    OWNER to oms_owner;

CREATE TABLE tnfmt
(
    nmodel character varying(4000) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tnfmt_pkey PRIMARY KEY (nmodel)
)

TABLESPACE pg_default;

ALTER TABLE tnfmt
    OWNER to oms_owner;	

CREATE OR REPLACE FUNCTION systimestamp(
	)
    RETURNS timestamp with time zone
    LANGUAGE 'sql'
    COST 100
    STABLE PARALLEL UNSAFE
AS $BODY$
SELECT CONCAT
    (
        COALESCE
        (
            MAX(tz_offset),
            0
        )::TEXT,
        ' HOURS'
    )::INTERVAL + clock_timestamp() 
    FROM ext_session_local_tz;
$BODY$;

ALTER FUNCTION systimestamp()
    OWNER TO oms_owner;
		

CREATE TABLE format_models
(
    fmt character varying(128) COLLATE pg_catalog.default NOT NULL,
    tp character varying(128) COLLATE pg_catalog.default NOT NULL,
    ord integer,
    grp character varying(128) COLLATE pg_catalog.default NOT NULL,
    wrapto character varying(128) COLLATE pg_catalog.default,
    CONSTRAINT format_models_pk PRIMARY KEY (tp, grp, fmt)
)

TABLESPACE pg_default;

ALTER TABLE format_models
    OWNER to oms_owner;

COMMENT ON TABLE format_models
    IS 'Possible format models for to_char from Oracle';

COMMENT ON COLUMN format_models.fmt
    IS 'Format model value';

COMMENT ON COLUMN format_models.tp
    IS 'Format model type: datetime/numeric';

COMMENT ON COLUMN format_models.ord
    IS 'Used in format model parser to select model best match';

COMMENT ON COLUMN format_models.grp
    IS 'Used in format model parser to speed up search';

COMMENT ON COLUMN format_models.wrapto
    IS 'Used in format model parser to replace Oracle model with PG model if needed';
	

CREATE OR REPLACE FUNCTION to_char_parser(
	pstr character varying,
	ptype character varying,
	pfm character varying DEFAULT NULL::character varying)
    RETURNS TABLE(ok character varying, tail character varying, fm character varying) 
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
    ROWS 1

AS $BODY$
DECLARE
    vRes VARCHAR;
    vStr VARCHAR;
    vPos INTEGER;
    vChunk VARCHAR;
    vGrp VARCHAR;
    vWrapTo VARCHAR;
    vYYYY VARCHAR;
    
    curDateFmtModels CURSOR (pGrp VARCHAR, pYYY VARCHAR) FOR
    SELECT fm.fmt,
        fm.wrapto
    FROM format_models fm
    WHERE tp = 'datetime'
    AND grp = pGrp
    AND (pYYY IS NULL OR (pYYY IS NOT NULL AND fm.fmt = pYYY))
    ORDER BY ord,
        fmt;

    eDateFormatNotRecognized CONSTANT VARCHAR := 'ORA-01821: date format not recognized';

    curNumberFmtModels CURSOR (pGrp VARCHAR) FOR
    SELECT fmt
    FROM format_models fm
    WHERE tp = 'number'
    AND grp = pGrp
    ORDER BY grp, ord;

    eInvalidNumberFormat CONSTANT VARCHAR := 'ORA-01481: invalid number format model';

    vModel VARCHAR;

    vFM VARCHAR;
BEGIN

    vStr := pStr;
    vPos := 1;

    vFm := COALESCE(pFm,'');

    CASE
        
        WHEN pType = 'number' THEN

            vGrp := UPPER(SUBSTR(vStr,1,1));
            
            OPEN curNumberFmtModels(vGrp);
            
            LOOP
                            
                FETCH curNumberFmtModels INTO vModel;
                
                EXIT WHEN NOT FOUND;

                vChunk := SUBSTR(vStr,1,  LENGTH(REPLACE(vModel, '\', '')));

                IF UPPER(vChunk) <> REPLACE(vModel, '\', '') THEN

                    vChunk := NULL;

                END IF;
                            
                IF vChunk IS NOT NULL THEN

                    vRes := CONCAT(vRes, SUBSTR(vStr, 1, LENGTH(vChunk)));
                    vPos := vPos + LENGTH(vChunk);
                    vStr := SUBSTR(vStr, vPos);

                    EXIT;

                END IF;

            END LOOP;

            CLOSE curNumberFmtModels;

            IF vChunk IS NULL THEN
                            
                RAISE USING MESSAGE := eInvalidNumberFormat;

            END IF;

        ELSE

            LOOP
            
                    -- special treat for FM/FX
                    IF UPPER(SUBSTR(vStr,1,2)) = 'FM' THEN

                        vFM := CASE WHEN vFM <> 'FM' THEN 'FM' ELSE '' END;
                        vStr := SUBSTR(vStr,3);
                    
                    END IF;

                    --If quotted - grab it
                    IF SUBSTR(vStr,1,1) = '"' THEN

                        vChunk := SUBSTRING(vStr FROM '"[^"]*"');

                        -- if unclosed quota - raise
                        IF vChunk IS NULL THEN

                            RAISE USING MESSAGE := eDateFormatNotRecognized;

                        END IF;

                        vRes := CONCAT(vFm, vRes, vChunk);
                        vPos := vPos + LENGTH(vChunk);
                        vStr := SUBSTR(vStr, vPos);

                        EXIT;

                    END IF;

                    -- spacers grab it
                    -- we will treat , and . specialy because of A.M. likes or Y,YYYY
                    IF SUBSTR(vStr,1,1) ~ '^[\.\,]' THEN

                        vChunk := SUBSTRING(vStr FROM '[\.\,]+');
                        vRes := CONCAT(vFM, vRes, vChunk);
                        vPos := vPos + LENGTH(vChunk);
                        vStr := SUBSTR(vStr, vPos);

                        EXIT;

                    END IF;

                    IF SUBSTR(vStr,1,1) ~ '[\s\!\\\/\#\$\%\&\''\(\)\*\+\\\-\:\;\<\=\>\?\@\[\]\^\_\`\{\|\}\~]' THEN
                        
                        vChunk := SUBSTRING(vStr FROM '[\s\!\\\/\#\$\%\&\''\(\)\*\+\\\-\:\;\<\=\>\?\@\[\]\^\_\`\{\|\}\~]+');
                        vRes := CONCAT(vFM, vRes, vChunk);
                        vPos := vPos + LENGTH(vChunk);
                        vStr := SUBSTR(vStr, vPos);

                        EXIT;

                    END IF;

                    vGrp := UPPER(SUBSTR(vStr,1,1));
                    
                    IF vGrp = 'Y' THEN
			
                        CASE
                            WHEN UPPER(SUBSTR(vStr,1,9)) IN ('Y,YYYTHSP', 'Y,YYYSPTH') THEN
                                vYYYY := REPLACE(UPPER(SUBSTR(vStr,1,9)),',','\,');
                            WHEN UPPER(SUBSTR(vStr,1,7)) IN ('Y,YYYTH', 'Y,YYYSP') THEN
                                vYYYY := REPLACE(UPPER(SUBSTR(vStr,1,7)),',','\,');
                            WHEN UPPER(SUBSTR(vStr,1,5)) = 'Y,YYY' THEN
                                vYYYY := REPLACE(UPPER(SUBSTR(vStr,1,5)),',','\,');
                            ELSE
                                vYYYY := NULL;
                        END CASE;

                    END IF;

                    OPEN curDateFmtModels(CASE WHEN vGrp = 'E' THEN 'Y' ELSE vGrp END, vYYYY);

                    LOOP
                        
                        FETCH curDateFmtModels INTO vModel, vWrapTo;
                        
                        EXIT WHEN NOT FOUND;
        
                        vChunk := SUBSTR(vStr,1,  LENGTH(REPLACE(vModel, '\', '')));

                        IF UPPER(vChunk) <> REPLACE(vModel, '\', '') THEN

                            vChunk := NULL;

                        END IF;
                        
                        IF vChunk IS NOT NULL THEN

                            vRes := CONCAT
                            (
                                vFM,
                                vRes,
                                CASE 
                                    WHEN vWrapTo IS NOT NULL THEN
                                        CASE 
                                            WHEN  vWrapTo = 'R->Y' THEN
                                                REPLACE(REPLACE(vChunk, 'R', 'Y'), 'r', 'y')
                                            ELSE
                                                REGEXP_REPLACE(SUBSTR(vStr, 1, LENGTH(vChunk)), CONCAT('(',SUBSTR(vStr, 1, LENGTH(vChunk)),')'), vWrapTo)
                                        END
                                    ELSE 
                                        SUBSTR(vStr, 1, LENGTH(vChunk)) 
                                END
                            );
                            vPos := vPos + LENGTH(vChunk);
                            vStr := SUBSTR(vStr, vPos);

                            EXIT;

                        END IF;

                    END LOOP;

                    CLOSE curDateFmtModels;

                    IF vChunk IS NULL THEN
                        
                        RAISE USING MESSAGE := eDateFormatNotRecognized;

                    END IF;

                    EXIT;

                END LOOP;

        END CASE;

    RETURN QUERY VALUES (vRes, CASE WHEN LENGTH(vStr) = 0 THEN NULL::VARCHAR ELSE vStr END, vFM);
        
END;
$BODY$;

ALTER FUNCTION to_char_parser(character varying, character varying, character varying)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char_parser(character varying, character varying, character varying)
    IS 'Parse to_char format parameter value to check it against Oracle rules';
	
CREATE OR REPLACE FUNCTION to_char_formatter(
	pval anyelement,
	pformat character varying DEFAULT NULL::character varying,
	pnlsparam character varying DEFAULT NULL::character varying,
	pnonumberformat character varying DEFAULT NULL::character varying)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    vFmt VARCHAR;
    rec RECORD;
    vResult VARCHAR;
    vDataType VARCHAR;
    vIsDateTime BOOLEAN DEFAULT FALSE;
    cAllowedDatatypes CONSTANT VARCHAR[] := ARRAY
    [
        -- numeric
        'REAL',
        'DOUBLE PRECISION',
        'NUMERIC',
        'INTEGER',
        --datetime
        'INTERVAL',
        'TIMESTAMP WITHOUT TIME ZONE',
        'TIMESTAMP WITH TIME ZONE',
        -- char
        'CHARACTER',
        'CHARACTER VARYING',
        'TEXT'

    ];
    cpellmodels CONSTANT TEXT[] := ARRAY
    [
        'SSSP',
        'Y,YYYSP',
        'SYYYYSP',
        'YYYYSP',
        'YYYSP',
        'YYSP',
        'YSP',
        'SCCSP',
        'CCSP',
        'RRRRSP',
        'RRSP',
        'HH24SP',
        'HH12SP',
        'HHSP',
        'DDDSP',
        'DDSP',
        'DSP',
        'MISP',
        'MMSP',
        'QSP',
        'TZHSP',
        'TZMSP',
        'FFSP',
        'FF1SP',
        'FF2SP',
        'FF3SP',
        'FF4SP',
        'FF5SP',
        'FF6SP',
        'FF7SP',
        'FF8SP',
        'FF9SP'
    ];
    vIsNumeric BOOLEAN DEFAULT FALSE;
    vIsChar BOOLEAN DEFAULT FALSE;

    verrorMessages CONSTANT VARCHAR[] := ARRAY
    [
        'ORA-01722: invalid number', --1
        'ORA-01821: date format not recognized', --2
        'ORA-01822: era format code is not valid with this calendar', --3
        'not implemented', --4
        'ORA-12702: invalid NLS parameter string used in SQL function', --5
        'ORA-01481: invalid number format model', --6
        'ORA-22835: Buffer too small for CLOB to CHAR', --7
        'ORA-00902: invalid datatype' --8
    ];

    v_lctime VARCHAR;
    vNlsParam VARCHAR;
    vNlsParamCopy VARCHAR;
    vDayMaxLengh INTEGER;
    vDyMaxLengh INTEGER;
    vMonthMaxLength INTEGER;
    vMonMaxLength INTEGER;
    --used to calculate localized day/month names
    cDayMonthNumbers CONSTANT INTEGER[] := ARRAY[1,2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
    vTm VARCHAR;

    vtail VARCHAR;
    vfm VARCHAR;

    vurrisset BOOLEAN DEFAULT FALSE;
    vGroupSeparator VARCHAR(2) DEFAULT NULL;
    vDecimalSeparator VARCHAR(2) DEFAULT NULL;
    vCurrencySymbol VARCHAR;
    vIsoCurrency VARCHAR;
    vDualCurrency VARCHAR;
    bd BOOLEAN DEFAULT FALSE;
    vln INTEGER;

BEGIN

    vDataType := UPPER(PG_TYPEOF(pVal)::TEXT);

    IF ARRAY_POSITION(cAllowedDatatypes,  vDataType) IS NULL THEN

        RAISE USING MESSAGE := verrorMessages[8];

    END IF;

    CASE
        WHEN vDataType IN
        (
            'INTERVAL',
            'TIMESTAMP WITHOUT TIME ZONE',
            'TIMESTAMP WITH TIME ZONE'
        ) THEN

            vIsDateTime := TRUE;

        WHEN vDataType IN
        (
            'REAL',
            'DOUBLE PRECISION',
            'NUMERIC',
            'INTEGER'
        ) THEN

            vIsNumeric := TRUE;

        ELSE

            vIsChar := TRUE; 

    END CASE;

    IF (vIsDateTime OR  (vIsNumeric AND pNoNumberFormat IS NULL)) AND (pFormat IS NULL OR pFormat = '') THEN

        IF vIsNumeric AND pFormat = '' THEN

            vResult := '#';
        
        ELSE

            vResult := NULL;

        END IF;

    ELSE

        CASE
            WHEN vIsDateTime AND vDataType <> 'INTERVAL' AND pFormat ~* '^FX$' THEN

                vResult := NULL;

            WHEN vIsDateTime THEN

                IF pNlsParam IS NOT NULL THEN

                    bd := TRUE;

                    vNlsParam := REPLACE(pNlsParam,' ', '');

                    SELECT STRING_AGG(TRIM(nls),'')
                    INTO vNlsParam
                    FROM  REGEXP_SPLIT_TO_TABLE
                    ( 
                        vNlsParam,
                        'NLS_',
                        'i'
                    ) nls
                    WHERE TRIM(nls) <> '' AND TRIM(nls) !~* 'CALENDAR';

                    IF vNlsParam IS NOT NULL THEN

                        IF vNlsParam  !~* 'DATE_LANGUAGE=' THEN

                            RAISE USING MESSAGE := verrorMessages[5];

                        END IF;

                        -- store original language
                        v_lctime := CURRENT_SETTING('lc_time');

                        vNlsParam := REGEXP_REPLACE(vNlsParam, 'DATE_LANGUAGE=', '', 'i');

                        -- try to set NLS
                        BEGIN

                            vTm := SET_CONFIG
                            (
                                'lc_time',
                                vNlsParam,
                                TRUE
                            );

                        EXCEPTION 
                            WHEN OTHERS THEN

                                RAISE USING MESSAGE := verrorMessages[5]; 
                        END;

                    END IF;

                END IF;

                IF NOT bd THEN

                    SELECT f.dmodelto
                    INTO vResult
                    FROM dfmt f
                    WHERE f.dmodel = pFormat;

                    IF FOUND THEN

                        RETURN vResult;

                    END IF;

                END IF;
        
                vtail:= pFormat;

                LOOP

                    SELECT ok, tail, fm
                    INTO vFmt, vtail, vfm
                    FROM to_char_parser
                    (
                        pStr  => vtail,
                        pType => 'datetime',
                        pFm   => vfm
                    );

                    IF vFmt !~ '"' THEN

                        IF vDataType IN ('TIMESTAMP WITHOUT TIME ZONE', 'TIMESTAMP WITH TIME ZONE') THEN

                            IF vFmt ~* 'SP$' THEN

                                IF EXISTS (SELECT 1 FROM UNNEST(cpellmodels) t WHERE t = UPPER(vFmt)) THEN

                                    bd := TRUE;
                                    IF vDataType = 'TIMESTAMP WITH TIME ZONE' THEN

                                        vFmt := spell_timestamptz
                                        (
                                            pval => pVal,
                                            pfmt => vFmt
                                        );

                                    ELSE

                                        vFmt := spell_timestamp
                                        (
                                            pval => pVal,
                                            pfmt => vFmt
                                        );

                                    END IF;
                                    vFmt := CONCAT
                                    (
                                        '"',
                                        vFmt,
                                        '"'
                                    );

                                    vResult := CONCAT(vResult, vFmt);
                                    EXIT WHEN vtail IS NULL;
                                    CONTINUE;

                                END IF;

                            END IF;

                        END IF;

                        -- exact set of dates is chosen to be sure about day names
                        --calculate max day of week name length for language
                        IF vDataType <> 'INTERVAL' THEN

                            IF vFmt ~* '^tmMON$' OR vFmt ~* '^tmMONTH$' OR vFmt ~* '^tmDAY$' OR vFmt ~* '^tmDY$' THEN

                                bd := TRUE;

                                SELECT MAX(LENGTH(TO_CHAR(TO_DATE(CONCAT(LPAD(val::TEXT, 2, '0') , '102017'), 'ddmmyyyy'),'TMDAY'))),
                                            MAX(LENGTH(TO_CHAR(TO_DATE(CONCAT(LPAD(val::TEXT, 2, '0'), '102017'), 'ddmmyyyy'),'TMDY')))
                                INTO vDayMaxLengh,
                                    vDyMaxLengh
                                FROM UNNEST(cDayMonthNumbers) AS val
                                WHERE val BETWEEN 2 AND 8;

                                --calculate max month name length for language
                                SELECT MAX(LENGTH(TO_CHAR(TO_DATE(CONCAT(LPAD(val::TEXT, 2, '0'), '2017'), 'mmyyyy'),'TMMONTH'))),
                                    MAX(LENGTH(TO_CHAR(TO_DATE(CONCAT(LPAD(val::TEXT, 2, '0'), '2017'), 'mmyyyy'),'TMMON')))
                                INTO vMonthMaxLength,
                                    vMonMaxLength
                                FROM UNNEST(cDayMonthNumbers) AS val;

                            END IF;

                        END IF;

                        IF vDataType <> 'INTERVAL' THEN
    
                            FOR rec IN
                            (
                                SELECT fm.fmt
                                FROM format_models fm
                                WHERE fm.tp = 'datetime'
                                AND fm.grp = UPPER(SUBSTR(vFmt,1,1))
                                AND
                                (
                                    fm.fmt LIKE '%SP%' OR
                                    fm.fmt IN ('SYEAR', 'YEAR')
                                )
                            )
                            LOOP

                                
                                IF vFmt ~* CONCAT('^', rec.fmt, '$') THEN

                                    RAISE USING MESSAGE := verrorMessages[4];
                                
                                END IF;

                            END LOOP;

                            IF vFmt ~* '^E$' OR vFmt ~* '^EE$' THEN

                                RAISE USING MESSAGE := verrorMessages[3];

                            END IF;
                       
                        END IF;

                        IF vFmt ~* 'TZ[HM]' AND vDataType = 'TIMESTAMP WITHOUT TIME ZONE' THEN

                            FOR rec IN
                            (
                                SELECT fm.fmt
                                FROM format_models fm
                                WHERE fm.tp = 'datetime'
                                AND fm.grp = UPPER(SUBSTR(vFmt,1,1))
                                AND 
                                (
                                    fm.fmt LIKE 'TZH%' OR 
                                    fm.fmt LIKE 'TZM%'
                                )
                            )
                            LOOP

                                IF vFmt ~* CONCAT('^', rec.fmt) THEN

                                    RAISE USING MESSAGE := verrorMessages[2];

                                END IF;

                            END LOOP;

                        END IF;

                        IF vDataType <> 'INTERVAL' THEN
                        
                            IF vDataType = 'TIMESTAMP WITHOUT TIME ZONE' THEN

                                bd := TRUE;

                                vFmt := REGEXP_REPLACE(vFmt, 'tzr', '"+00:00"', 'ig');

                            END IF;

                            vFmt := REGEXP_REPLACE(vFmt, 'tzd', '', 'ig');

                            -- Unable to calculate TS correctly, will produce
                            -- Oracle behavior 9:01:01 AM
                            IF vFmt ~* 'TS' THEN

                                bd := TRUE;
                            
                                vFmt := REGEXP_REPLACE
                                (
                                    vFmt,
                                    'ts',
                                    CONCAT
                                    (
                                        '"',
                                        TRIM(TO_CHAR(pVal, 'hh'),'0'),
                                        ':',
                                        TO_CHAR(pVal, 'mi'),
                                        ':',
                                        TO_CHAR(pVal, 'ss'),
                                        ' ',
                                        TO_CHAR(pVal, 'AM'),
                                        '"'
                                    ),
                                    'ig'
                                );

                            END IF;

                            -- X will be .
                            IF vFmt !~* 'FX' AND vFmt ~* '^x' THEN

                                vFmt := REGEXP_REPLACE(vFmt, 'x', '.', 'ig');

                            END IF;

                            -- Fix cases
                            vFmt := REPLACE(vFmt, 'A.d.', 'A.D.');
                            vFmt := REPLACE(vFmt, 'A.m.', 'A.M.');
                            vFmt := REPLACE(vFmt, 'B.c.', 'B.C.');
                            vFmt := REPLACE(vFmt, 'P.m.', 'P.M.');

                            FOR rec IN
                            (
                                SELECT
                                    ARRAY_TO_STRING
                                    (
                                        REGEXP_MATCHES(vFmt, 'Rm|Ad|Bc|Pm|Am', 'g'),
                                        ''
                                    ) case_match
                            ) LOOP

                                vFmt := REPLACE
                                (
                                    vFmt,
                                    rec.case_match,
                                    CONCAT
                                    (
                                        '"',
                                        INITCAP(TO_CHAR(pVal, UPPER(rec.case_match))),
                                        '"'
                                    )
                                );

                                bd := TRUE;

                            END LOOP;

                            -- PG 13+ does not need THIS
                            -- PG has SSSS, but Oracle SSSSS, so we need to convert SSSSS to SSSS
                            -- ssss to ss + ss
                            IF NOT (current_setting('server_version_num')::int) >= 130000 THEN

                                IF vFmt ~* '^FMS{2}$' OR vFmt ~* '^S{2}$' THEN

                                    bd := TRUE;

                                    vFmt := CONCAT
                                    (
                                        '"',
                                        TO_CHAR(pVal, 'ss'),
                                        '"'
                                    );

                                END IF;

                                IF vFmt ~* '^FMS{5}$' OR vFmt ~* '^S{5}$' THEN

                                        bd := TRUE;

                                        vFmt := CONCAT
                                        (
                                            '"',
                                            TO_CHAR(pVal, REGEXP_REPLACE(vFmt, 's{5}', 'ssss', 'i')),
                                            '"'
                                        );

                                END IF;

                            END IF;

                            -- PG has ID, but Oracle use it as I + D, no ID
                            IF vFmt ~* '^i$' THEN
                                
                                bd := TRUE;

                                vFmt := 
                                    CONCAT
                                    (
                                        '"',
                                        TO_CHAR(pVal, 'I'),
                                        '"'
                                    );
                            
                            END IF;

                            /* PG unable to convert TH if contains lower case letters in model */
                            IF vFmt ~* 'TH' AND vFmt !~* 'MONTH' AND vFmt ~ '[[:lower:]]' THEN

                                bd := TRUE;

                                vFmt := CONCAT
                                (
                                    '"',
                                    REGEXP_REPLACE(TO_CHAR(pVal, vFmt), '(\d+)(\D+)', '\1', 'i'),
                                    LOWER(REGEXP_REPLACE(TO_CHAR(pVal, UPPER(vFmt)), '(\d+)(\D+)', '\2', 'i')),
                                    '"'
                                );
                            
                            END IF;

                            -- hard to calculate correct DS format, will set to fmmm/fmdd/fmyyyy
                            IF vFmt ~* 'ds' THEN

                                bd := TRUE;
                            
                                vFmt := REGEXP_REPLACE
                                (
                                    vFmt,
                                    'ds',
                                    CONCAT
                                    (
                                        '"',
                                        LTRIM(TO_CHAR(pVal, 'mm'), '0'),
                                        '/',
                                        LTRIM(TO_CHAR(pVal, 'dd'), '0'),
                                        '/',
                                        LTRIM(TO_CHAR(pVal, 'yyyy'), '0'), 
                                        '"'
                                    ),
                                    'ig'
                                );

                            END IF;

                            IF vFmt ~* 'SYYYY' THEN

                                bd := TRUE;
                            
                                vFmt := REGEXP_REPLACE
                                (
                                    vFmt,
                                    'syyyy',
                                    CONCAT
                                    (
                                        '"',
                                        CASE
                                            WHEN TO_CHAR(pVal,'BC') = 'BC' THEN
                                                CONCAT('-', TO_CHAR(pVal, 'YYYY'))
                                            ELSE
                                                CONCAT(' ', TO_CHAR(pVal, 'YYYY'))
                                        END,
                                        '"'
                                    ),
                                    'ig'
                                );

                            END IF;

                            IF vFmt ~* 'CC' AND vFmt !~* 'SCC' THEN

                                bd := TRUE;

                                vFmt := REGEXP_REPLACE
                                (
                                    vFmt,
                                    'cc',
                                    CONCAT
                                    (
                                        '"',
                                        CASE
                                            WHEN TO_CHAR(pVal,'BC') = 'BC' THEN
                                                REPLACE(TO_CHAR(pVal, 'CC'),'-','')
                                            ELSE
                                                TO_CHAR(pVal, 'CC')
                                        END,
                                        '"'
                                    ),
                                    'ig'
                                );

                            END IF;

                            IF vFmt ~* 'SCC' THEN

                                bd := TRUE;

                                vFmt := REGEXP_REPLACE
                                (
                                    vFmt,
                                    'scc',
                                    CONCAT
                                    (
                                        '"',
                                        CASE
                                            WHEN TO_CHAR(pVal,'BC') = 'BC' THEN
                                                TO_CHAR(pVal, 'CC')
                                            ELSE
                                                CONCAT(' ', TO_CHAR(pVal, 'CC'))
                                        END,
                                        '"'
                                    ),
                                    'ig'
                                );

                            END IF;

                            IF vFmt ~* 'TZH' THEN

                                bd := TRUE;

                                vFmt := REGEXP_REPLACE
                                (
                                    vFmt,
                                    'tzh',
                                    CONCAT
                                    (
                                        '"',
                                        SUBSTRING(EXTRACT(TIMEZONE_HOUR FROM pVal)::TEXT FROM '\D'),
                                        LPAD
                                        (
                                            SUBSTRING(EXTRACT(TIMEZONE_HOUR FROM pVal)::TEXT FROM '\d+'),
                                            2,
                                            '0'
                                        ),
                                        '"'
                                    ),
                                    'ig'
                                );

                            END IF;

                            IF vFmt ~* 'TZM' THEN

                                bd := TRUE;

                                vFmt := REGEXP_REPLACE
                                (
                                    vFmt,
                                    'tzm',
                                    CONCAT
                                    (
                                        '"',
                                        SUBSTRING(EXTRACT(TIMEZONE_MINUTES FROM pVal)::TEXT FROM '\D'),
                                        LPAD
                                        (
                                            SUBSTRING(EXTRACT(TIMEZONE_MINUTES FROM pVal)::TEXT FROM '\d+'),
                                            2,
                                            '0'
                                        ),
                                        '"'
                                    ),
                                    'ig'
                                );

                            END IF;

                            IF vFmt ~* 'TZR' THEN

                                bd := TRUE;

                                vFmt := REGEXP_REPLACE
                                (
                                    vFmt,
                                    'tzr',
                                    CONCAT
                                    (
                                        '"',
                                        SUBSTRING(EXTRACT(TIMEZONE_HOUR FROM pVal)::TEXT FROM '\D'),
                                        LPAD
                                        (
                                            SUBSTRING(EXTRACT(TIMEZONE_HOUR FROM pVal)::TEXT FROM '\d+'),
                                            2,
                                            '0'
                                        ),
                                        ':',
                                        SUBSTRING(EXTRACT(TIMEZONE_MINUTES FROM pVal)::TEXT FROM '\D'),
                                        LPAD
                                        (
                                            SUBSTRING(EXTRACT(TIMEZONE_MINUTES FROM pVal)::TEXT FROM '\d+'),
                                            2,
                                            '0'
                                        ),
                                        '"'
                                    ),
                                    'ig'
                                );

                            END IF;

                            -- hard to calculate correct DL, set to hardcoded format
                            IF vFmt ~* '^DL' THEN

                                bd := TRUE;

                                vFmt := 
                                    CONCAT
                                    (
                                        '"',
                                        TRIM(TO_CHAR(pVal, 'TMDay')),
                                        ', ',
                                        TRIM(TO_CHAR(pVal, 'TMMonth')),
                                        ' ',
                                        TO_CHAR(pVal, 'dd'),
                                        ', ',
                                        TO_CHAR(pVal, 'yyyy'),
                                        
                                        '"'
                                    );

                            END IF;

                            -- PG13+ does not need this
                            --FF[1..9] FF = FF6 here
                            IF NOT (current_setting('server_version_num')::int) >= 130000 THEN
                            
                                IF vFmt ~* '^FF\d' THEN

                                    bd := TRUE;

                                    vln := SUBSTRING(vFmt FROM '\d')::INTEGER;

                                    IF vln < 6 THEN

                                        vFmt := CONCAT('"', SUBSTR(TO_CHAR(pVal, 'US'), 1, vln), '"');

                                    ELSE

                                        vFmt := CONCAT('"', RPAD(TO_CHAR(pVal, 'US'), vln, '0'), '"');

                                    END IF;

                                END IF;

                            END IF;

                            IF vFmt ~* '^tmMON$' THEN

                                bd := TRUE;

                                vFmt := CONCAT
                                (
                                    '"',
                                    RPAD(TO_CHAR(pVal, vFmt), vMonMaxLength),
                                    '"'
                                );

                            END IF;

                            IF vFmt ~* '^tmMONTH$' THEN

                                bd := TRUE;

                                vFmt := CONCAT
                                (
                                    '"',
                                    RPAD(TO_CHAR(pVal, vFmt), vMonthMaxLength),
                                    '"'
                                );

                            END IF;

                            IF vFmt ~* '^tmDAY$' THEN

                                bd := TRUE;

                                vFmt := CONCAT
                                (
                                    '"',
                                    RPAD(TO_CHAR(pVal, vFmt), vDayMaxLengh),
                                    '"'
                                );

                            END IF;

                            IF vFmt ~* '^tmDY$' THEN

                                bd := TRUE;

                                vFmt := CONCAT
                                (
                                    '"',
                                    RPAD(TO_CHAR(pVal, vFmt), vDyMaxLengh),
                                    '"'
                                );

                            END IF;

                        END IF;

                    END IF;

                    IF vResult ~ 'Dd' THEN

                        vResult := REPLACE(vResult, 'Dd', 'dd');

                    END IF;

                    IF vResult ~ 'dD' THEN

                        vResult := REPLACE(vResult, 'dD', 'dd');

                    END IF;

                    vResult := CONCAT(vResult, vFmt);

                    EXIT WHEN vtail IS NULL;

                END LOOP;

                IF NOT bd THEN

                    INSERT INTO dfmt
                    VALUES(pFormat, vResult)
                    ON CONFLICT ON CONSTRAINT dfmt_pkey DO NOTHING;

                END IF;

                -- restore language setting if they were changed
                IF CURRENT_SETTING('lc_time') <> v_lctime THEN

                    vTm := SET_CONFIG('lc_time', v_lctime, TRUE);

                END IF;

            WHEN vIsNumeric AND pNoNumberFormat IS NULL AND pNlsParam = '' THEN

                vResult := NULL;

            WHEN vIsNumeric THEN

                IF pNoNumberFormat IS NULL THEN

                    IF pNlsParam IS NOT NULL THEN

                        bd := TRUE;

                        SELECT STRING_AGG(CASE WHEN nls ~* 'CALENDAR' THEN '' ELSE nls END, '')
                        INTO vNlsParamCopy
                        FROM REGEXP_SPLIT_TO_TABLE
                        ( 
                            TRIM(E'\n\ ' FROM pNlsParam),
                            'NLS_',
                        'i') nls;

                        -- check if paramters are allowed
                        IF pNlsParam ~ '^\s+$' THEN

                            RAISE USING MESSAGE := verrorMessages[5];

                        END IF;

                        vNlsParam := REGEXP_REPLACE
                        (
                            vNlsParamCopy,
                            $$NUMERIC_CHARACTERS\s*=\s*('?)(.+?)('?)$$,
                            '',
                            'i'
                        );

                        vNlsParam := REGEXP_REPLACE
                        (
                            vNlsParam,
                            $$ISO_CURRENCY\s*=\s*('?)(.+?)('?)$$,
                            '',
                            'i'
                        );
              
                        vNlsParam := REGEXP_REPLACE
                        (
                            vNlsParam,
                            $$CURRENCY\s*=\s*('?)(.+?)('?)$$,
                            '',
                            'i'
                        );

                        IF LENGTH(TRIM(E'\n\ ' FROM vNlsParam)) > 0 THEN

                            RAISE USING MESSAGE := verrorMessages[5];

                        END IF;
                        
                        -- Try to get set NLS parts
                        FOR rec IN 
                        (
                            SELECT REGEXP_MATCHES
                            (
                                vNlsParamCopy,
                                $$NUMERIC_CHARACTERS\s*=\s*('?)(.+?)('?)$$,
                                'i'
                            ) m
                        ) LOOP

                            vDecimalSeparator := SUBSTR((rec.m)[2], 1, 1);
                            
                            IF LENGTH(vDecimalSeparator) = 0 THEN

                                RAISE USING MESSAGE := verrorMessages[5];

                            END IF;

                            vGroupSeparator := SUBSTR((rec.m)[2], 2, 1);

                            IF LENGTH(vGroupSeparator) = 0 THEN

                                RAISE USING MESSAGE := verrorMessages[5];

                            END IF;

                        END LOOP;

                        FOR rec IN 
                        (
                            SELECT REGEXP_MATCHES
                            (
                                vNlsParamCopy,
                                $$CURRENCY\s*=\s*('?)(.+?)('?)$$,
                                'i'
                            ) m
                        ) LOOP

                            vCurrencySymbol := (rec.m)[2];

                            IF LENGTH(vCurrencySymbol) = 0 THEN

                                RAISE USING MESSAGE := verrorMessages[5];

                            END IF;

                        END LOOP;

                        FOR rec IN 
                        (
                            SELECT REGEXP_MATCHES
                            (
                                vNlsParamCopy,
                                $$ISO_CURRENCY\s*=\s*('?)(.+?)('?)$$,
                                'i'
                            ) m
                        ) LOOP

                            vIsoCurrency := (rec.m)[2];

                            IF LENGTH(vIsoCurrency) = 0 THEN

                                RAISE USING MESSAGE := verrorMessages[5];

                            END IF;

                            SELECT icl.code
                            INTO vIsoCurrency
                            FROM iso_currency_list icl
                            WHERE icl.nls_iso_currency = (rec.m)[2];

                        END LOOP;

                    END IF;

                    IF NOT bd THEN

                        SELECT f.nmodel
                        INTO vFmt
                        FROM nfmt f
                        WHERE f.nmodel = pFormat;

                        IF FOUND THEN

                            vResult := TO_CHAR
                            (
                                pVal,
                                REGEXP_REPLACE(vFmt, 'FM', 'FM', 'i')
                            );

                        IF vResult ~ '#' AND NOT pFormat ~* '[^09GD\.]' THEN

                                vResult := REPEAT('#', LENGTH(pFormat) + 1);

                        END IF;
                            
                        RETURN vResult;

                        END IF;

                    END IF;

                    vtail:= pFormat;

                    LOOP

                        SELECT ok, tail, fm
                        INTO vFmt, vtail, vfm
                        FROM to_char_parser
                        (
                            pStr  => vtail,
                            pType => 'number',
                            pFm   => vfm
                        );
                        
                    
                        vResult := CONCAT(vResult, vFmt);

                        EXIT WHEN vtail IS NULL;

                    END LOOP;

                    /* Restrictions */
                    --A comma element cannot begin a number format model
                    IF SUBSTR(vResult, 1, 1) = ',' THEN

                        RAISE USING MESSAGE := verrorMessages[6];

                    END IF;
                    -- A comma cannot appear to the right of a decimal
                    -- character or period in a number format model
                    CASE 
                        WHEN vResult ~* '[\.D].*\,' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        --only one period in a number format model
                        WHEN vResult ~ '\..*\.' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        -- only one decimal character in a number format model
                        WHEN vResult ~* 'd.*d' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        -- group separator cannot appear to the right of a decimal 
                        -- character or period in a number format model
                        -- V also
                        WHEN vResult ~* '[\.DV].*g' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        -- Start with g is not allowed
                        WHEN vResult ~* '^G' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        -- It's not allowed to mix different type group and
                        -- decimal model symbols
                        WHEN vResult ~ '\.|\,' AND vResult ~* 'D|G' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        -- The MI format element can appear only in the last position 
                        -- of a number format model
                        WHEN vResult ~* 'MI.+' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        --The PR format element can appear only in the last position
                        -- of a number format model
                        WHEN vResult ~* 'PR.+' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        -- The S format element can appear only in the first 
                        -- or last position of a number format model
                        WHEN vResult ~* '[^S]+S[^S]+' OR vResult ~* 'S[^S]*S' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        -- You cannot precede this element with any other element
                        WHEN vResult ~* '.+TM' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        -- You can follow this element only with one 9 or 
                        -- one E (or e), but not with any combination of these
                        WHEN vResult ~* 'TM.{2,}' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        WHEN vResult ~* 'TM[^9e]' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        -- You can precede this element only with 0 (which returns leading zeroes)
                        -- or FM. Any other elements return an error
                        WHEN vResult ~* '.*X.?'
                            AND vResult !~* '^0+X{1,}$'
                            AND vResult !~* '^FMX{1,}$'
                            AND vResult !~* '^FM0{1,}X{1,}$'
                            AND vResult !~* '^X{1,}$' THEN

                                RAISE USING MESSAGE := verrorMessages[6];
                        -- only one $ allowed
                        WHEN vResult ~ '\$.*\$' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        -- only one C allowed
                        WHEN vResult ~* 'C.*C' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        -- only one L allowed
                        WHEN vResult ~* 'L.*L' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        -- only one U allowed
                        WHEN vResult ~* 'U.*U' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        -- C, L, $, U together is not allowed
                        WHEN vResult ~* 'C.*[\$LU]' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        WHEN vResult ~* '[UL\$].*C' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        -- only one V allowed
                        WHEN vResult ~* 'V.*V' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        -- V and period or decimal is not allowed
                        WHEN vResult ~* 'V.*[\.D]' OR vResult ~* '[\.D].*V' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        -- If RN - no other models allowed
                        WHEN vResult ~* '.+RN' OR vResult ~* 'RN.+' THEN

                            RAISE USING MESSAGE := verrorMessages[6];
                        -- Only EEEE is not allowed. No chars after EEEE. No comma before EEEE
                        WHEN vResult ~* '^EEEE$' 
                            OR vResult ~* 'EEEE[^S]+' 
                            OR vResult ~* '\,.*EEEE'
                            OR vResult ~* 'EEEE.{2,}' THEN

                                RAISE USING MESSAGE := verrorMessages[6];
                        ELSE

                            NULL;
                    END CASE;

                    /* Some Oracle actions repeated */
                    IF UPPER(vResult) = 'S' THEN

                        bd := TRUE;

                        vResult := '#';

                    END IF;

                    IF vResult ~* 'U|C|L' THEN

                        bd := TRUE;

                        IF vResult ~* 'U' THEN

                            IF vDualCurrency IS NULL THEN

                                SELECT value
                                INTO vDualCurrency
                                FROM v$nls_parameters WHERE parameter = 'NLS_DUAL_CURRENCY';

                            END IF;

                            vResult := REGEXP_REPLACE(vResult, 'U',  CONCAT('"', vDualCurrency, '"') , 'i');
                            vurrisset := true;

                        END IF;

                        IF vResult ~* 'C' AND NOT vurrisset THEN

                            IF vIsoCurrency IS NULL THEN

                                SELECT icl.code
                                INTO vIsoCurrency
                                FROM iso_currency_list icl
                                WHERE icl.nls_iso_currency = (SELECT value
                                FROM v$nls_parameters WHERE parameter = 'NLS_ISO_CURRENCY');

                            END IF;

                            vResult := REGEXP_REPLACE(vResult, 'C',  CONCAT('"', vIsoCurrency, '"') , 'i');
                            vurrisset := true;

                        END IF;

                        IF vResult ~* 'L' AND NOT vurrisset THEN

                            IF vCurrencySymbol IS NULL THEN

                                SELECT value
                                INTO vCurrencySymbol
                                FROM v$nls_parameters
                                WHERE parameter = 'NLS_CURRENCY';

                            END IF;

                            vResult := REGEXP_REPLACE(vResult, 'L',  CONCAT('"', vCurrencySymbol, '"') , 'i');
                            vurrisset := true;

                        END IF;

                    END IF;

                    IF vResult ~ '\$' AND vResult !~ '"\$"' THEN

                        bd := TRUE;

                        vResult := CONCAT('"$"', REPLACE(vResult, '$', ''));

                    END IF;

                    IF vResult ~* 'TMe' THEN

                        vResult = REGEXP_REPLACE(vResult, 'TMe', '9.9EEEE', 'i');

                    END IF;

                    -- converting

                    CASE
                        WHEN vResult ~* 'X' AND vResult !~ '"' THEN

                            bd := TRUE;
                            
                            vResult := CONCAT
                            (
                                CASE
                                    WHEN vResult ~* 'FM' THEN
                                        ''
                                    ELSE
                                        ' '
                                    END,
                                LPAD
                                (
                                    CASE
                                        WHEN vResult ~ 'X' THEN
                                            UPPER(TO_HEX(ROUND(pVal,0)::INTEGER)::TEXT)
                                        ELSE TO_HEX(ROUND(pVal,0)::INTEGER)::TEXT
                                    END,
                                    LENGTH
                                    (
                                        REGEXP_REPLACE
                                        (
                                            vResult,
                                            'FM',
                                            '',
                                            'i'
                                        )
                                    ),
                                    '0'
                                )
                            );
                            
                        WHEN vResult ~* 'EEEE' THEN

                            bd := TRUE;

                            vResult := 
                            CASE 
                                WHEN vResult ~* 'FM' THEN 
                                    LTRIM
                                    (
                                        REPLACE
                                        (
                                            REGEXP_REPLACE
                                            (
                                                TO_CHAR
                                                (
                                                    pVal,
                                                    REGEXP_REPLACE(vResult, 'FM', '', 'i')
                                                ),
                                                '00(\d)',
                                                '0\1'
                                            ),
                                            'e',
                                            'E'
                                        )
                                    )
                                ELSE
                                    CONCAT(' ', REPLACE(REGEXP_REPLACE(TO_CHAR(pVal, vResult), '00(\d)', '0\1'), 'e', 'E'))
                            END;

                            IF vDecimalSeparator IS NOT NULL THEN

                                vFmt := REPLACE(TO_CHAR(1.1, 'FM9D9'),'1','');
                                vResult := REPLACE(vResult, vFmt, '#.');    
                                vResult := REPLACE(vResult, '#.', vDecimalSeparator);

                            END IF;

                        WHEN vResult ~* 'G|D' AND vDecimalSeparator IS NOT NULL AND vGroupSeparator IS NOT NULL THEN

                            bd := TRUE;
                            vResult := TO_CHAR(pVal, REGEXP_REPLACE(vResult, 'FM', 'FM', 'i'));

                            vFmt := REPLACE(TO_CHAR(1.1, 'FM9D9'),'1','');
                            vResult := REPLACE(vResult, vFmt, '#.');
                            vFmt := REPLACE(TO_CHAR(1111, 'FM9G999'),'1','');
                            vResult := REPLACE(vResult, vFmt, '#,');

                            vResult := REPLACE(vResult, '#,', vGroupSeparator);
                            vResult := REPLACE(vResult, '#.', vDecimalSeparator);
                        
                        --  case with format = '.999'  
                        WHEN vResult ~* '^\.(?=\d+$)' THEN  

                            bd := TRUE;
                            
                            vResult :=
                            REGEXP_REPLACE
                            (
                                TO_CHAR
                                    (
                                        pVal,
                                        REGEXP_REPLACE
                                        (
                                            vResult, 
                                            '^\.(?=\d+$)', 
                                            '0.', 
                                            'g'
                                        )
                                    ),
                                '^\ ?0\.',
                                ' .',
                                'g'
                            );
                        WHEN vResult ~* 'TM9?' THEN

                            vResult := pVal::TEXT;

                            IF vDecimalSeparator IS NOT NULL THEN

                                vResult := REPLACE(vResult, '.', vDecimalSeparator);

                            END IF;

						ELSE

                            vResult := TO_CHAR
                            (
                                pVal,
                                REGEXP_REPLACE(vResult, 'FM', 'FM', 'i')
                            );

                            IF vResult ~ '#' AND NOT pFormat ~* '[^09GD\.]' THEN

                                bd := TRUE;

                                vResult := REPEAT('#', LENGTH(pFormat) + 1);

                            END IF;

                            IF NOT bd THEN

                                INSERT INTO nfmt
                                VALUES (pFormat)
                                ON CONFLICT ON CONSTRAINT nfmt_pkey DO NOTHING;

                            END IF;

                    END CASE;
                
                ELSE

                        IF pVal = 0 THEN

                            vResult := '0';
                            
                        ELSE

                            vResult := REGEXP_REPLACE(REGEXP_REPLACE(REGEXP_REPLACE(pVal::TEXT, '^0\.(\d+)', '.\1'), '\.0{2,}$', ''), '\.$', '');

                            vResult := REGEXP_REPLACE(vResult, '^-0\.(\d+)', '-.\1');

                            IF vResult ~ '\.' THEN

                                vResult := REGEXP_REPLACE(REGEXP_REPLACE(vResult, '0+$', ''), '\.$', '');

                            END IF;
                        
                        END IF;

                END IF;
                
            ELSE

                IF pFormat IS NOT NULL THEN
                    
                    RAISE USING MESSAGE := verrorMessages[1];

                END IF;
                
                IF LENGTH(pVal) > 4000 THEN

                    RAISE USING MESSAGE := verrorMessages[7];

                END IF;

                vResult := pVal::VARCHAR;

        END CASE;

    END IF;

    RETURN vResult;

END;
$BODY$;

ALTER FUNCTION to_char_formatter(anyelement, character varying, character varying, character varying)
    OWNER TO oms_owner;

COMMENT ON FUNCTION to_char_formatter(anyelement, character varying, character varying, character varying)
    IS 'Utility function making to_char results similar to expected on Oracle side';		
	
	CREATE OR REPLACE FUNCTION next_day(
	ddate timestamp without time zone,
	cchar text)
    RETURNS timestamp without time zone
    LANGUAGE 'sql'
    COST 100
    IMMUTABLE STRICT PARALLEL UNSAFE
AS $BODY$
WITH dn AS
    (
        SELECT
            CASE
                WHEN UPPER(cchar) = 'MONDAY' THEN 1 
                WHEN UPPER(cchar) = 'TUESDAY' THEN 2 
                WHEN UPPER(cchar) = 'WEDNESDAY' THEN 3 
                WHEN UPPER(cchar) = 'THURSDAY' THEN 4 
                WHEN UPPER(cchar) = 'FRIDAY' THEN 5 
                WHEN UPPER(cchar) = 'SATURDAY' THEN 6 
                WHEN UPPER(cchar) = 'SUNDAY' THEN 7 
                ELSE 0
            END AS dnum
    ) 
    SELECT ddate + make_interval
    (
        days =>
        CASE
            WHEN TO_CHAR(ddate, 'd')::INT > dnum THEN
                
                7 + dnum - TO_CHAR(ddate, 'd')::INT + 1

            ELSE

                dnum - TO_CHAR(ddate, 'd')::INT + 1

        END::INT
    )
    FROM dn;
$BODY$;