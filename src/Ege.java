import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Ege {

    public static void main(String[] args) {
        int maxSymbols = (int) Math.pow(10, 6);
        Random random = new Random();
        char[] symbols = {'X', 'Y', 'Z'};
        String fileName = "файл.txt";
        createTextFile(maxSymbols, random, symbols, fileName);
        readFile(maxSymbols, symbols, fileName);
    }

    public static void createTextFile(int maxSymbols, Random random, char[] symbols, String fileName) {
        try (FileWriter file = new FileWriter("/Users/oleg/Documents/программирование/системное программирование/9 лаба (ЕГЭ)/" + fileName)) {
            file.write(symbols);
            for (int i = 0; i < maxSymbols; i++) {
                char randomSymbol = symbols[random.nextInt(symbols.length)];
                file.write(randomSymbol);
            }
            System.out.println("Файл '" + fileName + "' успешно создан!");
            System.out.println("Размер файла: " + maxSymbols + " символов");
        } catch (IOException e) {
            System.err.println("Ошибка при создании файла: " + e.getMessage());
        }
    }

    public static void readFile(int maxSymbols, char[] symbols, String fileName) {
        try (FileReader file = new FileReader("/Users/oleg/Documents/программирование/системное программирование/9 лаба (ЕГЭ)/" + fileName)) {
            int c1; // предыдущий символ
            int c2; // текущий символ
            int k = 1; // длина каждой последовательности неповторяющихся символов
            int max = 0; // максимальная длина последовательности неповторяющихся символов
            for (int i = 0; i < maxSymbols; i++) {
                c1 = file.read(symbols);
                c2 = file.read();
                if (c2 != -1) {
                    c1 = c2;
                    max = 1;
                }
                while ((c2 = file.read()) != -1) {
                    if (c2 != c1) {
                        k++;
                    } else {
                        if (k > max) {
                            max = k;
                        }
                        k = 1;
                    }
                }
            }
            System.out.println("Максимальное количество идущих подряд символов, среди которых каждые два соседних различны: " + max);
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден!");
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}