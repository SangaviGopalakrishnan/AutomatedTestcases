import java.util.Scanner;

public class PG021AdditionOfMatrix {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int mat1[][] = new int[3][3];
		int mat2[][] = new int[3][3];
		int mat3[][] = new int[3][3];
		
		System.out.println("enter values for matrix1");
		for (int i = 0; i < mat1.length; i++) {
			for (int j = 0; j < mat1.length; j++) {
				mat1[i][j]=sc.nextInt();
			}
		}
		
		System.out.println("Matrix1 is");
		for (int i = 0; i < mat1.length; i++) {
			for (int j = 0; j < mat1.length; j++) {
				System.out.print(mat1[i][j] + "\t");
			}
			System.out.println("");
		}
		
		System.out.println("enter values for matrix2");
		for (int i = 0; i < mat2.length; i++) {
			for (int j = 0; j < mat2.length; j++) {
				mat2[i][j]=sc.nextInt();
			}
		}
		
		System.out.println("Matrix is");
		for (int i = 0; i < mat2.length; i++) {
			for (int j = 0; j < mat2.length; j++) {
				System.out.print(mat2[i][j] + "\t");
			}
			System.out.println("");
		}
		
		for (int i = 0; i < mat3.length; i++) {
			for (int j = 0; j < mat3.length; j++) {
				mat3[i][j]=mat1[i][j]+mat2[i][j];
			}
		}
		
		System.out.println("Added Matrix is");
		for (int i = 0; i < mat3.length; i++) {
			for (int j = 0; j < mat3.length; j++) {
				System.out.print(mat3[i][j] + "\t");
			}
			System.out.println("");
		}
	}
}
