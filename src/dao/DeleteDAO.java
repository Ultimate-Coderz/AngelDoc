package dao;

public class DeleteDAO
{
	
	public void deleteCoordinator(int id)
	{
		CoordinatorDAO cdao=new CoordinatorDAO();
		cdao.delete(id);
	}
	
	public void deleteDoctor(int id)
	{
		DoctorDAO ddao=new DoctorDAO();
		ddao.delete(id);
	}
	
	public void deletePatient(int id)
	{
		PatientDAO pdao=new PatientDAO();
		pdao.delete(id);
	}

}
