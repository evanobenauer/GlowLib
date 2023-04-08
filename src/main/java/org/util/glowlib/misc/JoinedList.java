package org.util.glowlib.misc;

import java.util.ArrayList;

/**
 * The joined list class is a simple array list container to make it easier to have 2 parallel columns in a list. The goal
 * of this class is to make it easier for the programmer to be able to have a value and its corresponding value and be
 * able to work with them to solve problems.
 * @param <T>
 * @param <J>
 */
public class JoinedList<T,J> {

    //TODO: Delete this, it is useless

    private final ArrayList<Object[]> list;


    public JoinedList() {
        list = new ArrayList<>();
    }

    public JoinedList(JoinedList values) {
        this();
        getList().addAll(values.getList());
    }


    public void add(T element1, J element2) {
        list.add(new Object[]{element1, element2});
    }


    public T getA(int index) {
        return (T) list.get(index)[0];
    }

    public J getB(int index) {
        return (J) list.get(index)[1];
    }


    public void setA(int index, T value) {
        list.get(index)[0] = value;
    }

    public void setB(int index, T value) {
        list.get(index)[1] = value;
    }


    public int getSize() {
        return getList().size();
    }


    private ArrayList<Object[]> getList() {
        return list;
    }


    @Override
    public String toString() {
        return getList().toString();
    }

}
