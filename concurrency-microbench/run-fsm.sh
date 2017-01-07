#!/bin/bash

WARMUP_ITERATIONS=6
WORK_ITERATIONS=6
FORKS=3

OUT_FILE=$1

for threads in {0..8..2}
  do
    if [ $threads = 0 ]; then
        java -jar target/benchmarks.jar CasVsLockFSMBenchmark -wi $WARMUP_ITERATIONS -i $WORK_ITERATIONS -f $FORKS -t 1 -rff $OUT_FILE
    else
        java -jar target/benchmarks.jar CasVsLockFSMBenchmark -wi $WARMUP_ITERATIONS -i $WORK_ITERATIONS -f $FORKS -t $threads -rff $OUT_FILE
    fi
 done
