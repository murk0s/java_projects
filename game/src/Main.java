import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static BufferedReader br;
    private static int state = 0; // для навигации по меню

    private static Scanner scanner;
    private static Player player = null;
    private static Battle battle = null;
    public static void main(String[] args) {
        //Инициализируем BufferedReader
        br = new BufferedReader(new InputStreamReader(System.in));
        scanner = new Scanner(System.in);
        battle = new Battle();

        System.out.println("Введите свое имя:");
        readCommand();
    }
    private static void command(String command){
        if (player == null) {
            player = new Player(command);
            printNavigation();
            return;
        }
        switch (command){
                case "1":
                    if (state == 0){
                        state = 1;
                        printNavigation();
                    }
                    else if (state == 1) {
                        player.buyHealth();
                        printNavigation();
                    }
                    else {
                        System.out.println("Такой команды нет");
                        mainMenu();
                    }
                    break;
                case "2":
                    if (state == 0) {
                        state = 2;
                        beginFight();
                    }
                    else if (state == 1) {
                        player.buyAgility();
                        printNavigation();
                    }
                    else {
                        System.out.println("Такой команды нет");
                        mainMenu();
                    }
                    break;
                case "да":
                    if (state == 2) beginFight();
                    else {
                        System.out.println("Такой команды нет");
                        mainMenu();
                    }
                    break;
                case "3":
                    if (state == 0) return;
                    else if (state == 1) mainMenu();
                    else {
                        System.out.println("Такой команды нет");
                        mainMenu();
                    }
                    break;
                case "нет":
                    mainMenu();
                    break;
                default:
                    System.out.println("Такой команды нет");
                    mainMenu();
         }
    }

    private static void mainMenu(){
        state = 0;
        printNavigation();
    }

    private static void printNavigation() {
        switch (state) {
            case 0:
                System.out.println("Куда вы хотите пойти?");
                System.out.println("1. К Торговцу");
                System.out.println("2. В темный лес");
                System.out.println("3. Выход");
                break;
            case 1:
                System.out.println("Добро пожаловать в наш супермаркет, что желаете приобрести?");
                System.out.println("1. +10 к здоровью за 10 золотых");
                System.out.println("2. +5 к ловкости за 10 золотых");
                System.out.println("3. Главное меню");
                break;
            case 2:
                System.out.println(player.getDecription()+ "Желаете продолжить поход или вернуться в город? (да/нет)");
                break;
        }
        readCommand();
    }

    private static void readCommand(){
        command(scanner.nextLine());
        /*try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private static void beginFight(){
        new Battle().figth(player, createMonster(player.getLevel()), new FightCallback() {
            @Override
            public void fightWin() {
                //System.out.println(String.format("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d едениц здоровья.", player.getName(), player.getExperience(), player.getGold(), player.getHealth()));
                /*System.out.println(player.getDecription()+ "Желаете продолжить поход или вернуться в город? (да/нет)");
                readCommand();*/
                printNavigation();
            }

            @Override
            public void fightLost() {
                System.out.println("Игра закончена!");
                return;
            }
        });
    }

    private static Person createMonster(int level){
        if (Math.random()>0.5) return new Goblin(level);
        else return new Skeleton(level);
    }

}