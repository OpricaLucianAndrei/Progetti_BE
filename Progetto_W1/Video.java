import java.sql.SQLOutput;

class Video extends ElementoMultimediale implements Riproducibile {
    private int volume; // volume del video
    private int luminosita; // luminosità del video
    private int durata; // durata del video

    public Video(String titolo, int volume, int luminosita, int durata) {
        super(titolo);
        this.volume = volume;
        this.luminosita = luminosita;
        this.durata = durata;
    }

    // Rimuovi i metodi aumentaLuminosita() e diminuisciLuminosita() per evitare confusione

    @Override
    public void play() {
            System.out.println("--------------------------------");
        for (int i = 0; i < durata; i++) {
            String puntiEsclamativi = "!".repeat(volume);
            String asterischi = "*".repeat(luminosita);
            System.out.println(titolo + ":");
            System.out.println("-Volume: " + puntiEsclamativi);
            System.out.println("-Luminosità: " + asterischi);
        }
            System.out.println("--------------------------------");
    }

    @Override
    public void riproduci() {
        play();
    }
}