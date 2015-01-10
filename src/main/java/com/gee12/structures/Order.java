package com.gee12.structures;

/**
 * Заказ
 * @author Иван
 */
public class Order {
    
    // плановые (необходимые) показатели
    protected double plannedVolume;         // плановый объем груза (тонн)
    protected double plannedHours;          // плановое время доставки (часов)
    protected int plannedTripNum;          // плановое количество ездок
    
    // во время оформления сделки
    protected double actualVolume;          // фактический объем груза (тонн) в заявке
    protected int requiredChangesNum;      // число требуемых изменений
    
    // после совершения сделки
    protected int correctCarsNum;   // кол-во авто, соответствующих типу и количеству груза,
                                    // прибывших под погрузку
    protected double factVolume;        // объем доставленного груза (тонн)
    protected int unspoiltNum;      // кол-во неиспорченного груза
    protected double factHours;         // фактическое время доставки (часов)
    protected int factTripNum;          // фактическое количество ездок
    protected int factChangesNum;      // число выполненных изменений
        
}
