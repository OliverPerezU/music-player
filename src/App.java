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
        System.out.println("Título: " + title);
        System.out.println("Artista: " + artist);
        System.out.println("Duración: " + time + " segundos");
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

    public static void emptyList() {
        System.out.println("---- ---- ---- ---- ---- ----");
        System.out.println("🎶 La playlist está vacía. 🎶");
        System.out.println("---- ---- ---- ---- ---- ----");
        return;
    }

    public static int getOption(Scanner scanner) {
        try {
            int option = numEntry(scanner);
            if (option < 1 || option > 6) {
                System.out.println("Por favor ingresa una opción válida entre 1 y 6.");
                return getOption(scanner);
            }
            return option;
        } catch (Exception e) {
            System.out.println("Oucrrió un error al obtener la opción. Por favor, inténtalo de nuevo.");
            return getOption(scanner);
        }
    }

    public static void printSongs(PlayList list) {
        if (list.start == null) {
            emptyList();
        } else {
            list.print();
        }
    }

    public static void searchSong(Scanner scanner, PlayList playList) {
        try {
            if (playList.start == null) {
                emptyList();
                return;
            }
            System.out.println("Introduce el título de la canción que deseas buscar:");
            String title = stringEntry(scanner);
            Nodo current = playList.start;
            boolean found = false;
            while (current != null) {
                if (current.song.title.equalsIgnoreCase(title)) {
                    System.out.println();
                    System.out.println("Canción encontrada:");
                    System.out.println();
                    current.song.details();
                    found = true;
                    break;
                }
                current = current.next;
            }
            if (!found) {
                System.out.println("❌ Canción no encontrada en la playlist. \n");
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("❌ Ocurrió un error al buscar la canción. Por favor, inténtalo de nuevo.");
            searchSong(scanner, playList);
        }
    }

    public static void addSong(Scanner scanner, PlayList playList) {
        try {
            System.out.println("Introduce el título de la canción:");
            String title = stringEntry(scanner);
            System.out.println("Introduce el artista de la canción:");
            String artist = stringEntry(scanner);
            System.out.println("Introduce la duración de la canción en segundos:");
            int time = numEntry(scanner);
            Song song = new Song(title, artist, time);
            playList.add(song);
            System.out.println("✅ Canción añadida correctamente a la playlist. \n");
        } catch (Exception e) {
            System.out.println("❌ Ocurrió un error al añadir la canción. Por favor, inténtalo de nuevo.");
            addSong(scanner, playList);
        }
    }

    public static void deleteSong(Scanner scanner, PlayList playList) {
        try {
            if (playList.start == null) {
                emptyList();
                return;
            }
            System.out.println("Introduce el título de la canción que deseas eliminar:");
            String title = stringEntry(scanner);
            Nodo current = playList.start;
            Nodo previous = null;
            boolean found = false;

            while (current != null) {
                if (current.song.title.equalsIgnoreCase(title)) {
                    found = true;
                    if (previous == null) {
                        playList.start = current.next;
                    } else {
                        previous.next = current.next;
                    }
                    if (current.next == null) {
                        playList.end = previous;
                    }
                    System.out.println("✅ Canción eliminada correctamente. \n");
                    break;
                }
                previous = current;
                current = current.next;
            }

            if (!found) {
                System.out.println("❌ Canción no encontrada en la playlist. \n");
            }
        } catch (Exception e) {
            System.out.println("❌ Ocurrió un error al eliminar la canción. Por favor, inténtalo de nuevo.");
            deleteSong(scanner, playList);
        }
    }

    public static void app(Scanner scanner, PlayList playlist) {
        try {
            int option = -1;
            do {
                menu();
                option = getOption(scanner);
                cleanConsole();
                switch (option) {
                    case 1:
                        playlist = new PlayList();
                        System.out.println("✅ Se ha generado una nueva playlist.");
                        break;

                    case 2:
                        addSong(scanner, playlist);
                        break;

                    case 3:
                        System.out.println("Canciones en la playlist:");
                        printSongs(playlist);
                        break;

                    case 4:
                        searchSong(scanner, playlist);
                        break;

                    case 5:
                        deleteSong(scanner, playlist);
                        break;
                    default:
                        break;
                }
            } while (option != 6);
            scanner.close();
        } catch (Exception e) {
            System.out.println("❌ Ocurrió un error mientras se ejecutaba la aplicación.");
        }
    }

    public static void main(String[] args) throws Exception {
        cleanConsole();
        Scanner scanner = new Scanner(System.in);
        PlayList list = new PlayList();
        app(scanner, list);
    }
}
