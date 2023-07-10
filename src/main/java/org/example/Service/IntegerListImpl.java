package org.example.Service;

import org.example.Example.ArrayOutOfBoundsException;
import org.example.Example.ElementNotFoundException;
import org.example.Example.ValueCanNotBeEmptyException;

public class IntegerListImpl implements IntegerList{

    private Integer[] arrayInteger;
    private int size;
    public IntegerListImpl(int initialSize) {
        this.arrayInteger = new Integer[initialSize];
        this.size = 0;
    }

    private void IncreasingArray(int requiredSize) {
        if (requiredSize > arrayInteger.length) {
            int newIncreasing = Math.max(arrayInteger.length * 2, requiredSize);
            Integer[] newArray = new Integer[newIncreasing];
            System.arraycopy(arrayInteger,0,newArray,0,size);
        }
    }

    private void goingBeyond(int index) {
        if (index < 0 || index > size) {
            throw new ArrayOutOfBoundsException("Выход за рамки массива");
        }
    }

    private void notNull(Integer item) {
        if (item == null) {
            throw new ValueCanNotBeEmptyException("Не может быть пустым");
        }
    }

    public int arraySize() {
        return size;
    }

    @Override
    public Integer add(Integer item) {
        notNull(item);
        IncreasingArray(size + 1);
        arrayInteger[size] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        notNull(item);
        IncreasingArray(size + 1);
        goingBeyond(index);
        System.arraycopy(arrayInteger, index, arrayInteger, index + 1, size - index);
        arrayInteger[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        notNull(item);
        arrayInteger[index] = item;
        return item;
    }


    @Override
    public Integer remove(Integer item) {
        notNull(item);
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (item.equals(arrayInteger[i])) {
                index = i;
                break;
            }

        }
        if (index != -1) {
            System.arraycopy(arrayInteger, index + 1, arrayInteger, index, size - index - 1);
            arrayInteger[size + 1] = null;
            size--;
            return item;
        } else {
            throw new ElementNotFoundException("Элемент не найден");
        }
    }

    @Override
    public Integer remove(int index) {
        goingBeyond(index);
        Integer removeElement = get(index);
        System.arraycopy(arrayInteger, index + 1, arrayInteger, index, size - index - 1);
        arrayInteger[size - 1] = null;
        size--;
        return removeElement;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] storageCopy = toArray();
        sort(storageCopy);
        return binarySearch(storageCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        notNull(item);
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (arrayInteger[i].equals(item)) {
                return i;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Integer item) {
        notNull(item);
        int index = -1;
        for (int i = size - 1; i >= 0; i--) {
            if (arrayInteger[i].equals(item)) {
                return i;
            }
        }
        return index;
    }

    @Override
    public Integer get(int index) {
        goingBeyond(index);
        return arrayInteger[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new ElementNotFoundException("Список для сравнения не может быть пустым");
        }
        if (!(otherList instanceof IntegerListImpl)) {
            return false;
        }
        IntegerListImpl other = (IntegerListImpl) otherList;
        if (size != other.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if(!arrayInteger[i].equals(otherList.get(i))){
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
            arrayInteger[i] = null;
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] listArrays = new Integer[size];
        for (int i = 0; i < size; i++) {
            listArrays[i] = get(i);
        }
        return listArrays;
    }
    private void sort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j = 1;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private boolean binarySearch(Integer[] arr,Integer item) {
        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item == arr[mid]) {
                return true;
            }
            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}