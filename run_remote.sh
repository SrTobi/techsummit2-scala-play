#!/usr/bin/sh

ssh -tt root@78.47.36.253 <<'ENDSSH'
cd /home/hackathonmesse-1.0/bin

./hackathonmesse \
 -Dplay.http.secret.key=ad31779d4ee49f2324 \
 -Dhttp.port=disabled \
 -Dhttps.port=443 \
 -Dplay.server.https.keyStore.path=/home/sslkey.jks \
 -Dplay.server.https.keyStore.password=aaaaaa

logout
ENDSSH

