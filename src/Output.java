import java.util.*;

public class Output {
    static Scanner console=new Scanner(System.in);
    public static void main(String[] args) {
        String imageName="";
        while(imageName.equals("")){
            System.out.print("Enter image name(support .tif & .raw only):");
            imageName=console.next();
            if(imageName.endsWith(".tif")){
                new ReadFile(imageName);
                System.out.println("Data image (.txt) generated!");
            }else if(imageName.endsWith(".raw")){
                int width=0,height=0;
                System.out.print("Enter width:");
                width=console.nextInt();
                System.out.print("Enter height:");
                height=console.nextInt();
                raw(imageName,width,height);
            }else{
                imageName="";
            }
        }
    }
    
    private static void raw(String imageName,int width,int height){
        String process="";
        while(process==""){
            System.out.println("Enter image process\n1.Patterning\n2.Dithering 1\n3.Dithering 2\n4.Enhancement\n5.Convolution\n6.Low Pass 3x3"
                    + "\n7.Low Pass 5x5\n8.High Pass\n9.High Boost\nAns:");
            process=console.next();
            if(process.equals("1")){
                new Patterning(imageName,width);
            }else if(process.equals("2")){
                new Dithering(1,imageName,width);
            }else if(process.equals("3")){
                new Dithering(2,imageName,width);
            }else if(process.equals("4")){
                new Enhancement(imageName);
            }else if(process.equals("5")){
                new KernelConvolution3x3(imageName,width,"convolution",new ArrayList<>(Arrays.asList(-1,0,1,
                                                                                                    -2,0,2,
                                                                                                    -1,0,1)));
            }else if(process.equals("6")){
                new KernelConvolution3x3(imageName,width,"low pass 3x3",new ArrayList<>(Arrays.asList(1,1,1,
                                                                                                    1,1,1,
                                                                                                    1,1,1)));
            }else if(process.equals("7")){
                new KernelConvolution5x5(imageName,width,"low pass 5x5",new ArrayList<>(Arrays.asList(1,1,1,1,1,
                                                                                                    1,1,1,1,1,
                                                                                                    1,1,1,1,1,
                                                                                                    1,1,1,1,1,
                                                                                                    1,1,1,1,1)));
            }else if(process.equals("8")){
                new KernelConvolution3x3(imageName,width,"high pass",new ArrayList<>(Arrays.asList(-1,-1,-1,
                                                                                                    -1,8,-1,
                                                                                                    -1,-1,-1)));
            }else if(process.equals("9")){
                new KernelConvolution3x3(imageName,width,"high boost",new ArrayList<>(Arrays.asList(-1,-1,-1,
                                                                                                    -1,12,-1,
                                                                                                    -1,-1,-1)));
            }else{
                process="";
            }
        }
    }
}
