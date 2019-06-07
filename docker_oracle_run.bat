docker rm oracle-11
docker run -d -p 1521:1521 -e ORACLE_DISABLE_ASYNCH_IO=true --name oracle-11 thebookpeople/oracle-xe-11g