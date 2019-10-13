#!/usr/bin/sh
git clean -Xdf
sbt -java-home /usr/lib/jvm/java-8-openjdk dist
scp target/universal/hackathonmesse-1.0.zip root@78.47.36.253:/home/hackathonmesse-1.0.zip
ssh -tt root@78.47.36.253 <<'ENDSSH'
cd /home
rm -rdf hackathonmesse-1.0
unzip hackathonmesse-1.0.zip
rm hackathonmesse-1.0.zip
ENDSSH
