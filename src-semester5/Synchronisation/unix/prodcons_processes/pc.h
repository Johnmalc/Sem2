/* Header file for producer-consumer program 'pc'. */
/*                                                 */
/* file: pc.h                                      */   

#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>

#define SHMKEY	(key_t)0x100
#define SEMKEY	(key_t)0x150

#define FIFO_SIZE	5	/* size of shared wraparound buffer */

struct fifo_buf {
	int in_index;
	int out_index;
	int fifo[FIFO_SIZE];
};

/* defines for 3 used semaphores */
#define MUTEX	0
#define EMPTY	1
#define FULL	2
