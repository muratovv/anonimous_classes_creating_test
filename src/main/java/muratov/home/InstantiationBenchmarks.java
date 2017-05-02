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

package muratov.home;

import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class InstantiationBenchmarks {


    private Random random = new Random();

    @Benchmark
    public int staticClassGet() {
        Gettable gettable = new StaticGet();
        return gettable.get();
    }

    @Benchmark
    public int anonymousClassGet() {
        Gettable gettable = new Gettable() {
            @Override
            public int get() {
                return 0;
            }
        };
        return gettable.get();
    }

    @Benchmark
    public int staticClassSetAndGet() {
        int       settableValue = random.nextInt();
        StaticGet gettable       = new StaticGet();
        gettable.set(settableValue);
        return gettable.get();
    }

    @Benchmark
    public int anonymousClassClosureAndGet() {
        final int closureValue = random.nextInt();
        Gettable gettable = new Gettable() {
            @Override
            public int get() {
                return closureValue;
            }
        };
        return gettable.get();
    }


    //    @Benchmark
    //    public int lambdaGet() {
    //        Gettable getable = () -> 0;
    //        return getable.get();
    //    }

    private interface Gettable {
        int get();
    }

    static class StaticGet implements Gettable {

        private int val;

        @Override
        public int get() {
            return val;
        }

        public void set(int val) {
            this.val = val;
        }
    }
}
