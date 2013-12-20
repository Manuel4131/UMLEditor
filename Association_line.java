
import java.awt.Graphics;


public class Association_line extends Connection_line{
/* Variables in the parent:
 * 	private Point start_point = new Point();
 *	private Point end_point= new Point();
 */
	public Association_line(){}
	
	public Association_line(Port Link_Start_Port, Port Link_End_Port)
	{
		this.Link_Start_Port =Link_Start_Port ;
		this.Link_End_Port =Link_End_Port ;
	}
	

	public void draw()
	{
		int Start_x = (int)Link_Start_Port.get_port_center().getX();
		int Start_y = (int)Link_Start_Port.get_port_center().getY();
		int End_x = (int)Link_End_Port.get_port_center().getX();
		int End_y = (int)Link_End_Port.get_port_center().getY();
		Draw.drawLine(Start_x ,Start_y,End_x ,End_y );
	}
}
