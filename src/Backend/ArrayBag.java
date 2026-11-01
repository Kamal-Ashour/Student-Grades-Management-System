package Backend;

public class ArrayBag<T> {

    private static final int EMPTY_SIZE = 0;
    private static final int NOT_FOUND = -1;
    private static final int MIN_CAPACITY = 1;

    private T[] items;
    private int Capacity;
    private int size;

    public ArrayBag(int capacity) {
        if (capacity >= MIN_CAPACITY) {
            this.Capacity = capacity;
            this.items = (T[]) new Object[capacity];
            this.size = EMPTY_SIZE;
        } else {
            throw new IllegalArgumentException("Capacity must be positive");
        }
    }

    public void Add(T item) {
        if (IsFual()) {
            return;
        }

        addItem(item);
    }

    public int frequencyOf(T item) {
        int count = EMPTY_SIZE;

        for (int i = EMPTY_SIZE; i < size; i++) {
            if (items[i].equals(item)) {
                count++;
            }
        }

        return count;
    }

    public void remove(T item) {
        if (IsEmpty()) {
            return;
        }

        int index = indexOf(item);

        if (index != NOT_FOUND) {
            for (int i = index; i < size - MIN_CAPACITY; i++) {
                items[i] = items[i + MIN_CAPACITY];
            }

            size--;
            items[size] = null;
        }
    }

    private int indexOf(T item) {
        for (int i = EMPTY_SIZE; i < size; i++) {
            if (items[i].equals(item)) {
                return i;
            }
        }

        return NOT_FOUND;
    }

    public boolean Contins(T item) {
        for (int i = EMPTY_SIZE; i < size; i++) {
            if (items[i].equals(item)) {
                return true;
            }
        }

        return false;
    }

    private void addItem(T item) {
        items[size] = item;
        size++;
    }

    public boolean IsFual() {
        return Capacity == size;
    }

    public int Size() {
        return size;
    }

    public boolean IsEmpty() {
        return size == EMPTY_SIZE;
    }
}
