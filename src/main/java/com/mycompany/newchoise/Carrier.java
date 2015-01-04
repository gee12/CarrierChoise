package com.mycompany.newchoise;

/**
 * Перевозчик
 * @author Иван
 */
public class Carrier {
    
    protected String name;
    protected double maxWeight;     // общий объем услуг (тонн)
    protected double price;            // стоимость услуг
    
//    protected Stock stock;          // подвижной состав
//    protected int allStock;         // общее кол-во подвижного состава
//    protected boolean isDocExist;    // наличие док-тов
//    
//    protected boolean isInsurance;      // возможность страхования груза
//    protected boolean isStorage;        // возможность хранения груза
//    protected boolean loadingUnloading; // Предоставление погрузочно – разгрузочных работ
//    protected boolean isGPS;            // наличие GPS связи
//    protected boolean isOperativeChange;    // возм.оперативного изменения условий сделки

    public Carrier(String name, double maxWeight, double price) {
        this.name = name;
        this.maxWeight = maxWeight;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public double getPrice() {
        return price;
    }
    
}
