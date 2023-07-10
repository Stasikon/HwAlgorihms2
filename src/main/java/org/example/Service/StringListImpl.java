package org.example.Service;

import org.example.Example.ArrayOutOfBoundsException;
import org.example.Example.ElementNotFoundException;
import org.example.Example.ValueCanNotBeEmptyException;

public class StringListImpl implements StringList{

    private String[] arrayString;
    private int size;
    public StringListImpl(int initialSize) {
        this.arrayString = new String[initialSize];
        this.size = 0;
    }

    private void IncreasingArray(int requiredSize) {
        if (requiredSize > arrayString.length) {
            int newIncreasing = Math.max(arrayString.length * 2, requiredSize);
            String[] newArray = new String[newIncreasing];
            System.arraycopy(arrayString,0,newArray,0,size);
        }
    }

    private void goingBeyond(int index) {
        if (index < 0 || index > size) {
            throw new ArrayOutOfBoundsException("Выход за рамки массива");
        }
    }

    private void notNull(String item) {
        if (item == null) {
            throw new ValueCanNotBeEmptyException("Не может быть пустым");
        }
    }

    public int arraySize() {
        return size;
    }

    @Override
    public String add(String item) {
        notNull(item);
        IncreasingArray(size + 1);
        arrayString[size] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        notNull(item);
        IncreasingArray(size + 1);
        goingBeyond(index);
        System.arraycopy(arrayString, index, arrayString, index + 1, size - index);
        arrayString[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        notNull(item);
        arrayString[index] = item;
        return item;
    }


    @Override
    public String remove(String item) {
        notNull(item);
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (item.equals(arrayString[i])) {
                index = i;
                break;
            }

        }
        if (index != -1) {
            System.arraycopy(arrayString, index + 1, arrayString, index, size - index - 1);
            arrayString[size + 1] = null;
            size--;
            return item;
        } else {
            throw new ElementNotFoundException("Элемент не найден");
        }
    }

    @Override
    public String remove(int index) {
        goingBeyond(index);
        String removeElement = get(index);
        System.arraycopy(arrayString, index + 1, arrayString, index, size - index - 1);
        arrayString[size - 1] = null;
        size--;
        return removeElement;
    }

    @Override
    public boolean contains(String item) {
        notNull(item);
        for (int i = 0; i < size; i++) {
            if (arrayString[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        notNull(item);
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (arrayString[i].equals(item)) {
                return i;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(String item) {
        notNull(item);
        int index = -1;
        for (int i = size - 1; i >= 0; i--) {
            if (arrayString[i].equals(item)) {
                return i;
            }
        }
        return index;
    }

    @Override
    public String get(int index) {
        goingBeyond(index);
        return arrayString[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new ElementNotFoundException("Список для сравнения не может быть пустым");
        }
        if (!(otherList instanceof StringListImpl)) {
            return false;
        }
        StringListImpl other = (StringListImpl) otherList;
        if (size != other.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if(!arrayString[i].equals(otherList.get(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return arraySize();
    }

    @Override
    public boolean isEmpty() {
        return size != 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arrayString[i] = null;
        }
        size = 0;
    }

    @Override
    public String[] toArray() {
        String[] listArrays = new String[size];
        for (int i = 0; i < size; i++) {
            listArrays[i] = get(i);
        }
        return listArrays;
    }
    
}