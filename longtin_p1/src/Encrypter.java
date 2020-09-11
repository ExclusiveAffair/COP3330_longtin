public class Encrypter {
    public String encrypt(String strToEncrypt) {
        int[] digitArray=new int[4];
        for(int i=0;i<4;i++) {
            digitArray[i]=(int)(strToEncrypt.charAt(i)-'0');
        }
        for(int i=0;i<4;i++) {
            digitArray[i]=addValueToDigit(digitArray[i],7);
        }
        swapDigits(digitArray,0,2);
        swapDigits(digitArray,1,3);

        String encryptedResult="";
        for(int i=0;i<4;i++) encryptedResult+=digitArray[i];

        return encryptedResult;
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
