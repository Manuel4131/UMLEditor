
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Composite_Object extends drawing_class{
   
	public List<Connection_line > a_inner_line_record; 
	private List<drawing_class> Containing_Obj = new ArrayList<drawing_class>();
	
	public Composite_Object(int depth) 
	{
			super(depth);
    }
	
	/** 
	 * @param added_obj
	 */
	@Override
	public void Add_An_Obj(drawing_class added_obj)
	{
		Containing_Obj.add(added_obj);
		Collections.sort(Containing_Obj);
	}
	
	/**
	 * Composite Object
	 * @return
	 */
	@Override 
	public List<drawing_class> Get_Containing_Obj()
	{
		return Containing_Obj; 
	}

	/**
	 * Update left up side coordinate. 
	 */
	@Override 
	public void update_left_up_coo(Point move_vector)
	{
		for(int i = Containing_Obj.size() -1 ; i>= 0;i--)
		{
			Containing_Obj.get(i).update_left_up_coo(move_vector);
		}
	}
	
	@Override 
	public void Update_Object_Port_Coo(Point move_vector)
	{
		for(int i = Containing_Obj.size() -1 ; i>= 0;i--)
		{
			Containing_Obj.get(i).Update_Object_Port_Coo(move_vector);
		}
	}
	
	public void draw() 
	{
		for(int i = Containing_Obj.size() -1 ; i>= 0;i--)
		{
			Containing_Obj.get(i).draw();
		}	
	}

	//Any element is selected? Yes: true, No: false. NO UPDATE is_selected.
	/**
	 * Check each component: if any component in Containing_Obj' is_selected is true then return true; 
	 */
	public boolean is_selected(int press_coordinate_x,int press_coordinate_y )
	{
		drawing_class check;
		for(int i = Containing_Obj.size() -1 ; i>= 0;i--)
		{
			if( Containing_Obj.get(i).is_selected(press_coordinate_x,press_coordinate_y ))
			{
				return true;
			}
		}
				return false;
	}
	
	
	public boolean update_objects_state(int x, int y)
	{
		int index = Containing_Obj.size() - 1;
		boolean rt_value = false; 
		while(index >= 0)
		{
			if(Containing_Obj.get(index).is_selected(x, y) == true)		//������@�ӬO ���N���O�F 
			{
				for(int i = 0; i < Containing_Obj.size();i++)
				{
					Containing_Obj.get(index).select();
				}
				this.is_selected = true; 
				return true;  
			}
			else
			{	
				Containing_Obj.get(index).unselect();
			}
			index--;			
		}//while loop
		
		this.is_selected = false;
		return false; 		
	}
	
	@Override
	/**
	 * ���Uselect() �|update �ۤvis_selected ��true;
	 */
	public void select() 
	{
		this.is_selected = true; 
		for(int i = Containing_Obj.size() -1 ; i>= 0;i--)
		{
			Containing_Obj.get(i).select();
		}
	}
	
	@Override
	/**
	 * ���Uunselect() �|update �ۤvis_selected ��false;
	 */
	public void unselect() 
	{
		this.is_selected = false; 
		for(int i = Containing_Obj.size() -1 ; i>= 0;i--)
		{
			Containing_Obj.get(i).unselect();
		}	
	}

	/**
	 * To show how many items are grouped.
	 */
	@Override
	public int how_many_items()
	{
		return Containing_Obj.size();		
	}
	/**
	 * As the Composite Object creation criteria is not satisfied, the new object should be killed.
	 * Although I can't kill it. But I still remain my habit to clear the Containing_Obj.
	 */
	@Override
	public void destroy_self()
	{
		Containing_Obj.clear();
	}
	
	public boolean update_range_obj_state(Rectangle selection_range) 
	{
	//Within the range, to update each component's state. 
		for(int i = Containing_Obj.size() -1 ; i>= 0;i--)
		{
			Containing_Obj.get(i).update_range_obj_state(selection_range);			
		}
		
	//To find out if "composite object" is selected or not. 
		for(int i = Containing_Obj.size() -1 ; i>= 0;i--)
		{
	
			if( Containing_Obj.get(i).is_selected== false)
			{
				this.is_selected = false; 
				return false;
			}
		}	     	
		this.is_selected = true; 
		return true;
	}

	@Override
   public void set_is_selected_true()
   {
	   this.is_selected = true; 
   }
}
