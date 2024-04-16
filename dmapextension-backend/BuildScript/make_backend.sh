#!/bin/bash
###################################################################################
# You can use the below 4 command to run this file
# docker run -d -it -e GIT_USER=<user_name> -e GIT_PASS=<password> -e GIT_BRANCH=<branch_name> -e LICENSE_REQUIRED=<True/False> --name=<container_name> <Image_Name ngdmapo/dmap_build:v1>
# docker cp <path of make_backend.sh file> <container_name>:/usr/local/tomcat
# docker exec -it <container_name> sh /usr/local/tomcat/make_backend.sh
# docker cp <container_name>:/usr/local/tomcat/DMAP_Backend/dist/Service.tar .
###################################################################################

# turn on bash's job control
#set -m

rm -rf DMAP_Extension-Backend
git clone -b "$GIT_BRANCH" https://"$GIT_USER":"$GIT_PASS"@github.com/newtglobalgit/DMAP_Extension-Backend.git


cd DMAP_Extension-Backend

mvn clean install surefire-report:report -Dserver.port=8090

cd target/

tar -cvf DMAP.tar AppRemediation.war

