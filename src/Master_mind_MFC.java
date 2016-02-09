import java.util.Arrays;
import java.util.Scanner;

public class Master_mind_MFC {

    public static void main (String[] args)  {

        //Abrimos el teclado
        Scanner teclat = new Scanner(System.in);

        //Pedir opcion
        int opcion = 0;

        //Pedir operacion
        System.out.println("Opción 1 -> Jugar");
        System.out.println("Opción 2 -> Reglas");
        System.out.println("Opción 3 -> Mostrar menu");
        System.out.println("Exit");

        while (opcion < 4){

            System.out.println();

            System.out.println("Escoje una opcion ");
            opcion = teclat.nextInt();

            switch (opcion){

                case 1:
                    jugar(generarColores(), teclat);
                    break;

                case 2:
                    reglas();
                    break;

                case 3:
                    System.out.println("Opción 1 -> Jugar");
                    System.out.println("Opción 2 -> Reglas");
                    System.out.println("Opción 3 -> Mostrar menu");
                    System.out.println("Exit");
                    break;

            }//cierro el switch
        }//cierro el while

        System.out.println("Exit");
        teclat.close();
    }

    public static String [] generarColores(){
        //aqui esta el array de los colores que se usarán en el juego
        String colores[] = {"rojo", "azul", "verde", "rosa", "amarillo", "lila", "naranja", "negro"};

        double atzar;
        int arrayDecide []= new int [4];

        //genero array de numeros al azar
        for (int posicioArray = 0; posicioArray < 4; posicioArray ++){
            atzar = Math.floor(Math.random()*(7-0+1)+0);
            int numero = (int)atzar;
            arrayDecide[posicioArray] = numero;
        }

        String[] arrayColores = new String [4];

        //Aqui cojo los colores segun la posicion de los numeros que han tocado
        for(int iterador = 0; iterador < 4; iterador++){
            arrayColores[iterador] = colores[arrayDecide[iterador]];	// Almacenamos el color que este en la posicion del arrayDecide
            System.out.println(arrayColores[iterador] + ", ");        // Si quereis ver los colores que se han generado, quitad este comentario
        }

        return arrayColores;
    }

    public static void jugar(String[]arrayGenerado, Scanner teclat){

        // Almacena los resultados de todas las jugadas
        String resultadosPartida[][] = new String [12][4];

		/* almacena un "O" si ha acertado o una "X" y una equis si está el color pero no en la posición correcta.
        Si ha fallado, almacena un espacio */
        String resultado[] = new String [4];

        // Colores elegidos por el jugador
        String jugada[] = new String [4];

        // Booleanos
        boolean fin = false;        // En caso de estar a false, se escribira en la posicion que estemos iterando un espacio
        boolean ganas = false;      // Determina si el jugador ha ganado la partida
        int jugadas = 0;            // numero de jugadas

        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("Adivina el codigo entre estos colores rojo, azul, verde, rosa, amarillo, lila, naranja, negro");
        System.out.println("¡Buena suerte!");
        System.out.println("----------------------------------------------------------------------------------------------\n");

        //Aqui empieza el for de jugadas

        for(jugadas = 0; jugadas < 12 ;jugadas ++){

            System.out.println("\nJugada numero " + jugadas);
            System.out.println("----------------------------");

            //Llenamos el array con los colores seleccionados por el jugador
            for(int iterador = 0; iterador < 4; iterador++){
                System.out.println("Introduce el color numero " + (iterador + 1));
                jugada[iterador] = teclat.next();
            }

            // ¿Como podemos saber si nos hemos pasado de mas con la cantidad de colores?

            // Hacemos un array de 7 posiciones para el array de colores generados y el array de colores que introduzca el jugador
            // Cada posicion del array representara un color. La 0 sera rojo, la uno, azul, etc, etc, etc.
            // Cada vez que encontremos un color, sumaremos uno a la posicion que le corresponda a ese color en el array
            // Si lo externalizamos en un metodo y lo hacemos para arrayGenerado y jugada sera facil
            // saber cuantos colores y en que cantidad tiene cada array
            // Luego es tan sencillo como comprobar que el array de jugada no es superior al de colores enerados.
            // En caso de que algun color sobrepase al primero, pondremos un espacio en la posicion que los colores no sean iguales :)

            // Array que se ha generado
            int coloresGenerados[] = new int [7];               // colores generados en forma de enteros
            coloresGenerados = contarColores(arrayGenerado);    // Contamos cuantos colores de cada tiene nuesto array rellenandolo con nuestro metodo

            // Array de colores introducidos por el jugador
            int coloresJugada[] = new int [7];                  // colores generados en forma de enteros
            coloresJugada = contarColores(jugada);              // Contamos cuantos colores de cada tiene nuesto array rellenandolo con nuestro metodo

            //Aqui comparamos el arrayGenerado con el que ha introducido el jugador

            for(int iterador = 0; iterador < 4; iterador ++){

                fin = false;    // Lo dejamos a false hasta que nos interese escribir algo que no sea un espacio en blanco

                if(jugada[iterador].equalsIgnoreCase(arrayGenerado[iterador])){ //si el color introducido es igual al del array generado
                    resultado[iterador] = "O"; 	//Escribimos O ya que esta el color y la posicion correcta
                    fin = true;
                }
                else{
                    for(int iterador2 = 0; iterador2 < 4 ;iterador2 ++){

                        if(jugada[iterador].equalsIgnoreCase(arrayGenerado[iterador2])){//si el color introducido esta en el array
                            resultado[iterador] = "X"; //PONEMOS x ya que esta el color pero no en la posicion correcta
                            fin = true;

                            // Comparamos los arrays de colores en la posicion del color que estamos iterando ahora
                            if(coloresJugada[extraerNumeroColor(jugada[iterador])] > coloresGenerados[extraerNumeroColor(jugada[iterador])]){
                                fin  = false;   // Si es mas grande, lo ponemos como false
                            }

                            break;
                        }
                    }

                    if(fin == false){
                        resultado[iterador] = " ";
                    }
                }//FIN ELSE
            } //FIN FOR QUE COMPRUEBA LOS COLORES


            // Este fragmento determina si el jugador ha ganado
            int contador = 0;
            String compara = "O";

            for(int iter = 0 ; iter < 4; iter ++){
                if(resultado[iter].equals(compara)){
                    contador++;
                }
                if(contador == 4){
                    ganas = true;
                }
                else {
                    ganas = false;
                }
            }

            //Este array almacena las jugadas
            for(int intentos = 0; intentos < 4; intentos ++){
                resultadosPartida[jugadas][intentos] = resultado[intentos];
            }

            System.out.println("Resultado de la jugada:");

            //Este imprime el resultado en el momento
            for(int itResultado = 0 ; itResultado < 4; itResultado ++){
                System.out.print(resultado[itResultado] + ",");
            }

            System.out.println(  );
            if(ganas == true ){
                System.out.println("¡Has ganado!");
                break;
            }//fin if
        }//fin for jugadas

        if(ganas == false){
            System.out.println("Has perdido :(");
        }

        //Imprimo el resultado

        System.out.println("------------------------------------------");
        System.out.println("Historico de jugadas:");
        System.out.println("------------------------------------------");

        for(int intentos1 = 0; intentos1 < jugadas + 1; intentos1 ++){
            for(int intentos2 = 0; intentos2 < 4; intentos2 ++){
                System.out.print(resultadosPartida[intentos1][intentos2] + ", ");
            }
            System.out.println();
        }

    }

