package com.mycompany.newchoise;

/**
 *
 * @author Иван
 */
public class Car {
    
    public enum Types {
        OutdoorBody,        // Открытый (бортовой) кузов
        TiltBody,           // Тентованный кузов
        InsulatedBody,      // Изотермический кузов 
        Refrigerator        // Рефрижератор 
    }
    
    protected Types type;
    protected double loadWeight;    // грузоподъемность
    protected double mileage;       // пробег
    protected double lifetime;       // срок эксплуатации
    
}
