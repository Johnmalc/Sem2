/*  E. Ammann:  'kunden_serv.c' (verteilte UNIX-Programmierung)           */
/*               (Cliententeil siehe 'kunden_cli.c')                      */
/*  Compilation auf AIX:   cc kunden_serv.c -o kunden_serv -D_BSD -lbsd   */

/* Dieses Programm dient als Serverprogramm in der Client-Server-Loesung  */
/* zum Suchen in einer Kundendatei.                                       */
/* Ein Socket fuer eine verbindungsorientierte Verbindung ueber TCP/IP    */
/* wird eingerichtet, die vom Betriebssystem zugewiesene Portnummer       */
/* am Bildschirm gemeldet und dann auf Auftraege von Clienten gewartet.   */
/* Die entsprechende Information wird an das Clientenprogramm geschickt.  */

/* Achtung: Aus Gruenden der Einfachheit sind die 'read'- und 'write'-    */
/*          Aufrufe fuer Sockets in der Annahme programmiert, dass jeweils*/
/*          alle Information mit einem Aufruf gesendet/empfangen werden   */
/*          kann.     Dies ist nicht immer der Fall ! (Warum ?)           */

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdio.h>
#include <string.h>

#define BACKLOG         5     /* Zahl von Requests, die gequeued werden  */
#define MAXNAMELEN      32    /* Groesse des Auftragspuffers             */

main()
{
        int  sock, newsock;
        struct sockaddr_in sa, newsa;
        int sa_len, newsa_len;

        /* Erzeuge Socket */
        sock = socket(AF_INET, SOCK_STREAM, 0);
        if (sock < 0) {
           fprintf(stderr, "%s", "Socket kann nicht geoeffnet werden\n");
           exit(1);
        }

        /* Binden des Sockets an einen Port. Hier: Nehme irgendeinen   */
        /* Port (Wildcard).                                            */
        sa.sin_family = AF_INET;
        sa.sin_len = sizeof(sa);
        sa.sin_addr.s_addr = INADDR_ANY;
        sa.sin_port = 0;
        if (bind(sock, &sa, sizeof(sa))) {
           fprintf(stderr, "%s", "Binden des Sockets nicht gelungen\n");
           exit(1);
        }

        /* Ausgabe des zugewiesenen Ports auf dem Bildschirm */
        sa_len = sizeof(sa);
        if (getsockname(sock, &sa, &sa_len)) {
           fprintf(stderr, "%s", "Port kann nicht erfragt werden\n");
           exit(1);
        }
        printf("Socket hat Port #%d\n", ntohs(sa.sin_port));
	fflush(stdout);

        /* Festlegung, wieviele Requests gequeued werden sollen */
        listen(sock, BACKLOG);

        /* Nun Warten auf Verbindungsanforderungen */
        for (;;) {
                newsa_len = sizeof(newsa);
                /* Warten auf Requests mit 'accept' */
                if ((newsock = accept(sock, &newsa, &newsa_len)) < 0) {
                   fprintf(stderr, "%s", "Fehler bei accept\n");
                   exit(1);
                }

                /* Fuehre die folgenden Auftraege dieser Verbindung aus */
                perform_service(newsock);

                /* Schliesse den Socket dieses Auftrags */
                close(newsock);
        }
}

perform_service(sock)
  int sock;
{
        char kuname[MAXNAMELEN];
        char kucontent[MAXNAMELEN];
        char buf[MAXNAMELEN];
        int count;
        FILE *kufile;

        for (;;) {
                /* Lese Auftrag ueber gegebenen Socket ein */
                if ((count = read(sock, buf, MAXNAMELEN)) <= 0)
                        return;

                /* Oeffne die Kundendatei */
                kufile=fopen("/home/ammann/sockettest/kudatei","r");
                if (kufile==NULL) {
                   fprintf(stderr, "Kundendatei nicht zu oeffnen\n");
                   return;
                }

                /* Suche in der Kundendatei nach String 'buf' */
                for(;;) {
                        if (fscanf(kufile,"%s",kucontent) == EOF) {
                           sprintf(buf, "%s", "Nicht_gefunden\n");
                           break;
                        }
                        if(strcmp(buf,kucontent) == 0) {
                           sprintf(buf, "Gefunden: %s\n", kucontent);
                           break;
                        }
                }

                /* Schicke Antwort zurueck */
                write(sock, buf, strlen(buf));

                /* Schliessen der Kundendatei */
                fclose(kufile);
        }
}
