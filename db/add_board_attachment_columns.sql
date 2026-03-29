-- Oracle migration for board attachment feature
ALTER TABLE board ADD (org_file_name VARCHAR2(255));
ALTER TABLE board ADD (stored_file_name VARCHAR2(255));
ALTER TABLE board ADD (file_size NUMBER(19) DEFAULT 0 NOT NULL);
