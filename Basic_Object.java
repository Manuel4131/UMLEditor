

import java.util.List;

public class Basic_Object extends drawing_class
{
	//CD's width and height 
	public final int CD_width = 60;  
	public final int CD_height = 80; 
	
	//Use case's  width and height
	public final int UC_width = 67;  
	public final int UC_height = 41; 
	
	//The left up coordinate of the basic object.
	public int left_up_x = 0;
	public int left_up_y = 0;
	public Port draw_port; 
	
	public List<Port> Conn_Port; 
	
	public Basic_Object(int depth)
	{
		super(depth);
	}
	
	@Override 
	public void set_draw_port_false()
	{
		for(int i= 0; i< Conn_Port.size(); i++)
		{
			Conn_Port.get(i).set_draw_port_false();
		}
	}
	
	@Override   
	public void set_draw_port_true()
	{
		for(int i= 0; i< Conn_Port.size(); i++)
		{
			Conn_Port.get(i).set_draw_port_true();
		}
	}
	
	
	public int get_left_up_x() 
	{
		return left_up_x ;
	}
	 	
	public int get_left_up_y() 
	{
		return left_up_y ;
	}
	

	public void setLeft_up(int left_up_x,int left_up_y) 
	{
		this.left_up_x = left_up_x;
		this.left_up_y = left_up_y;
	}


	@Override
	public void update_left_up_coo(Point moving_vector) {
		this.left_up_x = this.left_up_x +  (int)moving_vector.getX();
		this.left_up_y = this.left_up_y +  (int)moving_vector.getY();
	}
	
	
		public Port Update_Port; 
		@Override
		public void Update_Object_Port_Coo(Point move_vector)
		{
			for(Port Update_Port:Conn_Port)
			{
				Update_Port.Update_Port_Center(move_vector);
			}
			return ; 
		}
	private List<Connection_line> group_of_link_lines; 
	private Connection_line a_line; 

	@Override 
	public void Draw_Link_Lines()
	{
		for(Port draw_port : Conn_Port)
		{
			group_of_link_lines = draw_port.Get_Group_Of_Link_Lines();
			for( Connection_line  a_line : group_of_link_lines )
			{
				a_line.draw();
			}
		}
	}//Draw_Link_Lines()
}
