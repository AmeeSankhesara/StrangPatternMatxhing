// Amee Sankhesara
public class KMP_String_Pattern_Matching {
    //Index of text is starting from 0.
    public static void main(String[] args) {

        InputSource inputSource = new InputSource("Rabin_text.txt");
        String str = inputSource.inputString;
//        InputSource patternSourcee = new InputSource("rabin_pattern.txt");
        String subString = "50S ribosomal protein L3 Acinetobacter rudis 0S ribosomal protein L3 Acinetobacter rudis beijerinckii calcoaceticus";
        if (subString.trim().equals("") || subString == null)
        {
            System.out.println("Pattern is empty");
            return;
        }
        long start = System.currentTimeMillis();
        KMP_String_Pattern_Matching ss = new KMP_String_Pattern_Matching();
        ss.KMP(str.toCharArray(), subString.toCharArray());
        long end = System.currentTimeMillis();
        System.out.println("search takes " +  (end - start) + "ms");
    }

    private int[] ComputePrefixFun(char pattern[]){
        int m = pattern.length;
        int[] lps = new int[pattern.length];
        lps[0] = 0;
        int k = 0;
        for(int i=1;i<m;i++){
            while (k>0 && pattern[k]!=pattern[i]) {
                k = lps[k-1];
            }
            if(pattern[k]==pattern[i]) {
                k = k + 1;
            }
            lps[i]=k;
        }
        return lps;
    }

    public void KMP(char[] text, char[] pattern){
        int m = pattern.length;
        int n = text.length;
        int[] lps = ComputePrefixFun(pattern);
        //System.out.println(Arrays.toString(lps));
        int q=0;
        int count=0;
        for(int i=0;i<n;i++){
           // System.out.println("i="+i+",q="+q);
          while (q>0 && pattern[q]!=text[i]){
              q=lps[q-1];
          }
          if(pattern[q]==text[i]){
              q=q+1;
          }
          if(q==m){
              System.out.println("Pattern found at index "+((i+1)-q));
              q=lps[q-1];
              count++;
          }
        }

        if(count==0){
            System.out.println("Pattern not found in given text.");
        }

    }

}