    public static int extraerNumeroColor(String color){

        /* En este método se pasa un nombre de color como parametro y devuelve su valor numerico
        (es decir, en que posicion de un array de colores debe ir */

        int numeroColor = -1;

        if(color.equalsIgnoreCase("rojo")){
            numeroColor = 0;
        }
        if(color.equalsIgnoreCase("azul")){
            numeroColor = 1;
        }
        if(color.equalsIgnoreCase("verde")){
            numeroColor = 2;
        }
        if(color.equalsIgnoreCase("rosa")){
            numeroColor = 3;
        }
        if(color.equalsIgnoreCase("amarillo")){
            numeroColor = 4;
        }
        if(color.equalsIgnoreCase("lila")){
            numeroColor = 5;
        }
        if(color.equalsIgnoreCase("naranja")){
            numeroColor = 6;
        }
        if(color.equalsIgnoreCase("negro")){
            numeroColor = 7;
        }

        return numeroColor;
    }

    public static int[] contarColores(String jugada[]){

        /* Le pasas un array con colores y te genera otro array con la cantidad de ellos */

        int coloresJugada[] = {0,0,0,0,0,0,0,0};

        for(int iterador = 0; iterador < jugada.length; iterador++){
            if(jugada[iterador].equalsIgnoreCase("rojo")){
                coloresJugada[0] = coloresJugada[0] + 1;
            }
            else if(jugada[iterador].equalsIgnoreCase("azul")){
                coloresJugada[1] = coloresJugada[1] + 1;
            }
            else if(jugada[iterador].equalsIgnoreCase("verde")){
                coloresJugada[2] = coloresJugada[2] + 1;
            }
            else if(jugada[iterador].equalsIgnoreCase("rosa")){
                coloresJugada[3] = coloresJugada[3] + 1;
            }
            else if(jugada[iterador].equalsIgnoreCase("amarillo")){
                coloresJugada[4] = coloresJugada[4] + 1;
            }
            else if(jugada[iterador].equalsIgnoreCase("lila")){
                coloresJugada[5] = coloresJugada[5] + 1;
            }
            else if(jugada[iterador].equalsIgnoreCase("naranja")){
                coloresJugada[6] = coloresJugada[6] + 1;
            }
            else if(jugada[iterador].equalsIgnoreCase("negro")){
                coloresJugada[7] = coloresJugada[7] + 1;
            }
        }

        return coloresJugada;
    }

    public static void reglas(){

        /* En este metodo mostramos las reglas*/

        System.out.println("MASTER MIND");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("=> Tenemos 8 colores rojo,azul,verde,rosa,amarillo,lila,naranja,negro");
        System.out.println("=> De los cuales 4 seran puestos en una cadena al azar");
        System.out.println("=> Tienes que descubrir que colores hay en la cadena y su posicion");
        System.out.println("=> Pueden haber colores repetidos y tienes 12 intentos");
        System.out.println("=> Te aparecera un O si has hacertado la posicion");
        System.out.println("=> Te aparecera una X si has acertado el color pero no la posicion");
        System.out.println("=> Te aparecera un espacio si no has acertado el color ni la posicion");
        System.out.println("=> Ganas si has hacertado la cadena de 4 colores");
        System.out.println("=> Pierdes si en 12 intentos no lo has conseguido");
        System.out.println("=> Los colores se dicen por escrito poniendo por ejemplo  blanco");
        System.out.println("=> Se pulsa intro con cada color introducido");
        System.out.println("=> Mucha suerte");

    }
}