package dao;

public class CommonDAO 
{
	
	public int getRole(int id)
	{
		int role=0;
		if(id==1)
		{
			role=1;
			return role;
		}
		else if(id>=2 && id<3000000)
		{
			role=2;
			return role;
		}
		else if(id>=3000000 && id<5000000)
		{
			role=3;
			return role;
		}
		else
		{
			role=4;
			return role;
		}
		
	}

}
