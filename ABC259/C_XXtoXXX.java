import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class Main extends FastInput {
    private ArrayList<int[]> analysys(String s) {
        ArrayList<int[]> arr = new ArrayList<>();
        int[] bf = new int[2];
        bf[0] = s.charAt(0);
        bf[1] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == bf[0]) {
                bf[1]++;
                continue;
            }
            arr.add(bf);
            bf = new int[2];
            bf[0] = s.charAt(i);
            bf[1] = 1;
        }
        arr.add(bf);
        return arr;
    }
    private void solution() throws Exception {
        String s = nextLine();
        String t = nextLine();
        ArrayList<int[]> arrS = analysys(s);
        ArrayList<int[]> arrT = analysys(t);
        if (arrS.size() != arrT.size()) {
            System.out.println("No");
            return;
        }
        for (int i = 0; i < arrS.size(); i++) {
            int[] tmpS = arrS.get(i);
            int[] tmpT = arrT.get(i);
            if (tmpS[0] != tmpT[0] || tmpS[1] > tmpT[1] || tmpS[1] != tmpT[1] && tmpS[1] == 1) {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
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
    private static final int MAX_CHAR_LEN_FOR_NEXT_LINE = 200001;
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
