//import java.util.*;
//import java.io.*;
//
///**
// * A class for generating statistical information hash table insertion and finds.
// * 
// * @author Charles Hoot
// * @version 4.0
// */
//        
//public class HashPerformance
//{
//    
//    
//    
//    public static void main(String args[])
//    {
//        int insertCount;
//        int insertionLinearProbes=0, insertionDoubleProbes=0, insertionPerfectProbes=0;
//        int successLinearProbes=0, successDoubleProbes=0, successPerfectProbes=0;
//        int failureLinearProbes=0, failureDoubleProbes=0, failurePerfectProbes=0;
//        int trials;
//        double load;
//        String data[];
//        
//        HashedDictionaryOpenAddressingLinearInstrumented<String,String> linearTable;
//        HashedDictionaryOpenAddressingDoubleInstrumented<String,String> doubleTable;
//        HashedDictionaryOpenAddressingPerfectInstrumented<String,String> perfectTable;
//        
//        System.out.println("How many items should be inserted into the hash tables?");
//        insertCount = getInt("   It should be an integer value greater than or equal to 1.");
//        
//        System.out.println("How many trials would you like?");
//        trials = getInt("   It should be an integer value greater than or equal to 1.");
//
//        System.out.println("What should the maximum load factor be? ");
//        load = getDoublePercent();
//        
//        // ADD CODE TO GET THE INITIAL TABLE SIZE HERE
//        
//        for(int i = 0; i<trials; i++)
//        {
//            data = generateRandomData(insertCount);
//
////          Set the Starting Capacity to 101 so that we don't rehash too soon 
//            linearTable = new HashedDictionaryOpenAddressingLinearInstrumented<String,String>(101);
//            doubleTable = new HashedDictionaryOpenAddressingDoubleInstrumented<String,String>(101);
//            perfectTable = new HashedDictionaryOpenAddressingPerfectInstrumented<String,String>(101);
//                        
//                        
//            linearTable.setMaxLoadFactor(load);
//            doubleTable.setMaxLoadFactor(load);
//            perfectTable.setMaxLoadFactor(load);
//            
//            HashedDictionaryOpenAddressingLinearInstrumented.resetTotalProbes();
//            HashedDictionaryOpenAddressingDoubleInstrumented.resetTotalProbes();
//            HashedDictionaryOpenAddressingPerfectInstrumented.resetTotalProbes();
//
//            System.out.println("The data is: " + getString(data));
//            insertAllData(linearTable, data);
//            insertAllData(doubleTable, data);
//            insertAllData(perfectTable, data);
//    
//            insertionLinearProbes += HashedDictionaryOpenAddressingLinearInstrumented.getTotalProbes();
//            insertionDoubleProbes += HashedDictionaryOpenAddressingDoubleInstrumented.getTotalProbes();
//            insertionPerfectProbes += HashedDictionaryOpenAddressingPerfectInstrumented.getTotalProbes();
//
//            // ADD CODE HERE TO DO SUCCESSFULL AND FAILURE SEARCHES
//
//        }
//        
//        
//        System.out.println("Linear hashing");
//        System.out.println("    Total probes for inserting data values: " + insertionLinearProbes);
//        System.out.println("       Average probes made: " + insertionLinearProbes/(float)(trials*insertCount));
//        // ADD CODE HERE TO REPORT THE RESULTS FOR THE SUCCESS AND FAILURE SEARCHES
//
//
//        System.out.println("Double hashing");
//        System.out.println("    Total probes for inserting data values: " + insertionDoubleProbes);
//        System.out.println("       Average probes made: " + insertionDoubleProbes/(float)(trials*insertCount));
//        // ADD CODE HERE TO REPORT THE RESULTS FOR THE SUCCESS AND FAILURE SEARCHES
//
//
//        System.out.println("Perfect hashing");
//        System.out.println("    Total probes for inserting data values: " + insertionPerfectProbes);
//        System.out.println("       Average probes made: " + insertionPerfectProbes/(float)(trials*insertCount));
//        // ADD CODE HERE TO REPORT THE RESULTS FOR THE SUCCESS AND FAILURE SEARCHES
//       
//        
//    }
//
//
//
//    /**
//     * Generate an array of random words.  Each would will be composed of three randomly 
//     * chosen syllables.
//     *
//     * @param   size    The number of strings to generate.
//     * @return  The array of strings.
//     */
//    private static String[] generateRandomData(int size)
//    {
//        String result[] = new String[size];
//        HashedDictionaryOpenAddressingLinear<String,String> checkTable = new HashedDictionaryOpenAddressingLinear<String,String>();
//
//        
//        String firstSyl[] = {"ther", "fal","sol","cal","com","don", "gan", "tel", "fren", "ras", "tar", "men", "tri", "cap", "har"};
//        String secondSyl[] = {"mo", "ta","ra","te","bo","bi","du","ca", "dan", "sen", "di", "no", "fe", "mi", "so"};
//        String thirdSyl[] = {"tion", "ral", "tal","ly","nance","tor", "ing", "ger", "ten", "ful", "son", "dar", "der", "den", "ton"};
//        
//        // ADD CODE TO GENERATE THE RANDOM WORDS
//        
//        return result;
//    }
//
//    /**
//     * Insert all of the values in the array into the hash table. 
//     *
//     * @param   dict The dictionary to insert all the words into.
//     */
//    private static void insertAllData(DictionaryInterface<String,String> dict, String[] data)
//    {        
//        for(int i = 0; i< data.length; i++)
//        {
//           dict.add(data[i], data[i]);
//        }
//    }
//
//// ADD A METHOD HERE TO INSERT THE FIRST HALF OF THE DATA VALUES IN AN ARRAY
//// INTO A DICTIONARY
//
//
//// ADD A METHOD HERE TO SEARCH FOR ITEMS FROM THE FIRST HALF OF THE ARRAY
//// (SUCCESS SEARCHES)
//
//
//// ADD A METHOD HERE TO SEARCH FOR ITEMS FROM THE SECOND HALF OF THE ARRAY
//// (FAILURE SEARCHES)
//
//    /**
//     *  A displayable representation of an array of Objects where toString is 
//     *  applied on each object in the array.
//     *
//     * @param   data    The array to display.
//     * @return  A printable string.
//     */
//    private static String getString(Object [] data)
//    {
//        String result = new String("[ ");
//        
//        for(int i = 0; i< data.length; i++)
//        {
//            result = result + data[i].toString() + " ";
//        }
//        
//        result = result + "]";
//        
//        return result;
//    }
//    
//    
//    /**
//     * Get an integer value.
//     *
//     * @return     An integer. 
//     */
//    private static int getInt(String rangePrompt)
//    {
//        Scanner input;
//        int result = 10;        //Default value is 10
//        try
//        {
//            input = new Scanner(System.in);
//            System.out.println(rangePrompt);
//            result = input.nextInt();
//            
//        }
//        catch(NumberFormatException e)
//        {
//            System.out.println("Could not convert input to an integer");
//            System.out.println(e.getMessage());
//            System.out.println("Will use 10 as the default value");
//        }        
//        catch(Exception e)
//        {
//            System.out.println("There was an error with System.in");
//            System.out.println(e.getMessage());
//            System.out.println("Will use 10 as the default value");
//        }
//        return result;
//                                    
//    }    
//        
//    /**
//     * Get a floating point value greater than 0 and less than 1.
//     *
//     * @return     A double. 
//     */
//    private static Double getDoublePercent()
//    {
//        Scanner input;
//        Double result = .5;        //Default value is .5
//        try
//        {
//            input = new Scanner(System.in);
//            System.out.println("   It should be a floating point value greater than 0 and less than 1.");
//           
//            result = input.nextDouble();
//            
//        }
//        catch(NumberFormatException e)
//        {
//            System.out.println("Could not convert input to a double value");
//            System.out.println(e.getMessage());
//            System.out.println("Will use 0.5 as the default value");
//        }        
//        catch(Exception e)
//        {
//            System.out.println("There was an error with System.in");
//            System.out.println(e.getMessage());
//            System.out.println("Will use 0.5 as the default value");
//        }
//        
//        return result;
//                                    
//    }
//}
