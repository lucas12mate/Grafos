import java.util.Scanner;

public class GeraCSV {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (j != n-1){
					System.out.print(scan.nextInt()+";");
				} else {
					System.out.print(scan.nextInt()+"\n");
				}
			}
		}
	}
}