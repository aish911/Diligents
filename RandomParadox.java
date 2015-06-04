import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class RandomParadox extends JFrame
{
	public RandomParadox()
	{

        super("Lines Drawing Demo");
        setLayout(null);
        
        JLabel pro_label = new JLabel("Probabilities");
        pro_label.setBounds(60, 30, 100, 40);
        add(pro_label);
        
        JLabel num_peo_label = new JLabel("Number of people");
        num_peo_label.setBounds(220, 500, 100, 40);
        add(num_peo_label);
//Labels of probabilities	        
        JLabel label_00 = new JLabel("0");
        label_00.setBounds(60,450, 50, 40);
        add(label_00);
        
        JLabel label_01 = new JLabel("0.1");
        label_01.setBounds(60,412, 50, 40);
        add(label_01);
        
        JLabel label_02 = new JLabel("0.2");
        label_02.setBounds(60,374, 50, 40);
        add(label_02);
        
        JLabel label_03 = new JLabel("0.3");
        label_03.setBounds(60,336, 50, 40);
        add(label_03);
        
        JLabel label_04 = new JLabel("0.4");
        label_04.setBounds(60,298, 50, 40);
        add(label_04);
        
        JLabel label_05 = new JLabel("0.5");
        label_05.setBounds(60,260, 50, 40);
        add(label_05);
        
        JLabel label_06 = new JLabel("0.6");
        label_06.setBounds(60,222, 50, 40);
        add(label_06);
        
        JLabel label_07 = new JLabel("0.7");
        label_07.setBounds(60,184, 50, 40);
        add(label_07);
        
        JLabel label_08 = new JLabel("0.8");
        label_08.setBounds(60,146, 50, 40);
        add(label_08);
        
        JLabel label_09 = new JLabel("0.9");
        label_09.setBounds(60,108, 50, 40);
        add(label_09);
        
        JLabel label_10 = new JLabel("1.0");
        label_10.setBounds(60,70, 50, 40);
        add(label_10);
        
//Labels of num people
        JLabel label_peo_3 = new JLabel("3");
        label_peo_3.setBounds(124,480, 50, 40);
        add(label_peo_3);
        
        JLabel label_peo_9 = new JLabel("9");
        label_peo_9.setBounds(158,480, 50, 40);
        add(label_peo_9);
        
        JLabel label_peo_14 = new JLabel("14");
        label_peo_14.setBounds(192,480, 50, 40);
        add(label_peo_14);
        
        JLabel label_peo_18 = new JLabel("18");
        label_peo_18.setBounds(228,480, 50, 40);
        add(label_peo_18);
        
        JLabel label_peo_23 = new JLabel("23");
        label_peo_23.setBounds(262,480, 50, 40);
        add(label_peo_23);
        
        JLabel label_peo_35 = new JLabel("35");
        label_peo_35.setBounds(296,480, 50, 40);
        add(label_peo_35);
        
        JLabel label_peo_47 = new JLabel("47");
        label_peo_47.setBounds(332,480, 50, 40);
        add(label_peo_47);
        
        JLabel label_peo_55 = new JLabel("55");
        label_peo_55.setBounds(366,480, 50, 40);
        add(label_peo_55);
        
        JLabel label_peo_70 = new JLabel("70");
        label_peo_70.setBounds(402,480, 50, 40);
        add(label_peo_70);
        
        JLabel label_peo_82 = new JLabel("82");
        label_peo_82.setBounds(436,480, 50, 40);
        add(label_peo_82);
        
        JLabel label_peo_100 = new JLabel("100");
        label_peo_100.setBounds(472,480, 50, 40);
        add(label_peo_100);
                
        JLabel red_box = new JLabel("By formula");
        red_box.setBounds(420,280, 200, 100);
        add(red_box);
        
        JLabel blue_box = new JLabel("By random experiments");
        blue_box.setBounds(420,320, 200, 100);
        add(blue_box);
        
        setSize(600, 600);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    
	}
	
	void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Graphics2D graphLine = (Graphics2D) g;
                        
        g2d.drawLine(100, 100, 100, 500);
        g2d.drawLine(100, 500, 500, 500);
//points in probabilities        
        g2d.drawLine(90, 462, 110, 462);
        g2d.drawLine(90, 424, 110, 424);
        g2d.drawLine(90, 386, 110, 386);
        g2d.drawLine(90, 348, 110, 348);
        g2d.drawLine(90, 310, 110, 310);
        g2d.drawLine(90, 272, 110, 272);
        g2d.drawLine(90, 234, 110, 234);
        g2d.drawLine(90, 196, 110, 196);
        g2d.drawLine(90, 158, 110, 158);
        g2d.drawLine(90, 120, 110, 120);
        
//points of num people
        g2d.drawLine(135, 490, 135, 510);
        g2d.drawLine(170, 490, 170, 510);
        g2d.drawLine(205, 490, 205, 510);
        g2d.drawLine(240, 490, 240, 510);
        g2d.drawLine(275, 490, 275, 510);
        g2d.drawLine(310, 490, 310, 510);
        g2d.drawLine(345, 490, 345, 510);
        g2d.drawLine(380, 490, 380, 510);
        g2d.drawLine(415, 490, 415, 510);
        g2d.drawLine(450, 490, 450, 510);
        g2d.drawLine(485, 490, 485, 510);       
        
// graph lines by formula
        graphLine.setColor(Color.RED);
        graphLine.drawLine(100, 500,135,490);
        graphLine.drawLine(135, 490, 170, 465);
        graphLine.drawLine(170, 465, 205, 415);
        graphLine.drawLine(205, 415, 240, 370);
        graphLine.drawLine(240, 370, 275, 315);
        graphLine.drawLine(275, 315, 310, 190);
        graphLine.drawLine(310, 190, 345, 135);
        graphLine.drawLine(345, 135, 380, 130);
        graphLine.drawLine(380, 130, 415, 126);
        graphLine.drawLine(415, 125, 450, 123);
        graphLine.drawLine(450, 123, 485, 120);
        
//graph lines by random experiments
        graphLine.setColor(Color.BLUE);
        graphLine.drawLine(100, 500,135,493);
        graphLine.drawLine(135, 493, 170, 450);
        graphLine.drawLine(170, 450, 205, 417);
        graphLine.drawLine(205, 417, 240, 374);
        graphLine.drawLine(240, 374, 275, 311);
        graphLine.drawLine(275, 311, 310, 205);
        graphLine.drawLine(310, 205, 345, 142);
        graphLine.drawLine(345, 142, 380, 132);
        graphLine.drawLine(380, 132, 415, 125);
        graphLine.drawLine(415, 125, 450, 122);
        graphLine.drawLine(450, 122, 485, 120);
        
        g2d.setPaint(Color.RED);
        g2d.fill(new Rectangle(380, 350, 25, 25));
        
        g2d.setPaint(Color.BLUE);
        g2d.fill(new Rectangle(380, 390, 25, 25));
        
 
    }
 
    public void paint(Graphics g) {
        super.paint(g);
        drawLines(g);
    }
	
	public static void main(String[] args) 
	{
		System.out.println("Suppose n be number of people,\nthen probability of two people having same birthday is calculated by formula :\n");
		System.out.println("(365!)/((365-n)!*(365^n))");
		float for_num_3 = formula_computing(3);
		float for_num_9 = formula_computing(9);
		float for_num_14 = formula_computing(14);
		float for_num_18 = formula_computing(18);
		float for_num_23 = formula_computing(23);
		float for_num_35 = formula_computing(35);
		float for_num_47 = formula_computing(47);
		float for_num_55 = formula_computing(55);
		float for_num_70 = formula_computing(70);
		float for_num_82 = formula_computing(82);
		float for_num_100 = formula_computing(100);
		float for_num_150 = formula_computing(150);
		
		System.out.println("\nProbability that 2 people have same birthday using general formula for following number of people : \n");
		System.out.println("No. of people                Probability");
		System.out.println("   3                          "+for_num_3);
		System.out.println("   9                          "+for_num_9);
		System.out.println("   14                         "+for_num_14);
		System.out.println("   18                         "+for_num_18);
		System.out.println("   23                         "+for_num_23);
		System.out.println("   35                         "+for_num_35);
		System.out.println("   47                         "+for_num_47);
		System.out.println("   55                         "+for_num_55);
		System.out.println("   70                         "+for_num_70);
		System.out.println("   82                         "+for_num_82);
		System.out.println("   100                        "+for_num_100);
		System.out.println("   150                        "+for_num_150);
		
		System.out.println("__________________________________________________________________________");
		
		System.out.println("\nEnter any key and press enter key to see result by random experimemts");
		System.out.println("__________________________________________________________________________");
		System.out.println("The following experiment is carried carried on following sample points:");
		System.out.println("3,9,14,18,23,35,47,55,70,82,100");
		System.out.println("Experiment is carried out 50 times to take average.\n");
		Scanner sc = new Scanner(System.in);
		String ch = sc.next();
		
		
		float num_3 = Calculate(3);
		float num_9 = Calculate(9);
		float num_14 = Calculate(14);
		float num_18 = Calculate(18);
		float num_23 = Calculate(23);
		float num_35 = Calculate(35);
		float num_47 = Calculate(47);
		float num_55 = Calculate(55);
		float num_70 = Calculate(70);
		float num_82 = Calculate(82);
		float num_100 = Calculate(100);
		//float num_150 = Calculate(150);
		
		System.out.println("\nProbability that 2 people have same birthday by random experiments for following number of people: \n");
		System.out.println("No. of people                Probability");
		System.out.println("   3                          "+num_3);
		System.out.println("   9                          "+num_9);
		System.out.println("   14                         "+num_14);
		System.out.println("   18                         "+num_18);
		System.out.println("   23                         "+num_23);
		System.out.println("   35                         "+num_35);
		System.out.println("   47                         "+num_47);
		System.out.println("   55                         "+num_55);
		System.out.println("   70                         "+num_70);
		System.out.println("   82                         "+num_82);
		System.out.println("   100                        "+num_100);
		//System.out.println("   150                        "+num_150);
		
		System.out.println("\nEnter any key and press enter key to see the comparison\n");
		sc.next();
		
		System.out.println("No. of people                Probability by formula               Probability by Random Experiments");
		System.out.println("   3                          "+for_num_3+"                         "+num_3);
		System.out.println("   9                          "+for_num_9+"                         "+num_9);
		System.out.println("   14                         "+for_num_14+"                          "+num_14);
		System.out.println("   18                         "+for_num_18+"                          "+num_18);
		System.out.println("   23                         "+for_num_23+"                           "+num_23);
		System.out.println("   35                         "+for_num_35+"                           "+num_35);
		System.out.println("   47                         "+for_num_47+"                           "+num_47);
		System.out.println("   55                         "+for_num_55+"                          "+num_55);
		System.out.println("   70                         "+for_num_70+"                           "+num_70);
		System.out.println("   82                         "+for_num_82+"                          "+num_82);
		System.out.println("   100                        "+for_num_100+"                           "+num_100);
		//System.out.println("   150                        "+for_num_150+"                                 "+num_150);
		
		System.out.println("\nEnter any key and press enter key to view graphs\n");
		sc.next();
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RandomParadox().setVisible(true);
            }
        });
		
		System.out.println("\nProbabilities by formula and random experiments are same approximately\n");
		System.out.println("Thus Birthday Paradox is Valid .");
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	}
	
	public static float Calculate(int num_people)
	{
		//System.out.println("\n\nCalculating probability of "+num_people+" having same birthday\n\n");
		Random random = new Random();
		
		int start = 1;
		int end = 365;
		
		int cnt = 0;
		int loop_cnt = 50;
		float prob = 0;
		long range = (long)end - (long)start + 1;
		
		int idx;
		long frac;
		int randomNumber;
		float sum = 0;
		float avg = 0;
		
		/*System.out.println("Enetr number of people \n");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();*/
		
		int[] intArr = new int[num_people];
		
		for(int a = 0;a<10;a++)
		{
		
		for (int x = 0;x < loop_cnt;x++)
		{
		int flag=0;
		for (idx = 0 ; idx<num_people ; ++idx)
		{
			frac = (long)(range * random.nextDouble());
			randomNumber = (int) (frac + start);
			intArr[idx] = randomNumber;
			//System.out.println("Generated: "+randomNumber);
		}
		
		for (int i = 0;i<num_people;i++)
		{
			System.out.print(" "+intArr[i]+" ");
		}
		System.out.println();
		//String[] strArray = {"abc","pqr","abc","xyz","pqr","xyz"};
		
		for(int i = 0;i<intArr.length;i++)
		{
			for(int j = i+1;j<intArr.length;j++)
			{
				if((intArr[i] == (intArr[j])) && (i!=j))
				{
					cnt++;
					flag=1;
					break;
					
					//System.out.println("Duplicate element is "+intArr[i]);
				}
				
			}
			if(flag == 1)
			{
				break;
			}
		}
		}
		
		prob = (float)cnt/(float)loop_cnt;
		//System.out.println("\n\nIf "+num+" people are selected at random "+loop_cnt+" times then probability of someone sharing birthday with someone is = " +prob);
		System.out.println("======================================================================================================");
		sum = (float)sum + (float)prob;
		}
		avg = (float)prob/(float)10;
		//System.out.println((float)sum);
		System.out.println("\nIf "+num_people+" people are selected at random "+loop_cnt+" times then probability of someone sharing birthday with someone is = " +avg+"\n");
		return avg;
	}
	
	public static float formula_computing(int for_num)
	{

	    //int n;																		// Variable declaration
	    BigDecimal fact = new BigDecimal("1");
	    BigDecimal tsf = new BigDecimal("1");
	    BigDecimal res;
	    float result;
	    float fin_res;
	    BigDecimal a,b;
	    a = new BigDecimal("365");
	    /*
	    Scanner input = new Scanner(System.in);
	 
	    System.out.println("Enter the number of persons in room :");				// Accept input
	    n = input.nextInt();*/
	    
	    tsf = factorial(365);														// Calling factorial function
	    fact = factorial(365-for_num);
	    
	    b = a.pow(for_num);																// 365 to the power n
	    BigDecimal c = fact.multiply(b);											
	    res = tsf.divide(c, 8, RoundingMode.CEILING);
	    result = res.floatValue();						
	    
	    fin_res = 1 - result;														// Final probability
	    
	    //System.out.println("Probability of some pair having same birthday = " + fin_res);
	    
	  return fin_res;
	}
	
	 public static BigDecimal factorial(int n){
		 
		    int c;
		    BigDecimal inc = new BigDecimal("1");
		    BigDecimal fac = new BigDecimal("1");
		    for (c = 1; c <= n; c++) {
		      fac = fac.multiply(inc);												// Calculating factorial of n
		      inc = inc.add(BigDecimal.ONE);
		    }
		    return fac;																// return factorial
	  }
	
}
