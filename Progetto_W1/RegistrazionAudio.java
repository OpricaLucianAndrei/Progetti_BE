public class RegistrazionAudio extends ElementoMultimediale implements Riproducibile {
    private int volume;
    private int durata;
    public RegistrazionAudio(String titolo, int volume, int durata) {
        super(titolo);
        this.volume = volume;
        this.durata = durata;
    }

    public void abbassaVolume() {
        volume--;
    }

    public void alzaVolume() {
        volume++;
    }

    @Override
    public void play() {
        System.out.println("--------------------------------");
        for (int i = 0; i < durata; i++) {
            String puntiEsclamativi = "!".repeat(volume);
            System.out.println(titolo + ":");
            System.out.println("-Volume: " + puntiEsclamativi);
        }
        System.out.println("--------------------------------");
    }

    @Override
    public void riproduci() {
        play();
    }
}
