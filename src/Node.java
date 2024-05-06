public class Node {

    Integer data;
    Node right;
    Node left;
    int x, y;

    Node() {
        data = (Integer) null;
        right = null;
        left = null;
    }

    Node (Integer data) {
        this.data = data;
    }

    public String toString() {
        return "x = " + x + ", y = " + y;
    }
}
