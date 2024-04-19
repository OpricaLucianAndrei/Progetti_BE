import java.sql.SQLOutput;

class Video extends ElementoMultimediale implements Riproducibile {
    private int volume;
    private int luminosita;
    private int durata;

    public Video(String titolo, int volume, int luminosita, int durata) {
        super(titolo);
        this.volume = volume;
        this.luminosita = luminosita;
        this.durata = durata;
    }


    @Override
    public void play() {
            System.out.println("--------------------------------");
        for (int i = 0; i < durata; i++) {
            String puntiEsclamativi = "!".repeat(volume);
            String asterischi = "*".repeat(luminosita);
            String punti = ".".repeat(durata - i);
            System.out.println(titolo + ":");
            System.out.println("-Durata: " + punti);
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