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
            String rowID,cf,qualifier,value;

            System.out.format("\nProjet réseau social, base de donnée utilisée : %s\n",tablename);
            String input = "";

            printHelp();
            Scanner scanIn = new Scanner(System.in);
            while (!input.equals("q") && !input.equals("quit")) {
                System.out.print(">");
                input = scanIn.nextLine();
                switch (input){
                    case "s":
                    case "scan":
                        System.out.println("===========show all record========");
                        people.getAllRecord();
                        break;
                    case "put":
                    case "p":
                        System.out.println("[RowId (prénom)] : ");
                        rowID = scanIn.nextLine();
                        System.out.println("[CF (infos || friends)] : ");
                        cf = scanIn.nextLine();
                        switch (cf){
                            case "infos":
                                System.out.println("[Qualifier] : ");
                                qualifier=scanIn.nextLine();
                                System.out.format("[Qualifier][%s] : ",qualifier);
                                value=scanIn.nextLine();
                                people.addRecord(rowID, cf,qualifier , value);


                                break;
                            case "friends":
                                System.out.println("[Qualifier (bff || others)] : ");
                                qualifier=scanIn.nextLine();
                                System.out.format("[Qualifier][%s] : ", qualifier);
                                value=scanIn.nextLine();
                                people.addRecord(rowID, cf,qualifier , value);
                                break;

                        }

                        break;
                    case "q":
                    case "quit":
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
