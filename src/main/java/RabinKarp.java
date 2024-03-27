public class RabinKarp {
    private String pat;
    private long patHash;
    private int m;
    private long mod = 2047234517L;
    private int R;
    private long RM; // leading coefficient

    public RabinKarp(char[] pattern, int R) {
        this.pat = String.valueOf(pattern);
        this.R = R;
    }

    public RabinKarp(String pat) {
        this.pat = pat;
        R = 256; // 26 for english lowercase letters
        m = pat.length();
        // mod = longRandomPrime();
        RM = 1;
        for (int i = 1; i <= m - 1; i++) {
            RM = (R * RM) % mod;
        }
        patHash = hash(pat, m);
    }


    private long hash(String key, int m) {
        long h = 0;
        for (int j = 0; j < m; j++) {
            h = (R * h + key.charAt(j)) % mod;
        }

        return h;
    }

    private boolean check(String txt, int i) {
        for (int j = 0; j < m; j++)
            if (pat.charAt(j) != txt.charAt(i + j)) {
                return false;
            }
        return true;
    }

    public int search(String txt) {
        int n = txt.length();
        if (n < m) {
            return -1;
        }
        long txtHash = hash(txt, m);

        if ((patHash == txtHash) && check(txt, 0)) {
            return 0;
        }

        for (int i = m; i < n; i++) {
            txtHash = (txtHash + mod - RM * txt.charAt(i - m) % mod) % mod;
            txtHash = (txtHash * R + txt.charAt(i)) % mod;
            int offset = i - m + 1;
            if ((patHash == txtHash) && check(txt, offset)) {
                return offset;
            }
        }
        return -1;
    }

//    private static long longRandomPrime() {
//        BigInteger prime = BigInteger.probablePrime(31, new Random());
//        return prime.longValue();
//    }
}
