CREATE TABLE json_spec (id bigint,
                       spec_key VARCHAR(32),
                       json_specs bytea,
                       create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
	                   create_user_id VARCHAR(32)NOT NULL ,
	                   modify_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	                   modify_user_id VARCHAR(32),
	                   seal_flag VARCHAR(1)) 
tablespace tag_setup_data;

ALTER TABLE json_spec 
  ADD CONSTRAINT json_spec_pk PRIMARY KEY (id)
  USING index tablespace tag_setup_indx;
  




