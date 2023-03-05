//import libraria principala polyglot din graalvm
import org.graalvm.polyglot.*;
import java.util.*;

//clasa principala - aplicatie JAVA
class Polyglot {

    private static void afisare(Vector<Integer> arr) {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();

        polyglot.eval("js", "console.log('" + arr + "')");
        polyglot.close();
    }

    private static String cut(Vector<Integer> arr){
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();

        int nr1 = arr.size();

        String str = arr.toString();
        String str2 = str.replace('[',' ');
        String str3 = str2.replace(']',' ');
        //System.out.println(str3);
        Value arr2 = polyglot.eval("R","mean(c("+str3+"),trim = 0.2, na.rm = TRUE)");
        String x = arr2.toString();


        polyglot.close();
        return x;
    }


    private static Vector<Integer> Create(){
        //construim un context care ne permite sa folosim elemente din PYTHON
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value arr = polyglot.eval("python","import random; [random.randint(-100, 100) for _ in range(20)]");


        Vector<Integer> x = new Vector<Integer>();
        for(int i = 0; i < arr.getArraySize(); i++){
            x.add(arr.getArrayElement(i).asInt());
        }

        polyglot.close();
        return x;

    }

    public static void main(String[] args) {


        Vector<Integer> arr = Polyglot.Create();
        //System.out.println(arr);
        Polyglot.afisare(arr);
        System.out.println(Polyglot.cut(arr));

        //polyglot.close();
    }
}