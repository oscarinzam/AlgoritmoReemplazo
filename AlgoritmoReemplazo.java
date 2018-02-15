import java.util.LinkedList;
import java.util.ListIterator;
public class AlgoritmoReemplazo {
	private String[][] marcos;
	private String[] referencia;
	private String[] fallo;
	private LinkedList<String> cola;
	private int marcosUsados;
	private int numMarcos;
	
	public AlgoritmoReemplazo(int nummarcos,String[] referencia) {
		marcosUsados=0;
		this.numMarcos=nummarcos;
		marcos=new String[referencia.length][nummarcos];
		
		this.referencia=referencia;
		fallo=new String[referencia.length];
		cola=new LinkedList<String>();
	}
	public void AlgoritmoFifo() {
		int tamano=referencia.length;
		for(int i=0;i<tamano;i++) {
			reemplazaPaginaFIFO(i);
			if(i!=tamano-1) {
				copiaPagina(i);
			}
			System.out.print((i+1)+". ");
			imprimeCola(cola);
		}
	}
	public void AlgoritmoOptimo() {
		int tamano=referencia.length;
		for(int i=0;i<tamano;i++) {
			reemplazaPaginaOptimo(i);
			if(i!=tamano-1) {
				copiaPagina(i);
			}
			System.out.print((i+1)+". ");
			imprimeCola(cola);
		}
	}
	public void AlgoritmoLRU() {
		int tamano=referencia.length;
		for(int i=0;i<tamano;i++) {
			reemplazaPaginaLRU(i);
			if(i!=tamano-1) {
				copiaPagina(i);
			}
			System.out.print((i+1)+". ");
			imprimeCola(cola);
		}
	}
	private void reemplazaPaginaOptimo(int i) {
		if(marcosUsados<numMarcos) {
			
			if(cola.contains(referencia[i])) {
				fallo[i]=" ";
			}
			else {
				marcos[i][marcosUsados]=referencia[i];
				marcosUsados++;
				fallo[i]="X";
				cola.add(referencia[i]);
			}
			
		}
		else {
			if(cola.contains(referencia[i])) {
				fallo[i]=" ";
			}
			else {
				int masLejano=checarMasLejano(marcos[i],i);
				cola.set(cola.indexOf(marcos[i][masLejano]),referencia[i]);
				marcos[i][masLejano]=referencia[i];
				fallo[i]="X";
			}
		}
	}
	private void reemplazaPaginaLRU(int i) {
		if(marcosUsados<numMarcos) {
			
			if(cola.contains(referencia[i])) {
				fallo[i]=" ";
			}
			else {
				marcos[i][marcosUsados]=referencia[i];
				marcosUsados++;
				fallo[i]="X";
				cola.add(referencia[i]);
			}
			
		}
		else {
			if(cola.contains(referencia[i])) {
				fallo[i]=" ";
				cola.add(cola.removeFirst());				
			}
			else {
				fallo[i]="X";
				String elementoRemovido= cola.removeFirst();
				for(int j=0;j<numMarcos;j++) {
					if(marcos[i][j]==elementoRemovido) {
						marcos[i][j]=referencia[i];
						cola.add(referencia[i]);
					}
				}
			}
			
			
		}
	
	}
	private void reemplazaPaginaFIFO(int i) {
		if(marcosUsados<numMarcos) {
			if(cola.contains(referencia[i])) {
				fallo[i]=" ";
			}
			else {
				marcos[i][marcosUsados]=referencia[i];
				marcosUsados++;
				fallo[i]="X";
				cola.add(referencia[i]);
			}
		}
		else {
			if(cola.contains(referencia[i])) {
				fallo[i]=" ";
				
				
			}
			else {
				fallo[i]="X";
				String elementoRemovido= cola.removeFirst();
				for(int j=0;j<numMarcos;j++) {
					if(marcos[i][j]==elementoRemovido) {
						marcos[i][j]=referencia[i];
						cola.add(referencia[i]);
					}
				}
			}
			
			
		}
	
	}
	
	private int checarMasLejano(String marcos[],int currPos) {
		int masLejano=-1;
		boolean[]  checados=new boolean[marcos.length];
		for(int i=0;i<checados.length;i++) {
			checados[i]=false;
		}
		for(int i=currPos;i<referencia.length;i++) {
			
			for(int j=0;j<marcos.length;j++) {
				//System.out.println("checando marco "+marcos[j]+"con la referencia "+referencia[i]+" Su valor asignado es "+checados[j]+"Su valor real es"+(marcos[j]==referencia[i]));
				if(marcos[j].equals(referencia[i])&&checados[j]==false) {
					masLejano=j;
					checados[j]=true;
					
				}
			}
		}
		
		for(int i=0;i<checados.length;i++) {
			if(checados[i]==false) {
				masLejano=i;
				break;
			}
						
		}
		
			return masLejano;
		
	}
	private void copiaPagina(int i) {
		for (int j=0;j<numMarcos;j++) {
			marcos[i+1][j]=marcos[i][j];
		}
	}
	public void imprimeElementos() {
		System.out.print("Referencia:\t");
		for(int i=0;i<referencia.length;i++) {
			System.out.print(referencia[i]+"\t");
		}
		System.out.println("");
		for(int i=0;i<numMarcos;i++){
			System.out.print("Marco "+i+":   \t");
			for(int j=0;j<referencia.length;j++) {
				System.out.print(marcos[j][i]+"\t");
			}
			System.out.println("");
		}
		System.out.print("Fallos:   \t\t");
		for(int i = 0;i<fallo.length;i++) {
			System.out.print(fallo[i]+"\t");
		}
		System.out.println("\n");
	} 
	private void imprimeCola(LinkedList<String> a) {
		ListIterator<String> it=a.listIterator();
		while(it.hasNext()) {
			String elemento=it.next();
			if(it.hasNext())
				System.out.print(elemento+" , ");
			else
				System.out.print(elemento);
		}
		System.out.println("");
	}
	
}

