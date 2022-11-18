import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Set2Impl<T> implements Set<T> {
    List<List<T>> container = new ArrayList<>();
    int size = 0;
    int arraySize = 4;

    public Set2Impl() {
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


    public boolean isFull() {
        for (int i = 0; i < arraySize; i++) {
            if (container.get(i).size()== 4) {
                enlargeList();
            }
        }
        return false;
    }

    public void enlargeList() {
        int tempListCount = arraySize;
        this.arraySize = (int) Math.pow(arraySize,2);
        List<List<T>> largeContainer = new ArrayList<>();
        for (int i = 0; i < tempListCount; i++) {
            for (int j = 0; j < 4; j++) {
                if (!container.get(i).isEmpty()) {
                    int hashCodeOfElement = container.get(i).get(j).hashCode();
                    List<T> listOfHashIndex = container.get(hashCodeOfElement % tempListCount);
                    List<T> largeListOfHashIndex = largeContainer.get(hashCodeOfElement % arraySize);
                }
            }
        }
    }

    @Override
    public boolean contains(T object) {
        int hashCodeOfElement = object.hashCode();
        List<T> listOfHashIndex = container.get(hashCodeOfElement % arraySize);
        return listOfHashIndex.contains(object);
    }

    @Override
    public boolean add(T element) {
        int hashCodeOfElement = element.hashCode();
        List<T> listOfHashIndex = container.get(hashCodeOfElement % arraySize);
        if (!listOfHashIndex.contains(element)) {
            listOfHashIndex.add(element);
            size++;
            return true;
        }
        return false;
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
