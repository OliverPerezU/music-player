import java.util.Scanner;

class Song {
    String title;
    String artist;
    int time;

    public Song(String title, String artist, int time) {
        this.title = title;
        this.artist = artist;
        this.time = time;
    }

    public void details() {
        System.out.println("Título:" + title);
        System.out.println("Artista:" + artist);
        System.out.println("Duración:" + time + " segundos");
    }
}

class Nodo {
    Song song;
    Nodo next;

    public Nodo(Song song) {
        this.song = song;
    }

    public Nodo(Song song, Nodo next) {
        this.song = song;
        this.next = next;
    }
}

class PlayList {
    Nodo start, end;

    public PlayList() {
        start = null;
        end = null;
    }

    public void add(Song song) {
        Nodo newNode = new Nodo(song);
        if (start == null) {
            start = newNode;
            end = newNode;
        } else {
            end.next = newNode;
            end = newNode;
        }
    }

    public void print() {
        Nodo current = start;
        while (current != null) {
            current.song.details();
            System.out.println("canción " + current.song.title + "\n");
            current = current.next;
        }
    }

}

public class App {
    public static void cleanConsole() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void menu() {
        System.out.println("Bienvenido al reproductor de música 🎶");
        System.out.println("1. Crear playlist.");
        System.out.println("2. Añadir canciones a la playlist.");
        System.out.println("3. Mostrar canciones de la playlist");
        System.out.println("4. Buscar canción");
        System.out.println("5. Eliminar canción");
        System.out.println("6. Salir");
        System.out.println("Selecciones la opción que desea realizar");
    }

    public static int numEntry(Scanner scanner) {
        try {
            int number = Integer.parseInt(scanner.nextLine().trim());
            if (number <= 0) {
                System.out.println("Por favor ingresa un número mayor que 0");
                return numEntry(scanner);
            }
            return number;
        } catch (NumberFormatException e) {
            System.out.println("Por favor ingresa un número válido");
            return numEntry(scanner);
        }
    }

    public static String stringEntry(Scanner scanner) {
        try {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Por favor ingresa un texto válido");
                return stringEntry(scanner);
            }
            return input;

        } catch (Exception e) {
            System.out.println("Ocurrió un error al leer la entrada. Por favor, inténtalo de nuevo.");
            return stringEntry(scanner);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
