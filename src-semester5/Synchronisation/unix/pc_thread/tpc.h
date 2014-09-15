/* Header file for producer-consumer program 'tpc'. */
/*   (Multithreaded program accord to POSIX         */
/*    thread definitions)                           */
/*                                                  */
/* file: tpc.h                                      */

#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>

/* application specific part: Wrap Buffer */
#define MAX_NO_OF_PRODUCERS     10
#define FIFO_SIZE       5       /* size of shared wraparound buffer */

struct fifo_buf {
        int in_index;
        int out_index;
        int fifo[FIFO_SIZE];
};
