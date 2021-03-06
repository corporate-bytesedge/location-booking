use mysql;
DROP DATABASE IF EXISTS lb;
REVOKE ALL ON *.* FROM 'lb';
DELETE FROM mysql.user WHERE User='lb';
DROP USER 'lb';
FLUSH PRIVILEGES;
CREATE USER 'lb' IDENTIFIED BY '1_LbDataBase';
CREATE DATABASE IF NOT EXISTS lb CHARACTER SET utf8;
GRANT ALL PRIVILEGES ON lb.* TO 'lb';
GRANT ALL PRIVILEGES ON *.* TO 'lb'@'%' WITH GRANT OPTION;
use lb;