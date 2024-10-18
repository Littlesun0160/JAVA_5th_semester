import java.util.Scanner;
import java.util.Arrays;

public class task4
{
    public static void main(String[] args)
    {
        //4 задание. поворот массива на 90 градусов по часовой стрелке
        Scanner scan = new Scanner(System.in);
        System.out.println("Задание №4");
        int Size1 = 5; int Size2 = 3;
        int[][] StartMatrix =  {{1, 2,  3},
                                {4, 5,  6},
                                {7, 8,  9},
                                {10,11,12},
                                {13,14,15}};
        int[][] EndMatrix = new int[Size2][Size1];

        for (int i=0; i<Size2; i++)
        {
            for(int j=0; j<Size1; j++)
            {
                EndMatrix[i][Size1-1-j]=StartMatrix[j][i];
            }
        }

        System.out.println("Итоговый массив: ");
        for (int k=0; k<Size2; k++)
        {
            System.out.println(Arrays.toString(EndMatrix[k]));
        }
    }
}