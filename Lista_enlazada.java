package Ordenamiento;
import java.util.Arrays;

public class Lista_enlazada {
     public Nodo cabeza;
     public int tamano;
    Lista_enlazada(){
        cabeza=null;
        tamano=0;
    }
    void AgregarDelante(Object o){
        if(cabeza==null){
            cabeza=new Nodo(o);
        }else{
            Nodo temp=cabeza;
            Nodo nuevo=new Nodo(o);
            nuevo.agregar(temp);
            cabeza=nuevo;
        }
        tamano++;
    }
    Object ver(int indice){
        Nodo temp=cabeza;
        for(int i=0;i<indice;i++){
            temp=temp.verSiguente();
        }
        return temp.verDato();
    }
    Object indinodo(int indice){
        Nodo temp=cabeza;
        for(int i=0;i<indice;i++){
            temp=temp.verSiguente();
        }
        return temp;
    }
    Object indi(Object search){
        Nodo temp=cabeza;
        int i=0;
            while(temp.verDato().equals(search)!=true) {
                temp = temp.verSiguente();
                i++;
            }
        return i;
    }
    public void completa() {
        Nodo temp = cabeza;
        System.out.println(cabeza.verDato());
        for (int indi = 0; indi < tamano - 1; indi++) {
            temp = temp.verSiguente();
            System.out.println(temp.verDato());
        }
    }
        public void setTamaño(int tamano) {
            this.tamano = tamano;
        }
    public Nodo getDato(int indice) {
        Nodo temp = cabeza;
        for (int i = 0; i < indice; i++) {
            temp = temp.verSiguente();
        }
        return  temp;
    }
    public void bubblesort() {
        int t = 1, c = 1;
        Nodo act = cabeza;/*definimos que el apuntador act esta en el primer nodo*/
        while (act.verSiguente() != null)//Este while cuenta el total de nodos.
        {
            act = act.verSiguente();
            c++;
        }
        /* estas 2 variebles solo son variables que guardaran el valor temporalmente*/
        Object tem = 0;
        Nodo tem1;
        //aqui se hace el ordenamiento
        do {
            act = cabeza;//aux esta en el primer nodo
            Nodo sig = act.verSiguente();//esta en el siguiente nodo
            while (act.verSiguente() != null) {
                if ((int) act.verDato() > (int) sig.verDato()) {
                   act.Swap(act,sig);
                   act=act.verSiguente();
                   sig=sig.verSiguente();
                } else {
                    //[1] [3] [2]
                    //    act sig
                    act = act.verSiguente();
                    sig = sig.verSiguente();
                }
            }
            t++;
        } while (t <= c);//act.ap_sig != null);
    }
    public void Quicksort(Lista_enlazada lista){
        Nodo last=null;Nodo first=cabeza;Nodo act=cabeza;Nodo sig=cabeza.verSiguente();
        int pivot=tamano/2;
        Nodo piv= (Nodo) lista.indinodo(pivot);
        Lista_enlazada abecedario= new Lista_enlazada();
        abecedario.AgregarDelante("Z");abecedario.AgregarDelante("Y");abecedario.AgregarDelante("X");abecedario.AgregarDelante("W");abecedario.AgregarDelante("V");abecedario.AgregarDelante("U");abecedario.AgregarDelante("T");abecedario.AgregarDelante("S");abecedario.AgregarDelante("R");abecedario.AgregarDelante("Q");abecedario.AgregarDelante("P");abecedario.AgregarDelante("O");abecedario.AgregarDelante("Ñ");abecedario.AgregarDelante("N");abecedario.AgregarDelante("M");abecedario.AgregarDelante("L");abecedario.AgregarDelante("K");abecedario.AgregarDelante("J");abecedario.AgregarDelante("I");abecedario.AgregarDelante("H");abecedario.AgregarDelante("G");abecedario.AgregarDelante("F");abecedario.AgregarDelante("E");abecedario.AgregarDelante("D");abecedario.AgregarDelante("C");abecedario.AgregarDelante("B");abecedario.AgregarDelante("A");
        while(act.verSiguente()!=null){ //Para tener el ultimo nodo en la variable last
            last=act.verSiguente();
            act=act.verSiguente();
        }
        System.out.println();
        boolean change=true;
        while(change!=false){// while para verificar si hubos cambios, cuando no los haya no entrara a while por que la lista ya esta ordenada
            change=false;
            first=cabeza;
            piv= (Nodo) lista.indinodo(pivot);// Reiniciar parametros para que se ordene desde el inicio cada vez que entra
            sig=first.verSiguente();
            for (; first.verDato() != piv.verSiguente().verDato(); first = first.verSiguente()) {
                String f= first.verDato().toString();
                String s= sig.verDato().toString();
                Object F=f.substring(0,1);
                Object S=s.substring(0,1);
                if ((int)abecedario.indi(F) > (int)abecedario.indi(S)) {
                    first.Swap(first, sig);
                    sig = sig.verSiguente();
                    change=true;
                } else {
                    sig = sig.verSiguente();
                }
            }
            for (; piv != last; piv = piv.verSiguente()) {
                sig = piv.verSiguente();
                String p= (String) piv.verDato();
                String s2= (String) sig.verDato();
                Object P=p.substring(0,1);
                Object S2=s2.substring(0,1);
                if ((int) abecedario.indi(P) > (int) abecedario.indi(S2)) {
                    piv.Swap(piv, sig);
                    sig = sig.verSiguente();
                    change=true;
                } else {
                    sig = sig.verSiguente();
                }
            }
        }
    }
    static int getMax(Lista_enlazada list, int n) {
        int mx = (int) list.cabeza.dato;
        for (int i = 1; i < n; i++)
            if ((int)list.getDato(i).dato > mx)
                mx = (int) list.getDato(i).dato;
        return mx;
    }

    static void countSort(Lista_enlazada list, int n, int exp) {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < n; i++)
            count[((int)list.getDato(i).dato / exp) % 10]++;

        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (i = n - 1; i >= 0; i--) {
            output[count[((int)list.getDato(i).verDato() / exp) % 10] - 1] = (int) list.getDato(i).dato;
            count[((int)list.getDato(i).verDato() / exp) % 10]--;
        }

        for (i = 0; i < n; i++)
            list.getDato(i).dato = output[i];
    }


    static void radixsort(Lista_enlazada list) {
        int n = list.tamano;

        int m = getMax(list, n);

        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(list, n, exp);
    }

    public static void main(String[] args){
        Lista_enlazada l= new Lista_enlazada();
        l.AgregarDelante(100);l.AgregarDelante(4);l.AgregarDelante(1);l.AgregarDelante(34);l.AgregarDelante(200);
       //.Quicksort(l);
        l.radixsort(l);
       l.completa();
    }
}
