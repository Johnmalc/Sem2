/*                                   E.Ammann           */
/* Demo program 'w32_pc_event.exe' for the Win32 API.   */
/* Application is the producer-consumer problem with    */
/* n producers and 1 consumer.                          */
/* Hier: Nur Schutz gegen Ringpuffer Overflow und       */
/*       Underflow per Event-Semaphoren                 */ 
/*                                                      */
/* file: w32_pc_event.cpp                               */

#include <windows.h>
#include <string.h>
#include <stdio.h>

/* application specific part: Wrap Buffer */
#define MAX_NO_OF_PRODUCERS     10
#define NO_PRODUCTS     10
#define FIFO_SIZE       10       /* size of shared wraparound buffer */

struct fifo_buf {
		  int in_index;
		  int out_index;
		  int fifo[FIFO_SIZE];
};

/* global data accessible for all threads */
DWORD Tid[MAX_NO_OF_PRODUCERS];
HANDLE Thandle[MAX_NO_OF_PRODUCERS];
HANDLE hSema_empty, hSema_full;  /* handles to semaphores */
struct fifo_buf buf;

/* prototypes of used routines */
DWORD producer(LPVOID arg);
void consumer(int);

void main(int argc, char *argv[])
{
	DWORD exitcode;
	int i, no_producers;

	/* give console window a title */
	SetConsoleTitle("Producer/Consumer Multithreaded Program");

	/* initialize semaphores */
	hSema_empty = CreateSemaphore(NULL, FIFO_SIZE, FIFO_SIZE, "empty_sem");
	hSema_full = CreateSemaphore(NULL, 0, FIFO_SIZE, "full_sem");

	/* how many producers ? */
	if (argc < 2) no_producers = 1;
			  else  no_producers = atoi(argv[1]);
	if (no_producers > MAX_NO_OF_PRODUCERS)
			  no_producers = MAX_NO_OF_PRODUCERS;

	/* create as many producers threads as required */
	for (i=0; i<no_producers; i++) {
		Thandle[i]=CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)producer,
								(LPVOID)i, 0, &Tid[i]);
		if (Thandle[i] == 0) {
			printf("Error in producer thread creation (#%d)\n", i);
			fflush(stdout);
			exit(1);
		}
	}

	/* act as the single consumer thread now */
	consumer(no_producers);

	/* wait for completion of all threads */
	WaitForMultipleObjects(no_producers, Thandle, TRUE, INFINITE);

	/* collect and display status codes of completed producer threads */
	printf("\nExit stati of threads:\n");
	for (i=0; i<no_producers; i++) {
		GetExitCodeThread(Thandle[i], &exitcode);
		printf("     Thread %ld\n", exitcode);
	}

	/* close thread handles */
	for (i=0; i<no_producers; i++)
		CloseHandle(Thandle[i]);

	/* wait for keystroke */
	getchar();

}

/* producer routine (thread routine for generated threads) */

DWORD producer(LPVOID arg)
{
	int i, product, index;

	index = (int)arg;
	for (i=0; i<NO_PRODUCTS; i++) {
		product = 10*index + i;   /* produce product         */
		WaitForSingleObject(hSema_empty, INFINITE); /* empty slot in buffer? */
		buf.fifo[buf.in_index] = product;
		if (buf.in_index == FIFO_SIZE - 1) buf.in_index = 0;
                                              else buf.in_index++;
		ReleaseSemaphore(hSema_full, 1, NULL); /*one more full slot in buffer*/
	}
	ExitThread((DWORD)index*10);
	return 0;
}

/* consumer routine (run in the main thread) */

void consumer(int no_producers)
{
	int i, item;

	for (i=1;i<=no_producers * NO_PRODUCTS; i++) {
		WaitForSingleObject(hSema_full, INFINITE);  /* something in buffer?  */
		item = buf.fifo[buf.out_index];
		if (buf.out_index == FIFO_SIZE - 1) buf.out_index = 0;
					       else buf.out_index++;
		ReleaseSemaphore(hSema_empty, 1, NULL); /*one more empty slot in buffer*/
		printf("\n Consumer: item %2d\t(from producer %d)", item, item/10);
		fflush(stdout);
		Sleep(100);
	}
	printf("\n");
}
