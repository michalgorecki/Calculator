import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Created by emigore on 2017-03-16.
 */
public class StringToListConverter {

    public static ArrayList<String> convertStringToList(String input){
        ArrayList<String> outputList = new ArrayList<String>();
        input = input.replaceAll("\\+"," + ")
                .replaceAll("\\-"," - ")
                .replaceAll("\\*"," * ")
                .replaceAll("\\/"," / ")
                .replaceAll("\\("," ( ")
                .replaceAll("\\)"," ) ");
        StringTokenizer tokenizer = new StringTokenizer(input);
        while(tokenizer.hasMoreTokens()){
            outputList.add(tokenizer.nextToken());
        }
        return outputList;
    }

}
