import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        initFI();
        LinkedList<Integer> ll = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int q = nextInt();
        StringBuilder sb = new StringBuilder();
        while (q-->0) {
            int order = nextInt();
            if (order == 1) {
                ll.add(nextInt());
            } else if (order == 2) {
                sb.append(pq.isEmpty() ? ll.pollFirst() : pq.poll()).append('\n');
            } else if (order == 3) {
                pq.addAll(ll);
                ll.clear();
            }
        }
        System.out.println(sb);
    }

    private static final int DEFAULT_BUFFER_SIZE = 1 << 16;
    private static DataInputStream inputStream;
    private static byte[] buffer;
    private static int curIdx, maxIdx;

    private static void initFI() {
        inputStream = new DataInputStream(System.in);
        buffer = new byte[DEFAULT_BUFFER_SIZE];
        curIdx = maxIdx = 0;
    }

    private static int nextInt() throws IOException {
        int ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = (c == '-');
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg) return -ret;
        return ret;
    }

    private static byte read() throws IOException {
        if (curIdx == maxIdx) {
            maxIdx = inputStream.read(buffer, curIdx = 0, DEFAULT_BUFFER_SIZE);
            if (maxIdx == -1) buffer[0] = -1;
        }
        return buffer[curIdx++];
    }
}
