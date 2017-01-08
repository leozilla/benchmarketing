#!/bin/bash

WARMUP_ITERATIONS=6
WORK_ITERATIONS=6
FORKS=3

OUT_FILE=$1

java -jar target/benchmarks.jar CasVsLockFSMBenchmark -wi $WARMUP_ITERATIONS -i $WORK_ITERATIONS -f $FORKS -t 1 -rff $OUT_FILE
java -jar target/benchmarks.jar CasVsLockFSMBenchmark -wi $WARMUP_ITERATIONS -i $WORK_ITERATIONS -f $FORKS -t 2 -rff $OUT_FILE
java -jar target/benchmarks.jar CasVsLockFSMBenchmark -wi $WARMUP_ITERATIONS -i $WORK_ITERATIONS -f $FORKS -t 4 -rff $OUT_FILE
java -jar target/benchmarks.jar CasVsLockFSMBenchmark -wi $WARMUP_ITERATIONS -i $WORK_ITERATIONS -f $FORKS -t 8 -rff $OUT_FILE
