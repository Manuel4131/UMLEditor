
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class Port {
	
	private int Conn_Pt_x= 0;
	private int Conn_Pt_y= 0;
	private int lup_x = 0;
	private int lup_y = 0;
	private int square_shpae_width = 10;
	private static Graphics draw13;
	private boolean port_draw = true; 
	private List<Connection_line> Group_Of_Link_Lines = new ArrayList<Connection_line>();
	public Port(){}	
	public Port(int Conn_Pt_x, int Conn_Pt_y)
	{
		this.Conn_Pt_x = Conn_Pt_x;
		this.Conn_Pt_y = Conn_Pt_y;
		this.lup_x =this.Conn_Pt_x - this.square_shpae_width/2;
		this.lup_y =this.Conn_Pt_y - this.square_shpae_width/2; 
	}
	
	public void Update_Port_Center(Point move)
	{
		Conn_Pt_x = Conn_Pt_x + (int)move.getX();
		Conn_Pt_y = Conn_Pt_y + (int)move.getY();
	}
	
	public static void Set_Port_Graphics(Graphics cs)
	{
		draw13 = cs; 
	}
	
	public Point get_port_center()
	{
		return new Point(Conn_Pt_x, Conn_Pt_y);
	}
	
	public Port(int center_x, int center_y, Graphics canvas)
	{
		lup_x = center_x - square_shpae_width/2;
		lup_y = center_y - square_shpae_width/2;		
		draw13 = canvas; 
		if(draw13 ==null )
		{
			System.out.println("Port: canvas problem.");
		}
		else
		{
			//System.out.println("May not be canvas problem.");
		}
	}
	
	public void set_draw_port_false()
	{
		port_draw = false; 
	}
	
	public void set_draw_port_true()
	{
		port_draw = true; 
	}
	
	public void draw_port()
	{
		if(port_draw )
		{
			draw13.setColor(Color.BLACK);
			draw13.fillRect(Conn_Pt_x - square_shpae_width/2, Conn_Pt_y - square_shpae_width/2, square_shpae_width, square_shpae_width);
		}		
	}
	
	public void Add_Link_Lines(Connection_line line_added)
	{
		Group_Of_Link_Lines.add(line_added);
	}

	public List<Connection_line> Get_Group_Of_Link_Lines()
	{
		return Group_Of_Link_Lines;
	}
}
