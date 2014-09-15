
all: kunden_serv kunden_cli

kunden_serv: kunden_serv.c
	cc -okunden_serv kunden_serv.c -D_BSD -lbsd

kunden_cli: kunden_cli.c
	cc -okunden_cli kunden_cli.c -D_BSD -lbsd

