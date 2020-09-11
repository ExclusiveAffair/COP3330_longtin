public class Decrypter {
    public String decrypt(String strToDecrypt) {
        int[] digitArray=new int[4];
        for(int i=0;i<4;i++) {
            digitArray[i]=(int)(strToDecrypt.charAt(i)-'0');
        }
        swapDigits(digitArray,1,3);
        swapDigits(digitArray,0,2);

        for(int i=0;i<4;i++) {
            digitArray[i]=addValueToDigit(digitArray[i],-7);
        }

        String decryptedResult="";
        for(int i=0;i<4;i++) decryptedResult+=digitArray[i];

        return decryptedResult;
    }
    public void swapDigits(int[] digitArray, int index1, int index2) {
        int index1OriginalValue=digitArray[index1];

        digitArray[index1]=digitArray[index2];
        digitArray[index2]=index1OriginalValue;
    }

    public int addValueToDigit(int digit, int valueToAdd) {
        int newDigit=digit+valueToAdd;
        newDigit=(newDigit+10)%10;
        return newDigit;
    }
}
