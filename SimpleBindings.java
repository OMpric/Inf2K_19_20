import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import sample.PersonObs;

public class SimpleBindings {
    public static void main(String[] args) {
        // создаем наблюдаемый объект
        StringProperty name = new SimpleStringProperty("Вася");

        String lastName = "Васечкин";  // это будет "не наблюдаемый объект"

        //проверка их начального состояния
        System.out.println("Сначала :");
        System.out.println("наблюдаемый  = " + name);
        System.out.println("НЕ наблюдаемый  = " + lastName);

        // создаем "наблюдателя", он будет знать об изменениях в наблюдаемом
        StringBinding strBind = (StringBinding) Bindings.format("Персонажа зовут %s %s", name, lastName);

        //выводим
        System.out.println("Наблюдатель видит изменения только в наблюдаемом :");
        System.out.println(strBind.getValue()); // как было сначало
        // меняем наблюдаемого, смотрим что делает наблюдатель
        name.setValue("Петя") ;                        System.out.println(strBind.getValue());
        name.setValue("Маша") ;                        System.out.println(strBind.getValue());
        name.setValue("Billy") ;  lastName = "Gates";  System.out.println(strBind.getValue());

//--------------------------------------------------------------------------------
        // создаем наблюдаемый объект
        IntegerProperty first = new SimpleIntegerProperty(57);

        int x = 100;  // это будет "не наблюдаемый объект"

        //проверка их начального состояния
        System.out.println("Сначала :");
        System.out.println("наблюдаемый first = " + first);
        System.out.println("НЕ наблюдаемый ч = " + x);

        // создаем "наблюдателя", он будет знать об изменениях в наблюдаемом
        NumberBinding b1 = Bindings.max(x, first);
        //выводим
        System.out.println("Наблюдатель видит изменения только в наблюдаемом :");
        System.out.println(b1.getValue()); // как было сначало
        // меняем наблюдаемого, смотрим что делает наблюдатель
        first.setValue(200) ;            System.out.println(b1.getValue());
        first.setValue(-4) ;             System.out.println(b1.getValue());
        first.setValue(2019) ;  x=3000;  System.out.println(b1.getValue());
//--------------------------------------------------------------------------------
        // создаем наблюдаемый объекты
        IntegerProperty second = new SimpleIntegerProperty(444);
        IntegerProperty third = new SimpleIntegerProperty(2);

        //проверка их начального состояния

        System.out.println("наблюдаемый  = " + first);
        System.out.println("НЕ наблюдаемый ч = " + x);

        // создаем "наблюдателя", он будет знать об изменениях в наблюдаемых
        NumberBinding b2 = Bindings.add(second, third.multiply(1000));
        //выводим
        System.out.println("Сначала :");
        System.out.println(b2.getValue()); // как было сначало
        // меняем наблюдаемых, смотрим что делает наблюдатель
        second.setValue(200) ;
        third.setValue(-1) ;
        System.out.println("Наблюдатель видит изменения :");
        System.out.println(b2.getValue());
//--------------------------------------------------------------------------------
        PersonObs vasya = new PersonObs("Вася", 25);
        PersonObs mary = new PersonObs("Маша", 25);

        StringBinding whoMarried = (StringBinding) Bindings.format("%s + %s = !!!lovelovelove!!!", vasya.nameProperty(), mary.nameProperty());

        System.out.println(whoMarried.getValue());

//        whoMarried.addListener(new InvalidationListener() {
//                                   @Override
//                                   public void invalidated(Observable observable) {
//                                       System.out.println("были изменения, связь invalid");
//                                   }
//                               }
//        );
//        whoMarried.addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                System.out.println("изменились: "+oldValue +" на " + newValue);
//            }
//        });

        vasya.setName("Bill");
      //  mary.setName("Kate");

        System.out.println(whoMarried.getValue());
    }
}
