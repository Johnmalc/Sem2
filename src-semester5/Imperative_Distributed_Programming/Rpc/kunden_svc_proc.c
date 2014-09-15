/* Kundendateiabfrage als RPC-Loesung                    */
/* hier: kunden_svc_proc.c (Serverroutine)        Ammann */
/* (s.a.: kunden.x, kunden.c)                            */

#include <stdio.h>
#include <string.h>
#include <rpc/rpc.h>
#include "kunden.h"


int  *
kunden_frage_1(pname)
   char **pname;
{
   static int result;

   char kucontent[MAX_NAME];
   FILE *kufile;

   /* Oeffne die Kundendatei */
   kufile=fopen("/home/ammann/kunden_datei","r");
   if (kufile==NULL) {
      fprintf(stderr, "Kundendatei nicht zu oeffnen\n");
      return((int *)NULL);
   }

   /* Suche in der Kundendatei nach String 'filename' */
   for(;;) {
      if (fscanf(kufile,"%s",kucontent) == EOF) {
	 result = FALSE;
         break;
      }
      if(strcmp(*pname,kucontent) == 0) {
	 result = TRUE;
         break;
      }
   }

   /* Schliessen der Kundendatei */
   fclose(kufile);

   return (&result);
}

