public class Cache {
   private CacheEntry[] myCache; // The cache
   private int cachesize; // The number of blocks in the cache
   private int numhits;   // The number of hits so far
   private int nummisses; // The number of misses so far

   public Cache(int numblocks) {
       myCache = new CacheEntry[numblocks];
  
       for (int i = 0; i < numblocks; i++)
          myCache[i] = new CacheEntry(numblocks);

       cachesize = numblocks;
       numhits = 0;
       nummisses = 0;
   }

   public static int log2(int value) {
      return (int)(Math.log((double)value) / Math.log((double)2));
   }

   // Complete this function
   // This should take a memory address (you can assume positive or zero)
   // and convert it to a 32-bit binary string
   private String decToBin32(int address) {
      // Return a 32-bit binary String, representing the integer x
      int valueAsNum = address;

      int quotient, remainder;
      String result = "";

      // 1. Complete this loop.  Repeatedly take the quotient and remainder
      //    when dividing by two, and append a "0" or "1" to the result.
      do {
         //////////////////////////////////////////////////
         // PUT CODE HERE
         //////////////////////////////////////////////////
      } while (quotient != 0);

      String zeroes = "";
      // 2. Make a string of the appropriate amount of zeroes, so the result
      // has 32 bits.
      ////////////////////////////////////////////////////
      // PUT CODE HERE
      ////////////////////////////////////////////////////

      return zeroes+result;
   }

   // Complete this function.  
   // In summary, the task is to check the cache at the appropriate index
   // assuming we are reading from the passed DRAM memory address.
   // If the address hits in the cache, return true and add one to the hit counter (numhits)
   //    (remember for a hit, the valid bit must be on AND the tag must match
   // If the address misses, return false andd add one to the miss counter (nummisses)
   // but also add that address to the cache
   //    (you'll need to turn the valid bit on AND put its tag in the correct spot
   // You will also call your decToBin32() to convert the address to a 32-bit binary value
   // before you pull out the tag bits.
   private boolean cache(int address) {
      ////////////////////////////////////////////////////
      // PUT CODE HERE
      ////////////////////////////////////////////////////
   }

   // Print the contents of the cache
   private void display() {
      System.out.println("************************************************************");
      System.out.println("V INDEX\tTAG");
      for (int i = 0; i < cachesize; i++) {
          if (myCache[i].valid)
             System.out.print("1");
          else
             System.out.print("0");
          System.out.println(" "+i+"\t"+myCache[i].tag);
      }
      System.out.println("************************************************************");
      System.out.println("Current hit rate: "+hitRate()+"%");
   } 
 
   private double hitRate() {
      return ((double)numhits/(numhits+nummisses))*100;
   }

   // Called from main()
   public void checkCache(int address) {
      if (cache(address)) System.out.println("HIT, Data used.");
      else System.out.println("MISS, Must go to DRAM.");
      display();
   }

   
}
