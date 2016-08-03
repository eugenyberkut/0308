package main;

import java.util.Objects;

/**
 * Created by Eugeny on 03.08.2016.
 */
public class Person {
    private String name;
    private int age;
    private double zp;
    private String city;
    private char sex;

    public Person(String name, int age, double zp, String city, char sex) {
        this.name = name;
        this.age = age;
        this.zp = zp;
        this.city = city;
        this.sex = sex;
    }

    public char getSex() {
        //System.out.println(">>> get sex " + sex);
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        //System.out.println(">>> get age " + age);
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getZp() {
        return zp;
    }

    public void setZp(double zp) {
        this.zp = zp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age &&
                Double.compare(person.zp, zp) == 0 &&
                sex == person.sex &&
                Objects.equals(name, person.name) &&
                Objects.equals(city, person.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, zp, city, sex);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", zp=" + zp +
                ", city='" + city + '\'' +
                ", sex=" + sex +
                '}';
    }
}
