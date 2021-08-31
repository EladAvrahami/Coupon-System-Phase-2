package com.jb.Groupon.Enums;

/**
 * we will use this e-num to convert category string type that user enter in program to num type on DB
 */
public enum Category {

    attractions(1),
    clothes(2),
    electricity(3),
    flights(4),
    food(5),
    movies(6),
    resorts(7),
    restaurant(8),
    vacation(9);

    private int num;

    Category(int num) {
        this.num = num;
    }

    public int getText() {
        return this.num;
    }


    public static Category getCategoryStringFromInt(int num) {
        for (Category item : Category.values()) {
            if (item.num==num) {
                return item;
            }
        }
        return null;
    }

}
