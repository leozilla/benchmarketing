/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.leonhart.benchmarketing;

import com.leonhart.concurrency.bench.casvslock.GetAndUpdateFSM;
import com.leonhart.concurrency.bench.casvslock.LockFSM;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

// java -jar target/benchmarks.jar JMHSample_02 -wi 5 -i 5 -f 1
@State(Scope.Benchmark)
public class CasVsLockFSMBenchmark {
    GetAndUpdateFSM casFSM = new GetAndUpdateFSM();
    LockFSM lockFSM = new LockFSM();

    @Benchmark
    public boolean atomicIntegerUpdateAndGetFSM() {
        return exploreCasFsm();
    }

    @Benchmark
    public boolean lockFSM() {
        return exploreLockFsm();
    }

    private boolean exploreLockFsm() {
        lockFSM.associate();
        lockFSM.onAssociated();
        lockFSM.disassociate();
        lockFSM.onDisassociated();
        return lockFSM.isAssociated();
    }

    private boolean exploreCasFsm() {
        casFSM.associate();
        casFSM.onAssociated();
        casFSM.disassociate();
        casFSM.onDisassociated();
        return casFSM.isAssociated();
    }
}
