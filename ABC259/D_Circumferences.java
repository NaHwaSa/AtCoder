import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
class Circle {
    long x, y, r;
    public Circle(long x, long y, long r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
    public static boolean edgeChk(Circle a, Circle b) {
        if (a.r == 0) return circumferenceChk(a.x, a.y, b);
        if (b.r == 0) return circumferenceChk(b.x, b.y, a);
        long gapX = 1l*(a.x-b.x)*(a.x-b.x);
        long gapY = 1l*(a.y-b.y)*(a.y-b.y);
        if (gapX+gapY == 0) return false;
        if (gapX+gapY < 1l*(a.r-b.r)*(a.r-b.r)) return false;
        return 1l*(a.r+b.r)*(a.r+b.r) >= gapX+gapY;
    }
    private static boolean circumferenceChk(long x, long y, Circle a) {
        long gapX = (a.x-x)*(a.x-x);
        long gapY = (a.y-y)*(a.y-y);
        return 1l*a.r*a.r == gapX+gapY;
    }
}
public class Main extends FastInput {
    private ArrayList<Integer>[] edges;
    private boolean[] v;
    private boolean dfs(int idx) {
        if (idx == 1) {
            return true;
        }
        for (int next : edges[idx]) {
            if (v[next]) continue;
            v[next] = true;
            if (dfs(next))
                return true;
        }
        return false;
    }
    private void solution() throws Exception {
        int n = nextInt();
        ArrayList<Circle> arr = new ArrayList<>();
        edges = new ArrayList[n+2];
        v = new boolean[n+2];
        for (int i = 0; i <= n+1; i++) edges[i] = new ArrayList<>();
        arr.add(new Circle(nextInt(), nextInt(), 0));
        arr.add(new Circle(nextInt(), nextInt(), 0));
        if (arr.get(0).x == arr.get(1).x && arr.get(0).y == arr.get(1).y) {
            System.out.println("Yes");
            return;
        }
        for (int i = 2; i <= n+1; i++) {
            Circle circle = new Circle(nextInt(), nextInt(), nextInt());
            arr.add(circle);
            for (int j = 0; j < i; j++) {
                if (Circle.edgeChk(circle, arr.get(j))) {
                    edges[i].add(j);
                    edges[j].add(i);
                }
            }
        }
        v[0] = true;
        System.out.println(dfs(0)?"Yes":"No");
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
