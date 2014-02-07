
import java.awt.*;

import javax.swing.*;
import java.util.*;
import java.util.List;


public class Shape extends Basic_Object
{
/*
 * Parent drawing_class variable: 
 *  public static Graphics draw13;			//private variable "can't"" be inherited.
	public List<Port> show_selection_port = new ArrayList<Port>(); //name this since you can represent an object is selected by showing more ports of it. 
	public boolean is_selected = false;
 */	
	private final int first_separate_line_distance= 30;	 
	private final int second_separate_line_distance= 55;
	private final int draw_obj_name_shift_x = 13;
	private final int draw_obj_name_shift_y = 13;
	private Port draw_port; 

	public Shape(int depth, int left_up_x, int left_up_y)
	{
		super(depth);			//default set: set depth to 99	, Once you created a constructor, you have to fill the parent's instance field.
		this.left_up_x =left_up_x;
		this.left_up_y =left_up_y;
		Conn_Port = new ArrayList();
		Create_Port();
	}
	
	public void Create_Port()
	{
				Conn_Port.add(new Port(left_up_x, left_up_y + CD_height/2));		   	//Region 1 port
				Conn_Port.add(new Port(left_up_x+ CD_width/2, left_up_y + CD_height)); 	//Region 2 port
				Conn_Port.add(new Port(left_up_x+ CD_width, left_up_y + CD_height/2)); 	//Region 3 port
				Conn_Port.add(new Port(left_up_x+ CD_width/2, left_up_y ));			   	//Region 4 port
	}
	public Point get_right_down() 
	{
		return new Point(left_up_x + CD_width, left_up_y + CD_height);
	}

	/**
	 * This method corresponds to the inInside which the teach has already named it...
	 * The purpose is to check the press || released coordinate is within this object or not. 
	 */
	@Override
	public boolean is_selected(int press_coordinate_x,int press_coordinate_y )
	{
		if( ( (left_up_x <= press_coordinate_x) && (press_coordinate_x <= left_up_x + this.CD_width) )  &&((left_up_y <= press_coordinate_y )&&(press_coordinate_y <= left_up_y + this.CD_height) ) )
		{
			return true;
		}
			return false; 
	}
	
	@Override	
	public boolean update_range_obj_state(Rectangle selection_range) 
	{
		if(selection_range.contains( new Rectangle(this.left_up_x, this.left_up_y,this.CD_width, this.CD_height) )   )
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
	 * @param x y are the input coo which was clicked within a basic object.   
	 * @return Port. 
	 */
	public Port Get_Connection_Port(int input_x, int input_y )
	{
		double judge_value1 = 0;
		double judge_value2 = 0;
		
//Set two lines within one basic object.

	//set line 1
		Point line1_start = new Point((double)this.left_up_x,(double)this.left_up_y);
		Point line1_end = new Point(this.left_up_x +this.CD_width,this.left_up_y + this.CD_height );
		double slope1 =  ( line1_end.getY() - line1_start.getY() ) / ( line1_end.getX() - line1_start.getX() ) ;
		judge_value1 = input_y - line1_start.getY() - slope1 * (input_x - line1_start.getX() );
		
	//set line 2
		Point line2_start = new Point((double)this.left_up_x,(double)(this.left_up_y + this.CD_height ));
		Point line2_end = new Point((double)(this.left_up_x +this.CD_width),(double)(this.left_up_y ));
		double slope2 =  ( line2_end.getY() - line2_start.getY() ) / ( line2_end.getX() - line2_start.getX() ) ;
		judge_value2 = input_y - line2_start.getY() - slope2 * (input_x - line2_start.getX() );
		
	//return the port.
		if(judge_value1 >= 0 && judge_value2 <= 0)	//region 1 Point
		{
			de_Get_connection_point_bug(1);
			return Conn_Port.get(0);
		}
		else if(judge_value1 > 0 && judge_value2 > 0)	//region 2 Point
		{
			de_Get_connection_point_bug(2);
			return Conn_Port.get(1);
		}
		else if(judge_value1 <= 0 && judge_value2 >= 0)  //region 3 Point
		{
			de_Get_connection_point_bug(3);
			return Conn_Port.get(2);
		}
		
		else if(judge_value1 < 0 && judge_value2 < 0)	//region 4 Point
		{
			de_Get_connection_point_bug(4);
			return Conn_Port.get(3);
		}
		else
		{
			System.out.println("FATAL ERROR in Shape::Get_Connection_Port ");
			return null; 
		}		
	}
	
	public void de_Get_connection_point_bug(int region)
	{
		System.out.print("In region:" + region+"\n");
	}
	public  static void set_which_Panel_to_be_drawn(Graphics point_to)
	{
		draw13 = point_to;		
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub

		draw13.setColor(Color.WHITE);
		draw13.fillRect(left_up_x, left_up_y, CD_width, CD_height);
		draw13.setColor(Color.BLACK);
		draw13.drawRect(left_up_x, left_up_y, CD_width, CD_height);
		draw13.setColor(Color.BLACK);
		draw13.drawLine(left_up_x, left_up_y + first_separate_line_distance,left_up_x + CD_width , left_up_y + first_separate_line_distance);
		draw13.drawLine(left_up_x, left_up_y + second_separate_line_distance, left_up_x + CD_width , left_up_y + second_separate_line_distance);
		
		//draw port part.
		if(is_selected)
		{
			for(int i= 0; i< Conn_Port.size(); i++)
			{
				Conn_Port.get(i).draw_port();
			}
		}
	//Give the class a name: Animal, Dog...
		draw13.drawString(object_name, left_up_x + draw_obj_name_shift_x, left_up_y + draw_obj_name_shift_y);
	}
}