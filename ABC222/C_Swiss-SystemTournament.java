import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
 
public class Main {
    static class People {
        int num, cnt;
        public People(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
 
    private static int comp(char a, char b) {
        if (a == b) return 0;
        switch (a) {
            case 'P' : return b=='G' ? -1 : 1;
            case 'G' : return b=='C' ? -1 : 1;
            case 'C' : return b=='P' ? -1 : 1;
        }
        return -2;
    }
 
    private static void solution() throws Exception {
        int n = 2*nextInt();
        int m = nextInt();
        char[][] arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            String row = nextLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = row.charAt(j);
            }
        }
 
        int[] cntAll = new int[n+1];
 
        PriorityQueue<People> pq = new PriorityQueue<>(new Comparator<People>() {
            @Override
            public int compare(People o1, People o2) {
                if (o1.cnt == o2.cnt)
                    return o1.num - o2.num;
                return o2.cnt - o1.cnt;
            }
        });
 
        for (int i = 1; i <= n; i++) {
            pq.add(new People(i, 0));
        }
 
        for (int round = 0; round < m; round++) {
            int match = 0;
            while (match < n-1) {
                int a = pq.poll().num;
                int b = pq.poll().num;
                int res = comp(arr[a-1][round], arr[b-1][round]);
                if (res == -1) {
                    cntAll[a]++;
                } else if (res == 1) {
                    cntAll[b]++;
                }
                match += 2;
            }
 
            int idx = 0;
 
            pq = new PriorityQueue<>(new Comparator<People>() {
                @Override
                public int compare(People o1, People o2) {
                    if (o1.cnt == o2.cnt)
                        return o1.num - o2.num;
                    return o2.cnt - o1.cnt;
                }
            });
            for (int i = 1; i <= n; i++) {
                pq.add(new People(i, cntAll[i]));
            }
        }
 
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(pq.poll().num).append('\n');
        }
        System.out.println(sb);
    }
 
    public static void main(String[] args) throws Exception {
        initFI();
        solution();
    }
 
    private static final int DEFAULT_BUFFER_SIZE = 1 << 16;
    private static final int MAX_CHAR_LEN_FOR_NEXT_LINE = 101;
    private static DataInputStream inputStream;
    private static byte[] buffer;
    private static int curIdx, maxIdx;
 
    private static void initFI() {
        inputStream = new DataInputStream(System.in);
        buffer = new byte[DEFAULT_BUFFER_SIZE];
        curIdx = maxIdx = 0;
    }
 
    private static String nextLine() throws IOException {
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
 
    private static long nextLong() throws IOException {
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
 
    private static double nextDouble() throws IOException {
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
 
    private static char nextChar() throws IOException {
        byte c = read();
        while (c <= ' ') c = read();
        return (char)c;
    }
 
    private static byte read() throws IOException {
        if (curIdx == maxIdx) {
            maxIdx = inputStream.read(buffer, curIdx = 0, DEFAULT_BUFFER_SIZE);
            if (maxIdx == -1) buffer[0] = -1;
        }
        return buffer[curIdx++];
    }
}
