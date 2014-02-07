import java.awt.event.MouseEvent;

public class Gen_Line_Mode extends  Mouse_Mode
{
	drawing_class Link_Starts_From_O, Link_Ends_At_O;
	Port Link_Starts_From_P, Link_Ends_At_P;
	Generalization_line A_New_Gen_Line;
	
	public Gen_Line_Mode()
	{
		System.out.println("GeneralizationLineMode.");
		Current_object_status = canvas.get_Current_Obj_Status(); 
	}

	@Override 
	public void mousePressed(MouseEvent e) 
	{
		press_x = e.getX();
		press_y = e.getY();
		press_obj = canvas.Return_Pressed_Obj(press_x , press_y );
		//Guard.
		if(press_obj == null) return; 
		
		Link_Starts_From_O = press_obj;
		Link_Starts_From_P  = press_obj.Get_Connection_Port(press_x , press_y );
		System.out.println("The coordinate of the connection port is:( " + Link_Starts_From_P.get_port_center().getX() +"," + Link_Starts_From_P.get_port_center().getY()+ ")");
	}
	
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		released_x = e.getX();
		released_y = e.getY();
		released_obj = canvas.Return_Pressed_Obj(released_x , released_y);
		if(released_obj == null) return; 
		
		Link_Ends_At_O = released_obj ;
		Link_Ends_At_P  = released_obj.Get_Connection_Port(released_x , released_y);
		System.out.println("The coordinate of the connection port is:( " + Link_Ends_At_P.get_port_center().getX() +"," + Link_Ends_At_P.get_port_center().getY()+ ")");
		
		//Guard
		if(Link_Starts_From_O == null ||Link_Ends_At_O == null || Link_Starts_From_O == Link_Ends_At_O ) return; 
		else														//to create a new generalization line.
		{
			A_New_Gen_Line = new Generalization_line( Link_Starts_From_P,Link_Ends_At_P );
			canvas.Add_To_Line_Mang(A_New_Gen_Line );
			A_New_Gen_Line.Get_Start_Port().Add_Link_Lines(A_New_Gen_Line );
			A_New_Gen_Line.Get_End_Port().Add_Link_Lines(A_New_Gen_Line );
			A_New_Gen_Line.draw();			
		}		
	}
}