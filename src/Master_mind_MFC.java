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
			atzar = Math.floor(Math.random()*(8-0+1)+0);
			int numero = (int)atzar;
			arrayDecide[posicioArray] = numero;
		}
		
		String[] arrayGenerado = new String [4];
		
		//Aqui cojo los colores segun la posicion de los numeros que han tocado
		for(int iterador = 0; iterador < 4; iterador++){
            arrayGenerado[iterador] = colores[arrayDecide[iterador]];	// Almacenamos el color que este en la posicion del arrayDecide
			System.out.println(arrayGenerado[iterador]+", ");
		}
		return arrayGenerado;
	}
	
	
	public static void jugar(String[]arrayGenerado, Scanner teclat){

        // Colores elegidos por el jugador
		String jugada[] = new String [4];

        /* almacena un "O" si ha acertado o una "X" y una equis si está el color pero no en la posición correcta.
        Si ha fallado, almacena un espacio */
		String resultado[] = new String [4];

        // Almacena los resultados de todas las jugadas
		String resultadosPartida[] [] = new String [12][4];

        // Booleanos
        boolean fin = false;
		boolean ganas = false;

		System.out.println("Adivina el codigo entre estos colores rojo, azul, verde, rosa, amarillo, lila, naranja, negro");
		
		//Aqui empieza el for de jugadas
			for(int jugadas = 0; jugadas < 12 ;jugadas ++){

                //Llenamos el array con los colores seleccionados por el jugador

                for(int iterador = 0 ;iterador < 4 ;iterador++){
                    jugada[iterador] = teclat.next();
                }
					
                // Array que contiene cuantos colores
                int arrayrepitecolores [] = new int [4];

                //Aqui comprueba la cantidad de veces que esta cada color  por si se repite que no me salga una x y me salga un espacio
                //aqui esta el array de los colores que utilizare en el juego
			

                //Aqui comparo el arrayGenerado con el que ha introducido el jugador
                for(int iterador = 0; iterador < 4; iterador ++){
                    fin = false;
                    if(jugada[iterador].equalsIgnoreCase(arrayGenerado[iterador])){//si el color introducido es igual al del array generado
                        resultado[iterador] = "O"; //PONEMOS O ya que esta el color y la posicion correcta
                        fin = true;
                    }
                    else{
                        for(int cont = 0 ;cont < 4 ;cont ++){
                            if(jugada[iterador].equalsIgnoreCase(arrayGenerado[cont])){//si el color introducido esta en el array
                                resultado[iterador] = "X"; //PONEMOS x ya que esta el color pero no en la posicion correcta
                                fin = true;
                                break;
                            }
                        }
                        if(fin == false){
                            resultado[iterador] = " ";
                        }//fin IF
                    }//FIN ELSE
                } //FIN FOR QUE COMPRUEBA LOS COLORES
					
							
				//esto determina si has ganado
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
					
					
                //este array almacena las jugadas
                for(int intentos = 0; intentos < 4 ;intentos ++){
                    resultadosPartida[jugadas] [intentos] = resultado[intentos];
                }
						
                //este imprime el resultado en el monmento
                for(int it = 0 ; it < 4; it ++){
                    System.out.print(resultado[it]+"," );
                }//fin for
						
                System.out.println( );
                if(ganas == true ){
                    System.out.println("has ganado");
                    break;
                }//fin if
            }//fin for jugadas
						
        //imprimo el resultado
        for(int iterador = 0;iterador < 12 ;iterador ++){
            for(int iterador2 = 0;iterador2 < 4 ;iterador2 ++){
                System.out.print(resultadosPartida[iterador][iterador2] + ",");
            }
            System.out.println();
        }
	}
	
	
	public static void reglas(){
		
		System.out.println("MASTER MIND");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("=>Tenemos 8 colores rojo,azul,verde,rosa,amarillo,lila,naranja,negro");
		System.out.println("=>De los cuales 4 seran puestos en una cadena al azar");
		System.out.println("=>Tienes que descubrir que colores hay en la cadena y su posicion");
		System.out.println("=>Pueden haber colores repetidos y tienes 12 intentos");
		System.out.println("=>Te aparecera un O si has hacertado la posicion");
		System.out.println("=>Te aparecera una X si has acertado el color pero no la posicion");
		System.out.println("=>Te aparecera un espacio si no has acertado el color ni la posicion");
		System.out.println("=>Ganas si has hacertado la cadena de 4 colores");
		System.out.println("=>Pierdes si en 12 intentos no lo has conseguido");
		System.out.println("=>Los colores se dicen por escrito poniendo por ejemplo  blanco");
		System.out.println("=>se pulsa intro con cada color introducido");
		System.out.println("=>mucha suerte");
		
	}
}