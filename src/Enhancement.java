import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Enhancement {
    public Enhancement(String file){
        try{
            String outputfile=file.substring(0, file.length()-4)+" (Enhancement).raw";
            FileOutputStream MyOutputFile=new FileOutputStream(outputfile);
            FileInputStream MyInputFile=new FileInputStream(file);
            int value;
            int list[]=new int[256];
            int count=0;
            while((value=MyInputFile.read())!=-1){
                count++;
                list[value]+=1;
            }
            int runSum=0;
            ArrayList<Integer> enhanceList = new ArrayList<Integer>();
            for(int i=0;i<list.length;i++){
                runSum+=list[i];
                double normalized=(double)runSum/count;
                double multiply=normalized*(list.length);
                enhanceList.add(i, (int)multiply);
            }
            MyInputFile.close();
            MyInputFile=new FileInputStream(file);
            while((value=MyInputFile.read())!=-1){
                if(enhanceList.get(value)>255){
                    MyOutputFile.write(255);
                }else if(enhanceList.get(value)<=0){
                    MyOutputFile.write(0);
                }else{
                    MyOutputFile.write(enhanceList.get(value));
                }
            }
            MyInputFile.close();
            MyOutputFile.close();
        }catch(IOException ex){
            System.out.println("file output error!");
        }
    }
}
