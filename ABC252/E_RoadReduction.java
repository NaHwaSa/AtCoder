import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
 
public class Main extends FastInput {
    class Edge {
        int to, dist, idx;
        public Edge(int to, int dist, int idx) {
            this.to = to;
            this.dist = dist;
            this.idx = idx;
        }
    }
    class Pos implements Comparable<Pos> {
        int num;
        long dist;
        public Pos(int num, long dist) {
            this.num = num;
            this.dist = dist;
        }
 
        @Override
        public int compareTo(Pos o) {
            long calc = this.dist - o.dist;
            if (calc < 0) return -1;
            else if (calc > 0) return 1;
            return 0;
        }
    }
    int n;
    ArrayList<Edge>[] edges;
 
    private int[] dijkstra() {
        long[] dist = new long[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        int[] selectedEdgeIdx = new int[n+1];
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.add(new Pos(1, 0));
        dist[0] = 0;
        while (!pq.isEmpty()) {
            Pos cur = pq.poll();
            if (cur.dist > dist[cur.num]) continue;
            for (Edge edge : edges[cur.num]) {
                int next = edge.to;
                if (dist[next] <= edge.dist + cur.dist) continue;
                dist[next] = cur.dist + edge.dist;
                selectedEdgeIdx[next] = edge.idx;
                pq.add(new Pos(next, dist[next]));
            }
        }
        return selectedEdgeIdx;
    }
 
    private void solution() throws Exception {
        n = nextInt();
        int m = nextInt();
        edges = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) edges[i] = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            int a = nextInt();
            int b = nextInt();
            int c = nextInt();
            edges[a].add(new Edge(b, c, i));
            edges[b].add(new Edge(a, c, i));
        }
        int[] selectedEdgeIdx = dijkstra();
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            sb.append(selectedEdgeIdx[i]).append(' ');
        }
        System.out.println(sb);
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
    private static final int MAX_CHAR_LEN_FOR_NEXT_LINE = 15;
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
