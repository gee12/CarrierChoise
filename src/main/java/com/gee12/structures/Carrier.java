package com.gee12.structures;

/**
 * Перевозчик
 * @author Иван
 */
public class Carrier {
    
    protected String name;
    protected double capacity;
    protected int capacityRepeatNum;
    protected Matrix matrix;

//    protected double maxWeight;     // общий объем услуг (тонн)
//    protected double price;            // стоимость услуг
    
//    protected Stock stock;          // подвижной состав
//    protected int allStock;         // общее кол-во подвижного состава
//    protected boolean isDocExist;    // наличие док-тов
//    
//    protected boolean isInsurance;      // возможность страхования груза
//    protected boolean isStorage;        // возможность хранения груза
//    protected boolean loadingUnloading; // Предоставление погрузочно – разгрузочных работ
//    protected boolean isGPS;            // наличие GPS связи
//    protected boolean isOperativeChange;    // возм.оперативного изменения условий сделки

    public Carrier(String name, double capacity, int capacityRepeatNum, Matrix matrix) {
        this.name = name;
        this.capacity = capacity;
        this.capacityRepeatNum = capacityRepeatNum;
        this.matrix = matrix;
    }

    public String getName() {
        return name;
    }

    public double getCapacity() {
        return capacity;
    }

    public int getCapacityRepeatNum() {
        return capacityRepeatNum;
    }

    public Matrix getMatrix() {
        return matrix;
    }
    
//    public double getMaxWeight() {
//        return maxWeight;
//    }

//    public double getPrice() {
//        return price;
//    }
    
}
