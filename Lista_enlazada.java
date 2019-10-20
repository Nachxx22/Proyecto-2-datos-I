package Ordenamiento;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;

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
        while(temp.verDato()!=search){
            temp=temp.verSiguente();
            i++;
        }
        return i;
    }
    public void completa(){
        Nodo temp = cabeza;
        System.out.println(cabeza.verDato());
        for (int indi = 0; indi < tamano - 1; indi++) {
            temp = temp.verSiguente();
            System.out.println(temp.verDato());
        }
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
                if ((int) abecedario.indi(first.verDato()) > (int) abecedario.indi(sig.verDato())) {
                    first.Swap(first, sig);
                    sig = sig.verSiguente();
                    change=true;
                } else {
                    sig = sig.verSiguente();
                }
                System.out.println(first.verDato());
            }
            System.out.println("despues de pivote");
            for (; piv != last; piv = piv.verSiguente()) {
                sig = piv.verSiguente();
                if ((int) abecedario.indi(piv.verDato()) > (int) abecedario.indi(sig.verDato())) {
                    piv.Swap(piv, sig);
                    sig = sig.verSiguente();
                    change=true;
                } else {
                    sig = sig.verSiguente();
                }

                System.out.println(piv.verDato());

            }
            System.out.println(piv.verDato());
            System.out.println("new cycle");
        }
    }


    public static void main(String[] args){
        Lista_enlazada l= new Lista_enlazada();
        l.AgregarDelante("C");l.AgregarDelante("A");l.AgregarDelante("E");l.AgregarDelante("A");l.AgregarDelante("B");
        l.Quicksort(l);
        l.completa();
    }
}
