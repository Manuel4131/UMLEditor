

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

public abstract class Mouse_Mode implements MouseListener, MouseMotionListener{

	//Instance variable provided for various kind of handling mode
	/**
	 * Point to the Canvas Object!
	 */
	public static Edit_panel canvas; 
	public int press_x =0;
	public int press_y =0;
	public int released_x = 0;
	public int released_y = 0;
	public int drag_x = 0;
	public int drag_y = 0;
	public int move_segment_start_x = 0;
	public int move_segment_start_y = 0;
	public boolean press_on_obj = false;
	public boolean released_on_obj= false;
	public drawing_class press_obj; 
	public drawing_class released_obj;
	public Point move_vector = new Point(0,0);	
	public List<drawing_class> Current_object_status; 
	
	public static void set_canvas(Edit_panel canvas_)
	{
		canvas = canvas_;
	}
	
	/**
	 * �M��F��drawing_class�R�W�]�p�� method. ��L ������call �o��method. 
	 */
	public drawing_class Get_Pressed_Obj(){return null;}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
