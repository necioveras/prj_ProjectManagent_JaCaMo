// CArtAgO artifact code for project prjProjectManagement

package workspaces;

import models.Project;
import cartago.Artifact;
import cartago.OPERATION;
import cartago.OpFeedbackParam;
import dao.DAOGeneric;

public class EnvironmentDataProjects extends Artifact {
	
    private DAOGeneric dao = new DAOGeneric();
	
	private Object updateVal(){
		return dao.countProjectsInProgress();
	}
	
	private Object updateList(){
		return dao.getProjectsInProgress();
	}
	
	void init() {    //chamado quando o ambiente é criado
		//obter as credenciais para conectar o banco		
		defineObsProperty("countProjectsActive", updateVal());   //buscar o valor diretamente do banco de dados		 
		defineObsProperty("listOfProjectsActive", updateList());   //buscar o valor diretamente do banco de dados
	}
	
	@OPERATION
	void check(){         //checar as alterações promovidas
		getObsProperty("countProjectsActive").updateValue(updateVal());
		getObsProperty("listOfProjectsActive").updateValue(updateList());
	}
	
	@OPERATION
	void getInitialPlan(int id, OpFeedbackParam <Integer> timeProj, OpFeedbackParam <Double> budgetProj){
		Project p = dao.getProject(id);
		budgetProj.set(p.getBudget());
		timeProj.set(p.getTime());
	}
	
}

