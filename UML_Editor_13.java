
//Hi, �ڬO008 �]�N�O�A��xd....�ںƤF...


import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.UIManager;


public class UML_Editor_13 extends JFrame {
	private int button_width = 68;
	private int button_height = 49;
	
	protected JButton Select_button;
	protected JButton Association_button;
	protected JButton Generalization_button;	
	protected JButton Composition_button;
	protected JButton Class_button ;
	protected JButton Use_case_button;
	private JTextField txtSelect;
	private JTextField txtAssociationLine;
	private JTextField txtGeneralizationLine;
	private JTextField txtCompositionLine;
	private JTextField txtCreateAClass;
	private JTextField txtCreateUseCase;
	private static int panel_start_x = 200;	//����JPanel���_�l��m�C
	private static int panel_start_y = 20;
	private static int panel_width = 513;
	private static int panel_height = 531;
	private JMenuBar menuBar; 
	private JMenu File ;
	private JMenuItem Save; 
	private JMenu Open;
	private JMenu Edit;
	private JMenuItem ChangeObjName;
	private JMenuItem Group;
	private JMenuItem Ungroup ;
	
	//Operation instance variable. 
	//private Edit_panel just_use = new Edit_panel();
	
	public static int getPanel_start_x() {
		return panel_start_x;
	}

	public static int getPanel_start_y() {
		return panel_start_y;
	}

	public static int getPanel_width() {
		return panel_width;
	}

	public static int getPanel_height() {
		return panel_height;
	}

	//black the button
	private int pressed_button = 0;
	
