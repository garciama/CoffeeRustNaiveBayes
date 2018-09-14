import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class RustPrediction2 {

	public static void main(String[] args) throws IOException {
		File f = new File("TestingData2.txt");
		Scanner input = new Scanner(f);
		File fi = new File("model3.txt");
		Scanner model = new Scanner(fi);

		model.nextLine();
		double avgTempY =  model.nextDouble();
		double varTempY = model.nextDouble();

		double avgHumidY = model.nextDouble();
		double varHumidY = model.nextDouble();

		double avgPrecipY = model.nextDouble();
		double varPrecipY = model.nextDouble();

		double avgTempN =  model.nextDouble();
		double varTempN = model.nextDouble();

		double avgHumidN = model.nextDouble();
		double varHumidN = model.nextDouble();

		double avgPrecipN = model.nextDouble();
		double varPrecipN = model.nextDouble();
		
		model.close();

		double pTempY, pTempN, pHumidY, pHumidN, pPrecipY, pPrecipN;
		double pYes, pNo;
		double temp, humid, precip;
		PrintWriter out = new PrintWriter("prediction3.txt");

		input.nextLine();
		while(input.hasNext()) {
			input.nextDouble();
			temp = input.nextDouble();
			pTempY = (Math.exp( -( Math.pow( (temp-avgTempY), 2) / (2*varTempY) ) ) / ( Math.sqrt(varTempY*2*Math.PI) ) );
			pTempN = (Math.exp( -( Math.pow( (temp-avgTempN), 2) / (2*varTempN) ) ) / ( Math.sqrt(varTempN*2*Math.PI) ) );
			humid = input.nextDouble();
			pHumidY = (Math.exp( -( Math.pow( (humid-avgHumidY), 2) / (2*varHumidY) ) ) / ( Math.sqrt(varHumidY*2*Math.PI) ) );
			pHumidN = (Math.exp( -( Math.pow( (humid-avgHumidN), 2) / (2*varHumidN) ) ) / ( Math.sqrt(varHumidN*2*Math.PI) ) );
			precip = input.nextDouble();
			pPrecipY = (Math.exp( -( Math.pow( (precip-avgPrecipY), 2) / (2*varPrecipY) ) ) / ( Math.sqrt(varPrecipY*2*Math.PI) ) );
			pPrecipN = (Math.exp( -( Math.pow( (precip-avgPrecipN), 2) / (2*varPrecipN) ) ) / ( Math.sqrt(varPrecipN*2*Math.PI) ) );

			pYes = (pTempY*pHumidY*pPrecipY);
			pNo = (pTempN*pHumidN*pPrecipN);
			
			if(pYes > pNo)
				out.println("1");
			if(pYes <= pNo)
				out.println("0");

		}
		out.close();	
		input.close();

	}

}
