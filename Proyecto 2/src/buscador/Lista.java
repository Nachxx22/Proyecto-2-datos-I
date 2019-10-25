package buscador;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Segundo Proyecto de algoritmos y estructuras de datos 1
 * Text Finder
 *  @author sergio
 * @author ignacio
 *
 */
public class Lista {
	private Nodo cabeza;
	private int longitud=0;
	/**
	 * Clase privada que crea los nodos de las listas enlazadas con sus respectivos atributos
	 * @author sergio
	 *
	 */
	private class Nodo{
		public File archivo;
		public float Peso;
		public Nodo siguiente=null;
		/**
		 * Constructor de la clase, crea los nuevos nodos
		 * @param archivo archivo tipo File a guardar
		 */
		public Nodo(File archivo) {
			this.siguiente=null;
			this.archivo=archivo;
			this.Peso=archivo.length();
		}
		/**
		 * Funcion que retorna la fecha de creacion un archivo
		 * @param act Archivo que se desea conocer la fecha
		 * @return Fecha de creacion del archivo recibido
		 */
		public int getDate(Nodo act) {
			BasicFileAttributes attrs;
			String formatted="";
			try {
				attrs = Files.readAttributes(archivo.toPath(), BasicFileAttributes.class);
				FileTime time = attrs.creationTime();
				String forFecha = "dd";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(forFecha);
				formatted = simpleDateFormat.format( new Date( time.toMillis() ) );
				System.out.println(formatted);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return Integer.parseInt(formatted);
		}
		/**
		 * Funcion que sirve para intercambiar dos nodos de lugar en una lista
		 * @author ignacio
		 * @param j Nodo 1
		 * @param h Nodo 2
		 */
		public void Swap(Nodo j, Nodo h) {
	        File temp;
	        File htemp;
	        temp=j.archivo;
	        htemp=h.archivo;
	        j.cambiarDato(htemp);
	        h.cambiarDato(temp);
	    }
		/**
		 * Funcion para cambiar algun dato de la lista
		 * @author ignacio
		 * @param k Dato a reemplazar
		 */
		public void cambiarDato(File k){
	        this.archivo=k;
	    }
	}
	/**
	 * Funcion que retorna la pocision de un elemento cuando este es igual a uno deseado
	 * @author ignacio
	 * @param search Elemento a buscar
	 * @param lista Lista en donde buscar
	 * @return pocision de elemento encontrado
	 */
	public int indi(String search,String[] lista){
        int i=0;
        while(lista[i].equals(search)!=true){
            i++;
        }
       return i;
    }
	/**
	 * Funcion para agregar un elemento al final de la lista
	 * @param archivo Elemento a agregar
	 */
	public void addLast(File archivo) {
		Nodo nodo =new Nodo(archivo);
		Nodo current =cabeza;
		if(cabeza==null) {
			cabeza=nodo;
			cabeza.siguiente=null;
			longitud++;
		}
		else {
		while(current.siguiente!=null) {
			current=current.siguiente;
		}
		current.siguiente=nodo;
		longitud++;
		}
	}
	/**
	 * Funcion para obtener el ultimo elemento de la lista
	 * @return Ultimo elemento de la lista
	 */
	public File getLast(){
		Nodo current=cabeza;
		if(current==null) {
			return null;
		}else {
		while(current.siguiente!=null) {
			current=current.siguiente;
		}
		return current.archivo;
		}
	}
	/**
	 * Funcion para obtener la longitud de la lista
	 * @return longitud de la lista
	 */
	public int longitud() {
		return longitud;
	}
	/**
	 * Funcion usada para obtener algun nodo deseado 
	 * @param i posicion del nodo en la lista
	 * @return Nodo en la pocision otorgada
	 */
	public Nodo Obt(int i) {
		if (cabeza ==null) {
			return null;
		}else {
			Nodo current =cabeza;
			int cont =0;
			while(cont<i && current.siguiente!=null) {
				current=current.siguiente;
				cont++;
			}if(cont!=i) {
				return null;
			}else {
				return current;
			}
		}
	}
	
	/**
	 * Funcion para obtener el dato de un nodo deseado
	 * @param i Posicion del nodo deseado
	 * @return Dato guardado en el nodo de posicion i
	 */
	public  File obt(int i) {
		if (cabeza ==null) {
			return null;
		}else {
			Nodo current =cabeza;
			int cont =0;
			while(cont<i && current.siguiente!=null) {
				current=current.siguiente;
				cont++;
			}if(cont!=i) {
				return null;
			}else {
				return current.archivo;
			}
		}
	}
	
