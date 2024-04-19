import java.util.Scanner;

public class LettoreMultimediale {
    public static void main(String[] args) {
        ElementoMultimediale[] elementi = new ElementoMultimediale[5];
        elementi[0] = new Immagine("Immagine1", 3);
        elementi[1] = new Video("Video1", 2, 4, 10);
        elementi[2] = new RegistrazionAudio("Audio1", 3, 5);
        elementi[3] = new Video("Video2", 3, 5, 7);
        elementi[4] = new RegistrazionAudio("Audio2", 4, 15);

        Scanner scanner = new Scanner(System.in);
        int scelta;
        do {
            System.out.println("Inserire l'indice (da 1 a 5) dell'elemento da eseguire o 0 per uscire:");
            scelta = scanner.nextInt();
            if (scelta >= 1 && scelta <= 5) {
                elementi[scelta - 1].play();
            } else if (scelta != 0) {
                System.out.println("Inserire un indice valido");
            }
        } while (scelta!= 0);
        scanner.close();

    }
}
