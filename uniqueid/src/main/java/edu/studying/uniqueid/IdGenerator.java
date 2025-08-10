/*
 * MIT License
 *
 * Copyright (c) 2025 Cristiano Silva
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package edu.studying.uniqueid;

import com.fasterxml.uuid.Generators;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Benchmark)
public class IdGenerator {

  @Param({"345675274834", "562436736815"})
  private String inputNumber;

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
  @Measurement(iterations = 2000, time = 100, timeUnit = TimeUnit.MILLISECONDS)
  @Fork(2)
  public UUID generateType3UUID() {

    // 345675274834 -> 2e2aa146-649b-3571-9593-15bd5e71cdea
    // 562436736815 -> 84bf0a75-7290-329c-b716-097ee671fd35
    return UUID.nameUUIDFromBytes(inputNumber.getBytes(StandardCharsets.UTF_8));
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
  @Measurement(iterations = 2000, time = 100, timeUnit = TimeUnit.MILLISECONDS)
  @Fork(2)
  public UUID generateType5UUID() {

    // 345675274834 -> 2b95d9f9-632a-5289-b209-276fbc66909b
    // 562436736815 -> 65db9e44-e488-5d35-b364-195f5383a9ca
    return Generators.nameBasedGenerator().generate(inputNumber);
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
  @Measurement(iterations = 2000, time = 100, timeUnit = TimeUnit.MILLISECONDS)
  @Fork(2)
  public HashCode generateGuavaSha256HashCode() {

    // 345675274834 -> eee008c08cbc0f5fc5726bec97693ba34db5737ac27afdc8e3ddaea132682853
    // 562436736815 -> cc38d9bfb567648e7be86b53752c95fa35c3071ad98136d23583c04c871be900
    return Hashing.sha256().hashString(inputNumber, StandardCharsets.UTF_8);
  }
}
