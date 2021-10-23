import java.io.DataInputStream;
import java.io.IOException;
 
public class Main {
    private static void solution() throws Exception {
        int r = nextInt();
        int c = nextInt();
        int[][] arr = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                arr[i][j] = nextInt();
            }
        }
 
        for (int i = 0; i < r-1; i++) {
            for (int j = 0; j < c-1; j++) {
                if (arr[i][j] + arr[i+1][j+1] > arr[i+1][j] + arr[i][j+1]) {
                    System.out.println("No");
                    return;
                }
            }
        }
        System.out.println("Yes");
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
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
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
