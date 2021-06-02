package sample.calculating;

import java.util.Calendar;
import java.util.Date;

public class CalculatingDateResources {
    /**
     * Метод принимает в себя переменную типа Date, содержащую  дату, на которой были выполнены работы
     * через 6+1 месяцев эксплуатации самолета, прибавляет к этой дате 7 месяцев и высчитывает разницу между
     * этой датой и датой в настоящем времени
     */

    public static long calculateDateInMonth(Date date) {
        Date dateAfter = new Date(date.getTime());
        dateAfter.setMonth(dateAfter.getMonth() + 7);
        Date dateNow = new Date();
        return (dateAfter.getTime() + 86_400_000) - dateNow.getTime();
    }

    /**
     * Метод принимает в себя две переменных типа Date :
     * date -  дата крайнего полета самолета
     * date2 -  дата выполнения работ через 30+6 дней стоянки самолета.
     * Далее происходит сравнение этих двух дат, что было позже полет или выполнены работы и самолет после этого не летал
     * Если первое условие  = true, к дате крайнего полета прибавляется 37 дней и высчитывается разница между этой датой
     * и датой в настоящем времени.
     * Если позже была дата выполнения работ через 30+6 дней стоянки, тогда второе условие = true и аналогичные действия выполняются
     * с этой датой
     */
    public static long calculateDateInDays(Date date, Date date2) {
        Date dateAfter1 = new Date(date.getTime());
        Date dateAfter2 = new Date(date2.getTime());
        Date dateNow = new Date();
        Calendar c = Calendar.getInstance();
        if (dateAfter1.getTime() > dateAfter2.getTime()) {
            c.setTime(dateAfter1);
            c.add(Calendar.DATE, 37);
            dateAfter1 = c.getTime();
            return dateAfter1.getTime() - dateNow.getTime();
        } else {
            c.setTime(dateAfter2);
            c.add(Calendar.DATE, 37);
            dateAfter2 = c.getTime();
            return dateAfter2.getTime() - dateNow.getTime();
        }
    }
}
