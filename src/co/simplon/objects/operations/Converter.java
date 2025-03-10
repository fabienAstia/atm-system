package co.simplon.objects.operations;

public class Converter {

    public static boolean onlyDigits(String input){
        return  input.matches("[0-9]+");
    }

    public static Integer toInt(String string){
        return Integer.parseInt(string);
    }
}
