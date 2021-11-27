import java.lang.reflect.Constructor;
import java.util.*;

public class MainApplication
{

    public static void main(String[] args) throws Exception
    {
        Food[] breakfast = new Food[20];
        int i = 0;
        boolean var1, var2;         // случаи для спец параметров, начинающихся с дефиса
        var1 = var2 = false;

        for (String arg : args)         // проходим по передаваемым параметрам
        {
            String[] parts = arg.split(("/")); //раздлеяем параметры ком строки
            try
            {
                Class myClass = Class.forName(parts[0]);
                if (parts.length == 1) {                               //если передается 1 параметр (имя класса)
                    Constructor constructor = myClass.getConstructor();
                    breakfast[i] = (Food) constructor.newInstance();
                    i++;
                } else if (parts.length == 2) {                      //если передается 2 параметра(имя класса + поле)
                    Constructor constructor = myClass.getConstructor(String.class);
                    breakfast[i] = (Food) constructor.newInstance(parts[1]);
                    i++;
                } else if (parts.length == 3) {                           //если передается 3 параметра(имя класса + 2 поля)
                    Constructor constructor = myClass.getConstructor(String.class, String.class);
                    breakfast[i] = (Food) constructor.newInstance(parts[1], parts[2]);
                    i++;
                }
            }
            catch (ClassNotFoundException e)     // исключение, если введенный класс не найден
            {
                switch (parts[0])
                {
                    case "-sort":            // если паратметр -sort, значит нужно будет отсортировать продукты завтрака
                        var1 = true;
                        break;
                    case "-calories":       // если паратметр -calories, значит нужно будет посчитать калрийность завтрака
                        var2 = true;
                        break;
                    default:
                        System.out.println("Класс " + parts[0] + " не найден.");
                        break;
                }

            }
            catch (NoSuchMethodException e) // исключение, если метод класса не найден (к примеру конструктор)
            {
                System.out.println("Метод класса " + parts[0] + " не был найден.");
            }
        }

        System.out.println("Завтрак: "); //выводим завтрак таким,каким он был первоначально
        for (Food item : breakfast) {
            if (item != null)
            {
                if (item.calculateCalories()==0.0)  // если ввели продукт, который не предусмотрен в программе
                {
                    System.out.print("Такой продукт не предусмотрен (" + item.name);
                    if(item.par1!=null)
                        System.out.print(", " + item.par1);
                    if(item.par2!=null)
                        System.out.print(", " + item.par2);
                    System.out.println(")");
                    continue;
                }
                item.consume();
                System.out.println(" " + item.calculateCalories());
            }
            else
            {
                break;
            }
        }

        if (var1)
        {                //случай "ClassNotFoundException", когда мы задаем параметр -sort
            Arrays.sort(breakfast, new Comparator() {
                public int compare(Object o1, Object o2)
                {
                    if (o1 == null || ((Food)o1).calculateCalories() > ((Food)o2).calculateCalories())
                        return 1;
                    if (o2 == null || ((Food)o1).calculateCalories() < ((Food)o2).calculateCalories())
                        return -1;
                    else return 0;
                }
            });

            System.out.println("Завтрак (отсортированный вариант):");
            for (Food item : breakfast)
            {
                if (item != null)
                {
                    if (item.calculateCalories()==0.0)
                        continue;
                    item.consume();
                    System.out.println(" " + item.calculateCalories());
                }
                else
                    break;
            }
        }
        if (var2)
        {                            //случай "ClassNotFoundException", когда мы задаем параметр -calories
            double CaloriesCounter = 0.0;
            for (Food item : breakfast)
            {
                if (item != null)
                    CaloriesCounter += item.calculateCalories();
                else
                    break;
            }
            System.out.println("Общее количество калорий: " + CaloriesCounter);

        }
        int eaten_a1, eaten_a2, eaten_a3, eaten_c, eaten_cmo, eaten_cmle, eaten_cmli, eaten_cto, eaten_ctle, eaten_ctli, eaten_cco, eaten_ccle, eaten_ccli;
        eaten_a1 = eaten_a2 = eaten_a3 = eaten_c = eaten_cmo = eaten_cmle = eaten_cmli = eaten_cto = eaten_ctle = eaten_ctli = eaten_cco = eaten_ccle = eaten_ccli = 0;
        for(Food item: breakfast)                            // считаем, сколько чего было съедено на завтрак
        {
            if(item == null)
                break;
            if(item.name.equals("Яблоко"))
            {
                if(item.par1.equals("small"))
                    eaten_a1++;
                else if(item.par1.equals("medium"))
                    eaten_a2++;
                else if(item.par1.equals("big"))
                    eaten_a3++;
            }
            if(item.name.equals("Сыр"))
                eaten_c++;
            if(item.name.equals("Коктейль"))
            {
                if (item.par1.equals("mojito"))
                {
                    if (item.par2.equals("orange")) eaten_cmo++;
                    if (item.par2.equals("lemon")) eaten_cmle++;
                    if (item.par2.equals("lime")) eaten_cmli++;
                }

                if (item.par1.equals("tonic"))
                {
                    if (item.par2.equals("orange")) eaten_cto++;
                    if (item.par2.equals("lemon")) eaten_ctle++;
                    if (item.par2.equals("lime")) eaten_ctli++;
                }

                if (item.par1.equals("coke"))
                {
                    if (item.par2.equals("orange")) eaten_cmo++;
                    if (item.par2.equals("lemon")) eaten_cmle++;
                    if (item.par2.equals("lime")) eaten_cmli++;
                }
            }
        }
        System.out.println("На завтрак съедено и выпито:");
        System.out.println(" маленьких яблок - " + eaten_a1 + ", средних яблок - " + eaten_a2 + ", больших яблок - " + eaten_a3);
        System.out.println(" кусочков сыра - " + eaten_c);

        System.out.println(" стаканов мохито с апельсином- " + eaten_cmo);
        System.out.println(" стаканов мохито с лимоном- " + eaten_cmle);
        System.out.println(" стаканов мохито с лаймом- " + eaten_cmli);

        System.out.println(" стаканов тоникв с апельсином- " + eaten_cto);
        System.out.println(" стаканов тоника с лимоном- " + eaten_ctle);
        System.out.println(" стаканов тоника с лаймом- " + eaten_ctli);

        System.out.println(" стаканов колы с апельсином- " + eaten_cco);
        System.out.println(" стаканов колы с лимоном- " + eaten_ccle);
        System.out.println(" стаканов колы с лаймом- " + eaten_ccli);
    }
}