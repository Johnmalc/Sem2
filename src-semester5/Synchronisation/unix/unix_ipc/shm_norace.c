// shm_norace.c
//  Das Programm erstellt ein Shared-Memory-Segment.
//  8 Kindprozesse lesen und schreiben daraus.
//  Die Zugriffe auf das Shared Memory sind durch die
//  Nutzung einer Semaphore vor Inkonsistenzen geschuetzt.
//
#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <sys/wait.h>

#define MAXCOUNT  100000
#define NUM_CHILDREN  8
#define SHMSIZE  sizeof(int)

int main() {
  int i, semID, shmID, *shared_mem, count = 0;
  int pid[NUM_CHILDREN];
  struct sembuf vsema, psema;
  unsigned short anfangswert[1];

  // Anlegen eines Shared Memory-Bereichs und
  // Anhaengen an den Speicherbereich des Prozesses
  shmID = shmget(IPC_PRIVATE, SHMSIZE, IPC_CREAT | 0644);
  shared_mem = (int *)shmat(shmID, 0, 0);
  *shared_mem = 0;

  // Erzeugung einer Semaphore
  // (genauer: einer Semaphorengruppe mit genau einer Semaphore)  
  // Danach Initialisierung der Semaphore mit Wert 1
  semID = semget(IPC_PRIVATE, 1, IPC_CREAT |0644);

  anfangswert[0] = 1;
  semctl(semID, 0, SETALL, anfangswert);

  // Vorbereitung der vsema- und psema-Operationen
  // auf dieser Semaphore
  // dazu: Fuellen der Strukturen sembuf
  vsema.sem_num = 0;  // Semaphore 0 der Semaphorengruppe
  vsema.sem_op = 1;   // Semaphorenwert um 1 hochsetzen
  vsema.sem_flg = SEM_UNDO;

  psema.sem_num = 0;  // Semaphore 0 der Semaphorengruppe
  psema.sem_op = -1;  // Semaphorenwert um 1 herabsetzen
  psema.sem_flg = SEM_UNDO;        

  // Erzeugung von NUM_CHILDREN Kindprozessen.
  // Deren PrzessID wird im Arrray pid[] gespeichert.
  for (i=0; i<NUM_CHILDREN; i++) {
    pid[i] = fork();

    if (pid[i] == -1) {
      printf("  %diter Kindprozess nicht erzeugbar\n", i);
      return 0;
    }

    // Ab hier durch die Semaphore geschützter Zugriff
    // auf das Shared Memory durch den Kindprozess
    if (pid[i] == 0) {
      while(1) {
        semop(semID, &psema, 1);  // Anfang des kritischen Bereichs
        if (*shared_mem < MAXCOUNT) {
          *shared_mem += 1;
          count++;
          semop(semID, &vsema, 1); // Ende des kritischen Bereichs
        }
        else {
          semop(semID, &vsema, 1); // Ende des kritischen Bereichs
          break;
        }
      }
      printf("  %ites Kind erhoehte Wert um %i\n", i, count);
      shmdt(shared_mem);
      return 0;
    }
  }

  // Der Vaterprozess wartet auf die Terminierung
  // aller Kindprozesse
  for (i=0; i < NUM_CHILDREN; i++)
    waitpid(pid[i], NULL, 0);

  printf("  Shared Memory Inhalt = %i, MAXCOUNT = %i\n",
            *shared_mem, MAXCOUNT);

  // Entfernen und Loeschen des Shared Memory-Bereichs
  shmdt(shared_mem);
  shmctl(shmID, IPC_RMID, 0);
  // Loeschen der Semaphore(ngruppe)
  semctl(semID, 0, IPC_RMID);
  return 0;
}