	public static  Edit_panel canvas = new Edit_panel(); 
	//private int JFrame_width = 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		
				SwingUtilities.invokeLater( new Runnable() {
					@Override
					public void run() 
					{
						UML_Editor_13 frame = new UML_Editor_13();
						frame.setVisible(true);
						frame.setResizable(false);					//The frame size is fixed.
	
					
						//INITIALIZE THE DRAWING PLATE TO LET ALL CLASSES CAN DRAW ON THE SAME CANVAS
						Port.Set_Port_Graphics(canvas.getGraphics()); //This method will return null if this component is currently not displayable.	
																	  //http://docs.oracle.com/javase/6/docs/api/java/awt/Component.html#getGraphics%28%29
					}
				} );
	}

	/*
	 * Create the frame.
	 */
	public UML_Editor_13() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 687);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu Edit = new JMenu("EDIT");
		menuBar.add(Edit);
		
		JMenuItem ChangeObjName = new JMenuItem("Change Object Name");
		ChangeObjName.setSelected(true);
		Edit.add(ChangeObjName);
		
		JMenuItem Group = new JMenuItem("Group");
		Edit.add(Group);
		
		JMenuItem Ungroup = new JMenuItem("UnGroup");
		Edit.add(Ungroup);
		
		//SET layout. 
		getContentPane().setLayout(null);
		
		//1. Select BUTTON.
		Icon select_img = new ImageIcon(this.getClass().getResource("selection.jpg"));
		
		Select_button = new JButton( select_img );
		Select_button.setBackground(Color.WHITE);
		Select_button.setBounds(68, 48, 68 , 47);
		getContentPane().add(Select_button);		
		
		Select_button.setToolTipText("SELECT");	//tooltip
		
		//2. Association button
		Icon association_img = new ImageIcon(this.getClass().getResource("association.jpg"));//create image ICON
		
		Association_button = new JButton(association_img);	 // new JButton object. 	
		Association_button.setBackground(Color.WHITE);
		Association_button.setBounds(54, 148, 91 , 36 );		  //set the location.
		getContentPane().add(Association_button);			  //add to JFrame
		Association_button.setToolTipText("Association Line");//tooltip
		
		//3. Generalization button.
		Icon generalization_img = new ImageIcon(this.getClass().getResource("generalization.jpg"));
		
		Generalization_button = new JButton(generalization_img);				
		Generalization_button.setBackground(Color.WHITE);
		Generalization_button.setBounds(68, 226, 68 , 36 );
		getContentPane().add(Generalization_button);
		Generalization_button.setToolTipText("Generalization Line");

		//4. Composition button
		Icon composition_img = new ImageIcon(this.getClass().getResource("composition.jpg"));
		
		Composition_button = new JButton(composition_img );	
		Composition_button.setBackground(Color.WHITE);
		Composition_button.setToolTipText("Composition line");
		Composition_button.setBounds(68, 304, 68 , 36 );
		getContentPane().add(Composition_button);
	
		//5. Class button
		Icon class_img = new ImageIcon(this.getClass().getResource("class.jpg"));
		
		Class_button = new JButton(class_img);
		Class_button.setBackground(Color.WHITE);
		Class_button.setToolTipText("Create a class");
		Class_button.setBounds(68, 382, 68 , 49 );
		getContentPane().add(Class_button);
		
		
		//6. Use Class button
		Icon use_case_img = new ImageIcon(this.getClass().getResource("use_case.jpg"));
		
		Use_case_button = new JButton(use_case_img );
		Use_case_button.setBackground(Color.WHITE);
		Use_case_button.setToolTipText("Create an 'Use Case' ");
		Use_case_button.setBounds(68, 476, 68 , 49 );
		getContentPane().add(Use_case_button);
		
		txtSelect = new JTextField();
		txtSelect.setHorizontalAlignment(SwingConstants.CENTER);
		txtSelect.setText("Select");
		txtSelect.setBounds(68, 116, 67, 22);
		getContentPane().add(txtSelect);
		txtSelect.setColumns(10);
		
		txtAssociationLine = new JTextField();
		txtAssociationLine.setHorizontalAlignment(SwingConstants.CENTER);
		txtAssociationLine.setText("Association Line");
		txtAssociationLine.setBounds(44, 194, 114, 22);
		getContentPane().add(txtAssociationLine);
		txtAssociationLine.setColumns(10);
		
		txtGeneralizationLine = new JTextField();
		txtGeneralizationLine.setHorizontalAlignment(SwingConstants.CENTER);
		txtGeneralizationLine.setText("Generalization Line");
		txtGeneralizationLine.setBounds(44, 272, 114, 22);
		getContentPane().add(txtGeneralizationLine);
		txtGeneralizationLine.setColumns(10);
		
		txtCompositionLine = new JTextField();
		txtCompositionLine.setHorizontalAlignment(SwingConstants.CENTER);
		txtCompositionLine.setText("Composition Line");
		txtCompositionLine.setBounds(44, 350, 114, 22);
		getContentPane().add(txtCompositionLine);
		txtCompositionLine.setColumns(10);
		
		txtCreateAClass = new JTextField();
		txtCreateAClass.setHorizontalAlignment(SwingConstants.CENTER);
		txtCreateAClass.setText("Create a class");
		txtCreateAClass.setBounds(44, 442, 114, 22);
		getContentPane().add(txtCreateAClass);
		txtCreateAClass.setColumns(10);
		
		txtCreateUseCase = new JTextField();
		txtCreateUseCase.setHorizontalAlignment(SwingConstants.CENTER);
		txtCreateUseCase.setText("Create an Use Case");
		txtCreateUseCase.setBounds(44, 528, 114, 22);
		getContentPane().add(txtCreateUseCase);
		txtCreateUseCase.setColumns(10);
		
		canvas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(panel_start_x, panel_start_y, panel_width , panel_height);
		getContentPane().add(canvas);	
 
		//�@�}�l�Npass canvas object ��Mouse_Mode��canvas�h�� 
		Mouse_Mode.set_canvas(canvas); 

		    
