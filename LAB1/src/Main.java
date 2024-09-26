import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        //1 задание. Сиракузская последовательность
       Scanner scan = new Scanner(System.in);
        System.out.println("Задание №1");
        System.out.println("Введите натуральное число");
        int number1 = scan.nextInt();

        int steps = 0;
        while (number1 != 1)
        {
            if (number1 % 2 == 0)
            {
                number1 = number1 / 2;
            } else
            {
                number1 = 3 * number1 + 1;
            }
            steps++;
        }
        System.out.println("Необходимо " + steps + " шагов, чтобы прийти к 1");


        //Задание 2. Сумма ряда
        System.out.println("Задание №2");
        System.out.println("Введите количество чисел");
        int count = scan.nextInt();
        System.out.println("Введите натуральные числа");

        int result = 0;
        for (int i=0; i<count; i++)
        {
            int number2 = scan.nextInt();
            if (i % 2 == 0)
            {
                result += number2;
            }
            else
            {
                result -= number2;
            }
        }
        System.out.println("Сумма ряда равна " + result);


        //Задание 3. Поиск сокровища
        System.out.println("Задание №3");
        System.out.println("Введите координаты клада");
        int coordX = scan.nextInt();
        int coordY = scan.nextInt();
        System.out.println("Введите указания карты");

        int currX = 0;
        int currY = 0;
        int countOfSteps = 0;

        boolean check = false; //Проверяет, дошли ли мы до сокровища
        while (true)
        {
            scan.nextLine();
            String orientation = scan.nextLine();
            if (orientation.equals("стоп"))
            {
                break;
            }
            int moves = scan.nextInt();
            if (!check)
            {
                switch (orientation)
                {
                    case "север":
                        currY += moves;
                        break;
                    case "юг":
                        currY -= moves;
                        break;
                    case "запад":
                        currX -= moves;
                        break;
                    case "восток":
                        currX += moves;
                        break;
                    default:
                        throw new RuntimeException("Ошибка!");
                }
                countOfSteps++;

                if (currX==coordX && currY ==coordY)
                {
                    check = true;
                }
            }
        }
        System.out.println("Количество шагов равно " + countOfSteps);


        //Задание 4. Логистический максимин
        System.out.println("Задание №4");
        System.out.println("Введите количество дорог");
        int countOfRoads = scan.nextInt();

        int MaxHight = 0; //Максимальная высота грузовика
        int NumRoad = 0;
        for (int i = 1; i <= countOfRoads; i++)
        {
            System.out.println("Введите для " + i + " дороги количество тонелей, а затем высоту каждого тоннеля");
            int countOfTunnels = scan.nextInt();

            int MinHight = Integer.MAX_VALUE; //Минимальная высота среди тонелей
            for (int j = 0; j < countOfTunnels; j++)
            {
                int Tunnel = scan.nextInt();
                if (Tunnel < MinHight)
                {
                    MinHight = Tunnel;
                }
            }
            if (MinHight > MaxHight)
            {
                NumRoad = i;
                MaxHight = MinHight;
            }
        }
        System.out.println("Номер дороги " + NumRoad + ", максимальная высота грузовика " + MaxHight);


        //Задание 5. Дважды четное число
        System.out.println("Задание №5");
        System.out.println("Введите целое трехзначное число");
        int number = scan.nextInt();

        if (number < 99 || number > 999)
        {
            throw new RuntimeException("Ошибка! Число не трехзначное");
        }

        int num1 = number / 100;
        int num2 = (number / 10) % 10;
        int num3 = number % 10;
        int sum = num1 + num2 + num3;
        int multiply = num1 * num2 * num3;

        if (sum % 2 == 0 && multiply % 2 == 0)
        {
            System.out.println("Число является дважды четным");
        }
        else
        {
            System.out.println("Число не является дважды четным");
        }
    }
}
