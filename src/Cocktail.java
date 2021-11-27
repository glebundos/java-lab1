public class Cocktail extends Food
{
    public Cocktail(String drink, String fruit)                 // конструктор инициализации
    {
        super("Коктейль");
        par1 = drink;
        par2 = fruit;
    }
    public boolean equals(Object arg0)  // переопределние метода сравнения
    {
        if (super.equals(arg0))
        {
            if (!(arg0 instanceof Cocktail)) return false;
            if (!(par1.equals(((Cocktail)arg0).par1))) return false;
            return par2.equals(((Cocktail)arg0).par2);
        } else
            return false;
    }
    public Double calculateCalories()       // реализация метода подсчета калорий
    {
        calories=0.0;
        if(!par1.equals("mojito") && !par1.equals("tonic") && !par1.equals("coke"))
            return calories;
        if(!par2.equals("orange") && !par2.equals("lemon") && !par2.equals("lime"))
            return calories;
        if (par1.equals("mojito"))
            calories += 10.0;
        else if (par1.equals("tonic"))
            calories += 15.0;
        else calories += 20.0;

        if (par2.equals("orange"))
            calories += 5.0;
        else if (par2.equals("lemon"))
            calories += 7.0;
        else calories += 9.0;
        return calories;
    }
    public String getDrink()         // возвращает коктейль
    {
        return par1;
    }
    public String getFruit()           // возвращает фрукт
    {
        return par2;
    }
    public String toString()       // переопределение метода преобразования в строку
    {
        return super.toString() + " " + par1 + " c " + par2;
    }
    public void consume()            // реализация метода consume (что произошло с объектом)
    {
        System.out.println(this + " выпит");
    }

}