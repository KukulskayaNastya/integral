import java.util.Locale;

public class Main {

    private static double a = 0.0;
    private static double b = 0.4;
    private static double max1 = 51.2695;
    private static double max2 = 4136.81;

    public static void main(String[] s){
        Locale l = Locale.ENGLISH;
        System.out.println("      J8             J16             JR           |JR-J8|             A");
        double JR_Pr= (Math.pow(2,2)*Pr(16)-Pr(8))/(Math.pow(2,2)-1);
        double JR_Tr= (Math.pow(2,2)*Tr(16)-Tr(8))/(Math.pow(2,2)-1);
        double JR_Sim=(Math.pow(2,4)*Sim(16)-Sim(8))/(Math.pow(2,4)-1);
        double A_Pr= max1*(Math.abs(Math.pow(b-a,3)))/(24*Math.pow(8,2));
        double A_Tr= max1*(Math.abs(Math.pow(b-a,3)))/(12*Math.pow(8,2));
        double A_Sim=max2*(Math.abs(Math.pow(b-a,5)))/(2880*Math.pow(8,4));
        System.out.println(String.format(l,"%.10f",Pr(8))+"    "+String.format(l,"%.10f",Pr(16))+"    "
                          +String.format(l,"%.10f",JR_Pr)+"    "+String.format(l,"%.10f",Math.abs(JR_Sim-Pr(8)))+"    "
                          +String.format(l,"%.10f",A_Pr));

        System.out.println(String.format(l,"%.10f",Tr(8))+"    "+String.format(l,"%.10f",Tr(16))+"    "
                           +String.format(l,"%.10f",JR_Tr)+"    "+String.format(l,"%.10f",Math.abs(JR_Sim-Tr(8)))+"    "
                           +String.format(l,"%.10f",A_Tr));

        System.out.println(String.format(l,"%.10f",Sim(8))+"    "+String.format(l,"%.10f",Sim(16))+"    "
                          +String.format(l,"%.10f",JR_Sim)+"    "+String.format(l,"%.10f",Math.abs(JR_Sim-Sim(8)))+"    "
                          +String.format(l,"%.10f",A_Sim));
    }

    private static double Pr(int n){
        double h = (b-a)/n;
        double res = 0;
        for (int k=1;k<=n;k++){
            res = res + h*f(a+(2*k-1)*h/2);
        }
        return res;
    }

    private static double Tr(int n){
        double h = (b-a)/n;
        double sum = 0;
        for (int k=1; k<=n-1; k++){
            sum = sum + f(a+k*h);
        }
        return h*((f(a)+f(b))/2+sum);
    }

    private static double Sim(int n){
        double h = (b-a)/n;
        double sum1 = 0;
        for (int k=1; k<=n-1; k++){
            sum1 = sum1 + f(a+k*h);
        }
        double sum2 = 0;
        for (int k=1; k<=n; k++){
            sum2 = sum2 + f(a+(2*k-1)*h/2);
        }
        return h/6*(f(a)+f(b)+2*sum1+4*sum2);
    }

    private static double f(double x){
        return 1/(Math.exp(x)-0.68);
    }
}