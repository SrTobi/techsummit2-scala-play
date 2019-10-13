git clean -Xdf
sbt -java-home /usr/lib/jvm/java-8-openjdk dist
scp target/universal/hackathonmesse-1.0.zip root@78.47.36.253:/home/hackathonmesse.zip
ssh root@78.47.36.253 <<'ENDSSH'
rm -rdf hackathonmesse
unzip hackathonmesse.zip
cd hackathonmesse/bin
./hackathonmesse -Dplay.http.secret.key=ad31779d4ee49f2324 -Dhttp.port=80

ENDSSH
