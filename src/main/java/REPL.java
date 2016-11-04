import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by felicien on 04/11/16.
 */
public class REPL {

    public static void printHelp(){
        System.out.println("Voici la liste des commandes disponibles :");
        System.out.println("\thelp (h)\tAffiche l'aide ");
        System.out.println("\tput (p)\t\tAjouter une entrée");
        System.out.println("\tscan (s)\tAfficher le contenu de la base");
        System.out.println("\tquit (q) \tQuitter le programme");
    }

    public static void main(String[] agrs) {
        try {
            String tablename = "fcatherin";
            String[] familys = {"infos", "friends"};
            HBase people = new HBase(tablename, familys);
            String prenom,infos,bff,friends;

            people.addRecord("felicien", "infos", "sex", "m");

            people.addRecord("felicien", "infos", "email", "felicien.catherin@edu.ece.fr");
            people.addRecord("felicien", "infos", "age", "22");

            people.addRecord("felicien", "friends", "BFF", "Benji");
            friends = "Michel, Jonas, Paul_Hubert";

            people.addRecord("felicien", "friends", "others", friends);

            System.out.format("Projet réseau social, base de donnée utilisée : %s\n",tablename);
            String input = "";

            printHelp();
            Scanner scanIn = new Scanner(System.in);
            while (!input.equals("q")) {
                input = scanIn.nextLine();
                switch (input){
                    case "s":
                    case "scan":
                        System.out.println("===========show all record========");
                        People.getAllRecord();
                        break;
                    case "put":
                        System.out.println("[RowId(prenom)]: ");
                        input = scanIn.nextLine();

                    case "p":
                        break;
                    case "h":
                    case "help":
                    default:
                        printHelp();
                    break;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
