package util;

/**
 * Created by matthewletter on 11/8/14.
 */
import Core.EVector;
import com.sun.tools.internal.ws.wsdl.document.Import;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by matthewletter on 10/30/14.
 * used to import data from files
 */
public class ImportData {
    Vector<EVector> Data;

    /**
     * takes a file and parses it into a Vector of data classes
     * can get data back by calling getData() as a Vector
     * @param filename String name of file path to parse
     * @throws Exception
     */
    public ImportData(String filename){
        this.Data = new Vector<EVector>();
        System.out.println(filename);
        String str;
        String strLine[];
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filename));
            int count = 0;
            while (scanner.hasNext()) {
                str = scanner.nextLine();
                //clean data
                str = str.replaceAll("\\s+", " ");
                strLine = str.split(" ");

                //build  VectorDataFor1SetOfInputs into a vector

                if (strLine.length == 4) {

                    double tempvals[] = new double[strLine.length];
                    tempvals[0] = Double.parseDouble(strLine[0]);
                    tempvals[1] = Double.parseDouble(strLine[1]);
                    tempvals[2] = Double.parseDouble(strLine[2]);
                    tempvals[3] = Double.parseDouble(strLine[3]);
                    EVector tempData = new EVector();
                    tempData.add(tempvals[2]);
                    tempData.add(tempvals[3]);
                    Data.add(tempData);
                }
                count++;
            }
//            System.out.println(count);
            scanner.close();
        }
        catch(Exception e){

        }
    }
    /**
     * get the data vector
     * @return
     */
    public Vector<EVector> getData()
    {
        return this.Data;
    }

    @Override
    public String toString() {
        return "Data{" +
                "Data=" + Data.get(0).get(0) + Data.get(0).get(1)+
                '}'+"\\n Size="+ Data.size();
    }
}
