

import java.awt.Graphics;

/*
 * �o�̨�����ӭn��  Link_Start_Port.get_port_center() ���ާ@ �۰ʤ� ...
 * 1. �A���藍�i�H�Q�]�@��start_point, end_point ���ެOx,y �άOPoint �M��"��bconstructor"�N�F��
 *    �]��constructor �u�|�b�A�إߪ��󤧪����@���I �ҥH�p�G�A�C���ƹ��h�ʥ� �N���|update�� coordinate�F�I
 * 2. ���D �A��constructor�h�إ�coordinate�����Ӱʧ@ ���X�� �榨�@��method 
 *    ��C���Qinvoke �ɴNinvoke����method �h"""�T�w��y�а�update"""
 *    �N���ڳo�̳o�ˡG�T�w�h��center point coordinate. 
 */

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
	//�]���γ��V�q�|���@�ӫ��Y�������D �N�O�A��X�����V�q�|�Ӥp �Y�K�ڤ��Ѥw�g���double�F �L�٬O�����ন0
	//�ҥH�O�|��0 �ҥHend_point t1 �L�k�h�Ϥ� ��Lt2, t3�]�O
	//�ҥH��Τ�V�V�q�� 1/n �o�ˤ]�i�H�� ��i�H�׶}�L�������ڥ|�ˤ��J���ۼ� �o�O�{��������
	//���԰O�b��!
	//0.09�O���v�I �o�ӼƦr�U�j ����F���h �T���α�j
	public void set_Triangle_Points()
	{
		Point directional_vector = Link_End_Port.get_port_center().minus(Link_Start_Port.get_port_center());		//directional vector
		System.out.print("temp.get_length() is: "+ directional_vector.get_length() );   
		//unit_vector = temp .divide(temp.get_length());  //directional vector divide its' length.
		normal_to_directional_vector = directional_vector.Normal_vector();// BUT WE CAN"T BE SURE THE NORMAL VECTOR's Direction.
		t1 = Link_End_Port.get_port_center().minus(directional_vector.multiply(0.045));
		t2 = t1.add(normal_to_directional_vector .multiply(0.045));
		t3 = t1.minus(normal_to_directional_vector .multiply(0.045));
	}
	
	//���I�]�w�n�� �|call draw() �~�|call set_Triangle_Points() �o�ɦ]���I�w�g�]�w�n�F �ҥH���|��null ������ 
	//��O�p�G�S���ﲣ�ͪ��u���I���]�w �Ncall set_Triangle_Points() || draw() �|��null exception.
	public void draw()
	{
		set_Triangle_Points();
		Draw.drawLine((int)Link_Start_Port.get_port_center().getX(), (int)Link_Start_Port.get_port_center().getY() , (int)t1.getX(), (int)t1.getY());
		Draw.drawLine((int)t2.getX(), (int)t2.getY() , (int)t3.getX(), (int)t3.getY());
		Draw.drawLine((int)t2.getX(), (int)t2.getY() , (int)Link_End_Port.get_port_center().getX(), (int)Link_End_Port.get_port_center().getY());
		Draw.drawLine((int)t3.getX(), (int)t3.getY() , (int)Link_End_Port.get_port_center().getX(), (int)Link_End_Port.get_port_center().getY() );
	}
}
