// Agent sample_agent in project prjProjectManagement

/* Initial beliefs and rules */

/* Initial goals */

!create.

/* Plans */

+!create: true <- 
	?setupArtifact(ID).
	
+?setupArtifact(E) : true <-
   makeArtifact("env_DP", "workspaces.EnvironmentDataProjects", [], E);
   focus(E);
   ?countProjectsActive(V);   
   println("Percebi inicialmente um total de ", V, " projeto(s) ativo(s)");
   !monitoring.
	
-?setupArtifact(E) : true <-
	.wait(30);
	!create.	
	
+!monitoring : countProjectsActive(V)<-			
	.wait(3000);   
	check;	
	println("Monitorando....valor total de projeto(s) ativo(s): ", V);
	!analysisByProject;
	!monitoring.	

+!analysisByProject: listOfProjectsActive(L) <-
  for (.member(X, L)){
  	 print("Analisando projeto cujo id = ", X);
 	 getInitialPlan(X, Time, Budget);
 	 println(" tempo total = ", Time, " custo = ", Budget);
  }.
	
//perceptions of properties
+countProjectsActive(V).
+listOfProjectsActive(L).