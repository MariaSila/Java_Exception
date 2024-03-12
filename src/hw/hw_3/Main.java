package hw.hw_3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static final  int countOfFields = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите через пробел данные:\n" +
                "ФИО (полностью)  дата рождения (дд.мм.гггг)  " +
                "номер телефона (целое безнаковое число)  пол (f/m)");
        String input = scanner.nextLine();
        scanner.close();

        String[] fields = input.split(" ");
        if (fields.length != countOfFields) {
            System.out.println("Ошибка: Не все поля заполнены.");
        }

        String lastName = fields[0];
        String firstName = fields[1];
        String middleName = fields[2];

        LocalDate birthDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            birthDate = LocalDate.parse(fields[3], formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты");
            return;
        }

        long phone;
        try {
            phone = Long.parseLong(fields[4]);
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат телефона");
            return;
        }

        String gender = fields[5];
        if ((!"m".equals(gender)) && (!"f".equals(gender))) {
            System.out.println("Неверно указан пол, необходимо ввести f или m");
            return;
        }

        String fileName = lastName + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(lastName + " " + firstName + " " + middleName + " " +
                    birthDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + phone + " " + gender);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Ошибка записи");
        }
    }
}
