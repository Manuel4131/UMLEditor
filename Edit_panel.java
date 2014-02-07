/**
 * 2011 November Author: Manuel
 */

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import  java.awt.event.MouseListener;
import  java.awt.event.MouseMotionListener;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Edit_panel extends JPanel{
	
	//private Edit_panel edit_panel = new Edit_panel(); //& why are you so stupid?
	private int MODE = 0;		
	
// 	private List<List<drawing_class >> Object_status_history = new ArrayList<List<drawing_class >>();		//Object Status History �ܦ����󪺤@�Ӿ�v���ΡG1. �[�F2��  2.Group ���@��  3. �S�[�F�@��... 	
	private List<drawing_class > Current_object_status13 = new ArrayList<drawing_class >();//CRITICAL: �ثeCanvas�W�� ���󪺱���					
	private List<drawing_class > Next_object_status13 ;														//C.F:  Next_object_status13 �O�C���hnew �@�ӥ[�Jhistory �� //�o�Ӥ@�}�l�N�n���A�_�h�@�}�lnew �@��class ��ָˡH                                      
	private List<Connection_line> Connection_line_manager13= new ArrayList<Connection_line>();//�`��Panel �W�Ҧ�line���Ѽ�
//	private List<Connection_line> Nxt_cn_ln_st = new ArrayList<Connection_line>();
//	private List<Connection_line> current_move_connection_line = new ArrayList<Connection_line>(); //�ثe�n�Q���ʪ��u.

	
	private drawing_class press_obj;

//Group parameter: 
//	private List<List<Connection_line >> record_inner_lines = new ArrayList<List<Connection_line >>();
	private List<Connection_line > a_inner_line_record= new ArrayList<Connection_line>();
	
	private int press_x = 0;
	private int press_y = 0;
	
	Mouse_Mode Current_Event;
	private Graphics g = this.getGraphics();
	private boolean press_on_obj = false;
	private int Depth = 100;	 
//Group
	private drawing_class a_new_composite_obj;
	private int Group_Degree = 0;
//	private Point move_vector = new Point(0,0);
	/**
	 * @param event :new Selection_Mode() as a parameter. 
	 */
	public void Set_Mouse_Event(Mouse_Mode event)
	{
		Current_Event = event; 
		this.addMouseListener(event);
		this.addMouseMotionListener(event);
	}
	
	public void Add_To_Line_Mang(Connection_line added_line)
	{
		Connection_line_manager13.add(added_line);
	}
	
	/**
	 * @return canvas
	 */
	public List<drawing_class > get_Current_Obj_Status()
	{
		return Current_object_status13;
	}
	
	/**
	 * Update depth. 
	 * @return depth. 
	 */
	public int update_depth()
	{
		Depth--;
		return Depth; 
	}
	
	public void Remove_Mouse_Event()
	{
			this.removeMouseListener(Current_Event);			
			this.removeMouseMotionListener(Current_Event);	
	}

	public void set_MODE(int mode)
	{
		if( mode>= 1 && mode <= 6)
		{
			MODE = mode;
			System.out.print(MODE + "\n");
		}
		else	//FATAL ERROR. 
		{
			System.out.println("Input-Mode Error!\n");
			System.out.println("And MODE remains: " + MODE);
		}
	}//set_MODE
	
	public Edit_panel() {}   		//Edit_panel constructor.
	
	public void set_graphics(Graphics to_draw)
	{
		g = to_draw;
	}
//clear_panel: 
	public void clear_panel()
	{
		g= this.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0,0, UML_Editor_13.getPanel_width(), UML_Editor_13.getPanel_height());
		g.setColor(Color.BLACK);
		g.drawRect(0,0, UML_Editor_13.getPanel_width()-1, UML_Editor_13.getPanel_height()-1);
	}
	
