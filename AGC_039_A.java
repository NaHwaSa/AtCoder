import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
 
public class Main {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String s = br.readLine();		
		long k = Integer.parseInt(br.readLine());	
		int cnt = 1;
		long sum = 0;
		char c = s.charAt(0);
		boolean allSame = true;
		for (int i = 1; i < s.length(); i++) {
			if (c != s.charAt(i)) {
				sum += cnt/2;
				cnt = 1;
				c = s.charAt(i);
				allSame = false;
			}else
				cnt++;
		}
		if (allSame) {
			bw.write((k * s.length()) / 2 + "\n");
			bw.flush();
			return;
		}
		sum += cnt/2;
		if (s.charAt(0)!=s.charAt(s.length()-1)) {
			bw.write(sum*k + "\n");
			bw.flush();
			return;
		}
		s += s;
		cnt = 1;
		long sum2 = 0;
		c = s.charAt(0);
		for (int i = 1; i < s.length(); i++) {
			if (c != s.charAt(i)) {
				sum2 += cnt/2;
				cnt = 1;
				c = s.charAt(i);
			}else
				cnt++;
		}
		sum2 += cnt/2;
		bw.write(sum + (sum2-sum)*(k-1) + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
