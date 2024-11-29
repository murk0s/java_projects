public abstract class Person implements Fighter {
    String name;
    int health, force, agility, //ловкость
            experience, //опыт
            gold, level;

    public Person(String name, int health, int force, int agility, int experience, int gold, int level) {
        this.name = name;
        this.health = health;
        this.force = force;
        this.agility = agility;
        this.experience = experience;
        this.gold = gold;
        this.level = level;
    }


    @Override
    public int attack() {
        if (3*agility > getRandomValue()) {
            int res = force*(getRandomBool()?2:1);
            System.out.printf("%s нанес удар силой %d\n", name, res);
            return res;
        }
        else {
            System.out.printf("%s промахнулся\n", name);
            return 0;
        }
    }

    private int getRandomValue(){
        return (int) Math.random()*100;
    }

    private boolean getRandomBool(){
        if (Math.random()>0.8) return true;
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public String toString() {
        return name + '\'' +
                ", здоровье=" + health;
    }

}
