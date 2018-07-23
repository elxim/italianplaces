### Progetto per la creazione di un servizio JSON per la consultazione dei comuni

-- Per la documentazione  : http://localhost:8080/istatproxy/swagger-ui.html

#### Pubblicazione su Docker 

E' stato pubblicato su Docker al link : http://192.168.125.140:8084/istatproxy/swagger-ui.html 

Le variabili d'ambiente da configurare è la variabile $APP_HOME 

##### Versione

   - v0.4-20180119 
     Aggiornamento comune soppresso
     Verifica codici stati esteri
   - v0.3-20180110 
     Versione Con aggiunta del supporto ai comuni soppressi.
     
     Per aggiungere il comune basta inserire una nuova riga nel file elenco-comuni-soppressi.xls
     
   - v0.2-20171205 
     Versione di Produzione
   - v0.1-20171108
		Inserito log con tempi di esecuzione
	- v0.1-20171018
		Inserito l'ordinamento della lista comuni
	- v0.1-20171004
		Inserito il supperto per gli stati Esteri
	- v0.0.2-20170831 BugFix
		Corretto URL	istat;
		corretto formato celle; 
	- v0.0.1alfa-20161130 Prima versione