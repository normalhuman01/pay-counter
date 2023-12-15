package Classes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import sql.UserQueries;

public class Report {

    private int idUser;
    private LocalTime time;
    private LocalDate date;
    private double lecturaMes;
    private double payment;
    private String username;

    public Report() {

    }

    public Report(int idUser, LocalTime time, LocalDate date, double lecturaMes,
            double antMes, double cargoFijo, double mantConexion,
            double consumoEnergia, double alumbradoPublico, double interesComp,
            double electRural, String opcional) {
        this.idUser = idUser;
        this.time = time;
        this.date = date;
        this.lecturaMes = lecturaMes;
        this.payment = totalMonth(lecturaMes, antMes, cargoFijo, mantConexion, 
                consumoEnergia, alumbradoPublico, interesComp, electRural, opcional);
    }

    public Report(String username, LocalTime time, LocalDate date, 
            double lecturaMes, double payment) {
        this.username = username;
        this.time = time;
        this.date = date;
        this.lecturaMes = lecturaMes;
        this.payment = payment;
    }
    
    public Report(String username, double payment){
        this.username = username;
        this.payment = payment;
    }

    private static double sumOpc(String entrada) {
        entrada = entrada.trim();
        entrada = entrada.replaceAll(" ", "");
        String temp[] = entrada.split(",");
        double sum = 0;

        for (String n : temp) {
            sum = sum + Double.parseDouble(n);
        }

        return sum;
    }

    public int getidUser() {
        return idUser;
    }

    public LocalTime getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getLecturaMes() {
        return lecturaMes;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
    
    public String getUsername(){
        return username;
    }

    public final double totalMonth(double lecturaMes, double antMes, 
            double cargoFijo, double mantConexion, double consumoEnergia, 
            double alumbradoPublico, double interesComp,
            double electRural, String opcional) {

        double consumo = lecturaMes - antMes;
        consumo = consumo * consumoEnergia;
        double sumaImpuestos;

        sumaImpuestos = (cargoFijo + mantConexion + alumbradoPublico
                + interesComp + electRural + sumOpc(opcional))/ UserQueries.nUsers;

        sumaImpuestos = sumaImpuestos + consumo;     
        BigDecimal bigDecimal = new BigDecimal(sumaImpuestos + sumaImpuestos * 0.18).setScale(2, RoundingMode.UP);
        return bigDecimal.doubleValue();
    }
}
