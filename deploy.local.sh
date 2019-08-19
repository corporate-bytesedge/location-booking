/etc/init.d/tomcat stop
/bin/rm -rf /opt/tomcat/webapps/ROOT
/bin/rm -rf /opt/tomcat/work/*
/bin/rm -rf /opt/tomcat/temp/*
/bin/sudo rm -rf /var/log/tomcat/*
/bin/chmod 755 /opt/tomcat -R
/bin/chown tomcat:tomcat /opt/tomcat/ -R
/etc/init.d/tomcat start
