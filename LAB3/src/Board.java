import Figures.*;

import java.util.ArrayList;
import java.util.Objects;

public class Board
{
    private char colorGame;

    public void setColorGame(char colorGame)
    {
        this.colorGame = colorGame;
    }
    public  char getColorGame()
    {
        return colorGame;
    }

    private Figure fields[][] = new Figure[8][8];
    private ArrayList<String> takeWhite = new ArrayList(16);
    private ArrayList<String> takeBlack = new ArrayList(16);
    public ArrayList<String> getTakeBlack()
    {
        return takeBlack;
    }
    public ArrayList<String> getTakeWhite()
    {
        return takeWhite;
    }

    public void init()
    {
        this.fields[0] = new Figure[]{
                new Rook("R", 'w'),     new Knight("N", 'w'),
                new Bishop("B", 'w'),   new Queen("Q", 'w'),
                new King("K", 'w'),     new Bishop("B", 'w'),
                new Knight("N", 'w'),   new Rook("R",'w')
        };
        this.fields[1] = new Figure[]{
                new Pawn("P", 'w'), new Pawn("P", 'w'),
                new Pawn("P", 'w'), new Pawn("P", 'w'),
                new Pawn("P", 'w'), new Pawn("P", 'w'),
                new Pawn("P", 'w'), new Pawn("P", 'w'),
        };

        this.fields[6] = new Figure[] {
                new Pawn("P", 'b'), new Pawn("P", 'b'),
                new Pawn("P", 'b'), new Pawn("P", 'b'),
                new Pawn("P", 'b'), new Pawn("P", 'b'),
                new Pawn("P", 'b'), new Pawn("P", 'b')
        };
        this.fields[7] = new Figure[]{
                new Rook("R", 'b'),     new Knight("N", 'b'),
                new Bishop("B", 'b'),   new Queen("Q", 'b'),
                new King("K", 'b'),     new Bishop("B", 'b'),
                new Knight("N", 'b'),   new Rook("R",'b')
        };
    }

    public String getCell(int row, int col)
    {
        Figure figure = this.fields[row][col];
        if (figure ==null)
        {
            return "    ";
        }
        return  " "+figure.getColor()+figure.getName()+" ";
    }

    public void print_board()
    {
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for (int row = 7; row > -1 ; row --)
        {
            System.out.print(row);
            for (int col=0; col<8; col++)
            {
                System.out.print("|"+getCell(row, col));
            }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for(int col=0; col< 8; col++)
        {
            System.out.print("    "+col);
        }
    }

    public boolean canMoveOnBoard(int row, int col, int row1, int col1)
    {
        //Проверим, выбрал ли игрок свою фигуру
        if (fields[row][col] == null || fields[row][col].getColor() != colorGame)
            return false;
        //Проверим, есть ли препятствия на пути фигуры в зависимости от типа
        //В случае короля, пешки(за исключением первого хода, который будет прописан ниже) и коня
        //фигура не движется по линии, а скачет с одного места в конечное
        //Следовательно для них достаточно проверки figure.canMove
        switch (fields[row][col].getName())
        {
            case "B":
                for (int i = 1; i < Math.abs(row - row1); i++)
                {
                    if ((fields[row + i][col + i] != null && row < row1 && col < col1) ||
                            (fields[row - i][col + i] != null && row > row1 && col > col1) ||
                            (fields[row + i][col - i] != null && row < row1 && col > col1) ||
                            (fields[row - i][col - i] != null && row > row1 && col > col1))
                    {
                        return false;
                    }
                }
            case "P":
                if ((Math.abs(row - row1) == 2) &&
                        ((fields[row][col].getColor() == 'b' && fields[row - 1][col] != null) ||
                                (fields[row][col].getColor() == 'w' && fields[row + 1][col] != null)))
                {
                    return false;
                }
            case "Q":
                if (row==row1)
                {
                    for (int i = Math.min(col,col1)+1; i < Math.max(col,col1); i++)
                    {
                        if (fields[row][i] != null)
                        {
                            return false;
                        }
                    }
                }
                else if (col==col1)
                {
                    for (int i = Math.min(row,row1)+1; i < Math.max(row,row1); i++)
                    {
                        if (fields[i][col] != null)
                        {
                            return false;
                        }
                    }
                }
                else
                {
                    for (int i = 1; i < Math.abs(row - row1); i++)
                    {
                        if ((fields[row + i][col + i] != null && row < row1 && col < col1) ||
                                (fields[row - i][col + i] != null && row > row1 && col > col1) ||
                                (fields[row + i][col - i] != null && row < row1 && col > col1) ||
                                (fields[row - i][col - i] != null && row > row1 && col > col1))
                        {
                            return false;
                        }
                    }
                }
            case "R":
                if (row==row1)
                {
                    for (int i = Math.min(col,col1)+1; i < Math.max(col,col1); i++)
                    {
                        if (fields[row][i] != null)
                        {
                            return false;
                        }
                    }
                }
                else if (col==col1)
                {
                    for (int i = Math.min(row,row1)+1; i < Math.max(row,row1); i++)
                    {
                        if (fields[i][col] != null)
                        {
                            return false;
                        }
                    }
                }
        }
        return true;
    }

    private final int[] BlackKing = {7,4};
    private final int[] WhiteKing = {0,4};
    public void ChangeKingCoord(int row, int col)
    {
        if (colorGame == 'b')
        {
            BlackKing[0] = row;
            BlackKing[1] = col;
        } else {
            WhiteKing[0] = row;
            WhiteKing[1] = col;
        }
    }
    public boolean isCheck()
    {
        for (int row = 0; row < fields.length; row++)
        {
            for (int col = 0; col < fields[0].length; col++)
            {

            }
        }
    }
    public char getCheckColor()
    {

    }
    public boolean isMate()
    {
        return false;
    }
    public boolean move_figure(int row, int col, int row1, int col1)
    {
      Figure figure = this.fields[row][col];

      if (figure != null && figure.canMove(row, col, row1, col1) &&
              this.fields[row1][col1] == null && figure.getColor() == this.colorGame)
      {
          if (Objects.equals(figure.getName(), "K"))
              ChangeKingCoord(row1,col1);
          this.fields[row1][col1] = figure;
          this.fields[row][col] = null;
          return true;
      }else
      {
          if (figure != null && figure.canAttack(row, col, row1, col1) && this.fields[row1][col1] != null &&
                  !Objects.equals(this.fields[row1][col1].getName(), "K") &&
                  this.fields[row1][col1].getColor() != this.fields[row][col].getColor())
          {
              switch (this.fields[row1][col1].getColor())
              {
                  case 'w': this.takeWhite.add(this.fields[row1][col1].getColor() + this.fields[row1][col1].getName()); break;
                  case 'b': this.takeBlack.add(this.fields[row1][col1].getColor() + this.fields[row1][col1].getName()); break;
              }
              if (Objects.equals(figure.getName(), "K"))
                  ChangeKingCoord(row1,col1);
              this.fields[row1][col1] = figure;
              this.fields[row][col] = null;
              return true;
          }
      }
        return false;
    }
}
