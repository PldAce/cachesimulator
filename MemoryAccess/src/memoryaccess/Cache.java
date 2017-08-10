package memoryaccess;
public class Cache 
{
    private CacheEntry[] myCache; // The cache
    private int cachesize; // The number of blocks in the cache
    private int numhits;   // The number of hits so far
    private int nummisses; // The number of misses so far

    public Cache(int numblocks) 
    {
        myCache = new CacheEntry[numblocks];

        for (int i = 0; i < numblocks; i++) 
        {
            myCache[i] = new CacheEntry(numblocks);
        }

        cachesize = numblocks;
        numhits = 0;
        nummisses = 0;
    }

    public static int log2(int value) 
    {
        return (int) (Math.log((double) value) / Math.log((double) 2));
    }

   // Complete this function
    // This should take a memory address (you can assume positive or zero)
    // and convert it to a 32-bit binary string
    private String decToBin32(int address) 
    {
        // Return a 32-bit binary String, representing the integer x
        int valueAsNum = address;
        int quotient;
        String result = "";
      // 1. Complete this loop.  Repeatedly take the quotient and remainder
        //    when dividing by two, and append a "0" or "1" to the result.
        do {
            for (quotient = 32; quotient > 0; quotient--) 
            {
                if (valueAsNum % 2 == 1) 
                {
                    result = '1' + result;
                }
                if (valueAsNum % 2 == 0) 
                {
                    result = '0' + result;
                }
                valueAsNum = valueAsNum / 2;
            }
        } while (quotient != 0);
        return result;
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
    private boolean cache(int address) 
    {
        int indexAddress = address;
        for (int i = 0; indexAddress > (cachesize - 1); i++) 
            {
                indexAddress = indexAddress - (cachesize);
            }
        String bin, tag, index;
        int log = 32-Cache.log2(cachesize);
        bin = decToBin32(address);
        index = bin.substring(log, bin.length());
        tag = bin.substring(0,log);
        int indexDec = BinToDec(index);         
        if (myCache[indexAddress].valid == true && 
           (myCache[indexAddress].tag == null? tag == null : myCache[indexAddress].tag.equals(tag))) 
            {           
                numhits++;
                myCache[indexAddress].tag = tag;
                return true;
            }
        else if ((myCache[indexAddress].valid == true && !
                (myCache[indexAddress].tag.equals(tag))) ||
                (myCache[indexAddress].valid == false) ) 
            { 
                nummisses++;
                myCache[indexDec].tag = tag;
                myCache[indexAddress].valid = true; 
                return false;
            }       
        return false;
    }

    // Print the contents of the cache
    private void display() {
        System.out.println("************************************************************");
        System.out.println("V INDEX\tTAG");
        for (int i = 0; i < cachesize; i++) {
            if (myCache[i].valid) {
                System.out.print("1");
            } else {
                System.out.print("0");
            }
            System.out.println(" " + i + "\t" + myCache[i].tag);
        }
        System.out.println("************************************************************");
        System.out.println("Current hit rate: " + hitRate() + "%");
    }

    private double hitRate() {
        return ((double) numhits / (numhits + nummisses)) * 100;
    }

    // Called from main()
    public void checkCache(int address) {
        if (cache(address)) {
            System.out.println("HIT, Data used.");
        } else {
            System.out.println("MISS, Must go to DRAM.");
        }
        display();
    }

    // Appended method to handle binary to decimal conversion, called in cache.
    public int BinToDec(String index) 
    {
        double j = 0;
        for (int i = 0; i < index.length(); i++) 
        {
            if (index.charAt(i) == '1') 
            {
                j = j + Math.pow(2, index.length() - 1 - i);
            }
        }
        return (int) j;
    }

}
