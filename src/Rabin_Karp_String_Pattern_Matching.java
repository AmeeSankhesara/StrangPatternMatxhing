// Amee Sankhesara

public class Rabin_Karp_String_Pattern_Matching {
    public static void main(String[] args) {

        InputSource inputSource = new InputSource("Rabin_text.txt");
        String text = inputSource.inputString;
//        InputSource patternSourcee = new InputSource("rabin_pattern.txt");
        String pattern = "50S ribosomal protein L3 Acinetobacter rudis 0S ribosomal protein L3 Acinetobacter rudis beijerinckii calcoaceticus";

        if (pattern == null || pattern.trim().equals(""))
        {
            System.out.println("Pattern is empty");
            return;
        }
            int q = 101; // A prime number
            long start = System.currentTimeMillis();
            search(pattern.toCharArray(), text.toCharArray(), q);
            long end = System.currentTimeMillis();
            System.out.println("search takes " + (end - start) + "ms");
    }

    // d is the number of characters in the input alphabet
    public final static int d = 256;

    /* pat -> pattern
        txt -> text
        q -> A prime number
    */
    static void search(char[] pattern, char[] text, int q)
    {
        int M = pattern.length;
        int N = text.length;
        int i, j;
        int p = 0; // hash value for pattern
        int t = 0; // hash value for txt
        int h = 1;
        int count = 0;

        // The value of h would be "pow(d, M-1)%q"
        for (i = 0; i < M-1; i++)
            h = (h*d)%q;

        // Calculate the hash value of pattern and first
        // window of text
        for (i = 0; i < M; i++)
        {
            p = (d*p + pattern[i])%q;
            t = (d*t + text[i])%q;
        }

        // Slide the pattern over text one by one
        for (i = 0; i <= N - M; i++)
        {

            // Check the hash values of current window of text
            // and pattern. If the hash values match then only
            // check for characters on by one
            if ( p == t )
            {
                /* Check for characters one by one */
                for (j = 0; j < M; j++)
                {
                    if (text[i+j] != pattern[j])
                        break;
                }

                // if p == t and pat[0...M-1] = txt[i, i+1, ...i+M-1]
                if (j == M){
                    System.out.println("Pattern found at index " + i);
                    count++;
                }

            }

            // Calculate hash value for next window of text: Remove
            // leading digit, add trailing digit
            if ( i < N-M )
            {
                t = (d*(t - text[i]*h) + text[i+M])%q;

                // We might get negative value of t, converting it
                // to positive
                if (t < 0)
                    t = (t + q);
            }
        }
        if(count==0){
            System.out.println("Pattern not found in given text");
        }
    }

}
