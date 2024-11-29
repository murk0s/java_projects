public class Skeleton extends Person{
    public Skeleton(int level) {
        super("",
                100,
                (int)(Math.random()*15+5*(level-1)+1),
                (int)(Math.random()*(10+5*level)+1),
                (int)(Math.random()*10+1),
                (int)(Math.random()*15+1),
                level);
        name = generateName();
        System.out.println("Против Вас "+getDescription());
    }

    private String generateName(){ //функция генерации имения для скелета
        String names[] = {"Talus Terror",
                "Scapula Slasher",
                "Vomer Vanquisher",
                "Bleaksoul",
                "Skele Spear",
                "Gravegrin",
                "Skullduggery",
                "Death Drift",
                "Bonegrinder",
                "Skele Strike",
                "Clatterin' Claire"};

        return "Skeleton "+names[(int)(Math.random()*10)];
    }

    public String getDescription(){
        return name+" (сила "+force+", ловкость "+agility+", опыт "+experience+", золото "+gold+")";
    }
}
