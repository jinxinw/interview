package company.amazon.oa;

public class BitPalindrome {

    public boolean bIsIntPalindrome(int nInputNum) {
        if (nInputNum < 0) {
            return false;
        }
        int nResultNum = 0;
        int nInputNumCopy = nInputNum;

        while (nInputNumCopy > 0) {
            nResultNum <<= 1;
            if ((nInputNumCopy & 1) == 1) {
                nResultNum |= 1;
            }
            nInputNumCopy >>= 1;
        }

        if (nInputNum == nResultNum) {
            return true;
        }
        return false;
    }
    
    public static void main (String[] args) {
        BitPalindrome bp = new BitPalindrome();
        boolean res = bp.bIsIntPalindrome(9);
        System.out.println(res);
    }
}
