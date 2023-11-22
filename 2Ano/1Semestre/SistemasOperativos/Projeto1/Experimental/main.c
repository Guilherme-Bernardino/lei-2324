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
    sem_t mutex;
} SharedData;

volatile sig_atomic_t found_best_solution = 0;

// Função de tratamento de sinal para encerrar quando a melhor solução é encontrada
void handle_signal(int signo) {
    if (signo == SIGUSR1) {
        found_best_solution = 1;
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

void aj_pe_algorithm(SharedData *data, int max_iterations, int test_number, char *test_name, int num_processes, time_t start_time) {
    int current_path[MAX_CITIES];
    int current_distance;

    time_t current_time;
	
    //for (int i = 0; i < 1000; i++) {
    while ((current_time = time(NULL)) - start_time < max_iterations) {
        //Initialize a random path
        for (int j = 0; j < data->num_cities; j++) {
            current_path[j] = j + 1;
        }

        // Shuffle the path using Fisher-Yates algorithm
        for (int j = data->num_cities - 1; j > 0; j--) {
            int rand_index = random_range(0, j);
            // Swap
            int temp = current_path[j];
            current_path[j] = current_path[rand_index];
            current_path[rand_index] = temp;
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
        // Add the distance from the last city back to the starting city
        current_distance += data->cities[current_path[data->num_cities - 1] - 1][current_path[0] - 1];

        // Step 4: Update the best path if needed
        update_best_path(data, current_path, current_distance);


	    //print_solution(data, test_number, test_name, num_processes, start_time, max_iterations);
    printf("--SOLUÇÃO ENCONTRADA PELO PROCESSO %d--\n", test_number);
    //print_solution(data, process_id, filename, num_processes, start_time, max_iterations);
    print_solution(data, test_number, test_name, num_processes, start_time, max_iterations);
    }


}

void update_best_path(SharedData *data, int *new_path, int new_distance) {
    sem_wait(&(data->mutex));

    if (new_distance < data->best_distance) {
        for (int i = 0; i < data->num_cities; i++) {
            data->best_path[i] = new_path[i];
        }
        data->best_distance = new_distance;
    }

    sem_post(&(data->mutex));
}

void print_solution(SharedData *data, int test_number, char *test_name, int num_processes, time_t start_time, int iterations) {
    //printf("Número do processo: %d\n", test_number);
    printf("Nome do teste e número de cidades: %s %d\n", test_name, data->num_cities);
    
    time_t end_time = time(NULL);
    printf("Tempo total de execução: %ld segundos\n", end_time - start_time);
    
    printf("Número de processos total: %d\n", num_processes);

    printf("Melhor caminho encontrado e sua distância: ");
    for (int i = 0; i < data->num_cities; i++) {
        printf("%d ", data->best_path[i]);
    }
    printf("\n   Distância: %d\n", data->best_distance);

    printf("Número de iterações necessárias: %d\n", iterations);
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
            aj_pe_algorithm(data, max_execution_time, i + 1, filename, num_processes, start_time);
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
    printf("--MELHOR SOLUÇÃO--\n");
    print_solution(data, 0, filename, num_processes, start_time, max_execution_time);
    printf("Tempo que demorou até o programa atingir o melhor caminho encontrado: %ld segundos\n", end_time - start_time);
 
    // Detach and remove shared memory
    shmdt(data);
    shmctl(shmid, IPC_RMID, NULL);

    return 0;
}