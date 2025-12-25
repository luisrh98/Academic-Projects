package es.iessoterhernandez.daw.endes.fibonacci;

public class Fibonacci {
    public static int[] generateSequence(int n) {
        if (n <= 0) {
            return new int[]{};
        } else if (n == 1) {
            return new int[]{0};
        } else if (n == 2) {
            return new int[]{0, 1};
        } else {
            int[] sequence = new int[n];
            sequence[0] = 0;
            sequence[1] = 1;
            for (int i = 2; i < n; i++) {
                sequence[i] = sequence[i - 1] + sequence[i - 2];
            }
            return sequence;
        }
    }
}
