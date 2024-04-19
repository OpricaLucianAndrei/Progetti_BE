public class Immagine extends ElementoMultimediale {
    private int luminosita;

    public Immagine(String titolo, int luminosita) {
        super(titolo);
        this.luminosita = luminosita;
    }

    public void show() {
        System.out.println("--------------------------------");
        System.out.println(titolo + ":");
        System.out.println("-Luminosità:" + "*".repeat(luminosita));
        System.out.println("--------------------------------");
    }

    void play() {
        show();
    }

}
