/*                                   E.Ammann           */
/* Demo program 'tpc' for the POSIX thread library on   */
/* SUSE Linux 10.                                       */
/* Application is the producer-consumer problem with    */
/* n producers and 1 consumer.                          */
/*                                                      */
/* file: tpc_main.c                                     */

#include "tpc.h"

/* global data accessible for all threads */

sem_t mutex, empty, full;
struct fifo_buf buf;

void producer();
void consumer();

main(int argc, char *argv[])
{
   int i, ret, no_producers;
   int ids[MAX_NO_OF_PRODUCERS] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
   pthread_t thrhandle[MAX_NO_OF_PRODUCERS];
   void *join_status;
   pthread_attr_t attr;

   /* initialize semaphores */
   sem_init(&mutex, 0, 1);
   sem_init(&empty, 0, FIFO_SIZE);
   sem_init(&full, 0, 0);

   /* prepare attributes for undetached thread creation */
   pthread_attr_init(&attr);
   pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_JOINABLE);

   /* how many producers ? */
   if (argc < 2) no_producers = 1;
           else  no_producers = atoi(argv[1]);
   if (no_producers > MAX_NO_OF_PRODUCERS)
           no_producers = MAX_NO_OF_PRODUCERS;

   /* create as many producers threads as required */
   for (i=0; i<no_producers; i++) {
      if (ret = pthread_create(&thrhandle[i], &attr, producer, &ids[i]))
      {
         printf("Error %d in producer thread creation (#%d)\n", ret, i);
         fflush(stdout);
         exit(1);
      }
   }

   /* act as the single consumer thread now */
   consumer(no_producers);

   /* join all the threads created before */
   for (i=0; i<no_producers; i++ )
   {
      pthread_join(thrhandle[i], (void *)&join_status);
      printf("Joined thread #%d\n", *(int *)join_status);
   }

   /* destroy semaphores before leaving */
   sem_destroy(&mutex);
   sem_destroy(&empty);
   sem_destroy(&full);
}

