

import java.awt.Graphics;


public class Generalization_line extends Connection_line{
	
	Point t1;
	Point t2; 
	Point t3;
	Point unit_vector;

	Point normal_to_directional_vector;
	public Generalization_line(){}
	
	public Generalization_line(Port Link_Start_Port, Port Link_End_Port)
	{
		this.Link_Start_Port =Link_Start_Port ;
		this.Link_End_Port =Link_End_Port ;

	}

	public void set_Triangle_Points()
	{
		Point directional_vector = Link_End_Port.get_port_center().minus(Link_Start_Port.get_port_center());		//directional vector
		System.out.print("temp.get_length() is: "+ directional_vector.get_length() );   

		normal_to_directional_vector = directional_vector.Normal_vector();// BUT WE CAN"T BE SURE THE NORMAL VECTOR's Direction.
		t1 = Link_End_Port.get_port_center().minus(directional_vector.multiply(0.045));
		t2 = t1.add(normal_to_directional_vector .multiply(0.045));
		t3 = t1.minus(normal_to_directional_vector .multiply(0.045));
	}
	

	public void draw()
	{
		set_Triangle_Points();
		Draw.drawLine((int)Link_Start_Port.get_port_center().getX(), (int)Link_Start_Port.get_port_center().getY() , (int)t1.getX(), (int)t1.getY());
		Draw.drawLine((int)t2.getX(), (int)t2.getY() , (int)t3.getX(), (int)t3.getY());
		Draw.drawLine((int)t2.getX(), (int)t2.getY() , (int)Link_End_Port.get_port_center().getX(), (int)Link_End_Port.get_port_center().getY());
		Draw.drawLine((int)t3.getX(), (int)t3.getY() , (int)Link_End_Port.get_port_center().getX(), (int)Link_End_Port.get_port_center().getY() );
	}
}
