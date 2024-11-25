public class Main {

    private static int count;

    public static void main(String[] args) {
        String txt = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        String pattern = "ind";

        // robin karp algorithm with rolling hash
        // calculates inside the constructor
        var rabinKarp = new RabinKarp(txt, pattern);

        System.out.println("Resultado hash normal: " + search(pattern, txt));
        System.out.println("Resultado count normal: " + count);

    }

    private static int search(String pat, String txt) {
        count = 0;
        int m = pat.length();
        int n = txt.length();
        count += 2;
        long patHash = hash(pat, m);
        count += m;

        for (int i = 0; i <= n - m; i++) {
            count++;
            long txtHash = hash(txt.substring(i, i + m), m);
            count += m;
            if (patHash == txtHash) {
                count++;
                return i;
            }
        }
        return n;
    }
    private static long hash(String s, int m) {
        long h = 0;
        for (int j = 0; j < m; j++) {
            h = (h * 26 + s.charAt(j)) % RabinKarp.longRandomPrime();
        }
        return h;
    }
}
