import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean error = true;
        int windowNumber = 0;
        final int INITIAL_POSITION = 0;

        while (error) {
            System.out.print("Introduce el número de ventanas del Bloc de Notas que deseas abrir: ");
            windowNumber = sc.nextInt();
            sc.nextLine();

            if (windowNumber >= 0) {
                error = false;
            }
        }

        ArrayList<Process> processArrayList = new ArrayList<>(windowNumber);

        ProcessBuilder process = new ProcessBuilder();
        process.command("cmd.exe", "/c", "notepad");
        try {
            int n = windowNumber;

            while (n != 0) {
                processArrayList.add(process.start());
                n--;
            }

            while (processArrayList.size() != 0) {
                if (!processArrayList.get(INITIAL_POSITION).isAlive()) {
                    processArrayList.remove(INITIAL_POSITION);
                }
            }

            if (windowNumber == 0) {
                System.out.println("No has abierto ninguna ventana del Bloc de Notas");
            } else {
                System.out.println("Programa finalizado, se han abierto " + windowNumber + " ventanas del Bloc de Notas");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}