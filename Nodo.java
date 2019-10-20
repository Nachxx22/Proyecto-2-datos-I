package Ordenamiento;
public class Nodo {
    private Nodo siguiente;
    private Object dato;
    Nodo(Object dato){
        this.dato =dato;
        this.siguiente= null;
    }
    Nodo verSiguente(){
        return siguiente;
    }
    void agregar(Nodo n){
        this.siguiente=n;
    }
    Object verDato(){
        return dato;
    }
    public void Enlace(Nodo g){
        siguiente=g;
    }
    void cambiarDato(Object k){
        this.dato=k;
    }

    void Swap(Nodo j, Nodo h) {
        Object temp;
        Object htemp;
        Object jtemp;
        temp=j.verDato();
        htemp=h.verDato();
        j.cambiarDato(htemp);
        h.cambiarDato(temp);
    }

    {

    }

}
