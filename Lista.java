package buscador;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lista {
	private Nodo cabeza;
	private int longitud=0;
	private Nodo ultimo;
	private class Nodo{
		public File archivo;
		public Nodo siguiente=null;
		public Nodo(File archivo) {
			this.archivo=archivo;
		}
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
		void Swap(Nodo j, Nodo h) {
	        File temp;
	        File htemp;
	        File jtemp;
	        temp=j.archivo;
	        htemp=h.archivo;
	        j.cambiarDato(htemp);
	        h.cambiarDato(temp);
	    }
		void cambiarDato(File k){
	        this.archivo=k;
	    }
	}
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
	public int longitud() {
		return longitud;
	}
	public File obt(int i) {
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
	public void bubblesort() {
        int t = 1, c = 1;
        Nodo act=cabeza;/*definimos que el apuntador act esta en el primer nodo*/
        
        while (act.siguiente != null)//Este while cuenta el total de nodos.
        {
            act = act.siguiente;
            c++;
        }
        /* estas 2 variebles solo son variables que guardaran el valor temporalmente*/
        Object tem = 0;
        Nodo tem1;
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

	
	
	
	
	
	
}
