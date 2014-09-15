/* Kundendateiabfrage als RPC-Loesung                    */
/* hier: Spezifikationsdatei: kunden.x          E.Ammann */
/* (s.a.: kunden.c, kunden_svc_proc.c)                   */

/* constant definitions */
%#define MAX_NAME 80

/* program definition */
program KUNDENPROG { /* could manage multiple servers */
	version KUNDENVERS {
		int KUNDEN_FRAGE(string) = 1;
	} = 1;
} = 0x30000000;  /* program number ranges established by ONC */
