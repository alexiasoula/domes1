package domes1;

public class MyElement implements Element {
    private int key;

    public MyElement(int key) {
        this.key = key;
    }

    @Override
    public int getKey() {
        return key;
    }
}