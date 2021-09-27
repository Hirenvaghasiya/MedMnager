FROM mysql/mysql-server:5.7

COPY startup.sh startup.sh

RUN ["chmod", "+x", "startup.sh"]

ENTRYPOINT ["sh","startup.sh"]