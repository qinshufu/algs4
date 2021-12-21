package sort;

import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class CompareSorter {
    public static void main(String[] args) {
        var numsCount = Integer.parseInt(args[0]);
        var runTimes = Integer.parseInt(args[1]);

        // var insertionTime = testSortedTime(new InsertionSorter<Double>(), numsCount, runTimes);
        // var selectionTime = testSortedTime(new SelectionSorter<Double>(), numsCount, runTimes);
        var shellTime = testSortedTime(new ShellSorter<Double>(), numsCount, runTimes);
        var mergeTime = testSortedTime(new MergeSorter<Double>(), numsCount, runTimes);

        // printSortedTime("insertion", insertionTime);
        // printSortedTime("selection", selectionTime);
        printSortedTime("shell", shellTime);
        printSortedTime("merge", mergeTime);
    }

    public static void printSortedTime(String sortAlgs, double time) {
        var msg = String.format("%-10s\t %6.4f", sortAlgs, time);
        System.out.println(msg);
    }

    public static Double testSortedTime(Sorter<Double> sorter, int randomArraySize, int runCount) {
        var array = generateRandomNumbers(randomArraySize);
        var randomArrays = copyArrayNTime(array, runCount);

        var watch = new Stopwatch();
        for (int i = 0; i < randomArrays.length; i++) {
            var randomArray = randomArrays[i];
            sorter.sort(randomArray);
        }

        // 验证以下是否真的有序了
        for (int i = 0; i < runCount; i++) {
            assert Sorter.isSorted(randomArrays[i]);
        }

        return watch.elapsedTime();
    }

    public static Double[][] copyArrayNTime(Double[] array, int time) {
        var copiedArrays = new Double[time][];

        for (int i = 0; i < copiedArrays.length; i++) {
            copiedArrays[i] = Arrays.copyOf(array, array.length);
        }
        return copiedArrays;
    }

    public static Double[] generateRandomNumbers(int count) {
        var nums = new Double[count];
        for (int i = 0; i < count; i++) {
            nums[i] = StdRandom.uniform();
        }

        return nums;
    }
}
