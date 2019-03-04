package edu.chl.hajo.td.model.creeps;

public interface ICreep{
    double getSpeed();
    int getHp();
    int getMaxHp();
    int getKillPoints();
    int getDamage();

    boolean dealDamage(double damageDealt);
    void move();
    AbstractCreep copyOf();
}
