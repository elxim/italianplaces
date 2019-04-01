#!/bin/bash
# docker.rete.dom:5000/istatproxy:0.0.1
if [ -z $1 ]; then
        echo "impostare il nome dell'immagine ad es. docker.rete.dom:5000/istatproxy:v0.1-20171108";
        exit;
else
        echo "avvio il container per l'immagine : $1 ";
        docker run -d -v ~/istatproxy:/istatproxy \
        --name istatproxy -p 8084:8084 \
        --restart=unless-stopped \
        -e logging.file=/istatproxy/logs/istatproxy.log \
        -e it.istat.proxy.localPath=/istatproxy/ \
        -e it.istat.proxy.sourceUrl=http://www.istat.it/storage/codici-unita-amministrative/Elenco-comuni-italiani.xls \
        -e server.port=8084 \
        -e TZ=Europe/Rome \
        -t $1
fi;
