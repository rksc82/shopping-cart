FROM java:8

RUN mkdir -p /opt/rks/

ADD /build/libs/shoppingcart-0.1-SNAPSHOT.jar /opt/rks/

RUN chmod +x /opt/rks/shoppingcart-0.1-SNAPSHOT.jar

ADD shoppingCartApi.sh /opt/rks/
RUN chmod +x /opt/rks/shoppingCartApi.sh

EXPOSE 8080

CMD ["/opt/rks/shoppingCartApi.sh"]