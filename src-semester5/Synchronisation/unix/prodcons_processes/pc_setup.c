/* Initialization and terminatino code for the */
/* producer-consumer program 'pc'.             */
/*                                             */
/* file: pc_setup.c                            */

#include "pc.h"

#define IFLAGS	(IPC_CREAT|IPC_EXCL)
#define ERR	((struct fifo_buf *) -1)

static int shmid, semid;

getseg(ptr)	/* create and attach shared memory segment */
   struct fifo_buf **ptr;
{
   void *shmat();

   /* create shared memory segment */
   if ((shmid = shmget(SHMKEY, sizeof(struct fifo_buf), 0600|IFLAGS)) < 0)
	perror("shmget");

   /* attach this shared memory segment */
   if ((*ptr = (struct fifo_buf *)shmat(shmid, 0, 0)) == ERR)
        perror("shmat");

   /* zero shared memory segment */
   bzero((char *)(*ptr), sizeof(struct fifo_buf));
}

int get_semaphores()		/* define semaphores */
{
   short initarray[3] = { 1, FIFO_SIZE, 0};
   short outarray[3];

   /* create semaphore set with 3 semaphores */
   if ((semid = semget(SEMKEY, 3, 0600|IFLAGS)) < 0)
      perror("semget");

   /* set initial values for the semaphores */
   if (semctl(semid, 3, SETALL, initarray) < 0)
      perror("semctl: SETALL with initarray");
   if (semctl(semid, 3, GETALL, outarray) < 0)
      perror("semctl: GETALL with outarray");
   printf("\nSemaphore init values: %d %d %d\n", outarray[0], 
          outarray[1], outarray[2]); 

   return(semid);
}

release_ipc_objects()		/* release IPC objects */
{
   /* remove shared memory segment */
   if (shmctl(shmid, IPC_RMID, (struct shmid_ds *)0) < 0)
      perror("shmctl: removal of shared memory");

   /* remove semaphore set */
   if (semctl(semid, IPC_RMID, 0) < 0)
      perror("semctl: removal of semaphore set");
}
