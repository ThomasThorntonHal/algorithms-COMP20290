public class Trie{

    // Alphabet size (# of symbols) we pick 26 for English alphabet
    static final int ALPHABET_SIZE = 26;


    // class for Trie node
    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        // isEndOfWord is true if the node represents end of a word i.e. leaf node
        boolean isEndOfWord;

        TrieNode(){
            isEndOfWord = false;

            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    }

    static TrieNode root;
    // If not key present, inserts into trie
    // If the key is prefix of Trie node,
    //  marks leaf node
    static void insert(String key){
        int level;
        int height = key.length();
        int index;

        TrieNode walk = root;

        for(level = 0; level < height; level++) {
            index = key.charAt(level) - 'a';
            if(walk.children[index] == null) {
                walk.children[index] = new TrieNode();
            }
            walk = walk.children[index];
        }
        walk.isEndOfWord = true;
    }

    // Returns true if key presents in trie, else false
    static boolean search(String key) {
        int level;
        int height = key.length();
        int index;

        TrieNode walk = root;

        for(level = 0; level < height; level++) {
            index = key.charAt(level) - 'a';
            if(walk.children[index] == null) {
                return false;
            }

            walk = walk.children[index];
        }
        return (walk != null && walk.isEndOfWord);
    }


    // Driver
    public static void main(String[] args) {
        // Input keys (use only 'a' through 'z' and lower case)
        String[] keys = {"bank", "book", "bar", "bring", "film", "filter", "simple", "silt", "silver"};
        root = new TrieNode();

        // Construct trie
        int i;
        for (i = 0; i < keys.length ; i++) {
            insert(keys[i]);
        }
        System.out.println("Is bank in trie? (Expected true) " + search("bank"));
        System.out.println("Is banker in trie? (Expected false) " + search("banker"));
        insert("banker");
        System.out.println("Is banker in trie? (Expected true) " + search("banker"));
        System.out.println("Is simple in trie? (Expected true) " + search("simple"));
        System.out.println("Is silver in trie? (Expected true) " + search("silver"));
    }
}