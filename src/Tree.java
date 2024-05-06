public class Tree {
    Node root;
    Node temp;
    static int howmany = 0;
    static int h = 80;

    Tree() {
        root = null;
    }

    public void add(Integer data) {
        Node node = new Node(data);

        if (this.root == null) {
            this.root = node;
            this.root.x = 100;
            this.root.y = 10;
            howmany++;
        } else {
            boolean ok = true;
            temp = root;
            while (ok) {
                if (temp.left == null) {
                    temp.left = node;
                    temp.left.y = temp.y + h;
                    howmany++;
                    ok = false;
                } else if (temp.right == null) {
                    temp.right = node;
                    temp.right.y = temp.y + h;
                    howmany++;
                    ok = false;
                } else {
                    if (sizeOfTree(temp.left) <= sizeOfTree(temp.right)) {
                        temp = temp.left;
                    } else {
                        temp = temp.right;
                    }
                }
            }
        }
    }

    public int sizeOfTree(Node root) {
        if (root == null) {
            return 0;
        }

        return 1 + sizeOfTree(root.left) + sizeOfTree(root.right);
    }

    public static boolean updateX(Node root, int level, int difference) {
        if (root == null) {
            return false;
        }

        if (level == 1) {
            //System.out.print(root.data + " ");
            if (root.left != null) {
                root.left.x = root.x - difference;
            }
            if (root.right != null) {
                root.right.x = root.x + difference;
            }
            return true;
        }

        boolean left = updateX(root.left, level - 1, difference);
        boolean right = updateX(root.right, level - 1, difference);

        return left || right;
    }

    public static void levelOrderTraversal(Node root) {
        int level = 1;
        root.x = (int) ((Math.pow(2, getHeight(root)))*20+20);
        int difference = (int) ((Math.pow(2, getHeight(root)-1))*20+20);
        while (updateX(root, level, difference)) {
            level++;
            difference = difference/2;
        }
    }

    static int getHeight(Node root)
    {
        if (root.left == null && root.right == null)
            return 0;

        int left = 0;
        if (root.left != null)
            left = getHeight(root.left);

        int right = 0;
        if (root.right != null)
            right = getHeight(root.right);

        return (Math.max(left, right) + 1);
    }
}
