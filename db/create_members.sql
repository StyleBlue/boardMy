DECLARE
BEGIN
    EXECUTE IMMEDIATE '
        CREATE TABLE members (
            member_id   VARCHAR2(30) PRIMARY KEY,
            member_name VARCHAR2(50) NOT NULL,
            passwd      VARCHAR2(64) NOT NULL,
            reg_date    TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL
        )';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN
            RAISE;
        END IF;
END;
/