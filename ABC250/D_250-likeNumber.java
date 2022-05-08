import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
 
public class Main extends FastInput {
    ArrayList<Integer> pn = new ArrayList<>();
 
    private void initPn() {
        boolean[] isPn = new boolean[1000000+1];
        int sqrtN = (int)Math.sqrt(1000000);
        for (int i = 3; i <= sqrtN; i+=2) {
            if (isPn[i]) continue;
            int base = i+i;
            while (base <= 1000000) {
                isPn[base] = true;
                base+=i;
            }
        }
        pn.add(2);
        for (int i = 3; i <= 1000000; i+=2) {
            if (!isPn[i]) pn.add(i);
        }
    }
 
    private int getDigitCnt(int n) { return getDigitCnt((long)n); }
    private int getDigitCnt(long n) {
        int cnt = 0;
        while (n != 0) {
            n/=10;
            cnt++;
        }
        return cnt;
    }
 
    private void solution() throws Exception {
        initPn();
        long n = nextLong();
        int cnt = 0;
        for (int i = 0; i < pn.size(); i++) {
            for (int j = i+1; j < pn.size(); j++) {
                int a = pn.get(i);
                int b = pn.get(j);
                if (a > n || b > n) break;
                long calc = 1l*a*b*b;
                if (calc > n) break;
                if (getDigitCnt(calc) + getDigitCnt(b) > 19) break;
                calc *= b;
                if (calc > n || calc < 0) break;
 
                cnt++;
            }
        }
        System.out.println(cnt);
    }
 
    private static BufferedReader br;
    public static void main(String[] args) throws Exception {
        initFI();
//        initSI();
        new Main().solution();
    }
}
 
class FastInput {
    private static final int DEFAULT_BUFFER_SIZE = 1 << 16;
    private static final int MAX_CHAR_LEN_FOR_NEXT_LINE = 64;
    private static DataInputStream inputStream;
    private static byte[] buffer;
    private static int curIdx, maxIdx;
 
    private static BufferedReader br;
 
    protected static void initFI() {
        inputStream = new DataInputStream(System.in);
        buffer = new byte[DEFAULT_BUFFER_SIZE];
        curIdx = maxIdx = 0;
    }
 
    protected static void initSI() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }
 
    protected static String nextLine() throws IOException {
        byte[] buf = new byte[MAX_CHAR_LEN_FOR_NEXT_LINE];
        int cnt = 0, c;
        while ((c = read()) != -1) {
            if (c == '\n') {
                if (cnt != 0) break;
                continue;
            }
            buf[cnt++] = (byte)c;
        }
        return new String(buf, 0, cnt);
    }
 
    protected static int nextInt() throws IOException {
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
 
    protected static long nextLong() throws IOException {
        long ret = 0;
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
 
    protected static double nextDouble() throws IOException {
        double ret = 0, div = 1;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = (c == '-');
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (c == '.') while ((c = read()) >= '0' && c <= '9') ret += (c - '0') / (div *= 10);
        if (neg) return -ret;
        return ret;
    }
 
    protected static char nextChar() throws IOException {
        byte c = read();
        while (c <= ' ') c = read();
        return (char)c;
    }
 
    protected static byte read() throws IOException {
        if (curIdx == maxIdx) {
            maxIdx = inputStream.read(buffer, curIdx = 0, DEFAULT_BUFFER_SIZE);
            if (maxIdx == -1) buffer[0] = -1;
        }
        return buffer[curIdx++];
    }
}
