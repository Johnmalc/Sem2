/* Routines for producers and consumer in the    */
/* producer-consumer program 'pc'.               */
/*                                               */
/* file: pc_routines.c                           */       

#include <sys/time.h>
#include "pc.h"

#define NO_PRODUCTS	10

/* producer routine */

producer(semid, buf, index)
   int semid;
   struct fifo_buf *buf;
   int index;
{
   int i, product;
   struct timestruc_t tstruc;

   tstruc.tv_sec = 0;
   if (index%2 == 0)
      tstruc.tv_nsec = 10000000; /* 10 Millisek. */
     else
      tstruc.tv_nsec = 20000000; /* 20 Millisek. */

   for (i=0; i<NO_PRODUCTS; i++) {
      product = 10*index + i;	/* produce product                */
      nsleep(&tstruc, 0);       /* simulate hard working          */
      p(semid, EMPTY);		/* empty slot in buffer?          */
      p(semid, MUTEX);		/* begin of critical section      */
      buf->fifo[buf->in_index] = product;
      if (buf->in_index == FIFO_SIZE - 1) buf->in_index = 0;
                                     else buf->in_index++;
      v(semid, MUTEX);		/* end of critical section        */
      v(semid, FULL);		/* one more full slot in buffer   */
   }
}
 
/* consumer routine */

consumer(semid, buf, no_producers)
   int semid;
   struct fifo_buf *buf;
   int no_producers;
{
   int i, item;

   for (i=1;i<=no_producers * NO_PRODUCTS; i++) {
      p(semid, FULL);		/* something in buffer ?         */
      p(semid, MUTEX);		/* begin of critical section     */
      item = buf->fifo[buf->out_index];
      if (buf->out_index == FIFO_SIZE - 1) buf->out_index = 0;
                                      else buf->out_index++;
      v(semid, MUTEX);		/* end of critical section       */
      v(semid, EMPTY);		/* one more empty slot in buffer */
      printf("\n Consumer: item %2d\t(from producer %d)", item, item/10);
      fflush(stdout);
   }
   printf("\n");
}

