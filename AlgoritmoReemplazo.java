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
			imprimeCola(cola);
			if(i!=tamano-1) {
				copiaPagina(i);
			}
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
	public void reemplazaPaginaLRU(int i) {
		if(marcosUsados<numMarcos) {
			marcos[i][marcosUsados]=referencia[i];
			marcosUsados++;
			if(cola.contains(referencia[i])) {
				fallo[i]="A";
			}
			else {
				fallo[i]="X";
			}
			cola.add(referencia[i]);
		}
		else {
			if(cola.contains(referencia[i])) {
				fallo[i]="A";
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
	public void reemplazaPaginaFIFO(int i) {
		if(marcosUsados<numMarcos) {
			marcos[i][marcosUsados]=referencia[i];
			marcosUsados++;
			if(cola.contains(referencia[i])) {
				fallo[i]="A";
			}
			else {
				fallo[i]="X";
			}
			cola.add(referencia[i]);
		}
		else {
			if(cola.contains(referencia[i])) {
				fallo[i]="A";
				
				
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
	public void copiaPagina(int i) {
		for (int j=0;j<numMarcos;j++) {
			marcos[i+1][j]=marcos[i][j];
		}
	}
	public void imprimeElementos() {
		for(int i=0;i<referencia.length;i++) {
			System.out.print(referencia[i]+"\t");
		}
		System.out.println("");
		for(int i=0;i<numMarcos;i++){
			for(int j=0;j<referencia.length;j++) {
				System.out.print(marcos[j][i]+"\t");
			}
			System.out.println("");
		}
		for(int i = 0;i<fallo.length;i++) {
			System.out.print(fallo[i]+"\t");
		}
		System.out.println("");
	} 
	public void imprimeCola(LinkedList<String> a) {
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

