package dao;

import models.Project;

public interface DAOGeneric {

	public abstract int countProjectsInProgress();	
	public abstract Object[] getProjectsInProgress();	
	public abstract Project getProject(int id);
	
}
