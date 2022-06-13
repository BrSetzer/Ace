import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Loader {
    private static String serialization(int[]array){
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<array.length;i++){

            // 33 потому что на 32й позиции находится пробел. А он у нас используется как сплитер
            int celoe =(array[i]/32) +33;
            int ostatosk = (array[i]%32) +33;
            byte [] byteArray =  new byte[]{
                    (byte)celoe, (byte) ostatosk
            };
            String line = new String(byteArray, StandardCharsets.UTF_8);
            sb.append(line);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    private static int[] deserealization(String stroka) {
        int[] mass = new int[200];
        String[] massString = stroka.split(" ");
        for (int i = 0; i < massString.length; i++) {
            if ((massString[i]==null) || (massString[i].equals(""))){
                mass[i]=0;
            }else {
                char[] charmass = massString[i].toCharArray();
                int celoe = (((byte)charmass[0]-33)*32);
                int ostatok = (byte)charmass[1]-33;
                int result = celoe+ostatok;
                mass[i]=result;
            }
        }
        return mass;
    }

    static boolean isMassEquals(int[]beforeSerialized, int[]deserialized){
        for (int i=0; i<beforeSerialized.length; i++ ){
            if (beforeSerialized[i]!=deserialized[i])return false;
        }
        return true;
    }
    public static void main(String[] args) {

        int[] beforeSerialized = new int[200];

        for (int i = 0; i < 20; i++) {
            int a = 1 + (int) (Math.random() * 1000);
            beforeSerialized[i] = a;
        }

        System.out.println("Original data:");
        System.out.println(Arrays.toString(beforeSerialized));
        System.out.println();

        String serialized = serialization(beforeSerialized);

        System.out.println("Encoded:");
        System.out.println(serialized);
        System.out.println("Size: " + serialized.length());
        System.out.println();

        int [] deserialized = deserealization(serialized);

        System.out.println("Decoded:");
        System.out.println(Arrays.toString(deserialized));
        System.out.println();

        System.out.println(isMassEquals(beforeSerialized, deserialized));


    }
}
