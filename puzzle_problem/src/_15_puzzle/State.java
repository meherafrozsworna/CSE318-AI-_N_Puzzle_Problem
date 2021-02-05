package _15_puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static _15_puzzle.main.final_state;
import static _15_puzzle.main.n;

public class State {
    int arr[][];
    State previous_state ;
    int level ;
    int cost_displacement ;
    int cost_manhattan ;
    int blank_x , blank_y ;

    public State(int level)
    {
        arr = new int[n][n];

        this.level = level ;
    }
    public State(int level , int arr1[][] )
    {
        arr = new int[n][n];
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n ; j++) {
                arr[i][j] = arr1[i][j];
            }
        }

        this.level = level ;
    }
    public State()
    {

    }

    public void printState()
    {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j]+"  ");
            }
            System.out.println();
        }
    }

    public boolean is_Final_state()
    {
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j] != final_state[i][j])
                    return false ;
            }
        }
        return true ;
    }

    public int displacement_heuristic()
    {
        int count = 0 ;
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j] != final_state[i][j])
                    count++ ;
            }
        }
        return count ;
    }
    public void set_cost_displacement()
    {
        cost_displacement = level + displacement_heuristic();
    }

    public void setBlank_x_y()
    {
        int count = 0 ;
        int flag = 0 ;
        for (int i = 0; i < n ; i++) {
            if (flag == 0)
            {
                for (int j = 0; j < n; j++) {
                    if(arr[i][j] == 0)
                    {
                        blank_x = i ;
                        blank_y = j ;
                        flag = 1 ;
                        break;
                    }
                }
            }

        }
    }

    public int calculateDistance(int value , int x_val , int y_val)
    {
        int dist = 0 , x_dist , y_dist;
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n ; j++) {
                if (value !=0 && value == final_state[i][j])
                {
                    x_dist = Math.abs(i-x_val);
                    y_dist = Math.abs(j-y_val);
                    dist = x_dist+y_dist ;
                }
            }
        }
        return dist ;
    }

    public int calculateManhattan_heuristic()
    {
        int man = 0 ;
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n ; j++) {
                man+= calculateDistance(arr[i][j],i,j);
            }
        }
        return man;
    }

    public void set_cost_manhattan()
    {
        cost_manhattan = level + calculateManhattan_heuristic();
    }


    public void printSteps()
    {
        Stack<State> stateStack = new Stack<State>();
        stateStack.push(this);
        State temp = this ;
        while (temp.previous_state != null)
        {
            temp = temp.previous_state;
            stateStack.push(temp);
        }

        while (!stateStack.empty())
        {

            temp = stateStack.pop() ;
            System.out.println( "steps : "+ temp.level);
            temp.printState();
            System.out.println();
        }
    }

    public boolean solvable()
    {
        int blank_row = n-blank_x ;
        int inv = inversion_count();
        if ((blank_row%2==0) && (inv%2 !=0))
            return true;
        else if((blank_row%2!=0) && (inv%2 ==0))
            return true;

            return false;

    }

    int inversion_count()
    {
        int count= 0 ;
        List<Integer> final_list = new ArrayList<Integer>();
        List<Integer> c_list = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (final_state[i][j] != 0)
                    final_list.add(final_state[i][j]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != 0)
                    c_list.add(arr[i][j]);
            }
        }

        for (int i = 0; i < 15 ; i++) {
            int j = final_list.indexOf(c_list.get(i));
            //int c = 0 ;

            for (int k = 0; k < j ; k++) {

                for (int l = i+1; l < 15  ; l++) {
                    if ( final_list.get(k) == c_list.get(l))
                    {
                        count++ ;
                        //c++;
                        break;
                    }

                }

            }
            //System.out.println("  .......c : "+ c);
        }

        //System.out.println("Count : "+ count);
        return count;

    }





    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof State)) {
            return false;
        }
        State s = (State) obj;
        int flag = 0 ;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ( arr[i][j] != s.arr[i][j]) {
                    flag = 1 ;
                    break;
                }
            }
            if (flag == 1)
                break;
        }

        if(flag == 0)
            return true ;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.arr);
    }






}
