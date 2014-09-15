/*  E. Ammann:  'kunden_cli.c' (verteilte UNIX-Programmierung)            */
/*               (Serverteil siehe 'kunden_serv.c')                       */
/*  Compilation auf AIX:   cc kunden_cli.c -o kunden_cli -D_BSD -lbsd     */

/* Dieses Programm dient als Clientenprogramm in der Client-Server-Loesung*/
/* zum Suchen in einer Kundendatei.                                       */
/* Aufruf:  kunden_cli hostname portnummer, wobei                         */
/*           hostname   symbolischer Name des Rechners mit dem Server     */
/*           portnummer TCP Portnummer, vom Server beim Starten gemeldet  */
/* Ein Socket fuer eine verbindungsorientierte Kommunikation ueber TCP/IP */
/* wird eingerichtet, mit dem Port des Serverprogramms auf dem            */
/* entsprechenden Rechner verbunden und dann Auftraege an den Server      */
/* geschickt. Die zurueckerhaltenen Informationen werden am Bildschirm    */
/* ausgegeben.                                                            */

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

#define BACKLOG         5       /* Zahl von Requests, die gequeued werden */
#define MAXNAMELEN      32      /* Groesse des Auftragspuffers            */

main(argc, argv)
   int argc;
   char *argv[];
{
        int  sock;
        struct sockaddr_in sa;
        struct hostent *hp, *gethostbyname();
        int sa_len;
        char buf[MAXNAMELEN];
        int count;
        char stopstring[] = "stop";

        if (argc != 3) {
           fprintf(stderr, "%s", "Aufruf: kunden_cli host portnummer\n");
           exit(1);
        }

        /* Erzeuge Socket */
        sock = socket(AF_INET, SOCK_STREAM, 0);
        if (sock < 0) {
           fprintf(stderr, "%s", "Socket kann nicht geoeffnet werden\n");
           exit(1);
        }

        /* Verbinde den Socket entsprechend den eingegebenen Parametern */
        /* mit dem Server-Socket                                        */
        sa.sin_family = AF_INET;
        sa.sin_len = sizeof(sa);
        hp = gethostbyname(argv[1]);
        if (hp == 0) {
           fprintf(stderr, "%s", "Host kann nicht identifiziert werden\n");
           exit(1);
        }
        bcopy(hp->h_addr, &sa.sin_addr, hp->h_length);
        sa.sin_port = htons(atoi(argv[2]));

        if (connect(sock, &sa, sizeof(sa)) < 0) {
           fprintf(stderr, "%s", "Keine Verbindung zum Serverport\n");
           exit(1);
        }

        /* Anfordern von Auftraegen durch den Benutzer und schicken an  */
        /* den Server                                                   */
        for (;;) {
                printf("Eingabe eines Kundennamens oder 'stop'\n");
                if((count = read(0, buf, MAXNAMELEN)) == -1) {
                   fprintf(stderr, "%s", "Fehler beim Lesen der Eingabe\n");
                   exit(1);
                }

                /* Ersetze \n-Zeichen in der Eingabe durch EOS */
                buf[count-1]='\0';

                /* Falls gewuenscht, beenden des Programms */
                if(strcmp(buf,stopstring) == 0) return;

                /* Senden des Auftrags an den Server */
                if (write(sock, buf, count) != count) {
                   fprintf(stderr, "%s", "Fehler beim Senden des Auftrags\n");
                   exit(1);
                }

                /* Lesen der Antwort des Servers und Ausgabe am Bildschirm */
                if((count = read(sock, buf, MAXNAMELEN)) > 0)
                   write(1, buf, count);
                fflush(stdout);

                /* Atempause */
                sleep(2);
        }
}
