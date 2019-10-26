import java.util.List;
import java.util.ArrayList;

class Test{

    public static void main(String[] args) {
        System.out.println(calculate("3+(7+3*2)+(2*2)"));
    }
    static String calculate(String result){

        if(result.matches(".*[(].*")){
            String part1 = result.substring(0, result.indexOf('('));
            String part2 = result.substring(result.indexOf('(') + 1, result.indexOf(')'));
            String part3 = result.substring(result.indexOf(')') + 1);

            return calculate(part1 + calculate(part2) + part3);
        }

        String counting;

        if(result.charAt(0)=='-')
            counting = 0 + result;
        else
            counting = result;

        String[] numbers = counting.split("\\s*[-/*+]+\\s*");
        String[] signs = counting.split("\\s*[0-9.]+\\s*");

        List<String> signList = new ArrayList<>();
        List<Double> numbersList = new ArrayList<>();

        for(int i = 0; i < numbers.length; i++){
            numbersList.add(Double.valueOf(numbers[i]));
            signList.add(signs[i]);
        }

        signList.add("&");

        int a = 0;

        for(String sign : signList){
            if(sign.equals("/") || sign.equals("*")){
                a++;
            }
        }

        int b = 0;

        while(b < a){
            for(int i = 0; i < signList.size(); i++){
                if(signList.get(i).equals("/")){
                    if(numbersList.get(i) == 0)
                        return "Nie można dzielić przez 0!";
                    numbersList.set(i - 1, numbersList.get(i - 1) / numbersList.get(i));
                    numbersList.remove(i);
                    signList.remove(i);
                    b++;
                }
                if(signList.get(i).equals("*")){
                    numbersList.set(i - 1, numbersList.get(i - 1) * numbersList.get(i));
                    numbersList.remove(i);
                    signList.remove(i);
                    b++;
                }
            }
        }

        while(signList.size() > 2){
            for(int i = 0; i < signList.size(); i++){
                if(signList.get(i).equals("+")){
                    numbersList.set(i - 1, numbersList.get(i - 1) + numbersList.get(i));
                    numbersList.remove(i);
                    signList.remove(i);
                    b++;
                }
                if(signList.get(i).equals("-")){
                    numbersList.set(i - 1, numbersList.get(i - 1) - numbersList.get(i));
                    numbersList.remove(i);
                    signList.remove(i);
                    b++;
                }
            }
        }

        return Double.toString(numbersList.get(0));
    }
}