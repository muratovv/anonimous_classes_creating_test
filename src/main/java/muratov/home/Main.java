package muratov.home;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author @muratovv
 * @date 02.05.17
 */
public class Main {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + InstantiationBenchmarks.class.getSimpleName() + ".*")
                .warmupIterations(10)
                .forks(1)
                .measurementIterations(10)
                .build();
        new Runner(opt).run();
    }
}