	/**
	 * Funcion para eliminar un nodo deseado 
	 * @param i Posicion del nodo a eliminar
	 */
	public void eliminar(int i) {
		if(cabeza!=null) {
			if(i==0) {
				Nodo primer=cabeza;
				cabeza=cabeza.siguiente;
				primer.siguiente=null;
				longitud--;
			}else if(i<longitud) {
				Nodo current=cabeza;
				int contador=0;
				while(contador<(i-1)) {
					current=current.siguiente;
					contador++;
				}
				Nodo temp=current.siguiente;
				current.siguiente=temp.siguiente;
				temp.siguiente=null;
				longitud--;
		}
	}
	
	}
	/**
	 * Funcion para ordenar la lista de acuerdo a la fercha de creacion de cada uno de sus nodos
	 * @author ignacio
	 */
	public void bubblesort() {
        int t = 1, c = 1;
        Nodo act=cabeza;/*definimos que el apuntador act esta en el primer nodo*/
        
        while (act.siguiente != null)//Este while cuenta el total de nodos.
        {
            act = act.siguiente;
            c++;
        }
        //aqui se hace el ordenamiento
        do {
            act = cabeza;//aux esta en el primer nodo
            Nodo sig = act.siguiente;//esta en el siguiente nodo
            while (act.siguiente != null) {
                if (act.getDate(act) > sig.getDate(sig)) {//get date
                   act.Swap(act,sig);
                   act=act.siguiente;
                   sig=sig.siguiente;
                } else {
                    //[1] [3] [2]
                    //    act sig
                    act = act.siguiente;
                    sig = sig.siguiente;
                }
            }
            t++;
        } while (t <= c);//act.ap_sig != null);
    }
	/**
	 * Funcion para obtener el peso de un archivo deseado
	 * @author ignacio
	 * @param archivo Archivo a saber el peso
	 * @return Peso del archivo seleccionado
	 */
	public long getPeso(File archivo) {
		return archivo.length();
	}

    /**
     * Funcion para ordenar la lista de acuerdo al nombre de cada uno de sus nodos 
     * @author ignacio
     * @param lista Lista a ordenar
     */
    public void Quicksort(Lista lista){
        Nodo last=null;Nodo first=cabeza;Nodo act=cabeza;Nodo sig=cabeza.siguiente;
        int pivot=longitud/2;
        Nodo piv=  lista.Obt(pivot);
        String[] ABC=new String[26];
        ABC[0]="A";ABC[1]="B";ABC[2]="C";ABC[3]="D";ABC[4]="E";ABC[5]="F";ABC[6]="G";ABC[7]="H";ABC[8]="I";ABC[9]="J";ABC[10]="K";ABC[11]="L";ABC[12]="M";ABC[13]="N";ABC[14]="O";ABC[15]="P";ABC[16]="Q";ABC[17]="R";ABC[18]="S";ABC[19]="T";ABC[20]="U";ABC[21]="V";ABC[22]="W";ABC[23]="X";ABC[24]="Y";ABC[25]="Z";
        
        while(act.siguiente!=null){ //Para tener el ultimo nodo en la variable last
            last=act.siguiente;
            act=act.siguiente;
        }
        System.out.println();
        boolean change=true;
        while(change!=false){// while para verificar si hubos cambios, cuando no los haya no entrara a while por que la lista ya esta ordenada
            change=false;
            //first=cabeza;
            piv= (Nodo) lista.Obt(pivot);// Reiniciar parametros para que se ordene desde el inicio cada vez que entra
            sig=first.siguiente;
            System.out.println("Piv "+piv.archivo.getName());
            System.out.println("First: "+first.archivo.getName());
            
            for (; first.archivo != piv.archivo; first = first.siguiente) {
            	
                String f= (String) first.archivo.getName();
                String s= (String) sig.archivo.getName();
                String F=f.substring(0,1);
                String S=s.substring(0,1);
                
                if (indi(F,ABC) > indi(S,ABC)) {
                    first.Swap(first, sig);
                    sig = sig.siguiente;
                    change=true;
                } else {
                    sig = sig.siguiente;
                }
            }
            for (; piv != last; piv = piv.siguiente) {
                sig = piv.siguiente;
                String p= (String) piv.archivo.getName();
                String s2= (String) sig.archivo.getName();
                String P=p.substring(0,1);
                String S2=s2.substring(0,1);
                
                if (indi(P,ABC) > indi(S2,ABC)) {
                    piv.Swap(piv, sig);
                    sig = sig.siguiente;
                    change=true;
                } else {
                    sig = sig.siguiente;
                }

                System.out.println("piv.archivo: "+piv.archivo);

            }
            System.out.println("piv.archivo: "+piv.archivo);
            System.out.println("new cycle");
        }
    }	
    

    /**
     * Funcion para obtener el archivo de mayor peso en la lista
     * @author ignacio
     * @param list Lista a buscar
     * @param n Int con la longitud de la lista
     * @return Mayor peso encontrado en la lista
     */
    static int getMax(Lista list, int n) {
        int mx = (int) list.cabeza.Peso;
        for (int i = 1; i < n; i++)
            if ((int)list.getPeso(list.obt(i)) > mx)
                mx = (int) list.getPeso(list.obt(i));
        return mx;
    }

    /**
     * Funcion que realiza un ordenamiento de int respecto a sus digitos
     * @author ignacio
     * @param list Lista a ordenar
     * @param n Longitud de la lista
     * @param exp int que controla el ciclo 
     */
    static void countSort(Lista list, int n, int exp) {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < n; i++)
            count[((int)list.getPeso(list.obt(i)) / exp) % 10]++;

        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (i = n - 1; i >= 0; i--) {
            output[count[((int)list.getPeso(list.obt(i)) / exp) % 10] - 1] = (int) list.getPeso(list.obt(i));
            count[((int)list.getPeso(list.obt(i)) / exp) % 10]--;
        }

        for (i = 0; i < n; i++) {
        	int O=(int) list.getPeso(list.obt(i));
        	 output[i]=O;
        }
    }


    /**
     * Funcion para ordenar la lista respecto al peso de cada uno de sus nodos
     * @author ignacio
     * @param list Lista a ordenar
     */
    static void radixsort(Lista list) {
        int n = list.longitud;

        int m = getMax(list, n);

        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSort(list, n, exp);
        }
    }
    
    
	
}
