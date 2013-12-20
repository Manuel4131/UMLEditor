
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Use_case extends Basic_Object 
{
	/*
	 * Parent drawing_class variable: 
	 *  public static Graphics draw13;			//private variable "can't"" be inherited.
		public List<Port> show_selection_port = new ArrayList<Port>(); //name this since you can represent an object is selected by showing more ports of it. 
		public boolean is_selected = false;
	 */
	

	private final int draw_obj_name_shift_x = 20; //��left_up_x left_up_y ��_�Ӫ��۹�첾 �[�W�h �N�Oobject name �e��m�m
	private final int draw_obj_name_shift_y = 20;
	
	//constructor 
	public Use_case(int depth,int left_up_x, int left_up_y)
	{
		super(depth);		//�@�}�lset depth ��99 
		this.left_up_x =left_up_x;
		this.left_up_y =left_up_y;
		Conn_Port = new ArrayList();
		Create_Port();
	}
	
	public void Create_Port()
	{
		Conn_Port.add(new Port(left_up_x, left_up_y + UC_height/2));		   //Region 1 port
		Conn_Port.add(new Port(left_up_x+ UC_width/2, left_up_y + UC_height)); //Region 2 port
		Conn_Port.add(new Port(left_up_x+ UC_width, left_up_y + UC_height/2)); //Region 3 port
		Conn_Port.add(new Port(left_up_x+ UC_width/2, left_up_y ));			   //Region 4 port	
	}
	
	//�p�G������b���rectangular�d�� �hreturn true �_�hreturn false. �ǤJ���i�Orectangle.
	@Override	//critical reference01	
	public boolean update_range_obj_state(Rectangle selection_range) 
	{
		//2.0 support:
		if(selection_range.contains( new Rectangle(this.left_up_x, this.left_up_y,this.UC_width, this.UC_height) )   )
		{
			is_selected = true;
			return true; 
		}
		else
		{
			is_selected = false;
			return false; 
		}

	}
	
	/**
	 * This method corresponds to the inInside which the teach has already named it...
	 * The purpose is to check the press || released coordinate is within this object or not. 
	 */
	@Override
	public boolean is_selected(int press_coordinate_x,int press_coordinate_y )	//�p�p�a���������ή��䪺���@�p�p������,�N��L��@ �Ouse_case���@���� don't know ok or not?
	{
		if( ( (left_up_x <= press_coordinate_x) && (press_coordinate_x <= left_up_x + this.UC_width) )  &&((left_up_y <= press_coordinate_y )&&(press_coordinate_y <= left_up_y + this.UC_height) ) )
		{
			return true;
		}
		
		return false; 
	}
	

	//Set which component to be drawn by calling a method.
	public static void set_which_Panel_to_be_drawn(Graphics point_to)
	{
		draw13 = point_to;		
	}
	

	@Override 	
	public Point get_right_down() 
	{
		return new Point(left_up_x + UC_width, left_up_y + UC_height);
	}
	
	
	@Override
	public void draw() 
	{
		//System.out.println("Call draw method(Use_case)?\n");
		
		draw13.setColor(Color.WHITE);
		draw13.fillOval(left_up_x, left_up_y, UC_width, UC_height);
		
		draw13.setColor(Color.BLACK);
		draw13.drawOval(left_up_x, left_up_y, UC_width, UC_height);
		if(is_selected)
		{
			for(int i= 0; i< Conn_Port.size(); i++)
			{
				Conn_Port.get(i).draw_port();
			}
		}
		//show_selection_port.clear();
		//draw object name part.
		draw13.drawString(object_name, left_up_x + draw_obj_name_shift_x, left_up_y + draw_obj_name_shift_y);
	}
	
	@Override
	public Port Get_Connection_Port(int input_x, int input_y )
	{
		double judge_value1 = 0;
		double judge_value2 = 0;
		
		//set line 1
				Point line1_start = new Point((double)this.left_up_x,(double)this.left_up_y);
				Point line1_end = new Point(this.left_up_x +this.UC_width,this.left_up_y + this.UC_height );
				double slope1 =  ( line1_end.getY() - line1_start.getY() ) / ( line1_end.getX() - line1_start.getX() ) ;
				judge_value1 = input_y - line1_start.getY() - slope1 * (input_x - line1_start.getX() );
				
				System.out.println("judge_value1 is :" + judge_value1+"\n" );
		//set line 2
				Point line2_start = new Point((double)this.left_up_x,(double)(this.left_up_y + this.UC_height ));
				Point line2_end = new Point((double)(this.left_up_x +this.UC_width),(double)(this.left_up_y ));
				double slope2 =  ( line2_end.getY() - line2_start.getY() ) / ( line2_end.getX() - line2_start.getX() ) ;
				judge_value2 = input_y - line2_start.getY() - slope2 * (input_x - line2_start.getX() );
				System.out.println("judge_value2 is :" + judge_value2+"\n" );
	
		//To get Port
				if(judge_value1 >= 0 && judge_value2 <= 0)	//region 1 Point
				{
					return Conn_Port.get(0);					
				}
				
				else if(judge_value1 > 0 && judge_value2 > 0)	//region 2 Point
				{
					return Conn_Port.get(1);
				}
				
				else if(judge_value1 <= 0 && judge_value2 >= 0)  //region 3 Point
				{
					return Conn_Port.get(2);
				}
				
				else if(judge_value1 < 0 && judge_value2 < 0)	//region 4 Point
				{
					return Conn_Port.get(3);
				}
				else
				{
					System.out.println("FATAL ERROR in Use_case::Get_Connection_Port ");
					return null; 
				}
	}
	
	@Override 
	public void set_draw_port_false()
	{
		for(int i= 0; i< Conn_Port.size(); i++)
		{
			Conn_Port.get(i).set_draw_port_false();
		}
	}
}//class: Use_case.


