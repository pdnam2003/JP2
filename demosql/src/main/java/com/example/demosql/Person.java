package com.example.demosql;

public class Person {
    private  String last;
    private  String first;
    private String add;

    public Person(String last, String first, String add) {
        this.last = last;
        this.first = first;
        this.add = add;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    @Override
    public String toString() {
        return "Person{" +
                "last='" + last + '\'' +
                ", first='" + first + '\'' +
                ", add='" + add + '\'' +
                '}';
    }
}
