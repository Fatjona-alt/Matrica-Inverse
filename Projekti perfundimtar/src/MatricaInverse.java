import java.util.Scanner;

public class MatricaInverse {
	
	public static void main(String args[]) 
    {
		
        Scanner input = new Scanner(System.in);
        System.out.println("Shkruani dimensionin e matrices katrore: ");
        int n = input.nextInt();
        double a[][]= new double[n][n];
        System.out.println("Shkruani elemnetet e matrices: ");
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                a[i][j] = input.nextDouble();
        
 
        double d[][] = invert(a);
        
        System.out.println("Inversi i matrices ?sht?: ");
        for (int i=0; i<n; ++i) 
        {
            for (int j=0; j<n; ++j)
            {
                System.out.print(d[i][j]+"  ");
            }
            System.out.println();
            
        }
        input.close();
    }	
	 
    public static double[][] invert(double a[][]) 
    {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i) 
            b[i][i] = 1;
 
 // Transformoni matric?n n? nj? trek?nd?sh t? sip?rm
        
        gaussian(a, index);
 
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                    	    -= a[index[j]][i]*b[index[i]][k];
 
 // Kryeni z?vend?sime t? prapambetura
        for (int i=0; i<n; ++i) 
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) 
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) 
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }
 
// K?tu indeksi [] ruan renditjen
 
    public static void gaussian(double a[][], int index[]) 
    {
        int n = index.length;
        double c[] = new double[n];
 

        for (int i=0; i<n; ++i) 
            index[i] = i;
 
 // Gjejm? faktor?t m? t? vog?l, nj? nga secila rresht
        for (int i=0; i<n; ++i) 
        {
            double c1 = 0;
            for (int j=0; j<n; ++j) 
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
 
 // K?rkojm? elementin rrotullues nga secila kolon?
        int k = 0;
        for (int j=0; j<n-1; ++j) 
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i) 
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) 
                {
                    pi1 = pi0;
                    k = i;
                }
            }
 
   // Shk?mbejm? rreshtat sipas renditjes s? pivotimit
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) 	
            {
                double pj = a[index[i]][j]/a[index[j]][j];
 
 // Regjistrojm? raportet e pivotimit n?n diagonale
                a[index[i]][j] = pj;
 
 //Modifikojm? elementet e tjer? n? p?rputhje me rrethanat
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }
}



