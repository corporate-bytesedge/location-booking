sudo /etc/init.d/tomcat stop
rm -rf /opt/tomcat/webapps/ROOT
rm -rf /opt/tomcat/work/*
rm -rf /opt/tomcat/temp/*
sudo rm -rf /var/log/tomcat/*
chmod 755 /opt/tomcat -R
chown tomcat:tomcat /opt/tomcat -R
sudo /etc/init.d/tomcat start