/**
 *
 * StackBuilder
 */
package Pico_Placa_predictor;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Javier_Reyes
 */
public class Main {

    private String numberPlate;
    private String date;
    private Time time;

    public static String dayOfWeekRestrict(String numberPlate) {
        String result = null;
        String lastDigit = null;
        if (numberPlate.length() != 7) {
            System.out.println("NUMERO DE PLACA INVALIDO");
            result = null;
        } else {
            lastDigit = numberPlate.substring(6);
            if (!isNumeric(lastDigit)) {
                System.out.println("NUMERO DE PLACA INVALIDO");
                result = null;
            } else {
                if (lastDigit.equals("1") || lastDigit.equals("2")) {
                    result = "Monday";
                } else if (lastDigit.equals("3") || lastDigit.equals("4")) {
                    result = "Tuesday";
                } else if (lastDigit.equals("5") || lastDigit.equals("6")) {
                    result = "Wednesday";
                } else if (lastDigit.equals("7") || lastDigit.equals("8")) {
                    result = "Thursday";
                } else if (lastDigit.equals("9") || lastDigit.equals("0")) {
                    result = "Friday";
                }
            }
        }
        return result;
    }

    private static boolean isNumeric(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String dayOfDate(String date) throws ParseException {
        String result = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateAux = sdf.parse(date);
        if (dateAux.getDay() == 1) {
            result = "Monday";
        } else if (dateAux.getDay() == 2) {
            result = "Tuesday";
        } else if (dateAux.getDay() == 3) {
            result = "Wednesday";
        } else if (dateAux.getDay() == 4) {
            result = "Thursday";
        } else if (dateAux.getDay() == 5) {
            result = "Friday";
        }
        return result;
    }

    public static boolean hourRestrict(String hour) {
        boolean result = false;
        LocalTime horaInicioManana = LocalTime.parse("07:00");
        LocalTime horaFinManana = LocalTime.parse("09:30");
        LocalTime horaInicioTarde = LocalTime.parse("16:00");
        LocalTime horaFinTarde = LocalTime.parse("19:30");
        LocalTime hora = LocalTime.parse(hour);
        if ((hora.compareTo(horaInicioManana) >= 0 && hora.compareTo(horaFinManana) <= 0) || (hora.compareTo(horaInicioTarde) >= 0 && hora.compareTo(horaFinTarde) <= 0)) {
            result = true;
        }
        return result;
    }

    public static void main(String args[]) throws ParseException {
        /*String placa="PDF0190";
        String fecha="31/01/2020";
        String hora="19:10";*/
        Scanner in = new Scanner(System.in);
        String placa = "";
        String fecha = "";
        String hora = "";
        System.out.println("INGRESE UNA PLACA CON FORMATO AAA0987");
        placa = in.nextLine();
        System.out.println("INGRESE UNA FECHA CON FORMATO  dd/MM/aaaa");
        fecha = in.nextLine();
        System.out.println("INGRESE UNA HORA CON FORMATO hh:mm");
        hora = in.nextLine();
        String diaRestriccion = null;
        String diaFecha = null;
        boolean horaEvaluar = false;
        diaRestriccion = dayOfWeekRestrict(placa);
        diaFecha = dayOfDate(fecha);
        horaEvaluar = hourRestrict(hora);
        if (diaRestriccion == null) {
            System.out.println("NUMERO DE PLACA INVALIDO");
        } else {
            if (diaRestriccion.equals(diaFecha) && horaEvaluar) {
                System.out.println("EL VEHICULO DE PLACA " + placa + " NO PUEDE CIRCULAR");
            } else {
                System.out.println("EL VEHICULO PUEDE CIRCULAR");
            }
        }
    }
}
