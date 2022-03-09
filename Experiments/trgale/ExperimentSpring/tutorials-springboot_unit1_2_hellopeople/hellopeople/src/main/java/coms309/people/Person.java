package coms309.people;


/**
 * Provides the Definition/Structure for the people row
 *
 * @author Vivek Bengre
 */

public class Person {

    private String Name;
    
    private int Age;

    private String collarColor;

    private String type;

    public Person(){
        
    }

    public Person(String Name, int Age, String collarColor, String type){
        this.Name = Name;
        this.Age = Age;
        this.collarColor = collarColor;
        this.type = type;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String newName) {
        this.Name = newName;
    }

    public int getAge() {
        return this.Age;
    }

    public void setAge(int newAge) {
        this.Age = newAge;
    }

    public String getCollarColor() {
        return this.collarColor;
    }

    public void setCollarColor(String newColor) {
        this.collarColor = newColor;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return Name + " " 
               + Age + " "
               + collarColor + " "
               + type;
    }
}
