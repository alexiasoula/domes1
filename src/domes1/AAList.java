package domes1;

public class AAList implements List {

    public boolean insert(Element element) {
        n = nextfree; //elexgos
        nextfree = p[n] = p[nextfree] //elegxos gia an einai adeio
        p[tail] = n;
        p[n] = -1;
    }
}