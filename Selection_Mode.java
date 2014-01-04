

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
		//UML_Editor_13.set_mouse_pos( mouse_pos_str);
	}
	
	//OverLOADING For MouseDrag
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
	//Successively update the segment coordinate. 
		move_segment_start_x = press_x;
		move_segment_start_y = press_y;
		
//Critical 		
	//Show Current_Pos
		Show_Current_Pos(); 
		
	//Critical: press_on_obj  
		press_on_obj = canvas.update_objects_state(press_x,press_y);
		
	//��o�I�쪺���� �άO�S�I��F��^�� ��Repaint();
		if(press_on_obj == false)
		{
			System.out.println("NOT PRESSED ON OBJ\n");
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
		
	// Interaction: �����I��@��}�A���W�N���_port���\��C �]�n���I��쪫��~���I �S�I�� �N�|��null.set~ ---> error. 
		if(press_obj != null) press_obj.set_draw_port_true();					
		
	//For ��X minimum rectangle ���Ѽ� �MGroup �f�ΡG Reset variables: �I�줣�I�쪫�� ���M�� 
		canvas.store_all_selected_obj_x.clear();
		canvas.store_all_selected_obj_y.clear();
		canvas.for_find_min_rec.clear();
	
		if(press_x == released_x && press_y == released_y )	//�o�ɭԬO�N���I�@�U �ҥH�u�O�hupdate object state �M��N�u�J...
		{
			canvas.update_objects_state(released_x , released_y );
			System.out.println("Just click.\n");
			canvas.Repaint();
			return ; 
		}
		
		selection_range = canvas.produce_a_rectangle(press_x, press_y, released_x, released_y);
		
		if(press_on_obj == false)//�N�O�nrange select���N��N��F �p�G�L�@�}�l���I��F�� �S�h���� �A���ϳo���|�y�����ʮɤ]�|����@�Ǫ��� ���X
		{
			//update each object's state in the region.
			for(int i = 0; i <  (canvas.get_Current_Obj_Status()).size(); i++) //FC!
			{
				(canvas.get_Current_Obj_Status()).get(i).update_range_obj_state(selection_range);							
			}

			//�@�}�l�O�I�� ���Ū��~�|�i�J��o ��ަp�� ���@�U->update object state ��@�U-> update object state   
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
		 if(press_on_obj)		//�}�l���I��F�� �~�e �S���� ����out. 
		 {
	//���_��s move_vector.���_��s���ʦV�q�C
				move_vector.setX((double)(drag_x -  move_segment_start_x) );
				move_vector.setY((double)(drag_y -  move_segment_start_y) );
				move_segment_start_x = drag_x ;
				move_segment_start_y = drag_y ;  
    //�@������n�}�l���ʮ� �Aport �N���๺�F�A�ҥH draw port set false. �_�h�|�ܹ��ʽu(�p�e�a) 
				press_obj.set_draw_port_false();
	//�@�}�l���ʴN Update ���󪺥��W���y��
				press_obj.update_left_up_coo(move_vector);	
				press_obj.Update_Object_Port_Coo(move_vector);
				canvas.Repaint();
				
				//canvas.Paint_Moved_Obj_Lines(press_obj);
		 }
	}//mouseDragged
	
	@Override 
	public drawing_class Get_Pressed_Obj()
	{
		return press_obj; 
	}
}//The most outer {} 
