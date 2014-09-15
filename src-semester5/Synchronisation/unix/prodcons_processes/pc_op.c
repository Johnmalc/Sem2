/* Semaphore p and v operations for the producer-consumer */
/* program 'pc'.                                          */
/*                                                        */
/* file: pc_op.c                                          */

#include "pc.h"

p(semid, number)		/* p operation on semaphore number in */
				/* semaphore set semid */
   int semid;
   int number;
{

   struct sembuf p_buf;

   p_buf.sem_num = number;
   p_buf.sem_op  = -1;
   p_buf.sem_flg = 0;

   if (semop(semid, &p_buf, 1) == -1) {
       perror("p operation failed");
      }
     else
      return(0);
}

v(semid, number)		/* v operation on semaphore number in */
				/* semaphore set semid */
   int semid;
   int number;
{

   struct sembuf v_buf;

   v_buf.sem_num = number;
   v_buf.sem_op  = 1;
   v_buf.sem_flg = 0;

   if (semop(semid, &v_buf, 1) == -1) {
       perror("v operation failed");
      }
     else
      return(0);
}
