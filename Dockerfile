FROM njmittet/alpine-wildfly:10.1.0.Final

ADD ./target/appeventos-0.0.1-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/eventosBackEnd.war