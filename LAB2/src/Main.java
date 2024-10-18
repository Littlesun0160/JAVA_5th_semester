import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        //1 задание. подстрока без повторяющихся символов
        Scanner scan = new Scanner(System.in);
        System.out.println("Задание №1");
        System.out.println("Введите текст");
        String str = scan.nextLine();

        String result = ""; //Задаем переменную, которая будет в дальнейшем ответом. Наименьшим ответом может быть первый символ строки
        for (int i = 0; i< str.length()-1; i++)
        {
            String InterStr = str.substring(i,i+1);
            for (int j = i+1; j < str.length(); j++)
            {
                if (InterStr.indexOf(str.charAt(j)) >= 0)
                {
                    break;
                }
                else
                {
                    InterStr = InterStr + str.charAt(j);
                }
            }

            if (InterStr.length() > result.length())
            {
                result = InterStr;
            }
        }
        System.out.println("Наибольшая подстрока с различными символами это: " + result);
    }
}
