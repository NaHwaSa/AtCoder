import java.io.DataInputStream;
import java.io.IOException;
 
public class Main {
    static private class Pos {
        long r, c;
        public Pos(long r, long c) {
            this.r = r;
            this.c = c;
        }
    }
 
    private static void solution() throws Exception {
        int n = nextInt();
        Pos[] arr = new Pos[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pos(nextInt(), nextInt());
        }
 
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    long s = (arr[j].r-arr[i].r)*(arr[k].c-arr[i].c)-(arr[j].c-arr[i].c)*(arr[k].r-arr[i].r);
                    if (s != 0) answer++;
                }
            }
        }
        System.out.println(answer);
    }
 
    public static void main(String[] args) throws Exception {
        initFI();
        solution();
    }
 
    private static final int DEFAULT_BUFFER_SIZE = 1 << 16;
    private static final int MAX_CHAR_LEN_FOR_NEXT_LINE = 64;
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
