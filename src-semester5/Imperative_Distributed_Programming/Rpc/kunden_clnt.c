#include <rpc/rpc.h>
#include <sys/time.h>
#include "kunden.h"
#define MAX_NAME 80

/* Default timeout can be changed using clnt_control() */
static struct timeval TIMEOUT = { 25, 0 };

int *
kunden_frage_1(argp, clnt)
	char **argp;
	CLIENT *clnt;
{
	static int res;

	bzero((char *)&res, sizeof(res));
	if (clnt_call(clnt, KUNDEN_FRAGE, xdr_wrapstring, argp, xdr_int, &res, TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&res);
}

