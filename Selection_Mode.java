import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class Selection_Mode extends Mouse_Mode
{
	Rectangle selection_range;
	String mouse_pos_str= " ";	
	public Selection_Mode()	{}	
	public void Show_Current_Pos()
	{
		mouse_pos_str= "( " + press_x + " , " + press_y + " )";
	}
	 
	public void Show_Current_Pos(int drag_x, int drag_y)
	{
		mouse_pos_str= "( " + drag_x + " , " + drag_y + " )";
		//UML_Editor_13.set_mouse_pos( mouse_pos_str);
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		press_x = e.getX();
		press_y = e.getY();

//Critical Interaction Code:
		move_segment_start_x = press_x;
		move_segment_start_y = press_y;
		
//Critical 		
	//Show Current_Pos
		Show_Current_Pos(); 
	//Critical: press_on_obj  
		press_on_obj = canvas.update_objects_state(press_x,press_y);
		if(press_on_obj == false)
		{
			canvas.Repaint();
			return ; 
		}
		else
		{
			Current_object_status= canvas.get_Current_Obj_Status();
			press_obj = Current_object_status.get(canvas.Select_which_object());
			canvas.Repaint();
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		released_x = e.getX();
		released_y = e.getY();
		Show_Current_Pos(); 
		
		if(press_obj != null) press_obj.set_draw_port_true();					
		canvas.store_all_selected_obj_x.clear();
		canvas.store_all_selected_obj_y.clear();
		canvas.for_find_min_rec.clear();
	
		if(press_x == released_x && press_y == released_y )	
		{
			canvas.update_objects_state(released_x , released_y );
			System.out.println("Just click.\n");
			canvas.Repaint();
			return ; 
		}		
		selection_range = canvas.produce_a_rectangle(press_x, press_y, released_x, released_y);		
		if(press_on_obj == false)
		{
			//update each object's state in the region.
			for(int i = 0; i <  (canvas.get_Current_Obj_Status()).size(); i++)
			{
				(canvas.get_Current_Obj_Status()).get(i).update_range_obj_state(selection_range);							
			}   
			canvas.Repaint();
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
	//To show current position in left up position in JFrame. 
		drag_x = e.getX();
		drag_y = e.getY();
		Show_Current_Pos(drag_x, drag_y);
		System.out.println("MouseDrag");
	//SET Basic parameter
		//Guard:
		 if(press_on_obj)		 
		 {
				move_vector.setX((double)(drag_x -  move_segment_start_x) );
				move_vector.setY((double)(drag_y -  move_segment_start_y) );
				move_segment_start_x = drag_x ;
				move_segment_start_y = drag_y ;  
				press_obj.set_draw_port_false();
				press_obj.update_left_up_coo(move_vector);	
				press_obj.Update_Object_Port_Coo(move_vector);
				canvas.Repaint();
		 }
	}//mouseDragged
	
	@Override 
	public drawing_class Get_Pressed_Obj()
	{
		return press_obj; 
	}
} 
