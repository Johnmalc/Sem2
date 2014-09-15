/* Routines for producers and consumer in        */
/* the producer-consumer program 'tpc'.          */
/*                                               */
/* file: tpc_rout.c                              */

#include "tpc.h"

#define NO_PRODUCTS     10

extern struct sema_t mutex, full, empty;
extern struct fifo_buf buf;

/* producer routine (thread routine for generated threads) */

producer(void *arg)
{
   int i, product, *p_index;

   p_index = (int *)arg;
   for (i=0; i<NO_PRODUCTS; i++) {
      product = 10*(*p_index) + i;   /* produce product         */
      sem_wait(&empty);          /* empty slot in buffer?          */
      sem_wait(&mutex);          /* begin of critical section      */
      buf.fifo[buf.in_index] = product;
      if (buf.in_index == FIFO_SIZE - 1) buf.in_index = 0;
                                     else buf.in_index++;
      sem_post(&mutex);          /* end of critical section        */
      sem_post(&full);           /* one more full slot in buffer   */
   }
   pthread_exit(p_index);
}

/* consumer routine (run in the main thread) */

consumer(int no_producers)
{
   int i, item;

   for (i=1;i<=no_producers * NO_PRODUCTS; i++) {
      sem_wait(&full);           /* something in buffer ?         */
      sem_wait(&mutex);          /* begin of critical section     */
      item = buf.fifo[buf.out_index];
      if (buf.out_index == FIFO_SIZE - 1) buf.out_index = 0;
                                      else buf.out_index++;
      sem_post(&mutex);          /* end of critical section       */
      sem_post(&empty);          /* one more empty slot in buffer */
      printf("\n Consumer: item %2d\t(from producer %d)", item, item/10);
      fflush(stdout);
   }
   printf("\n");
}

