package memoryaccess;

import java.util.Scanner;

public class MemoryAccess {
   public static void main(String[] args) {
      System.out.println("Enter the size of your cache in blocks: ");
      Scanner scan = new Scanner(System.in);
      int mySize = scan.nextInt();
      while ((mySize & (mySize - 1)) != 0) {
         System.out.println("Cache size must be a power of two.  Please reenter.");
         mySize = scan.nextInt();
      }
      Cache myCache = new Cache(mySize);

      int myAddress = 0;
      while (myAddress >= 0) {
         System.out.println("Enter a memory address to read (negative value to stop):");
         myAddress = scan.nextInt();
         if (myAddress >= 0)
 	    myCache.checkCache(myAddress);
      }
   }
}