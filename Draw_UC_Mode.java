// To implement the Mouse_Mode interface

import java.awt.event.MouseEvent;

//Draw USE CASE mode.
public class Draw_UC_Mode extends Mouse_Mode
{
	public Draw_UC_Mode()
	{
		System.out.println("Draw a use case.");
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		press_x = e.getX();
		press_y = e.getY();
		
		int new_depth = canvas.update_depth();
		Basic_Object a_use_case = new Use_case(new_depth,press_x ,press_y);
	//Update its' state: set the coordinate. 
		a_use_case.setLeft_up(e.getX(), e.getY());
	//add to Current_object_status13 in CANVAS
		canvas.get_Current_Obj_Status().add(a_use_case);
	//After updating, just to draw; THE PRINCIPLE: 1. Logic 2. Update Properties 3. Draw the new status of the obj. 
		a_use_case.draw();
	}
	
	//the following actions are useless for drawing a class diagram on Panel
	@Override
	public void mouseDragged(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}

