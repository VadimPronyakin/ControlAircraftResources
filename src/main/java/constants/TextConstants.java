package constants;

public class TextConstants {
    public static final String ENGINEER_TEXT = "Не введены данные о инженерах(ак)." + "\n" +
            "Добавьте хотя бы одного инженера(ак)";
    public static final String AIRCRAFT_TEXT = "Не введены данные о самолетах." +  "\n" +
            "Добавьте хотя бы один самолет";
    public static final String ENGINE_TEXT = "Не введены данные о двигателях." + "\n" +
            "Добавьте хотя бы один двигатель";
    public static final String KSA_TEXT = "Не введены данные о КСА." + "\n" +
            "Добавьте хотя бы одну КСА";
    public static final String MAIN_BREAK_TEXT = "Не введены данные об основных тормозах." + "\n" +
            "Добавьте хотя бы один основной тормоз";
    public static final String FRONT_BREAK_TEXT = "Не введены данные о передних тормозах." + "\n" +
            "Добавьте хотя бы один передний тормоз";
    public static final String MAIN_WHEEL_TEXT = "Не введены данные о колесах основной стойки шасси." + "\n" +
            "                       Добавьте хотя бы одно колесо";
    public static final String FRONT_WHEEL_TEXT = "Не введены данные о колесах передней стокий шасси." + "\n" +
            "                       Добавьте хотя бы одно колесо";
    public static final String CYLINDER_TEXT = "Не введены данные о цилиндрах подкоса." + "\n" +
            "         Добавьте хотя бы один цилиндр";
    public static final String AIRCRAFT_TO_ENGINEER = "За данным инженером(АК) нет закрпеленных самолетов";
    public static final String NOT_INSTALLED_ON_AIRCRAFT = "Не установлено на самолет";
    public static final String THE_FIELDS_ARE_REQUIRED = "Поля, помеченные звездочкой, обязательны для заполнения" + "\n"
            + "Поле - номер приказа заполняется только цифрами";
    public static final String DUPLICATION_ENGINE = "Двигатель с таким серийным номером уже существует";
    public static final String DUPLICATION_KSA = "КСА с таким серийным номером уже существует";
    public static final String DUPLICATION_PLANER = "Планер с таким серийным номером уже существует";
    public static final String DUPLICATION_MAIN_BREAK = "Тормоз основного колеса с таким серийным номером уже существует";
    public static final String DUPLICATION_MAIN_WHEEL = "Основное колесо с таким серийным номером уже существует";
    public static final String DUPLICATION_FRONT_WHEEL = "Переднее колесо с таким серийным номером уже существует";
    public static final String DUPLICATION_FRONT_BREAK = "Тормоз преднего колеса с таким серийным номером уже существует";
    public static final String DUPLICATION_CYLINDER = "Цилиндр подкоса с таким серийным номером уже существует";
    public static final String DUPLICATION_ENGINEER = "Такой инженер(ак) уже существует";
    public static final String DUPLICATION_AIRCRAFT = "Самолет с таким бортовым номером уже существует";
    public static final String ENGINE_NOT_INSTALLED = "Двигатель не установлен на самолет";
    public static final String KSA_NOT_INSTALLED = "КСА не установлена на самолет";
    public static final String FLIGHT_WITHOUT_POWER_PLANT = "На самолете не укомплектована силовая установка," +
            "вылет невозможен";
    public static final String TEST_WITHOUT_POWER_PLANT = "На самолете не укомплектована силовая установка," +
            "опробование невозможно";
    public static final String ENGINE_INSTALLED = "Двигатель установлен на самолет ";
    public static final String ENGINE_DELETE = ". Прежде чем удалить, открепите его от этого самолета";
    public static final String KSA_INSTALLED = "КСА установлена на самолет ";
    public static final String KSA_DELETE = ". Прежде чем удалить, открепите её от этого самолета";
    public static final String CYLINDER_INSTALLED = "Цилиндр подкоса установлен на самолет ";
    public static final String CYLINDER_DELETE = ". Прежде чем удалить, замените его на другой в этом самолете";
    public static final String FRONT_BREAK_INSTALLED = "Передний тормоз установлен на самолет ";
    public static final String FRONT_BREAK_DELETE = ". Прежде чем удалить, замените его на другой в этом самолете";
    public static final String FRONT_WHEEL_INSTALLED = "Переднее колесо установлено на самолет ";
    public static final String FRONT_WHEEL_DELETE = ". Прежде чем удалить, замените его на другое в этом самолете";
    public static final String MAIN_BREAK_INSTALLED = "Основной тормоз установлен на самолет ";
    public static final String MAIN_BREAK_DELETE = ". Прежде чем удалить, замените его на другой в этом самолете";
    public static final String MAIN_WHEEL_INSTALLED = "Основное колесо установлено на самолет ";
    public static final String MAIN_WHEEL_DELETE = ". Прежде чем удалить, замените его на другое в этом самолете";
    public static final String ENGINEER_ASSIGNED = "За выбранным инженером(ак) закреплены самолеты." + "\n" +
            "Прежде чем удалить его из списка инженеров, перезакрепите его самолеты за другими инженерами(ак)";
    public static final String ENGINE_DUPLICATE = "Выберите разные двигатели для самолета";
    public static final String MAIN_BREAK_DUPLICATE = "Выберите разные основные тормоза для самолета";
    public static final String MAIN_WHEEL_DUPLICATE = "Выберите разные основные колеса для самолета";
    public static final String FRONT_BREAK_DUPLICATE = "Выберите разные передние тормоза для самолета";
    public static final String FRONT_WHEEL_DUPLICATE = "Выберите разные передние колеса для самолета";
    public static final String CYLINDER_DUPLICATE = "Выберите разные цилиндры подкоса для самолета";
    public static final String MINUTES_SIZE = "Проверь вводимы данные в полях, содержащих минуты," + "\n"
            + "Поле не может принимать в себя число больше 59-ти" + "\n"
            + "Поле - номер двигателя не должно быть пустым";
    public static final String ONLY_INT = "Убедись, что все поля заполнены." + "\n"
            + "Все поля кроме серийного номера заполняются только цифрами.";
    public static final String FILLING_OPERATION_TIME = "Убедись, что все поля заполнены."+ "\n"
            + "Заполнение полей допускается только цифрами";
    public static final String ONLY_INT_PLANER = "Убедись, что все поля заполнены." + "\n"
            + "Все поля заполняются только цифрами.";
    public static final String MAKE_WORKS_PLANER = "Убедись, что выбран вид работ и дата выполнения(если требуется)";
    public static final String MAKE_WORKS_AGGREGATES = "Убедись, что выбран вид работ";
    public static final String CHANGE_ENGINEER_IN_AIRCRAFT = "При перезакреплении ВС за другим ниженером(ак)" +"\n"
            + " изменяется номер и дата приказа.";
    public static final String FLIGHT_DAY = "| Летная смена: ";
    public static final String OPERATING_FLY = "наработка воздух = ";
    public static final String OPERATING_FLY_GROUND = " наработка воздух+20% = ";
    public static final String QUANTITY_LANDINGS = " количество посадок = ";
    public static final String TEST_START = "| Газовка:";
    public static final String LEFT_OPERATING = " левый 20% земли = ";
    public static final String RIGHT_OPERATING = " правый 20% земли = ";
    public static final String QUANTITY_STARTING_LEFT = " запусков левого = ";
    public static final String QUANTITY_STARTING_RIGHT = " запусков правого = ";
    public static final String ADD_OPERATING = "Добавь наработку";
    public static final String REPAIR_COMPLETED = "РЕМОНТ ВЫПОЛНЕН";
    public static final String DISK_REPLACED = "ДИСК ЗАМЕНЕН";
    public static final String NON_OPERATING_IN_HISTORY = "Добавьте наработку,чтобы она отображалась в истории";



}
