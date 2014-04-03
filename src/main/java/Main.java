import br.com.sfchatter.ChatterBO;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Select what do you want to do:");
        System.out.println("1) Get statistics;");
        System.out.println("2) Create random posts;");
        System.out.println("3) Like random posts;");

        ChatterBO chatterBO = new ChatterBO();

        Integer choice = keyboard.nextInt();
        if(choice == 1){
            System.out.println("Please wait, it will take some minutes!");
            chatterBO.getStatistics();
        }else {
            System.out.print("How many groups? ");
            Integer howManyGroups = keyboard.nextInt();
            System.out.print("How many posts? ");
            Integer howManyPosts = keyboard.nextInt();

            if (choice ==  2){
                chatterBO.randomPost(howManyGroups, howManyPosts);
            } else {
                chatterBO.randomLike(howManyGroups, howManyPosts);
            }
        }
    }
}
