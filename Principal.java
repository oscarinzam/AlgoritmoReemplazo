
import java.util.Scanner;
public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Dame el numero de marcos");
		int numMarcos=sc.nextInt();
		System.out.println("Deme el numero de referencias");
		int NumEnt=sc.nextInt();
		String[] Referencias= new String[NumEnt];
		String hola=sc.nextLine();
		for(int i=0;i<NumEnt;i++)
		{
			System.out.println("Deme la referencia " +(i+1));
			
			Referencias[i]=sc.nextLine();
			
		}
		sc.close();
		AlgoritmoReemplazo ar= new AlgoritmoReemplazo(numMarcos, Referencias);
		System.out.println("\nAlgoritmo FIFO\n");
		ar.AlgoritmoFifo();
		ar.imprimeElementos();
		System.out.println("\nAlgoritmo LRU\n");
		ar=new AlgoritmoReemplazo(numMarcos,Referencias);
		ar.AlgoritmoLRU();
		ar.imprimeElementos();
		
	}

}