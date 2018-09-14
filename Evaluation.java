import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Evaluation {

	public static void main(String[] args) throws FileNotFoundException {
		double truePos = 0;
		double trueNeg = 0;
		double falsePos = 0;
		double falseNeg = 0;
		
		//get data from prediction and test data
		File testFile = new File("RustEvaluationData.txt");
		Scanner test = new Scanner(testFile);
		File predFile = new File("WekaPredict.txt");
		Scanner prd = new Scanner(predFile); 
		
		while(prd.hasNext()){
			int actual = test.nextInt();
			int pred = prd.nextInt();
			
			if(actual == 1 && pred == 1){
				truePos++;
			}
			else if(actual == 1 && pred == 0){
				falseNeg++;
			}
			else if(actual == 0 && pred ==1){
				falsePos++;
			}
			else{
				trueNeg++;
			}
		}//while
		
		double accuracy = (truePos + trueNeg) / (truePos + falsePos + trueNeg + falseNeg);
		double precision = truePos / (truePos + falsePos);
		double sensitivity = truePos / (truePos + falseNeg);  //recall
		double specificity = trueNeg / (trueNeg+ falsePos);
		double fScore = 2 * ((precision * sensitivity) / (precision + sensitivity));
		
		System.out.printf("Accuracy = %.3f, Precision = %.3f, Sensitivity = %.3f, Specificity = %.3f, fScore = %.3f", 
				accuracy,  precision, sensitivity, specificity, fScore);
		
		test.close();
		prd.close();

	}

}
