import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Set2Impl<T> implements Set<T> {
    List<List<T>> container = new ArrayList<>();
    int size = 0;
    int arraySize;

    public Set2Impl(int arraySize) {
        this.arraySize = arraySize;
        for (int i = 0; i < arraySize; i++) {
            container.add(new LinkedList<>());
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    public void isFull() {
        for (int i = 0; i < arraySize; i++) {
            if (container.get(i).size() == 4) {
                enlargeList();
            }
        }
    }

    public void enlargeList() {
        int tempArrSize = (int) Math.pow(arraySize,2);
        Set2Impl<T> tempSet = new Set2Impl<>(tempArrSize);
        for (int i = 0; i < arraySize; i++) {
            if (!container.get(i).isEmpty()) {
                for (T element:container.get(i)) {
                    int hashCodeOfElement = element.hashCode();
                    List<T> largeHashList = tempSet.container.get(hashCodeOfElement % tempArrSize);
                    largeHashList.add(element);
                }
            }
        }
        this.container = tempSet.container;
        this.arraySize = tempArrSize;
    }

    @Override
    public boolean contains(T object) {
        int hashCodeOfElement = object.hashCode();
        List<T> listOfHashIndex = container.get(hashCodeOfElement % arraySize);
        return listOfHashIndex.contains(object);
    }

    @Override
    public boolean add(T element) {
        isFull();
        int hashCodeOfElement = element.hashCode();
        List<T> listOfHashIndex = container.get(hashCodeOfElement % arraySize);
        if (!listOfHashIndex.contains(element)) {
            listOfHashIndex.add(element);
            size++;
            return true;
        }
        return false;
    }

    public String toString() {
        String toString = "";
        System.out.println(arraySize);
        for (int i = 0; i < arraySize; i++) {
            if (!container.get(i).isEmpty()) {
                for (T element:container.get(i)) {
                    toString += element+",";
                }
            }
        }
        return toString;
    }

    @Override
    public boolean remove(T element) {
        return false;
    }

    @Override
    public void clear() {
        this.container = new ArrayList<>();
    }
}
