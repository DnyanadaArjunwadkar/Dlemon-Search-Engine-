import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import java.io.*;
import java.io.IOException;


public class TestClass {
	

   
	public static void main(String[] args) throws IOException {
        // Prints "Hello, World" to the terminal window.
        //System.out.println("Hello, World");
		
		/* String NER=NERTagging("The Marine National Park is situated in Pune district which Putin wants to visit even if it's at midnight on a Sunday and the tickets are priced $100");
	        System.out.print(NER);*/
		
		
		
		File dir = new File(".");
		 FilenameFilter filter = new FilenameFilter() {
		        public boolean accept(File directory, String fileName) {
		            return fileName.endsWith(".json");
		        }
		        };
		File[] filesList = dir.listFiles(filter);
		
		if(filesList!=null)
		{
			JSonHandler js = new JSonHandler();
			for (int i =0; i< filesList.length ; i++)
			{
				
				js.ReadJSonFile(filesList[i]);
			}
			
			
			System.out.println("Success!");
			System.out.println("Total files updated: "+filesList.length);
		}
		else
			System.out.println("no files");
		
	
		 
    }
	
	 

}
