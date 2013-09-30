public class DigitalSearchTree {
    private static class Node {
        public Node[] next;
        public boolean isTerminalNode;

        Node() {
            next = new Node[26];
        }
    }

    private static class Tree {
        private Node root;

        Tree() {
            root = new Node();
        }

        private int getIntegerValueForCharacter(char inputChar) {
            return inputChar - 'a';
        }

        public void insertWord(String inputWord) {
            if(inputWord == null) {
                return;
            }
            if(inputWord.length() == 0) {
                return;
            }

            Node currentNode = root;
            for(int idx=0; idx<=inputWord.length(); ++idx) {
                if(idx == inputWord.length()) {
                    currentNode.isTerminalNode = true;
                    return;
                }

                int integerValueForCharacter = getIntegerValueForCharacter(Character.toLowerCase(inputWord.charAt(idx)));
                if(currentNode.next[integerValueForCharacter] == null) {
                    currentNode.next[integerValueForCharacter] = new Node();
                }
                currentNode = currentNode.next[integerValueForCharacter];
            }
        }

        public boolean isValidWord(String inputWord) {
            if(inputWord == null) {
                return;
            }
            if(inputWord.length() == 0) {
                return;
            }

            Node currentNode = root;
            for(int idx=0; idx<inputWord.length(); ++idx) {
                int integerValueForCharacter = getIntegerValueForCharacter(Character.toLowerCase(inputWord.charAt(idx)));
                if(currentNode.next[integerValueForCharacter] == null) {
                    return false;
                }
                currentNode = currentNode.next[integerValueForCharacter];
            }

            if(currentNode.isTerminalNode == true) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.insertWord("something");
        tree.insertWord("abacus");
        tree.insertWord("absurd");
        System.out.println(tree.isValidWord("something"));
        System.out.println(tree.isValidWord("abacus"));
        System.out.println(tree.isValidWord("absurd"));
        System.out.println(tree.isValidWord("dodo"));

        System.out.println("Hello World!");
    }
}
