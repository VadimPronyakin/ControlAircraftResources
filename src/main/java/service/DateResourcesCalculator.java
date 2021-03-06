package service;

import java.util.Calendar;
import java.util.Date;

public class DateResourcesCalculator {
    /**
     * Метод принимает в себя переменную типа Date, содержащую  дату, на которой были выполнены работы
     * через 6+1 месяцев эксплуатации самолета, прибавляет к этой дате 7 месяцев и высчитывает разницу между
     * этой датой и датой в настоящем времени
     */

    public static long calculateDateInMonth(Date date) {
        Date dateAfterWork = new Date(date.getTime());
        Date dateNow = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateAfterWork);
        calendar.add(Calendar.MONTH, 7);
        dateAfterWork = calendar.getTime();
        return (dateAfterWork.getTime() + 86_400_000) - dateNow.getTime();
    }

    /**
     * Метод принимает в себя две переменных типа Date :
     * LastFlight -  дата крайнего полета самолета
     * workDate -  дата выполнения работ через 30+6 дней стоянки самолета.
     * Далее происходит сравнение этих двух дат, что было позже полет или выполнены работы и самолет после этого не летал
     * Если первое условие  = true, к дате крайнего полета прибавляется 37 дней и высчитывается разница между этой датой
     * и датой в настоящем времени.
     * Если позже была дата выполнения работ через 30+6 дней стоянки, тогда второе условие = true и аналогичные действия выполняются
     * с этой датой
     */
    public static long calculateDateInDays(Date lastFlight, Date workDate) {
        Date dateAfterFlight = new Date(lastFlight.getTime());
        Date dateAfterWork = new Date(workDate.getTime());
        Date dateNow = new Date();
        Calendar c = Calendar.getInstance();
        if (dateAfterFlight.getTime() > dateAfterWork.getTime()) {
            c.setTime(dateAfterFlight);
            c.add(Calendar.DATE, 37);
            dateAfterFlight = c.getTime();
            return dateAfterFlight.getTime() - dateNow.getTime();
        } else {
            c.setTime(dateAfterWork);
            c.add(Calendar.DATE, 37);
            dateAfterWork = c.getTime();
            return dateAfterWork.getTime() - dateNow.getTime();
        }
    }
}
