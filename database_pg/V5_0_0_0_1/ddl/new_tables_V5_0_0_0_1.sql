CREATE TABLE oms_owner.module_dynamic_forms_audit (
    seq bigserial NOT NULL,
    form_id bigint NULL,
    module_name varchar(100) NULL,
    form_name varchar(100) NULL,
    form_json json NULL,
    form_identifier varchar(100) NULL,
    create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    create_user_id VARCHAR(32) NOT NULL ,
    modify_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_user_id VARCHAR(32),
    seal_flag VARCHAR(1),
    CONSTRAINT module_dynamic_forms_audit_pkey PRIMARY KEY (seq)
);

