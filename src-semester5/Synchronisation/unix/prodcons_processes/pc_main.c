/*                                   E.Ammann  7/10/95  */
/* Demo program 'pc' for the UNIX system V IPC-package. */
/* Application is the producer-consumer problem with    */
/* n producers and 1 consumer.                          */
/*                                                      */     
/* file: pc_main.c                                      */   

#include "pc.h"

main(argc, argv)
   int argc;
   char *argv[];
{

   int semid, i, no_producers;
   struct fifo_buf *buf;

   /* initialize semaphore set */
   semid = get_semaphores();

   /* create and attach shared memory segment */
   getseg(&buf);

   /* how many producers ? */
   if (argc < 2) no_producers = 1;
           else   no_producers = atoi(argv[1]);

   /* fork as many producers as required */
   for (i=0; i<no_producers; i++) {
      if (fork() == 0) {
         producer(semid, buf, i);
         exit(0);
      }
   }

   /* act as the single consumer now */
   consumer(semid, buf, no_producers);

   /* free all IPC objects and exit */
   release_ipc_objects();
   exit(0);
}

