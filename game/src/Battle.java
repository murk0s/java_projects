public class Battle {
    public void figth(Person person1, Person person2, FightCallback fightCallback){

        //Ходы будут идти в отдельном потоке
        Runnable runnable = () -> {
            //Сюда будем записывать, какой сейчас ход по счету
            int turn = 1;
            //Когда бой будет закончен
            boolean isFightEnded = false;
            System.out.println("Бой начинается...");
            //чтобы игрок успел прочитать, кто против него играет
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (!isFightEnded) {
                System.out.println("----Ход: " + turn + "----");
                //Воины бьют по очереди, поэтому здесь мы описываем логику смены сторон
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(person1, person2, fightCallback);
                } else {
                    isFightEnded = makeHit(person2, person1, fightCallback);
                }
                try {
                    //Чтобы бой не проходил за секунду, сделаем имитацию работы, как если бы
                    //у нас была анимация
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //Запускаем новый поток
        Thread thread = new Thread(runnable);
        thread.start();
    }
    //Метод для совершения удара
    private Boolean makeHit(Person defender, Person attacker, FightCallback fightCallback) {
        //Получаем силу удара
        int hit = attacker.attack();
        //Отнимаем количество урона из здоровья защищающегося
        int defenderHealth = Math.max(defender.getHealth() - hit,0);
        //Если атака прошла, выводим в консоль сообщение об этом
        if (hit > 0) {
            //System.out.println(String.format("%s Нанес удар в %d единиц!", attacker.getName(), hit));
            System.out.println(String.format("У %s осталось %d единиц здоровья...", defender.getName(), defenderHealth));
        }
//        else {
//            //Если атакующий промахнулся (то есть урон не 0), выводим это сообщение
//            System.out.println(String.format("%s промахнулся!", attacker.getName()));
//        }
        if (defenderHealth <= 0 && defender instanceof Player) {
            //Если здоровье меньше 0 и если защищающейся был героем, то игра заканчивается
            System.out.println("Вас убили");
            defender.setHealth(0);
            //Вызываем коллбэк, что мы проиграли
            fightCallback.fightLost();
            return true;
        } else if(defenderHealth <= 0) {
            //Если здоровья больше нет и защищающийся – это монстр, то мы забираем от монстра его опыт и золото
            System.out.println(String.format("Враг повержен! Вы получаете %d опыта и %d золота", defender.getExperience(), defender.getGold()));
            attacker.setExperience(attacker.getExperience() + defender.getExperience());
            attacker.setGold(attacker.getGold() + defender.getGold());
            //вызываем коллбэк, что мы победили
            fightCallback.fightWin();
            return true;
        } else {
            //если защищающийся не повержен, то мы устанавливаем ему новый уровень здоровья
            defender.setHealth(defenderHealth);
            return false;
        }
    }
}
