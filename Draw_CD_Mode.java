

import java.awt.event.MouseEvent;

//Draw CLASS DIAGRAM Mode. 
public class Draw_CD_Mode  extends Mouse_Mode
{
	public Draw_CD_Mode()
	{
		System.out.println("Draw a class_diargram.");
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		press_x = e.getX();
		press_y = e.getY();
		
		int new_depth = canvas.update_depth();
		Shape a_class = new Shape(new_depth,press_x,press_y );
	//Update its' state: set the coordinate. 
		a_class.setLeft_up(e.getX(), e.getY());
	//add to Current_object_status13 in CANVAS
		canvas.get_Current_Obj_Status().add(a_class);
	//After updating, just to draw; THE PRINCIPLE: 1. Logic 2. Update Properties 3. Draw the new status of the obj. 
		a_class.draw();
		
	}
	
	//the following actions are useless for drawing a class diagram on Panel
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
