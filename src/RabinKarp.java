import java.math.BigInteger;
import java.util.Random;

class RabinKarp {
    /** String Pattern **/
    private final String pat;
    /** pattern hash value **/
    private final long patHash;
    /** pattern length **/
    private final int M;
    /** Large prime **/
    private final long Q;
    /** radix **/
    private final int R;
    /** R^(M-1) % Q **/
    private long RM;
    private int count;

    public RabinKarp(String txt, String pat) {
        this.pat = pat;
        R = 256;
        M = pat.length();
        count++;
        Q = longRandomPrime();
        count++;
        /* precompute R^(M-1) % Q for use in removing leading digit **/
        RM = 1;
        for (int i = 1; i <= M-1; i++) {
            count++;
            RM = (R * RM) % Q;
        }
        patHash = hash(pat, M);
        int pos = search(txt);
        if (pos == txt.length()) {
            System.out.println("\nNo Match\n");
            System.out.println("Rolling hash count: " + count);
        }
        else {
            System.out.println("Pattern found at : " + pos);
            System.out.println("Rolling hash count: " + count);
        }
    }

    /** Compute hash **/
    private long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            count++;
            h = (R * h + key.charAt(j)) % Q;
        }
        return h;
    }

    /** Funtion check **/
    private boolean check(String txt, int i) {
        for (int j = 0; j < M; j++) {
            count++;
            if (pat.charAt(j) != txt.charAt(i + j))
                return false;
        }
        return true;
    }

    /** Funtion to check for exact match**/
    public int search(String txt) {
        int N = txt.length();
        if (N < M) return N;
        long txtHash = hash(txt, M);
        /* check for match at start **/
        if ((patHash == txtHash) && check(txt, 0))
            return 0;
        /* check for hash match. if hash match then check for exact match**/
        for (int i = M; i < N; i++) {
            count++;
            // Remove leading digit, add trailing digit, check for match.
            txtHash = (txtHash + Q - RM*txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            // match
            int offset = i - M + 1;
            if ((patHash == txtHash) && check(txt, offset))
                return offset;
            count++;
        }
        /* no match **/
        return N;
    }

    /** generate a random 31 bit prime **/
    public static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }
}