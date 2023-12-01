#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <semaphore.h>
#include <time.h>
#include <limits.h>
#include <signal.h>

#define MAX_CITIES 50
#define MAX_PROCESSES 10

typedef struct {
    int cities[MAX_CITIES][MAX_CITIES];
    int num_cities;
    int best_path[MAX_CITIES];
    int best_distance; 
    int iterations;
    time_t best_time;
    sem_t mutex;
    pid_t parent_pid; //***
} SharedData;

//Signal global variable and funtion
volatile sig_atomic_t update_signal_received = 0;

void handle_update_signal(int signo) {
    if (signo == SIGUSR1) {
        update_signal_received = 1;
    }
}

// Função para gerar um número aleatório no intervalo [min, max]
int random_range(int min, int max) {
    return min + rand() % (max - min + 1);
}

void initialize(SharedData *data, char *filename) {
    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        fprintf(stderr, "Error opening file.\n");
        exit(EXIT_FAILURE);
    }

    fscanf(file, "%d", &(data->num_cities));

    for (int i = 0; i < data->num_cities; i++) {
        for (int j = 0; j < data->num_cities; j++) {
            fscanf(file, "%d", &(data->cities[i][j]));
        }
    }

    fclose(file);

    // Initialize best path and distance
    for (int i = 0; i < data->num_cities; i++) {
        data->best_path[i] = i + 1;
    }
    data->best_distance = INT_MAX;
}

void aj_pe_algorithm(SharedData *data, int max_execution_time, int test_number, char *test_name, int num_processes, time_t start_time, pid_t pid) {
    int current_path[MAX_CITIES];
    int current_distance;
    unsigned int iterations_counter = 0;
    time_t current_time;

    for (int j = 0; j < data->num_cities; j++) {
        current_path[j] = j + 1;
    }
	
    // Shuffle the path 
    for (int j = data->num_cities - 1; j > 0; j--) {
         int rand_index = random_range(0, j);
         // Swap
         int temp = current_path[j];
         current_path[j] = current_path[rand_index];
         current_path[rand_index] = temp;
     }   

   //Até o tempo não acabar, fazer as iterações
    while ((current_time = time(NULL)) - start_time < max_execution_time) {
        iterations_counter++;

        if (update_signal_received) {
            update_signal_received = 0;

            // Processo recebeu um sinal de atualização
            sem_wait(&(data->mutex));
            // Atualiza os dados internos com base nos dados compartilhados
            for (int i = 0; i < data->num_cities; i++) {
                current_path[i] = data->best_path[i];
            }
            current_distance = data->best_distance;
            sem_post(&(data->mutex));

            printf("Process: %d ", pid);
            printf("Received update signal. Using updated path.\n");
        }

        // Apply mutation (exchange mutation)
        int mutation_point1 = random_range(0, data->num_cities - 1);
        int mutation_point2 = random_range(0, data->num_cities - 1);

        // Swap cities to perform mutation
        int temp = current_path[mutation_point1];
        current_path[mutation_point1] = current_path[mutation_point2];
        current_path[mutation_point2] = temp;

        // Calculate the total distance
        current_distance = 0;
        for (int j = 0; j < data->num_cities - 1; j++) {
            current_distance += data->cities[current_path[j] - 1][current_path[j + 1] - 1];
        }

        current_distance += data->cities[current_path[data->num_cities - 1] - 1][current_path[0] - 1];
	
	printf("Process: %d ", pid);
        printf("Current Path:");
        for(int i = 0; i < data->num_cities; i++){
            printf("%d ", current_path[i]);
        }
        printf("Distance: %d", current_distance);
        printf("\n");

        //Update the best path 
        update_best_path(data, current_path, current_distance, current_time, iterations_counter);
    }
}

void update_best_path(SharedData *data, int *new_path, int new_distance, time_t new_time, int new_iterations) {
    sem_wait(&(data->mutex));

    if (new_distance < data->best_distance) {
        for (int i = 0; i < data->num_cities; i++) {
            data->best_path[i] = new_path[i];
        }
        data->best_distance = new_distance;
	data->best_time = new_time;
	data->iterations = new_iterations;
    }

    sem_post(&(data->mutex));
}

void update_shared_data(SharedData *data, int *new_path, int new_distance, time_t new_time, int new_iterations) {
    sem_wait(&(data->mutex));

    if (new_distance < data->best_distance) {
        for (int i = 0; i < data->num_cities; i++) {
            data->best_path[i] = new_path[i];
        }
        data->best_distance = new_distance;
        data->best_time = new_time;
        data->iterations = new_iterations;

        // Notifica todos os processos filhos sobre a atualização
        for (int i = 0; i < MAX_PROCESSES; i++) {
            if (data->best_path[i] != getpid()) {
                kill(data->best_path[i], SIGUSR1);
            }
        }
    }

    sem_post(&(data->mutex));
}

void print_solution(SharedData *data, int test_number, char *test_name, int num_processes, time_t start_time, int iterations) {

    struct timeval end_timeval;
    gettimeofday(&end_timeval, NULL);
    time_t end_time = end_timeval.tv_sec;
    long milliseconds = end_timeval.tv_usec / 1000;

    printf("----------------------------------------------- \n");
    printf("Nome do teste: %s\n", test_name);
    printf("Número de cidades: %d\n", data->num_cities);
    
    printf("Tempo total de execução: %ld.%03ld segundos\n", end_time - start_time, milliseconds);
    
    printf("Número de processos usados: %d\n", num_processes);

    printf("Melhor caminho encontrado: ");
    for (int i = 0; i < data->num_cities; i++) {
        printf("%d ", data->best_path[i]);
    }
    printf("\n   Distância: %d\n", data->best_distance);

    printf("Número de iterações necessárias: %d\n", data->iterations);

    printf("Tempo necessário para encontrar: %ld.%03ld segundos\n", data->best_time - start_time, milliseconds);

    printf("\n ");
    
}

int main(int argc, char *argv[]) {
    if (argc != 4) {
        fprintf(stderr, "Usage: %s <filename> <num_processes> <max_execution_time>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    char *filename = argv[1];
    int num_processes = atoi(argv[2]);
    int max_execution_time = atoi(argv[3]);

    // Track start time
    time_t start_time = time(NULL);

    SharedData *data;
    int shmid;

    // Allocate shared memory
    shmid = shmget(IPC_PRIVATE, sizeof(SharedData), IPC_CREAT | 0666);
    data = (SharedData *)shmat(shmid, NULL, 0);

    // Initialize shared data
    sem_init(&(data->mutex), 1, 1);
    initialize(data, filename);

    // Fork processes
    pid_t pid;
    for (int i = 0; i < num_processes; i++) {
        pid = fork();
        if (pid == 0) {
            // Child process
	    signal(SIGUSR1, handle_update_signal); //****
            srand(time(NULL) * getppid());
            aj_pe_algorithm(data, max_execution_time, i + 1, filename, num_processes, start_time, getpid());
            exit(EXIT_SUCCESS);
        } else if (pid < 0) {
            fprintf(stderr, "Fork failed\n");
            exit(EXIT_FAILURE);
        }
    }

    // Wait for child processes to finish
    sleep(max_execution_time);
    for (int i = 0; i < num_processes; i++) {
        wait(NULL);
    }

    time_t end_time = time(NULL);

    // Print the final best solution found
    ("--MELHOR SOLUÇÃO--\n");
    print_solution(data, 0, filename, num_processes, start_time, max_execution_time);
 
    // Detach and remove shared memory
    shmdt(data);
    shmctl(shmid, IPC_RMID, NULL);

    return 0;
}
