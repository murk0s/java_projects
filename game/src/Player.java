public class Player extends Person{
    public Player(String name) {
        super(name, 100, 20, 5, 0, 0, 1);
        System.out.printf("Добро пожаловать, %s!!!"+getDecription(),
                name);
    }

    public int getLevel() {
        return level;
    }

    public String getDecription(){
        return "У Вас "+health+" здоровья, "+force+" силы, "+agility+" ловкости, "+experience+" опыта, "+gold+" золота. ";
    }

    @Override
    public void setExperience(int experience) {
        super.setExperience(experience);
        int level0 = level;
        level = (experience/10)+1;
        if (level>level0) {
            setHealth(Math.max(getHealth(),100)); //восстанавливаем здоровье на новом уровне
            force = 15+5*level; //увеличиваем силу
            System.out.println("Вы перешли на уровень "+level);
        }
    }

    public void buyHealth(){
        if (getGold()>10) {
            setHealth(Math.min(getHealth()+10, 100));
            setGold(getGold()-10);
            System.out.println(getDecription());
        }
        else System.out.println("В мешке с золотом недостаточно средств");
    }

    public void buyAgility(){
        if (getGold()>10) {
            setAgility(getAgility()+5);
            setGold(getGold()-10);
            System.out.println(getDecription());
        }
        else System.out.println("В мешке с золотом недостаточно средств");
    }
}
