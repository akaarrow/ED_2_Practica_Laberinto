public class Laberinto {

    private final int TAMAÑO;
    private char[][] tablero;
    private Coordenada entrada, salida;
    private Pila pilaCaminos;
    private boolean[][] visitados;


    public Laberinto(int tamaño, char[][] tablero, Coordenada entrada, Coordenada salida) {
        this.TAMAÑO = tamaño;
        this.tablero = tablero;
        this.entrada = entrada;
        this.salida = salida;
        this.pilaCaminos = new Pila();
        this.visitados = new boolean[TAMAÑO][TAMAÑO];
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO; j++) {
                this.visitados[i][j] = false;
            }
        }
    }

    public void mostrar() {
        for (int i = 0; i <= TAMAÑO + 1; i++) {
            System.out.print("X");
        }
        System.out.println();
        for (int i = 0; i < TAMAÑO; i++) {
            if (i != entrada.getFila()) {
                System.out.print("X");
            } else {
                System.out.print(" ");
            }
            for (int j = 0; j < TAMAÑO; j++) {
                System.out.print(tablero[i][j]);
            }
            if (i != salida.getFila()) {
                System.out.println("X");
            } else {
                System.out.println(" ");
            }
        }
        for (int i = 0; i <= TAMAÑO + 1; i++) {
            System.out.print("X");
        }
        System.out.println();
        System.out.println();
    }

    /**
     *
     * @param coordenada - coordenada a ser evaluada
     * @return bool - si el camino cumple tmbn las tres funciones siguientes
     */
    private boolean esCaminoValidoNuevo(Coordenada coordenada) {
        return this.esValido(coordenada) && this.esCamino(coordenada) && this.esNuevo(coordenada);
    }

    private boolean esValido(Coordenada coordenada) {
        return coordenada.getFila()>=0 && coordenada.getFila()<TAMAÑO && coordenada.getColumna()>=0 && coordenada.getColumna()<TAMAÑO;
    }

    private boolean esCamino(Coordenada coordenada) {
        return tablero[coordenada.getFila()][coordenada.getColumna()]!='X';
    }

    private boolean esNuevo(Coordenada coordenada) {
        return !visitados[coordenada.getFila()][coordenada.getColumna()];
    }

    /**
     * @desc averigua si un laberinto(clase) tiene camino de la entrada a la salida
     * @return bool - success or failure
     */
    public boolean existeCamino() {
        /* la funcion devuelve directamente true o false
            aunque tambien se podria hacer devolviendo una variable bool por ejemplo existe y darle el valor true cuando
            aux sea igual a salida, solo que habria que modificar tambien la condicion del while  */
        Coordenada aux;
        pilaCaminos.apilar(entrada);    //apilamos la entrada para no tener la pila vacia y emepezar por ahi
        visitados[entrada.getFila()][entrada.getColumna()] = true;             //y la marcamos como visitada
        while(!pilaCaminos.vacia()) {
            aux = pilaCaminos.desapilar();
            aux.mostrar();
            if(aux.iguales(salida)){
                return true;    //devolvemos true directamente cuando nuestra coord actual es la salida
            }
            /* si una coordenada adayacente es valida la apilamos y
               la marcamos como visitada */
            if(esCaminoValidoNuevo(aux.arriba())){
                pilaCaminos.apilar(aux.arriba());
                visitados[aux.arriba().getFila()][aux.arriba().getColumna()] = true;
            }
            if(esCaminoValidoNuevo(aux.derecha())){
                pilaCaminos.apilar(aux.derecha());
                visitados[aux.derecha().getFila()][aux.derecha().getColumna()] = true;
            }
            if(esCaminoValidoNuevo(aux.abajo())){
                pilaCaminos.apilar(aux.abajo());
                visitados[aux.abajo().getFila()][aux.abajo().getColumna()] = true;
            }
            if(esCaminoValidoNuevo(aux.izquierda())){
                pilaCaminos.apilar(aux.izquierda());
                visitados[aux.izquierda().getFila()][aux.izquierda().getColumna()] = true;
            }
        }
        return false;  //si se han recorrido todas las rutas posibles (ha terminado el while) no exite y devolvemos false
    }
}
