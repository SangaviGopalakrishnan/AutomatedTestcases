import java.util.Scanner;

public class PG020TransposeOfMatrix {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int mat[][] = new int[3][3];
		int transmat[][] = new int[3][3];
		
		System.out.println("enter values for matrix");
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				mat[i][j]=sc.nextInt();
			}
		}
		
		System.out.println("Matrix is");
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				System.out.print(mat[i][j] + "\t");
			}
			System.out.println("");
		}
		
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				transmat[j][i] = mat[i][j];
			}
		}
		
		System.out.println("Transposed matrix");
		for (int i = 0; i < transmat.length; i++) {
			for (int j = 0; j < transmat.length; j++) {
				System.out.print(transmat[i][j] + "\t");
			}
			System.out.println("");
		}
	}
}
