package com.campusdual.ejercicio5;

public class Food {
    private String name;
    private Integer carbos;
    private Integer fats;
    private Integer proteins;

    public Food(String name){
        this.carbos=0;
        this.fats=0;
        this.proteins=0;
        this.name=name;
    }

    public Food(String name, Integer carbos, Integer fats, Integer proteins){
        this.name=name;
        this.carbos=carbos;
        this.fats=fats;
        this.proteins=proteins;
    }

    public Integer getCalories(Integer weight){
        //1 gramo de proteína nos da 4 calorías.
        // 1 gramo de carbohidratos nos da 4 calorías.
        // 1 gramo de grasa nos da 9 calorías
        return(((carbos*4)+(fats*9)+(proteins*4))*weight/100);
    }
    public Integer getCarbos() {
        return carbos;
    }

    public void setCarbos(Integer carbos) {
        this.carbos = carbos;
    }

    public Integer getFats() {
        return fats;
    }

    public void setFats(Integer fats) {
        this.fats = fats;
    }

    public Integer getProteins() {
        return proteins;
    }

    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
