package com.accenture.itfactory.task1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BullsAndCows {
    public static void main(String[] args) {
        System.out.println("Задание 1.1 - Вывод строки на консоль\n");
        System.out.println("IT Factory begins!");
        for(int i = 0; i < 3; i++){
            System.out.println();
        }
        BullsAndCows();
    }
    public static int[] guessed_number (int length){
        Random random_set = new Random();
        int[] array = new int[length];
        boolean signal_backup = true;
        for(int i = 0; i < length; i++){
            array[i] = random_set.nextInt(10);
            for(int j = 0; j < i; j++) {
                if (array[j] == array[i]) {
                    signal_backup = false;
                    break;
                }
            }
            if(!signal_backup){
                signal_backup = true;
                array[i] = 0;
                i--;
            }
        }
        return array;
    }
    public static String excep (String s1, int[] array){
        boolean a = true;
        boolean b = true;
       Scanner in = new Scanner(System.in);
        while(b){
            try{
                System.out.print("Введите число: ");
                s1 = in.next(); //сдаюсь
                int i = Integer.parseInt(s1);

            }
            catch(Exception e){
                if(s1.equals("сдаюсь")){
                    return s1;
                }
                System.out.println("\nВы ввели неккоректное число, пожалуйста, введите ещё раз");
                continue;
            }
            if(s1.length() != array.length){
                System.out.println("Вы ввели число неправильной длины, пожалуйста, введите ещё раз");
            }
            for (int i = 0; i < s1.length(); i++) {
                a = true;
                for (int j = 0; j < i; j++) {
                    if (Character.digit(s1.charAt(i), 10) == Character.digit(s1.charAt(j), 10) && i != j) {
                        a = false;
                    }
                }
                if (!a) {
                    System.out.println("Цифры вашего числа не различны, пожалуйста, введите ещё раз");
                    break;
                }
            }
            if(s1.length() == array.length && a){
                break;
            }
        }
        return s1;
    }
    public static void BullsAndCows(){
        System.out.println("Задание 1.2 - Консольная игра Быки и Коровы\n");
        System.out.println("ПРАВИЛА\nПравила игры следующие: Вам нужно разгадать, задуманное мной число.\n\n" +
                "Выбрав понравившеюся сложность (она варьируется от 1 до 5 - длина числа), вы пытаетесь отгадать его,\n" +
                "при этом вам будут даваться подсказки: быки и коровы. Количество быков(bulls) показывает количество цифр,\n" +
                "которые, находятся на правильных позициях;. Количество коров(cows) показывает количество цифр, которые\n" +
                "стоят на неправильных позициях.");
        System.out.println("\nВыберите сложность игры от 1 до 5, путём ввода соответствующей цифры");
        System.out.print("\nСложность: ");
        Scanner in = new Scanner(System.in);
        int amount = 0;
        int difficulty = 0;
        boolean b = true;
        while(b) {
            try {
                difficulty = in.nextInt();
            } catch (Exception e) {
                difficulty = 0;
                break;
            }
            if(difficulty >= -32767 && difficulty <= 32767){
                break;
            }
        }
        int bulls = 0, cows = 0;
        if(difficulty >=1 && difficulty <= 5){
            Random random_range = new Random();
            int[] random_number = new int[difficulty];
            switch(difficulty){
                case 1:
                    random_number = guessed_number(1);
                    break;
                case 2:
                    random_number = guessed_number(2);
                    break;
                case 3:
                    random_number = guessed_number(3);
                    break;
                case 4:
                    random_number = guessed_number(4);
                    break;
                case 5:
                    random_number = guessed_number(5);
                    break;
            }
            System.out.println("\nОтлично! Вы выбрали сложность: " + difficulty +
                    "\n\nЧисло загадано, попытайтесь отгадать его. Удачи!" +
                    "\n\nУчтите, что все цифры загаданного числа различны");
            int[] attempt = new int[random_number.length];
            System.out.println();
            String s1 = "";
            int intermediate = 0;
            s1 = excep(s1, random_number);
            while(!s1.equals("сдаюсь")){
                for(int i = 0; i < s1.length(); i++){
                    attempt[i] = Character.digit(s1.charAt(i),10);
                }
                for(int i = 0; i < random_number.length; i++){
                    if(attempt[i] == random_number[i]){
                        bulls++;
                    }
                    for(int j = 0; j < random_number.length; j++){
                        if(attempt[i] == random_number[j] && i != j){
                            cows++;
                        }
                    }
                }
                System.out.println("bulls: " + bulls + " cows: " + cows);
                bulls = 0;
                cows = 0;
                if(Arrays.equals(attempt, random_number)){
                    System.out.println("Поздравляю! Вы отгадали число. Сделанных попыток: " + amount);
                    break;
                }
                amount++;
                s1 = excep(s1, random_number);
           }
            System.out.println();
            if(s1.equals("сдаюсь")){
                String s2 = "";
                for(int i = 0; i < random_number.length; i++){
                    s2 += random_number[i];
                }
                System.out.println("К сожалению вы сдались, вот загаданное число " + s2 + ". Попробуйте сыграть ещё раз, и у вас обязательно получится! Сделанных попыток: ");
            }
        }
        else{
            System.out.println("Игра окончена, вы ввели некоректное значение сложности");
        }
    }
}
