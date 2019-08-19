-- Local machine
-- Get latest code
-- Run build.xml with Target 'copy.bv.db.file'
mysql
source /tmp/schema/bv_user.sql
source /tmp/schema/bv_schema.sql
source /tmp/schema/bv_base_data.sql
exit
-- Update the build on the server with build.xml target 'deploy.bv.war'
mysql -u bv -p1_BvDatabase bv
-- Validate with admin@bytesedge.com/1!Qhariome


-- GCE Server
-- run the SQL files in the following order
cd /opt/release
svn checkout https://hariome.svn.cloudforge.com/bookvenue/trunk --username=divyavanij --password='Creative1@Q'

mysql -uroot -p1_MySqlRoot
source /opt/release/trunk/db/schema/bv_user.sql
source /opt/release/trunk/db/schema/bv_schema.sql
source /opt/release/trunk/db/schema/bv_base_data.sql
exit

mysql -ubv -p1_BookvenueDatabase bv
-- Validate the functionality with admin@bytesedge.com/1!Qhariome