//Clear && repaint!
	public void Repaint()
	{
		clear_panel();
		
		for(int i = 0; i < Current_object_status13.size(); i++) //FC! Form get Confirmed already!
		{
			Current_object_status13.get(i).draw();
		}
		//to repaint all the connection line
		for(int i = 0; i < Connection_line_manager13.size(); i++) //FC!
		{
			Connection_line_manager13.get(i).draw();
		}
	}
		
	public void Paint_Moved_Obj_Lines(drawing_class activated_obj)
	{
		clear_panel();
		activated_obj.draw();
		activated_obj.Draw_Link_Lines();
	}

	public boolean update_objects_state(int x, int y)
	{
		int index = Current_object_status13.size() - 1;
		boolean rt_value = false; 
		while(index >= 0)
		{
			if( Current_object_status13.get(index).is_selected(x, y) ) 
			{
				System.out.println("Item: "+ index +"in Current_object_status13 is selected.\n");
				Current_object_status13.get(index).select();
				rt_value = true; 
				
				for(int j= index -1; j >= 0; j--)
				{
					Current_object_status13.get(j).unselect();	
				}
				break; 
			}
			else
			{	
				Current_object_status13.get(index).unselect();
			}
			index--;			
		}//while loop
		return rt_value; 		
	}
	
	/**
	 * return null
	 * @return  A selected obj or null. 
	 */
	public drawing_class Return_Pressed_Obj(int x, int y)
	{
		for(int i = Current_object_status13.size() -1; i >= 0; i--)
		{
			if(Current_object_status13.get(i).is_selected(x, y))
			{
				return Current_object_status13.get(i); 
			}
		}
		return null;
	}
	
	//return a index in the Object_manger13 to get which object is selected.  
	public int Select_which_object()
	{
		for(int index = Current_object_status13.size()-1; index >=0   ; index --)
		{
			if(Current_object_status13.get(index).is_selected == true)
			{
				return index; 
			}
			
		}
		// if no ""return"" happens, it means that no true condition happens.
		return -2; 		
	}
	
	/**
	 * Scenario: According to the user's input to decide the four points' coordinates. 
	 * @param x1,y1,x2,y2 
	 * @return Rectangle. No possible to be null by default. 
	 */
	public Rectangle produce_a_rectangle(int x1,int y1, int x2, int y2)
	{
		Rectangle fianl_return_rec = null;
		
		if(x1 <= x2 && y1<= y2)
		{
			fianl_return_rec = new Rectangle(x1,y1,x2-x1,y2-y1);
		}		
		else if(x1 >= x2 && y2 >= y1)
		{
			fianl_return_rec = new Rectangle(x2,y1,x1-x2,y2-y1);
		}
		
		else if(x1 >= x2 && y1 >= y2)
		{
			fianl_return_rec = new Rectangle(x2,y2,x1 -x2, y1-y2);
		}
		
		else if(x2 >= x1 && y1 >=y2)
		{
			fianl_return_rec = new Rectangle(x1,y2,x2 - x1, y1-y2);
		}
		else
		{
			//nothing. 
		}
		return fianl_return_rec ;
	}
	
	public void connection_in_pressed (Connection_line conn_line)
	{
		press_on_obj = update_objects_state(press_x, press_y);//
		Repaint();
//If the user didn't	pressed on an object in the first, then break;
		if( press_on_obj  == false)
		{
			return ;
		}
		//else we pressed on an object, and need to find the connection point. ---> WRONG 
	    press_obj = Current_object_status13.get( Select_which_object() );
		if(press_obj  == null)
		{
			System.out.println("\n"+"Impossible 001 \n");
		}
				
		return; 
 }
		
	/**
	 *	Change_object_name: 
	 */
	public void Change_object_name()
	{
		press_obj = Current_Event.Get_Pressed_Obj();
		
		//Guard
		if(press_obj == null)
		{
			System.out.println("�n���I�磌��~���W�I�I");
			return; 
		}
				
		System.out.println("Ready to change an objects' name:\n");
		String input_text= JOptionPane.showInputDialog("Please Change Object Name","Change Object Name");//��J��r   
        
		if(input_text==null)            //�����ɤ~�|��null~
        {
        	Repaint();					//since the pop menu still overlaps the canvas, the Repaint is still required. 
        	return;
        }
    //Set object's name
		press_obj.setObject_name(input_text);
        Repaint();
	}
	
	Rectangle min_range ;
	List<drawing_class> for_find_min_rec = new ArrayList<drawing_class>();
	List<Integer> store_all_selected_obj_x = new ArrayList<Integer>();
	List<Integer> store_all_selected_obj_y = new ArrayList<Integer>();
	
	public void set_inner_lines_and_record_it()
	{
			a_inner_line_record.clear();
	}//set_inner_lines_and_record_it()
	
	//Group:
	public void Group()
	{
		int selected_itme_count = 0;
		update_depth();
		System.out.println("Depth in Edit_panel is: " +  Depth +"\n");

	//To initialize the Composite_Object first. 
		a_new_composite_obj = new Composite_Object(Depth);

		for(int i = 0; i < Current_object_status13.size(); i++)
		{
			if(Current_object_status13.get(i).is_selected == true)
			{
				a_new_composite_obj.Add_An_Obj(Current_object_status13.get(i));
				selected_itme_count ++;	
			}
		}
		
		//Guard.
		if( selected_itme_count < 2)
		{
			a_new_composite_obj.destroy_self();							     //clear components 
			return; 
		}

//Composite object survive. 
		a_new_composite_obj.set_is_selected_true();
		//System.out.println("Test: �X�Ӫ���Group�b�@�_�H�G"+ a_new_composite_obj.how_many_items() +"\n");
		//System.out.println("The depth of this composite obj is: "+ a_new_composite_obj.get_Depth() +"\n");
			Next_object_status13 = new ArrayList<drawing_class >();
			for(int i = 0 ; i< Current_object_status13.size() ; i++)
			{
				if(Current_object_status13.get(i).is_selected == false)
				{
					Next_object_status13.add(Current_object_status13.get(i));
				}
				else
				{
				}				
			}
				
				Next_object_status13.add(a_new_composite_obj);		//Add a new obj.	
				Current_object_status13 =Next_object_status13;
				Group_Obj_History.add(a_new_composite_obj);			//Record what composite object is stored.
				Group_Degree++;
	}//Group 
	
	private List<drawing_class> Group_Obj_History = new ArrayList<drawing_class>();
	private List<drawing_class> objects_in_group;
	public void UnGroup()
	{
		if(Group_Degree > 0 ) 
		{
			System.out.println("�i�H�Ѻc");
			Group_Degree--; 
		}
		else
		{
			return ; 
		}
		a_new_composite_obj = Group_Obj_History.get(Group_Degree);
		Remove_Obj_In_Object_Mang(a_new_composite_obj);
		System.out.println("Group_degree is :" + Group_Degree );
		
		if(Group_Degree < 0) return; 
		objects_in_group = a_new_composite_obj.Get_Containing_Obj();
		for(int i = 0 ; i< objects_in_group .size() ; i++)
		{
			Current_object_status13.add(objects_in_group.get(i));		
		}
			Collections.sort(Current_object_status13);
	}
	
	boolean k =false;
	public void Remove_Obj_In_Object_Mang(drawing_class removed_obj)
	{
		for(int i = 0; i < Current_object_status13.size() ; i++)
		{
			if(Current_object_status13.get(i) == removed_obj)
			{
				k = Current_object_status13.remove(removed_obj);
				System.out.println("k is " + k);
			}			
		}
	}
}//CLASS BRACKET!