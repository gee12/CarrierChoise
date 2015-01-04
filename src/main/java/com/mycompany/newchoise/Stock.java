package com.mycompany.newchoise;

import java.util.List;

/**
 *
 * @author Иван
 */
public class Stock {
    
//    protected int outdoorBody;      // Открытый (бортовой) кузов
//    protected int tiltBody;         // Тентованный кузов
//    protected int insulatedBody;    // Изотермический кузов 
//    protected int refrigerator;     // Рефрижератор 
//    
    protected List<Car> cars;
    
    public int getCarsNum() {
        return cars.size();
    }
    
    public int getCarTypeNum(Car.Types type) {
        int res = 0;
        for (Car car : cars) {
            if (car.type == type) {
                res++;
            }
        }
        return res;
    }
    
    public int getAllWeight() {
        int res = 0;
        for (Car car : cars) {
            res += car.loadWeight;
        }
        return res;
    }
    
    public int getAllWeight(Car.Types type) {
        int res = 0;
        for (Car car : cars) {
            if (car.type == type) {
                res += car.loadWeight;
            }
        }
        return res;
    }

 }
