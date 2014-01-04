
/* 2011/11/06 �t�d��ӵe�O���ʧ@,�D�`�����n...�ҥH�]�ܦM�I...


 * 
 * */

//2011 11 18���j�ŧG ���Ҧ��O��getgrpaphics ���F�� ���Ӿ��T���U��method �b�ҩ�java �O���ꪺ�y�� ���pc�� �󤣭n��c++ ���F
					//�A�o"���W"��X���N��� �_�h�N���c sharp �ӧ����M��
//2011 11 23: Edition003 �ϥ�... 4131�I
//Ediction 
//

//2011 11 25 18:00 Edition 4 Created. For the depth, For the Group.
//2011 11 28 Toward to the final step. �ثe�pmove��bug �ױ��F Group/ Ungroup �]ok��Compositve Object ����move
//2011 11 29 ���󪺵��c �Ѻc��� update Current_Object_status ���o��@�k "���󪺲��ʩM��ܬO�S�����D��"(�ֶq��զܤ֬O) ��O�I �u�@�����c��u���Ĥ@�h��composite object �䤺���u�|�@�_�� �~���u�|���},�t�~...�A�h���c�ɡA�Ҧ���composite object �䤺���u�q�q���|��۰ʤF = |||=
//2011 12 24: 007: 149 150 151 �O��U�ƹ��ƥ�C
//			   Line 248 Change method: Surgery001
//             Surgery002 getconnectionPoint to Get_Connection_Port in class_diagram && use case


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
	
	private List<List<drawing_class >> Object_status_history = new ArrayList<List<drawing_class >>();		//Object Status History �ܦ����󪺤@�Ӿ�v���ΡG1. �[�F2��  2.Group ���@��  3. �S�[�F�@��... 	
	private int History = 0 ;	//�f��Object_status_history :history ���N
	private List<drawing_class > Current_object_status13 = new ArrayList<drawing_class >();//CRITICAL: �ثeCanvas�W�� ���󪺱���					
	private List<drawing_class > Next_object_status13 ;														//C.F:  Next_object_status13 �O�C���hnew �@�ӥ[�Jhistory �� //�o�Ӥ@�}�l�N�n���A�_�h�@�}�lnew �@��class ��ָˡH                                      
	private List<Connection_line> Connection_line_manager13= new ArrayList<Connection_line>();//�`��Panel �W�Ҧ�line���Ѽ�
	private List<Connection_line> Nxt_cn_ln_st = new ArrayList<Connection_line>();
	private List<Connection_line> current_move_connection_line = new ArrayList<Connection_line>(); //�ثe�n�Q���ʪ��u.
	private List<My_Integer> indicate_line_start_or_end = new ArrayList<My_Integer>();
	
	//STILL NEEDED! 
	private drawing_class press_obj;
	
	//Group parameter: 
	private List<List<Connection_line >> record_inner_lines = new ArrayList<List<Connection_line >>();
	private int group_degree = -1;
	private List<Connection_line > a_inner_line_record= new ArrayList<Connection_line>();
	
	private int press_x = 0;
	private int press_y = 0;
	private int released_x = 0;
	private int released_y = 0;
	private int drag_x = 0;
	private int drag_y = 0;
	
	//�O��ثe��mouse event ���U�@���s��event �nregister�e �o��M���L �_�hlist���|�����event  DEITEL Page532
	Mouse_Mode Current_Event;
	private final Point error_point = new Point(-1,-1);
	private Graphics g = this.getGraphics();
	private boolean press_on_obj = false;
	private boolean released_on_obj= false;
	private drawing_class released_obj;
	private Point connection_line_start_pt;
	private Point connection_line_end_pt;
	private Generalization_line gen_line; 
	private Composition_line comp_line; 
	private Association_line ass_line;
	
	//the 3 parameter above are ready to one.
	private Connection_line connection_line_in_three; 
	private boolean draw_and_added_or_not = true;  
	private int Depth = 100;	//Depth. ����`�� ��Edit_panel �v�T   �]������ͦ��Ӥ@�����ܲ`�ת��Ѽ�	�`�׭�0-99,�Y�Y�Ӫ��󪺲`�׭Ȥ��L����`�׭Ȥ֡A�bø�ϮɡA�Ӫ��������л\��L����
							//�@�}�l�����Ӫ���`�׬O99 ���U�� �Cnew�@�� �`�״N��L�֤@ 
	//Group
	private drawing_class a_new_composite_obj;
	private Rectangle Selection_range ;
	
	private Point move_vector = new Point(0,0);	//�A�٦ۭqPoint �u�O�����誺���I
	private int move_segment_start_x = 0;
	private int move_segment_start_y = 0;
	
	/**
	 * ��U�U���U�˪��ƥ� �i��Oselect �i��Oassociation...
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
	 * @return canvas�W������޲zlist ���� �ΨӦs��edit_panel��Current_object_status13
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
			this.removeMouseMotionListener(Current_Event);	//�O�ѤF Mouse_Mode is a MouseListener �ҥH��M�i�H��o && �L�]�OMouseMotionListener 
															//�A�����Devent�O����(drag �Omotion) �ҥH���䳣�n�R�� �p�P���䳣�n�[�@�ˡC
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
	//clear_panel: �⪩�������b�C
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
	
	/**
	 * �u�w�ﲾ�ʨ쪺object�M�u�i��repaint.
	 * Pre: ��Drag �h�۴��L�C 
	 */
	public void Paint_Moved_Obj_Lines(drawing_class activated_obj)
	{
		clear_panel();
		activated_obj.draw();
		activated_obj.Draw_Link_Lines();
	}

	//��ߤ@���I��쪺drawing_class select ��L��Unselect �άO���S�Q���ҥH��unselect.	
	//ed04: �A�ӭn�ק諸 method �]���p½��
	public boolean update_objects_state(int x, int y)
	{
		int index = Current_object_status13.size() - 1;
		boolean rt_value = false; 
		while(index >= 0)
		{
			if( Current_object_status13.get(index).is_selected(x, y) ) 
			{
				System.out.println("Item: "+ index +"in Current_object_status13 is selected.\n");
				//true:����쪫��G update �L��is_select ��true��L�H�����Ofalse.
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
	 * ��J�y�СA�^���I��쪺���� //�p�G���S�� �ֳQselect �N��L�I��ťճB				
	 * �p�G�S�I�����F��]�t�u �]�N��S���Qselect��I ��P�I��ťճB (future extension.)
	 * return null
	 * @return �I��쪺���� || null
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
	
	//�^�Ǥ@��rectangle ��O�p�G �̭���rectangle �ͦ����~ �|�^��null
	/**
	 * @param x1,y1,x2,y2 �O�ƹ��h�I�諸��Ӯy���I����O��x,y �y��
	 * @return �^�Ǥ@��Rectangle. No possible to be null by default. 
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
		
	//Change_object_name:
	public void Change_object_name()
	{
		int str_start_x = 0;
		int str_start_y = 0;
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
		
    //Set ��쪺object's name
		press_obj.setObject_name(input_text);
        Repaint();
	}
	private drawing_class traversal_in_Current_object_status13;
	
	//�ǤJ�@�Ӯy�� �hmethod�|�̷ӳo�Ӯy�Хhupdate �C�Ӫ���Q����ΡG
	//���i�ೣ�S�Q�� ��̦h�u�|���@�ӳQ�� �Mrange select�O���P���I�I
	
	private boolean select_any_obj = false;

	//�u�n�ƹ��@�I��canvas �N�@�w�|��Ҧ���objects �y���v�T�G�i��1.�����ܦ�unselect 2. �u���@�ӬOselect

	
	//Group method. 
	
	/**
	 * What: ��group �b�@�_������䶡���s�u�s�_��
	 * Pre:
	 * Ope: Group �e�|�Φ� rectangle �ǤJ��Ѽ� �M��h��Connection_line_manager13�������ǽu�O�b�̭��� �s�_�ӡC
	 */
	/*
	public void record_a_group_inner_line(Rectangle group_region)
	{
		//�d�C�@��u�A�p�G���b group_region���N�[�J
		for(int i = 0; i < Connection_line_manager13.size(); i++)
		{
			int start_point_x =  (int)Connection_line_manager13.get(i).getStart_point().getX();
			int start_point_y = (int)Connection_line_manager13.get(i).getStart_point().getY();
			int end_point_x = (int)Connection_line_manager13.get(i).getEnd_point().getX();
			int end_point_y = (int)Connection_line_manager13.get(i).getEnd_point().getY();
				//�p�Ggroup region �]�[�F�u���_�l�I�M���I �N��]�t�F�o��u
					if( (group_region.contains( start_point_x  , start_point_y )) &&( group_region.contains( end_point_x , end_point_y ) ))
					{
						a_inner_line_record.add(Connection_line_manager13.get(i));
						
					}
		}
		record_inner_lines.add(a_inner_line_record);				//�����Ĥ@�h������: �Ĥ@�h�Orecord_inner_lines[0]
		group_degree++;									//�ҥH�ݷ|�O�ĤG�h
		
	}
	*/
	
	
	Rectangle min_range ;
	List<drawing_class> for_find_min_rec = new ArrayList<drawing_class>();
	List<Integer> store_all_selected_obj_x = new ArrayList<Integer>();
	List<Integer> store_all_selected_obj_y = new ArrayList<Integer>();
	
	public void set_inner_lines_and_record_it()
	{
			//reset variable. 
			a_inner_line_record.clear();
			
	}//set_inner_lines_and_record_it()
	
	//An obj to be added. 
	private drawing_class Selected_obj ;
	private drawing_class latest_grouped_Obj; 
	//Group:
	public void Group()
	{
		int selected_itme_count = 0;
		
		update_depth();
		System.out.println("Depth in Edit_panel is: " +  Depth +"\n");
		
	//��@�}�l���Υ[�JGroup History. 
		//Group_History.add(Current_object_status13);
	
	//To initialize the Composite_Object first. 
		a_new_composite_obj = new Composite_Object(Depth);
		//lines_within_composite_obj = new Composite_Line();
		//a_inner_line_record.clear();
		
		//�إ�Selection Range:
		//Selection_range  =produce_a_rectangle(Current_Event.press_x,Current_Event.press_y,Current_Event.released_x ,Current_Event.released_y ); 

		//��إߤ@�� �s��composite ����: ���쪺����[�Jcomposite 
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
			System.out.println("�ܤ֭n��ӥH�W������~��Group! \n");
			a_new_composite_obj.destroy_self();							     //clear components 
			return; 
		}
 //Composite object survive ...(Somehow, it's critical. 
		a_new_composite_obj.set_is_selected_true();
		
		System.out.println("Test: �X�Ӫ���Group�b�@�_�H�G"+ a_new_composite_obj.how_many_items() +"\n");
		System.out.println("The depth of this composite obj is: "+ a_new_composite_obj.get_Depth() +"\n");
		//�}�l��sObject status.
			Next_object_status13 = new ArrayList<drawing_class >();
			for(int i = 0 ; i< Current_object_status13.size() ; i++)
			{
				if(Current_object_status13.get(i).is_selected == false)
				{
					Next_object_status13.add(Current_object_status13.get(i));
				}
				else
				{
					//���is_selected �w�g�Otrue �� �]�N�O���n�Hcomposite ���A�[�J�� �o�̷�M���n�A�[�J					
				}				
			}
			//�@group �N�n�h��s�ثe��Ӫ��󪬺A
				//CREITICAL: �[�J�s���ͪ� composite object �@�w�n�̫�~���s���ͪ��[�J �_�h���logic �|��
				Next_object_status13.add(a_new_composite_obj);	
				//��s�ثe���object status!
				Current_object_status13 =Next_object_status13;
				
				//Record what composite object is stored.
				Group_Obj_History.add(a_new_composite_obj);
				
				Group_Degree++;
	}//Group 
	
	private List<drawing_class> Group_Obj_History = new ArrayList<drawing_class>();
	
	
	private drawing_class object_added_back;
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
			System.out.println("Group_Degree �w�g����0�]��Group ���A�^�A�Ʀܤp��0�F�A����A�Ѻc�F");
			return ; 
		}
		
	//a_new_composite_obj �N�O��A�hGroup �_�Ӫ����Ϊ��� �bUngroup�ɧڷ�M�n�hobject manager ���⥦���� ����]�����n���h 
		a_new_composite_obj = Group_Obj_History.get(Group_Degree);
		*/* If I am not wrong, I guess some error happens as using 'Group_Degree' to select the composite object.
		 / The composite_obj is not selected as expected~*/
		
		
		//��쥻������composite object. 
		Remove_Obj_In_Object_Mang(a_new_composite_obj);
		System.out.println("Group_degree is :" + Group_Degree );
		
		if(Group_Degree < 0) return; 
		
		objects_in_group = a_new_composite_obj.Get_Containing_Obj();
		
		
		for(int i = 0 ; i< objects_in_group .size() ; i++)
		{
			Current_object_status13.add(objects_in_group.get(i));		
			
		}
		//�����[�^�h��A�̷�Depth �hsort �O��Ѳ`��L���ʽ�
			Collections.sort(Current_object_status13);
	}
	
	/*
	 * �qCurrent_object_status13�� �hremove���@�� object. 
	 */
	//private drawing_class removed_target;
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
	//HERE
	//ajdsfkj DEMO probably OK but while the interleaving situation happens to group && ungroup, some bug still occus 
	//���D�b��group �R�������� �M degree �S���P�B�� �٦�...�A��Degree msg �O���O�i�H��@��xd...
	
	
	//SHIT! �ڦѬO�ѰO�I �F��|�W�[�p�G�u�O�^�줧�e��state �|�� �sstate�[���F�� �˱�...
	//private List<List<drawing_class>> Group_History = new ArrayList<List<drawing_class>>();
	private int Group_Degree = 0; 
	private boolean is_composite_object_exist = true;
	
	private List <drawing_class> temp = new ArrayList<drawing_class>();
	

	
	
	
	/* �o��method�O�hreplay ���e�����p �@�Ӥ@�� ���Ocell������
	public void Ungroup()
	{
		
		
		if(History <= 0 )	//���i�H�A�Ѻc ,or Null exception. 
		{
			System.out.println("���i�H�A�Ѻc �F �]���w�g��History 0 �@�}�l�S��Group �����ΤF");
			return ;
		}
		
		Object_status_history.remove(History);
		//�h�o��ثe�̷s��object status. 
		History--;
		Current_object_status13 =Object_status_history.get(History); 
	}
	*/
	
	
	
	//default �w�g��repaint�F �ҥH���n�Ψ��ӦW�r �γo�ӳ�I ���M�|��. repaint won't do that work. 

	//Test Code Area:	
		public void test_manager13_size()
		{
			System.out.println("Current_object_status13's size is :\n ");
			System.out.println("\n" + Current_object_status13.size() +"\n");
				
		}
		
		public void print_List_drawing_obj_depth(List<drawing_class>  pr )		
		{			
			System.out.print("The depth is:\n");
			for(int i = 0; i < pr.size(); i++)
			{
				System.out.print(pr.get(i).get_Depth()+"	" );
			}
		}
		
		public void test_canvas()
		{
			System.out.println("MODE in canvas obj is: " + MODE);
		}
}//CLASS BRACKET!
