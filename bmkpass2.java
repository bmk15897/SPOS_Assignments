import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;



class SymTab
{
	public int address;
	public int length;
	public String sName;
  public int index;
	SymTab(int i,String s,int a,int l)
	{
    index = i;
		address = a;
		length = l;
		sName = s;
	}
}

class LitTab
{
	public int address;
	public String lName;
  public int index;
	LitTab(int i,String l,int a)
	{
    index = i;
		address = a;
		lName = l;
	}
}


class pass2
{
	HashMap<Integer,String> tPot = new HashMap<Integer,String>();

	String tempIn;
	String toBePrinted;
	int LC;
	int sIndex;
	int lIndex;
	File file,fileIC,fileST,fileLT;
	Scanner sc = new Scanner(System.in);
	SymTab arrSym[] = new SymTab[20];
	LitTab arrLit[] = new LitTab[20];

  void init()
  {
    LC = 0;
    sIndex = 0;
    lIndex = 0;
    toBePrinted = "";

    tPot.put(1,"START");
    tPot.put(2,"END");
    tPot.put(3,"EQU");
    tPot.put(4,"LTORG");
    tPot.put(5,"ORIGIN");
    tPot.put(6,"DS");
    tPot.put(7,"DC");

  }

  public void p2()
  {
    try
    {


      //will contain the Symbol table
      System.out.println("ENTER THE NAME OF TEXT FILE IN WHICH SYMBOL TABLE IS PRESENT");	//	/home/pccoe/Downloads/samtext.txt
      tempIn = sc.next();

      fileST = new File(tempIn);
      Scanner fscST = new Scanner(fileST);

        while(fscST.hasNextLine())
        {
          tempIn = fscST.nextLine();
					String []arrStr = tempIn.split(" ");

          arrSym[sIndex] = new SymTab(sIndex,arrStr[1],Integer.parseInt(arrStr[2]),Integer.parseInt(arrStr[3]));
          sIndex+=1;


        }

        //will contain the Literal table
        System.out.println("ENTER THE NAME OF TEXT FILE IN WHICH LITERAL TABLE IS PRESENT");	//	/home/pccoe/Downloads/samtext.txt
        tempIn = sc.next();
        fileLT = new File(tempIn);
        Scanner fscLT = new Scanner(fileLT);

        while(fscLT.hasNextLine())
        {
          tempIn = fscLT.nextLine();
          String []arrStr = tempIn.split(" ");

          arrLit[lIndex] = new LitTab(lIndex,arrStr[1],Integer.parseInt(arrStr[2]));
          lIndex+=1;

        }


        //printing SymTab
        int iter = 0;
        System.out.println("Symbol table contents");
        for(iter=0;iter<sIndex;iter++)
        {
          System.out.println(arrSym[iter].index+" "+arrSym[iter].sName+" "+arrSym[iter].length+" "+arrSym[iter].address);
        }

        //printing SymTab
        iter = 0;
        System.out.println("Literal table contents");
        for(iter=0;iter<lIndex;iter++)
        {
          System.out.println(arrLit[iter].index+" "+arrLit[iter].lName+" "+arrSym[iter].address);
        }


        //will contain the Intermediate code
        System.out.println("ENTER THE NAME OF TEXT FILE IN WHICH INTERMEDIATE CODE IS PRESENT");	//	/home/pccoe/Downloads/samtext.txt
        tempIn = sc.next();
        fileIC = new File(tempIn);
        Scanner fscIC = new Scanner(fileIC);

        int cnt=0;
        System.out.println("after scanner");
        while(fscIC.hasNextLine())
        {
          tempIn = fscIC.nextLine();
          String []arrStr = tempIn.split(" ");


          if (arrStr[0].equals("AD"))
          {


            if (tPot.get(Integer.parseInt(arrStr[1])).equals("START"))
            {
              if (arrStr.length>2)
              {
                LC=Integer.parseInt(arrStr[3]);
              }

            }
            else if (tPot.get(Integer.parseInt(arrStr[1])).equals("END")) {
              LC+=lIndex;
            }
            else if (tPot.get(Integer.parseInt(arrStr[1])).equals("DS")) {
              LC+=arrSym[cnt].length;
            }
            else if(tPot.get(Integer.parseInt(arrStr[1])).equals("DC")) {

              System.out.println(LC+") 00 00 "+Integer.parseInt(arrStr[4]));
              LC+=1;

            }
          }
          else if (arrStr[0].equals("IS")) {
          	if (arrStr[3].equals("S")){
              	int symAddr = arrSym[Integer.parseInt(arrStr[4])].address;
              	System.out.println(LC+") 0"+arrStr[1]+" "+arrStr[2]+" "+symAddr);
              }
              else{
              	int litAddr = arrLit[Integer.parseInt(arrStr[4])].address;
              	System.out.println(LC+") 0"+arrStr[1]+" "+arrStr[2]+" "+litAddr);
              }
            LC+=1;

          }

        }



    }
    catch(Exception e)
		{
			System.out.println("Error "+e);
		}
  }
  public static void main(String[] args) {
    // System.out.println("Symbol Table Entries--");
    // System.out.println("How many entries in symbol table?")
    // int noOfEntriesSymTab = sc.nextInt();
    //
    // int i=0;
    // for(i=0;i<noOfEntriesSymTab;i++)
    // {
    //
    //
    //   System.out.println("For "+i+"th entry, enter the symbol name");
    //   int tempName = sc.nextLine();
    //   System.out.println("For "+i+"th entry, enter the symbol address");
    //   int tempAdd = sc.nextInt();
    //
    //   arrSym[sIndex] = new SymTab(tempName,tempAdd);
    //
    //   sIndex+=1;
    // }


    System.out.println("PASS 2");

    try{
      pass2 pTwo = new pass2();
			pTwo.init();
			pTwo.p2();
    }
    catch (Exception e) {
      System.out.println(e);
    }





  }

}
