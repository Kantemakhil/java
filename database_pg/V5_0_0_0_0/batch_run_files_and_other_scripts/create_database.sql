--create database

\prompt 'Please enter database name (ex. testdb) : '  dbname
\prompt 'Please enter database comment (ex. Test database) : '  dbcomment

\set quoted_dbcomment '\'' :dbcomment '\''

CREATE DATABASE :dbname
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    --LC_COLLATE = 'en_US.UTF-8'
    --LC_CTYPE = 'en_US.UTF-8'
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE :dbname
    IS :quoted_dbcomment;

GRANT ALL ON DATABASE :dbname TO postgres;

GRANT TEMPORARY, CONNECT ON DATABASE :dbname TO PUBLIC;

GRANT ALL PRIVILEGES  ON DATABASE :dbname TO oms_owner;