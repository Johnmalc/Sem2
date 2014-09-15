#include <stdio.h>
#include <rpc/rpc.h>
#include "kunden.h"
#define MAX_NAME 80

static void kundenprog_1();

main()
{
	SVCXPRT *transp;

	(void)pmap_unset(KUNDENPROG, KUNDENVERS);

	transp = svcudp_create(RPC_ANYSOCK);
	if (transp == NULL) {
		(void)fprintf(stderr, "cannot create udp service.\n");
		exit(1);
	}
	if (!svc_register(transp, KUNDENPROG, KUNDENVERS, kundenprog_1, IPPROTO_UDP)) {
		(void)fprintf(stderr, "unable to register (KUNDENPROG, KUNDENVERS, udp).\n");
		exit(1);
	}

	transp = svctcp_create(RPC_ANYSOCK, 0, 0);
	if (transp == NULL) {
		(void)fprintf(stderr, "cannot create tcp service.\n");
		exit(1);
	}
	if (!svc_register(transp, KUNDENPROG, KUNDENVERS, kundenprog_1, IPPROTO_TCP)) {
		(void)fprintf(stderr, "unable to register (KUNDENPROG, KUNDENVERS, tcp).\n");
		exit(1);
	}
	svc_run();
	(void)fprintf(stderr, "svc_run returned\n");
	exit(1);
}

static void
kundenprog_1(rqstp, transp)
	struct svc_req *rqstp;
	SVCXPRT *transp;
{
	union {
		char *kunden_frage_1_arg;
	} argument;
	char *result;
	bool_t (*xdr_argument)(), (*xdr_result)();
	char *(*local)();

	switch (rqstp->rq_proc) {
	case NULLPROC:
		(void)svc_sendreply(transp, xdr_void, (char *)NULL);
		return;

	case KUNDEN_FRAGE:
		xdr_argument = xdr_wrapstring;
		xdr_result = xdr_int;
		local = (char *(*)()) kunden_frage_1;
		break;

	default:
		svcerr_noproc(transp);
		return;
	}
	bzero((char *)&argument, sizeof(argument));
	if (!svc_getargs(transp, xdr_argument, &argument)) {
		svcerr_decode(transp);
		return;
	}
	result = (*local)(&argument, rqstp);
	if (result != NULL && !svc_sendreply(transp, xdr_result, result)) {
		svcerr_systemerr(transp);
	}
	if (!svc_freeargs(transp, xdr_argument, &argument)) {
		(void)fprintf(stderr, "unable to free arguments\n");
		exit(1);
	}
}

