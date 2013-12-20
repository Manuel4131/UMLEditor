
/*
 * 
 * **About Depth: ����connection line�Ө��٬O���Ҭ����`�� ���M�b�o��case�� �S���ܥX�ӥL���S�� 
 *                ��default �N�O�]99 
 * */



import java.awt.Graphics;
import java.awt.Rectangle;


public class Connection_line extends drawing_class
{
//���VStart �MEnd drawing_class ������reference. 
	public drawing_class Start; 
	public drawing_class End;
//Connection line�� �_���I
	public Point start_point = new Point();
	public Point end_point = new Point();
	public static Graphics Draw ;
//PORT!
	public Port Link_Start_Port;
	public Port Link_End_Port;
	
	@Override
	public void draw(){};
	@Override
	public void Set_depth() {};
	@Override
	public void setLeft_up(int left_up_x, int left_up_y) {};
	@Override //basically not implemented here, default: set return false; 
	public boolean update_range_obj_state(Rectangle selection_range) 
	{
		return false;
	}
	
	/**
	 * @return ���oconnection line��Start Port.
	 */
	public Port Get_Start_Port()
	{
		return Link_Start_Port;
	}
	
	/**
	 * @return ���oconnection line��End Port.
	 */
	public Port Get_End_Port()
	{
		return Link_End_Port;
	}
	public Connection_line() 
	{
			super(99);
	}
	public static void set_Draw(Graphics g)
	{
		Draw = g; 
	}
	
	/**
	 * ���u���_�IStart ���� Start�� drawing class
	 */
	public void Set_Start(drawing_class Start)
	{
		this.Start = Start;
	}
	
	/**
	 * ���u�����IEnd ����End ��drawing class
	 */
	public void Set_End(drawing_class End)
	{
		this.End = End;
	}
	

	/**Set start point
	���@��Point �i�� �M���|set�nConnection_line ���_�I
	*/
	public void setStart_point(Point start_point) {
		this.start_point.setX( start_point.getX() );
		this.start_point.setY( start_point.getY() );
	}
	
	/**Set end point
	���@��Point �i�� �M���|set�nConnection_line ���_�I
	*/
	public void setEnd_point(Point end_point) 
	{
		this.end_point.setX( end_point.getX() );
		this.end_point.setY( end_point.getY() );
	}

	//�I���@���u
	/**
	 * ���M���@���h �@���u �O���|�����������]�i���ڥu�|�bconsole window �����������ۤv�^ 
	 * �����٬O�g �]������ �i���|�Ψ� ��ĵ�O�o�@���Ʊ��I 
	 * �u�Q���첣�� ���� �[�J/ �Ѻccomposite ���Orange_select�b�M�w���C
	*/ 
	@Override				
	public boolean is_selected(int press_coordinate_x, int press_coordinate_y) {
		//���Ӧp�G�����I�u�����εo�ͮ� �b�Ҷq�����g �]��spec�S�����n������ �S���n�D�I
		return false;
	};
	
	/**Get start point.
	 */
	public Point getStart_point() {
		return start_point;
	}
	
	/**Get start point.
	 */
	public Point getEnd_point() {
		return end_point;
	}

	/**
	 * �{���S���QSelection rectangle�]�� 
	 * @param range
	 * @return
	 */
	public boolean is_within_a_range(Rectangle range)
	{
		if( ( range.contains(start_point.getX(), start_point.getY() ) ) && ( (range.contains(end_point.getX(), end_point.getY() )  )))
		{
			return true; 
		}
		else
		{
			return false;
		}
	}
	
	/**
	 *   �u�u���_�I�b�d����
	 */
	public boolean Start_in_range(Rectangle range)
	{
		if(range.contains(start_point.getX(), start_point.getY()) && !range.contains(end_point.getX(), end_point.getY()))
		{			
			return true;
		}
		return false;		
	}
	
	/**
	 *   �u�u�����I�b�d����
	 */
	public boolean End_in_range(Rectangle range)
	{
		if(range.contains(end_point.getX(), end_point.getY())  && !range.contains(start_point.getX(), start_point.getY()))
		{			
			return true;
		}
		return false;		
	}

	@Override
	public void select()   {is_selected = true;}

	@Override
	public void unselect() {is_selected = true;}

}
