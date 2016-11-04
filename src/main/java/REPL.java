import java.util.Arrays;
import java.util.List;

/**
 * Created by felicien on 04/11/16.
 */
public class REPL {

    public static void main(String[] agrs) {
        try {
            String tablename = "fcatherin";
            String[] familys = {"infos", "friends"};
            HBase People = new HBase(tablename, familys);

            People.addRecord("felicien", "infos", "sex", "m");

            People.addRecord("felicien", "infos", "email", "felicien.catherin@edu.ece.fr");
            People.addRecord("felicien", "infos", "age", "22");

            People.addRecord("felicien", "friends", "BFF", "Benji");
            String friends = "Michel, Jonas, Paul_Hubert";

            People.addRecord("felicien", "friends", "others", friends);


            People.getAllRecord();

            System.out.println("===========show all record========");
            People.getAllRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
