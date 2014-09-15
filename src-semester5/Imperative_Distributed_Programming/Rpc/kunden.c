/* Kundendateiabfrage als RPC-Loesung                    */
/* hier: Datei: kunden.c (Cliententeil)         E.Ammann */
/* (s.a.: kunden.x, kunden_svc_proc.c)                   */

#include <stdio.h>
#include <string.h>
#include <rpc/rpc.h>
#include "kunden.h"

main(argc, argv)
  int  argc;
  char *argv[];
{
  CLIENT  *cl;    /* a client handle */
  char    name[80];
  char    *filename = name;
  int	  *result;
  char    stopstring[] = "stop";

  if (argc != 2) {
    fprintf(stderr, "Usage: %s server\n", argv[0]);
    exit(1);
  }

  if (!(cl = clnt_create(argv[1], KUNDENPROG, KUNDENVERS, "tcp"))) {
    /* CLIENT handle couldn't be created, server not there. */
    clnt_pcreateerror(argv[1]);
    exit(1);
  }

  for (;;) {
     printf("Eingabe des Namens: \n");
     scanf("%s", name);

     /* got stop indication? */
     if (!strcmp(name, stopstring))
        exit(1);

     /* Aufruf der remote Routine */
     result = kunden_frage_1(&filename,cl); 

     if (result == NULL) {
       /* An error occured while calling the server. */
       clnt_perror(cl, argv[1]);
       exit(1);
     } 

     /* Okay, we successfully called the remote procedure */
     if (*result == TRUE) 
       printf("Name %s occurs in customer file\n\n", filename);
      else
       printf("Name %s does not occur in customer file\n\n", filename);
  }
}
