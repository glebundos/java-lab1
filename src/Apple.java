public class Apple extends Food
{
    public Apple(String size)     // конструктор инициализации
    {
        super("Яблоко");               // вызывает конструктор базового класса
        par1 = size;
    }
    public boolean equals(Object arg0)  // переопределние метода сравнения
    {
        if (super.equals(arg0))
        {
            if (!(arg0 instanceof Apple)) return false;
            return par1.equals(((Apple)arg0).par1);
        } else
            return false;
    }
    public Double calculateCalories()       // реализация метода подсчета калорий
    {
        if(par1.equals("small"))
        {
            calories = 15.0;
        }
        else if(par1.equals("medium"))
        {
            calories = 20.0;
        }
        else if(par1.equals("big"))
        {
            calories = 25.0;
        }
        else return 0.0;
        return calories;
    }
    public String getSize()   // возвращает размер яблока
    {
        return par1;
    }

    public void setSize(String size)  // изменение размера яблока
    {
        this.par1 = size;
    }

    public void consume()           // реализация метода consume (что произошло с объектом)
    {
        System.out.println(this + " съедено");
    }

    public String toString()       // переопределение метода преобразования в строку
    {
        return super.toString() + " размера '" + par1.toUpperCase() + "'";
    }
}
