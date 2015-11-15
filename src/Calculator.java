import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;

import java.text.DecimalFormat;
import java.math.RoundingMode;

public class Calculator extends JFrame implements ActionListener, ItemListener
{
	//JTextField array to easily reset the textboxs when reset is pressed
	JTextField t[] = new JTextField[]{new JTextField(5) , new JTextField(5), new JTextField(5), new JTextField(7), new JTextField(7)};
	JButton b1, b2, b3;
	public JLabel l1, l2, l3 , l4 , l5, l6, l7, l8;//l7 is the image/logo
	public String[] str = {"quick", "qu1", "qu2", "qu3", "qu4"};
	public double[] cache = {0, 0, 0, 0, 0, 0};
	//length of the array is the amount of number sets that can be entered, not all will be used, only the amount you enter in.
	public double[] nums = {1, 2, 3, 4, 5, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	public int counter = 0;
	//VVV up to 18 digit display history(40 sets), need to click one of the bottom selections for the above(most recent) to update, do not know why
	public String[] numscroll = {"Numbers entered so far: ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        ", "        "};
	public JList numlist;
	
	public Calculator()
	{
		super("Standard deviation calculator made by Johnny Ceja");
		setLayout(new FlowLayout());
		l1 = new JLabel("Enter the number(s) here: ");
		add(l1);
		add(t[0]);
		b1 = new JButton("Input number");
		add(b1);
		l2 = new JLabel("Press Input number once for every number set.");
		add(l2);
		b2 = new JButton("Calculate NOW");
		add(b2);
		l3 = new JLabel("Total number of number sets entered: ");
		add(l3);
		add(t[1]);
		l4 = new JLabel("Mean/Average of entered numbers: ");
		add(l4);
		add(t[2]);
		l6 = new JLabel("Standard Deviation without the -1 in (n-1): ");
		add(l6);
		add(t[4]);//standard deviation
		l5 = new JLabel("STANDARD DEVIATION: ");
		add(l5);
		add(t[3]);//without the -1
		Icon logo = new ImageIcon(getClass().getResource("logo.png"));//epic logo
		l7 = new JLabel(logo);
		add(l7);
		b3 = new JButton("RESET NOW");
		add(b3);
		numlist = new JList(numscroll);
		numlist.setVisibleRowCount(6);
		numlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(numlist), BorderLayout.SOUTH);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) 
	{
		if(event.getSource() == b1)//after pressed input
		{
			counter++;
			str[0] = t[0].getText();//quick = number typed
			cache[1] = Double.parseDouble(str[0]);//q1 = number typed
			cache[5] = cache[5] + cache[1];//add to total
			System.out.println(cache[5]);//debug
			t[0].setText("");//reset
			nums[counter] = cache[1];//nums array = to whatever typed in, saved
			numscroll[counter] = str[0];//string number placement = nums number placement, str[0] changes every time
			numlist = new JList(numscroll);//update the numlist to numscroll array of strings
			numlist.setVisibleRowCount(6);//visible row count of 6
			numlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//only select one at a time
			add(new JScrollPane(numlist), BorderLayout.SOUTH);//make list go to bottom of screen
			System.out.println("scroll: " + numscroll[counter - 1]);//debugging
			
		}
		if(event.getSource() == b2)//CALCULATE NOW
		{
			System.out.println("working");
			cache[2] = cache[5] / counter;//average/mean
			str[2] = Double.toString(cache[2]);//convert double to string
			str[1] = Double.toString(counter);//converts int to string to print to text box, is the nums typed in so far
			System.out.println(str[1]);//debug nums typed in
			t[1].setText(str[1]);//nums typed in 
			t[2].setText(str[2]);//average/mean
			for(int i = 1; i < (counter + 1); i++)
			{
				System.out.println("numbers equal : " + nums[i]);
			}
			for(int i = 1; i < (counter + 1); i++)
			{
			nums[i] = Math.pow((nums[i]- cache[2]), 2);//x minues the value of q3 then square
			System.out.println("x - mean before square : " + nums[i]);
			}
			for(int i = 1; i < (counter + 1); i++)//adds squares
			{
				cache[4] = nums[i];
				cache[0] = cache[4] + cache[0];
				System.out.println("add squares: " + cache[0]);
			}
			cache[3] = (cache[0] / (counter - 1));//ANSWER
			cache[4] = (cache[0] / (counter));//answer for population (n) not (n - 1)
			cache[3] = (Math.sqrt(cache[3]));//squareroot - 1 
			cache[4] = (Math.sqrt(cache[4]));//^^ not -1\
			DecimalFormat dec = new DecimalFormat("#.####");//format for truncation
			dec.setRoundingMode(RoundingMode.FLOOR);//round down/rounding mode
			str[3] = dec.format(cache[3]);//string[3-4] = to truncated cache[3-4]
			str[4] = dec.format(cache[4]);
			t[3].setText(str[3]);//textbox = to string(textboxs can not contain double variables)
			t[4].setText(str[4]);
		}
		if(event.getSource() == b3)//if press reset
		{
			for(int i = 0; i <= 4; i++)//reset all of the textboxs
			{
				t[i].setText("");
			}
			for(int i = 0; i < counter; i ++)//reset nums typed 
			{
				nums[i] = 0;
			}
			for(int i = 0; i <= 5; i++)//reset all of the memory variables for future use
			{
				cache[i] = 0;
				if(i <= 4)//str has one less value in the array than cache, so only do it after i >= 1(ones less than cache)
				{
					str[i] = "null";
				}
			}
			for(int i = 1; 40 >= i; i++)//start at counter, erase anything above that
			{
				numscroll[i] = "                  ";//18 digits is the same as the others to keep it consistent
			}
			counter = 0;//reset counter	
		}
	}
		
	@Override
	public void itemStateChanged(ItemEvent event) 
	{
		// Not going to be used(so far), but is needed which is why it is here
	}
}