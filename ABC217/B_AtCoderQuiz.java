import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
 
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> hs = new HashSet<>();
        hs.add("ABC");
        hs.add("ARC");
        hs.add("AGC");
        hs.add("AHC");
        for (int i = 0; i < 3; i++) {
            hs.remove(br.readLine());
        }
        System.out.println(hs.iterator().next());
    }
}
