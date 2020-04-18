import java.io.*;
import java.util.ArrayList;

public class KernelConvolution5x5 {
    static ArrayList<Integer> array;
    public KernelConvolution5x5(String file,int width,String process,ArrayList<Integer>kernelArray){
        try{
             array = new ArrayList<Integer>();
            String outputfile=file.substring(0, file.length()-4)+" ("+process+").raw";
            FileOutputStream MyOutputFile=new FileOutputStream(outputfile);
            FileInputStream MyInputFile=new FileInputStream(file);
            int row=MyInputFile.available()/width;
            for(int i=0;i<width+2;i++){
                array.add(-1);
            }
            for(int i=0;i<width+2;i++){
                array.add(-1);
            }
            for(int j=0;j<row;j++){
                array.add(-1);
                array.add(-1);
                for(int i=0;i<width;i++){
                    array.add(MyInputFile.read());
                }
                array.add(-1);
                array.add(-1);
            }
            for(int i=0;i<width+2;i++){
                array.add(-1);
            }
            for(int i=0;i<width+2;i++){
                array.add(-1);
            }
            for(int z=0;z<array.size();z++){
                if(array.get(z)!=-1){
                    MyOutputFile.write(findValue(z,width,kernelArray));
                }
            }
            MyInputFile.close();
            MyOutputFile.close();
        }catch(IOException ex){
            System.out.println("file output error!");
        }
    }
    
    static int findValue(int i,int width,ArrayList<Integer>kernelArray){
        ArrayList<Integer> myArray = new ArrayList<Integer>();
        int value;
        myArray.add(value= ((array.get(i-(width*2)-4-2)!=null) ? array.get(i-(width*2)-4-2) : 0));
        myArray.add(value= ((array.get(i-(width*2)-4-1)!=-1) ? array.get(i-(width*2)-4-1) : 0));
        myArray.add(value= ((array.get(i-(width*2)-4)!=-1) ? array.get(i-(width*2)-4) : 0));
        myArray.add(value= ((array.get(i-(width*2)-4+1)!=-1) ? array.get(i-(width*2)-4+1) : 0));
        myArray.add(value= ((array.get(i-(width*2)-4+2)!=-1) ? array.get(i-(width*2)-4+2) : 0));
        
        myArray.add(value= ((array.get(i-width-2-2)!=-1) ? array.get(i-width-2-2) : 0));
        myArray.add(value= ((array.get(i-width-2-1)!=-1) ? array.get(i-width-2-1) : 0));
        myArray.add(value= ((array.get(i-width-2)!=-1) ? array.get(i-width-2) : 0));
        myArray.add(value= ((array.get(i-width-2+1)!=-1) ? array.get(i-width-2+1) : 0));
        myArray.add(value= ((array.get(i-width-2+2)!=-1) ? array.get(i-width-2+2) : 0));
        
        myArray.add(value= ((array.get(i-2)!=-1) ? array.get(i-2) : 0));
        myArray.add(value= ((array.get(i-1)!=-1) ? array.get(i-1) : 0));
        myArray.add(array.get(i));
        myArray.add(value= ((array.get(i+1)!=-1) ? array.get(i+1) : 0));
        myArray.add(value= ((array.get(i+2)!=-1) ? array.get(i+2) : 0));
        
        myArray.add(value= ((array.get(i+width+2-2)!=-1) ? array.get(i+width+2-2) : 0));
        myArray.add(value= ((array.get(i+width+2-1)!=-1) ? array.get(i+width+2-1) : 0));
        myArray.add(value= ((array.get(i+width+2)!=-1) ? array.get(i+width+2) : 0));
        myArray.add(value= ((array.get(i+width+2+1)!=-1) ? array.get(i+width+2+1) : 0));
        myArray.add(value= ((array.get(i+width+2+2)!=-1) ? array.get(i+width+2+2) : 0));
        
        myArray.add(value= ((array.get(i+(width*2)+4-2)!=-1) ? array.get(i+(width*2)+4-2) : 0));
        myArray.add(value= ((array.get(i+(width*2)+4-1)!=-1) ? array.get(i+(width*2)+4-1) : 0));
        myArray.add(value= ((array.get(i+(width*2)+4)!=-1) ? array.get(i+(width*2)+4) : 0));
        myArray.add(value= ((array.get(i+(width*2)+4+1)!=-1) ? array.get(i+(width*2)+4+1) : 0));
        myArray.add(value= ((array.get(i+(width*2)+4+2)!=-1) ? array.get(i+(width*2)+4+2) : 0));
        
        return kernelMatrix(kernelArray,myArray);
    }
    
    static int kernelMatrix(ArrayList<Integer>kernelArray,ArrayList<Integer>myArray){
        int ans=0;
        int divide=0;
        for(int i=0;i<kernelArray.size();i++){
            ans+=kernelArray.get(i)*myArray.get(i);
            divide+=kernelArray.get(i);
        }
        if(divide!=0){
            ans/=divide;
        }
        if(ans<0){
            ans=0;
        }else if(ans>255){
            ans=255;
        }
        return ans;
    }
}
