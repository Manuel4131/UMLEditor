
//�L�٬O���ۤv�Ǫ���� �]���ڦ��Ӧh�Ȼs�ƪ��n�D�F? 
public class Point {
	
	private double x = 0;
	private double y = 0;
	
	public Point(){}
	
	public Point(double x, double y)
	{		
		this.x = x;
		this.y = y;
	}
	

	public double getX() 
	{
		return x;
	}
	
	public double getY() 
	{
		return y;
	}

	public void setX(double f) 
	{
		this.x = f;
	}

	public void setY(double y) 
	{
		this.y = y;
	}
	
	public boolean equal(Point compared)
	{
		if(this.x == compared.getX()&& this.y == compared.getY())
		{
			return true; 
		}
		else
		{
			return false; 
		}			
	}
	
	public float get_length()
	{
		return (float)Math.sqrt(x*x + y*y); 
	}
	
	public Point minus(Point m)
	{
		return new Point(x - m.getX(), y - m.getY());
	}
	
	public Point add(Point m)
	{
		return new Point(x + m.getX(), y+ m.getY());
	}
	
	public Point divide(double value)
	{
		return new Point((int) (x/value) , (int)(y/value) );
	}
	
	public Point multiply(double d)
	{
		return new Point( (x *  d), y *  d);
	}
	
	//�䤤�@�䪺normal vector �Y�i  
	public Point Normal_vector()
	{
		return new Point( -1* y, x);
	}
}
