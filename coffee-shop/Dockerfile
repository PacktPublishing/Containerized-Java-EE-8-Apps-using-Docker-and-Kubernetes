FROM docker.sebastian-daschner.com/open-liberty:2

ENV JVM_ARGS="-Xmx512M"

COPY openliberty/server.xml $CONFIG_DIR
COPY target/coffee-shop.war $DEPLOYMENT_DIR
