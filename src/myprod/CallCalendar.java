package myprod;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class CallCalendar {
	// ------------------ поля
	
	// ------------------ конструктор
	
	// ------------------ методы
	public static void getCalendar(int dMonth)
	{
		// некорректный ввод, выходим из программы
		if((dMonth <= 0) || (dMonth >12))
		{
			System.out.println("Incorrect input! Input range 1-12!");
			System.exit(0);
		}
		else
		{
			startWorkWthCalendar(dMonth);
			System.exit(0);
		}
	}
	
	private static void startWorkWthCalendar(int dMonth)
	{
		// строим объект LocalDate
		LocalDate dateNow = LocalDate.now();
		// текущий год
		int dYearNow = dateNow.getYear();
		// текущий месяц
//		int dMonthNow = dateNow.getMonthValue();
		int dMonthNow = dMonth;
		// текущий день (для календаря берём равным 1)
		int dDayNow = 1;
		// переинициализируем объект LocalDate параметрами текущих значений
		dateNow = dateNow.of(dYearNow, dMonthNow, dDayNow);
		
		// теперь необходимо выяснить каким днём недели будет 1ое число месяца
		// напомню, текущим днём недели выбрано 1-ое число (было выше)
		DayOfWeek NameDay = dateNow.getDayOfWeek();
		int dNumberOfDay = NameDay.getValue();
//		// выводим эту инфу для проверки
//		System.out.printf("%d\n", dNumberOfDay);
		
		// массив с календарными числами
		ArrayList<Integer> dCalendarArrayList = new ArrayList<>();
		// счётчик индекса вынесем за пределы циклов
		int i;
		// заполняем 0-ми позиции вначале месяца
		// те, что идут от первого понедельника до 1-го числа месяца
		for(i = 0; i < (dNumberOfDay - 1); i++)	
		{
			// заполняем 0-ми
			dCalendarArrayList.add(i, 0);
		}
		// проходим циклом, заполняя следующие элементы массива
		while(dateNow.getMonthValue() == dMonthNow)	
		{
			
			dCalendarArrayList.add(i,dateNow.getDayOfMonth());
			i++;
			// перемещаемся по объекту на 1 день вперёд до тех пор,
			// пока название месяца не изменится -- это будет сигналом
			// для прекращения данного цикла
			dateNow = dateNow.plusDays(1);			
		}
		
		// массив чисел месяца заполнен, можно выполнять форматированный вывод
		// вывод названий дней недели
		// создаём массив типа DayOfWeek, методом values() заполняем его 
		// названиями дней недели
		DayOfWeek[] dow = DayOfWeek.values();
		// затем необходимо вывести все названия в одну строку,
		// параметр форматирования выбран "SHORT" - 3 символа для каждого дня
		// "mon", "tue" ...
		for(DayOfWeek d : dow)
		{
			System.out.printf("  %s", d.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
//			System.out.printf("  %s", d.getDisplayName(TextStyle.SHORT, Locale.UK));
		}
		System.out.println();
		
		// вывод чисел месяца
		// проходим по всем членам массива
		// служебное, число дней в неделе, используется в счётчике (ниже)
		int dNumbersInWeek = 7;
		
		for(int d1 : dCalendarArrayList)
		{
			// проверяем счётчик "использованных" дней недели
			if(dNumbersInWeek > 0)
			{
				// если элемент массива = 0, выводим 5 пробелов
				if(d1 == 0)
				{
					System.out.printf("     ");
					dNumbersInWeek--;
				}
				// ..в противном сл., выводим число в поле из 5 символов ("   12")
				else
				{
					System.out.printf("%5d", d1);
					dNumbersInWeek--;
				}
			}
			// достигнув "0" по счётчику, "прыгаем" на новую строку,
			// выводим имеющийся элемент массива и возобновляем счётчик, 
			// но уже с 6-ти, т.к. 7-ая позиция нами уже занята
			else
			{
				System.out.println();
				System.out.printf("%5d", d1);
				dNumbersInWeek = 6;
			}
		}
		System.out.println();
	}	
}
