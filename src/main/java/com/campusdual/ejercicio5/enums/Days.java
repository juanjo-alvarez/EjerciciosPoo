package com.campusdual.ejercicio5.enums;

public enum Days {
    L(1,"Lunes"),
    M(2,"Martes"),
    X(3,"Míercoles"),
    J(4,"Jueves"),
    V(5,"Viernes"),
    S(6,"Sábado"),
    D(7,"Domingo");

    private Integer position;
    private String name;

    Days(Integer position, String name) {
        this.position = position;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Integer getPosition(){
        return position;
    }

    public static Days getDayFromPosition(Integer position){
        for(Days day : values()){
            if(day.getPosition()==position){
                return day;
            }
        }
        return null;
    }
}
