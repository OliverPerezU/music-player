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

class List {
    Nodo start, end;

    public List() {
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
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
