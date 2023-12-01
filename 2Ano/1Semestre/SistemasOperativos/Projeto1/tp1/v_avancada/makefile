CC = gcc
CFLAGS = -pthread
TARGET = tsp

.PHONY: build run run_10

build: 
	$(CC) $(CFLAGS) -o $(TARGET) main.c

run:
	./$(TARGET) $(ARGS)

run_10:
	for i in {1..10}; do \
		./$(TARGET) $(ARGS); \
	done
