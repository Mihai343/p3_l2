import org.graalvm.polyglot.*;
class Polyglot {

    public static void main(String[] args) {

        Context polyglot=Context.newBuilder().allowAllAccess(true).build();

        Value x=polyglot.eval("python", "int(input())");
        int xInt=x.asInt();

        Value x2=polyglot.eval("python", "int(input())");
        int x2Int=x2.asInt();

        Value y=polyglot.eval("R", "pbinom("+x2Int+", "+xInt+", prob=0.5)");
        double result=y.asDouble();
        System.out.println(result);

        polyglot.close();

    }
}
