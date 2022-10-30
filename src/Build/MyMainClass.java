//Omer Arviv 205967847


package Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MyMainClass {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void Generate(int n,int count){
        System.out.println(ANSI_RED+"New Execution with "+n+" boxes!");
        System.out.println(ANSI_GREEN+"The randomized generated boxes:");

        int[] Length =new int[n];
        int[] Width=new int[n];
        int[] Height=new int[n];
        int temp;
        int flag=1;

        Random rand=new Random();
        for(int i=0;i<n;i++){ // initializing the arrays
            while(flag==1) {
                temp = rand.nextInt(count)+1;
                for(int j=0;j<i;j++){
                    if(Length[j]==temp)
                        flag=0;
                }
                if(flag!=0) {
                    Length[i] = temp;
                    break;
                }
                else{
                    flag=1;
                }
            }
            flag=1;
            while(flag==1) {
                temp = rand.nextInt(count)+1;
                for(int j=0;j<i;j++){
                    if(Width[j]==temp)
                        flag=0;
                }
                if(flag!=0) {
                    Width[i] = temp;
                    break;
                }
                else
                    flag=1;
            }
            while(flag==1) {
                temp = rand.nextInt(count)+1;
                for(int j=0;j<i;j++){
                    if(Height[j]==temp)
                        flag=0;
                }
                if(flag!=0) {
                    Height[i] = temp;
                    break;
                }
                else
                    flag=1;
            }
        }
        Box[] boxes=new Box[n];
        for(int i=0;i<n;i++){
            boxes[i]=new Box(Length[i],Width[i],Height[i],i);
        }

        System.out.println(ANSI_YELLOW+"Box number         Length  Width  Height");
        for(Box box:boxes) {
            System.out.println(ANSI_RED+"\t" + box.number +ANSI_YELLOW+ "\t\t->\t\t" +ANSI_PURPLE+ box.length + "\t\t" + box.width + "\t\t" + box.height);
        }

        ///// ***********************************************************************************  /////
        ///// The Algorithm

        int max;
        int[]arr=new int[n+1];
        String[] strings=new String[n+1];
        HashMap<Integer, List<Box>> hashMap=new HashMap<>();
        arr[0]=0;
        arr[1]=boxes[0].height;
        List<Box> list=new ArrayList<>();
        list.add(boxes[0]);
        hashMap.put(arr[1],list);
        for(int i=1;i<n;i++){
            max=0;
            list=new ArrayList<>();
            for(int j=0;j<i;j++){
                if((boxes[j].length>boxes[i].length)&&(boxes[j].width>boxes[i].width)) {
                    if(arr[j+1]>max) {
                        max = arr[j + 1];
                    }
                }
            }
            if(hashMap.containsKey(max)) {
                for (Box box : hashMap.get(max)) {
                    list.add(box);
                }
            }
            arr[i+1]=boxes[i].height+max;
            list.add(boxes[i]);
            if(!hashMap.containsKey(arr[i+1])) {
                hashMap.put(arr[i + 1], list);
            }
        }
        max=arr[0];
        for(int key:hashMap.keySet()){
            if(key>max)
                max=key;
        }

        System.out.println(ANSI_BLUE+"\nThe max height is:  "+ANSI_RED+max);
        System.out.println(ANSI_GREEN+"The numbers of boxes who build it:\n");
        System.out.println(ANSI_YELLOW+"Box number         Length  Width  Height");
        for(Box box:hashMap.get(max)){
            System.out.println(ANSI_RED+"\t"+box.number+ANSI_YELLOW+"\t\t->\t\t"+ANSI_PURPLE+box.length+"\t\t"+box.width+"\t\t"+box.height);
        }
        System.out.println("\n\n");
    }
    public static void main(String args[]){
        System.out.println(ANSI_CYAN+"Omer Arviv 205967847\n");
        int n=20;
        int count=200;
        Generate(n,count);
        n=30;
        Generate(n,count);
    }
}
