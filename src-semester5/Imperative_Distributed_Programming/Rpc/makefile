# this makefile uses your sources:
 # $(APPN).x - RPCgen input, XDR protocol definition
 # $(APPN)_svc_proc.c - define server process called by server stub
 # $(APPN).c - tell the client what to do (talk to servers etc)
# to make the following:
 # $(APPN)_svc.c - server stub, a main() wrapper
 # $(APPN)_clnt.c - client stub w/ function name defined in protocol
 # $(APPN).h - miscellaneous includes generated from protocol def.
 # $(APPN) - the executable for the client
 # $(APPN)_svc - the executable for the server
 
# these for different machines and stages of development
APPN= kunden
#LFLAGS=
RSRC=$(APPN).x $(APPN)_svc_proc.c $(APPN).c
CFLAGS= -g 
RPCGEN = rpcgen

all: $(APPN) $(APPN)_svc # client and server executables

# compile the protocol
$(APPN)_svc.c $(APPN)_clnt.c $(APPN).h: $(APPN).x
	$(RPCGEN) $(APPN).x

# build the client application
$(APPN): $(APPN).h $(APPN).o $(APPN)_clnt.o
	cc $(CFLAGS) -o $(APPN) $(APPN).o $(APPN)_clnt.o \
	$(LFLAGS)

# explicitly watch for new .h files
$(APPN).o: $(APPN).c $(APPN).h
	cc -c -o $@ $(CFLAGS) $(APPN).c 
$(APPN)_svc_proc.o: $(APPN)_svc_proc.c $(APPN).h
	cc -c -o $@ $(CFLAGS) $(APPN)_svc_proc.c

# build the server application
$(APPN)_svc: $(APPN).h $(APPN)_svc_proc.o $(APPN)_svc.o
	cc $(CFLAGS) -o $(APPN)_svc $(APPN)_svc_proc.o $(APPN)_svc.o \
	$(LFLAGS)

# blast away
clean:
	rm $(APPN) $(APPN)_svc $(APPN)_svc.c $(APPN)_clnt.c $(APPN).h *.o
