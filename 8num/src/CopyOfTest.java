import java.util.Random;

public class CopyOfTest
{
   private static Random rd = null;

     public static int[] random(int[] src)
   {
       if(src == null)
       {
            return null; 
        }
  
        rd = new Random();
         int[] tmp = new int[src.length];
  
        int num = src.length;
  

        int index;

        for(int i = 0;i < src.length;i++)
        {
  
            index = Math.abs(rd.nextInt()) % num;
          tmp[i] = src[index];
            src[index] = src[num - 1];
            num--;
        }
        return tmp;
   }

   public static void main(String[] args)
   {
        int[] test = {1,2,3,4,5,6,7,8,9};
        int a[] = random(test);
  
        for(int i = 0;i < a.length;i++){
            System.out.println(a[i]); 
        } 
   }
}