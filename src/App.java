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

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
