public class Nodo {

    private Coordenada dato;
    private Nodo siguiente;

    public Nodo(Coordenada dato, Nodo siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Coordenada getDato() {
        return dato;
    }

    public void setDato(Coordenada dato) {
        this.dato = dato;
    }
}

