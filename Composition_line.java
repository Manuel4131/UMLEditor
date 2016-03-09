// Author: mchang

import java.awt.Graphics;

public class Composition_line extends Connection_line{

	Point p1;
	Point p2; 
	Point p3;
	Point pt;
	Point normal_to_directional_vector;

	public Composition_line(){}
	
	public Composition_line(Port Link_Start_Port, Port Link_End_Port)
	{
		this.Link_Start_Port =Link_Start_Port ;
		this.Link_End_Port =Link_End_Port ;

	}
	
	
	public void set_diamond()
	{
		Point directional_vector = Link_End_Port.get_port_center().minus(Link_Start_Port.get_port_center());		//directional vector
		System.out.print("temp.get_length() is: "+ directional_vector.get_length() );   
		//unit_vector = temp .divide(temp.get_length());  //directional vector divide its' length.
		normal_to_directional_vector = directional_vector.Normal_vector();
		pt = Link_End_Port.get_port_center().minus(directional_vector.multiply(0.045));
		p1=  Link_End_Port.get_port_center().minus(directional_vector.multiply(0.09));
		p2 = pt.add(normal_to_directional_vector .multiply(0.045));
		p3 = pt.minus(normal_to_directional_vector .multiply(0.045));
	}
	
	public void paintComponent(Graphics g)
	{
		set_diamond();
		Draw.drawLine((int)Link_End_Port.get_port_center().getX(), (int)Link_End_Port.get_port_center().getY() , (int)p2.getX(), (int)p2.getY());
		Draw.drawLine((int)Link_End_Port.get_port_center().getX(), (int)Link_End_Port.get_port_center().getY() , (int)p3.getX(), (int)p3.getY());
		Draw.drawLine((int)p2.getX(), (int)p2.getY() , (int)p1.getX(), (int)p1.getY());
		Draw.drawLine((int)p3.getX(), (int)p3.getY() , (int)p1.getX(), (int)p1.getY());
		Draw.drawLine((int)p1.getX(), (int)p1.getY() , (int)start_point.getX(), (int)start_point.getY());
	}
	
	public void draw()
	{
		set_diamond();
		Draw.drawLine((int)Link_End_Port.get_port_center().getX(), (int)Link_End_Port.get_port_center().getY() , (int)p2.getX(), (int)p2.getY());
		Draw.drawLine((int)Link_End_Port.get_port_center().getX(), (int)Link_End_Port.get_port_center().getY() , (int)p3.getX(), (int)p3.getY());
		Draw.drawLine((int)p2.getX(), (int)p2.getY() , (int)p1.getX(), (int)p1.getY());
		Draw.drawLine((int)p3.getX(), (int)p3.getY() , (int)p1.getX(), (int)p1.getY());
		Draw.drawLine((int)p1.getX(), (int)p1.getY() , (int)Link_Start_Port.get_port_center().getX(), (int)Link_Start_Port.get_port_center().getY());
	}
}
