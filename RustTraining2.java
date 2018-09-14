import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class RustTraining2 {
	//uses the Gaussian Naive Bayes Algorithm
	public static void main(String[] args) throws IOException {
		File input = new File("TrainingData2.txt");
		Scanner train = new Scanner(input);
		
		double[] signif = new double[76];
		double[] temp = new double[76];
		double[] humidity = new double[76];
		double[] precip = new double[76];
		
		train.nextLine();
		for(int i =0; i<76; i++) {
			signif[i] = train.nextDouble();
			temp[i] = train.nextDouble();
			humidity[i] = train.nextDouble();
			precip[i] = train.nextDouble();
		}
		
		double tempY=0, tempN=0, humY=0, humN=0, precY=0, precN=0;
		ArrayList<Float> tempYes = new ArrayList<Float>(50);
		ArrayList<Float> tempNo = new ArrayList<Float>(50);
		ArrayList<Float> humidYes = new ArrayList<Float>(50);
		ArrayList<Float> humidNo = new ArrayList<Float>(50);
		ArrayList<Float> precipYes = new ArrayList<Float>(50);
		ArrayList<Float> precipNo = new ArrayList<Float>(50);
		
		for(int i = 0; i<76; i++) {
			if(signif[i] == 1) {
				tempY+= temp[i];
				tempYes.add((float) temp[i]);
				humY+= humidity[i];
				humidYes.add((float) humidity[i]);
				precY+= precip[i];
				precipYes.add((float) precip[i]);
			}
	
			if(signif[i] == 0) {
				tempN+= temp[i];
				tempNo.add((float) temp[i]);
				humN+= humidity[i];
				humidNo.add((float) humidity[i]);
				precN+= precip[i];			
				precipNo.add((float) precip[i]);
			}
		}
		
		double avgTempY =  tempY / tempYes.size();
		double avgHumidY = humY / humidYes.size();
		double avgPrecipY = precY / precipYes.size();
		double avgTempN =  tempN / tempNo.size();
		double avgHumidN = humN / humidNo.size();
		double avgPrecipN = precN / precipNo.size();
		
		double varTempY=0;
		double varTempN=0;
		double varHumidY=0;
		double varHumidN=0;
		double varPrecipY=0;
		double varPrecipN=0;
		
		for(int i=0; i<tempYes.size(); i++) {
			varTempY = varTempY + (Math.pow((tempYes.get(i) - avgTempY), 2) );
		}
		varTempY = varTempY  / tempYes.size() ;
		
		
		for(int i=0; i<tempNo.size(); i++) {
			varTempN = varTempN + (Math.pow((tempNo.get(i) - avgTempN), 2) );
		}
		varTempN = varTempN / tempNo.size() ;
		
		for(int i=0; i<humidYes.size(); i++) {
			varHumidY = varHumidY + (Math.pow((humidYes.get(i)-avgHumidN), 2) );
		}
		varHumidY = varHumidY / humidYes.size();
		
		for(int i=0; i<humidNo.size(); i++) {
			varHumidN = varHumidN + (Math.pow((humidNo.get(i)-avgHumidN), 2) );
		}
		varHumidN = varHumidN  / humidNo.size() ;
		
		for(int i=0; i<precipYes.size(); i++) {
			varPrecipY = varPrecipY + (Math.pow((precipYes.get(i)-avgPrecipY), 2) );
		}
		varPrecipY = varPrecipY / precipYes.size();
		
		for(int i=0; i<precipNo.size(); i++) {
			varPrecipN = varPrecipN + (Math.pow((precipNo.get(i)- avgPrecipN), 2) );
		}
		varPrecipN = varPrecipN / precipNo.size();
		
		train.close();
		//print model
		PrintWriter out = new PrintWriter("model3.txt");
		
		out.println("mean, variance");
		out.println(avgTempY + "\t" + varTempY);
		out.println(avgTempN + "\t" + varTempN);
		out.println(avgHumidY + "\t" + varHumidY);
		out.println(avgHumidN + "\t" + varHumidN);
		out.println(avgPrecipY + "\t" + varPrecipY);
		out.println(avgPrecipN + "\t" + varPrecipN);
		out.close();

	}

}
