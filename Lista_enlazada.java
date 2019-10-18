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
    public void completa(){
        Nodo temp = cabeza;
        System.out.println(cabeza.verDato());
        for (int indi = 0; indi < tamano - 1; indi++) {
            temp = temp.verSiguente();
            System.out.println(temp.verDato());
        }
    }
    public void ordenar() {
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

    public static void main(String[] args){
        Lista_enlazada l= new Lista_enlazada();
        l.AgregarDelante(9);l.AgregarDelante(1);l.AgregarDelante(3);l.AgregarDelante(20);
        l.ordenar();
        l.completa();
    }
}
