import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainRegexV01 {
    public static void main(String[] args) throws IOException {
        // быстрая проверка наличия совпадения
        System.out.println( Pattern.matches("a*b", "aaaaab") );
        System.out.println( Pattern.matches("a*b", "b") );
        System.out.println( Pattern.matches("c*b", "aaaaab") );

        // обработка через объект Pattern
        // только split
        Pattern p1 = Pattern.compile("<-*>");
        String[] sss =  p1.split("Королева Манана любила грабить корованы.<--->Король Банана любил бананы.<------->Принц Папана купил батут.<->Наташа Королёва любила тюльпаны.");
        for(String s : sss)
            System.out.println(s);

        // типовая схема проверки / обработки
        String myText = "Королева Манана любила грабить корованы с коровами и менять награбленное на грабли.<--->Король Банана любил бананы.<------->Принц Папана купил батут.<->Наташа Королёва любила тюльпаны.";

        Pattern pattern = Pattern.compile("<-*>"); //, Pattern.

        Matcher m = pattern.matcher(myText);

        System.out.println( m.matches());

        System.out.println(m.find());
        System.out.println(m.group());

        m.reset();
        while (m.find()){
            int left = m.start();
            int right = m.end();
            System.out.println(myText.substring(left, right));
        }

        System.out.println(  m.replaceAll("***"));

    }
}
