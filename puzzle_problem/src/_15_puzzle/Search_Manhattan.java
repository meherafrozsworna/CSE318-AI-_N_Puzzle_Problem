package _15_puzzle;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import static _15_puzzle.main.n;

public class Search_Manhattan {
    public void Manhattan_heuristic_search(int start_state[][])
    {
        int number_of_expanded_node =0;
        int node_in_pq = 0 ;
        State state = new State(0,start_state);
        state.previous_state = null ;
        state.setBlank_x_y();

        state.set_cost_manhattan();
        PriorityQueue<State> q = new PriorityQueue<State>(Comparator.comparing(State -> State.cost_manhattan));
        HashMap<State, Integer> hashMap = new HashMap<>();
        q.add(state);
        node_in_pq++ ;
        hashMap.put(state,node_in_pq);

        System.out.println("Solavable : "+state.solvable());

        if(state.solvable())
        {
            while (!q.isEmpty())  {
                State s1 = q.poll();
                number_of_expanded_node++;
                State temp ;
                //s1.printState();
                if(s1.is_Final_state())
                {
                    System.out.println("Path : ");
                    s1.printSteps();
                    System.out.println("Path cost : "+ s1.level);
                    System.out.println("Number of expanded node : "+ (number_of_expanded_node-1));
                    //System.out.println(hashMap.size());
                    //System.out.println("Number of explored node : "+ node_in_pq);
                    return;
                }
                else
                {
                    //moving up
                    if(s1.blank_x-1 >=0)
                    {
                        int t ;
                        temp = new State(s1.level+1 ,s1.arr);
                        t = temp.arr[s1.blank_x][s1.blank_y] ;
                        temp.arr[s1.blank_x][s1.blank_y] = temp.arr[s1.blank_x-1][s1.blank_y];
                        temp.arr[s1.blank_x-1][s1.blank_y] = t ;
                        temp.blank_x = s1.blank_x-1;
                        temp.blank_y = s1.blank_y ;
                        temp.previous_state = s1 ;
                        temp.set_cost_manhattan();

                        if (!(hashMap.containsKey(temp)) && !(q.contains(temp)))
                        {
                            //System.out.println();
                            //temp.printState();
                            q.add(temp);
                            node_in_pq++ ;
                            hashMap.put(temp,node_in_pq);
                        }


                    }

                    //moving down
                    if(s1.blank_x+1 < n)
                    {
                        int t ;
                        temp = new State(s1.level+1 ,s1.arr);
                        t = temp.arr[s1.blank_x][s1.blank_y] ;
                        temp.arr[s1.blank_x][s1.blank_y] = temp.arr[s1.blank_x+1][s1.blank_y];
                        temp.arr[s1.blank_x+1][s1.blank_y] = t ;
                        temp.blank_x = s1.blank_x+1;
                        temp.blank_y = s1.blank_y ;
                        temp.previous_state = s1 ;
                        temp.set_cost_manhattan();
                        if (!(hashMap.containsKey(temp)) && !(q.contains(temp)))
                        {
                            //System.out.println();
                            //temp.printState();
                            q.add(temp);
                            node_in_pq++ ;
                            hashMap.put(temp,node_in_pq);
                        }
                    }

                    //moving left
                    if(s1.blank_y-1 >=0 )
                    {
                        int t ;
                        temp = new State(s1.level+1 ,s1.arr);
                        t = temp.arr[s1.blank_x][s1.blank_y] ;
                        temp.arr[s1.blank_x][s1.blank_y] = temp.arr[s1.blank_x][s1.blank_y-1];
                        temp.arr[s1.blank_x][s1.blank_y-1] = t ;
                        temp.blank_x = s1.blank_x;
                        temp.blank_y = s1.blank_y-1 ;
                        temp.previous_state = s1 ;
                        temp.set_cost_manhattan();
                        if (!(hashMap.containsKey(temp)) && !(q.contains(temp)))
                        {
                            //System.out.println();
                            //temp.printState();
                            q.add(temp);
                            node_in_pq++ ;
                            hashMap.put(temp,node_in_pq);
                        }

                    }

                    //moving right
                    if(s1.blank_y+1 < n)
                    {
                        int t ;
                        temp = new State(s1.level+1 ,s1.arr);
                        t = temp.arr[s1.blank_x][s1.blank_y] ;
                        temp.arr[s1.blank_x][s1.blank_y] = temp.arr[s1.blank_x][s1.blank_y+1];
                        temp.arr[s1.blank_x][s1.blank_y+1] = t ;
                        temp.blank_x = s1.blank_x;
                        temp.blank_y = s1.blank_y+1 ;
                        temp.previous_state = s1 ;
                        temp.set_cost_manhattan();
                        if (!(hashMap.containsKey(temp)) && !(q.contains(temp)))
                        {
                            //System.out.println();
                            //temp.printState();
                            q.add(temp);
                            node_in_pq++ ;
                            hashMap.put(temp,node_in_pq);
                        }
                    }
                }
                //System.out.println("_________________________________________");
            }
        }


    }

}
