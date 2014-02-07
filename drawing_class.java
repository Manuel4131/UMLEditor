import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
public class drawing_class  implements Comparable<drawing_class>{
	
	public int Depth = 99;	//�̲`
	public boolean is_selected = false;
	public static Graphics draw13 ;			//private variable "can't"" be inherited.
	public boolean is_selected(int press_coordinate_x,int press_coordinate_y ){return false;};
	public void draw(){};
	public void Set_depth(){};

	//pure declaration, no hoping to use this method.
	public Point Get_connection_point(int x, int y) {return new Point(-1,-1);};	
	public int get_left_up_x()	{return 0;}		
	public int get_left_up_y() {return 0;}
	public Point get_right_down() {return new Point(-1,-1);}
	public void setLeft_up(int left_up_x, int left_up_y) {};
	
	//Set all draw port with it not to be drawn
	public void set_draw_port_false(){};
	public void update_inner_line(Point moving_vector){};
	//pure for overridden 
	public void update_left_up_coo(Point moving_vector){}; 
	public List<Connection_line > connection_line_list = new ArrayList<Connection_line >(); 
	public String object_name= " ";  		
	public Port Get_Connection_Port(int input_x, int input_y ){return new Port();}
	
	//Constructor
	public drawing_class(){}
	public drawing_class(int depth)
	{
		Depth  = depth;
	}
	//set
	public int get_Depth() 
	{
		return Depth;
	}
	public void set_Depth(int depth) 
	{
		Depth = depth;
	}
	
	//String object_name
	public String getObject_name() 
	{
		return object_name;
	}

	public void setObject_name(String object_name) 
	{
		this.object_name = object_name;
	}

	public void add_the_connection_line(Connection_line a_new_connection_line)
	{
		connection_line_list.add(a_new_connection_line);
	}
	
	/**
	 * is_selected = true;
	 */
	public void select()
	{
		is_selected = true;
	}
	
	/**
	 * is_selected = false;
	 */
	public void unselect() {
		is_selected = false;		
	}

	public boolean update_range_obj_state(Rectangle selection_range) 
	{
		return false;
	}
	   
	@Override		
	public int compareTo(drawing_class n) 
	{
	   Integer DEPTH_at_this = Integer.valueOf(Depth); 
	   Integer DEPTH_at_that = Integer.valueOf(n.get_Depth()); 
	   int Cmp_value = DEPTH_at_that .compareTo(DEPTH_at_this );
	   return Cmp_value;
	}
		
	public List<drawing_class> get_components()
	{
		List<drawing_class> it_is_null = null;
		return it_is_null; 
	}
		
	//For upcasting. 
	public void Update_Object_Port_Coo(Point move_vector){};
	public void set_draw_port_true(){};
	public void Draw_Link_Lines(){};
	public void Add_An_Obj(drawing_class added_obj){};
	public void destroy_self(){};
	public void set_is_selected_true(){};
	public int how_many_items(){return 0;};
	public List<drawing_class> Get_Containing_Obj() {return null;};
}