//EVENT HANDLING.
   
	//JButton Event Wait.
	//�@�}�l�� �N���ӭn�]�w�n JButton ��L�̳B�bwait���A���ݦ�press��action �o��
    //�ҥH��bGUI�� default constructor.
			
			Select_button.addActionListener(new ActionListener(){ 
			  public void actionPerformed(ActionEvent e){
			  //Remove the last mouse event:
				  canvas.Remove_Mouse_Event();
			  //Exchange switch to Command Line method 
				  canvas.Set_Mouse_Event(new Selection_Mode());
		// !!!!!!!!!!!!! Set Condition!!!!!!!!!!!!!
				  canvas.set_MODE(1);
				  
		// SHOW black or white.
				  reset_back_color();				  
				  Select_button.setBackground(Color.black);
			  }
			});
		    
			Association_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				//Remove the last mouse event:
					  canvas.Remove_Mouse_Event();
				//Exchange switch to Command Line method 
					  canvas.Set_Mouse_Event(new AssociationLineMode());
					
					  canvas.set_MODE(2);
					 Association_line.set_Draw(canvas.getGraphics());
					reset_back_color();
					Association_button.setBackground(Color.black);
				}
			});
		    
			Generalization_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				//Remove the last mouse event:
					  canvas.Remove_Mouse_Event();
				//Exchange switch to Command Line method 
					  canvas.Set_Mouse_Event(new Gen_Line_Mode());
     				  canvas.set_MODE(3);
					
					//Set graphics. 
					//Generalization_line a_line = new Generalization_line();
					Generalization_line.set_Draw(canvas.getGraphics());
					reset_back_color();		
					Generalization_button.setBackground(Color.black);
				}
			});
		    
			Composition_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				//Remove the last mouse event:
					  canvas.Remove_Mouse_Event();
				//Exchange switch to Command Line method 
					  canvas.Set_Mouse_Event(new Cmp_Line_Mode());
   					  canvas.set_MODE(4);
					
					//Composition_line a_line = new Composition_line();
					Composition_line.set_Draw(canvas.getGraphics());
					reset_back_color();		
					Composition_button.setBackground(Color.black);
				}
			});
			
			Class_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					canvas.Remove_Mouse_Event();
					 //Exchange switch to Command Line method 
					  canvas.Set_Mouse_Event(new Draw_CD_Mode());
					  canvas.set_MODE(5);
					reset_back_color();
					Shape.set_which_Panel_to_be_drawn(canvas.getGraphics());
					
					//��Depth �ǵ�Edit_panel ��Depth �M�ᶶ�z������-1			
					Class_button.setBackground(Color.black);
				}
			});
			
			Use_case_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					canvas.Remove_Mouse_Event();
					 //Exchange "switch case" to Command Line method 
					canvas.Set_Mouse_Event(new Draw_UC_Mode());
					
					canvas.set_MODE(6);
					reset_back_color();
					
					Use_case.set_which_Panel_to_be_drawn(canvas.getGraphics());
					Use_case_button.setBackground(Color.black);
				}
			});
			
			 
		
			//Jmenubar: Edit:
			//ChangeObjName:
			ChangeObjName.addActionListener(
					new ActionListener()//anonymous inner class 
					{
						public void actionPerformed(ActionEvent e) 
						{			
							// YOU CAN'T PUT THIS STATEMENT ABOVE //Jmenubar: Edit:
							//just_use.set_graphics(canvas.getGraphics());
							canvas.Change_object_name(); //test
						}//end method actionPerformed.
					}//end anonymous inner class
			);//end call to addActionListener
			
			//Group action:
			Group.addActionListener(
					new ActionListener()//anonymous inner class 
					{
						public void actionPerformed(ActionEvent e) 
						{								
							canvas.Group();
						}//end method actionPerformed.
					}//end anonymous inner class
			);//end call to addActionListener
			
			//Ungroup action:
			Ungroup.addActionListener(
					new ActionListener()//anonymous inner class 
					{
						public void actionPerformed(ActionEvent e) 
						{					
							canvas.UnGroup();
						}//end method actionPerformed.
					}//end anonymous inner class
			);//end call to addActionListener
	}
	
		public void reset_back_color()
		{
			Select_button.setBackground(Color.WHITE);
			Association_button.setBackground(Color.WHITE);
			Generalization_button.setBackground(Color.WHITE);
			Composition_button.setBackground(Color.WHITE);
			Class_button.setBackground(Color.WHITE);
			Use_case_button.setBackground(Color.WHITE);			
		}		
}
