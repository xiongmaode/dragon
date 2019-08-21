package com.example.dragon.study.arraylist;


/**
 * @ClassNAME ArrayList
 * @Description 自己实现一个ArrayList
 * @Author XiongMao
 * @Date 2019-7-25
 */
public class ArrayList<T> {
    private Object[] elementDate;
    private int size = 0;
    private static final int MAX_VALUE = 10;

    public int size() {
        return size;
    }

    public ArrayList() {
        this.elementDate = new Object[]{};
    }

    public boolean isEmpty() {
        if (size > 0)
            return false;
        return true;
    }

    public boolean add(T e) {
        //判断是否需要给数组扩容
        if (size > 0) {
            //如果大于0且大于等于十的时候判断能整除10才扩容，节省性能
            if (size >= MAX_VALUE) {
                if (size % MAX_VALUE == 0) {
                    T[] newData = (T[]) new Object[size + MAX_VALUE];
                    System.arraycopy(elementDate, 0, newData, 0, size);
                    elementDate = newData;
                }
            }
        } else if (size < 1) { //小于1说明第一次添加，需要增加大小
            //一次给newData十个大小
            T[] newData = (T[]) new Object[MAX_VALUE];
            //System.arraycopy可以copy数组，从elementDate第0个开始，复制给newData的第0个，一共复制size个数
            System.arraycopy(elementDate, 0, newData, 0, size);
            elementDate = newData;
        }
        elementDate[size++] = e;
        return false;
    }

    public boolean remove(Object o) {
        //TODO 未考虑性能与空值的情况，可添加删除最后一位直接置空，判断空的情况
        for (int i = 0; i < elementDate.length; i++) {
            if (elementDate[i].equals(o)) {
                //数组拷贝，删除位置的地方后边值前移
                System.arraycopy(elementDate, i + 1, elementDate, i, size - i);
                //置空最后一位达到删除的效果
                elementDate[--size] = null;
                return true;
            }
        }
        return false;
    }

    public T get(int index) {
        checkNull(index);
        return (T) elementDate[index];
    }

    private void checkNull(int index) {
        if (index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
}
