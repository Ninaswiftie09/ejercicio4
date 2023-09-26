//Nombre: Ingrid Nina Alessandra Nájera Marakovits, 231088
//Ejercicio 4

//importamos librerias
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//creamos nuestra clase jugador
class Jugador { //creamos nuestras variables
    private String nombre;
    private String pais;
    private String posicion;
    private int faltas;
    private int golesDirectos;
    private int lanzamientos;
    private int paradas;
    private int golesRecibidos;
    private int pases;
    private int asistencias;

    public Jugador(String nombre, String pais, String posicion, int faltas, int golesDirectos, int lanzamientos, int paradas, int golesRecibidos, int pases, int asistencias) {
        this.nombre = nombre;
        this.pais = pais;
        this.posicion = posicion;
        this.faltas = faltas;
        this.golesDirectos = golesDirectos;
        this.lanzamientos = lanzamientos;
        this.paradas = paradas;
        this.golesRecibidos = golesRecibidos;
        this.pases = pases;
        this.asistencias = asistencias;
    }
//hacemos los get
    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public String getPosicion() {
        return posicion;
    }

    public int getFaltas() {
        return faltas;
    }

    public int getGolesDirectos() {
        return golesDirectos;
    }

    public int getLanzamientos() {
        return lanzamientos;
    }

    public int getParadas() {
        return paradas;
    }

    public int getGolesRecibidos() {
        return golesRecibidos;
    }

    public int getPases() {
        return pases;
    }

    public int getAsistencias() {
        return asistencias;
    }
}

//creamos nuestra clase portero con herencia de jugador
class Portero extends Jugador{
    public Portero(String nombre, String pais, int faltas, int golesDirectos, int totalLanzamientos, int paradas, int golesRecibidos) {
        super(nombre, pais, "Portero", faltas, golesDirectos, totalLanzamientos, paradas, golesRecibidos, 0, 0);
    }
    //formula de efectividad
    public double efectividadportero() {
        return ((getParadas() - getGolesRecibidos()) * 100.0 / (getParadas() + getGolesRecibidos())) + (getGolesDirectos() * 100.0 / getLanzamientos());
    }
}


//creamos nuestra clase extremo con herencia de jugador
class Extremo extends Jugador {
    private int pases;
    private int asistencias;

    public Extremo(String nombre, String pais, int faltas, int golesDirectos, int totalLanzamientos, int pases, int asistencias) {
        super(nombre, pais, "Extremo", faltas, golesDirectos, totalLanzamientos, 0, 0, pases, asistencias);
        this.pases = pases;
        this.asistencias = asistencias;
    }
    //formula de efectividad
    public double efectividadextremo() {
        return ((pases + asistencias - getFaltas()) * 100.0 / (pases + asistencias + getFaltas())) + (getGolesDirectos() * 100.0 / getLanzamientos());
    }
}

// class Campeonato {}

//creamos clase para guardar la informacion en el csv
class Guardar {
    String archivo = "jugadores.csv";

    public void guardarJugador(Jugador jugador) {
        try {
            FileWriter insertar = new FileWriter(archivo, true); //para que la informacion se guarde
            insertar.append(jugador.getNombre());
            insertar.append(",");
            insertar.append(jugador.getPais());
            insertar.append(",");
            insertar.append(jugador.getPosicion());
            insertar.append(",");
            insertar.append(String.valueOf(jugador.getFaltas()));
            insertar.append(",");
            insertar.append(String.valueOf(jugador.getGolesDirectos()));
            insertar.append(",");
            insertar.append(String.valueOf(jugador.getLanzamientos()));

            if (jugador.getPosicion().equalsIgnoreCase("Portero")) {
                insertar.append(",");
                insertar.append(String.valueOf(jugador.getParadas()));
                insertar.append(",");
                insertar.append(String.valueOf(jugador.getGolesRecibidos()));
            } else if (jugador.getPosicion().equalsIgnoreCase("Extremo")) {
                insertar.append(",");
                insertar.append(String.valueOf(jugador.getPases()));
                insertar.append(",");
                insertar.append(String.valueOf(jugador.getAsistencias()));
            } else {
                insertar.append(",,"); // Si no es Portero ni Extremo, deja los campos vacíos
            }

            insertar.append("\n");
            insertar.flush();
            insertar.close();
            System.out.println("Datos del jugador guardados en el archivo CSV exitosamente.");
        } catch (IOException e) {
            System.err.println("Error, no se guardaron los datos: " + e.getMessage());
        }
    }
}

//creamos el drive
public class ejercicio4 {
    public static void main(String[] args) { //pedimos info al usuario
        Scanner leer = new Scanner(System.in);
        System.out.print("Para ingresar un nuevo jugador, proporciona la siguiente información: \n");
        System.out.print("Nombre: ");
        String nombre = leer.nextLine();
        System.out.print("País: ");
        String pais = leer.nextLine();
        System.out.print("Posición (Portero/Extremo/Jugador): ");
        String posicion = leer.nextLine();
        System.out.print("Faltas: ");
        int faltas = Integer.parseInt(leer.nextLine());
        System.out.print("Goles directos: ");
        int golesDirectos = Integer.parseInt(leer.nextLine());
        System.out.print("Lanzamientos: ");
        int lanzamientos = Integer.parseInt(leer.nextLine());

        int paradas = 0;
        int golesRecibidos = 0;
        int pases = 0;
        int asistencias = 0;

        if (posicion.equalsIgnoreCase("Portero")) {
            System.out.print("Paradas Efectivas (portero): ");
            paradas = Integer.parseInt(leer.nextLine());
            System.out.print("Goles recibidos (portero): ");
            golesRecibidos = Integer.parseInt(leer.nextLine());
            //System.out.print("Efectividad: " + Portero.efectividadportero);
        } else if (posicion.equalsIgnoreCase("Extremo")) {
            System.out.print("Pases (extremo): ");
            pases = Integer.parseInt(leer.nextLine());
            System.out.print("Asistencias efectivas: ");
            asistencias = Integer.parseInt(leer.nextLine());
            //System.out.print("Efectividad: " + Portero.efectividadextremo);
        }

        leer.close();
        //guardamos
        Jugador jugador = new Jugador(nombre, pais, posicion, faltas, golesDirectos, lanzamientos, paradas, golesRecibidos, pases, asistencias);
        Guardar guardar = new Guardar();
        guardar.guardarJugador(jugador);
    }
}
